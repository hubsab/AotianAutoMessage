package com.qq1727534436;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AotianAutoMessage extends JavaPlugin{
	private ArrayList<String> message=new ArrayList<String>();
	private int index=0;
	@Override
	public void onEnable() {
		if(getDataFolder().exists()) {
			message=(ArrayList<String>) getConfig().getStringList("Message");
			a();
		}else {
			try {
				getDataFolder().mkdir();
				new File(getDataFolder(),"config.yml").createNewFile();
				message.add("Test");
				getConfig().set("Message",message);
				saveConfig();
				a();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info("Welcome to use AotianAutoMessage!Autuor:youkucola(QQ:1727534436)!");
	}
	@Override
	public void onDisable() {
		getLogger().info("AotianAutuoMessage is disable!Autuor:youkucola(QQ:1727534436)!");
	}
	public void a() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(message.size()!=0) {
					getServer().broadcastMessage(message.get(index));
					if(++index==message.size()) {
						index=0;
					}
				}else {
					message.add("Test");
					throw new NullPointerException("Wring!Message is not set!Use default Message!");
				}
			}
		}.runTaskTimer(this, 0, 20*30);
	}
}
