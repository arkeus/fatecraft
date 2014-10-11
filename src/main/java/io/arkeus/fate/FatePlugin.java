package io.arkeus.fate;

import io.arkeus.fate.command.FateCommand;
import io.arkeus.fate.component.impl.ChatComponent;
import io.arkeus.fate.listener.FateListener;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class FatePlugin<T extends FatePlugin<T>> extends JavaPlugin {
	private final T plugin;
	private final String name;
	private final String version;
	private final ChatComponent<T> chat;
	private final List<Class<? extends FateListener<?>>> listeners;
	private final List<Class<? extends FateCommand<?>>> commands;

	@SuppressWarnings("unchecked")
	public FatePlugin(final String name, final String version, final List<Class<? extends FateListener<?>>> listeners, final List<Class<? extends FateCommand<?>>> commands) {
		this.name = name;
		this.version = version;
		this.listeners = listeners;
		this.commands = commands;
		this.plugin = (T) this;
		this.chat = new ChatComponent<T>(plugin);
	}

	@Override
	public void onEnable() {
		registerListeners(listeners);
		registerCommands(commands);
		chat.sendAll(name + " v" + version + " Enabled");
	}

	@Override
	public void onDisable() {
		chat.sendAll(name + " v" + version + " Disabled");
	}

	public final ChatComponent<T> getChat() {
		return chat;
	}

	@SuppressWarnings("unchecked")
	private void registerListeners(final List<Class<? extends FateListener<?>>> listeners) {
		for (final Class<? extends FateListener<?>> listenerClass : listeners) {
			FateListener<T> listener;
			try {
				listener = (FateListener<T>) listenerClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Failed to load listener with class " + listenerClass);
				continue;
			}
			listener.setPlugin(plugin);
			getServer().getPluginManager().registerEvents(listener, this);
			System.out.println("Registered listener with class " + listenerClass);
		}
	}

	@SuppressWarnings("unchecked")
	private void registerCommands(final List<Class<? extends FateCommand<?>>> commands) {
		for (final Class<? extends FateCommand<?>> commandClass : commands) {
			FateCommand<T> command;
			try {
				command = (FateCommand<T>) commandClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Failed to load command with class " + commandClass);
				continue;
			}
			command.setPlugin(plugin);
			System.out.println("command " + getCommand(command.getCommand()));
			getCommand(command.getCommand()).setExecutor(command);
			System.out.println("Registered command with class " + commandClass);
		}
	}
}
