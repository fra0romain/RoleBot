package de.lesh.rolebot.commands;

import java.awt.Color;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Information extends ListenerAdapter{

	
	
	public void onGuildMemberJoin(GuildMemberJoinEvent e){
		Member m = e.getMember();
		e.getGuild().getTextChannelById(316325706923507721L).sendMessage();
		EmbedBuilder eB = new EmbedBuilder();
		eB.setAuthor(" >> Willkommen & Informatin", null, lib.bot_image);
		eB.addField("Willkommen", "`Hallo neuer unerfahrener Programmierer. Wir wünschen dir viel Spaß auf diesem Server. Ließ am besten den #willkommen Channel durch`", true);
		eB.addField("Information", "`Du kannst dir selbst einen Programmierer Rang geben. Nutz .r (role) für eine Rolle und .l (sprache) für unterschiedliche Programmiersprachen`", true);
		eB.setColor(Color.YELLOW);
		eB.setFooter("Rolebot - Made by @lesh", null);
		String welcome = eB.build();

		System.out.println("User joined: " + m);
	}
	
	public void sendMessage() {
		

		
	}
}
