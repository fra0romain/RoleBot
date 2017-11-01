package de.lesh.rolebot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import de.lesh.rolebot.commands.Information;
import de.lesh.rolebot.commands.forceRole;
import de.lesh.rolebot.commands.giveLanguage;
import de.lesh.rolebot.commands.giveRole;
import de.lesh.rolebot.commands.manageRoles;
import de.lesh.rolebot.commands.info.Credits;
import de.lesh.rolebot.util.ChannelRegister;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.Game;

public class Main {
	
	public static JDA jda;
	public static JDABuilder jdaB = new JDABuilder(AccountType.BOT);
	
	public static void main(String[] args) throws Exception {
		System.out.println("[BOOT] >> Launching RoleBot");
		System.out.println("[BOOT] >> Version: " + lib.version);
		System.out.println("[INFO] >> Checking JDA Version: " + JDAInfo.VERSION);
		
		jdaB.setToken(bot_token.BOT_TOKEN).setGame(Game.of("a role game")).setAutoReconnect(true);
		jdaB.addEventListener(new giveRole());
		jdaB.addEventListener(new giveLanguage());
		jdaB.addEventListener(new Information());
		jdaB.addEventListener(new manageRoles());
		jdaB.addEventListener(new forceRole());
		jdaB.addEventListener(new ChannelRegister());
		jdaB.addEventListener(new Credits());
		loadChannels();
		loadRoles();
		lib.setUserPermissions();
		System.out.println("[SUCCESSFUL] >> Added all EventListeners");
		jda = jdaB.buildBlocking();
		System.out.println("[SUCCESSFUL] >> Activating RoleBot");
	}
	public static JDA getSetup(){
		return jda;
	}
	
	public static void loadRoles() {
		manageRoles.languages.clear();
		String line;
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(manageRoles.location));
        	while ((line = reader.readLine()) != null){
        		String[] parts = line.split(":", 2);
        		if(parts.length >= 2){
        			Long id = Long.parseLong(parts[0]);
        			String name = parts[1];
        			manageRoles.languages.put(name, id);
        		} else{ System.out.println("[INFO] >> Line ignored: " + line); }
        	}
        	reader.close();
    	} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void loadChannels() {
		ChannelRegister.channels.clear();
		String line;
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(ChannelRegister.location));
        	while ((line = reader.readLine()) != null){
        		String[] parts = line.split(":", 2);
        		if(parts.length >= 2){
        			Long id = Long.parseLong(parts[0]);
        			String name = parts[1];
        			ChannelRegister.channels.put(name, id);
        		} else{ System.out.println("[INFO] >> Line ignored: " + line); }
        	}
        	reader.close();
    	} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}