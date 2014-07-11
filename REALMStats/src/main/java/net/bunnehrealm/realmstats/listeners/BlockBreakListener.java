package net.bunnehrealm.realmstats.listeners;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
	REALMStats plugin = REALMStats.plugin;

	public BlockBreakListener(REALMStats instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlace(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!(e.isCancelled())) {
			plugin.players
					.set(p.getUniqueId() + ".blocksbroken",
							plugin.players.getInt(p.getUniqueId()
									+ ".blocksbroken") + 1);
			plugin.savePlayers();
		}
	}

}
