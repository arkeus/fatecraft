package io.arkeus.fate.component.impl;

import io.arkeus.fate.FatePlugin;
import io.arkeus.fate.component.Component;

import java.util.UUID;

import org.bukkit.entity.Player;

public class ChatComponent<T extends FatePlugin<T>> extends Component<T> {
	public ChatComponent(final T plugin) {
		super(plugin);
	}

	public void sendAll(final String message) {
		for (final Player player : getPlugin().getServer().getOnlinePlayers()) {
			player.sendMessage(message);
		}
	}

	public void send(final UUID id, final String message) {
		final Player player = getPlugin().getServer().getPlayer(id);
		if (player.isOnline()) {
			player.sendMessage(message);
		}
	}
}
