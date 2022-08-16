package com.chumcraft.newsannouncer;

import com.chumcraft.newsannouncer.configuration.playerConfiguration;
import org.bukkit.configuration.ConfigurationSection;


public class Player {
    playerConfiguration playerconfig;

    public Player(String UUID){
        this.playerconfig = new playerConfiguration(UUID);
    }

    public boolean show(String id, int count){
        ConfigurationSection section = playerconfig.getConfigurationSection(id);
        if(section == null){
            playerconfig.addSection(id);
            playerconfig.setSetting(id,"count",0);
            section = playerconfig.getConfigurationSection(id);
        }
        return section.getInt("count")<=count;
    }

    public void increment(String id){
        ConfigurationSection section = playerconfig.getConfigurationSection(id);
        if(section == null){
            playerconfig.addSection(id);
            playerconfig.setSetting(id,"count",1);
        }else{
            int incrementCount = section.getInt("count")+1;
            playerconfig.setSetting(id,"count",incrementCount);
        }
        return ;
    }


}
