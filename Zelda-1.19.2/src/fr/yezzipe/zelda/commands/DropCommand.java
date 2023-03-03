package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.items.enums.Drop;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropCommand implements CommandExecutor {
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    if (arg3.length == 5) {
		if (arg3[0].equals("absolute")) {
		    Drop drop = Drop.valueOf(arg3[1]);
		    if (drop == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack item = drop.getDrop();
		    if (Main.isInt(arg3[2]) && Main.isInt(arg3[3]) && Main.isInt(arg3[4])) {
			Location loc = new Location(p.getWorld(), Integer.parseInt(arg3[2]), Integer.parseInt(arg3[3]),
				Integer.parseInt(arg3[4]));
			p.getWorld().dropItem(loc, item);
		    } else {
			arg0.sendMessage("§cWrong Syntax in Command");
		    }
		} else if (arg3[0].equals("relative")) {
		    Drop drop = Drop.valueOf(arg3[1]);
		    if (drop == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack item = drop.getDrop();
		    if (Main.isInt(arg3[2]) && Main.isInt(arg3[3]) && Main.isInt(arg3[4])) {
			Location loc = new Location(p.getWorld(), p.getLocation().getX() + Integer.parseInt(arg3[2]),
				p.getLocation().getY() + Integer.parseInt(arg3[3]),
				p.getLocation().getZ() + Integer.parseInt(arg3[4]));
			p.getWorld().dropItem(loc, item);
		    } else {
			arg0.sendMessage("§cWrong Syntax in Command");
		    }
		} else {
		    arg0.sendMessage("§cWrong Syntax in Command");
		}
	    } else {
		arg0.sendMessage("§cWrong Syntax in Command");
	    }
	} else {
	    arg0.sendMessage("You're not a Player");
	}
	return false;
    }
}
