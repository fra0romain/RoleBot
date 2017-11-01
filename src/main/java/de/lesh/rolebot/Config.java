package de.lesh.rolebot;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static String TOKEN = null;
    public static File configFile = new File("config.json");
    public static List<Long> admins = new ArrayList<>();
    public static List<Long> bans = new ArrayList<>();

    static {
        load();
    }

    public static void load() {
        JSONObject c = new JSONObject(configFile);
        c.getJSONArray("admins")
                .forEach(id -> admins.add(Long.valueOf(id + "")));
        c.getJSONArray("band")
                .forEach(id -> bans.add(Long.valueOf(id + "")));
        TOKEN = c.getString("token");
    }

    public static void save() {
        JSONObject c = new JSONObject();
        admins.forEach(aLong -> c.accumulate("admins", aLong + ""));
        bans.forEach(aLong -> c.accumulate("band", aLong + ""));
        c.put("token", TOKEN);


        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(configFile);
            fos.write(c.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
