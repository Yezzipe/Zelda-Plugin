package fr.yezzipe.zelda.entity.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamHandler {
  private Team piaf;
  
  private Team zora;
  
  private Team goron;
  
  private Team gerudo;
  
  private Team sheika;
  
  private Team twili;
  
  private Team hylien;
  
  private Team kokiri;
  
  private Team hylia;
  
  public TeamHandler(Scoreboard board) {
    this.piaf = board.registerNewTeam("piaf");
    this.piaf.setColor(ChatColor.AQUA);
    this.zora = board.registerNewTeam("zora");
    this.zora.setColor(ChatColor.BLUE);
    this.goron = board.registerNewTeam("goron");
    this.goron.setColor(ChatColor.DARK_RED);
    this.gerudo = board.registerNewTeam("gerudo");
    this.gerudo.setColor(ChatColor.YELLOW);
    this.sheika = board.registerNewTeam("sheika");
    this.sheika.setColor(ChatColor.DARK_PURPLE);
    this.twili = board.registerNewTeam("twili");
    this.twili.setColor(ChatColor.DARK_GRAY);
    this.hylien = board.registerNewTeam("hylien");
    this.hylien.setColor(ChatColor.GRAY);
    this.kokiri = board.registerNewTeam("kokiri");
    this.kokiri.setColor(ChatColor.GREEN);
    this.hylia = board.registerNewTeam("hylia");
    this.hylia.setColor(ChatColor.GOLD);
  }
  
  public void addPlayer(Player p) {
    PlayerData playerData = PlayerData.getData(p);
    switch (playerData.getCurrentRace()) {
      case PIAF:
        this.piaf.addEntry(p.getName());
        break;
      case ZORA:
        this.zora.addEntry(p.getName());
        break;
      case GORON:
        this.goron.addEntry(p.getName());
        break;
      case GERUDO:
        this.gerudo.addEntry(p.getName());
        break;
      case SHEIKA:
        this.sheika.addEntry(p.getName());
        break;
      case TWILI:
        this.twili.addEntry(p.getName());
        break;
      case HYLIEN:
        this.hylien.addEntry(p.getName());
        break;
      case KOKIRI:
        this.kokiri.addEntry(p.getName());
        break;
      case HYLIA:
        this.hylia.addEntry(p.getName());
        break;
      default:
		break;
    } 
  }
  
  public void removePlayer(Player p) {
    this.piaf.removeEntry(p.getName());
    this.zora.removeEntry(p.getName());
    this.goron.removeEntry(p.getName());
    this.gerudo.removeEntry(p.getName());
    this.sheika.removeEntry(p.getName());
    this.twili.removeEntry(p.getName());
    this.hylien.removeEntry(p.getName());
    this.kokiri.removeEntry(p.getName());
    this.hylia.removeEntry(p.getName());
  }
}

