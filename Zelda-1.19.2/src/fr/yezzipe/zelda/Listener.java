package fr.yezzipe.zelda;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.blocks.BlockBuilder;
import fr.yezzipe.zelda.blocks.enums.BlockEnum;
import fr.yezzipe.zelda.entity.CustomBlock;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.PacketReader;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.events.EntityDarkDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityElectricDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityFireDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityIceDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityLightDamageByEntityEvent;
import fr.yezzipe.zelda.events.EntityPhysicalDamageByEntityEvent;
import fr.yezzipe.zelda.events.ModifierCalculator;
import fr.yezzipe.zelda.events.enums.DamageType;
import fr.yezzipe.zelda.inventory.CookingInventoryManager;
import fr.yezzipe.zelda.inventory.InventoryManager;
import fr.yezzipe.zelda.inventory.RaceInventoryManager;
import fr.yezzipe.zelda.inventory.TraderInventoryManager;
import fr.yezzipe.zelda.items.GrapplingHookManager;
import fr.yezzipe.zelda.items.HeartContainerManager;
import fr.yezzipe.zelda.items.ItemTable;
import fr.yezzipe.zelda.items.RingCalculator;
import fr.yezzipe.zelda.items.ShadowCrystalManager;
import fr.yezzipe.zelda.items.enums.Drop;
import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.FoodBonus;
import fr.yezzipe.zelda.items.enums.Ingredient;
import fr.yezzipe.zelda.items.enums.Item;
import fr.yezzipe.zelda.items.enums.Ring;
import fr.yezzipe.zelda.items.enums.Rupees;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Wither;
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
import org.bukkit.event.block.LeavesDecayEvent;
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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
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
    public void onPlayerTp(PlayerTeleportEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	PData.setCurrentDimension(e.getTo().getWorld().getEnvironment());
	PData.tick(true, false, false);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
	Player p = e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	PData.setCurrentDimension(e.getTo().getWorld().getEnvironment());
	PData.tick(true, false, false);
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
	final Player p = e.getPlayer();
	final PlayerData PData = PlayerData.getData(p);
	boolean dimC = PData.getCurrentDimension() != e.getTo().getWorld().getEnvironment();
	if (dimC)
	    PData.setCurrentDimension(e.getTo().getWorld().getEnvironment());
	PData.tick(dimC, dimC, false);
	if (PData.isAttachedToWall()) {
	    p.setGravity(false);
	    p.setVelocity(new Vector(0, 0, 0));
	    e.setTo(new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ(),
		    e.getTo().getYaw(), e.getTo().getPitch()));
	    return;
	}
	if (!p.hasGravity())
	    p.setGravity(true);
	/*
	 * if (e.getTo().getChunk() != e.getFrom().getChunk()) { int x =
	 * e.getTo().getChunk().getX(); int z = e.getTo().getChunk().getZ();
	 * Collection<Chunk> chunks2 = new ArrayList<>(); for (int i = -3; i < 4; i++) {
	 * for (int k = -3; k < 4; k++) chunks2.add(e.getTo().getWorld().getChunkAt(x +
	 * i, z + k)); } Collection<Chunk> chunks = new ArrayList<>(); for (int j = -1;
	 * j < 2; j++) { for (int k = -1; k < 2; k++)
	 * chunks.add(e.getTo().getWorld().getChunkAt(x + j, z + k)); } for (Chunk chunk
	 * : chunks) { List<NPCMemory> memories =
	 * NPCHandler.getLoadedNPCsInChunk(chunk); if (memories != null &&
	 * !memories.isEmpty()) for (NPCMemory memory : memories) { CustomNPC brain =
	 * (CustomNPC) NPCHandler.NPCMemoryToBrain.get(memory);
	 * NPCHandler.hideNPCBrain(p, brain); } } for (Chunk chunk : chunks2) {
	 * List<CustomNPC> brains = NPCHandler.mountBrains(chunk); if (brains != null)
	 * for (CustomNPC brain : brains) { for (Player player :
	 * Bukkit.getOnlinePlayers()) NPCHandler.createNPCShell(player, brain); } }
	 * Thread thread = new Thread(new Runnable() { public void run() { if
	 * (!NPCHandler.allNPCLoaded(p)) { NPCHandler.showNPCs(p);
	 * NPCHandler.hideNPCs(p); } List<Thread> tasks = Listener.NPCTasks.get(p); if
	 * (tasks != null) { if (!tasks.isEmpty()) { if
	 * (tasks.contains(Thread.currentThread()))
	 * tasks.remove(Thread.currentThread()); if (!tasks.isEmpty()) { Thread
	 * nextThread = tasks.get(0); nextThread.start(); } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); }
	 * Listener.NPCTasks.put(p, tasks); } }); if (chargingNPCs.get(p) == null)
	 * chargingNPCs.put(p, Boolean.valueOf(false)); if (((Boolean)
	 * chargingNPCs.get(p)).booleanValue()) { List<Thread> tasks = NPCTasks.get(p);
	 * if (tasks == null) tasks = new ArrayList<>(); tasks.add(thread);
	 * NPCTasks.put(p, tasks); } else { chargingNPCs.put(p, Boolean.valueOf(true));
	 * thread.start(); } (new Thread(new Runnable() { public void run() { Chunk
	 * chunk1 = e.getFrom().getChunk(); Chunk chunk2 = e.getTo().getChunk();
	 * TerritoryChunk TChunk1 = new TerritoryChunk(chunk1); TerritoryChunk TChunk2 =
	 * new TerritoryChunk(chunk2); if (TChunk1.getOwningRace() !=
	 * TChunk2.getOwningRace()) p.sendMessage("Leaving " +
	 * TChunk1.getOwningRace().toString() + " Domain and Entering " +
	 * TChunk2.getOwningRace().toString() + " Domain"); } })).start(); }
	 */
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
	    PData.setDuration(1);
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
	} else if (ent instanceof Cow || ent instanceof Sheep) {
	    Random rand = new Random();
	    int r = Math.abs(rand.nextInt()) % 1000;
	    if (r < 500) {
		List<ItemStack> items = e.getDrops();
		Iterator<ItemStack> iterator = items.iterator();
		while (iterator.hasNext()) {
		    ItemStack item = iterator.next();
		    if (item.getType() == Material.BEEF || item.getType() == Material.MUTTON)
			iterator.remove();
		}
		r = Math.abs(rand.nextInt()) % 1000;
		int q = 1;
		if (r < 100) {
		    items.add(Ingredient.RAW_GOURMET_MEAT.getIngredient());
		} else if (r < 300) {
		    ItemStack p = Ingredient.RAW_PRIME_MEAT.getIngredient();
		    q = Math.abs(rand.nextInt()) % 2 + 1;
		    p.setAmount(q);
		    items.add(p);
		} else {
		    ItemStack p = Ingredient.RAW_MEAT.getIngredient();
		    q = Math.abs(rand.nextInt()) % 3 + 1;
		    p.setAmount(q);
		    items.add(p);
		}
	    }
	} else if (ent instanceof Chicken) {
	    Random rand = new Random();
	    int r = Math.abs(rand.nextInt()) % 1000;
	    if (r < 500) {
		List<ItemStack> items = e.getDrops();
		Iterator<ItemStack> iterator = items.iterator();
		while (iterator.hasNext()) {
		    ItemStack item = iterator.next();
		    if (item.getType() == Material.CHICKEN)
			iterator.remove();
		}
		r = Math.abs(rand.nextInt()) % 1000;
		int q = 1;
		if (r < 100) {
		    items.add(Ingredient.RAW_WHOLE_BIRD.getIngredient());
		} else if (r < 300) {
		    ItemStack p = Ingredient.RAW_BIRD_THIGH.getIngredient();
		    q = Math.abs(rand.nextInt()) % 2 + 1;
		    p.setAmount(q);
		    items.add(p);
		} else {
		    ItemStack p = Ingredient.RAW_BIRD_DRUMSTICK.getIngredient();
		    q = Math.abs(rand.nextInt()) % 3 + 1;
		    p.setAmount(q);
		    items.add(p);
		}
	    }
	} else if (ent instanceof Parrot) {
	    Random rand = new Random();
	    int r = Math.abs(rand.nextInt()) % 1000;
	    if (r < 500) {
		List<ItemStack> items = e.getDrops();
		r = Math.abs(rand.nextInt()) % 1000;
		int q = 1;
		if (r < 100) {
		    ItemStack p = Ingredient.RAW_BIRD_THIGH.getIngredient();
		    p.setAmount(q);
		    items.add(p);
		} else {
		    ItemStack p = Ingredient.RAW_BIRD_DRUMSTICK.getIngredient();
		    q = Math.abs(rand.nextInt()) % 2 + 1;
		    p.setAmount(q);
		    items.add(p);
		}
	    }
	} else if (ent instanceof Rabbit) {
	    Random rand = new Random();
	    int r = Math.abs(rand.nextInt()) % 1000;
	    if (r < 500) {
		List<ItemStack> items = e.getDrops();
		Iterator<ItemStack> iterator = items.iterator();
		while (iterator.hasNext()) {
		    ItemStack item = iterator.next();
		    if (item.getType() == Material.RABBIT)
			iterator.remove();
		}
		r = Math.abs(rand.nextInt()) % 1000;
		int q = 1;
		if (r < 100) {
		    ItemStack p = Ingredient.RAW_PRIME_MEAT.getIngredient();
		    q = Math.abs(rand.nextInt()) % 2 + 1;
		    p.setAmount(q);
		    items.add(p);
		} else {
		    ItemStack p = Ingredient.RAW_MEAT.getIngredient();
		    q = Math.abs(rand.nextInt()) % 3 + 1;
		    p.setAmount(q);
		    items.add(p);
		}
	    }
	}
	if (ede instanceof EntityDamageByEntityEvent && !(ent instanceof Player)) {
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
		    items.add(Drop.HEART.getDrop());
		if (e.getEntity() instanceof ElderGuardian || e.getEntity() instanceof EnderDragon
			|| e.getEntity() instanceof Wither) {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 200) {
			items.add(Rupees.SILVER.getRupee());
		    } else if (rand <= 250) {
			items.add(Rupees.GOLD.getRupee());
		    }
		    rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 500)
			items.add(Item.HEART_PIECE.getItem());
		} else {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 50) {
			items.add(Rupees.GREEN.getRupee());
		    } else if (rand <= 10) {
			items.add(Rupees.BLUE.getRupee());
		    }
		}
		if (e.getEntity() instanceof ElderGuardian) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_NAYDRAS_FANG,
			    Ingredient.SHARD_OF_NAYDRAS_HORN, Ingredient.NAYDRAS_CLAW, Ingredient.NAYDRAS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		} else if (e.getEntity() instanceof Wither) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_DINRAALS_FANG,
			    Ingredient.SHARD_OF_DINRAALS_HORN, Ingredient.DINRAALS_CLAW, Ingredient.DINRAALS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		} else if (e.getEntity() instanceof EnderDragon) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_FAROSHS_FANG,
			    Ingredient.SHARD_OF_FAROSHS_HORN, Ingredient.FAROSHS_CLAW, Ingredient.FAROSHS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		}
	    } else {
		List<ItemStack> items = e.getDrops();
		if ((int) (Math.random() * 100.0D) <= 5)
		    items.add(Drop.HEART.getDrop());
		if (e.getEntity() instanceof ElderGuardian || e.getEntity() instanceof EnderDragon
			|| e.getEntity() instanceof Wither) {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 20) {
			items.add(Rupees.SILVER.getRupee());
		    } else if (rand <= 25) {
			items.add(Rupees.GOLD.getRupee());
		    }
		    rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 50)
			items.add(Item.HEART_PIECE.getItem());
		} else {
		    int rand = (int) (Math.random() * 10000.0D);
		    if (rand <= 5) {
			items.add(Rupees.GREEN.getRupee());
		    } else if (rand <= 1) {
			items.add(Rupees.BLUE.getRupee());
		    }
		}
		if (e.getEntity() instanceof ElderGuardian) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_NAYDRAS_FANG,
			    Ingredient.SHARD_OF_NAYDRAS_HORN, Ingredient.NAYDRAS_CLAW, Ingredient.NAYDRAS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		} else if (e.getEntity() instanceof Wither) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_DINRAALS_FANG,
			    Ingredient.SHARD_OF_DINRAALS_HORN, Ingredient.DINRAALS_CLAW, Ingredient.DINRAALS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		} else if (e.getEntity() instanceof EnderDragon) {
		    Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_FAROSHS_FANG,
			    Ingredient.SHARD_OF_FAROSHS_HORN, Ingredient.FAROSHS_CLAW, Ingredient.FAROSHS_SCALE });
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r < 100)
			items.add(Main.randomInCollection(c).getIngredient());
		}
	    }
	} else {
	    List<ItemStack> items = e.getDrops();
	    if ((int) (Math.random() * 100.0D) <= 5)
		items.add(Drop.HEART.getDrop());
	    if (e.getEntity() instanceof org.bukkit.entity.ElderGuardian
		    || e.getEntity() instanceof org.bukkit.entity.EnderDragon
		    || e.getEntity() instanceof org.bukkit.entity.Wither) {
		int rand = (int) (Math.random() * 10000.0D);
		if (rand <= 20) {
		    items.add(Rupees.SILVER.getRupee());
		} else if (rand <= 25) {
		    items.add(Rupees.GOLD.getRupee());
		}
		rand = (int) (Math.random() * 10000.0D);
		if (rand <= 50)
		    items.add(Item.HEART_PIECE.getItem());
	    } else {
		int rand = (int) (Math.random() * 10000.0D);
		if (rand <= 5) {
		    items.add(Rupees.GREEN.getRupee());
		} else if (rand <= 1) {
		    items.add(Rupees.BLUE.getRupee());
		}
	    }
	    if (e.getEntity() instanceof ElderGuardian) {
		Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_NAYDRAS_FANG,
			Ingredient.SHARD_OF_NAYDRAS_HORN, Ingredient.NAYDRAS_CLAW, Ingredient.NAYDRAS_SCALE });
		Random rand = new Random();
		int r = Math.abs(rand.nextInt()) % 1000;
		if (r < 50)
		    items.add(Main.randomInCollection(c).getIngredient());
	    } else if (e.getEntity() instanceof Wither) {
		Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_DINRAALS_FANG,
			Ingredient.SHARD_OF_DINRAALS_HORN, Ingredient.DINRAALS_CLAW, Ingredient.DINRAALS_SCALE });
		Random rand = new Random();
		int r = Math.abs(rand.nextInt()) % 1000;
		if (r < 50)
		    items.add(Main.randomInCollection(c).getIngredient());
	    } else if (e.getEntity() instanceof EnderDragon) {
		Collection<Ingredient> c = Arrays.asList(new Ingredient[] { Ingredient.SHARD_OF_FAROSHS_FANG,
			Ingredient.SHARD_OF_FAROSHS_HORN, Ingredient.FAROSHS_CLAW, Ingredient.FAROSHS_SCALE });
		Random rand = new Random();
		int r = Math.abs(rand.nextInt()) % 1000;
		if (r < 50)
		    items.add(Main.randomInCollection(c).getIngredient());
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
	    } else if (nbt.getKeys().contains("BlockType") && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
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
			    if (b.getType() == Material.BARRIER)
				b.setType(Material.AIR);
			    if (b.getRelative(BlockFace.UP).getType() == Material.LIGHT)
				b.getRelative(BlockFace.UP).setType(Material.AIR);
			} else {
			    cb.remove();
			    p.getInventory().addItem(BlockBuilder.build(BlockEnum.CAMP_UNLIT));
			}
		    } else {
			String uuid = nbt2.getData().getString("LinkedArmorStand");
			CustomBlock cb = CustomBlock.getCustomBlock(uuid);
			ItemStack i = cb.getItem();
			NBTItem nbt3 = new NBTItem(i);
			if (nbt3.getString("BlockType").equals("Campfire_Lit")) {
			    CookingInventoryManager manager = new CookingInventoryManager(p, cb);
			    p.openInventory(manager.getInventory());
			}
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

    /*
     * @EventHandler public void onChunkUnload(ChunkUnloadEvent e) { Chunk chunk =
     * e.getChunk(); NPCHandler.dismountBrains(chunk); }
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
	PlayerData PData;
	final Player p = e.getPlayer();
	PacketReader reader = new PacketReader();
	reader.inject(p);
	/*
	 * final Chunk chunk = p.getLocation().getChunk(); final int x = chunk.getX();
	 * final int z = chunk.getZ(); Collection<Chunk> chunks = new ArrayList<>(); for
	 * (int i = -3; i < 4; i++) { for (int j = -3; j < 4; j++)
	 * chunks.add(chunk.getWorld().getChunkAt(x + i, z + j)); } for (Chunk c :
	 * chunks) NPCHandler.mountBrains(c); NPCHandler.createNPCs(p);
	 * NPCHandler.hideNPCBrains(p); Thread thread = new Thread(new Runnable() {
	 * public void run() { NPCHandler.showNPCs(p); NPCHandler.hideNPCs(p);
	 * List<Thread> tasks = Listener.NPCTasks.get(p); if (tasks != null) { if
	 * (!tasks.isEmpty()) { if (tasks.contains(Thread.currentThread()))
	 * tasks.remove(Thread.currentThread()); if (!tasks.isEmpty()) { Thread
	 * nextThread = tasks.get(0); nextThread.start(); } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
	 * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); }
	 * Listener.NPCTasks.put(p, tasks); } }); if (chargingNPCs.get(p) == null)
	 * chargingNPCs.put(p, Boolean.valueOf(false)); if (((Boolean)
	 * chargingNPCs.get(p)).booleanValue()) { List<Thread> tasks = NPCTasks.get(p);
	 * if (tasks == null) tasks = new ArrayList<>(); tasks.add(thread);
	 * NPCTasks.put(p, tasks); } else { chargingNPCs.put(p, Boolean.valueOf(true));
	 * thread.start(); }
	 */
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
	    PData.applyAttributes(true);
	} else {
	    PData.applyAttributes(false);
	    PlayerData.applyColors(p);
	}
	/*
	 * (new BukkitRunnable() { public void run() { Collection<Chunk> chunks = new
	 * ArrayList<>(); for (int i = -1; i < 2; i++) { for (int j = -1; j < 2; j++)
	 * chunks.add(chunk.getWorld().getChunkAt(x + i, z + j)); } for (Chunk chunk :
	 * chunks) { List<NPCMemory> memories = NPCHandler.getLoadedNPCsInChunk(chunk);
	 * if (memories != null && !memories.isEmpty()) for (NPCMemory memory :
	 * memories) { CustomNPC brain = (CustomNPC)
	 * NPCHandler.NPCMemoryToBrain.get(memory); NPCHandler.hideNPCBrain(p, brain); }
	 * } } }).runTaskLater((Plugin) Main.getInstance(), 20L);
	 */
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
		    PData.applyAttributes(true);
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
	EquipmentSlot s = e.getHand();
	if (Food.isFood(item)) {
	    e.setCancelled(true);
	    ItemStack i = p.getInventory().getItem(s);
	    if (p.getGameMode() != GameMode.CREATIVE)
		i.setAmount(i.getAmount() - 1);
	    NBTItem nbt = new NBTItem(item);
	    FoodBonus b = FoodBonus.valueOf(nbt.getString("Bonus"));
	    int health = nbt.getInteger("Health");
	    p.setHealth(Math.min(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), p.getHealth() + health));
	    PData.setHealth(p.getHealth());
	    if (health > 0)
		p.playSound(p.getLocation(), "zelda.heart.get", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    if (b != FoodBonus.RANDOM && b != FoodBonus.NONE) {
		int potency = nbt.getInteger("Potency");
		int duration;
		switch (b) {
		case CHILLY:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(potency);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case ELECTRO:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(potency);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case ENDURING:
		    p.setSaturation(Math.min(20, p.getSaturation() + potency));
		    break;
		case ENERGIZING:
		    p.setFoodLevel(Math.min(20, p.getFoodLevel() + potency));
		    break;
		case FIREPROOF:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(potency);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case HASTY:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(potency);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case HEARTY:
		    p.setAbsorptionAmount(potency);
		    break;
		case MIGHTY:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(potency);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case NONE:
		    break;
		case RANDOM:
		    break;
		case SNEAKY:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(potency);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case SPICY:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(potency);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(0);
		    PData.setDuration(duration);
		    break;
		case TOUGH:
		    duration = nbt.getInteger("Duration");
		    PData.setColdResistance(0);
		    PData.setHeatResistance(0);
		    PData.setHastyBonus(0);
		    PData.setFireproof(0);
		    PData.setElectro(0);
		    PData.setSneaky(0);
		    PData.setMighty(0);
		    PData.setTough(potency);
		    PData.setDuration(duration);
		    break;
		default:
		    break;

		}
	    }
	} else if (item.getType() == Material.MILK_BUCKET) {
	    PData.resetExtraEffects();
	    e.setCancelled(true);
	    if (p.getGameMode() != GameMode.CREATIVE) {
		ItemStack i = p.getInventory().getItem(s);
		if (i.getType() == Material.MILK_BUCKET)
		    i.setType(Material.BUCKET);
	    }
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
		} else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE
			|| e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
			|| e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR
			|| e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
		    double modifier = 1d - 0.05 * PData.getFireproof();
		    e.setDamage(e.getDamage() * modifier);
		} else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
		    double modifier = 1d - 0.05 * PData.getElectro();
		    e.setDamage(e.getDamage() * modifier);
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
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
	if (attacker instanceof Player && entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    Player p2 = (Player) attacker;
	    PlayerData PData2 = PlayerData.getData(p2);
	    List<Ring> rings2 = PData2.getRings();
	    double mod1 = RingCalculator.getPhysicalArmorModifier(rings);
	    double mod2 = RingCalculator.getPhysicalDamageModifier(rings2);
	    double mod3 = 1d + 0.05 * PData2.getMighty();
	    double mod4 = 1d - 0.05 * PData.getTough();
	    double mod5 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	    PlayerData.checkApplyExtraEffect(entity, p2);
	} else if (entity instanceof Player) {
	    Player p = (Player) entity;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    double mod1 = RingCalculator.getPhysicalArmorModifier(rings);
	    double mod2 = 1d - 0.05 * PData.getTough();
	    e.setDamage(e.getDamage() * mod1 * mod2);
	    PData.setHealth(p.getHealth() - e.getEvent().getFinalDamage());
	} else if (attacker instanceof Player) {
	    Player p = (Player) attacker;
	    PlayerData PData = PlayerData.getData(p);
	    List<Ring> rings = PData.getRings();
	    double mod1 = RingCalculator.getPhysicalDamageModifier(rings);
	    double mod2 = 1d + 0.05 * PData.getMighty();
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3);
	    PlayerData.checkApplyExtraEffect(entity, p);
	}
    }

    @EventHandler
    public void onEntityFireDamageByEntity(EntityFireDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
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
	    double mod5 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5 * 1.45D);
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
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.45D);
	}
    }

    @EventHandler
    public void onEntityDarkDamageByEntity(EntityDarkDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
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
	    double mod5 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5 * 1.4D);
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
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.4D);
	}
    }

    @EventHandler
    public void onEntityElectricDamageByEntity(EntityElectricDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	final org.bukkit.entity.Entity entity = e.getEntity();
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
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
	    double mod5 = 1d - 0.05 * PData.getElectro();
	    double mod6 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5 * mod6 * 1.45D);
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
	    double mod3 = 1d - 0.05 * PData.getElectro();
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.45D);
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
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.45D);
	}
    }

    @EventHandler
    public void onEntityIceDamageByEntity(EntityIceDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
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
	    double mod5 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5 * 1.25D);
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
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.25D);
	}
    }

    @EventHandler
    public void onEntityLightDamageByEntity(EntityLightDamageByEntityEvent e) {
	org.bukkit.entity.Entity attacker = e.getAttacker();
	org.bukkit.entity.Entity entity = e.getEntity();
	double dist = attacker.getLocation().distanceSquared(entity.getLocation());
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
	    double mod5 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * mod4 * mod5 * 1.75D);
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
	    double mod3 = PData.getCurrentRace() == Race.PIAF ? 1d + 1d / (1 + Math.exp(-0.1 * (dist - 900))) : 1d;
	    e.setDamage(e.getDamage() * mod1 * mod2 * mod3 * 1.75D);
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
		newItems.add(Item.HEART_PIECE.getItem());
	    rand = (int) (Math.random() * 10000.0D);
	    if (rand <= 200) {
		newItems.add(Rupees.YELLOW.getRupee());
	    } else if (rand <= 300) {
		newItems.add(Rupees.RED.getRupee());
	    } else if (rand <= 350) {
		newItems.add(Rupees.PURPLE.getRupee());
	    }
	    if (item.getType() != Material.AIR) {
		Ring ring = Ring.getRingFromItem(item);
		ItemStack itemRing = ring.getRing();
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
		p.playSound(chest.getLocation(), "zelda.open_chest", SoundCategory.BLOCKS, 1000.0F, 1.0F);
		lootChest.remove(chest.getLocation());
	    }
	} else if (holder instanceof StorageMinecart) {
	    StorageMinecart minecart = (StorageMinecart) holder;
	    if (lootMinecart.contains(minecart.getUniqueId())) {
		Player p = (Player) e.getPlayer();
		p.playSound(minecart.getLocation(), "zelda.open_chest", SoundCategory.BLOCKS, 1000.0F, 1.0F);
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
	if (hand == null || hand.getType() == Material.AIR) {
	    Random rand = new Random();

	    int r = Math.abs(rand.nextInt()) % 1000;
	    Collection<Ingredient> normal = Arrays.asList(
		    new Ingredient[] { Ingredient.HYLIAN_SHROOM, Ingredient.SWIFT_CARROT, Ingredient.STAMELLA_SHROOM,
			    Ingredient.HEARTY_RADISH, Ingredient.BIG_HEARTY_RADISH, Ingredient.HYRULE_HERB,
			    Ingredient.FORTIFIED_PUMPKIN, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE });
	    Collection<Ingredient> warm = Arrays
		    .asList(new Ingredient[] { Ingredient.ENDURA_CARROT, Ingredient.ZAPSHROOM, Ingredient.ENDURA_SHROOM,
			    Ingredient.RAZORSHROOM, Ingredient.SPICY_PEPPER, Ingredient.SUNSHROOM, Ingredient.ARMORANTH,
			    Ingredient.ELECTRIC_SAFFLINA, Ingredient.WARM_SAFFLINA });
	    Collection<Ingredient> cold = Arrays.asList(
		    new Ingredient[] { Ingredient.RUSHROOM, Ingredient.SILENT_SHROOM, Ingredient.BIG_HEARTY_TRUFFLE,
			    Ingredient.HEARTY_TRUFFLE, Ingredient.IRONSHROOM, Ingredient.CHILLSHROOM,
			    Ingredient.COOL_SAFFLINA, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS });
	    if (r < 50
		    && (mat == Material.FERN || mat == Material.LARGE_FERN || mat == Material.GRASS
			    || mat == Material.TALL_GRASS || mat == Material.DEAD_BUSH)
		    && block.getMetadata("NoRupee").isEmpty()) {
		switch (block.getBiome()) {
		case MEADOW:
		case MUSHROOM_FIELDS:
		case OLD_GROWTH_BIRCH_FOREST:
		case SUNFLOWER_PLAINS:
		case PLAINS:
		case BIRCH_FOREST:
		case FOREST:
		case FLOWER_FOREST:
		case BEACH:
		    block.getWorld().dropItemNaturally(block.getLocation(),
			    Main.randomInCollection(normal).getIngredient());
		    break;
		case SPARSE_JUNGLE:
		case WINDSWEPT_SAVANNA:
		case SWAMP:
		case SAVANNA_PLATEAU:
		case MANGROVE_SWAMP:
		case SAVANNA:
		case JUNGLE:
		case WOODED_BADLANDS:
		case BADLANDS:
		case BAMBOO_JUNGLE:
		case DARK_FOREST:
		case ERODED_BADLANDS:
		case DESERT:
		    block.getWorld().dropItemNaturally(block.getLocation(),
			    Main.randomInCollection(warm).getIngredient());
		    break;
		case OLD_GROWTH_PINE_TAIGA:
		case FROZEN_PEAKS:
		case GROVE:
		case OLD_GROWTH_SPRUCE_TAIGA:
		case SNOWY_BEACH:
		case SNOWY_TAIGA:
		case TAIGA:
		case SNOWY_PLAINS:
		case SNOWY_SLOPES:
		case STONY_SHORE:
		case WINDSWEPT_FOREST:
		case WINDSWEPT_GRAVELLY_HILLS:
		case WINDSWEPT_HILLS:
		case STONY_PEAKS:
		case JAGGED_PEAKS:
		case ICE_SPIKES:
		    block.getWorld().dropItemNaturally(block.getLocation(),
			    Main.randomInCollection(cold).getIngredient());
		    break;
		default:
		    break;
		}
	    }
	    r = Math.abs(rand.nextInt()) % 1000;
	    if (r <= 50 && (mat == Material.ACACIA_LEAVES || mat == Material.BIRCH_LEAVES
		    || mat == Material.MANGROVE_LEAVES || mat == Material.OAK_LEAVES || mat == Material.AZALEA_LEAVES
		    || mat == Material.FLOWERING_AZALEA_LEAVES || mat == Material.JUNGLE_LEAVES)) {
		Collection<Ingredient> jungle = Arrays.asList(new Ingredient[] { Ingredient.HYDROMELON,
			Ingredient.MIGHTY_BANANAS, Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT });
		switch (e.getBlock().getType()) {
		case ACACIA_LEAVES:
		case BIRCH_LEAVES:
		    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			    Ingredient.VOLTFRUIT.getIngredient());
		    break;
		case MANGROVE_LEAVES:
		    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			    Ingredient.FLEET_LOTUS_SEEDS.getIngredient());
		    break;
		case OAK_LEAVES:
		    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			    Ingredient.APPLE.getIngredient());
		    break;
		case AZALEA_LEAVES:
		case FLOWERING_AZALEA_LEAVES:
		    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			    Ingredient.WILDBERRY.getIngredient());
		    break;
		case JUNGLE_LEAVES:
		    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			    Main.randomInCollection(jungle).getIngredient());
		    break;
		default:
		    break;
		}
	    }
	    return;
	}
	if (!hand.containsEnchantment(Enchantment.SILK_TOUCH) && (mat == Material.FERN || mat == Material.LARGE_FERN
		|| mat == Material.GRASS || mat == Material.TALL_GRASS || mat == Material.DEAD_BUSH)) {
	    if (!block.getMetadata("NoRupee").isEmpty()
		    && ((MetadataValue) block.getMetadata("NoRupee").get(0)).asBoolean())
		return;
	    int rand = (int) (Math.random() * 10000.0D);
	    if (rand <= 200) {
		block.getWorld().dropItemNaturally(block.getLocation(), Rupees.GREEN.getRupee());
	    } else if (rand <= 50) {
		block.getWorld().dropItemNaturally(block.getLocation(), Rupees.BLUE.getRupee());
	    } else if (rand <= 500) {
		block.getWorld().dropItemNaturally(block.getLocation(), Drop.HEART.getDrop());
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
		    drops.add(Rupees.GOLD.getRupee());
		} else if (rand <= 75) {
		    drops.add(Rupees.SILVER.getRupee());
		} else if (rand <= 200) {
		    drops.add(Rupees.PURPLE.getRupee());
		} else if (rand <= 450) {
		    drops.add(Rupees.RED.getRupee());
		} else if (rand <= 950) {
		    drops.add(Rupees.YELLOW.getRupee());
		} else if (rand <= 1825) {
		    drops.add(Rupees.BLUE.getRupee());
		} else if (rand <= 3075) {
		    drops.add(Rupees.GREEN.getRupee());
		}
		Random random = new Random();
		int r = Math.abs(random.nextInt()) % 10000;
		Collection<Ingredient> rare = Arrays.asList(new Ingredient[] { Ingredient.TOPAZ, Ingredient.RUBY,
			Ingredient.SAPPHIRE, Ingredient.DIAMOND });
		Collection<Ingredient> normal = Arrays
			.asList(new Ingredient[] { Ingredient.AMBER, Ingredient.OPAL, Ingredient.LUMINOUS_STONE });
		if (r <= 100) {
		    drops.add(Main.randomInCollection(rare).getIngredient());
		} else if (r <= 300) {
		    drops.add(Main.randomInCollection(normal).getIngredient());
		}
		for (ItemStack drop : drops) {
		    block.getWorld().dropItemNaturally(block.getLocation(), drop);
		}
	    } else {
		NBTItem nbtI = new NBTItem(hand);
		if (nbtI.getKeys().contains("ItemType")) {
		    Item i = Item.valueOf(nbtI.getString("ItemType"));
		    if (i == Item.CATCHING_NET && block.getBlockData() instanceof Leaves) {
			Leaves l = (Leaves) block.getBlockData();
			Collection<Ingredient> mangrove = Arrays.asList(new Ingredient[] { Ingredient.HOT_FOOTED_FROG,
				Ingredient.TIRELESS_FROG, Ingredient.WARM_DARNER });
			Collection<Ingredient> spruce = Arrays
				.asList(new Ingredient[] { Ingredient.WINTERWING_BUTTERFLY, Ingredient.COLD_DARNER });
			Collection<Ingredient> acacia = Arrays
				.asList(new Ingredient[] { Ingredient.SUMMERWING_BUTTERFLY,
					Ingredient.SMOTHERWING_BUTTERFLY, Ingredient.HEARTY_LIZARD });
			Collection<Ingredient> birch = Arrays.asList(new Ingredient[] { Ingredient.RUGGED_RHINO_BEETLE,
				Ingredient.THUNDERWING_BUTTERFLY, Ingredient.ELECTRIC_DARNER });
			Collection<Ingredient> dark_oak = Arrays
				.asList(new Ingredient[] { Ingredient.SUNSET_FIREFLY, Ingredient.BLADED_RHINO_BEETLE });
			Collection<Ingredient> oak = Arrays
				.asList(new Ingredient[] { Ingredient.RESTLESS_CRICKET, Ingredient.HIGHTAIL_LIZARD });
			Collection<Ingredient> jungle = Arrays.asList(
				new Ingredient[] { Ingredient.ENERGETIC_RHINO_BEETLE, Ingredient.FIREPROOF_LIZARD });
			Collection<Ingredient> azalea = Arrays.asList(new Ingredient[] { Ingredient.FAIRY });
			Random rand = new Random();
			int r = Math.abs(rand.nextInt() % 10000);
			if (!l.isPersistent() && r < 500) {
			    ItemStack d = new ItemStack(Material.AIR);
			    switch (mat) {
			    case MANGROVE_LEAVES:
				d = Main.randomInCollection(mangrove).getIngredient();
				break;
			    case SPRUCE_LEAVES:
				d = Main.randomInCollection(spruce).getIngredient();
				break;
			    case ACACIA_LEAVES:
				d = Main.randomInCollection(acacia).getIngredient();
				break;
			    case BIRCH_LEAVES:
				d = Main.randomInCollection(birch).getIngredient();
				break;
			    case DARK_OAK_LEAVES:
				d = Main.randomInCollection(dark_oak).getIngredient();
				break;
			    case OAK_LEAVES:
				d = Main.randomInCollection(oak).getIngredient();
				break;
			    case JUNGLE_LEAVES:
				d = Main.randomInCollection(jungle).getIngredient();
				break;
			    case AZALEA_LEAVES:
			    case FLOWERING_AZALEA_LEAVES:
				d = Main.randomInCollection(azalea).getIngredient();
				break;
			    default:
				break;
			    }
			    if (d.getType() != Material.AIR)
				block.getWorld().dropItemNaturally(block.getLocation(), d);
			}
		    }
		} else if (mat == Material.ACACIA_LEAVES || mat == Material.BIRCH_LEAVES
			|| mat == Material.MANGROVE_LEAVES || mat == Material.OAK_LEAVES
			|| mat == Material.AZALEA_LEAVES || mat == Material.FLOWERING_AZALEA_LEAVES
			|| mat == Material.JUNGLE_LEAVES) {
		    Random rand = new Random();
		    int r = Math.abs(rand.nextInt()) % 1000;
		    if (r <= 50) {
			Collection<Ingredient> jungle = Arrays.asList(new Ingredient[] { Ingredient.HYDROMELON,
				Ingredient.MIGHTY_BANANAS, Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT });
			switch (e.getBlock().getType()) {
			case ACACIA_LEAVES:
			case BIRCH_LEAVES:
			    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
				    Ingredient.VOLTFRUIT.getIngredient());
			    break;
			case MANGROVE_LEAVES:
			    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
				    Ingredient.FLEET_LOTUS_SEEDS.getIngredient());
			    break;
			case OAK_LEAVES:
			    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
				    Ingredient.APPLE.getIngredient());
			    break;
			case AZALEA_LEAVES:
			case FLOWERING_AZALEA_LEAVES:
			    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
				    Ingredient.WILDBERRY.getIngredient());
			    break;
			case JUNGLE_LEAVES:
			    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
				    Main.randomInCollection(jungle).getIngredient());
			    break;
			default:
			    break;
			}
		    }
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

    /*
     * @EventHandler public void onRightClickNPCEvent(RightClickNPCEvent e) { Player
     * p = e.getPlayer(); PlayerData PData = PlayerData.getData(p); NPCMemory memory
     * = e.getMemory(); if (memory.hasStable() && memory.isMainNPC()) { StableMemory
     * stable = memory.getStable(); if (PData.hasDiscoveredStable(stable)) {
     * 
     * Inventory inv = InventoryManager.createInventory(null, 54, "Stable",
     * CustomInventoryType.STABLE); if (stable.isOwner(p)) {
     * StableManager.populateStableOwnerMenu(inv, p, stable); } else {
     * StableManager.populateStableTeleport(inv, p, stable, 0); }
     * 
     * } else { p.sendMessage("Discovered !"); PData.discoverStable(stable); } } }
     */

    /*
     * @EventHandler public void onLeftClickNPCEvent(final LeftClickNPCEvent e) {
     * Player player = e.getPlayer(); if
     * (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR)
     * { for (Player p : Bukkit.getOnlinePlayers()) { final Chunk chunk =
     * NPCHandler.getChunkFromBrain(e.getNpcBrain()); Thread thread = new Thread(new
     * Runnable() { public void run() { NPCHandler.removeNPC(e.getNpcBrain(), p,
     * chunk); List<Thread> tasks = Listener.NPCTasks.get(p); if (tasks != null) {
     * if (!tasks.isEmpty()) { if (tasks.contains(Thread.currentThread()))
     * tasks.remove(Thread.currentThread()); if (!tasks.isEmpty()) { Thread
     * nextThread = tasks.get(0); nextThread.start(); } else {
     * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
     * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); } } else {
     * Listener.chargingNPCs.put(p, Boolean.valueOf(false)); }
     * Listener.NPCTasks.put(p, tasks); } }); if (chargingNPCs.get(p) == null)
     * chargingNPCs.put(p, Boolean.valueOf(false)); if (((Boolean)
     * chargingNPCs.get(p)).booleanValue()) { List<Thread> tasks = NPCTasks.get(p);
     * if (tasks == null) tasks = new ArrayList<>(); tasks.add(thread);
     * NPCTasks.put(p, tasks); continue; } chargingNPCs.put(p,
     * Boolean.valueOf(true)); thread.start(); } } else {
     * player.sendMessage("Left Click"); } }
     */

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
	else {
	    Random rand = new Random();
	    double d = rand.nextFloat();
	    if (d < 0.08 * PData.getSneaky())
		e.setCancelled(true);
	}
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
	if (e.getCaught() instanceof org.bukkit.entity.Item) {
	    org.bukkit.entity.Item item = (org.bukkit.entity.Item) e.getCaught();
	    Biome b = e.getHook().getLocation().getBlock().getBiome();
	    Collection<Ingredient> normal = Arrays.asList(new Ingredient[] { Ingredient.STAMINOKA_BASS,
		    Ingredient.ARMORED_CARP, Ingredient.VOLTFIN_TROUT, Ingredient.RAZORCLAW_CRAB });
	    Collection<Ingredient> cold = Arrays
		    .asList(new Ingredient[] { Ingredient.ARMORED_PORGY, Ingredient.HEARTY_SALMON,
			    Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.CHILLFIN_TROUT, Ingredient.IRONSHELL_CRAB });
	    Collection<Ingredient> warm = Arrays.asList(new Ingredient[] { Ingredient.MIGHTY_CARP,
		    Ingredient.MIGHTY_PORGY, Ingredient.HEARTY_BLUESHELL_SNAIL, Ingredient.SIZZLEFIN_TROUT,
		    Ingredient.BRIGHT_EYED_CRAB });
	    Collection<Ingredient> river = Arrays.asList(new Ingredient[] { Ingredient.SANKE_CARP,
		    Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS, Ingredient.STEALTHFIN_TROUT });
	    Random rand = new Random();
	    int r = Math.abs(rand.nextInt()) % 100;
	    if (r < 50) {
		switch (b) {
		case OCEAN:
		case DEEP_OCEAN:
		    item.setItemStack(Main.randomInCollection(normal).getIngredient());
		    break;
		case FROZEN_OCEAN:
		case DEEP_FROZEN_OCEAN:
		case COLD_OCEAN:
		case DEEP_COLD_OCEAN:
		    item.setItemStack(Main.randomInCollection(cold).getIngredient());
		    break;
		case LUKEWARM_OCEAN:
		case DEEP_LUKEWARM_OCEAN:
		case WARM_OCEAN:
		    item.setItemStack(Main.randomInCollection(warm).getIngredient());
		    break;
		case RIVER:
		    item.setItemStack(Main.randomInCollection(river).getIngredient());
		default:
		    break;
		}
	    }
	}
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
	Entity ent = e.getRightClicked();
	if (ent instanceof WanderingTrader) {
	    e.setCancelled(true);
	    TraderInventoryManager manager = TraderInventoryManager.getManager((WanderingTrader) ent);
	    e.getPlayer().openInventory(manager.getInventory());
	}
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
	Random rand = new Random();
	int r = Math.abs(rand.nextInt()) % 1000;
	if (r <= 50) {
	    Collection<Ingredient> jungle = Arrays.asList(new Ingredient[] { Ingredient.HYDROMELON,
		    Ingredient.MIGHTY_BANANAS, Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT });
	    switch (e.getBlock().getType()) {
	    case ACACIA_LEAVES:
	    case BIRCH_LEAVES:
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			Ingredient.VOLTFRUIT.getIngredient());
		break;
	    case MANGROVE_LEAVES:
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			Ingredient.FLEET_LOTUS_SEEDS.getIngredient());
		break;
	    case OAK_LEAVES:
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Ingredient.APPLE.getIngredient());
		break;
	    case AZALEA_LEAVES:
	    case FLOWERING_AZALEA_LEAVES:
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			Ingredient.WILDBERRY.getIngredient());
		break;
	    case JUNGLE_LEAVES:
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
			Main.randomInCollection(jungle).getIngredient());
		break;
	    default:
		break;
	    }
	}
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent e) {
	Vehicle v = e.getVehicle();
	if (v instanceof StorageMinecart) {
	    Location loc = e.getTo();
	    if (loc.getWorld().getName().equals("world_the_end") && loc.getY() <= 0.0D) {
		Location newLoc = new Location(Bukkit.getWorld("world"), loc.getX(), 400.0D, loc.getZ());
		newLoc.getChunk().load();
		v.teleport(newLoc);
		v.setVelocity(v.getVelocity());
	    } else if (loc.getWorld().getName().equals("world") && loc.getY() >= 450.0D) {
		Location newLoc = new Location(Bukkit.getWorld("world_the_end"), loc.getX(), 0.0D, loc.getZ());
		newLoc.getChunk().load();
		v.teleport(newLoc);
		v.setVelocity(v.getVelocity());
	    }
	}
    }
}
