package de.lesh.rolebot.commands;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import de.lesh.rolebot.user.bannedList;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.handle.GuildMemberAddHandler;
import net.dv8tion.jda.core.handle.GuildMemberRemoveHandler;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class giveRole extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e, GuildMemberAddHandler add, GuildMemberRemoveHandler rem){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
		if(!msg.getRawContent().startsWith(".r") || bannedList.black.contains(e.getAuthor().getIdLong()) || e.getAuthor().isBot()) {
			return;
		}
		
		Role beginnerID = e.getJDA().getRoleById(316125948544155649L);
		Role mediumID = e.getJDA().getRoleById(316125835323113472L);
		Role profiID = e.getJDA().getRoleById(316125663226626048L);
		
		String role = e.getMessage().getRawContent().split("\\s+", 2)[1];
		EmbedBuilder eB = new EmbedBuilder();
		
		switch(role){
			case "beginner" :  {
				e.getGuild().getController().modifyMemberRoles(member, beginnerID, profiID).queue();
				System.out.println("[UPGRADE] >> Added <Beginner> to " + member);
				break;
			}
			case "medium" : {
				e.getGuild().getController().modifyMemberRoles(member, mediumID, profiID, beginnerID).queue();
				System.out.println("[UPGRADE] >> Added <Medium> to " + member);
				break;
			}	
			case "profi" : {
				e.getGuild().getController().modifyMemberRoles(member, profiID, beginnerID, mediumID).queue();
				System.out.println("[UPGRADE] >> Added <Profi> to " + member);
				break;
			}	
			default : {
				eB.setAuthor("ERROR > Cant find role", null, user.getEffectiveAvatarUrl());
				eB.addField("", "Die angegebene Rolle steht nicht zur auswahl", false);
				eB.addField("**Solution**", "Nutzen sie >> .r (beginner|medium|profi)", false);
				eB.setColor(Color.RED);
				e.getChannel().sendMessage(eB.build()).queue();
			}		
		}
	}
}

