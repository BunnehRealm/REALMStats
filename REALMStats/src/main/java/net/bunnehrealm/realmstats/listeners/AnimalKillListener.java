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
