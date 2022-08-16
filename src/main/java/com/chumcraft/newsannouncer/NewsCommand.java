package com.chumcraft.newsannouncer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class NewsCommand implements CommandExecutor {

    private NewsAnnouncerPlugin plugin;

    public NewsCommand(NewsAnnouncerPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("news")) {
                commandProcessor(args,sender);
            }else{
                sender.sendMessage("Permission denied.");
            }
        }else if(sender instanceof ConsoleCommandSender){
            for (String arg : args) {
                Bukkit.broadcastMessage(ChatColor.RED + arg);
            }
            Bukkit.broadcastMessage(ChatColor.RED + "====================");
        }

        return true;
    }

    private void commandProcessor(String[] args, CommandSender sender) {
        if (args.length == 0) {
            invalidCommand(sender);
        } else {
            switch (args[0]) {
                case "help":
                    help(sender);
                    break;
                case "create":
                    createCommand(args, sender);
                    break;
                case "disable":
                    disableCommand(args, sender);
                    break;
                case "enable":
                    enableCommand(args, sender);
                    break;
                case "edit":
                    editCommand(args, sender);
                    break;
                case "list":
                    listCommand(args, sender);
                    break;
                case "delete":
                    deleteCommand(args, sender);
                    break;
                default:
                    break;
            }
        }
    }

    private void createCommand(String[] args, CommandSender sender){
        if(args.length > 3){
            String news = String.join(" ",Arrays.copyOfRange(args,2,args.length));
            try{
                int count = Integer.parseInt(args[1]);
                this.plugin.getNews().newNewsItem(news,true, count);
            }catch(Exception e){
                invalidCommand(sender);
                return;
            }

        }else
            invalidCommand(sender);
    }

    private void disableCommand(String[] args, CommandSender sender){
        if(args.length == 2){
            ConfigurationSection section = this.plugin.getNewsConfig().getConfigurationSection(args[1]);
            if(section != null)
                this.plugin.getNews().disableNewsItem(args[1]);
            else
                sender.sendMessage(ChatColor.RED + "News ID not found");
        }else
            invalidCommand(sender);
    }

    private void deleteCommand(String[] args, CommandSender sender){
        if(args.length == 2){
            ConfigurationSection section = this.plugin.getNewsConfig().getConfigurationSection(args[1]);
            if(section != null)
                this.plugin.getNews().deleteNewsItem(args[1]);
            else
                sender.sendMessage(ChatColor.RED + "News ID not found");
        }else
            invalidCommand(sender);
    }

    private void enableCommand(String[] args, CommandSender sender){
        if(args.length == 2){
            ConfigurationSection section = this.plugin.getNewsConfig().getConfigurationSection(args[1]);
            if(section != null)
                this.plugin.getNews().enableNewsItem(args[1]);
            else
                sender.sendMessage(ChatColor.RED + "News ID not found");
        }else
            invalidCommand(sender);
    }

    private void editCommand(String[] args, CommandSender sender){
        if(args.length > 4){
            ConfigurationSection section = this.plugin.getNewsConfig().getConfigurationSection(args[1]);
            if(section != null) {
                String news = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                try {
                    int count = Integer.parseInt(args[2]);
                    this.plugin.getNews().editNewsItem(args[1], news, count);
                } catch (Exception e) {
                    invalidCommand(sender);
                    return;
                }
            }
        }else
            invalidCommand(sender);
    }

    private void listCommand(String[] args, CommandSender sender){
        if(args.length == 1){
            plugin.getNews().listNews("1",sender);
        }else if(args.length == 2){
            plugin.getNews().listNews(args[1],sender);
        }else{
            invalidCommand(sender);
        }
    }

    private void invalidCommand(CommandSender sender){
        sender.sendMessage(ChatColor.RED + "Invalid Command");
    }

    private void help(CommandSender sender){
        sender.sendMessage(ChatColor.GREEN + "News Help:");
        sender.sendMessage(ChatColor.GREEN + "   Allowed sub-commands: create, disable, enable, edit, list");
        sender.sendMessage(ChatColor.GREEN + "   create usage: /news create <# of times show> <news message>");
        sender.sendMessage(ChatColor.GREEN + "   delete usage: /news delete <id#>");
        sender.sendMessage(ChatColor.GREEN + "   enable usage: /news enable <id#>");
        sender.sendMessage(ChatColor.GREEN + "   disable usage: /news disable <id#>");
        sender.sendMessage(ChatColor.GREEN + "   edit usage: /news edit <id-#> <# of times show> <news message");
        sender.sendMessage(ChatColor.GREEN + "   list usage: /news list <page-#>");
    }
}