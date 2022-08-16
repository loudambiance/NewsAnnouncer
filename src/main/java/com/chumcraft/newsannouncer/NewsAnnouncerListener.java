/**
 * Copyright (C) 2020 Daniel Baucom
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/license
 * 
 */

package com.chumcraft.newsannouncer;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewsAnnouncerListener implements Listener {
     private NewsAnnouncerPlugin plugin;
     private News news;

     public NewsAnnouncerListener() {
          this.plugin = NewsAnnouncerPlugin.getInstance();
          this.news = NewsAnnouncerPlugin.getInstance().getNews();
     }

     @EventHandler
     public void onPlayerJoin(PlayerJoinEvent event) {
          Player player = new Player(event.getPlayer().getUniqueId().toString());

          for(NewsItem newsItem : news.newsItems){
               if(player.show(newsItem.id, newsItem.count)){
                    event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD +"NEWS »" + ChatColor.RESET + newsItem.news);
                    player.increment(newsItem.id);
               }
          }
     }
}