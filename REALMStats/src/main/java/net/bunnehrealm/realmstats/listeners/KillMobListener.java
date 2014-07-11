package net.bunnehrealm.realmstats.listeners;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobListener implements Listener {
	REALMStats plugin = REALMStats.plugin;

	public KillMobListener(REALMStats instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMobKill(EntityDeathEvent e) {
		if ((e.getEntity() instanceof CaveSpider
				|| e.getEntity() instanceof Enderman
				|| e.getEntity() instanceof Zombie
				|| e.getEntity() instanceof PigZombie
				|| e.getEntity() instanceof Blaze
				|| e.getEntity() instanceof Creeper
				|| e.getEntity() instanceof Ghast
				|| e.getEntity() instanceof MagmaCube
				|| e.getEntity() instanceof Silverfish
				|| e.getEntity() instanceof Skeleton
				|| e.getEntity() instanceof Slime
				|| e.getEntity() instanceof EnderDragon
				|| e.getEntity() instanceof Wither
				|| e.getEntity() instanceof Spider || e.getEntity() instanceof Witch)
				&& (e.getEntity().getKiller() instanceof Player)) {
			Player p = e.getEntity().getKiller();
			plugin.players.set(p.getUniqueId() + ".killedmobs",
					plugin.players.getInt(p.getUniqueId() + ".killedmobs") + 1);
		}
	}
}
