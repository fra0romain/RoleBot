package de.lesh.rolebot.commands;

import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class reload {

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		User user = e.getAuthor();
		Member member = e.getMember();
		
		if(!msg.getRawContent().startsWith(".r") || !permittedList.perm.contains(e.getAuthor().getIdLong()) || e.getAuthor().isBot()) {
			return;
		}
	}
}
