package com.chumcraft.newsannouncer;

import org.bukkit.configuration.ConfigurationSection;

public class NewsItem {
    public String news;
    public String id;
    public int count;
    public boolean enabled;

    public NewsItem(String id, boolean enabled, int count, String news){
        this.id = id;
        this.enabled = enabled;
        this.count = count;
        this.news = news;
    }

    public NewsItem(ConfigurationSection section){
        this(section.getName(),section.getBoolean("enabled"),
                section.getInt("count"),section.getString("news"));
    }
}
