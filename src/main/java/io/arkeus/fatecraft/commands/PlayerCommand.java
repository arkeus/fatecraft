package io.arkeus.fatecraft.commands;

import io.arkeus.fate.command.FateCommand;
import io.arkeus.fatecraft.Fatecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends FateCommand<Fatecraft> {
	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		return onCommand((Player) sender, command, label, args);
	}

	public abstract boolean onCommand(final Player sender, final Command command, final String label, final String[] args);
}
