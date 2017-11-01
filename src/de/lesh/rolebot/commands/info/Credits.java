package de.lesh.rolebot.commands.info;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Credits extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User u = e.getAuthor();
		EmbedBuilder eB = new EmbedBuilder();
		if(!msg.getRawContent().startsWith(".credits") || e.getAuthor().isBot() || !lib.uPerm.contains(e.getAuthor().getIdLong())) {
			return;
		}
		
		eB.setTitle("Rolebot Credits", u.getAvatarUrl());
		eB.addField("Creator", u.getAsMention() + " - Lesh", true);
		eB.addField("Thanks to", "- Sanduhr\n- romangraef\n", false);
		e.getChannel().sendMessage(eB.build()).queue();
	}
	
}
