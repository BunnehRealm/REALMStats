/*REALM Stats for logging and maintaining statistics in bukkit servers.
 Copyright (C) 2013  Rory Finnegan
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
