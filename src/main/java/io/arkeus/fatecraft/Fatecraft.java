package io.arkeus.fatecraft;

import io.arkeus.fate.FatePlugin;
import io.arkeus.fate.command.FateCommand;
import io.arkeus.fate.listener.FateListener;
import io.arkeus.fatecraft.commands.HearthCommand;
import io.arkeus.fatecraft.commands.HomeCommand;
import io.arkeus.fatecraft.listeners.ExperienceListener;
import io.arkeus.fatecraft.user.UserHandler;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.Event;

public class Fatecraft extends FatePlugin<Fatecraft> {
	private static final List<Class<? extends FateListener<?>>> LISTENERS = Arrays.<Class<? extends FateListener<?>>>asList(
			ExperienceListener.class
			);
	private static final List<Class<? extends FateCommand<?>>> COMMANDS = Arrays.<Class<? extends FateCommand<?>>>asList(
			HomeCommand.class,
			HearthCommand.class
			);
	private final UserHandler users;

	public Fatecraft() {
		super(Configuration.PLUGIN_NAME, Configuration.PLUGIN_VERSION, LISTENERS, COMMANDS);
		this.users = new UserHandler(this);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				getServer().getWorlds().get(0).setTime(0);
				System.out.println("Resetting time");
			}
		}, 0, 10000);
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public UserHandler getUsers() {
		return users;
	}

	public void sendEvent(final Event event) {
		getServer().getPluginManager().callEvent(event);
	}
}
