package com.serverworld.worldProfile;

import com.serverworld.worldProfile.Listeners.Playerjoin;
import com.serverworld.worldProfile.commands.worldProfileCommands;
import net.md_5.bungee.api.ChatColor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class worldProfile extends JavaPlugin{
    public static Connection connection;
    private worldProfileconfig config;
    private String host, database, username, password;
    private int port;

    @Override
    public void onLoad() {
        config = new worldProfileconfig(this);
    }

    @Override
    public void onEnable() {
        config.loadDefConfig();
        host = config.host();
        port = config.port();
        database = config.database();
        username = config.username();
        password = config.password();
        try {
            openConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worldprofile_userlang` (`PlayerUUID` varchar(36),`PlayerID` varchar(16), `Lang` varchar(8), PRIMARY KEY(PlayerUUID),INDEX (PlayerID))");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            getLogger().warning("Error while connection to database");
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().warning("Error while connection to database");
        }
        try {
            openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Events
        new Playerjoin(this);
        this.getCommand("worldProfile").setExecutor(new worldProfileCommands());
        getLogger().info(ChatColor.GREEN+getDescription().getName()+" Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.YELLOW+"Disable "+this.getDescription().getName()+" "+this.getDescription().getVersion());
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            getLogger().info("Connected to database!");
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }
}
