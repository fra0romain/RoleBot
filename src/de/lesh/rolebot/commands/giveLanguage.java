package de.lesh.rolebot.commands;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class giveLanguage extends ListenerAdapter {
	
	String OS = "os.name";
	
	private final static Map<String, Long> languages = new HashMap<>();
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
    }
	
    private void showError(MessageReceivedEvent e)
    {
        EmbedBuilder eB = new EmbedBuilder();
        eB.setAuthor("ERROR", null, e.getAuthor().getEffectiveAvatarUrl());
        eB.addField("You used the command wrongly", "To display help, type\".l help\"", false);
		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
        e.getChannel().sendMessage(eB.build()).queue();
    }

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
        if (!msg.getRawContent().startsWith(".l") || e.getAuthor().isBot()) {
            return;
        }

        e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
        EmbedBuilder eB = new EmbedBuilder();

        String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        if (split.length < 2) {
            eB.setAuthor("ERROR >> Missing argument", null, user.getEffectiveAvatarUrl());
            eB.addField("", "The command is missing an argument", false);
            eB.addField("**Solution**", "Infos >> .l help", false);
    		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
            return;
        }

        String arg = split[1];

        if (arg.equals("help")) {
            eB.setAuthor("HELP", null, user.getEffectiveAvatarUrl());
            eB.addField("Usage", ".l help\n.l (add|remove) <language>", false);
            eB.addField("Available Languages:", String.join(", ", languages.keySet()), false);
    		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("[INFO] >> Used command: .l help - Command performed by " + user);
            return;
        }

        split = arg.split("\\s+", 2);
        if (split.length < 2)
        {
            showError(e);
            return;
        }

        BiConsumer<Member, Role> modify;
        switch (split[0].toLowerCase()) {
	        case "add":
	        	modify = (m, r) -> {
	        		e.getGuild().getController().addRolesToMember(m, r).queue();
	        		eB.addField("New Language >> " + r.getName(), user.getName() + " added " + r.getName() + " to their languages", true);
	        		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
	        		eB.setColor(Color.GREEN);
	        		e.getChannel().sendMessage(eB.build()).queue();
	        		System.out.println("[SUCCESFUL] >> Added language " + r.getName() + " to " + user);
	        	};
	            break;
	        case "remove":
	        	modify = (m, r) -> { 
	        		e.getGuild().getController().removeRolesFromMember(m, r).queue();
	        		eB.addField("Remove Language >> " + r.getName(), user.getName() + " removed " + r.getName() + " from their languages", true);
	        		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
	        		eB.setColor(Color.RED);
	        		e.getChannel().sendMessage(eB.build()).queue();
	        		System.out.println("[SUCCESFUL] >> Removed language " + r.getName() + " from " + user);
	        	};
	        	break;
	        default:
	            showError(e);
	            return;
        }
        
        Long roleId = languages.get(split[1].toLowerCase());
        if (roleId == null) {
            eB.addField("ERROR >> Unknown language", user.getName() + " > I dont know this language", true);
    		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("[ERROR] >> Unknown language - Command performed by " + user);
            return;
        }
        modify.accept(member, e.getGuild().getRoleById(roleId));
    }
}
