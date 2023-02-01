package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.territory.structures.StructureData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StructureUpdateCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg3.length == 1) {
      StructureData.update(arg3[0]);
      arg0.sendMessage("§bStructure Updated");
    } 
    return false;
  }
}

