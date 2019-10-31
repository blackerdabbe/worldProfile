package com.serverworld.worldProfile.commands;

import com.serverworld.worldProfile.data.playerdata;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class worldProfileCommands implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==0){
            return true;
        }else {
            //hello world!
            if (strings[0].equals("hello")){
                commandSender.sendMessage(commandSender.getName()+", Hello world!");
                return true;
            }
            //setlang by uuid or id.
            else if(strings[0].equals("setlang")) {
                if(strings.length==3){
                    if(strings[1].length()>20){
                        if(playerdata.changeLangbyuuid(strings[1],strings[2]))
                        {commandSender.sendMessage(ChatColor.GREEN + "Set " + strings[1] + " lang to " + strings[2]); }
                        else{ commandSender.sendMessage("Error while execute command"); }
                    } else {
                        if(playerdata.changeLangbyname(strings[1],strings[2]))
                        {commandSender.sendMessage(ChatColor.GREEN + "Set " + strings[1] + " lang to " + strings[2]); }
                        else{ commandSender.sendMessage("Error while execute command"); }
                    }
                }else { commandSender.sendMessage(ChatColor.RED+"Usage: /worldProfile setlang playerid/uuid language"); }
            }
            //sqlcmd, only for test.
            else if(strings[0].equals("cmd")){
                playerdata.sqlcmd(strings[1]);
            }



            else {commandSender.sendMessage("Unknow command."); }
            return true;
        }
    }
}
