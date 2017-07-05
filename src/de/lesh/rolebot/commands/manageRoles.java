package de.lesh.rolebot.commands;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class manageRoles extends ListenerAdapter{

	public static String location = "roles.txt";
	String OS = "os.name";
	
	//
	
	public final static Map<String, Long> languages = new HashMap<>();
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(e.getAuthor().isBot()){
			return;
		}
        if (msg.getRawContent().startsWith(".save") && !permittedList.perm.contains(e.getAuthor().getIdLong())) {
    		String line;
        	try {
    			BufferedReader reader = new BufferedReader(new FileReader(location));
            	while ((line = reader.readLine()) != null){
            		String[] parts = line.split(":", 2);
            		if(parts.length >= 2){
            			Long id = Long.parseLong(parts[0]);
            			String name = parts[1];
            			languages.put(name, id);
            		} else{
            			System.out.println("[INFO] >> Line ignored: " + line);
            		}
            	}
            	reader.close();
        	} catch (FileNotFoundException e1) {
    			e1.printStackTrace();
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	eB.addField("Loading ...", "Succesful loades all roles", false);
    		eB.setColor(Color.GREEN);
    		e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
        	e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
        	
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
			eB.addField("Saving ...", "Succesful saved all roles", false);
			eB.setColor(Color.GREEN);
			e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
			e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
			return;
        }
        if(msg.getRawContent().startsWith(".add") && !permittedList.perm.contains(e.getAuthor().getIdLong())) {
        	String[] split = e.getMessage().getRawContent().split("\\s+", 3);
        	String name = split[1];
        	long id = Long.parseLong(split[2]);
        	languages.put(name, id);
        	eB.addField("New Role", name, true);
        	eB.addField("ID", "" + id, true);
        	eB.setColor(Color.GREEN);
        	e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
			e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
        	return;
        }
	}
}
