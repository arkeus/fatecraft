package io.arkeus.fatecraft.events;

import io.arkeus.fatecraft.user.level.Skill;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkillUpEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private final UUID target;
	private final Skill skill;
	private final int level;

	public SkillUpEvent(final UUID target, final Skill skill, final int level) {
		this.target = target;
		this.skill = skill;
		this.level = level;
	}

	public UUID getTarget() {
		return target;
	}

	public Skill getSkill() {
		return skill;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public String toString() {
		return "SkillUpEvent [target=" + target + ", skill=" + skill + ", level=" + level + "]";
	}
}
