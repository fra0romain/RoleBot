package de.lesh.rolebot.commands;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class manageRoles extends ListenerAdapter{

	public static String location = "roles.txt";
	public final static Map<String, Long> languages = new HashMap<>();
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(e.getAuthor().isBot()){ return; }
        if (msg.getRawContent().startsWith(".save") && lib.uPerm.contains(e.getAuthor().getIdLong())) {
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
			} catch (IOException e1) { e1.printStackTrace(); }
			eB.addField("Saving ...", "Succesful saved all roles", false);
			eB.setColor(Color.GREEN);
			e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
			e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
			return;
        }
        // --- --- --- If input is .add --- --- ---
        if(msg.getRawContent().startsWith(".add ") && lib.uPerm.contains(e.getAuthor().getIdLong())) {
        	String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        	String name = split[1];	
        	Role role = e.getGuild().getController().createRole().setMentionable(true).setColor(lib.randomColor()).setName(name).complete();
        	Long id = role.getIdLong();
        	languages.put(name.toLowerCase(), id);
        	Collection<Permission> perms = new LinkedList<>();
        	lib.defaultPermissions(perms);
        	role.getManager().setPermissions(perms).queue();
	        eB.addField("New Role", name, true);
	        eB.addField("ID", "" + id, true);
	        eB.setColor(Color.GREEN);
	        e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
	        e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
	        return;
        }
        // --- --- --- If input is .remove --- --- ---
        if(msg.getRawContent().startsWith(".remove ") && lib.uPerm.contains(e.getAuthor().getIdLong())) {
        	String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        	String longID = split[1];	
        	Role role = e.getGuild().getRoleById(longID);
        	String name = role.getName();
        	Long id = role.getIdLong();
        	languages.remove(name, id);
        	role.delete().queue();
	        eB.addField("Deleted Role", name, true);
	        eB.addField("ID", "" + id, true);
	        eB.setColor(Color.RED);
	        e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
	        e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
	        return;
        }
	}
}