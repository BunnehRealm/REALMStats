package net.bunnehrealm.realmstats.listeners;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.entity.Bat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class AnimalKillListener implements Listener {
	REALMStats plugin = REALMStats.plugin;
	
	public AnimalKillListener(REALMStats instance){
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onKill(EntityDeathEvent e) {
		if ((e.getEntity() instanceof Cow || e.getEntity() instanceof Chicken
				|| e.getEntity() instanceof Horse
				|| e.getEntity() instanceof Ocelot
				|| e.getEntity() instanceof Pig
				|| e.getEntity() instanceof Sheep
				|| e.getEntity() instanceof Bat
				|| e.getEntity() instanceof Wolf
				|| e.getEntity() instanceof Snowman
				|| e.getEntity() instanceof IronGolem
				|| e.getEntity() instanceof Squid || e.getEntity() instanceof Villager)&& (e.getEntity().getKiller() instanceof Player)) {
					Player p = e.getEntity().getKiller();
					plugin.players.set(p.getUniqueId() + ".killedanimals",
							plugin.players.getInt(p.getUniqueId() + ".killedanimals") + 1);
			
		}
	}
}
