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
