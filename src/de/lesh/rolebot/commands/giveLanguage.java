package de.lesh.rolebot.commands;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class giveLanguage extends ListenerAdapter {
    private void showError(MessageReceivedEvent e)
    {
        EmbedBuilder eB = new EmbedBuilder();
        eB.setAuthor("ERROR", null, e.getAuthor().getEffectiveAvatarUrl());
        eB.addField("You used the command wrongly", "To display help, type\".l help\"", false);
        eB.setColor(Color.RED);
		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
        e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
    }

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
        if (!(msg.getRawContent().startsWith(".l ") || msg.getRawContent().startsWith(".lang ") || msg.getRawContent().startsWith(".L ")) || e.getAuthor().isBot()) {
            return;
        }

        e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
        EmbedBuilder eB = new EmbedBuilder();
        String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        if (split.length < 2) {
            eB.setAuthor("ERROR >> Missing argument", null, user.getEffectiveAvatarUrl());
            eB.addField("", "The command is missing an argument", false);
            eB.addField("**Solution**", "Infos >> .l help", false);
    		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
            System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
            return;
        }

        String arg = split[1];

        if (arg.equals("help")) {
            eB.setAuthor("HELP", null, user.getEffectiveAvatarUrl());
            eB.addField("Usage", ".l help\n.l (add|remove) <language>\n.lang (add|remove <language>\n.L (add|remove) <language>", false);
    		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
    		eB.setColor(Color.YELLOW);
            e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
            System.out.println("[INFO] >> Used command: .l help - Command performed by " + user);
            return;
        }
        if (arg.equals("list")) {
            eB.setAuthor("All avaibale languages", null, user.getEffectiveAvatarUrl());
            eB.addField("Available Languages:", String.join(", ", manageRoles.languages.keySet()), false);
    		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
    		eB.setColor(Color.YELLOW);
            e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(1, TimeUnit.MINUTES));
            System.out.println("[INFO] >> Used command: .l help - Command performed by " + user);
            return;
        }

        split = arg.split("\\s+", 2);
        if (split.length < 2)
        { showError(e); return; }

        BiConsumer<Member, Role> modify;
        switch (split[0].toLowerCase()) {
	        case "add":
	        	modify = (m, r) -> {
	        		e.getGuild().getController().addRolesToMember(m, r).queue();
	        		eB.addField("New Language >> " + r.getName(), user.getName() + " added " + r.getName() + " to their languages", true);
	        		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
	        		eB.setColor(Color.GREEN);
	        		e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(30, TimeUnit.SECONDS));
	        		System.out.println("[SUCCESFUL] >> Added language " + r.getName() + " to " + user);
	        	};
	            break;
	        case "remove":
	        	modify = (m, r) -> { 
	        		e.getGuild().getController().removeRolesFromMember(m, r).queue();
	        		eB.addField("Remove Language >> " + r.getName(), user.getName() + " removed " + r.getName() + " from their languages", true);
	        		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
	        		eB.setColor(Color.RED);
	        		e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(30, TimeUnit.SECONDS));
	        		System.out.println("[SUCCESFUL] >> Removed language " + r.getName() + " from " + user);
	        	};
	        	break;
	        default:
	            showError(e);
	            return;
        }
        Long roleId = manageRoles.languages.get(split[1].toLowerCase());
        if (roleId == null) {
            eB.addField("ERROR >> Unknown language", user.getName() + " > I dont know this language", true);
            eB.addField("Tipp", "Use: .l help for all languages", false);
    		eB.setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + System.getProperty(lib.OS), null);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(7, TimeUnit.SECONDS));
            System.out.println("[ERROR] >> Unknown language - Command performed by " + user);
            return;
        }
        modify.accept(member, e.getGuild().getRoleById(roleId));
    }
}
