package de.lesh.rolebot.commands;

import java.awt.Color;
import java.util.Arrays;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class giveRole extends ListenerAdapter{

	Role beginnerID = null;
	Role mediumID = null;
	Role profiID = null;
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		EmbedBuilder eB = new EmbedBuilder();
		
		if(!msg.getRawContent().startsWith(".r") || e.getAuthor().isBot()) {
			return;
		}
		
		beginnerID = e.getJDA().getRoleById(316125948544155649L);
		mediumID = e.getJDA().getRoleById(316125835323113472L);
		profiID = e.getJDA().getRoleById(316125663226626048L);
		
		// role = e.getMessage().getRawContent().split("\\s+", 2)[1];
		String[] split = e.getMessage().getRawContent().split("\\s+", 2);
		if (split.length < 2) {
			eB.setAuthor("ERROR >> Missing variable", null, user.getEffectiveAvatarUrl());
			eB.addField("", "Der Command braucht eine weitere Variable", false);
			eB.addField("**Solution**", "Nutzen sie >> .r (beginner|medium|profi)", false);
			eB.setColor(Color.RED);
			e.getChannel().sendMessage(eB.build()).queue();
			System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
		    return;
		}
		String role = split[1];
		
		switch(role.toLowerCase()){
			case "beginner" :  {
				e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(beginnerID), Arrays.asList(mediumID, profiID)).queue();
				eB.setAuthor("ROLE UPDATE >> " + user.getName(), null, user.getEffectiveAvatarUrl());
				eB.addField("New Role","" + beginnerID.getName(), false);
				eB.addField("Old Role", this.getOldRole(member), false);
				eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
				eB.setColor(Color.GREEN);
				e.getChannel().sendMessage(eB.build()).queue();
				System.out.println("[UPGRADE] >> Added <Beginner> to " + member);
				break;
			}
			case "medium" : {
				e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(mediumID), Arrays.asList(beginnerID, profiID)).queue();
				eB.setAuthor("ROLE UPDATE >> " + user.getName(), null, user.getEffectiveAvatarUrl());
				eB.addField("New Role","" + mediumID.getName(), true);
				eB.addField("Old Role", this.getOldRole(member), false);
				eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
				eB.setColor(Color.GREEN);
				e.getChannel().sendMessage(eB.build()).queue();
				System.out.println("[UPGRADE] >> Added <Medium> to " + member);
				break;
			}	
			case "profi" : {
				e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(profiID), Arrays.asList(beginnerID, mediumID)).queue();
				eB.setAuthor("ROLE UPDATE >> " + user.getName(), null, user.getEffectiveAvatarUrl());
				eB.addField("New Role","" + profiID.getName(), true);
				eB.addField("Old Role", this.getOldRole(member), false);
				eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
				eB.setColor(Color.GREEN);
				e.getChannel().sendMessage(eB.build()).queue();
				System.out.println("[UPGRADE] >> Added <Profi> to " + member);
				break;
			}	
			default : {
				eB.setAuthor("ERROR >> Wrong role name", null, user.getEffectiveAvatarUrl());
				eB.addField("", "Die angegebene Rolle steht nicht zur auswahl", false);
				eB.addField("**Solution**", "Nutzen sie >> .r (beginner|medium|profi)", false);
				eB.setColor(Color.RED);
				e.getChannel().sendMessage(eB.build()).queue();
				System.out.println("[ERROR] >> Wrong role name - Command performed by " + user);
			}		
		}
	}	
	private String getOldRole(Member member) {		
		for(Role role : member.getRoles()) {
	        if (role.getName().toLowerCase().contains("beginner")) { return beginnerID.getName(); } 
	        else if (role.getName().toLowerCase().contains("medium")) { return mediumID.getName(); }
	        else if (role.getName().toLowerCase().contains("profi")) { return profiID.getName(); };
		}return "Keine vorherige Rolle vorhanden";
	}
}

