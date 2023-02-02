package fr.yezzipe.zelda.inventory;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.items.RupeeBuilder;
import fr.yezzipe.zelda.items.enums.Rupees;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StableManager extends InventoryManager {
  public static HashMap<UUID, StableMemory> stables = new HashMap<>();
  
  public static void populateStableTeleport(Inventory inv, Player p, StableMemory stable, int page) {
    UUID uuid = InventoryUUID.get(inv);
    stables.put(uuid, stable);
    List<StableMemory> list = StableMemory.getAllStables();
    PlayerData PData = PlayerData.getData(p);
    list.remove(stable);
    byte b;
    int j;
    StableMemory[] arrayOfStableMemory;
    for (j = (arrayOfStableMemory = list.<StableMemory>toArray(new StableMemory[list.size()])).length, b = 0; b < j; ) {
      StableMemory s = arrayOfStableMemory[b];
      if (!PData.hasDiscoveredStable(s))
        list.remove(s); 
      b++;
    } 
    for (int i = 0; i < 54; i++) {
      if (i < 45) {
        if (i + page * 45 < list.size()) {
          StableMemory s = list.get(i + page * 45);
          ItemStack item = new ItemStack(getBed(s.getBiome()));
          NBTItem nbt = new NBTItem(item);
          nbt.setString("StableUUID", s.getUuid().toString());
          item = nbt.getItem();
          inv.setItem(i, item);
        } else {
          inv.setItem(i, new ItemStack(Material.AIR));
        } 
      } else {
        switch (i) {
          case 45:
            if (stable.isOwner(p)) {
              ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
              NBTItem nbt = new NBTItem(item);
              nbt.setString("StableMenu", "OwnerMenu");
              item = nbt.getItem();
              inv.setItem(i, item);
              break;
            } 
            inv.setItem(i, new ItemStack(Material.AIR));
            break;
          case 47:
            if (page > 0) {
              ItemStack bed = new ItemStack(Material.WHITE_BED);
              NBTItem nbt = new NBTItem(bed);
              nbt.setString("StableMenu", "Teleportation");
              nbt.setInteger("StableTPPage", Integer.valueOf(page - 1));
              bed = nbt.getItem();
              inv.setItem(i, bed);
              break;
            } 
            inv.setItem(i, new ItemStack(Material.AIR));
            break;
          case 51:
            if (45 + page * 45 < list.size()) {
              ItemStack bed = new ItemStack(Material.WHITE_BED);
              NBTItem nbt = new NBTItem(bed);
              nbt.setString("StableMenu", "Teleportation");
              nbt.setInteger("StableTPPage", Integer.valueOf(page + 1));
              bed = nbt.getItem();
              inv.setItem(i, bed);
              break;
            } 
            inv.setItem(i, new ItemStack(Material.AIR));
            break;
        } 
      } 
    } 
    p.openInventory(inv);
  }
  
  public static void populateStableOwnerMenu(Inventory inv, Player p, StableMemory stable) {
    UUID uuid = InventoryUUID.get(inv);
    stables.put(uuid, stable);
    for (int i = 0; i < 54; i++) {
      ItemStack green;
      NBTItem nbt;
      ItemStack bed;
      ItemStack scaffolding;
      ItemStack air;
      switch (i) {
        case 10:
          green = RupeeBuilder.build(Rupees.GREEN);
          nbt = new NBTItem(green);
          nbt.setString("StableMenu", "Rupees");
          green = nbt.getItem();
          inv.setItem(i, green);
          break;
        case 12:
          bed = new ItemStack(Material.WHITE_BED);
          nbt = new NBTItem(bed);
          nbt.setString("StableMenu", "Teleportation");
          nbt.setInteger("StableTPPage", Integer.valueOf(0));
          bed = nbt.getItem();
          inv.setItem(12, bed);
          break;
        case 14:
          scaffolding = new ItemStack(Material.SCAFFOLDING);
          nbt = new NBTItem(scaffolding);
          nbt.setString("StableMenu", "Upgrades");
          scaffolding = nbt.getItem();
          inv.setItem(14, scaffolding);
          break;
        default:
          air = new ItemStack(Material.AIR);
          inv.setItem(i, air);
          break;
      } 
    } 
    p.openInventory(inv);
  }
  
  public static boolean isStable(Inventory inv) {
    UUID uuid = InventoryUUID.get(inv);
    if (uuid == null)
      return false; 
    if (UUIDTypes.get(uuid) != CustomInventoryType.STABLE)
      return false; 
    return true;
  }
  
  public static void handleClick(InventoryClickEvent e) {
    e.setCancelled(true);
    ItemStack item = e.getCurrentItem();
    if (item != null && 
      item.getType() != Material.AIR) {
      NBTItem nbt = new NBTItem(item);
      if (nbt.getKeys().contains("StableUUID")) {
        String uuidString = nbt.getString("StableUUID");
        UUID uuid = UUID.fromString(uuidString);
        UUID mainUUID = InventoryUUID.get(e.getInventory());
        StableMemory mainStable = stables.get(mainUUID);
        StableMemory stable = (StableMemory)StableMemory.Stables.get(uuid);
        ItemStack gold = RupeeBuilder.build(Rupees.GOLD);
        ItemStack silver = RupeeBuilder.build(Rupees.SILVER);
        ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
        ItemStack red = RupeeBuilder.build(Rupees.RED);
        ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
        ItemStack blue = RupeeBuilder.build(Rupees.BLUE);
        ItemStack green = RupeeBuilder.build(Rupees.GREEN);
        Player p = (Player)e.getWhoClicked();
        if (mainStable.isOwner(p)) {
          stable.teleport(p);
        } else {
          boolean tp = false;
          if (p.getInventory().containsAtLeast(green, 10)) {
            green.setAmount(10);
            p.getInventory().removeItem(new ItemStack[] { green });
            tp = true;
          } else if (p.getInventory().containsAtLeast(green, 5) && p.getInventory().containsAtLeast(blue, 1)) {
            green.setAmount(5);
            p.getInventory().removeItem(new ItemStack[] { green });
            p.getInventory().removeItem(new ItemStack[] { blue });
            tp = true;
          } else if (p.getInventory().containsAtLeast(blue, 2)) {
            blue.setAmount(2);
            p.getInventory().removeItem(new ItemStack[] { blue });
            tp = true;
          } else if (p.getInventory().containsAtLeast(yellow, 1)) {
            p.getInventory().remove(yellow);
            tp = true;
          } else if (p.getInventory().containsAtLeast(red, 1)) {
            p.getInventory().removeItem(new ItemStack[] { red });
            p.getInventory().addItem(new ItemStack[] { yellow });
            tp = true;
          } else if (p.getInventory().containsAtLeast(purple, 1)) {
            p.getInventory().removeItem(new ItemStack[] { purple });
            red.setAmount(2);
            p.getInventory().addItem(new ItemStack[] { red });
            tp = true;
          } else if (p.getInventory().containsAtLeast(silver, 1)) {
            p.getInventory().removeItem(new ItemStack[] { silver });
            p.getInventory().addItem(new ItemStack[] { purple });
            red.setAmount(2);
            p.getInventory().addItem(new ItemStack[] { red });
            tp = true;
          } else if (p.getInventory().containsAtLeast(gold, 1)) {
            p.getInventory().removeItem(new ItemStack[] { gold });
            silver.setAmount(2);
            p.getInventory().addItem(new ItemStack[] { silver });
            p.getInventory().addItem(new ItemStack[] { purple });
            red.setAmount(2);
            p.getInventory().addItem(new ItemStack[] { red });
            tp = true;
          } 
          if (tp) {
            stable.teleport(p);
            mainStable.addRupees(3);
          } else {
            p.sendMessage("Enough Rupees !");
          } 
        } 
      } else if (nbt.getKeys().contains("StableMenu")) {
        Player p = (Player)e.getWhoClicked();
        UUID mainUUID = InventoryUUID.get(e.getInventory());
        StableMemory mainStable = stables.get(mainUUID);
        String menu = nbt.getString("StableMenu");
        if (menu.equals("Teleportation")) {
          int page = nbt.getInteger("StableTPPage").intValue();
          populateStableTeleport(e.getInventory(), p, mainStable, page);
        } else if (menu.equals("OwnerMenu")) {
          populateStableOwnerMenu(e.getInventory(), p, mainStable);
        } else if (menu.equals("Rupees")) {
          int rupees = mainStable.getRupees();
          int rest = 0;
          if (rupees >= 300) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 300;
            int amount = (rupees - rest) / 300;
            ItemStack rupee = RupeeBuilder.build(Rupees.GOLD);
            rupee.setAmount(amount);
            rupees -= amount * 300;
            mainStable.removeRupees(amount * 300);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees >= 100) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 100;
            int amount = (rupees - rest) / 100;
            ItemStack rupee = RupeeBuilder.build(Rupees.SILVER);
            rupee.setAmount(amount);
            rupees -= amount * 100;
            mainStable.removeRupees(amount * 100);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees >= 50) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 50;
            int amount = (rupees - rest) / 50;
            ItemStack rupee = RupeeBuilder.build(Rupees.PURPLE);
            rupee.setAmount(amount);
            rupees -= amount * 50;
            mainStable.removeRupees(amount * 50);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees >= 20) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 20;
            int amount = (rupees - rest) / 20;
            ItemStack rupee = RupeeBuilder.build(Rupees.RED);
            rupee.setAmount(amount);
            rupees -= amount * 20;
            mainStable.removeRupees(amount * 20);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees >= 10) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 10;
            int amount = (rupees - rest) / 10;
            ItemStack rupee = RupeeBuilder.build(Rupees.YELLOW);
            rupee.setAmount(amount);
            rupees -= amount * 10;
            mainStable.removeRupees(amount * 10);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees >= 5) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 5;
            int amount = (rupees - rest) / 5;
            ItemStack rupee = RupeeBuilder.build(Rupees.BLUE);
            rupee.setAmount(amount);
            rupees -= amount * 5;
            mainStable.removeRupees(amount * 5);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
          if (rupees > 1) {
            if (p.getInventory().firstEmpty() == -1)
              return; 
            rest = rupees % 1;
            int amount = (rupees - rest) / 1;
            ItemStack rupee = RupeeBuilder.build(Rupees.GREEN);
            rupee.setAmount(amount);
            rupees -= amount * 1;
            mainStable.removeRupees(amount);
            p.getInventory().addItem(new ItemStack[] { rupee });
            if (p.getInventory().firstEmpty() == -1)
              return; 
          } 
        } 
      } 
    } 
  }
  
  private static Material getBed(Biome biome) {
    switch (biome) {
      case WINDSWEPT_HILLS:
      case FROZEN_RIVER:
      case SNOWY_PLAINS:
      case SNOWY_TAIGA:
      case WINDSWEPT_GRAVELLY_HILLS:
      case ICE_SPIKES:
      case SNOWY_SLOPES:
      case FROZEN_PEAKS:
      case JAGGED_PEAKS:
      case STONY_PEAKS:
        return Material.LIGHT_BLUE_BED;
      case OCEAN:
      case FROZEN_OCEAN:
      case BEACH:
      case DEEP_OCEAN:
      case STONY_SHORE:
      case SNOWY_BEACH:
      case WARM_OCEAN:
      case LUKEWARM_OCEAN:
      case COLD_OCEAN:
      case DEEP_LUKEWARM_OCEAN:
      case DEEP_COLD_OCEAN:
      case DEEP_FROZEN_OCEAN:
        return Material.CYAN_BED;
      case TAIGA:
      case SWAMP:
      case MUSHROOM_FIELDS:
      case DARK_FOREST:
      case OLD_GROWTH_PINE_TAIGA:
      case OLD_GROWTH_SPRUCE_TAIGA:
      case DRIPSTONE_CAVES:
      case MANGROVE_SWAMP:
        return Material.GRAY_BED;
      case PLAINS:
      case FOREST:
      case RIVER:
      case BIRCH_FOREST:
      case WINDSWEPT_FOREST:
      case SUNFLOWER_PLAINS:
      case FLOWER_FOREST:
      case OLD_GROWTH_BIRCH_FOREST:
      case LUSH_CAVES:
      case MEADOW:
      case GROVE:
      case CUSTOM:
        return Material.GREEN_BED;
      case DESERT:
      case BADLANDS:
      case WOODED_BADLANDS:
      case ERODED_BADLANDS:
        return Material.YELLOW_BED;
      case SAVANNA:
      case SAVANNA_PLATEAU:
      case WINDSWEPT_SAVANNA:
        return Material.ORANGE_BED;
      case JUNGLE:
      case SPARSE_JUNGLE:
      case BAMBOO_JUNGLE:
        return Material.BROWN_BED;
      case NETHER_WASTES:
      case SOUL_SAND_VALLEY:
      case CRIMSON_FOREST:
      case WARPED_FOREST:
      case BASALT_DELTAS:
        return Material.RED_BED;
      case THE_END:
      case SMALL_END_ISLANDS:
      case END_MIDLANDS:
      case END_HIGHLANDS:
      case END_BARRENS:
      case THE_VOID:
      case DEEP_DARK:
        return Material.BLACK_BED;
    default:
	break;
    } 
    return Material.WHITE_BED;
  }
}

