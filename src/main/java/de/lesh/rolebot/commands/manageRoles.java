package de.lesh.rolebot.commands;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;
import de.lesh.rolebot.lib;
import de.lesh.rolebot.user.permittedList;
import java.io.BufferedWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
public class manageRoles extends ListenerAdapter {

  final static File langFile = new File("roles.txt");
	public final static Map<String, Long> languages = new HashMap<>();

    static {
        loadLanguages(langFile);
    }

    public static String location = "roles.txt";
    String OS = "os.name";

    private static void putDefaultLangs() {
        languages.put("java", 316323991646109698L);
        languages.put("c++", 316324124832301076L);
        languages.put("c#", 316324077533003786L);
        languages.put("python", 316324218482589696L);
        languages.put("php", 316324736806420480L);
        languages.put("javascript", 321762279244955658L);
        languages.put("lua", 323140804941971456L);
        languages.put("html5", 323145144620548096L);
        //languages.put("vb.net", 0L);
        languages.put("css", 323145260488327170L);
        languages.put("assembler", 323145448993062925L);
        languages.put("go", 330432150207725578L);
    }

    private static void loadLanguages(File file) {
        createLangFile(file);
        try (Scanner s = new Scanner(file)) {
            int lineNumber = -1; // Only for debugging/errorstream
            while (s.hasNextLine()) {
                lineNumber++;
                String line = s.nextLine().trim().split("#|//")[0];//Use # or // for line comments
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    System.err.println("[LANGUAGE]Unrecognized line(#" + lineNumber + "): \"" + line + "\". Ignoring");
                    continue;
                }
                if (!parts[0].matches("\\d+")) {
                    System.err.println("[LANGUAGE]Unrecognized role id(#" + lineNumber + "): \"" + parts[0] + "\"");
                    continue;
                }
                languages.put(parts[1], Long.parseLong(parts[0]));
                System.out.println("[LANGUAGE]Language \"" + parts[1] + "\" loaded with id " + parts[0] + ".");
            }
            System.out.println("[LANGUAGE]Loading done.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[LANGUAGE]Using default langs instead");
            putDefaultLangs();
        }
    }


    private static boolean saveLanguages(File file) {
        createLangFile(file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("# Version " + lib.dateFormat.format(new Date()));
            writer.newLine();
            for (String name : languages.keySet()) {
                writer.write(languages.get(name) + "");
                writer.write(":");
                writer.write(name);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[LANGUAGE]Cancelling save.");
            return false;
        }
        return true;
    }

    private static void createLangFile(File file) {
        try {
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    System.out.println("[LANGUAGE]Language dir created");
                }
            }
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("[LANGUAGE]Language file created");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[LANGUAGE]Using default langs instead");
            putDefaultLangs();
        }
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        if (e.getAuthor().isBot()) {
            return;
        }

        if (msg.getRawContent().startsWith(".save") && lib.uPerm.equals(e.getAuthor().getIdLong())) {
            e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
            boolean success = saveLanguages(langFile);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setAuthor("Language Management", null, e.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("Languages save" + (success ? "d successfully" : " failed."))
                    .setDescription("Read the log for further information")
                    .setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), e.getJDA().getSelfUser().getEffectiveAvatarUrl())
                    .build()).queue();
        }

        if (msg.getRawContent().startsWith(".add") && lib.uPerm.equals(e.getAuthor().getIdLong())) {
            e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
            String[] split = e.getMessage().getRawContent().split("\\s+", 3);
            if (split.length < 3) {
                return;
            }
            String name = split[1];
            long id = Long.parseLong(split[2]);
            languages.put(name, id);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setAuthor("Language Management", null, e.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("Language added successfully")
                    .setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), e.getJDA().getSelfUser().getEffectiveAvatarUrl())
                    .build()).queue();
        }
    }
}
