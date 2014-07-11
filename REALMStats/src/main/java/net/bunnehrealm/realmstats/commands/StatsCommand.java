package net.bunnehrealm.realmstats.commands;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import net.bunnehrealm.realmstats.REALMStats;
import net.bunnehrealm.realmstats.utils.UUIDFetcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
	REALMStats plugin = REALMStats.plugin;

	public StatsCommand(REALMStats instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command command, String label,
			String[] args) {

		if (label.equalsIgnoreCase("stats")) {
			if ((args.length != 0) && (args.length != 1)) {
				cs.sendMessage(ChatColor.RED
						+ "Incorrect usage, use /stats [playername]");
			} else {
				if (args.length == 0
						&& (cs.hasPermission("realmstats.command.stats") || cs
								.isOp())) {

					if (!(cs instanceof Player)) {
						cs.sendMessage("Correct usage is /stats [player]");
					} else {
						Player p = (Player) cs;
						DecimalFormat df = null;
						double timenumb = (plugin.players.getDouble(p
								.getUniqueId() + ".time") / 3600);
						if (timenumb < 9.9) {
							df = new DecimalFormat("0.00");
						}
						if (timenumb > 9.9) {
							df = new DecimalFormat("00.00");
						}
						if (timenumb > 99.9) {
							df = new DecimalFormat("000.00");
						}
						if (timenumb > 999.9) {
							df = new DecimalFormat("0000.00");
						}

						plugin.loadPlayers();
						cs.sendMessage(ChatColor.GOLD + "-------------"
								+ ChatColor.GREEN + ChatColor.BOLD
								+ p.getName() + ChatColor.RESET
								+ ChatColor.GOLD + "-------------");
						String time = (df.format(plugin.players.getDouble(p
								.getUniqueId() + ".time") / 3600));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bHours Played: ")
								+ ChatColor.GREEN
								+ time);
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bBlocks Placed: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(p.getUniqueId()
										+ ".blocksplaced"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bBlocks Broken: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(p.getUniqueId()
										+ ".blocksbroken"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bMobs Slain: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(p.getUniqueId()
										+ ".killedmobs"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bAnimals Slain: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(p.getUniqueId()
										+ ".killedanimals"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bPlayers Killed: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(p.getUniqueId()
										+ ".killedplayers"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&',
								"&bVotes: "
										+ ChatColor.GREEN
										+ plugin.players.getInt(p.getUniqueId()
												.toString() + ".votes")));
						cs.sendMessage(ChatColor.GOLD
								+ "-------------------------------------------");
					}
				} else if (args.length == 1
						&& (cs.hasPermission("realmstats.command.stats.others") || cs
								.isOp())) {
					OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
					OfflinePlayer p = t;
					UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(p
							.getName()));
					Map<String, UUID> response = null;
					try {
						response = fetcher.call();
					} catch (Exception e) {
						Bukkit.getLogger().warning(
								"Exception while running UUIDFetcher");
						e.printStackTrace();
					}
					UUID uuid = response.get(p.getName());
					if (uuid == null) {
						cs.sendMessage(ChatColor.RED
								+ "We do not have the logs for that player.");
						return false;
					}
					if (!(plugin.players.contains(uuid.toString()))) {
						cs.sendMessage(ChatColor.RED
								+ "We do not have statistics on that player!");
					} else {

						plugin.loadPlayers();
						cs.sendMessage(ChatColor.GOLD + "-------------"
								+ ChatColor.GREEN + ChatColor.BOLD
								+ p.getName() + ChatColor.RESET
								+ ChatColor.GOLD + "-------------");
						DecimalFormat df = null;
						double timenumb = (plugin.players.getDouble(uuid
								+ ".time") / 3600);
						if (timenumb < 9.9) {
							df = new DecimalFormat("0.00");
						}
						if (timenumb > 9.9) {
							df = new DecimalFormat("00.00");
						}
						if (timenumb > 99.9) {
							df = new DecimalFormat("000.00");
						}
						if (timenumb > 999.9) {
							df = new DecimalFormat("0000.00");
						}
						String time = (df.format(plugin.players.getDouble(uuid
								.toString() + ".time") / 3600));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bHours Played: ")
								+ ChatColor.GREEN
								+ time);
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bBlocks Placed: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(uuid.toString()
										+ ".blocksplaced"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bBlocks Broken: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(uuid.toString()
										+ ".blocksbroken"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bMobs Slain: ")
								+ ChatColor.GREEN
								+ plugin.players.getInt(uuid + ".killedmobs"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bAnimals Slain: ")
								+ ChatColor.GREEN
								+ plugin.players
										.getInt(uuid + ".killedanimals"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&bPlayers Killed: ")
								+ ChatColor.GREEN
								+ plugin.players
										.getInt(uuid + ".killedplayers"));
						cs.sendMessage(ChatColor.translateAlternateColorCodes(
								'&',
								"&bVotes: "
										+ ChatColor.GREEN
										+ plugin.players
												.getInt(uuid + ".votes")));
						cs.sendMessage(ChatColor.GOLD
								+ "-------------------------------------------");
					}
				}
			}
		}

		return false;
	}

}
