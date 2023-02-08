package fr.yezzipe.zelda.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class StructureWriteCommandCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    List<String> list = new ArrayList<>();
	    switch (arg3.length) {
	    case 1:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getX()));
		break;
	    case 2:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getY()));
		break;
	    case 3:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getZ()));
		break;
	    case 4:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getX()));
		break;
	    case 5:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getY()));
		break;
	    case 6:
		list.add(Integer.toString(p.getTargetBlock(null, 10).getZ()));
		break;
	    }
	    return list;
	}
	return null;
    }
}
