package de.lesh.rolebot;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Random;

import static de.lesh.rolebot.Main.getSetup;

public class lib {
    public static final String version = "1.7.3";
    public static final String server_reg = "Frankfurt";
    public static final String prefix = ".";
    public static Random rand = new Random();
    public static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static Role beginnerRole;
    public static Role mediumRole;
    public static Role profiRole;
    public static Role botRole;

    static {
        botRole = getSetup().getRoleById(316126438069633024L);
        beginnerRole = getSetup().getRoleById(316125948544155649L);
        mediumRole = getSetup().getRoleById(316125835323113472L);
        profiRole = getSetup().getRoleById(316125663226626048L);
    }

    public static EmbedBuilder defaultEmbed() {
        return new EmbedBuilder().setFooter("Rolebot - Made by @Lesh - Version:" + lib.version + " - " + getOsName(), getBotImage());
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getBotName() {
        return getSetup().getSelfUser().getName();
    }

    public static String getBotImage() {
        return getSetup().getSelfUser().getAvatarUrl();
    }

    public static Color randomColor() {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    public static void defaultPermissions(Collection<Permission> perm) {
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

    public static String getOldRole(Member member) {
        for (Role role : member.getRoles()) {
            if (role.getName().toLowerCase().contains("beginner")) {
                return beginnerRole.getName();
            } else if (role.getName().toLowerCase().contains("medium")) {
                return mediumRole.getName();
            } else if (role.getName().toLowerCase().contains("profi")) {
                return profiRole.getName();
            }
        }
        return "Keine vorherige Rolle vorhanden";
    }
}

