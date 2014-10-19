package io.arkeus.fatecraft.commands;

import io.arkeus.fatecraft.user.User;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HearthCommand extends PlayerCommand {
	@Override
	public boolean onCommand(final Player sender, final Command command, final String label, final String[] args) {
		final User user = getPlugin().getUsers().get(sender.getUniqueId());
		if (user.hearth()) {
			getPlugin().getChat().send(sender.getUniqueId(), "You close your eyes and feel your body whisked away.");
		} else {
			getPlugin().getChat().send(sender.getUniqueId(), "You must set your home location at a bed to gain the ability to hearth.");
		}
		return true;
	}

	@Override
	public String getCommand() {
		return "hearth";
	}
}
