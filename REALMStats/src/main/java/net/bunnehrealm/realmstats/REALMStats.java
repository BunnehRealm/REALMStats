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

package net.bunnehrealm.realmstats;

import java.io.File;

import net.bunnehrealm.realmstats.commands.StatsCommand;
import net.bunnehrealm.realmstats.listeners.AnimalKillListener;
import net.bunnehrealm.realmstats.listeners.BlockBreakListener;
import net.bunnehrealm.realmstats.listeners.BlockPlaceListener;
import net.bunnehrealm.realmstats.listeners.KillMobListener;
import net.bunnehrealm.realmstats.listeners.PlayerDeathListener;
import net.bunnehrealm.realmstats.managers.TimeManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class REALMStats extends JavaPlugin {
	public static REALMStats plugin;

	public File playersFile;
	public FileConfiguration players;

	public TimeManager tm = new TimeManager(this);

	public BlockPlaceListener bpl = new BlockPlaceListener(this);
	public BlockBreakListener bbl = new BlockBreakListener(this);
	public KillMobListener kml = new KillMobListener(this);
	public PlayerDeathListener pdl = new PlayerDeathListener(this);
	public AnimalKillListener akl = new AnimalKillListener(this);

	public StatsCommand sc = new StatsCommand(this);

	@Override
	public void onDisable() {
		plugin = null;

	}

	public void onEnable() {
		plugin = this;
		playersFile = new File(getDataFolder(), "Players.yml");
		players = new YamlConfiguration();

		try {
			firstPlayerRun();
		} catch (Exception e) {
			e.printStackTrace();
		}

		loadPlayers();
		savePlayers();

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(bpl, this);
		pm.registerEvents(bbl, this);
		pm.registerEvents(kml, this);
		pm.registerEvents(pdl, this);
		pm.registerEvents(akl, this);

		getCommand("stats").setExecutor(sc);

		tm.timeRun();

	}

	public void firstPlayerRun() throws Exception {
		if (!playersFile.exists()) {
			getLogger().info("Creating a Players.yml file");
			playersFile.getParentFile().mkdirs();
			playersFile.createNewFile();

		}
	}

	public void loadPlayers() {
		try {
			players.load(playersFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void savePlayers() {
		try {
			players.save(playersFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
