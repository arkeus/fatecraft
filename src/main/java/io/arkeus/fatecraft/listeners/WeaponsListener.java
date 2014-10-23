package io.arkeus.fatecraft.listeners;

import io.arkeus.fate.listener.FateListener;
import io.arkeus.fatecraft.Fatecraft;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WeaponsListener extends FateListener<Fatecraft> {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMove(final EntityDamageByEntityEvent event) {
		if (event.getDamager().getType() != EntityType.PLAYER) {
			return;
		}
		final Player source = (Player) event.getDamager();
		//final User user = getPlugin().getUsers().get(source);
		getPlugin().getChat().send(source.getUniqueId(), "You damaged " + event.getEntity());
	}
}
