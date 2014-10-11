package io.arkeus.fate.listener;

import io.arkeus.fate.FatePlugin;

import org.bukkit.event.Listener;

public class FateListener<T extends FatePlugin<T>> implements Listener {
	private T plugin;

	public void setPlugin(final T plugin) {
		this.plugin = plugin;
	}

	public T getPlugin() {
		return plugin;
	}
}
