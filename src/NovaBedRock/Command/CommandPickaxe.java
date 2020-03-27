package NovaBedRock.Command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import NovaBedRock.Main;
import NovaBedRock.Utilites.Bar;
import NovaBedRock.Utilites.Colorizer;
import NovaBedRock.Utilites.ItemHandler;

public class CommandPickaxe implements CommandExecutor {

	Main plugin;

	public CommandPickaxe(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("pickaxe")) {
				if (args.length == 0) {
					
					sender.sendMessage(plugin.getConfig().getString("Message.Usage"));
					return true;
				}

				if (args.length == 1) {
					
					if (getPlayer(args[0]) != null) {
						
						Player target = getPlayer(args[0]);

						int durability = plugin.getConfig().getInt("Pickaxe.Durability");

						ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
						ItemMeta meta = item.getItemMeta();

						ItemHandler.setDisplay(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")), meta);
						ItemHandler.setEnchant(Arrays.asList(new String[] { "SILK_TOUCH:2" }), meta);
						ItemHandler.setLore(Arrays.asList(new String[] { "", Colorizer.parseColors("&c" + plugin.getConfig().getString("Pickaxe.Durability_Translation") + ": &7" + durability), "", Bar.Create(100) }), meta);

						meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						
						item.setItemMeta(meta);
												
						sender.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Give.Self")).replace("%player%", target.getName()));
						target.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Give.Target")).replace("%player%", sender.getName()));
						
						target.getInventory().addItem(item);
						return true;
					}
					sender.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.NoFound")));
					return true;
				}
			}
		}

		Player player = (Player) sender;
		if (player.hasPermission("NovaBedRock.User")) {
			if (cmd.getName().equalsIgnoreCase("pickaxe")) {
				if (args.length == 0) {

					int durability = plugin.getConfig().getInt("Pickaxe.Durability");

					ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
					ItemMeta meta = item.getItemMeta();

					ItemHandler.setDisplay(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")), meta);
					ItemHandler.setEnchant(Arrays.asList(new String[] { "SILK_TOUCH:2" }), meta);
					ItemHandler.setLore(Arrays.asList(new String[] { "", Colorizer.parseColors("&c" + plugin.getConfig().getString("Pickaxe.Durability_Translation") + ": &7" + durability), "", Bar.Create(100) }), meta);

					item.setItemMeta(meta);

					player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Give.Target")).replace("%player%", player.getName()));
					player.getInventory().addItem(item);
					return true;
				}

				if (args.length == 1) {
					
					if (getPlayer(args[0]) != null) {
						
						Player target = getPlayer(args[0]);

						int durability = plugin.getConfig().getInt("Pickaxe.Durability");

						ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
						ItemMeta meta = item.getItemMeta();

						ItemHandler.setDisplay(plugin.getConfig().getString("Pickaxe.Name"), meta);
						ItemHandler.setEnchant(Arrays.asList(new String[] { "SILK_TOUCH:2" }), meta);
						ItemHandler.setLore(Arrays.asList(new String[] { "", Colorizer.parseColors("&c" + plugin.getConfig().getString("Pickaxe.Durability_Translation") + ": &7" + durability), "", Bar.Create(100) }), meta);

						item.setItemMeta(meta);
												
						player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Give.Self")).replace("%player%", target.getName()));
						target.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Give.Target")).replace("%player%", player.getName()));
						
						target.getInventory().addItem(item);
						return true;
					}
					player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.NoFound")));
					return true;
				}
			}
		}
		player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Player.Permission")));
		return true;
	}

	public static Player getPlayer(String arg0) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getName().equalsIgnoreCase(arg0)) {
				return player;
			}
		}
		return null;
	}
}