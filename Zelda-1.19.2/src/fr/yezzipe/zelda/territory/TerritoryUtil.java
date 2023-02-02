package fr.yezzipe.zelda.territory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class TerritoryUtil {
  private static HttpClient client = HttpClient.newHttpClient();
  
  public static void sendMultipleChunks(List<HTTPChunk> chunks) {
    Gson GSON = (new GsonBuilder()).create();
    String s = GSON.toJson(chunks);
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/sendchunks")).POST(HttpRequest.BodyPublishers.ofString(s)).headers(new String[] { "Content-Type", "text/plain;charset=UTF-8" }).build();
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
  }
  
  public static void sendPlayerPositions() {
    (new Thread(new Runnable() {
          public void run() {
            List<HTTPPlayer> players = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
              HTTPPlayer player = new HTTPPlayer(p);
              players.add(player);
            } 
            Gson GSON = (new GsonBuilder()).create();
            String s = GSON.toJson(players);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/sendplayers")).POST(HttpRequest.BodyPublishers.ofString(s)).headers(new String[] { "Content-Type", "text/plain;charset=UTF-8" }).build();
            TerritoryUtil.client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
          }
        })).start();
  }
  
  public static void updatePlayerNearbyChunks() {
    List<Chunk> chunks = new ArrayList<>();
    int r = 5;
    for (Player p : Bukkit.getOnlinePlayers()) {
      Chunk centerChunk = p.getLocation().getChunk();
      int x = centerChunk.getX();
      int z = centerChunk.getZ();
      for (int i = -r; i < r + 1; i++) {
        for (int j = -r; j < r + 1; j++) {
          Chunk c = centerChunk.getWorld().getChunkAt(x + i, z + j);
          if (!chunks.contains(c))
            chunks.add(c); 
        } 
      } 
    } 
    final List<TerritoryChunk> Tchunks = new ArrayList<>();
    for (Chunk chunk : chunks) {
      TerritoryChunk t = new TerritoryChunk(chunk);
      Tchunks.add(t);
    } 
    (new Thread(new Runnable() {
          public void run() {
            List<HTTPChunk> list = new ArrayList<>();
            for (TerritoryChunk t : Tchunks) {
              HTTPChunk h = new HTTPChunk(t);
              list.add(h);
            } 
            TerritoryUtil.sendMultipleChunks(list);
          }
        })).start();
  }
}

