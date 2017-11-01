package de.lesh.rolebot.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import de.lesh.rolebot.lib;
import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChannelRegister extends ListenerAdapter{
	
	final static File chFile = new File("channels.txt");
	public final static Map<String, Long> channels = new HashMap<>();
	String OS = "os.name";
	public static String location = "channels.txt";
	
	static {
        loadChannelFile(chFile);
    }
	
	private static void setDefaultChannel(){
		channels.put("bot_rush", 316126394629357568L);
		channels.put("bot_spammer", 318372703662768131L);
	}
	
	private static void loadChannelFile(File file){
		createChannelFile(file);
		int lineNumber = -1;
		try(Scanner s = new Scanner(file)){
			while(s.hasNextLine()){
				lineNumber++;
				String line = s.nextLine().trim().split("#|//")[0];
				String[] parts = line.split(":");
				if(parts.length != 2){
					System.err.println("[CHANNEL]Unrecognized line(#" + lineNumber + "): \"" + line + "\". Ignoring");
					continue;
				}
				if(!parts[0].matches("\\d+")){
					System.err.println("[CHANNEL]Unrecognized role id(#" + lineNumber + "): \"" + parts[0] + "\"");
					continue;
				}
				channels.put(parts[1], Long.parseLong(parts[0]));
				System.out.println("[CHANNEL]Language \"" + parts[1] + "\" loaded with id " + parts[0] + ".");
			}
			System.out.println("[CHANNEL] >> Finished loading");
		}catch(IOException e){
            e.printStackTrace();
            System.err.println("[CHANNEL]Using default langs instead");
            setDefaultChannel();
		}
	}
	
	private static boolean setChannels(File file) {
		createChannelFile(file);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			writer.write("# Version " + lib.dateFormat.format(new Date()));
			writer.newLine();
			for(String name : channels.keySet()){
				writer.write(channels.get(name) + "");
				writer.write(":");
				writer.write(name);
				writer.newLine();
			}
			writer.flush();
			writer.close();
		}catch(IOException e){
            e.printStackTrace();
            System.err.println("[CHANNEL] >> Cancelling save.");
            return false;
		}
		return true;
	}
	
	private static void createChannelFile(File file){
        try {
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    System.out.println("[CHANNEL] >> Channel Dir generated");
                }
            }
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("[CHANNEL] >> Channel File generated");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[CHANNEL] >> Using default Channels");
            setDefaultChannel();
        }
	}

    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        if (e.getAuthor().isBot()) { return; }
        if (msg.getRawContent().startsWith(".save") && permittedList.isUserPermitted(e.getAuthor().getIdLong())) {
            e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
            boolean success = setChannels(chFile);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setAuthor("Channel Management", null, e.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("Channel save" + (success ? "d successfully" : " failed."))
                    .setDescription("Read the log for further information")
                    .setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), e.getJDA().getSelfUser().getEffectiveAvatarUrl())
                    .build()).queue();
        }
        if (msg.getRawContent().startsWith(".add") && permittedList.isUserPermitted(e.getAuthor().getIdLong())) {
            e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
            String[] split = e.getMessage().getRawContent().split("\\s+", 3);
            if (split.length < 3) { return; }
            String name = split[1];
            long id = Long.parseLong(split[2]);
            channels.put(name, id);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setAuthor("Channel Management", null, e.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("Channel added successfully")
                    .setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), e.getJDA().getSelfUser().getEffectiveAvatarUrl())
                    .build()).queue();
        }
    }
}
