package NovaBedRock;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import NovaBedRock.Command.CommandPickaxe;
import NovaBedRock.Listener.BlockListener;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		
		Register();
		Commands();
		Configs();
	}
	
	public void Register() {
		getRegisterEvent(new BlockListener(this), this);
	}
	
	public void getRegisterEvent(Listener arg0, Plugin plugin) {
		getServer().getPluginManager().registerEvents(arg0, plugin);
	}
	
	public void Commands() {
		getCommand("pickaxe", new CommandPickaxe(this));
	}
	
	public void getCommand(String Command, CommandExecutor Executor) {
		Bukkit.getServer().getPluginCommand(Command).setExecutor(Executor);
	}
	
	public void Configs() {
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("Pickaxe.Name", "&cNitride Pickaxe");
		getConfig().addDefault("Pickaxe.Durability", 2500);
		getConfig().addDefault("Pickaxe.Durability_Translation", "Durability");
		getConfig().addDefault("Bedrock.Drop", true);
		getConfig().addDefault("Message.Usage", "Use: pickaxe <player>");
		getConfig().addDefault("Message.Break", "&cYour pick broke");
		getConfig().addDefault("Message.NoBreak", "&cThis pickaxe can only remove bedrock");
		getConfig().addDefault("Message.NoBreak.Limit", "&cYou can't break this block at this depth");
		getConfig().addDefault("Message.Player.NoFound", "&cPlayer can not be found");
		getConfig().addDefault("Message.Player.Give.Target", "&7You received a pickaxe");
		getConfig().addDefault("Message.Player.Give.Self", "&7%player% get a pickaxe");
		getConfig().addDefault("Message.Player.Permission", "&cYou're not allowed");
		saveConfig();
	}

}
