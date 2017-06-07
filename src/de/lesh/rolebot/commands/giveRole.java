package de.lesh.rolebot.commands;

import de.lesh.rolebot.user.bannedList;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class giveRole extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
		if(!msg.getRawContent().startsWith(".r") || bannedList.black.contains(e.getAuthor().getIdLong()) || e.getAuthor().isBot()) {
			return;
		}
		
		
		Role beginnerID = e.getGuild().getRoleById(316125948544155649L);
		String role = e.getMessage().getRawContent().split("\\s+", 2)[1];
		
		switch(role){
			case "beginner" :  {
				e.getGuild().getController().addRolesToMember(member, beginnerID).queue();
				System.out.println("[UPGRADE] >> Added <Beginner> to " + member);
				break;
			}			//.getRoleById(316125948544155649L).;
			case "medium" : 
				break;
			case "profi" : 
				break;
			default : e.getChannel().sendMessage("Die angegebene Gruppe ist nicht vorhanden. \n Bitte wähle zwischen beginner , medium oder profi").queue();
		}
	}
}

