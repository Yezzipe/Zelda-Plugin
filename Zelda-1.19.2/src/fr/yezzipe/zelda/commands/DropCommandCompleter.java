package fr.yezzipe.zelda.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.yezzipe.zelda.items.enums.Drop;

public class DropCommandCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    if (arg3.length == 1) {
		List<String> strings = new ArrayList<>();
		strings.add("absolute");
		strings.add("relative");
		Pattern pattern = Pattern.compile("^" + arg3[0], 10);
		List<String> strings2 = new ArrayList<>();
		for (String s : strings) {
		    Matcher matcher = pattern.matcher(s.toString());
		    if (matcher.find())
			strings2.add(s.toString());
		}
		return strings2;
	    }
	    if (arg3.length == 2) {
		String drop = "^" + arg3[1];
		Pattern pattern = Pattern.compile(drop, 10);
		List<String> strings = new ArrayList<>();
		byte b;
		int i;
		Drop[] arrayOfDrop;
		for (i = (arrayOfDrop = Drop.values()).length, b = 0; b < i;) {
		    Drop d = arrayOfDrop[b];
		    Matcher matcher = pattern.matcher(d.toString());
		    if (matcher.find())
			strings.add(d.toString());
		    b++;
		}
		return strings;
	    }
	    if (arg3.length == 3) {
		List<String> strings = new ArrayList<>();
		if (arg3[0].equals("relative")) {
		    strings.add("0");
		} else if (arg3[0].equals("absolute")) {
		    strings.add(Integer.toString(p.getLocation().getBlockX()));
		}
		return strings;
	    }
	    if (arg3.length == 4) {
		List<String> strings = new ArrayList<>();
		if (arg3[0].equals("relative")) {
		    strings.add("0");
		} else if (arg3[0].equals("absolute")) {
		    strings.add(Integer.toString(p.getLocation().getBlockY()));
		}
		return strings;
	    }
	    if (arg3.length == 5) {
		List<String> strings = new ArrayList<>();
		if (arg3[0].equals("relative")) {
		    strings.add("0");
		} else if (arg3[0].equals("absolute")) {
		    strings.add(Integer.toString(p.getLocation().getBlockZ()));
		}
		return strings;
	    }
	}
	return null;
    }
}
