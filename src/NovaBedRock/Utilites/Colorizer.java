package NovaBedRock.Utilites;

import org.bukkit.ChatColor;

public class Colorizer {
	
	public static String parseColors(String parsed) {
		String color = ChatColor.translateAlternateColorCodes('&', parsed);
		return color;
	}
}
