package com.chumcraft.newsannouncer;

import com.chumcraft.newsannouncer.configuration.Configuration;
import com.chumcraft.newsannouncer.configuration.newsConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class News {
    public ArrayList<NewsItem> newsItems;
    public Configuration newsConfig;
    public Configuration mainConfig;
    public int index;

    public News(){
        this.mainConfig = NewsAnnouncerPlugin.getInstance().getMainConfig();
        this.newsConfig = NewsAnnouncerPlugin.getInstance().getNewsConfig();
        loadNewsItems();
    }

    public void loadNewsItems(){
        ArrayList<NewsItem> newsItems = new ArrayList<NewsItem>();
        Set<String> keys = this.newsConfig.getKeys();
        for(String key : keys){
            newsItems.add(new NewsItem(this.newsConfig.getConfigurationSection(key)));
            try{this.index = Integer.parseInt(this.newsConfig.getConfigurationSection(key).getName())+1;}
            catch(Exception e){};
        }
        this.newsItems = newsItems;
    }

    public void newNewsItem(String news, boolean enabled, int count){
        this.newsConfig.addSection(String.valueOf(index));
        this.newsConfig.setSetting(String.valueOf(index),"news", news);
        this.newsConfig.setSetting(String.valueOf(index),"count", count);
        this.newsConfig.setSetting(String.valueOf(index),"enabled", enabled);
        loadNewsItems();
    }

    public void editNewsItem(String id, String news, int count){
        this.newsConfig.setSetting(id,"news", news);
        this.newsConfig.setSetting(id,"count", count);
        loadNewsItems();
    }

    public void disableNewsItem(String id){
        this.newsConfig.setSetting(id, "enabled",false);
        loadNewsItems();
    }

    public void deleteNewsItem(String id){
        this.newsConfig.deleteSection(id);
        loadNewsItems();
    }

    public void enableNewsItem(String id){
        this.newsConfig.setSetting(id, "enabled",true);
        loadNewsItems();
    }

    public void listNews(String pageNumber, CommandSender sender){
        int totalPages = (int)Math.ceil((newsItems.size()/5.0));
        int pageNum = 1;
        try{
            pageNum = Integer.parseInt(pageNumber);
        }catch (Exception e){
            pageNum = 1;
        }
        if (pageNum > totalPages)
            pageNum = totalPages;
        int index = (pageNum-1)*5;
        int endindex = index+5;
        if (endindex > newsItems.size()-1)
            endindex = newsItems.size()-1;
        List<NewsItem> subset = newsItems.subList(index, endindex+1);
        String header = String.format("News Items: Page %s of %s",pageNum,totalPages);
        sender.sendMessage(ChatColor.GREEN + header);
        for (NewsItem item : subset){
            sender.sendMessage(ChatColor.BLUE + String.format("   ID: %s ENABLED: %s COUNT: %s NEWS: %s",
                    item.id,item.enabled,item.count,item.news));
        }
    }
}
