package de.lesh.rolebot;

<<<<<<< HEAD
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.dv8tion.jda.core.Permission;
=======
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class lib {
>>>>>>> 83e7b0060b43cb520e634a858980298a40a85b4a

public class lib {
	public static final String bot_name = "RoleBot";
	public static final String version = "1.7.2";
	public static final String OS = "os.name";
	
	public static final String bot_image = "http://leshdev.tk/bot/img/kuhlProgramming.png";
	
	public static final String server_reg = "Frankfurt";
	
	public static final String prefix = ".";
	public static int sentMSG = 0;
<<<<<<< HEAD
	
	public static Random rand = new Random();
	public static Color randomColor() {
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return  new Color(r, g, b);
	}
	
	public static final void defaultPermissions(Collection<Permission> perm){
		perm.add(Permission.VOICE_CONNECT);
		perm.add(Permission.VOICE_SPEAK);
		perm.add(Permission.VOICE_USE_VAD);
		perm.add(Permission.MESSAGE_ADD_REACTION);
		perm.add(Permission.MESSAGE_ATTACH_FILES);
		perm.add(Permission.MESSAGE_HISTORY);
		perm.add(Permission.MESSAGE_EXT_EMOJI);
		perm.add(Permission.MESSAGE_EMBED_LINKS);
		perm.add(Permission.MESSAGE_WRITE);
		perm.add(Permission.MESSAGE_READ);
		perm.add(Permission.CREATE_INSTANT_INVITE);
	}
	
	public static List<Long> uPerm = new ArrayList<>();
	public static List<Long> uBan = new ArrayList<>();
	
	public static final void setUserPermissions(){
		uPerm.add(155704314638106624L);
	}
=======

	public static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
>>>>>>> 83e7b0060b43cb520e634a858980298a40a85b4a
}

