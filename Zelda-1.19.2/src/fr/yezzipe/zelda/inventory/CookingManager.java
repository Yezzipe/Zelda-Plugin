package fr.yezzipe.zelda.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class CookingManager extends InventoryManager {
    
    @SuppressWarnings("unused")
    private Player p;
    
    public CookingManager(Player p) {
	this.p = p;
	createInventory(null, 9, "Campfire", CustomInventoryType.COOKING);
	populateInventory();
    }

    protected void populateInventory() {
	// TODO Auto-generated method stub
	
    }
    
    public void handleClick(InventoryClickEvent e) {
	// TODO Auto-generated method stub
	
    }

    public void handleDrag(InventoryDragEvent e) {
	// TODO Auto-generated method stub
	
    }

    public void handleClose(InventoryCloseEvent e) {
	// TODO Auto-generated method stub
	
    }

}
