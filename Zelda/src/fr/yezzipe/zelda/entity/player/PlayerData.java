package fr.yezzipe.zelda.entity.player;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.PotionEffectMemory;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.items.enums.Ring;
import fr.yezzipe.zelda.territory.Waypoint;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.datafixers.util.Pair;

public class PlayerData {
    private String playerName;

    private String uuid;

    private Race currentRace;

    private double health;

    private int bonusHealth;

    private boolean attachedToWall;

    private boolean noLeftClickHook;

    private int skyLight;

    private int blockLight;

    private boolean isUnderwater;

    private boolean isVisible;

    private Environment currentDimension;

    private boolean isAtNight;

    private boolean isSpedUp;

    private List<PotionEffectMemory> effects = new ArrayList<PotionEffectMemory>();

    private List<Ring> rings = new ArrayList<Ring>();

    private List<Waypoint> waypoints = new ArrayList<Waypoint>();

    private List<UUID> discoveredStables = new ArrayList<UUID>();

    private static String folderPrefix = "players/";

    private static HashMap<String, PlayerData> Players = new HashMap<String, PlayerData>();

    private int potionTask;

    private Biome currentBiome;

    public PlayerData(Player player, Race race, double health) {
	this.playerName = player.getName();
	this.uuid = player.getUniqueId().toString();
	checkTick(true, player);
	setCurrentRace(race);
	this.health = health;
	this.bonusHealth = 0;
	this.attachedToWall = false;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public PlayerData(OfflinePlayer oP, Race race, int health) {
	this.playerName = oP.getName();
	this.uuid = oP.getUniqueId().toString();
	checkTick(true, oP.getPlayer());
	setCurrentRace(race);
	this.health = health;
	this.bonusHealth = 0;
	this.attachedToWall = false;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public String getPlayerName() {
	return this.playerName;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public Race getCurrentRace() {
	return this.currentRace;
    }

    public void setCurrentRace(Race currentRace) {
	this.currentRace = currentRace;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	if (currentRace == Race.PIAF) {
	    ItemStack chestplate = p.getInventory().getChestplate();
	    ItemStack elytra = new ItemStack(Material.ELYTRA);
	    ItemMeta meta = elytra.getItemMeta();
	    meta.setUnbreakable(true);
	    elytra.setItemMeta(meta);
	    if (chestplate != null) {
		p.getInventory().addItem(chestplate);
	    }
	    p.getInventory().setChestplate(elytra);
	} else {
	    ItemStack chestplate = p.getInventory().getChestplate();
	    if (chestplate != null) {
		ItemMeta meta = chestplate.getItemMeta();
		if (meta.isUnbreakable() && chestplate.getType() == Material.ELYTRA) {
		    p.getInventory().setChestplate(new ItemStack(Material.AIR));
		} else if (chestplate.getType() == Material.ELYTRA) {
		    p.getInventory().setChestplate(new ItemStack(Material.AIR));
		    p.getInventory().addItem(chestplate);
		}
	    }
	}
	if (currentRace == Race.TWILI) {
	    for (Player player : Bukkit.getOnlinePlayers()) {
		p.showPlayer(Main.getPlugin(Main.class), player);
	    }
	} else {
	    setVisible();
	    for (Player player : Bukkit.getOnlinePlayers()) {
		PlayerData PData = PlayerData.getData(player);
		if (PData.isVisible)
		    p.showPlayer(Main.getPlugin(Main.class), player);
		else
		    p.hidePlayer(Main.getPlugin(Main.class), player);
	    }
	}
	PlayerData.applyColors(p);
	PlayerData.applyAttributes(p, true);
	applyEffects(true);
    }

    private static boolean dataExist(Player player) {
	return Main.exist(String.valueOf(folderPrefix) + player.getUniqueId().toString());
    }

    private static boolean dataExist(OfflinePlayer oP) {
	return Main.exist(String.valueOf(folderPrefix) + oP.getUniqueId().toString());
    }

    public static boolean isPlayerDataRegistered(Player player) {
	if (Players.get(player.getUniqueId().toString()) != null)
	    return true;
	return false;
    }

    private static PlayerData getExistingData(Player player) {
	PlayerData Pdata = Main.<PlayerData>read(String.valueOf(folderPrefix) + player.getUniqueId().toString(),
		PlayerData.class);
	return Pdata;
    }

    private static PlayerData getExistingData(OfflinePlayer oP) {
	PlayerData Pdata = Main.<PlayerData>read(String.valueOf(folderPrefix) + oP.getUniqueId().toString(),
		PlayerData.class);
	return Pdata;
    }

    public void register() {
	Players.put(getUuid().toString(), this);
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	isSpedUp = false;
	this.skyLight = p.getLocation().getBlock().getLightFromSky();
	this.blockLight = p.getLocation().getBlock().getLightFromBlocks();
	this.isUnderwater = p.getEyeLocation().getBlock().getType() == Material.WATER;
	tick(true, true, currentRace == Race.NONE || currentRace == null);
	potionTask = new BukkitRunnable() {

	    @Override
	    public void run() {
		if(!p.isOnline()) {
		    cancel();
		    return;
		}
		ListIterator<PotionEffectMemory> iterator = effects.listIterator();
		boolean mustRefresh = false;
		while (iterator.hasNext()) {
		    PotionEffectMemory eff = iterator.next();
		    eff.TickDuration();
		    if (eff.getDuration() <= 0) {
			mustRefresh = true;
			iterator.remove();
		    }
		}
		if (mustRefresh)
		    applyEffects(false);

	    }
	}.runTaskTimer(Main.getPlugin(Main.class), 0, 20).getTaskId();
    }

    public void unregister() {
	save();
	Players.remove(getUuid().toString());
	Bukkit.getScheduler().cancelTask(potionTask);
    }

    public void setHealth(double health) {
	this.health = health;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public double getHealth() {
	return this.health;
    }

    public static PlayerData getData(Player player) {
	PlayerData PData = Players.get(player.getUniqueId().toString());
	if (PData != null)
	    return PData;
	return dataExist(player) ? getExistingData(player) : new PlayerData(player, Race.NONE, player.getHealth());
    }

    public static PlayerData getData(OfflinePlayer oP) {
	return dataExist(oP) ? getExistingData(oP) : new PlayerData(oP, Race.NONE, 20);
    }

    public static HashMap<String, PlayerData> getRegisteredPlayers() {
	return Players;
    }

    public void save() {
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public List<Ring> getRings() {
	return this.rings;
    }

    public void setRings(List<Ring> rings) {
	this.rings = rings;
	System.out.println(String.valueOf(this.playerName) + " equiped : " + rings);
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
	Player p = Bukkit.getPlayer(getUuid());
	PlayerData.applyAttributes(p, false);
	applyEffects(false);
    }

    public static void applyAttributes(Player player, boolean reset) {
	PlayerData playerData = getData(player);
	HashMap<Attribute, Double> attributes = new HashMap<>();
	player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1D);
	player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
	double health = 20.0D;
	switch (playerData.getCurrentRace()) {
	case PIAF:
	    health += 10.0D;
	    break;
	default:
	    break;
	}
	health += (2 * playerData.getBonusHealth());
	attributes.put(Attribute.GENERIC_MAX_HEALTH, Double.valueOf(health));
	List<Ring> rings = playerData.getRings();
	if (rings != null && rings.contains(Ring.AGILITY_RING))
	    if (attributes.containsKey(Attribute.GENERIC_MOVEMENT_SPEED)) {
		attributes.put(Attribute.GENERIC_MOVEMENT_SPEED, Double
			.valueOf(((Double) attributes.get(Attribute.GENERIC_MOVEMENT_SPEED)).doubleValue() + 0.2D));
	    } else {
		attributes.put(Attribute.GENERIC_MOVEMENT_SPEED, Double.valueOf(0.2D));
	    }
	for (Attribute key : attributes.keySet()) {
	    if (key == Attribute.GENERIC_MOVEMENT_SPEED) {
		player.getAttribute(key).setBaseValue(0.1D * (1.0D + ((Double) attributes.get(key)).doubleValue()));
		continue;
	    }
	    if (key == Attribute.GENERIC_MAX_HEALTH) {
		player.getAttribute(key).setBaseValue(((Double) attributes.get(key)).doubleValue());
		if (reset) {
		    player.setHealth(((Double) attributes.get(key)).doubleValue());
		    playerData.setHealth(((Double) attributes.get(key)).doubleValue());
		    continue;
		}
		player.setHealth((playerData.getHealth() > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
			? ((Double) attributes.get(key)).doubleValue()
			: playerData.getHealth());
	    }
	}
    }

    private void clearEffects(boolean resetEffects) {
	Player player = Bukkit.getPlayer(UUID.fromString(uuid));
	Collection<PotionEffect> effects = player.getActivePotionEffects();
	for (PotionEffect eff : effects) {
	    player.removePotionEffect(eff.getType());
	}
	if (resetEffects)
	    this.effects = new ArrayList<PotionEffectMemory>();
    }

    private List<PotionEffect> getPotionFromRace() {
	List<PotionEffect> list = new ArrayList<PotionEffect>();
	Player p = Bukkit.getPlayer(getUuid());
	switch (getCurrentRace()) {
	case ZORA:
	    if (isUnderwater()) {
		if (getSkyLight() >= 4)
		    list.add(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2147483647, 0, false, false, false));
		else
		    list.add(new PotionEffect(PotionEffectType.WATER_BREATHING, 2147483647, 0, false, false, false));
	    }
	    break;
	case GERUDO:
	    list.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647, 0, false, false, false));
	    if (p.getWorld().getEnvironment() == Environment.NORMAL) {
		if (BiomeRegistry.getRaceFromBiome(currentBiome).contains(currentRace)
			&& p.getWorld().getTime() <= 12000) {
		    list.add(new PotionEffect(PotionEffectType.SPEED, 2147483647, 0, false, false, false));
		}
	    }
	    break;
	case GORON:
	    list.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2147483647, 0, false, false, false));
	    break;
	case HYLIEN:
	    list.add(new PotionEffect(PotionEffectType.FAST_DIGGING, 2147483647, 0, false, false, false));
	    break;
	case KOKIRI:
	    list.add(new PotionEffect(PotionEffectType.REGENERATION, 2147483647, 0, false, false, false));
	    break;
	case TWILI:
	    list.add(new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 0, false, false, false));
	    break;
	case SHEIKA:
	    if (isSpedUp)
		list.add(new PotionEffect(PotionEffectType.SPEED, 2147483647, 0, false, false, false));
	    break;
	default:
	    break;
	}
	return list;
    }

    private List<PotionEffect> getPotionsFromRings() {
	List<PotionEffect> potions = new ArrayList<>();
	List<Ring> rings = getRings();
	if (rings.contains(Ring.KOKIRI_RING))
	    potions.add(new PotionEffect(PotionEffectType.REGENERATION, 2147483647, 0, false, false));
	if (rings.contains(Ring.INVISIBILITY_RING))
	    potions.add(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0, false, false));
	if (rings.contains(Ring.ZORA_RING))
	    potions.add(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2147483647, 0, false, false));
	if (rings.contains(Ring.HASTE_RING_2))
	    potions.add(new PotionEffect(PotionEffectType.FAST_DIGGING, 2147483647, 1, false, false));
	else if (rings.contains(Ring.HASTE_RING_1))
	    potions.add(new PotionEffect(PotionEffectType.FAST_DIGGING, 2147483647, 0, false, false));
	return potions;
    }

    private void applyEffects(boolean resetEffects) {
	Player player = Bukkit.getPlayer(getUuid());

	clearEffects(resetEffects);

	Pair<PotionEffectType, Integer> biomeEffect = BiomeRegistry.getRaceFromBiome(currentBiome).contains(currentRace)
		&& currentRace != Race.KOKIRI ? new Pair<>(PotionEffectType.REGENERATION, Integer.valueOf(0)) : null;
	List<PotionEffect> ringEffects = getPotionsFromRings();
	List<PotionEffect> raceEffect = getPotionFromRace();
	List<PotionEffect> potions = new ArrayList<>();
	if (biomeEffect != null) {
	    boolean contained = false;
	    for (PotionEffect eff : ringEffects) {
		if (eff.getType().equals(biomeEffect.getFirst())) {
		    contained = true;
		    potions.add(
			    new PotionEffect(eff.getType(), eff.getDuration(), eff.getAmplifier() + 1, false, false));
		} else {
		    potions.add(eff);
		}
	    }
	    if (!contained)
		potions.add(
			new PotionEffect(biomeEffect.getFirst(), 2147483647, biomeEffect.getSecond(), false, false));
	} else {
	    for (PotionEffect eff : ringEffects) {
		potions.add(eff);
	    }
	}
	List<PotionEffect> potions1 = new ArrayList<>();
	if (!raceEffect.isEmpty()) {
	    for (PotionEffect eff : raceEffect) {
		boolean contained = false;
		int base = 0;
		for (PotionEffect eff2 : potions) {
		    if (eff.getType().equals(eff2.getType())) {
			contained = true;
			base = eff2.getAmplifier() + 1;
			break;
		    }
		}
		if (contained) {
		    potions1.add(new PotionEffect(eff.getType(), 2147483647, eff.getAmplifier() + base, false, false,
			    false));
		} else {
		    potions1.add(eff);
		}
	    }
	    for (PotionEffect eff : potions) {
		boolean contained = false;
		for (PotionEffect e : potions1) {
		    if (eff.getType().equals(e.getType())) {
			contained = true;
		    }
		}
		if (!contained)
		    potions1.add(eff);
	    }
	} else {
	    for (PotionEffect eff : potions) {
		potions1.add(eff);
	    }
	}
	if (!resetEffects) {
	    List<PotionEffect> potions2 = new ArrayList<>();
	    for (PotionEffectMemory eff : effects) {
		boolean contained = false;
		int base = 0;
		for (PotionEffect eff2 : potions1) {
		    if (eff.getType().equals(eff2.getType())) {
			contained = true;
			base = eff2.getAmplifier() + 1;
			break;
		    }
		}
		if (contained) {
		    potions2.add(new PotionEffect(eff.getType(), 2147483647, eff.getAmplifier() + base, false, false));
		} else {
		    potions2.add(eff.getPotionEffect());
		}
	    }
	    for (PotionEffect eff : potions1) {
		boolean contained = false;
		for (PotionEffect e : potions2) {
		    if (eff.getType().equals(e.getType())) {
			contained = true;
		    }
		}
		if (!contained)
		    potions2.add(eff);
	    }
	    potions1 = potions2;
	}
	for (PotionEffect effect : potions1) {
	    player.addPotionEffect(effect);
	}
    }

    public static void applyColors(Player player) {
	PlayerData playerData = getData(player);
	EntityManager.teamHandler.removePlayer(player);
	String color = "";
	switch (playerData.getCurrentRace()) {
	case GERUDO:
	    color = "§e";
	    break;
	case GORON:
	    color = "§4";
	    break;
	case HYLIA:
	    color = "§6";
	    break;
	case HYLIEN:
	    color = "§7";
	    break;
	case KOKIRI:
	    color = "§a";
	    break;
	case NONE:
	    color = "§f";
	    break;
	case PIAF:
	    color = "§b";
	    break;
	case SHEIKA:
	    color = "§5";
	    break;
	case TWILI:
	    color = "§8";
	    break;
	case ZORA:
	    color = "§9";
	    break;
	}
	player.setDisplayName(String.valueOf(color) + player.getName() + "§f");
	if (playerData.getCurrentRace() != Race.NONE)
	    EntityManager.teamHandler.addPlayer(player);
    }

    public int getBonusHealth() {
	return this.bonusHealth;
    }

    public void setBonusHealth(int bonusHealth) {
	this.bonusHealth = bonusHealth;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public List<Waypoint> getWaypoints() {
	return this.waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
	this.waypoints = waypoints;
	Main.write(String.valueOf(folderPrefix) + this.uuid, this);
    }

    public UUID getUuid() {
	return UUID.fromString(uuid);
    }

    public void setUuid(String uuid) {
	this.uuid = uuid;
	Main.write(String.valueOf(folderPrefix) + uuid, this);
    }

    public boolean hasDiscoveredStable(StableMemory stable) {
	UUID stableUUID = stable.getUuid();
	if (this.discoveredStables == null)
	    this.discoveredStables = new ArrayList<>();
	if (this.discoveredStables.contains(stableUUID))
	    return true;
	return false;
    }

    public void discoverStable(StableMemory stable) {
	UUID stableUUID = stable.getUuid();
	if (this.discoveredStables == null)
	    this.discoveredStables = new ArrayList<>();
	if (!this.discoveredStables.contains(stableUUID))
	    this.discoveredStables.add(stableUUID);
	stable.addDiscoveredPlayer(Bukkit.getPlayer(UUID.fromString(this.uuid)));
    }

    public void removeStable(StableMemory stable) {
	UUID stableUUID = stable.getUuid();
	if (this.discoveredStables == null)
	    this.discoveredStables = new ArrayList<>();
	if (this.discoveredStables.contains(stableUUID))
	    this.discoveredStables.remove(stableUUID);
    }

    public boolean isAttachedToWall() {
	return attachedToWall;
    }

    public void attachToWall() {
	this.attachedToWall = true;
    }

    public void detachFromWall() {
	this.attachedToWall = false;
    }

    public boolean isNoLeftClickHook() {
	return noLeftClickHook;
    }

    public void setNoLeftClickHook(boolean noLeftClickHook) {
	this.noLeftClickHook = noLeftClickHook;
    }

    public void deleteEffect(PotionEffect effect) {
	boolean contain = false;
	PotionEffectMemory e = null;
	for (PotionEffectMemory eff : effects) {
	    if (eff.getType() == effect.getType() && eff.getAmplifier() == effect.getAmplifier()) {
		contain = true;
		e = eff;
		break;
	    }
	}
	if (contain == true) {
	    effects.remove(e);
	}
    }

    public void addEffect(PotionEffect effect) {
	boolean contain = false;
	boolean redo = false;
	for (PotionEffectMemory eff : effects) {
	    if (eff.getType() == effect.getType() && eff.getAmplifier() >= effect.getAmplifier()) {
		contain = true;
		if (effect.getDuration() > eff.getDuration())
		    redo = true;
		break;
	    }
	}
	if (!contain) {
	    effects.add(new PotionEffectMemory(effect));
	    applyEffects(false);
	} else if (redo) {
	    deleteEffect(effect);
	    effects.add(new PotionEffectMemory(effect));
	}
    }

    public boolean isUnderwater() {
	return isUnderwater;
    }

    public int getSkyLight() {
	return skyLight;
    }

    private boolean setSkyLight() {
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	int newLight = p.getLocation().getBlock().getLightFromSky();
	boolean mustChange = false;
	if (this.currentRace == Race.ZORA) {
	    if (((newLight >= 4 && this.skyLight < 4) || (newLight < 4 && this.skyLight >= 4)) && isUnderwater()) {
		mustChange = true;
	    }
	}
	this.skyLight = newLight;
	return mustChange;
    }

    public int getBlockLight() {
	return blockLight;
    }

    private boolean setBlockLight() {
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	int newLight = p.getLocation().getBlock().getLightFromBlocks();
	boolean mustChange = false;
	if (this.currentRace == Race.TWILI) {
	    if ((newLight > 5 && this.blockLight <= 5) || (newLight <= 5 && this.blockLight > 5)) {
		mustChange = true;
	    }
	}
	this.blockLight = newLight;
	return mustChange;
    }

    private boolean gotInWater() {
	this.isUnderwater = true;
	if (this.currentRace == Race.ZORA)
	    return true;
	return false;
    }

    private boolean gotOutWater() {
	this.isUnderwater = false;
	if (this.currentRace == Race.ZORA)
	    return true;
	return false;
    }

    private void setVisible() {
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	for (Player player : Bukkit.getOnlinePlayers()) {
	    PlayerData PData = PlayerData.getData(player);
	    if (PData.getCurrentRace() != Race.TWILI)
		player.showPlayer(Main.getPlugin(Main.class), p);
	}
	this.isVisible = true;
    }

    private void setInvisible() {
	Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	for (Player player : Bukkit.getOnlinePlayers()) {
	    PlayerData PData = PlayerData.getData(player);
	    if (PData.getCurrentRace() != Race.TWILI)
		player.hidePlayer(Main.getPlugin(Main.class), p);
	}
	this.isVisible = false;
    }

    private void checkVisible() {
	if (currentRace == Race.TWILI) {
	    Player p = Bukkit.getPlayer(UUID.fromString(uuid));
	    if (((getSkyLight() > 7 && p.getLocation().getWorld().getTime() <= 12000) || (getBlockLight() > 5)
		    || (p.getInventory().getItemInMainHand().getType() == Material.SOUL_TORCH))
		    && p.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
		setInvisible();
	    } else {
		setVisible();
	    }
	} else {
	    this.isVisible = true;
	}
    }

    public static void checkApplyExtraEffect(Entity entity, Player damager) {
	PlayerData PData = PlayerData.getData(damager);
	if (PData.getCurrentRace() == Race.SHEIKA && PData.getBlockLight() <= 6 && PData.getSkyLight() <= 7) {
	    PotionEffect effect = new PotionEffect(PotionEffectType.WITHER, 240, 0);
	    if (entity instanceof LivingEntity)
		((LivingEntity) entity).addPotionEffect(effect);
	    if (entity instanceof Player) {
		PlayerData PData2 = PlayerData.getData((Player) entity);
		PData2.addEffect(effect);
	    }
	}
    }

    public Environment getCurrentDimension() {
	return currentDimension;
    }

    private boolean setCurrentDimension(Environment currentDimension) {
	this.currentDimension = currentDimension;
	if (currentRace == Race.SHEIKA) {
	    return true;
	}
	return false;
    }

    private boolean isAtNight() {
	Player p = Bukkit.getPlayer(getUuid());
	if (currentDimension == Environment.NORMAL) {
	    isAtNight = p.getWorld().getTime() > 12000;
	}
	return isAtNight;
    }

    private void checkSpeedUp() {
	if (currentRace != Race.SHEIKA && currentRace != Race.GERUDO) {
	    isSpedUp = false;
	    return;
	}
	boolean mustChange = false;
	if (currentRace == Race.SHEIKA) {
	    if ((!isAtNight && currentDimension == Environment.NORMAL) || currentDimension == Environment.NETHER) {
		mustChange = isSpedUp;
		isSpedUp = false;
	    } else if (((isAtNight && currentDimension == Environment.NORMAL)
		    || currentDimension == Environment.THE_END)) {
		mustChange = !isSpedUp;
		isSpedUp = true;
	    }
	} else {
	    if (isAtNight && currentDimension == Environment.NORMAL
		    && BiomeRegistry.getRaceFromBiome(currentBiome).contains(currentRace)) {
		mustChange = !isSpedUp;
		isSpedUp = true;
	    } else {
		mustChange = isSpedUp;
		isSpedUp = false;
	    }
	}
	if (mustChange)
	    applyEffects(false);
    }

    private boolean checkBiome() {
	Player p = Bukkit.getPlayer(getUuid());
	if (currentBiome != p.getLocation().getBlock().getBiome()) {
	    currentBiome = p.getLocation().getBlock().getBiome();
	    return true;
	}
	return false;
    }

    private void checkSeeNearbyEnemies() {
	if (currentRace != Race.KOKIRI || !BiomeRegistry.getRaceFromBiome(currentBiome).contains(Race.KOKIRI))
	    return;
	Player p = Bukkit.getPlayer(getUuid());
	List<org.bukkit.entity.Entity> entities = p.getNearbyEntities(30, 30, 30);
	for (org.bukkit.entity.Entity ent : entities) {
	    if (ent instanceof Monster) {
		double dist = ent.getLocation().distanceSquared(p.getLocation());
		CraftEntity cent = (CraftEntity) ent;
		net.minecraft.world.entity.Entity NMSEntity = cent.getHandle();
		DataWatcher watcher = new DataWatcher(NMSEntity);
		watcher.a(new DataWatcherObject<>(0, DataWatcherRegistry.a),
			Byte.valueOf(PacketManager.getMetaData(ent, (byte) (dist > 25 * 25 ? 0 : 64))));
		PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(NMSEntity.ae(), watcher, true);
		PacketManager.sendPacket(p, packet);
	    }
	}
    }

    public boolean checkTick(boolean dimensionCheck, Player p) {
	// Update Player Informations
	boolean mustChange = false;
	mustChange = mustChange || checkBiome();
	mustChange = mustChange || setSkyLight();
	mustChange = mustChange || setBlockLight();
	if (p.getEyeLocation().getBlock().getType() == Material.WATER && !isUnderwater())
	    mustChange = mustChange || gotInWater();
	if (p.getEyeLocation().getBlock().getType() != Material.WATER && isUnderwater())
	    mustChange = mustChange || gotOutWater();
	if (dimensionCheck)
	    mustChange = mustChange || setCurrentDimension(p.getWorld().getEnvironment());
	isAtNight();
	return mustChange;
    }

    public void tick(boolean force, boolean dimensionCheck, boolean reset) {
	Player p = Bukkit.getPlayer(getUuid());
	boolean mustChange = checkTick(dimensionCheck, p);
	// Apply Effects To Player
	checkSeeNearbyEnemies();
	checkVisible();
	checkSpeedUp();
	if (force || mustChange) {
	    applyEffects(false);
	}
    }

    public void respawn() {
	tick(true, true, true);
    }

    public void resetExtraEffects() {
	applyEffects(true);
    }
}
