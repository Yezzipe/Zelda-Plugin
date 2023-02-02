package fr.yezzipe.zelda.entity;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.events.LeftClickNPCEvent;
import fr.yezzipe.zelda.events.RightClickNPCEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PacketReader {
    public static Map<UUID, ChannelDuplexHandler> channels = new HashMap<>();

    public static Map<UUID, BukkitRunnable> debounce = new HashMap<>();

    public void inject(final Player p) {
	CraftPlayer cp = (CraftPlayer) p;
	ChannelPipeline pipeline = (cp.getHandle()).b.b.m.pipeline();
	ChannelDuplexHandler handler = new ChannelDuplexHandler() {
	    int count = 0;

	    public void channelRead(ChannelHandlerContext context, Object obj) throws Exception {
		super.channelRead(context, obj);
		if (obj instanceof PacketPlayInUseEntity) {
		    PacketPlayInUseEntity packet = (PacketPlayInUseEntity) obj;
		    Field field = PacketReader.getField(packet.getClass(), "a");
		    final int id = field.getInt(obj);
		    this.count++;
		    if (this.count > 1) {
			if (PacketReader.debounce.get(p.getUniqueId()) != null) {
			    ((BukkitRunnable) PacketReader.debounce.get(p.getUniqueId())).cancel();
			    PacketReader.debounce.remove(p.getUniqueId());
			}
			BukkitRunnable runnable = new BukkitRunnable() {
			    public void run() {
				count = 0;
				PacketReader.debounce.remove(p.getUniqueId());
				Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getPlugin(Main.class),
					new Runnable() {
					    public void run() {
						for (CustomNPC npcBrain : NPCHandler.getNPCs()) {
						    for (EntityPlayer npc : NPCHandler.getShells(npcBrain)) {
							if (npc.ae() == id) {
							    NPCMemory memory = NPCHandler.NPCMemories.get(npcBrain);
							    Bukkit.getPluginManager()
								    .callEvent((Event) new RightClickNPCEvent(p, npc,
									    npcBrain, memory));
							}
						    }
						}
					    }
					});
			    }
			};
			runnable.runTaskLater((Plugin) Main.getPlugin(Main.class), 1L);
			PacketReader.debounce.put(p.getUniqueId(), runnable);
		    } else {
			if (PacketReader.debounce.get(p.getUniqueId()) != null) {
			    ((BukkitRunnable) PacketReader.debounce.get(p.getUniqueId())).cancel();
			    PacketReader.debounce.remove(p.getUniqueId());
			}
			BukkitRunnable runnable = new BukkitRunnable() {
			    public void run() {
				count = 0;
				PacketReader.debounce.remove(p.getUniqueId());
				Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getPlugin(Main.class),
					new Runnable() {
					    public void run() {
						for (CustomNPC npcBrain : NPCHandler.getNPCs()) {
						    for (EntityPlayer npc : NPCHandler.getShells(npcBrain)) {
							if (npc.ae() == id) {
							    NPCMemory memory = NPCHandler.NPCMemories.get(npcBrain);
							    Bukkit.getPluginManager()
								    .callEvent((Event) new LeftClickNPCEvent(p, npc,
									    npcBrain, memory));
							    return;
							}
						    }
						}
					    }
					});
			    }
			};
			runnable.runTaskLater((Plugin) Main.getPlugin(Main.class), 1L);
			PacketReader.debounce.put(p.getUniqueId(), runnable);
		    }
		}
	    }

	    public void write(ChannelHandlerContext context, Object obj, ChannelPromise promise) throws Exception {
		super.write(context, obj, promise);
	    }
	};
	pipeline.addBefore("packet_handler", p.getUniqueId().toString(), (ChannelHandler) handler);
	channels.put(p.getUniqueId(), handler);
    }

    public void uninject(Player p) {
	if (channels.get(p.getUniqueId()) != null) {
	    CraftPlayer cp = (CraftPlayer) p;
	    ChannelPipeline pipeline = (cp.getHandle()).b.b.m.pipeline();
	    pipeline.remove(p.getUniqueId().toString());
	    channels.remove(p.getUniqueId());
	}
    }

    @SuppressWarnings("deprecation")
    public static Field getField(Class<?> clazz, String name) {
	try {
	    Field field = clazz.getDeclaredField(name);
	    if (!field.isAccessible())
		field.setAccessible(true);
	    return field;
	} catch (NoSuchFieldException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @SuppressWarnings("deprecation")
    public static Method getMethod(Class<?> clazz, String name) {
	try {
	    Method method = clazz.getDeclaredMethod(name, new Class[0]);
	    if (!method.isAccessible())
		method.setAccessible(true);
	    return method;
	} catch (NoSuchMethodException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
