package de.lesh.rolebot.commands;

import de.lesh.rolebot.Config;
import de.lesh.rolebot.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Arrays;

import static de.lesh.rolebot.lib.*;

public class forceRole extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        User user = e.getAuthor();
        EmbedBuilder eB = defaultEmbed();

        if (!msg.getRawContent().startsWith(".force") || e.getAuthor().isBot() || !Config.admins.contains(e.getAuthor().getIdLong())) {
            return;
        }


        String[] split = e.getMessage().getRawContent().split("\\s+", 3);
        Member member = e.getGuild().getMember(e.getMessage().getMentionedUsers().get(0));
        if (split.length < 3) {
            eB.setAuthor("ERROR >> Missing variable", null, null);
            eB.addField("", "Der Command braucht eine weitere Variable", false);
            eB.addField("**Solution**", "Nutzen sie >> .r (beginner|medium|profi)", false);
            eB.setColor(Color.RED);
            e.getChannel().sendMessage(eB.build()).queue();
            System.out.println("[ERROR] >> Missing variable - Command performed by " + user);
            return;
        }

        String role = split[2];
        switch (role.toLowerCase()) {
            case "beginner": {
                e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(beginnerRole), Arrays.asList(mediumRole, profiRole)).queue();
                eB.setAuthor("Force Role update", null, null);
                eB.addField("New Role", "" + beginnerRole.getName(), false);
                eB.addField("Old Role", lib.getOldRole(member), false);
                eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
                eB.setColor(Color.GREEN);
                e.getChannel().sendMessage(eB.build()).queue();
                System.out.println("[UPGRADE] >> Added <Beginner> to " + member);
                break;
            }
            case "medium": {
                e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(mediumRole), Arrays.asList(beginnerRole, profiRole)).queue();
                eB.setAuthor("Force Role update", null, null);
                eB.addField("New Role", "" + mediumRole.getName(), true);
                eB.addField("Old Role", lib.getOldRole(member), false);
                eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
                eB.setColor(Color.GREEN);
                e.getChannel().sendMessage(eB.build()).queue();
                System.out.println("[UPGRADE] >> Added <Medium> to " + member);
                break;
            }
            case "profi": {
                e.getGuild().getController().modifyMemberRoles(member, Arrays.asList(profiRole), Arrays.asList(beginnerRole, profiRole)).queue();
                eB.setAuthor("Force Role update", null, null);
                eB.addField("New Role", "" + profiRole.getName(), true);
                eB.addField("Old Role", lib.getOldRole(member), false);
                eB.setFooter("COMMAND: .r | USE: .r (beginner|medium|profi)", null);
                eB.setColor(Color.GREEN);
                e.getChannel().sendMessage(eB.build()).queue();
                System.out.println("[UPGRADE] >> Added <Profi> to " + member);
                break;
            }
            default: {
                eB.setAuthor("ERROR >> Wrong role name", null, null);
                eB.addField("", "Die angegebene Rolle steht nicht zur auswahl", false);
                eB.addField("**Solution**", "Nutzen sie >> .r (beginner|medium|profi)", false);
                eB.setColor(Color.RED);
                e.getChannel().sendMessage(eB.build()).queue();
                System.out.println("[ERROR] >> Wrong role name - Command performed by " + user);
            }
        }
    }

}
