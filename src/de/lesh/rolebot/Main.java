package de.lesh.rolebot;

import de.lesh.rolebot.commands.Information;
import de.lesh.rolebot.commands.giveLanguage;
import de.lesh.rolebot.commands.giveRole;
import de.lesh.rolebot.commands.manageRoles;
import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;

public class Main {
	
	public static JDA jda;
	public static JDABuilder jdaB = new JDABuilder(AccountType.BOT);
	
	public static void main(String[] args) throws Exception {
		System.out.println("[BOOT] >> Launching RoleBot");
		System.out.println("[BOOT] >> Version: " + lib.version);
		System.out.println("[INFO] >> Checking JDA Version: " + JDAInfo.VERSION);
		
		jdaB.setToken(bot_token.BOT_TOKEN).setGame(Game.of(".r (role) // .l (add/remove) (language)")).setAutoReconnect(true);
		jdaB.addEventListener(new giveRole());
		jdaB.addEventListener(new giveLanguage());
		jdaB.addEventListener(new Information());
		jdaB.addEventListener(new manageRoles());
		System.out.println("[SUCCESSFUL] >> Added all EventListeners");
		jda = jdaB.buildBlocking();
		System.out.println("[SUCCESSFUL] >> Activating RoleBot");
	}
	public static JDA getSetup(){
		return jda;
	}
	
	public static void onReady(ReadyEvent e){
		permittedList.perm.add(155704314638106624L);
	}
}