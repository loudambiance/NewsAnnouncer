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

import org.bukkit.plugin.java.JavaPlugin;
import com.chumcraft.newsannouncer.configuration.*;

public class NewsAnnouncerPlugin extends JavaPlugin {

    private static NewsAnnouncerPlugin plugin;
    private mainConfiguration mainConfig;
    private newsConfiguration newsConfig;
    private News news;

    public static NewsAnnouncerPlugin getInstance(){
        return NewsAnnouncerPlugin.plugin;
    }

    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {
        // Don't log enabling, Spigot does that for you automatically!
        NewsAnnouncerPlugin.plugin = this;
        this.mainConfig = new mainConfiguration();
        this.newsConfig = new newsConfiguration();
        this.news = new News();

        // Commands enabled with following method must have entries in plugin.yml
        this.getCommand("news").setExecutor(new NewsCommand(this));
        getServer().getPluginManager().registerEvents(new NewsAnnouncerListener(), this);
    }

    public Configuration getNewsConfig() {
        return this.newsConfig;
    }
    public Configuration getMainConfig() {
        return this.mainConfig;
    }
    public News getNews() {
        return this.news;
    }
}
