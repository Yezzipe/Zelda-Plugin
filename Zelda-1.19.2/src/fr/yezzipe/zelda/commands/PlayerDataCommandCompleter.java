package fr.yezzipe.zelda.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.yezzipe.zelda.entity.enums.Race;

public class PlayerDataCommandCompleter implements TabCompleter {
  public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg3.length == 1) {
      List<String> strings = new ArrayList<>();
      strings.add("change");
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
      List<String> players = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers())
        players.add(p.getName()); 
      Pattern pattern = Pattern.compile("^" + arg3[1], 8);
      List<String> strings = new ArrayList<>();
      for (String s : players) {
        Matcher matcher = pattern.matcher(s.toString());
        if (matcher.find())
          strings.add(s.toString()); 
      } 
      return strings;
    } 
    if (arg3.length == 3) {
      String ring = "^" + arg3[2];
      Pattern pattern = Pattern.compile(ring, 10);
      List<String> strings = new ArrayList<>();
      byte b;
      int i;
      Race[] arrayOfRace;
      for (i = (arrayOfRace = Race.values()).length, b = 0; b < i; ) {
        Race r = arrayOfRace[b];
        Matcher matcher = pattern.matcher(r.toString());
        if (matcher.find())
          strings.add(r.toString()); 
        b++;
      } 
      return strings;
    } 
    if (arg3.length == 4) {
      List<String> strings = new ArrayList<>();
      strings.add("0");
      strings.add("1");
      strings.add("2");
      strings.add("3");
      strings.add("4");
      strings.add("5");
      strings.add("6");
      strings.add("7");
      strings.add("8");
      strings.add("9");
      strings.add("10");
      Pattern pattern = Pattern.compile("^" + arg3[3], 10);
      List<String> strings2 = new ArrayList<>();
      for (String s : strings) {
        Matcher matcher = pattern.matcher(s.toString());
        if (matcher.find())
          strings2.add(s.toString()); 
      } 
      return strings2;
    } 
    return null;
  }
}
