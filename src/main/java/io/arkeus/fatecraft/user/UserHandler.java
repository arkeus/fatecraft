package io.arkeus.fatecraft.user;

import io.arkeus.fatecraft.Fatecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class UserHandler {
	private final Fatecraft fc;
	private final Map<UUID, User> users;

	public UserHandler(final Fatecraft fc) {
		this.fc = fc;
		this.users = new HashMap<>();
	}

	public User get(final UUID id) {
		User character = users.get(id);
		if (character == null) {
			character = new User(fc, id);
			users.put(id, character);
		}
		return character;
	}

	public User get(final Player player) {
		return get(player.getUniqueId());
	}
}
