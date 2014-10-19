package io.arkeus.fatecraft.user;

import io.arkeus.fatecraft.Fatecraft;
import io.arkeus.fatecraft.events.SkillUpEvent;
import io.arkeus.fatecraft.user.level.Experience;
import io.arkeus.fatecraft.user.level.Skill;
import io.arkeus.fatecraft.user.level.SkillHandler;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User {
	private final Fatecraft fc;
	private final UUID id;
	private final SkillHandler skills;

	private Location home;

	public User(final Fatecraft fc, final UUID id) {
		this.fc = fc;
		this.id = id;
		this.home = null;
		this.skills = new SkillHandler();
	}

	public int getLevel(final Skill skill) {
		return skills.getSkill(skill).getLevel();
	}

	public void gainExperience(final Skill skill, final double xp) {
		final Experience experience = skills.getSkill(skill);
		if (experience.gainExperience(xp)) {
			fc.sendEvent(new SkillUpEvent(id, skill, experience.getLevel()));
		}
	}

	public void setHome(final Location home) {
		this.home = home;
	}

	public Location getHome() {
		return home;
	}

	public boolean hearth() {
		final Player player =  fc.getServer().getPlayer(id);
		if (player.getBedSpawnLocation() != null) {
			player.teleport(player.getBedSpawnLocation());
			return true;
		} else {
			return false;
		}
	}
}
