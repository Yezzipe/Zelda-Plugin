package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.territory.structures.StructureWriter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StructureWriteCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg0 instanceof Player) {
      Player p = (Player)arg0;
      if (arg3.length == 7) {
        if (Main.isInt(arg3[0]) && Main.isInt(arg3[1]) && Main.isInt(arg3[2]) && Main.isInt(arg3[3]) && Main.isInt(arg3[4]) && Main.isInt(arg3[5])) {
          StructureWriter writer = new StructureWriter(Integer.parseInt(arg3[0]), Integer.parseInt(arg3[1]), Integer.parseInt(arg3[2]), Integer.parseInt(arg3[3]), Integer.parseInt(arg3[4]), Integer.parseInt(arg3[5]), p.getWorld());
          writer.save(arg3[6]);
          p.sendMessage("§bStructure Saved");
        } else {
          p.sendMessage("§4Wrong Arguments");
        } 
      } else {
        p.sendMessage("§4Wrong Arguments");
      } 
    } else {
      arg0.sendMessage("§4You're not a Player !");
    } 
    return false;
  }
}

