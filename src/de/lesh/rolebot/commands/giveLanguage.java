package de.lesh.rolebot.commands;

import java.awt.*;
import java.util.function.BiConsumer;

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
        e.getChannel().sendMessage(eB.build()).queue();
    }

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
        if (!msg.getRawContent().startsWith(".l") || e.getAuthor().isBot()) {
            return;
        }

        EmbedBuilder eB = new EmbedBuilder();

        String[] split = e.getMessage().getRawContent().split("\\s+", 2);
        if (split.length < 2) {
            eB.setAuthor("ERROR >> Missing variable", null, user.getEffectiveAvatarUrl());
            eB.addField("", "Der Command braucht eine weitere Variable", false);
            eB.addField("**Solution**", "Infos unter >> .l help", false);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
            return;
        }

        String arg = split[1];

        if (arg.equals("help")) {
            eB.setAuthor("HELP", null, user.getEffectiveAvatarUrl());
            eB.addField("Usage", ".l help\n.l (add|remove) <language>", false);
            eB.addField("Available Languages:", "java, c++, c#, python, php, javascript", false);
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
	        	modify = (m, r) -> e.getGuild().getController().addRolesToMember(m, r).queue();
	            eB.addField("New Language", "You've added a new language", true);
	            e.getChannel().sendMessage(eB.build()).queue();
	            break;
	        case "remove":
	        	modify = (m, r) -> e.getGuild().getController().removeRolesFromMember(m, r).queue();
	            eB.addField("Remove Language", "You've removed an language", true);
	            e.getChannel().sendMessage(eB.build()).queue();
	            break;
	        default:
	            showError(e);
	            return;
        }

        switch (split[1].toLowerCase()) {
	        case "java": {
	            modify.accept(member, e.getGuild().getRoleById(316323991646109698L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <java> to " + user);
	            break;
	        }
	        case "c++": {
	            modify.accept(member, e.getGuild().getRoleById(316324124832301076L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <c++> to " + user);
	            break;
	        }
	        case "c#": {
	            modify.accept(member, e.getGuild().getRoleById(316324077533003786L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <c#> to " + user);
	            break;
	        }
	        case "python": {
	            modify.accept(member, e.getGuild().getRoleById(316324218482589696L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <python> to " + user);
	            break;
	        }
	        case "php": {
	            modify.accept(member, e.getGuild().getRoleById(316324736806420480L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <php> to " + user);
	            break;
	        }
	        case "javascript": {
	            modify.accept(member, e.getGuild().getRoleById(321762279244955658L));
	            System.out.println("[SUCCESSFUL] >> Added/removed language <javascript> to " + user);
	            break;
	        }
	        default: {
	            eB.setAuthor("", null, user.getEffectiveAvatarUrl());
	        }
        }
    }
}
