package fr.rremis.image.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

	public static void registerCommand(JavaPlugin plugin) {

		plugin.getCommand("map").setExecutor(new MapCommand());
		
	}

}
