package fr.yezzipe.zelda.commands;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.territory.HTTPChunk;
import fr.yezzipe.zelda.territory.TerritoryChunk;
import fr.yezzipe.zelda.territory.TerritoryUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefreshChunksCommand implements CommandExecutor {
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	if (arg0 instanceof Player) {
	    Player p = (Player) arg0;
	    Chunk centerChunk = p.getLocation().getChunk();
	    int x = centerChunk.getX();
	    int z = centerChunk.getZ();
	    int r = 0;
	    if (arg3.length > 0) {
		Main.isInt(arg3[0]);
		r = Integer.parseInt(arg3[0]);
	    }
	    final List<TerritoryChunk> chunks = new ArrayList<>();
	    for (int i = -r; i < r + 1; i++) {
		for (int j = -r; j < r + 1; j++) {
		    Chunk c = centerChunk.getWorld().getChunkAt(x + i, z + j);
		    TerritoryChunk t = new TerritoryChunk(c);
		    chunks.add(t);
		}
	    }
	    (new Thread(new Runnable() {
		public void run() {
		    List<HTTPChunk> list = new ArrayList<>();
		    for (TerritoryChunk t : chunks) {
			HTTPChunk h = new HTTPChunk(t);
			list.add(h);
		    }
		    TerritoryUtil.sendMultipleChunks(list);
		}
	    })).start();
	    p.sendMessage("§bSuccess");
	}
	return false;
    }
}
