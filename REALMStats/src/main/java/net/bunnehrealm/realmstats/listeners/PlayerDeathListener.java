package net.bunnehrealm.realmstats.listeners;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener{
	REALMStats plugin = REALMStats.plugin;
	
	public PlayerDeathListener(REALMStats instance){
		this.plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent e){
		if(e.getEntity().getKiller() instanceof Player){
			Player p = e.getEntity().getKiller();
			plugin.players.set(p.getUniqueId() + ".killedplayers",
					plugin.players.getInt(p.getUniqueId() + ".killedplayers") + 1);
			plugin.savePlayers();
		}
	}
	
}
