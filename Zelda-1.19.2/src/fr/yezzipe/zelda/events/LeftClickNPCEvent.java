package fr.yezzipe.zelda.events;

import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LeftClickNPCEvent extends Event implements Cancellable {
  private final Player player;
  
  private final EntityPlayer npc;
  
  private final CustomNPC npcBrain;
  
  private final NPCMemory memory;
  
  private boolean isCancelled;
  
  private static final HandlerList HANDLERS = new HandlerList();
  
  public LeftClickNPCEvent(Player player, EntityPlayer npc, CustomNPC npcBrain, NPCMemory memory) {
    this.player = player;
    this.npc = npc;
    this.npcBrain = npcBrain;
    this.memory = memory;
  }
  
  public boolean isCancelled() {
    return this.isCancelled;
  }
  
  public void setCancelled(boolean arg0) {
    this.isCancelled = arg0;
  }
  
  public HandlerList getHandlers() {
    return HANDLERS;
  }
  
  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public CustomNPC getNpcBrain() {
    return this.npcBrain;
  }
  
  public EntityPlayer getNpc() {
    return this.npc;
  }
  
  public NPCMemory getMemory() {
    return this.memory;
  }
}

