package fr.yezzipe.zelda.entity.player;

import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.PlayerConnection;

public class PacketManager {

    public static <T> void sendPacket(Player player, T packet) {
	if (packet instanceof Packet) {
	    PlayerConnection conn = (((CraftPlayer) player).getHandle()).b;
	    conn.a((Packet<?>)packet);
	}
    }
    
    public static byte getMetaData(Entity entity, byte mask) {
	byte b = 0;
	if (entity instanceof Player) {
	    Player p = (Player) entity;
	    b = (byte) (b | (p.isGliding() ? 1 << 7 : 0));
	    b = (byte) (b | (p.isSprinting() ? 1 << 3 : 0));
	    b = (byte) (b | (p.isSneaking() ? 1 << 1 : 0));
	}
	b = (byte) (b | (entity.isGlowing() ? 1 << 6 : 0));
	b = (byte) (b | (entity.getFireTicks() > 0 ? 1 : 0));
	if (entity instanceof LivingEntity) {
	    LivingEntity l = (LivingEntity) entity;
	    b = (byte) (b | (l.isInvisible() ? 1 << 5 : 0));
	    b = (byte) (b | (l.isSwimming() ? 1 << 4 : 0));
	}
	b = (byte) (b | mask);
	return b;
    }
}
