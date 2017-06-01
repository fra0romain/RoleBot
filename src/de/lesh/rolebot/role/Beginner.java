package de.lesh.rolebot.role;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Beginner extends ListenerAdapter{

	public static JDA jda;
	
	//static User user = jda.getPresence().getJDA();
	static String role = ":floppy_disk: Beginner - Programmer";
			
	public static void updateRole(){
	//	jda.getGuilds().add(user, role);
	}
	
}
