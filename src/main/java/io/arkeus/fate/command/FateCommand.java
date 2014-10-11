package io.arkeus.fate.command;

import io.arkeus.fate.FatePlugin;

import org.bukkit.command.CommandExecutor;

public abstract class FateCommand<T extends FatePlugin<T>> implements CommandExecutor {
	private T plugin;

	public void setPlugin(final T plugin) {
		this.plugin = plugin;
	}

	public final T getPlugin() {
		return plugin;
	}

	public abstract String getCommand();
}
