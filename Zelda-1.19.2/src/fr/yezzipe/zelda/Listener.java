package fr.yezzipe.zelda;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.blocks.BlockBuilder;
import fr.yezzipe.zelda.blocks.enums.BlockEnum;
import fr.yezzipe.zelda.entity.CustomBlock;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.PacketReader;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.events.EntityDarkDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityElectricDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityFireDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityIceDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityLightDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityPhysicalDamageByEntityEvent;
import fr.yezzipe.zelda.events.LeftClickNPCEvent;
import fr.yezzipe.zelda.events.ModifierCalculator;
import fr.yezzipe.zelda.events.RightClickNPCEvent;
import fr.yezzipe.zelda.events.enums.DamageType;
import fr.yezzipe.zelda.inventory.CookingInventoryManager;
import fr.yezzipe.zelda.inventory.InventoryManager;
import fr.yezzipe.zelda.inventory.RaceInventoryManager;
import fr.yezzipe.zelda.items.DropBuilder;
import fr.yezzipe.zelda.items.GrapplingHookManager;
import fr.yezzipe.zelda.items.HeartContainerManager;
import fr.yezzipe.zelda.items.ItemBuilder;
import fr.yezzipe.zelda.items.ItemTable;
import fr.yezzipe.zelda.items.RingBuilder;
import fr.yezzipe.zelda.items.RingCalculator;
import fr.yezzipe.zelda.items.RupeeBuilder;
import fr.yezzipe.zelda.items.ShadowCrystalManager;
import fr.yezzipe.zelda.items.enums.Drop;
import fr.yezzipe.zelda.items.enums.Item;
import fr.yezzipe.zelda.items.enums.Ring;
import fr.yezzipe.zelda.items.enums.Rupees;
import fr.yezzipe.zelda.territory.TerritoryChunk;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootTable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Listener implements org.bukkit.event.Listener {
    public static Collection<Material> chests = Arrays.asList(
	    new Material[] { Material.NETHERITE_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.GOLDEN_CHESTPLATE,
		    Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.LEATHER_CHESTPLATE });

    public static Collection<Location> disableLightning = new ArrayList<>();

    public static HashMap<Player, Boolean> chargingNPCs = new HashMap<>();

    public static HashMap<Player, List<Thread>> NPCTasks = new HashMap<>();

    @EventHandler
    public void onPlayerChangeDimension(PlayerChangedWorldEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	PData.tick(false, true, false);
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
	final Player p = e.getPlayer();
	final PlayerData PData = PlayerData.getData(p);
	PData.tick(false, false, false);
	if (PData.isAttachedToWall()) {
	    p.setGravity(false);
	    p.setVelocity(new Vector(0, 0, 0));
	    e.setTo(new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ(),
		    e.getTo().getYaw(), e.getTo().getPitch()));
	    return;
	}
	if (!p.hasGravity())
	    p.setGravity(true);
	if (e.getTo().getChunk() != e.getFrom().getChunk()) {
	    int x = e.getTo().getChunk().getX();
	    int z = e.getTo().getChunk().getZ();
	    Collection<Chunk> chunks2 = new ArrayList<>();
	    for (int i = -3; i < 4; i++) {
		for (int k = -3; k < 4; k++)
		    chunks2.add(e.getTo().getWorld().getChunkAt(x + i, z + k));
	    }
	    Collection<Chunk> chunks = new ArrayList<>();
	    for (int j = -1; j < 2; j++) {
		for (int k = -1; k < 2; k++)
		    chunks.add(e.getTo().getWorld().getChunkAt(x + j, z + k));
	    }
	    for (Chunk chunk : chunks) {
		List<NPCMemory> memories = NPCHandler.getLoadedNPCsInChunk(chunk);
		if (memories != null && !memories.isEmpty())
		    for (NPCMemory memory : memories) {
			CustomNPC brain = (CustomNPC) NPCHandler.NPCMemoryToBrain.get(memory);
			NPCHandler.hideNPCBrain(p, brain);
		    }
	    }
	    for (Chunk chunk : chunks2) {
		List<CustomNPC> brains = NPCHandler.mountBrains(chunk);
		if (brains != null)
		    for (CustomNPC brain : brains) {
			for (Player player : Bukkit.getOnlinePlayers())
			    NPCHandler.createNPCShell(player, brain);
		    }
	    }
	    Thread thread = new Thread(new Runnable() {
		public void run() {
		    if (!NPCHandler.allNPCLoaded(p)) {
			NPCHandler.showNPCs(p);
			NPCHandler.hideNPCs(p);
		    }
		    List<Thread> tasks = Listener.NPCTasks.get(p);
		    if (tasks != null) {
			if (!tasks.isEmpty()) {
			    if (tasks.contains(Thread.currentThread()))
				tasks.remove(Thread.currentThread());
			    if (!tasks.isEmpty()) {
				Thread nextThread = tasks.get(0);
				nextThread.start();
			    } else {
				Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			    }
			} else {
			    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			}
		    } else {
			Listener.chargingNPCs.put(p, Boolean.valueOf(false));
		    }
		    Listener.NPCTasks.put(p, tasks);
		}
	    });
	    if (chargingNPCs.get(p) == null)
		chargingNPCs.put(p, Boolean.valueOf(false));
	    if (((Boolean) chargingNPCs.get(p)).booleanValue()) {
		List<Thread> tasks = NPCTasks.get(p);
		if (tasks == null)
		    tasks = new ArrayList<>();
		tasks.add(thread);
		NPCTasks.put(p, tasks);
	    } else {
		chargingNPCs.put(p, Boolean.valueOf(true));
		thread.start();
	    }
	    (new Thread(new Runnable() {
		public void run() {
		    Chunk chunk1 = e.getFrom().getChunk();
		    Chunk chunk2 = e.getTo().getChunk();
		    TerritoryChunk TChunk1 = new TerritoryChunk(chunk1);
		    TerritoryChunk TChunk2 = new TerritoryChunk(chunk2);
		    if (TChunk1.getOwningRace() != TChunk2.getOwningRace())
			p.sendMessage("Leaving " + TChunk1.getOwningRace().toString() + " Domain and Entering "
				+ TChunk2.getOwningRace().toString() + " Domain");
		}
	    })).start();
	}
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
	ItemStack item = e.getItemDrop().getItemStack();
	Player p = e.getPlayer();
	NBTItem nbt = new NBTItem(item);
	if (nbt.getKeys().contains("ItemType") && nbt.getString("ItemType").equals(Item.GRAPPLING_HOOK.toString())) {
	    GrapplingHookManager manager = GrapplingHookManager.getFromPlayer(p);
	    if (manager != null)
		manager.cleanArrow();
	}
    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent e) {
	ItemStack item = e.getItem();
	NBTItem nbt = new NBTItem(item);
	if (nbt.getKeys().contains("DropType") && nbt.getString("DropType").equals(Drop.HEART.toString())) {
	    item.setAmount(0);
	    e.setItem(item);
	}
    }

    @EventHandler
    public void onHopperPickup(InventoryPickupItemEvent e) {
	if (e.getInventory().getType().equals(InventoryType.HOPPER)) {
	    ItemStack item = e.getItem().getItemStack();
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("DropType") && nbt.getString("DropType").equals(Drop.HEART.toString())) {
		e.setCancelled(true);
		e.getItem().remove();
	    }
	}
    }

    @EventHandler
    public void onEntityDies(EntityDeathEvent e) {
	LivingEntity ent = e.getEntity();
	EntityDamageEvent ede = ent.getLastDamageCause();
	if (ent instanceof Player) {
	    Player p = (Player) ent;
	    PlayerData PData = PlayerData.getData(p);
	    if (PData.getCurrentRace() == Race.PIAF) {
		List<ItemStack> drops = e.getDrops();
		ListIterator<ItemStack> iterator = drops.listIterator();
		while (iterator.hasNext()) {
		    ItemStack item = iterator.next();
		    ItemMeta meta = item.getItemMeta();
		    if (item.getType() == Material.ELYTRA && meta.isUnbreakable()) {
			iterator.remove();
		    }
		}
	    }
	}
	if (ede instanceof EntityDamageByEntityEvent) {
	    boolean proceed = false;
	    EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ede;
	    if (event.getDamager() instanceof Player && !(event.getEntity() instanceof Player)) {
		proceed = true;
	    } else if (event.getDamager() instanceof Projectile && !(event.getEntity() instanceof Player)) {
		Projectile proj = (Projectile) event.getDamager();
		if (proj.getShooter() instanceof Player)
		    proceed = true;
	    }
	    if (proceed) {
		List<ItemStack> items = e.getDrops();
		if ((int) (Math.random() * 100.0D) <= 5)
		    items.add(DropBuilder.build(Drop.HEART));
		if (e.getEntity() instanceof org.bukkit.entity.ElderGuardian
			|| e.getEntity() instanceof org.bukkit.entity.EnderDragon
			|| e.getEntity() instanceof org.bukkit.entity.Wither) {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 200) {
			items.add(RupeeBuilder.build(Rupees.SILVER));
		    } else if (rand <= 250) {
			items.add(RupeeBuilder.build(Rupees.GOLD));
		    }
		    rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 500)
			items.add(ItemBuilder.build(Item.HEART_PIECE));
		} else {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 50) {
			items.add(RupeeBuilder.build(Rupees.GREEN));
		    } else if (rand <= 10) {
			items.add(RupeeBuilder.build(Rupees.BLUE));
		    }
		}
	    } else {
		List<ItemStack> items = e.getDrops();
		if ((int) (Math.random() * 100.0D) <= 5)
		    items.add(DropBuilder.build(Drop.HEART));
		if (e.getEntity() instanceof org.bukkit.entity.ElderGuardian
			|| e.getEntity() instanceof org.bukkit.entity.EnderDragon
			|| e.getEntity() instanceof org.bukkit.entity.Wither) {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 20) {
			items.add(RupeeBuilder.build(Rupees.SILVER));
		    } else if (rand <= 25) {
			items.add(RupeeBuilder.build(Rupees.GOLD));
		    }
		    rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 50)
			items.add(ItemBuilder.build(Item.HEART_PIECE));
		} else {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 5) {
			items.add(RupeeBuilder.build(Rupees.GREEN));
		    } else if (rand <= 1) {
			items.add(RupeeBuilder.build(Rupees.BLUE));
		    }
		}
	    }
	} else {
	    List<ItemStack> items = e.getDrops();
	    if ((int) (Math.random() * 100.0D) <= 5)
		items.add(DropBuilder.build(Drop.HEART));
	    if (e.getEntity() instanceof org.bukkit.entity.ElderGuardian
		    || e.getEntity() instanceof org.bukkit.entity.EnderDragon
		    || e.getEntity() instanceof org.bukkit.entity.Wither) {
		int rand = (int) (Math.random() * 10000.0D);
		if (rand <= 20) {
		    items.add(RupeeBuilder.build(Rupees.SILVER));
		} else if (rand <= 25) {
		    items.add(RupeeBuilder.build(Rupees.GOLD));
		}
		rand = (int) (Math.random() * 10000.0D);
		if (rand <= 50)
		    items.add(ItemBuilder.build(Item.HEART_PIECE));
	    } else {
		int rand = (int) (Math.random() * 10000.0D);
		if (rand <= 5) {
		    items.add(RupeeBuilder.build(Rupees.GREEN));
		} else if (rand <= 1) {
		    items.add(RupeeBuilder.build(Rupees.BLUE));
		}
	    }
	}
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
	if (e.getEntity() instanceof Player) {
	    Player p = (Player) e.getEntity();
	    PlayerData PData = PlayerData.getData(p);
	    ItemStack item = e.getItem().getItemStack();
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("DropType")) {
		if (nbt.getString("DropType").equals(Drop.HEART.toString())) {
		    e.setCancelled(true);
		    e.getItem().remove();
		    double health = (p.getHealth() + 2.0D > p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
			    ? p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()
			    : (p.getHealth() + 2.0D);
		    p.setHealth(health);
		    PData.setHealth(health);
		    p.playSound(p.getLocation(), "zelda.heart.get", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		}
	    } else if (nbt.getKeys().contains("RupeeType")) {
		if (nbt.getString("RupeeType").equals(Rupees.GREEN.toString())) {
		    p.playSound(p.getLocation(), "zelda.rupee.get_green", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		} else if (nbt.getString("RupeeType").equals(Rupees.BLUE.toString())) {
		    p.playSound(p.getLocation(), "zelda.rupee.get_blue", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		} else if (nbt.getString("RupeeType").equals(Rupees.YELLOW.toString())
			|| nbt.getString("RupeeType").equals(Rupees.RED.toString())
			|| nbt.getString("RupeeType").equals(Rupees.PURPLE.toString())
			|| nbt.getString("RupeeType").equals(Rupees.SILVER.toString())
			|| nbt.getString("RupeeType").equals(Rupees.GOLD.toString())) {
		    p.playSound(p.getLocation(), "zelda.rupee.get_yellow", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		}
	    }

	}
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
	ItemStack item = e.getItem();
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	if (item != null && item.getType() != Material.AIR) {
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("ItemType")) {
		Item type = Item.valueOf(nbt.getString("ItemType"));
		switch (type) {
		case BOMB_BOW:
		    break;
		case DARK_BOW:
		    break;
		case ELECTRIC_BOW:
		    break;
		case FIRE_BOW:
		    break;
		case GRAPPLING_HOOK:
		    new GrapplingHookManager(e);
		    break;
		case HEART_CONTAINER:
		    new HeartContainerManager(e);
		    break;
		case HEART_PIECE:
		    break;
		case ICE_BOW:
		    break;
		case LIGHT_BOW:
		    break;
		case SHADOW_CRYSTAL:
		    new ShadowCrystalManager(e);
		    break;
		default:
		    break;

		}
	    } else if (nbt.getKeys().contains("BlockType") &&  e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		if (nbt.getString("BlockType").equals("Campfire_Unlit")) {
		    Block b = e.getClickedBlock();
		    if (b != null) {
			Block b2 = b.getRelative(e.getBlockFace());
			Block b3 = b2.getRelative(BlockFace.UP);
			if (b3.getType() != Material.AIR && b3.getType() != Material.VOID_AIR
				&& b3.getType() != Material.CAVE_AIR && b3 != null)
			    return;
			new CustomBlock(b2, 0, BlockEnum.CAMP_UNLIT);
			item.setAmount(item.getAmount() - 1);
		    }

		}
	    } else if (chests.contains(item.getType())) {
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
			&& PData.getCurrentRace() == Race.PIAF)
		    e.setCancelled(true);
	    } else if (item.getType() == Material.ELYTRA
		    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		    && PData.getCurrentRace() != Race.PIAF) {
		e.setCancelled(true);
	    } else if (item.getType() == Material.FLINT_AND_STEEL && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Block b = e.getClickedBlock();
		if (b != null) {
		    NBTBlock nb = new NBTBlock(b);
		    if (nb.getData().getKeys().contains("LinkedArmorStand")) {
			String uuid = nb.getData().getString("LinkedArmorStand");
			CustomBlock cb = CustomBlock.getCustomBlock(uuid);
			cb.setItem(BlockEnum.CAMP_LIT);
			cb.setLightLevel(15);
			Damageable m = (Damageable) item.getItemMeta();
			m.setDamage(m.getDamage() + 1);
			if (m.getDamage() > 64) {
			    item.setAmount(0);
			} else {
			    item.setItemMeta(m);
			}
			e.setCancelled(true);
		    }
		}
	    } else if (item.getType() == Material.WATER_BUCKET && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Block b = e.getClickedBlock();
		if (b != null) {
		    NBTBlock nb = new NBTBlock(b);
		    if (nb.getData().getKeys().contains("LinkedArmorStand")) {
			String uuid = nb.getData().getString("LinkedArmorStand");
			CustomBlock cb = CustomBlock.getCustomBlock(uuid);
			cb.setItem(BlockEnum.CAMP_UNLIT);
			cb.setLightLevel(0);
			item.setType(Material.BUCKET);
			e.setCancelled(true);
		    }
		}
	    }
	} else if (e.getHand() == EquipmentSlot.HAND) {
	    if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Block b = e.getClickedBlock();
		NBTBlock nbt2 = new NBTBlock(b);
		if (nbt2.getData().getKeys().contains("LinkedArmorStand")) {
		    if (p.isSneaking()) {
			String uuid = nbt2.getData().getString("LinkedArmorStand");
			CustomBlock cb = CustomBlock.getCustomBlock(uuid);
			if (cb == null) {
			 nbt2.getData().clearNBT();
			 if (b.getType() == Material.BARRIER) b.setType(Material.AIR);
			 if (b.getRelative(BlockFace.UP).getType() == Material.LIGHT) b.getRelative(BlockFace.UP).setType(Material.AIR);
			} else {
			cb.remove();
			p.getInventory().addItem(BlockBuilder.build(BlockEnum.CAMP_UNLIT));
			}
		    } else {
			String uuid = nbt2.getData().getString("LinkedArmorStand");
			CustomBlock cb = CustomBlock.getCustomBlock(uuid);
			CookingInventoryManager manager = new CookingInventoryManager(p, cb);
		    	p.openInventory(manager.getInventory());
		    }
		}
	    }
	}
    }

    public static Collection<Player> glideCanceled = new ArrayList<>();

    @EventHandler
    public void onProjectileLand(final ProjectileHitEvent e) {
	if (e.getHitBlock() != null) {
	    boolean isGrap = GrapplingHookManager.isHook(e.getEntity());
	    if (isGrap) {
		GrapplingHookManager manager = GrapplingHookManager.getFromArrow((Arrow) e.getEntity());
		manager.retractBlock();
	    } else {
		Projectile proj = e.getEntity();
		if (proj instanceof Arrow && proj.getPersistentDataContainer()
			.has(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"), PersistentDataType.STRING)) {
		    final Arrow arrow = (Arrow) proj;
		    String string = (String) arrow.getPersistentDataContainer().get(
			    new NamespacedKey((Plugin) Main.getInstance(), "DamageType"), PersistentDataType.STRING);
		    DamageType dmgType = DamageType.valueOf(string);
		    if (dmgType == DamageType.FIRE) {
			Block block = e.getHitBlock();
			BlockFace face = e.getHitBlockFace();
			Block relative = block.getRelative(face);
			Block relativeDown = relative.getRelative(BlockFace.DOWN);
			if (relative.getType() == Material.AIR || relative.getType() == Material.VOID_AIR
				|| relative.getType() == Material.CAVE_AIR)
			    if (relativeDown.getType() == Material.AIR || relativeDown.getType() == Material.VOID_AIR
				    || relativeDown.getType() == Material.CAVE_AIR) {
				relative.setType(Material.FIRE);
				BlockData data = relative.getBlockData();
				MultipleFacing facing = (MultipleFacing) data;
				switch (face) {
				case WEST:
				    facing.setFace(BlockFace.EAST, true);
				    break;
				case EAST:
				    facing.setFace(BlockFace.WEST, true);
				    break;
				case NORTH:
				    facing.setFace(BlockFace.SOUTH, true);
				    break;
				case SOUTH:
				    facing.setFace(BlockFace.NORTH, true);
				    break;
				default:
				    return;
				}
				relative.setBlockData((BlockData) facing);
			    } else {
				relative.setType(Material.FIRE);
			    }
		    } else if (dmgType == DamageType.ELECTRIC) {
			disableLightning.add(arrow.getLocation());
			arrow.getWorld().strikeLightning(arrow.getLocation());
			(new BukkitRunnable() {
			    public void run() {
				Listener.disableLightning.remove(arrow.getLocation());
			    }
			}).runTaskLaterAsynchronously((Plugin) Main.getInstance(), 40L);
		    } else if (dmgType == DamageType.BOMB) {
			Location loc = arrow.getLocation();
			arrow.remove();
			loc.getWorld().createExplosion(loc, 2, false, false);
		    }
		}
	    }
	} else if (e.getHitEntity() != null) {
	    boolean isGrap = GrapplingHookManager.isHook(e.getEntity());
	    if (isGrap) {
		e.setCancelled(true);
		GrapplingHookManager manager = GrapplingHookManager.getFromArrow((Arrow) e.getEntity());
		manager.retractEntity(e.getHitEntity());
	    }
	}
    }

    @EventHandler
    public void onGlideToggle(EntityToggleGlideEvent event) {
	org.bukkit.entity.Entity ent = event.getEntity();
	if (ent instanceof Player) {
	    Player p = (Player) ent;
	    if (glideCanceled.contains(p) && !event.isGliding())
		event.setCancelled(true);
	}
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e) {
	Chunk chunk = e.getChunk();
	NPCHandler.dismountBrains(chunk);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
	PlayerData PData;
	final Player p = e.getPlayer();
	PacketReader reader = new PacketReader();
	reader.inject(p);
	final Chunk chunk = p.getLocation().getChunk();
	final int x = chunk.getX();
	final int z = chunk.getZ();
	Collection<Chunk> chunks = new ArrayList<>();
	for (int i = -3; i < 4; i++) {
	    for (int j = -3; j < 4; j++)
		chunks.add(chunk.getWorld().getChunkAt(x + i, z + j));
	}
	for (Chunk c : chunks)
	    NPCHandler.mountBrains(c);
	NPCHandler.createNPCs(p);
	NPCHandler.hideNPCBrains(p);
	Thread thread = new Thread(new Runnable() {
	    public void run() {
		NPCHandler.showNPCs(p);
		NPCHandler.hideNPCs(p);
		List<Thread> tasks = Listener.NPCTasks.get(p);
		if (tasks != null) {
		    if (!tasks.isEmpty()) {
			if (tasks.contains(Thread.currentThread()))
			    tasks.remove(Thread.currentThread());
			if (!tasks.isEmpty()) {
			    Thread nextThread = tasks.get(0);
			    nextThread.start();
			} else {
			    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			}
		    } else {
			Listener.chargingNPCs.put(p, Boolean.valueOf(false));
		    }
		} else {
		    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
		}
		Listener.NPCTasks.put(p, tasks);
	    }
	});
	if (chargingNPCs.get(p) == null)
	    chargingNPCs.put(p, Boolean.valueOf(false));
	if (((Boolean) chargingNPCs.get(p)).booleanValue()) {
	    List<Thread> tasks = NPCTasks.get(p);
	    if (tasks == null)
		tasks = new ArrayList<>();
	    tasks.add(thread);
	    NPCTasks.put(p, tasks);
	} else {
	    chargingNPCs.put(p, Boolean.valueOf(true));
	    thread.start();
	}
	p.setScoreboard(EntityManager.board);
	p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.0D);
	PData = PlayerData.getData(p);
	if (PData.getUuid() == null)
	    PData.setUuid(p.getUniqueId().toString());
	PData.register();
	CustomBlock.initPlayer(p);
	if (PData.getCurrentRace() == Race.NONE) {
	    RaceInventoryManager manager = new RaceInventoryManager();
	    p.openInventory(manager.getInventory());
	    PlayerData.applyAttributes(p, true);
	} else {
	    PlayerData.applyAttributes(p, false);
	    PlayerData.applyColors(p);
	}
	(new BukkitRunnable() {
	    public void run() {
		Collection<Chunk> chunks = new ArrayList<>();
		for (int i = -1; i < 2; i++) {
		    for (int j = -1; j < 2; j++)
			chunks.add(chunk.getWorld().getChunkAt(x + i, z + j));
		}
		for (Chunk chunk : chunks) {
		    List<NPCMemory> memories = NPCHandler.getLoadedNPCsInChunk(chunk);
		    if (memories != null && !memories.isEmpty())
			for (NPCMemory memory : memories) {
			    CustomNPC brain = (CustomNPC) NPCHandler.NPCMemoryToBrain.get(memory);
			    NPCHandler.hideNPCBrain(p, brain);
			}
		}
	    }
	}).runTaskLater((Plugin) Main.getInstance(), 20L);
    }

    @EventHandler
    public void onEnchantPrepare(PrepareItemEnchantEvent e) {
	ItemStack item = e.getItem();
	if (item != null && item.getType() != Material.AIR) {
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("ItemType")) {
		e.setCancelled(true);
	    }
	}
    }

    @EventHandler
    public void onSmithPrepare(PrepareAnvilEvent e) {
	ItemStack item = e.getResult();
	if (item != null && item.getType() != Material.AIR) {
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("ItemType")) {
		e.setResult(new ItemStack(Material.AIR));
	    }
	}
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
	HumanEntity ent = e.getWhoClicked();
	if (InventoryManager.isCustomInventory(e.getInventory()) && ent instanceof Player) {
	    InventoryManager m = InventoryManager.getManager(e.getInventory());
	    m.handleClick(e);
	} else if (ent instanceof Player) {
	    int slot = e.getSlot();
	    ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
	    ItemStack nextItem = (e.getCursor() == null) ? new ItemStack(Material.AIR) : e.getCursor();
	    Player p = (Player) ent;
	    PlayerData PData = PlayerData.getData(p);
	    boolean shift = e.isShiftClick();
	    if (e.getInventory().getType().equals(InventoryType.PLAYER)
		    || e.getInventory().getType().equals(InventoryType.CRAFTING)) {
		if (PData.getCurrentRace() == Race.PIAF) {
		    if (slot == 38) {
			e.setResult(Result.DENY);
		    }
		} else {
		    if (currItem.getType() == Material.AIR || chests.contains(currItem.getType())) {
			if (Material.ELYTRA == nextItem.getType() && slot == 38)
			    e.setCancelled(true);
		    } else if (shift && currItem.getType() == Material.ELYTRA && nextItem.getType() == Material.AIR) {
			e.setResult(Result.DENY);
		    }
		}
	    }
	}
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent e) {
	(new BukkitRunnable() {
	    public void run() {
		if (!e.getPlayer().isDead()) {
		    cancel();
		    Player p = e.getPlayer();
		    PlayerData PData = PlayerData.getData(p);
		    GrapplingHookManager manager = GrapplingHookManager.getFromPlayer(p);
		    if (manager != null)
			manager.cleanArrow();
		    PlayerData.applyAttributes(p, true);
		    PData.respawn();
		    if (PData.getCurrentRace() == Race.PIAF) {
			ItemStack chestplate = p.getInventory().getChestplate();
			ItemStack elytra = new ItemStack(Material.ELYTRA);
			ItemMeta meta = elytra.getItemMeta();
			meta.setUnbreakable(true);
			elytra.setItemMeta(meta);
			if (chestplate != null) {
			    p.getInventory().addItem(chestplate);
			}
			p.getInventory().setItem(EquipmentSlot.CHEST, elytra);
		    }
		}
		if (!e.getPlayer().isOnline())
		    cancel();
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 2L);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	GrapplingHookManager manager = GrapplingHookManager.getFromPlayer(p);
	if (manager != null)
	    manager.cleanArrow();
	PData.setHealth(p.getHealth());
	PData.unregister();
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	if (p.getOpenInventory().getType() != InventoryType.CRAFTING && p.getGameMode() != GameMode.CREATIVE) {
	    e.setCancelled(true);
	    return;
	}
	ItemStack item = e.getItem();
	if (item.getType() == Material.MILK_BUCKET) {
	    PData.resetExtraEffects();
	    e.setCancelled(true);
	    if (p.getGameMode() != GameMode.CREATIVE)
		e.setItem(new ItemStack(Material.BUCKET));
	}
    }

    @EventHandler
    public void onDispenseArmorEvent(BlockDispenseArmorEvent e) {
	LivingEntity ent = e.getTargetEntity();
	if (ent instanceof Player) {
	    Player p = (Player) ent;
	    PlayerData PData = PlayerData.getData(p);
	    ItemStack item = e.getItem();
	    if (PData.getCurrentRace() == Race.PIAF && chests.contains(item.getType())) {
		e.setCancelled(true);
	    } else if (PData.getCurrentRace() != Race.PIAF && item.getType() == Material.ELYTRA) {
		e.setCancelled(true);
	    }
	}
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
	ItemStack nextItem = (e.getOldCursor() == null) ? new ItemStack(Material.AIR) : e.getOldCursor();
	ItemStack currItem = (e.getCursor() == null) ? new ItemStack(Material.AIR) : e.getCursor();
	Set<Integer> slots = e.getInventorySlots();
	if (e.getWhoClicked() instanceof Player && InventoryManager.isCustomInventory(e.getInventory())) {
	    InventoryManager m = InventoryManager.getManager(e.getInventory());
	    m.handleDrag(e);
	} else if (e.getWhoClicked() instanceof Player) {
	    Player p = (Player) e.getWhoClicked();
	    PlayerData PData = PlayerData.getData(p);
	    if (PData.getCurrentRace() == Race.PIAF) {
		if ((e.getInventory().getType().equals(InventoryType.PLAYER)
			|| e.getInventory().getType().equals(InventoryType.CRAFTING))
			&& (currItem.getType() == Material.AIR || currItem.getType() == Material.ELYTRA)
			&& chests.contains(nextItem.getType()) && slots.contains(Integer.valueOf(38)))
		    e.setCancelled(true);
	    } else if (PData.getCurrentRace() != Race.PIAF
		    && (e.getInventory().getType().equals(InventoryType.PLAYER)
			    || e.getInventory().getType().equals(InventoryType.CRAFTING))
		    && (currItem.getType() == Material.AIR || chests.contains(currItem.getType()))
		    && Material.ELYTRA == nextItem.getType() && slots.contains(Integer.valueOf(38))) {
		e.setCancelled(true);
	    }
	}
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent e) {
	if (e.getEntity() instanceof Player) {
	    Player p = (Player) e.getEntity();
	    PlayerData PData = PlayerData.getData(p);
	    PData.setHealth(p.getHealth() + e.getAmount());
	}
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
	HumanEntity ent = e.getPlayer();
	if (ent instanceof Player && InventoryManager.isCustomInventory(e.getInventory())) {
	    InventoryManager m = InventoryManager.getManager(e.getInventory());
	    m.handleClose(e);
	}
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
	if (e.getEntity() instanceof Player) {
	    Player p = (Player) e.getEntity();
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    if (rings != null)
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
		    double modifier = 1d;
		    modifier -= rings.contains(Ring.FEATHER_RING) ? 0.5 : 0;
		    modifier -= PData.getCurrentRace() == Race.PIAF && PData.getSkyLight() == 15 ? 0.5 : 0;
		    e.setDamage(e.getDamage() * modifier);
		} else if (e.getCause() == EntityDamageEvent.DamageCause.VOID
			&& p.getWorld().getName().equals("world_the_end")) {
		    e.setCancelled(true);
		} else if (e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
		    if (rings.contains(Ring.TENACITY_RING_2)) {
			e.setDamage(e.getDamage() * 0.7D);
		    } else if (rings.contains(Ring.TENACITY_RING_1)) {
			e.setDamage(e.getDamage() * 0.85D);
		    }
		} else if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
			|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
		    if (rings.contains(Ring.BURST_RING_2)) {
			e.setDamage(e.getDamage() * 0.6D);
		    } else if (rings.contains(Ring.BURST_RING_1)) {
			e.setDamage(e.getDamage() * 0.8D);
		    }
		}
	    PData.setHealth(p.getHealth() - e.getFinalDamage());
	}
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
	org.bukkit.entity.Entity entity = e.getEntity();
	org.bukkit.entity.Entity damager = e.getDamager();
	switch (e.getCause()) {
	case ENTITY_ATTACK:
	case ENTITY_SWEEP_ATTACK:
	    EntityPhysicalDamageByEntityEvent physicalEvent = new EntityPhysicalDamageByEntityEvent(e, entity, damager);
	    Bukkit.getPluginManager().callEvent((Event) physicalEvent);
	    break;
	case PROJECTILE:
	    Projectile projectile = (Projectile) damager;
	    ProjectileSource ent = projectile.getShooter();
	    if (ent instanceof Player) {
		Player p = (Player) ent;
		PlayerData.checkApplyExtraEffect(entity, p);
	    }
	    if (damager instanceof Arrow) {
		Arrow arrow = (Arrow) damager;
		if (arrow.getPersistentDataContainer().has(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			PersistentDataType.STRING) && ent instanceof org.bukkit.entity.Entity) {
		    EntityDarkDamageByEntityEvent eventDark;
		    EntityElectricDamageByEntityEvent eventElectric;
		    EntityFireDamageByEntityEvent eventFire;
		    EntityIceDamageByEntityEvent eventIce;
		    EntityLightDamageByEntityEvent eventLight;
		    org.bukkit.entity.Entity shooter = (org.bukkit.entity.Entity) ent;
		    String damageTypeString = (String) arrow.getPersistentDataContainer().get(
			    new NamespacedKey((Plugin) Main.getInstance(), "DamageType"), PersistentDataType.STRING);
		    DamageType damageType = DamageType.valueOf(damageTypeString);
		    switch (damageType) {
		    case DARK:
			eventDark = new EntityDarkDamageByEntityEvent(e, entity, shooter);
			Bukkit.getPluginManager().callEvent((Event) eventDark);
			break;
		    case ELECTRIC:
			eventElectric = new EntityElectricDamageByEntityEvent(e, entity, shooter);
			Bukkit.getPluginManager().callEvent((Event) eventElectric);
			break;
		    case FIRE:
			eventFire = new EntityFireDamageByEntityEvent(e, entity, shooter);
			Bukkit.getPluginManager().callEvent((Event) eventFire);
			break;
		    case ICE:
			eventIce = new EntityIceDamageByEntityEvent(e, entity, shooter);
			Bukkit.getPluginManager().callEvent((Event) eventIce);
			break;
		    case LIGHT:
			eventLight = new EntityLightDamageByEntityEvent(e, entity, shooter);
			Bukkit.getPluginManager().callEvent((Event) eventLight);
			break;
		    case BOMB:
			Location loc = entity.getLocation();
			loc.getWorld().createExplosion(loc, 2, false, false);
		    }
		}
	    }
	    break;
	default:
	    if (entity instanceof Player) {
		Player p = (Player) e.getEntity();
		PlayerData PData = PlayerData.getData(p);
		PData.setHealth(p.getHealth() - e.getFinalDamage());
	    }
	    break;
	}

    }

    @EventHandler
    public void onEntityPhysicalDamageByEntity(EntityPhysicalDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getPhysicalArmorModifier(rings);
	    double mod2 = RingCalculator.getPhysicalDamageModifier(rings2);
	    e.setDamage(e.getDamage() * mod1 * mod2);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	    PlayerData.checkApplyExtraEffect(entity, p2);
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    double mod1 = RingCalculator.getPhysicalArmorModifier(rings);
	    e.setDamage(e.getDamage() * mod1);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    double mod1 = RingCalculator.getPhysicalDamageModifier(rings);
	    e.setDamage(e.getDamage() * mod1);
	    PlayerData.checkApplyExtraEffect(entity, p);
	}
    }

    @EventHandler
    public void onEntityFireDamageByEntity(EntityFireDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    p.setFireTicks(60);
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getFireArmorModifier(rings);
	    double mod2 = RingCalculator.getFireDamageModifier(rings2);
	    double mod3 = ModifierCalculator.getResitanceModifier(DamageType.FIRE, PData.getCurrentRace());
	    double mod4 = ModifierCalculator.getStrengthModifier(DamageType.FIRE, PData2.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * 1.45D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    p.setFireTicks(60);
	    double mod1 = RingCalculator.getFireArmorModifier(rings);
	    double mod2 = ModifierCalculator.getResitanceModifier(DamageType.FIRE, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.45D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    if (entity instanceof LivingEntity) {
		LivingEntity mob = (LivingEntity) entity;
		mob.setFireTicks(60);
	    }
	    double mod1 = RingCalculator.getFireDamageModifier(rings);
	    double mod2 = ModifierCalculator.getStrengthModifier(DamageType.FIRE, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.45D);
	}
    }

    @EventHandler
    public void onEntityDarkDamageByEntity(EntityDarkDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 30, 0, false, false, false);
	    p.addPotionEffect(effect);
	    PData.addEffect(effect);
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getDarkArmorModifier(rings);
	    double mod2 = RingCalculator.getDarkDamageModifier(rings2);
	    double mod3 = ModifierCalculator.getResitanceModifier(DamageType.DARK, PData.getCurrentRace());
	    double mod4 = ModifierCalculator.getStrengthModifier(DamageType.DARK, PData2.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * 1.4D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 30, 0, false, false, false);
	    PData.addEffect(effect);
	    p.addPotionEffect(effect);
	    double mod1 = RingCalculator.getDarkArmorModifier(rings);
	    double mod2 = ModifierCalculator.getResitanceModifier(DamageType.DARK, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.4D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    if (entity instanceof LivingEntity) {
		final LivingEntity mob = (LivingEntity) entity;
		mob.setAI(false);
		(new BukkitRunnable() {
		    public void run() {
			mob.setAI(true);
		    }
		}).runTaskLaterAsynchronously((Plugin) Main.getInstance(), 8L);
	    }
	    double mod1 = RingCalculator.getDarkDamageModifier(rings);
	    double mod2 = ModifierCalculator.getStrengthModifier(DamageType.DARK, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.4D);
	}
    }

    @EventHandler
    public void onEntityElectricDamageByEntity(EntityElectricDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	final org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    final Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    disableLightning.add(p.getLocation());
	    p.getWorld().strikeLightning(p.getLocation());
	    (new BukkitRunnable() {
		public void run() {
		    Listener.disableLightning.remove(p.getLocation());
		}
	    }).runTaskLaterAsynchronously((Plugin) Main.getInstance(), 40L);
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getElectricArmorModifier(rings);
	    double mod2 = RingCalculator.getElectricDamageModifier(rings2);
	    double mod3 = ModifierCalculator.getResitanceModifier(DamageType.ELECTRIC, PData.getCurrentRace());
	    double mod4 = ModifierCalculator.getStrengthModifier(DamageType.ELECTRIC, PData2.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * 1.45D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (entity instanceof Player) {
	    final Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    disableLightning.add(p.getLocation());
	    p.getWorld().strikeLightning(p.getLocation());
	    (new BukkitRunnable() {
		public void run() {
		    Listener.disableLightning.remove(p.getLocation());
		}
	    }).runTaskLaterAsynchronously((Plugin) Main.getInstance(), 40L);
	    double mod1 = RingCalculator.getElectricArmorModifier(rings);
	    double mod2 = ModifierCalculator.getResitanceModifier(DamageType.ELECTRIC, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.45D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    final Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    disableLightning.add(entity.getLocation());
	    entity.getWorld().strikeLightning(entity.getLocation());
	    (new BukkitRunnable() {
		public void run() {
		    Listener.disableLightning.remove(entity.getLocation());
		}
	    }).runTaskLaterAsynchronously((Plugin) Main.getInstance(), 40L);
	    double mod1 = RingCalculator.getElectricDamageModifier(rings);
	    double mod2 = ModifierCalculator.getStrengthModifier(DamageType.ELECTRIC, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.45D);
	}
    }

    @EventHandler
    public void onEntityIceDamageByEntity(EntityIceDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 30, 255, false, false, false);
	    PData.addEffect(effect);
	    p.addPotionEffect(effect);
	    p.setFreezeTicks(170);
	    List<Ring> rings = PData.getRings();
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getIceArmorModifier(rings);
	    double mod2 = RingCalculator.getIceDamageModifier(rings2);
	    double mod3 = ModifierCalculator.getResitanceModifier(DamageType.ICE, PData.getCurrentRace());
	    double mod4 = ModifierCalculator.getStrengthModifier(DamageType.ICE, PData2.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * 1.25D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 30, 255, false, false, false);
	    p.addPotionEffect(effect);
	    PData.addEffect(effect);
	    p.setFreezeTicks(170);
	    List<Ring> rings = PData.getRings();
	    double mod1 = RingCalculator.getIceArmorModifier(rings);
	    double mod2 = ModifierCalculator.getResitanceModifier(DamageType.ICE, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.25D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    if (entity instanceof LivingEntity) {
		LivingEntity mob = (LivingEntity) entity;
		mob.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, false, false, false));
	    }
	    double mod1 = RingCalculator.getIceDamageModifier(rings);
	    double mod2 = ModifierCalculator.getStrengthModifier(DamageType.ICE, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.25D);
	}
    }

    @EventHandler
    public void onEntityLightDamageByEntity(EntityLightDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    PotionEffect effect = new PotionEffect(PotionEffectType.GLOWING, 30, 0, false, false, false);
	    PData.addEffect(effect);
	    p.addPotionEffect(effect);
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getLightArmorModifier(rings);
	    double mod2 = RingCalculator.getLightDamageModifier(rings2);
	    double mod3 = ModifierCalculator.getResitanceModifier(DamageType.LIGHT, PData.getCurrentRace());
	    double mod4 = ModifierCalculator.getStrengthModifier(DamageType.LIGHT, PData2.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * 1.75D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    PotionEffect effect = new PotionEffect(PotionEffectType.GLOWING, 30, 0, false, false, false);
	    PData.addEffect(effect);
	    p.addPotionEffect(effect);
	    double mod1 = RingCalculator.getLightArmorModifier(rings);
	    double mod2 = ModifierCalculator.getResitanceModifier(DamageType.LIGHT, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.75D);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    if (entity instanceof LivingEntity) {
		LivingEntity mob = (LivingEntity) entity;
		mob.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false, false));
	    }
	    double mod1 = RingCalculator.getLightDamageModifier(rings);
	    double mod2 = ModifierCalculator.getStrengthModifier(DamageType.LIGHT, PData.getCurrentRace());
	    e.setDamage(e.getDamage() * mod1 * mod2 * 1.75D);
	}
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	Location loc = e.getTo();
	if (loc.getWorld().getName().equals("world_the_end") && loc.getY() <= -150.0D) {
	    Location newLoc = new Location(Bukkit.getWorld("world"), loc.getX(), 400.0D, loc.getZ());
	    p.teleport(newLoc);
	    p.setVelocity(p.getVelocity());
	} else if (loc.getWorld().getName().equals("world") && loc.getY() >= 450.0D) {
	    Location newLoc = new Location(Bukkit.getWorld("world_the_end"), loc.getX(), 0.0D, loc.getZ());
	    p.teleport(newLoc);
	    p.setVelocity(p.getVelocity());
	}
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e) {
	org.bukkit.entity.Entity proj = e.getProjectile();
	LivingEntity livingEntity = e.getEntity();
	if (proj instanceof Arrow && livingEntity instanceof Player) {
	    Arrow arrow = (Arrow) proj;
	    ItemStack bow = e.getBow();
	    NBTItem nbt = new NBTItem(bow);
	    if (nbt.getKeys().contains("ItemType")) {
		Item type = Item.valueOf(nbt.getString("ItemType"));
		switch (type) {
		case BOMB_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.BOMB.toString());
		    break;
		case DARK_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.DARK.toString());
		    break;
		case ELECTRIC_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.ELECTRIC.toString());
		    break;
		case FIRE_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.FIRE.toString());
		    break;
		case ICE_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.ICE.toString());
		    break;
		case LIGHT_BOW:
		    arrow.getPersistentDataContainer().set(new NamespacedKey((Plugin) Main.getInstance(), "DamageType"),
			    PersistentDataType.STRING, DamageType.LIGHT.toString());
		    break;
		case SHADOW_CRYSTAL:
		case HEART_PIECE:
		case HEART_CONTAINER:
		case GRAPPLING_HOOK:
		default:
		    break;
		}
		arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
	    }
	}
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
	if (e.getBlock() != null) {
	    Location loc = e.getBlock().getLocation();
	    if (e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING)
		for (Location l : disableLightning) {
		    if (l.distance(loc) <= 3.0D)
			e.setCancelled(true);
		}
	}
    }

    private Collection<Location> lootChest = new ArrayList<>();

    private Collection<UUID> lootMinecart = new ArrayList<>();

    @EventHandler
    public void onLootGenerate(LootGenerateEvent e) {
	LootTable table = e.getLootTable();
	RandomCollection<ItemStack> rc = ItemTable.get(table.getKey().toString());
	if (rc != null) {
	    ItemStack item = rc.next();
	    List<ItemStack> items = e.getLoot();
	    List<ItemStack> newItems = new ArrayList<>();
	    for (ItemStack i : items)
		newItems.add(i);
	    int rand = (int) (Math.random() * 100.0D);
	    if (rand <= 6)
		newItems.add(ItemBuilder.build(Item.HEART_PIECE));
	    Pattern pattern = Pattern.compile("village", 10);
	    Matcher matcher = pattern.matcher(table.getKey().toString());
	    if (matcher.find()) {
		rand = (int) (Math.random() * 10000.0D);
		if (rand <= 200) {
		    newItems.add(RupeeBuilder.build(Rupees.YELLOW));
		} else if (rand <= 300) {
		    newItems.add(RupeeBuilder.build(Rupees.RED));
		} else if (rand <= 350) {
		    newItems.add(RupeeBuilder.build(Rupees.PURPLE));
		}
	    }
	    if (item.getType() != Material.AIR) {
		Ring ring = RingBuilder.getRingFromItem(item);
		ItemStack itemRing = RingBuilder.build(ring);
		newItems.add(itemRing);
	    }
	    e.setLoot(newItems);
	}
	InventoryHolder holder = e.getInventoryHolder();
	if (holder instanceof Chest) {
	    Chest chest = (Chest) holder;
	    lootChest.add(chest.getLocation());
	} else if (holder instanceof StorageMinecart) {
	    StorageMinecart minecart = (StorageMinecart) holder;
	    lootMinecart.add(minecart.getUniqueId());
	}
    }

    @EventHandler
    public void onPlayerOpenInventory(InventoryOpenEvent e) {
	InventoryHolder holder = e.getInventory().getHolder();
	if (holder instanceof Chest) {
	    Chest chest = (Chest) holder;
	    if (lootChest.contains(chest.getLocation())) {
		Player p = (Player) e.getPlayer();
		p.playSound(chest.getLocation(), "zelda.open_chest", SoundCategory.MUSIC, 1000.0F, 1.0F);
		lootChest.remove(chest.getLocation());
	    }
	} else if (holder instanceof StorageMinecart) {
	    StorageMinecart minecart = (StorageMinecart) holder;
	    if (lootMinecart.contains(minecart.getUniqueId())) {
		Player p = (Player) e.getPlayer();
		p.playSound(minecart.getLocation(), "zelda.open_chest", SoundCategory.MUSIC, 1000.0F, 1.0F);
		lootMinecart.remove(minecart.getUniqueId());
	    }
	}
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
	Block block = e.getBlock();
	NBTBlock nbt = new NBTBlock(block);
	if (nbt.getData().getKeys().contains("hasStable")) {
	    e.setCancelled(true);
	    return;
	}
	Material mat = block.getType();
	ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
	if (e.getPlayer().getGameMode() != GameMode.SURVIVAL)
	    return;
	if (!hand.containsEnchantment(Enchantment.SILK_TOUCH) && (mat == Material.FERN || mat == Material.LARGE_FERN
		|| mat == Material.GRASS || mat == Material.TALL_GRASS || mat == Material.DEAD_BUSH)) {
	    if (!block.getMetadata("NoRupee").isEmpty()
		    && ((MetadataValue) block.getMetadata("NoRupee").get(0)).asBoolean())
		return;
	    int rand = (int) (Math.random() * 10000.0D);
	    if (rand <= 200) {
		block.getWorld().dropItemNaturally(block.getLocation(), RupeeBuilder.build(Rupees.GREEN));
	    } else if (rand <= 50) {
		block.getWorld().dropItemNaturally(block.getLocation(), RupeeBuilder.build(Rupees.BLUE));
	    } else if (rand <= 500) {
		block.getWorld().dropItemNaturally(block.getLocation(), DropBuilder.build(Drop.HEART));
	    }
	} else if (!hand.containsEnchantment(Enchantment.SILK_TOUCH)) {
	    Player p = e.getPlayer();
	    PlayerData PData = PlayerData.getData(p);
	    if (PData.getCurrentRace() == Race.GORON && mat.toString().contains("ORE")) {
		e.setDropItems(false);

		ItemStack newHand = new ItemStack(hand.getType());
		newHand.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,
			hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1);
		Collection<ItemStack> drops = block.getDrops(newHand);
		int rand = (int) (Math.random() * 100000.0D);
		if (rand <= 25) {
		    drops.add(RupeeBuilder.build(Rupees.GOLD));
		} else if (rand <= 75) {
		    drops.add(RupeeBuilder.build(Rupees.SILVER));
		} else if (rand <= 200) {
		    drops.add(RupeeBuilder.build(Rupees.PURPLE));
		} else if (rand <= 450) {
		    drops.add(RupeeBuilder.build(Rupees.RED));
		} else if (rand <= 950) {
		    drops.add(RupeeBuilder.build(Rupees.YELLOW));
		} else if (rand <= 1825) {
		    drops.add(RupeeBuilder.build(Rupees.BLUE));
		} else if (rand <= 3075) {
		    drops.add(RupeeBuilder.build(Rupees.GREEN));
		}
		for (ItemStack drop : drops) {
		    block.getWorld().dropItemNaturally(block.getLocation(), drop);
		}
	    }
	}
    }

    @EventHandler
    public void onPistonExtends(BlockPistonExtendEvent e) {
	for (Block block : e.getBlocks()) {
	    NBTBlock nbt = new NBTBlock(block);
	    if (nbt.getData().getKeys().contains("hasStable")) {
		e.setCancelled(true);
		return;
	    }
	}
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent e) {
	for (Block block : e.getBlocks()) {
	    NBTBlock nbt = new NBTBlock(block);
	    if (nbt.getData().getKeys().contains("hasStable")) {
		e.setCancelled(true);
		return;
	    }
	}
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
	List<Block> blocks = e.blockList();
	byte b;
	int i;
	Block[] arrayOfBlock;
	for (i = (arrayOfBlock = blocks.<Block>toArray(new Block[blocks.size()])).length, b = 0; b < i;) {
	    Block block = arrayOfBlock[b];
	    NBTBlock nbt = new NBTBlock(block);
	    if (nbt.getData().getKeys().contains("hasStable"))
		blocks.remove(block);
	    b++;
	}
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
	List<Block> blocks = e.blockList();
	int b;
	int i;
	Block[] arrayOfBlock;
	for (i = (arrayOfBlock = blocks.<Block>toArray(new Block[blocks.size()])).length, b = 0; b < i;) {
	    Block block = arrayOfBlock[b];
	    NBTBlock nbt = new NBTBlock(block);
	    if (nbt.getData().getKeys().contains("hasStable"))
		blocks.remove(block);
	    b++;
	}
    }

    @EventHandler
    public void onFertilizeEvent(BlockFertilizeEvent e) {
	List<BlockState> blocks = e.getBlocks();
	for (BlockState state : blocks) {
	    Material mat = state.getType();
	    if (mat == Material.FERN || mat == Material.LARGE_FERN || mat == Material.GRASS
		    || mat == Material.TALL_GRASS || mat == Material.DEAD_BUSH)
		state.getBlock().setMetadata("NoRupee",
			(MetadataValue) new FixedMetadataValue((Plugin) Main.getInstance(), Boolean.valueOf(true)));
	}
    }

    @EventHandler
    public void onRightClickNPCEvent(RightClickNPCEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	NPCMemory memory = e.getMemory();
	if (memory.hasStable() && memory.isMainNPC()) {
	    StableMemory stable = memory.getStable();
	    if (PData.hasDiscoveredStable(stable)) {
		/*
		 * Inventory inv = InventoryManager.createInventory(null, 54, "Stable",
		 * CustomInventoryType.STABLE); if (stable.isOwner(p)) {
		 * StableManager.populateStableOwnerMenu(inv, p, stable); } else {
		 * StableManager.populateStableTeleport(inv, p, stable, 0); }
		 */
	    } else {
		p.sendMessage("Discovered !");
		PData.discoverStable(stable);
	    }
	}
    }

    @EventHandler
    public void onLeftClickNPCEvent(final LeftClickNPCEvent e) {
	Player player = e.getPlayer();
	if (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
	    for (Player p : Bukkit.getOnlinePlayers()) {
		final Chunk chunk = NPCHandler.getChunkFromBrain(e.getNpcBrain());
		Thread thread = new Thread(new Runnable() {
		    public void run() {
			NPCHandler.removeNPC(e.getNpcBrain(), p, chunk);
			List<Thread> tasks = Listener.NPCTasks.get(p);
			if (tasks != null) {
			    if (!tasks.isEmpty()) {
				if (tasks.contains(Thread.currentThread()))
				    tasks.remove(Thread.currentThread());
				if (!tasks.isEmpty()) {
				    Thread nextThread = tasks.get(0);
				    nextThread.start();
				} else {
				    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
				}
			    } else {
				Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			    }
			} else {
			    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			}
			Listener.NPCTasks.put(p, tasks);
		    }
		});
		if (chargingNPCs.get(p) == null)
		    chargingNPCs.put(p, Boolean.valueOf(false));
		if (((Boolean) chargingNPCs.get(p)).booleanValue()) {
		    List<Thread> tasks = NPCTasks.get(p);
		    if (tasks == null)
			tasks = new ArrayList<>();
		    tasks.add(thread);
		    NPCTasks.put(p, tasks);
		    continue;
		}
		chargingNPCs.put(p, Boolean.valueOf(true));
		thread.start();
	    }
	} else {
	    player.sendMessage("Left Click");
	}
    }

    @EventHandler
    public void onPlayerPotion(EntityPotionEffectEvent e) {
	if (e.getCause() != EntityPotionEffectEvent.Cause.PLUGIN
		&& e.getCause() != EntityPotionEffectEvent.Cause.MILK) {
	    org.bukkit.entity.Entity ent = e.getEntity();
	    if (!(ent instanceof Player))
		return;
	    Player player = (Player) ent;
	    PlayerData PData = PlayerData.getData(player);
	    if ((e.getAction() == EntityPotionEffectEvent.Action.REMOVED
		    || e.getAction() == EntityPotionEffectEvent.Action.CLEARED)) {
		PData.deleteEffect(e.getOldEffect());
	    } else {
		PData.addEffect(e.getNewEffect());
	    }
	    e.setCancelled(true);
	}

    }

    @EventHandler
    public void onPlayerCraft(CraftItemEvent e) {
	ItemStack result = e.getRecipe().getResult();
	Player p = (Player) e.getWhoClicked();
	if (result.getType() != Material.AIR && result != null) {
	    NBTItem nbt = new NBTItem(result);
	    if (nbt.getKeys().contains("AntiStack") && e.getResult() == Result.ALLOW) {

		if (e.isShiftClick()) {
		    e.setCancelled(true);
		    int max = 64;
		    CraftingInventory inv = e.getInventory();
		    for (ItemStack i : inv.getMatrix()) {
			if (i != null && i.getType() != Material.AIR && i.getAmount() > 0)
			    max = Math.min(i.getAmount(), max);
		    }
		    for (ItemStack i : inv.getMatrix()) {
			if (i != null && i.getType() != Material.AIR && i.getAmount() > 0)
			    i.setAmount(i.getAmount() - max);
		    }
		    inv.setResult(new ItemStack(Material.AIR));
		    if (nbt.getKeys().contains("BlockType")) {
			String s = nbt.getString("BlockType");
			if (s.equals("Campfire_Unlit")) {
			    for (int i = 0; i < max; i++) {
				ItemStack t = BlockBuilder.build(BlockEnum.CAMP_UNLIT);
				if (p.getInventory().firstEmpty() != -1)
				    p.getInventory().addItem(t);
				else
				    p.getLocation().getWorld().dropItemNaturally(p.getLocation(), t);
			    }
			}
		    }
		} else if (e.getResult() == Result.ALLOW) {
		    e.setCancelled(true);
		    int max = 64;
		    CraftingInventory inv = e.getInventory();
		    for (ItemStack i : inv.getMatrix()) {
			if (i != null && i.getType() != Material.AIR && i.getAmount() > 0)
			    max = Math.min(i.getAmount(), max);
		    }
		    for (ItemStack i : inv.getMatrix()) {
			if (i != null && i.getType() != Material.AIR && i.getAmount() > 0)
			    i.setAmount(i.getAmount() - 1);
		    }
		    if (max == 1)
			inv.setResult(new ItemStack(Material.AIR));
		    if (nbt.getKeys().contains("BlockType")) {
			String s = nbt.getString("BlockType");
			if (s.equals("Campfire_Unlit")) {
			    ItemStack t = BlockBuilder.build(BlockEnum.CAMP_UNLIT);
			    if (p.getInventory().firstEmpty() != -1)
				p.getInventory().addItem(t);
			    else
				p.getLocation().getWorld().dropItemNaturally(p.getLocation(), t);
			}
		    }
		}
	    }
	}
    }

    @EventHandler
    public void onMobTarget(EntityTargetLivingEntityEvent e) {
	if (!(e.getTarget() instanceof Player))
	    return;
	Player p = (Player) e.getTarget();
	PlayerData PData = PlayerData.getData(p);
	if (!PData.isVisible())
	    e.setCancelled(true);
    }
}
