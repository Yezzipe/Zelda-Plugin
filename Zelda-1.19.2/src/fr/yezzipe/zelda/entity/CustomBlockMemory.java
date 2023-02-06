package fr.yezzipe.zelda.entity;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class CustomBlockMemory {
    
    private int x;
    
    private int y;
    
    private int z;
    
    private String world;
    

    public CustomBlockMemory(Block block) {
	x = block.getX();
	y = block.getY();
	z = block.getZ();
	world = block.getWorld().getName();
    }
    
    public Block getBlock() {
	return Bukkit.getWorld(world).getBlockAt(x, y, z);
    }

}
