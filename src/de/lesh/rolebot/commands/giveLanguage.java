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
    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        User user = e.getAuthor();
        Member member = e.getMember();

<<<<<<< HEAD
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
		if(!msg.getRawContent().startsWith(".l") || e.getAuthor().isBot()) {
			return;
		}
		
		EmbedBuilder eB = new EmbedBuilder();
		
		String[] split = e.getMessage().getRawContent().split("\\s+", 3);
		if (split.length < 3) {
			eB.setAuthor("ERROR >> Missing variable", null, user.getEffectiveAvatarUrl());
			eB.addField("", "Der Command braucht eine weitere Variable", false);
			eB.addField("**Solution**", "Infos unter >> .l all", false);
			eB.setColor(Color.RED);
			e.getChannel().sendMessage(eB.build()).queue();
			System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
		    return;
		}
		String give = split[1];
		String lang = split[2];
		
		switch(give){
			case "all" : {
					eB.setAuthor("ALL LANGUAGES", null, user.getEffectiveAvatarUrl());
					eB.addField("Java", "ADD: .l add java | REMOVE: .l remove java", false);
					eB.addField("C++", "ADD: .l add c++ | REMOVE: .l remove c++", false);
					eB.addField("C#", "ADD: .l add c# | REMOVE: .l remove c#", false);
					eB.addField("Python", "ADD: .l add python | REMOVE: .l remove python", false);
					eB.addField("PHP", "ADD: .l add php | REMOVE: .l remove php", false);
					eB.addField("JavaScript", "ADD: .l add javascript | REMOVE: .l remove javascript", false);
					e.getChannel().sendMessage(eB.build()).queue();
					System.out.println("Showing all commands");
				break;
			}
			case "add" : {
				switch(lang){
					case "java" : {
						System.out.println("HEY");
						break;
					}
					case "c++" : {
						break;
					}
					case "c#" : {
						break;
					}
					case "python" : {
						break;
					}
					case "php" : {
						break;
					}
					case "javascript" : {
						break;
					}
				}
			}
			case "remove" : {
				switch(lang){
					case "java" : {
						break;
					}
					case "c++" : {
						break;
					}
					case "c#" : {
						break;
					}
					case "python" : {
						break;
					}
					case "php" : {
						break;
					}
					case "javascript" : {
						break;
					}
				}
			}
		}
	}
=======
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
            eB.addField("Usage", ".l help\n.l (add|remove) <lamguage>", false);
            eB.addField("Available Languages:", "Java, C++, C#, Python, PHP, JavaScript", false);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("Showing help");
            return;
        }

        split = arg.split("\\s+", 2);
        if (split.length < 2)
        {
            showError(e);
            return;
        }

        BiConsumer<Member, Role> modify;
        switch (arg) {
        case "add":
            modify = e.getGuild().getController()::addRolesToMember;
            break;
        case "remove":
            modify = e.getGuild().getController()::removeRolesFromMember;
            break;
        default:
            showError(e);
            return;
        }

        switch (split[1].toLowerCase()) {
        case "java": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here java role id */));
            break;
        }
        case "c++": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here c++ role id */));
            break;
        }
        case "c#": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here c# role id */));
            break;
        }
        case "python": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here python role id */));
            break;
        }
        case "php": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here php role id */));
            break;
        }
        case "javascript": {
            modify.accept(member, e.getGuild().getRoleById(0L /* Here javascript role id */));
            break;
        }
        default: {
            // here build message for invalid language
        }
        }
    }
>>>>>>> origin/master
}
