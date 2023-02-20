package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.inventory.ExchangeInventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExchangeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    ExchangeInventoryManager manager = new ExchangeInventoryManager();
	    p.openInventory(manager.getInventory());
	} else {
	    arg0.sendMessage("You're not a Player.");
	}
	return false;
    }
}
