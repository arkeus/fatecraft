package io.arkeus.fatecraft.listeners;

import io.arkeus.fate.listener.FateListener;
import io.arkeus.fatecraft.Fatecraft;
import io.arkeus.fatecraft.user.User;
import io.arkeus.fatecraft.user.level.Skill;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class AcrobaticsListener extends FateListener<Fatecraft> {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMove(final PlayerMoveEvent event) {
		if (((Entity) event.getPlayer()).isOnGround()) {
			event.getPlayer().setAllowFlight(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onFlightAttempt(final PlayerToggleFlightEvent event) {
		if (!event.isFlying()) {
			return;
		}
		final Player player = event.getPlayer();
		final User user = getPlugin().getUsers().get(player);
		player.setFlying(false);
		final Vector jump = player.getLocation().getDirection().multiply(0.25).setY(0.3 + 1.0 * (user.getLevel(Skill.ACROBATICS) / 1000.0));
		player.setVelocity(player.getVelocity().add(jump));
		player.setAllowFlight(false);
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(final EntityDamageEvent event) {
		if (event.getEntityType() != EntityType.PLAYER || event.getCause() != DamageCause.FALL) {
			return;
		}
		final Player player = (Player) event.getEntity();
		final User user = getPlugin().getUsers().get(player);
		final int acrobatics = user.getLevel(Skill.ACROBATICS);

		double damage = event.getDamage();
		damage -= Math.floor(acrobatics / 100);
		if (!player.getAllowFlight()) {
			damage -= 1;
		}
		damage *= 1 - (acrobatics / 1000) * 0.8;
		event.setDamage(damage);

		if (damage <= 0) {
			event.setCancelled(true);
		}
	}
}
