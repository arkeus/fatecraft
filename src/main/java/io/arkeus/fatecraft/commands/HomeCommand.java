package io.arkeus.fatecraft.commands;

import io.arkeus.fatecraft.user.User;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HomeCommand extends PlayerCommand {
	@Override
	public boolean onCommand(final Player sender, final Command command, final String label, final String[] args) {
		final User user = getPlugin().getUsers().get(sender.getUniqueId());
		user.setHome(sender.getLocation());
		getPlugin().getChat().send(sender.getUniqueId(), "Your home location has been set.");
		return true;
	}

	@Override
	public String getCommand() {
		return "home";
	}
}
