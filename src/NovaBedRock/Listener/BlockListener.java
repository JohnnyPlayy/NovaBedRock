package NovaBedRock.Listener;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import NovaBedRock.Main;
import NovaBedRock.Utilites.Bar;
import NovaBedRock.Utilites.Colorizer;
import NovaBedRock.Utilites.ItemHandler;

public class BlockListener implements Listener {

	Main plugin;

	public BlockListener(Main instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		try {

			ItemStack hand = player.getItemInHand();
			ItemMeta handMeta = hand.getItemMeta();

			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (hand.getType() == Material.DIAMOND_PICKAXE) {

					if (event.getClickedBlock().getType() == Material.BEDROCK) {

						if (handMeta.getDisplayName().equals(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")))) {
							
							if (!(player.getGameMode() == GameMode.CREATIVE)) {
								
								Block block = event.getClickedBlock();

								block.setType(Material.AIR);

								if (plugin.getConfig().getBoolean("Bedrock.Drop")) {
									block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.BEDROCK));
								}

								ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
								ItemMeta meta = item.getItemMeta();

								String name = String.valueOf(handMeta.getLore().get(1));
								String[] args = ChatColor.stripColor(name).split(" ");

								int durability = Integer.parseInt(args[1]);
								int maxDurability = plugin.getConfig().getInt("Pickaxe.Durability");

								String value = String.valueOf(durability - 1);

								int Percentage = durability * 100 / maxDurability;

								ItemHandler.setDisplay(plugin.getConfig().getString("Pickaxe.Name"), meta);
								ItemHandler.setEnchant(Arrays.asList(new String[] { "SILK_TOUCH:2" }), meta);
								ItemHandler.setLore(Arrays.asList(new String[] { "", "&c" + plugin.getConfig().getString("Pickaxe.Durability_Translation") + ": &7" + value, "", Bar.Create(Percentage) }), meta);
								
								handMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								
								if(durability > maxDurability) {
									
									ItemHandler.setDisplay(plugin.getConfig().getString("Pickaxe.Name"), meta);
									ItemHandler.setEnchant(Arrays.asList(new String[] { "SILK_TOUCH:2" }), meta);
									ItemHandler.setLore(Arrays.asList(new String[] { "", "&c" + plugin.getConfig().getString("Pickaxe.Durability_Translation") + ": &7" + maxDurability, "", Bar.Create(100) }), meta);
									
									handMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									
								}
								
								player.getInventory().getItemInHand().setDurability((short) 1562);
								item.setItemMeta(meta);

								player.getInventory().setItemInHand(item);

								if (Integer.parseInt(args[1]) < 2) {
									player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString("Message.Break")));
									player.getInventory().remove(item);
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();

		try {

			ItemStack hand = player.getItemInHand();
			ItemMeta handMeta = hand.getItemMeta();

			if (handMeta.getDisplayName().equals(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")))) {

				if (!(player.getGameMode() == GameMode.CREATIVE)) {

					if (!(player.getLocation().getBlock().getType() == Material.BEDROCK)) {
						player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString(Colorizer.parseColors("Message.NoBreak"))));
						event.setCancelled(true);
						return;

					}
				}
			}	
			
			if (!(player.getGameMode() == GameMode.CREATIVE)) {
				if (hand.getType() == Material.DIAMOND_PICKAXE) {
					if (handMeta.getDisplayName().equals(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")))) {
						if (event.getBlock().getType() == Material.BEDROCK) {
							if ((event.getBlock().getY() <= 0) || (event.getBlock().getY() <= 1) || (event.getBlock().getY() <= 2) || (event.getBlock().getY() <= 3) || (event.getBlock().getY() <= 4)) {
					
								player.sendMessage(Colorizer.parseColors(plugin.getConfig().getString(Colorizer.parseColors("Message.NoBreak.Limit"))));
								event.setCancelled(true);
							}
						}
					}
				}
			}

		} catch (Exception e) {

		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (event.getInventory() instanceof AnvilInventory) {

			try {

				ItemStack current = event.getCurrentItem();
				ItemMeta meta = current.getItemMeta();

				if (meta.getDisplayName().equals(Colorizer.parseColors(plugin.getConfig().getString("Pickaxe.Name")))) {
					event.setCancelled(true);
				}
				
			} catch (Exception e) {

			}
		}
	}
}
