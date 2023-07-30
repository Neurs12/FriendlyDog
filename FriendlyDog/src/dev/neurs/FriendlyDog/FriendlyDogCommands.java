package dev.neurs.FriendlyDog;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FriendlyDogCommands implements CommandExecutor {

	private final FriendlyDogListener friendlyDogListener;

    public FriendlyDogCommands(FriendlyDogListener trainedDogsListener) {
        this.friendlyDogListener = trainedDogsListener;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command lol, String stuff, String[] args) {
		if (sender.isOp() || sender.hasPermission("friendlydog.toggle")) {
			friendlyDogListener.setListenerEnabled(!friendlyDogListener.isListenerEnabled());
	        if (friendlyDogListener.isListenerEnabled()) {
	            sender.sendMessage("[FriendlyDog] Dogs are now friendly.");
	        } else {
	            sender.sendMessage("[FriendlyDog] Dogs hate you now.");
	        }
	        return true;
		}
		sender.sendMessage("[FriendlyDog] You have no permission to perform this action.");
        return false;
	}

}
