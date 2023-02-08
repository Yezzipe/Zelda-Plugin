package fr.yezzipe.zelda.inventory;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.yezzipe.zelda.Registry;

public abstract class InventoryManager {
   
    protected UUID uuid;
    
    protected Inventory inv;
    
    protected CustomInventoryType type;
    
    private static Registry<Inventory, InventoryManager> registry = new Registry<Inventory, InventoryManager>();

    
    
    public Inventory createInventory(InventoryHolder holder, int size, String title, CustomInventoryType type) {
	Inventory inv = Bukkit.createInventory(holder, size, title);
	this.uuid = UUID.randomUUID();
	this.inv = inv;
	this.type = type;
	registry.bind(inv, this);
	return inv;
    }
    
    public void closeInventory() {
	registry.unbind(inv);
    }

    public static ItemStack guiItemBuilder(Material material, String displayName) {
	ItemStack item = new ItemStack(material);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);
	return item;
    }

    protected abstract void populateInventory();
    
    public UUID getUUID() {
	return uuid;
    }
    
    public Inventory getInventory() {
	return inv;
    }
    
    public CustomInventoryType getType() {
	return type;
    }
    
    public static boolean isCustomInventory(Inventory inv) {
	return registry.keys().contains(inv);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends InventoryManager> T getManager(Inventory inv) {
	return (T) registry.get(inv);
    }
    
    public abstract void handleClick(InventoryClickEvent e);
    
    public abstract void handleDrag(InventoryDragEvent e);
    
    public abstract void handleClose(InventoryCloseEvent e);
    

}
