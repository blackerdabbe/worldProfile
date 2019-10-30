package com.serverworld.worldProfile.Listeners;

import com.serverworld.worldProfile.worldProfile;
//APIs
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.Color;
import org.bukkit.entity.Player;
//Events
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;
//SQL
import java.sql.*;


public class Playerjoin implements Listener {

    private final worldProfile plugin;
    public Playerjoin(worldProfile plg) {
        plugin=plg;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        try {
            Statement statement = worldProfile.connection.createStatement();
            String checker = "INSERT INTO worldprofile_userlang (PlayerUUID, PlayerID,Lang) VALUES ("+"'" + player.getUniqueId() + "','" + player.getName() + "','zh_tw')" +" ON DUPLICATE KEY UPDATE"+" PlayerID='"+player.getName()+"';";
            ResultSet rs = statement.executeQuery("SELECT PlayerUUID from worldprofile_userlang WHERE PlayerUUID='"+player.getUniqueId()+"';");
            Boolean playerexists =false;
            playerexists = rs.next();
            Bukkit.getServer().getLogger().info(playerexists.toString());
            if (playerexists){
                Bukkit.getServer().getLogger().info(String.format(plugin.getName()+ ChatColor.GREEN+ " Player "+player.getName()+" Found!"));
                statement.executeUpdate(checker);
            }else {
                Bukkit.getServer().getLogger().info(String.format(plugin.getName()+ ChatColor.YELLOW+  " Player "+player.getName()+" Not Found! Creading new one."));
                statement.executeUpdate(checker);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }
}

