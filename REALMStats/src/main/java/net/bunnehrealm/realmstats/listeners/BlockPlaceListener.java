package net.bunnehrealm.realmstats.listeners;

import net.bunnehrealm.realmstats.REALMStats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
	REALMStats plugin = REALMStats.plugin;

	public BlockPlaceListener(REALMStats instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!(e.isCancelled())) {
			plugin.players
					.set(p.getUniqueId() + ".blocksplaced",
							plugin.players.getInt(p.getUniqueId()
									+ ".blocksplaced") + 1);
			plugin.savePlayers();
		}
	}

}
