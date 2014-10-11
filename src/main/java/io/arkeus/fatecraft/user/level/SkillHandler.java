package io.arkeus.fatecraft.user.level;

import java.util.HashMap;
import java.util.Map;

public class SkillHandler {
	private final Map<Skill, Experience> skills;

	public SkillHandler() {
		this.skills = new HashMap<>();
		for (final Skill skill : Skill.values()) {
			skills.put(skill, new Experience());
		}
	}

	public Experience getSkill(final Skill skill) {
		return skills.get(skill);
	}
}
