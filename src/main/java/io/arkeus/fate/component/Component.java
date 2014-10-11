package io.arkeus.fate.component;

import io.arkeus.fate.FatePlugin;

public class Component<T extends FatePlugin<T>> {
	private final T plugin;

	public Component(final T plugin) {
		this.plugin = plugin;
	}

	public final T getPlugin() {
		return plugin;
	}
}
