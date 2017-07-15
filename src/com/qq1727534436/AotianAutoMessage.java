package com.qq1727534436;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AotianAutoMessage extends JavaPlugin{
	private ArrayList<String> messages=new ArrayList<String>();
	private int time=60;
	private int index=0;
	@Override
	public void onEnable() {
		//Read
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		if(new File(getDataFolder(),"config.yml").exists()) {
			messages=(ArrayList<String>) getConfig().getStringList("Message");
			new BukkitRunnable() {
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					if(messages.size()==0) {
						getLogger().log(Level.WARNING,"Please set the message!");
						cancel();
						getServer().getPluginManager().disablePlugin(getServer().getPluginManager().getPlugin("AotianAutoMessage"));
					}
					for(int i=0;i<getServer().getOnlinePlayers().length;i++) {
						getServer().getOnlinePlayers()[i].sendMessage(messages.get(index));
					}
					index++;
					if(index==messages.size()) {
						index=0;
					}
				}
			}.runTaskTimer(this,0,time*20);
		}else {
			try {
				new File(getDataFolder(),"config.yml").createNewFile();
				messages.add("Test");
				getConfig().set("Message",messages);
				getConfig().set("Time", 60);
				saveConfig();
				getServer().getPluginManager().disablePlugin(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info("Welcome to use AotianAutoMessage!Autuor:youkucola(QQ:1727534436)!");
	}
	@Override
	public void onDisable() {
		//Save
		getConfig().set("Message",messages);
		getLogger().info("AotianAutoMessage disable!Autuor:youkucola(QQ:1727534436)!");
	}
}
