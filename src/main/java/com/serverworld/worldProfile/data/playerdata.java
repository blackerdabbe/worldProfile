package com.serverworld.worldProfile.data;

import com.serverworld.worldProfile.worldProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class playerdata {
    private worldProfile plugon;
    public void setup(worldProfile i){
        plugon=i;
    }
    public static String getLangbyname(String name){
        try {
            Statement statement = worldProfile.connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM worldprofile_userlang WHERE PlayerID = '" + name + "';");
            return res.getString("Lang");
        } catch (SQLException e) {
            e.printStackTrace();
            return "us_en";
        }
    }
    public static String getLangbyuuid(String uuid){
        try {
            Statement statement = worldProfile.connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM worldprofile_userlang WHERE PlayerUUID = '" + uuid + "';");
            return res.getString("Lang");
        } catch (SQLException e) {
            e.printStackTrace();
            return "us_en";
        }
    }
    public static boolean changeLangbyuuid(String uuid ,String lang){
        try {
            Statement statement = worldProfile.connection.createStatement();
            String updateer = "UPDATE worldprofile_userlang SET Lang = '"+lang+"' WHERE PlayerUUID = '"+uuid+"'";
            statement.executeUpdate(updateer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean changeLangbyname(String name ,String lang){
        try {
            Statement statement = worldProfile.connection.createStatement();
            String updateer = "UPDATE worldprofile_userlang SET Lang = '"+lang+"' WHERE PlayerID = '"+name+"'";
            statement.executeUpdate(updateer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String sqlcmd(String com){
        try {
            if(com==""){
                return "Error";
            }
            Statement statement = worldProfile.connection.createStatement();
            statement.executeUpdate(com);
            return com+" executed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
