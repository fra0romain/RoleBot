package de.lesh.rolebot.commands;

import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Arrays;

import static de.lesh.rolebot.lib.botRole;
import static de.lesh.rolebot.lib.defaultEmbed;

public class Information extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member m = e.getMember();
        EmbedBuilder eB = defaultEmbed();
        if (e.getMember().getUser().isBot()) {
            eB.setAuthor(" >> Bot hat den Server betreten", null, lib.getBotImage());
            eB.addField("Bot Name", m.getAsMention(), true);
            eB.addField("Information", "Der Bot hat automatisch die Bot Rolle bekommen. Dadurch kann er nur in #bot_rush 1 und 2 schreiben", true);
            eB.addField("Problem?", "Melde dich bei @Lesh wenn es probleme mit dem Bot gibt.", true);
            eB.setColor(Color.YELLOW);
            e.getGuild().getController().addRolesToMember(m, botRole);
            e.getGuild().getController().modifyMemberRoles(m, Arrays.asList(botRole), Arrays.asList()).queue();
            System.out.println("Bot joined: " + m);
        } else {
            eB.setAuthor(" >> User hat den Server betreten", null, lib.getBotImage());
            eB.addField("User", "Hallo " + m.getAsMention() + " // " + m.getEffectiveName(), true);
            eB.addField("Information", "Lies am besten den #willkommen Channel durch.", true);
            eB.addField("Get role", "Du kannst dir selbst einen Programmierer Rang geben. Verwende .r (role) f\ufffdr eine Rolle und .l (add/remove) (sprache) f\ufffdr unterschiedliche Programmiersprachen", true);
            eB.setColor(Color.YELLOW);
            System.out.println("User joined: " + m);
        }
        e.getGuild().getTextChannelById(316325706923507721L).sendMessage(eB.build()).queue();
    }
}
