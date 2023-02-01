package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.territory.Waypoint;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnNPCCommand implements CommandExecutor {
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
    if (arg0 instanceof Player) {
      Player p = (Player)arg0;
      if (arg3.length == 0) {
        NPCMemory memory = NPCHandler.createNPCMemory(new Waypoint(p.getLocation()), p.getName(), p.getName());
        NPCHandler.mountBrains(memory.getWaypoint().getLocation().getChunk());
        CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
        for (Player player : Bukkit.getOnlinePlayers()) {
          final EntityPlayer shell = NPCHandler.createNPCShell(player, brain);
          (new Thread(new Runnable() {
                public void run() {
                  NPCHandler.showNPC(player, shell);
                  NPCHandler.hideNPC(player, shell);
                }
              })).start();
        } 
      } else if (arg3.length == 1) {
        NPCMemory memory = NPCHandler.createNPCMemory(new Waypoint(p.getLocation()), arg3[0], p.getName());
        NPCHandler.mountBrains(memory.getWaypoint().getLocation().getChunk());
        CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
        for (Player player : Bukkit.getOnlinePlayers()) {
          final EntityPlayer shell = NPCHandler.createNPCShell(player, brain);
          (new Thread(new Runnable() {
                public void run() {
                  NPCHandler.showNPC(player, shell);
                  NPCHandler.hideNPC(player, shell);
                }
              })).start();
        } 
      } else if (arg3.length == 2) {
        NPCMemory memory = NPCHandler.createNPCMemory(new Waypoint(p.getLocation()), arg3[0], arg3[1]);
        NPCHandler.mountBrains(memory.getWaypoint().getLocation().getChunk());
        CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
        for (Player player : Bukkit.getOnlinePlayers()) {
          final EntityPlayer shell = NPCHandler.createNPCShell(player, brain);
          (new Thread(new Runnable() {
                public void run() {
                  NPCHandler.showNPC(player, shell);
                  NPCHandler.hideNPC(player, shell);
                }
              })).start();
        } 
      } 
    } else {
      arg0.sendMessage("You're not a Player !");
    } 
    return false;
  }
}

