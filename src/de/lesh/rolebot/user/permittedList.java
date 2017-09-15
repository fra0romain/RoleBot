package de.lesh.rolebot.user;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class permittedList extends ListenerAdapter {

    public static List<Long> perm = new ArrayList<>();

    public static boolean isUserPermitted(long id) {
		return perm.contains(id);
    }

    public void onReady(ReadyEvent e) {
        perm.add(155704314638106624L);
    }

}
