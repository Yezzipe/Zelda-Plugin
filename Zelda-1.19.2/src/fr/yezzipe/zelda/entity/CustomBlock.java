package fr.yezzipe.zelda.entity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.gson.reflect.TypeToken;
import com.mojang.datafixers.util.Pair;

import de.tr7zw.nbtapi.NBTBlock;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.Registry;
import fr.yezzipe.zelda.blocks.BlockBuilder;
import fr.yezzipe.zelda.blocks.enums.BlockEnum;
import fr.yezzipe.zelda.entity.player.PacketManager;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.decoration.EntityArmorStand;

public class CustomBlock {
    private transient EntityArmorStand as;

    private transient ItemStack item;

    private BlockEnum b;

    private String uuid;

    private transient Block block;

    private transient Block light;

    private int lightLevel;

    private CustomBlockMemory blockMemory;

    private CustomBlockMemory lightMemory;

    private static Registry<UUID, CustomBlock> registry = new Registry<UUID, CustomBlock>();

    private static String folderPrefix = "blocks/";

    public CustomBlock(Block block, int lightLevel, BlockEnum b) {
	this.block = block;
	this.light = block.getRelative(BlockFace.UP);
	this.lightLevel = lightLevel;
	block.setType(Material.BARRIER);
	light.setType(Material.LIGHT);
	Levelled level = (Levelled) light.getBlockData();
	level.setLevel(lightLevel);
	light.setBlockData(level);
	CraftWorld cw = (CraftWorld) block.getWorld();
	as = new EntityArmorStand((net.minecraft.world.level.World) cw.getHandle(), block.getX() + 0.5,
		block.getY() - 1, block.getZ() + 0.5);
	PacketPlayOutSpawnEntity packet1 = new PacketPlayOutSpawnEntity(as);
	PacketManager.sendPacketToAll(packet1);
	DataWatcher watcher = new DataWatcher(as);
	watcher.a(new DataWatcherObject<>(0, DataWatcherRegistry.a), Byte.valueOf((byte) (1 << 5)));
	PacketPlayOutEntityMetadata packet2 = new PacketPlayOutEntityMetadata(as.ae(), watcher, true);
	PacketManager.sendPacketToAll(packet2);
	NBTBlock nbt2 = new NBTBlock(block);
	uuid = UUID.randomUUID().toString();
	registry.bind(getUUID(), this);
	nbt2.getData().setString("LinkedArmorStand", uuid.toString());
	setItem(b);

    }

    public UUID getUUID() {
	return UUID.fromString(uuid);
    }

    public void setItem(BlockEnum be) {
	ItemStack item = BlockBuilder.build(be);
	this.b = be;
	List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> list = new ArrayList<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>>();
	Pair<EnumItemSlot, net.minecraft.world.item.ItemStack> pair = new Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>(
		EnumItemSlot.f, CraftItemStack.asNMSCopy((CraftItemStack) item));
	list.add(pair);
	PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(as.ae(), list);
	PacketManager.sendPacketToAll(packet);
	this.item = item;
    }

    public void setLightLevel(int l) {
	Levelled level = (Levelled) light.getBlockData();
	level.setLevel(l);
	light.setBlockData(level);
	this.lightLevel = l;
    }

    public void remove() {
	PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(as.ae());
	PacketManager.sendPacketToAll(packet);
	NBTBlock nbt = new NBTBlock(block);
	block.setType(Material.AIR);
	light.setType(Material.AIR);
	nbt.getData().clearNBT();
	Main.remove(folderPrefix + uuid);
	item = null;
	as = null;
	block = null;
	light = null;
	registry.unbind(getUUID());
	uuid = null;
	lightLevel = 0;
	blockMemory = null;
	lightMemory = null;
	b = null;
    }

    public static CustomBlock getCustomBlock(UUID uuid) {
	return registry.get(uuid);
    }

    public static CustomBlock getCustomBlock(String uuid) {
	return getCustomBlock(UUID.fromString(uuid));
    }
    
    public Block getBlock() {
	return this.block;
    }

    public void sendToPlayer(Player player) {
	PacketPlayOutSpawnEntity packet1 = new PacketPlayOutSpawnEntity(as);
	PacketManager.sendPacket(player, packet1);
	DataWatcher watcher = new DataWatcher(as);
	watcher.a(new DataWatcherObject<>(0, DataWatcherRegistry.a), Byte.valueOf((byte) (1 << 5)));
	PacketPlayOutEntityMetadata packet2 = new PacketPlayOutEntityMetadata(as.ae(), watcher, true);
	PacketManager.sendPacket(player, packet2);
	List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> list = new ArrayList<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>>();
	Pair<EnumItemSlot, net.minecraft.world.item.ItemStack> pair = new Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>(
		EnumItemSlot.f, CraftItemStack.asNMSCopy((CraftItemStack) item));
	list.add(pair);
	PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(as.ae(), list);
	PacketManager.sendPacket(player, packet);
    }

    public static void initPlayer(Player player) {
	Set<UUID> uuids = registry.keys();
	for (UUID uuid : uuids) {
	    getCustomBlock(uuid).sendToPlayer(player);
	}
    }

    public static void saveAll() {
	Set<UUID> uuids = registry.keys();
	Main.write(String.valueOf(folderPrefix) + "list", uuids);
	for (UUID uuid : uuids) {
	    getCustomBlock(uuid).save();
	}
    }

    public void save() {
	this.blockMemory = new CustomBlockMemory(block);
	this.lightMemory = new CustomBlockMemory(light);
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
	PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(as.ae());
	PacketManager.sendPacketToAll(packet);
	as = null;
    }

    public static void initAll() {
	if (!Main.exist(String.valueOf(folderPrefix) + "list"))
	    return;
	Type list = (new TypeToken<Set<UUID>>() {
	}).getType();
	Type type = (new TypeToken<CustomBlock>() {
	}).getType();
	Set<UUID> uuids = Main.read(folderPrefix + "list", list);
	if (uuids == null) return;
	for (UUID uuid : uuids) {
	    CustomBlock cb = Main.read(folderPrefix + uuid.toString(), type);
	    if (cb != null)
		cb.init();
	}
    }

    public void init() {
	this.block = blockMemory.getBlock();
	this.light = lightMemory.getBlock();
	NBTBlock nbt = new NBTBlock(block);
	nbt.getData().setString("LinkedArmorStand", uuid.toString());
	blockMemory = null;
	lightMemory = null;
	Levelled level = (Levelled) light.getBlockData();
	level.setLevel(lightLevel);
	light.setBlockData(level);
	CraftWorld cw = (CraftWorld) block.getWorld();
	as = new EntityArmorStand((net.minecraft.world.level.World) cw.getHandle(), block.getX() + 0.5,
		block.getY() - 1, block.getZ() + 0.5);
	PacketPlayOutSpawnEntity packet1 = new PacketPlayOutSpawnEntity(as);
	PacketManager.sendPacketToAll(packet1);
	DataWatcher watcher = new DataWatcher(as);
	watcher.a(new DataWatcherObject<>(0, DataWatcherRegistry.a), Byte.valueOf((byte) (1 << 5)));
	PacketPlayOutEntityMetadata packet2 = new PacketPlayOutEntityMetadata(as.ae(), watcher, true);
	PacketManager.sendPacketToAll(packet2);
	setItem(b);
	registry.bind(getUUID(), this);
    }
}
