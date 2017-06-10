package de.lesh.rolebot.commands;

import java.awt.Color;

import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class giveLanguage extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
		if(!msg.getRawContent().startsWith(".l") || e.getAuthor().isBot()) {
			return;
		}
		
		EmbedBuilder eB = new EmbedBuilder();
		
		String[] split = e.getMessage().getRawContent().split("\\s+", 3);
		if (split.length < 2) {
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
}
