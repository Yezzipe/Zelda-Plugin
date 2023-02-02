package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.player.PlayerData;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerDataCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg3.length == 4) {
      if (arg3[0].equals("change")) {
        String playerName = arg3[1];
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Player p = null;
        boolean playerValid = false;
        for (Player player : players) {
          if (player.getName().equals(playerName)) {
            playerValid = true;
            p = player;
          } 
        } 
        String race = arg3[2];
        boolean raceValid = false;
        Race newRace = null;
        byte b;
        int i;
        Race[] arrayOfRace;
        for (i = (arrayOfRace = Race.values()).length, b = 0; b < i; ) {
          Race r = arrayOfRace[b];
          if (r.toString().equals(race)) {
            raceValid = true;
            newRace = r;
          } 
          b++;
        } 
        String bonusHealth = arg3[3];
        if (playerValid && raceValid && Main.isInt(bonusHealth)) {
          int bonus = (Integer.parseInt(bonusHealth) > 10) ? 10 : Integer.parseInt(bonusHealth);
          PlayerData PData = PlayerData.getData(p);
          PData.setCurrentRace(newRace);
          PData.setBonusHealth(bonus);
          PData.tick(true, true, true);
          PlayerData.applyColors(p);
          PlayerData.applyAttributes(p, true);
          arg0.sendMessage("§aSuccessfully changed player's data !");
        } else {
          arg0.sendMessage("§cWrong Syntax in Command");
        } 
      } else {
        arg0.sendMessage("§cWrong Syntax in Command");
      } 
    } else {
      arg0.sendMessage("§cWrong Syntax in Command");
    } 
    return false;
  }
}

