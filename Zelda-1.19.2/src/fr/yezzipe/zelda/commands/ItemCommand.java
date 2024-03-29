package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.blocks.BlockBuilder;
import fr.yezzipe.zelda.blocks.enums.BlockEnum;
import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.Ingredient;
import fr.yezzipe.zelda.items.enums.Item;
import fr.yezzipe.zelda.items.enums.Ring;
import fr.yezzipe.zelda.items.enums.Rupees;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    if (arg3.length == 2) {
		if (arg3[0].equals("ring")) {
		    Ring ring = Ring.valueOf(arg3[1]);
		    if (ring == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack item = ring.getRing();
		    p.getInventory().addItem(new ItemStack[] { item });
		} else if (arg3[0].equals("item")) {
		    Item item = Item.valueOf(arg3[1]);
		    if (item == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    p.getInventory().addItem(new ItemStack[] { item.getItem() });
		} else if (arg3[0].equals("rupee")) {
		    Rupees rupee = Rupees.valueOf(arg3[1]);
		    if (rupee == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    p.getInventory().addItem(new ItemStack[] { rupee.getRupee() });
		} else if (arg3[0].equals("block")) {
		    BlockEnum b = BlockEnum.valueOf(arg3[1]);
		    if (b == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack customItem = BlockBuilder.build(b);
		    p.getInventory().addItem(new ItemStack[] { customItem });
		} else if (arg3[0].equals("food")) {
		    Food b = Food.valueOf(arg3[1]);
		    if (b == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack customItem = b.getFood();
		    p.getInventory().addItem(new ItemStack[] { customItem });
		} else if (arg3[0].equals("ingredient")) {
		    Ingredient b = Ingredient.valueOf(arg3[1]);
		    if (b == null) {
			arg0.sendMessage("§cWrong Syntax in Command");
			return false;
		    }
		    ItemStack customItem = b.getIngredient();
		    p.getInventory().addItem(new ItemStack[] { customItem });
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
