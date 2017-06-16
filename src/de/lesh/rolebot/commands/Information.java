package de.lesh.rolebot.commands;

import java.awt.Color;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Information extends ListenerAdapter{

	String OS = "os.name";
	
	public void onGuildMemberJoin(GuildMemberJoinEvent e){
		Member m = e.getMember();
		EmbedBuilder eB = new EmbedBuilder();
		eB.setAuthor(" >> Willkommen & Informatin", null, lib.bot_image);
		eB.addField("Willkommen", "Hallo Neuer " + m.getAsMention() + " Wir wünschen dir viel Spaß auf diesem Server. Lies am besten den #willkommen Channel durch", true);
		eB.addField("Information", "Du kannst dir selbst einen Programmierer Rang geben. Verwende .r (role) für eine Rolle und .l (sprache) für unterschiedliche Programmiersprachen", true);
		eB.setColor(Color.YELLOW);
		eB.setFooter("Rolebot - Made by @Lesh - " + System.getProperty(OS), null);
		e.getGuild().getTextChannelById(316325706923507721L).sendMessage(eB.build()).queue();
		System.out.println("User joined: " + m);
	}
}
