package de.lesh.rolebot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.lesh.rolebot.user.permittedList;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class manageRoles extends ListenerAdapter{

	String location = "roles.txt";
	
	final static Map<String, Long> languages = new HashMap<>();
    static {
        languages.put("java", 316323991646109698L);
        languages.put("c++", 316324124832301076L);
        languages.put("c#", 316324077533003786L);
        languages.put("python", 316324218482589696L);
        languages.put("php", 316324736806420480L);
        languages.put("javascript", 321762279244955658L);
        languages.put("lua", 323140804941971456L);
        languages.put("html5", 323145144620548096L);
        //languages.put("vb.net", 0L);
        languages.put("css", 323145260488327170L);
        languages.put("assembler", 323145448993062925L);
        languages.put("go", 330432150207725578L);
    }
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		
        if (msg.getRawContent().equals(".save")|| e.getAuthor().isBot() || !permittedList.perm.contains(e.getAuthor().getIdLong())) {
            try {
				FileOutputStream saveFile = new FileOutputStream(location);
				ObjectOutputStream oos;
				oos = new ObjectOutputStream(saveFile);
				oos.writeObject(languages);
				oos.close();
				saveFile.close();
				System.out.println("[SUCCESFUL] >> Saved languages in " + location);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
	}
}
