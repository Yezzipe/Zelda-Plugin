package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.items.enums.Item;
import fr.yezzipe.zelda.items.enums.Ring;
import fr.yezzipe.zelda.items.enums.Rupees;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ItemCommandCompleter implements TabCompleter {
  public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg3.length == 1) {
      List<String> strings = new ArrayList<>();
      strings.add("ring");
      strings.add("item");
      strings.add("rupee");
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
      if (arg3[0].equals("ring")) {
        String ring = "^" + arg3[1];
        Pattern pattern = Pattern.compile(ring, 10);
        List<String> strings = new ArrayList<>();
        byte b;
        int i;
        Ring[] arrayOfRing;
        for (i = (arrayOfRing = Ring.values()).length, b = 0; b < i; ) {
          Ring r = arrayOfRing[b];
          Matcher matcher = pattern.matcher(r.toString());
          if (matcher.find())
            strings.add(r.toString()); 
          b++;
        } 
        return strings;
      } 
      if (arg3[0].equals("item")) {
        String ring = "^" + arg3[1];
        Pattern pattern = Pattern.compile(ring, 10);
        List<String> strings = new ArrayList<>();
        byte b;
        int i;
        Item[] arrayOfItem;
        for (i = (arrayOfItem = Item.values()).length, b = 0; b < i; ) {
          Item r = arrayOfItem[b];
          Matcher matcher = pattern.matcher(r.toString());
          if (matcher.find())
            strings.add(r.toString()); 
          b++;
        } 
        return strings;
      } 
      if (arg3[0].equals("rupee")) {
        String ring = "^" + arg3[1];
        Pattern pattern = Pattern.compile(ring, 10);
        List<String> strings = new ArrayList<>();
        byte b;
        int i;
        Rupees[] arrayOfRupees;
        for (i = (arrayOfRupees = Rupees.values()).length, b = 0; b < i; ) {
          Rupees r = arrayOfRupees[b];
          Matcher matcher = pattern.matcher(r.toString());
          if (matcher.find())
            strings.add(r.toString()); 
          b++;
        } 
        return strings;
      } 
    } 
    return null;
  }
}
