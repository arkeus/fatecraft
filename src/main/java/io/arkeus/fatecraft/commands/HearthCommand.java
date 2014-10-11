package io.arkeus.fatecraft.commands;

import io.arkeus.fatecraft.user.User;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HearthCommand extends PlayerCommand {
	@Override
	public boolean onCommand(final Player sender, final Command command, final String label, final String[] args) {
		final User user = getPlugin().getUsers().get(sender.getUniqueId());
		if (user.getHome() == null) {
			getPlugin().getChat().send(sender.getUniqueId(), "You have not yet set a home position with the /hearth command.");
			return true;
		}
		user.hearth();
		getPlugin().getChat().send(sender.getUniqueId(), "You close your eyes and feel your body whisked away.");
		return true;
	}

	@Override
	public String getCommand() {
		return "hearth";
	}
}
