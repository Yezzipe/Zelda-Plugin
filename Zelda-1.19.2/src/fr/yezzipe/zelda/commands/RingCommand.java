package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.inventory.CustomInventoryType;
import fr.yezzipe.zelda.inventory.InventoryManager;
import fr.yezzipe.zelda.inventory.RingManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RingCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg0 instanceof Player) {
      Player p = (Player)arg0;
      Inventory inv = InventoryManager.createInventory(null, 9, "Rings", CustomInventoryType.RINGS);
      RingManager.populateRings(inv, p);
    } else {
      arg0.sendMessage("You're not a Player.");
    } 
    return false;
  }
}

