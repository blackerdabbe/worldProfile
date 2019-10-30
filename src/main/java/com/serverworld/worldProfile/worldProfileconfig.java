package com.serverworld.worldProfile;
public class worldProfileconfig {
    private worldProfile plugin;
    public worldProfileconfig(worldProfile i){
        plugin = i;
    }
    public void loadDefConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }
    public String host() {
        return plugin.getConfig().getString("data.mysql.host");
    }
    public int port() {
        return plugin.getConfig().getInt("data.mysql.port");
    }
    public String username() {
        return plugin.getConfig().getString("data.mysql.username");
    }
    public String password() {
        return plugin.getConfig().getString("data.mysql.password");
    }
    public String database() {
        return plugin.getConfig().getString("data.mysql.database");
    }
}
