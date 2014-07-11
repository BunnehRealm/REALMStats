package net.bunnehrealm.realmstats.managers;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class TimeManager {
	REALMStats plugin = REALMStats.plugin;
	
	public TimeManager(REALMStats instance){
		this.plugin = instance;
	}

	public void timeRun(){
		BukkitScheduler bs = Bukkit.getScheduler();
		bs.scheduleSyncRepeatingTask(plugin, new BukkitRunnable(){

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					REALMStats.plugin.players.set(p.getUniqueId() + ".time", REALMStats.plugin.players.getInt(p.getUniqueId() + ".time") + 1);
					REALMStats.plugin.savePlayers();
				}
				
			}
			
		}, 0, 20);
	}
	
	
}
