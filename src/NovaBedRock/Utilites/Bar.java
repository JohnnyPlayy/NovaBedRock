package NovaBedRock.Utilites;

import org.bukkit.ChatColor;

public class Bar {

	public static String Create(int percetage) {
		
		ChatColor wrapColour = ChatColor.WHITE;
		
		String bar = "";
		ChatColor colour;

		if (percetage >= 20) {
			colour = ChatColor.GREEN;
		} else {

			if (percetage >= 18) {
				colour = ChatColor.GREEN;
			} else {

				if (percetage >= 15) {
					colour = ChatColor.YELLOW;
				} else {

					colour = ChatColor.RED;
				}
			}
		}

		int looped = 0;
		while (looped++ < percetage / 5) {
			bar = bar + '❙';
		}

		bar = bar + ChatColor.WHITE;
		while (looped++ <= 20) {
			bar = bar + ChatColor.GRAY + "❙";
		}

		return wrapColour + "【" + colour + bar + wrapColour + "】";
	}

	public static String Create(int percetage, boolean inverse) {
		
		ChatColor wrapColour = ChatColor.WHITE;
		String bar = "";
		ChatColor colour;

		if (!inverse) {
			if (percetage >= 75) {
				colour = ChatColor.GREEN;
			} else {

				if (percetage >= 40) {
					colour = ChatColor.GREEN;
				} else {

					if (percetage >= 25) {
						colour = ChatColor.YELLOW;
					} else {

						colour = ChatColor.RED;
					}
				}
			}
		} else {

			if (percetage >= 75) {
				colour = ChatColor.RED;
			} else {

				if (percetage >= 40) {
					colour = ChatColor.YELLOW;
				} else {

					if (percetage >= 25) {
						colour = ChatColor.GREEN;
					} else {

						colour = ChatColor.GREEN;
					}
				}
			}

			int looped = 0;
			while (looped++ < percetage / 5) {
				bar = bar + '❙';
			}

			bar = bar + ChatColor.WHITE;
			while (looped++ <= 20) {
				bar = bar + ChatColor.GRAY + "❙";
			}
		}
		return wrapColour + "【" + colour + bar + wrapColour + "】";
	}

	public static String Create(int percetage, String characters, boolean inverse) {
		ChatColor wrapColour = ChatColor.WHITE;
		String bar = "";
		ChatColor colour;

		if (!inverse) {
			if (percetage >= 75) {
				colour = ChatColor.GREEN;
			} else {

				if (percetage >= 40) {
					colour = ChatColor.GREEN;
				} else {

					if (percetage >= 25) {
						colour = ChatColor.YELLOW;
					} else {

						colour = ChatColor.RED;
					}
				}
			}
		} else {

			if (percetage >= 75) {
				colour = ChatColor.RED;
			} else {

				if (percetage >= 40) {
					colour = ChatColor.YELLOW;
				} else {

					if (percetage >= 25) {
						colour = ChatColor.GREEN;
					} else {

						colour = ChatColor.GREEN;
					}
				}
			}

			int looped = 0;
			while (looped++ < percetage / 5) {
				bar = bar + characters;
			}

			bar = bar + ChatColor.WHITE;
			while (looped++ <= 20) {
				bar = bar + ChatColor.GRAY + "_";
			}
		}
		return wrapColour + "【" + colour + bar + wrapColour + "】";
	}
}
