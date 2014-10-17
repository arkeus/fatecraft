package io.arkeus.fatecraft.listeners;

import io.arkeus.fate.listener.FateListener;
import io.arkeus.fatecraft.Fatecraft;
import io.arkeus.fatecraft.events.SkillUpEvent;
import io.arkeus.fatecraft.user.User;
import io.arkeus.fatecraft.user.level.Skill;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

public class ExperienceListener extends FateListener<Fatecraft> {
	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final User user = getPlugin().getUsers().get(player.getUniqueId());

		if (event.getPlayer().getVelocity().getY() <= 0 && event.getTo().getY() > event.getFrom().getY()) {
			user.gainExperience(Skill.ACROBATICS, 1 * 100);
		}

		user.gainExperience(Skill.ATHLETICS, event.getFrom().distance(event.getTo()) / 2);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSkillUp(final SkillUpEvent event) {
		getPlugin().getChat().send(event.getTarget(), "Your " + event.getSkill() + " level is now " + event.getLevel());
	}
}
