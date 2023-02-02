package fr.yezzipe.zelda.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class SpawnNPCCommandCompleter implements TabCompleter {
  public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg0 instanceof Player) {
      Player p = (Player)arg0;
      if (arg3.length == 1) {
        List<String> list = new ArrayList<>();
        list.add(p.getName());
        return list;
      } 
      if (arg3.length == 2) {
        List<String> list = new ArrayList<>();
        list.add(p.getName());
        return list;
      } 
    } 
    return null;
  }
}
