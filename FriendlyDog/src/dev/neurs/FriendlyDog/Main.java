package dev.neurs.FriendlyDog;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		FriendlyDogListener friendlyDogListener = new FriendlyDogListener();
		getServer().getPluginManager().registerEvents(friendlyDogListener, this);
		this.getCommand("fd").setExecutor(new FriendlyDogCommands(friendlyDogListener));
	}
}
