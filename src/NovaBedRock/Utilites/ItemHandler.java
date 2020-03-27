package NovaBedRock.Utilites;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemHandler {

	public static ItemStack getItem(String material, int amount, int data) {
		ItemStack meta = new ItemStack(getMaterial(material), amount, (short) data);
		return meta;
	}

	public static Material getMaterial(String name) {
		Material mat = Material.getMaterial(name.toUpperCase());
		return mat;
	}

	public static void setDisplay(String name, ItemMeta meta) {
		meta.setDisplayName(Colorizer.parseColors(name));
	}

	public static void setLore(List<String> name, ItemMeta meta) {

		List<String> lore = new ArrayList<String>();
		for (String listLore : name) {
			lore.add(Colorizer.parseColors(listLore));
			meta.setLore(lore);
		}
	}

	public static ItemMeta setEnchant(List<String> name, ItemMeta meta) {
		List<String> enchantName = name;
		for (String level : enchantName) {
			if (level.contains(":")) {
				String[] enchant = level.split(":");
				meta.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
		}
		return meta;
	}

	public static boolean setSkull(String name, ItemMeta meta) {
		boolean skullItem = ((SkullMeta) meta).setOwner(name);
		return skullItem;
	}
	
	public static ItemMeta setSkull(String texture, String uuid, ItemMeta meta) {

		GameProfile profile = new GameProfile(UUID.fromString(uuid), null);
		profile.getProperties().put("textures", new Property("textures", texture));
		Field profileField = null;
		
		try {
			
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
			
		} catch (Exception localException1) {
			
		}
		return meta;
	}
}
