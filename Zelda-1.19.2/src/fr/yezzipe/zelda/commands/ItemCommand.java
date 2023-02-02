package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.items.ItemBuilder;
import fr.yezzipe.zelda.items.RingBuilder;
import fr.yezzipe.zelda.items.RupeeBuilder;
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
      Player p = (Player)arg0;
      if (arg3.length == 2) {
        if (arg3[0].equals("ring")) {
          Ring ring = Ring.valueOf(arg3[1]);
          if (ring == null) {
            arg0.sendMessage("§cWrong Syntax in Command");
            return false;
          } 
          ItemStack item = RingBuilder.build(ring);
          p.getInventory().addItem(new ItemStack[] { item });
        } else if (arg3[0].equals("item")) {
          Item item = Item.valueOf(arg3[1]);
          if (item == null) {
            arg0.sendMessage("§cWrong Syntax in Command");
            return false;
          } 
          ItemStack customItem = ItemBuilder.build(item);
          p.getInventory().addItem(new ItemStack[] { customItem });
        } else if (arg3[0].equals("rupee")) {
          Rupees rupee = Rupees.valueOf(arg3[1]);
          if (rupee == null) {
            arg0.sendMessage("§cWrong Syntax in Command");
            return false;
          } 
          ItemStack customItem = RupeeBuilder.build(rupee);
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
