package de.lesh.rolebot.commands;

import java.awt.Color;
import java.util.Arrays;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Information extends ListenerAdapter{

	String OS = "os.name";
	Role botID = null;
	Role profiID = null;
	
	
	public void onGuildMemberJoin(GuildMemberJoinEvent e){
		Member m = e.getMember();
		botID = e.getJDA().getRoleById(316126438069633024L);
		profiID = e.getJDA().getRoleById(316125663226626048L);
		EmbedBuilder eB = new EmbedBuilder();
		
		if(e.getMember().getUser().isBot()){
			eB.setAuthor(" >> Bot hat den Server betreten", null, lib.bot_image);
			eB.addField("Bot Name", m.getAsMention(), true);
			eB.addField("Information", "Der Bot hat automatisch die Bot Rolle bekommen. Dadurch kann er nur in #bot_rush 1 und 2 schreiben", true);
			eB.addField("Problem?", "Melde dich bei @Lesh wenn es probleme mit dem Bot gibt.", true);
			eB.setColor(Color.YELLOW);
			eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
			e.getGuild().getController().modifyMemberRoles(m, Arrays.asList(botID),Arrays.asList(profiID)).queue();
			e.getGuild().getTextChannelById(316325706923507721L).sendMessage(eB.build()).queue();
			System.out.println("User joined: " + m);
		}else{
			eB.setAuthor(" >> User hat den Server betreten", null, lib.bot_image);
			eB.addField("User", "Hallo " + m.getAsMention(), true);
			eB.addField("Information", "Lies am besten den #willkommen Channel durch.", true);
			eB.addField("Get role", "Du kannst dir selbst einen Programmierer Rang geben. Verwende .r (role) für eine Rolle und .l (add/remove) (sprache) für unterschiedliche Programmiersprachen", true);
			eB.setColor(Color.YELLOW);
			eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
			e.getGuild().getTextChannelById(316325706923507721L).sendMessage(eB.build()).queue();
			System.out.println("User joined: " + m);
		}
	}
}
