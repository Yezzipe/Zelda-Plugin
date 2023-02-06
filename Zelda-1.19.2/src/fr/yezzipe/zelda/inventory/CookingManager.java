package fr.yezzipe.zelda.inventory;

import org.bukkit.entity.Player;

public class CookingManager extends InventoryManager {
    
    private Player p;
    
    public CookingManager(Player p) {
	this.p = p;
	createInventory(null, 9, "Campfire", CustomInventoryType.COOKING);
	populateInventory();
    }

}
