package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.territory.structures.StructureData;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StructureBuildCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg0 instanceof Player) {
      if (arg3.length == 1) {
        Player p = (Player)arg0;
        BlockFace face = p.getFacing();
        StructureData struc = StructureData.load(arg3[0], face);
        struc.build(p.getLocation(), face, p);
      } else {
        arg0.sendMessage("§4Wrong Arguments !");
      } 
    } else {
      arg0.sendMessage("§4You're not a player !");
    } 
    return false;
  }
}

