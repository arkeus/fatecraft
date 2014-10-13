package io.arkeus.fatecraft.listeners;

import io.arkeus.fate.listener.FateListener;
import io.arkeus.fatecraft.Fatecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class AcrobaticsListener extends FateListener<Fatecraft> {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(final PlayerJoinEvent event) {
		event.getPlayer().setAllowFlight(true);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onFlightAttempt(final PlayerToggleFlightEvent event) {
		if (!event.isFlying()) {
			return;
		}
		final Player player = event.getPlayer();
		player.setFlying(false);
		final Vector jump = player.getLocation().getDirection().multiply(0.25).setY(0.8);
		player.setVelocity(player.getVelocity().add(jump));
		event.setCancelled(true);
	}
}
