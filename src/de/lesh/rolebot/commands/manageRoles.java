package de.lesh.rolebot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class manageRoles extends ListenerAdapter{

	String location = "roles.txt";
	String OS = "os.name";
	
	final static Map<String, Long> languages = new HashMap<>();
    static {
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
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		
        if (msg.getRawContent().matches(".save")|| !e.getAuthor().isBot() || !permittedList.perm.contains(e.getAuthor().getIdLong())) {
      
			FileWriter saveFile;
			BufferedWriter out;
			try {
				saveFile = new FileWriter(location);
				out = new BufferedWriter(saveFile);
				for (Entry<String, Long> entry : languages.entrySet()) {
				    out.write(entry.getValue() + ":" + entry.getKey() + "\n");
				}
				System.out.println("[SUCCESFUL] >> Saved current language Map into " + location);
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if(msg.getRawContent().matches(".add")|| !e.getAuthor().isBot() || !permittedList.perm.contains(e.getAuthor().getIdLong())) {
        	String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        	Long[] longSplit = e.getMessage().getRawContent();
        	String name = split[1];
        	String id = split[2];
        	
        	languages.put(name, id);
        }
        else {
        	eB.addField("Fehler", "Du hast nicht die Berechtigung dafür", true);
        	eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
        	e.getChannel().sendMessage(eB.build()).queue();
        }
	}
}
