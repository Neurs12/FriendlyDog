package dev.neurs.FriendlyDog;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Entity;

public class FriendlyDogListener implements Listener {
	private boolean listenerEnabled = true;

	// Cancel any damages event done by players to tamed wolf and reverse.
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity gotDamaged = event.getEntity();
		Entity damager = event.getDamager();
		 
		if (gotDamaged instanceof Wolf && damager instanceof Player && listenerEnabled) {
			Wolf wolf = (Wolf) gotDamaged;
			if (wolf.isTamed()) {
				event.setCancelled(true);
			}
		}
		
		if (gotDamaged instanceof Player && damager instanceof Wolf && listenerEnabled) {
			Wolf wolf = (Wolf) damager;
			if (wolf.isTamed()) {
				event.setCancelled(true);
			}
		}
	}
	
	// Cancel any damages event to tamed wolf when the owner's offline.
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		Entity gotDamaged = event.getEntity();

		// Check if the entity is wolf.
        if (gotDamaged instanceof Wolf && listenerEnabled) {
            Wolf wolf = (Wolf) gotDamaged;
            
            // Then check if it's tamed.
            if (wolf.isTamed()) {
            	OfflinePlayer owner = (OfflinePlayer) wolf.getOwner();
            	
            	// Finally, check if the owner is online or not.
            	if (owner != null && !owner.isOnline()) {
            		event.setCancelled(true);
            	}
            }
        }
    }
	
	// Cancel any target event of tamed wolf on player.
	@EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
		Entity targeter = event.getEntity();
		Entity targeted = event.getTarget();
		
		// Check if a wolf is targeting a player and if the listener is allowed to perform a cancel event.
        if (targeter instanceof Wolf && targeted instanceof Player && listenerEnabled) {
            Wolf wolf = (Wolf) targeter;
            
            // Then check if it's tamed.
            if (wolf.isTamed()) {
                event.setCancelled(true);
            }
        }
    }

	public void setListenerEnabled(boolean enabled) {
        this.listenerEnabled = enabled;
    }

    public boolean isListenerEnabled() {
        return listenerEnabled;
    }
}
