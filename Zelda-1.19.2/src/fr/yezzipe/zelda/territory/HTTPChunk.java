/* Decompiler 24079ms, total 24714ms, lines 411 */
package fr.yezzipe.zelda.territory;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;

import fr.yezzipe.zelda.entity.enums.Race;

public class HTTPChunk {
   private final Race race;
   private final int x;
   private final int z;
   private final String world;
   private final List<HTTPBlock> blocks;

   public HTTPChunk(TerritoryChunk chunk) {
      this.race = chunk.getOwningRace();
      this.x = chunk.getX();
      this.z = chunk.getZ();
      this.world = chunk.getWorld();
      Chunk c = Bukkit.getWorld(chunk.getWorld()).getChunkAt(this.x, this.z);
      ChunkSnapshot s = c.getChunkSnapshot(true, true, true);
      List<HTTPBlock> blocks = new ArrayList<HTTPBlock>();

      for(int i = 0; i < 16; ++i) {
         for(int k = 0; k < 16; ++k) {
            for(int j = 255; j >= 0; --j) {
               Material mat = s.getBlockType(i, j, k);
               if (this.isValid(mat)) {
                  HTTPBlock b = new HTTPBlock(mat, s.getBiome(i, j, k), i, j, k);
                  blocks.add(b);
                  break;
               }
            }
         }
      }

      this.blocks = blocks;
   }

   private boolean isValid(final Material type) {
       switch (type) {
           case AIR:
           case OAK_SAPLING:
           case SPRUCE_SAPLING:
           case BIRCH_SAPLING:
           case JUNGLE_SAPLING:
           case ACACIA_SAPLING:
           case DARK_OAK_SAPLING:
           case GLASS:
           case TINTED_GLASS:
           case GRASS:
           case FERN:
           case DEAD_BUSH:
           case SEA_PICKLE:
           case DANDELION:
           case POPPY:
           case BLUE_ORCHID:
           case ALLIUM:
           case AZURE_BLUET:
           case RED_TULIP:
           case ORANGE_TULIP:
           case WHITE_TULIP:
           case PINK_TULIP:
           case OXEYE_DAISY:
           case CORNFLOWER:
           case LILY_OF_THE_VALLEY:
           case WITHER_ROSE:
           case SPORE_BLOSSOM:
           case BROWN_MUSHROOM:
           case RED_MUSHROOM:
           case CRIMSON_FUNGUS:
           case WARPED_FUNGUS:
           case CRIMSON_ROOTS:
           case WARPED_ROOTS:
           case NETHER_SPROUTS:
           case WEEPING_VINES:
           case TWISTING_VINES:
           case HANGING_ROOTS:
           case BIG_DRIPLEAF:
           case SMALL_DRIPLEAF:
           case BAMBOO:
           case TORCH:
           case END_ROD:
           case LADDER:
           case OAK_FENCE:
           case SPRUCE_FENCE:
           case BIRCH_FENCE:
           case JUNGLE_FENCE:
           case ACACIA_FENCE:
           case DARK_OAK_FENCE:
           case CRIMSON_FENCE:
           case WARPED_FENCE:
           case SOUL_TORCH:
           case IRON_BARS:
           case CHAIN:
           case GLASS_PANE:
           case VINE:
           case GLOW_LICHEN:
           case LILY_PAD:
           case NETHER_BRICK_FENCE:
           case SUNFLOWER:
           case LILAC:
           case ROSE_BUSH:
           case PEONY:
           case TALL_GRASS:
           case LARGE_FERN:
           case WHITE_STAINED_GLASS:
           case ORANGE_STAINED_GLASS:
           case MAGENTA_STAINED_GLASS:
           case LIGHT_BLUE_STAINED_GLASS:
           case YELLOW_STAINED_GLASS:
           case LIME_STAINED_GLASS:
           case PINK_STAINED_GLASS:
           case GRAY_STAINED_GLASS:
           case LIGHT_GRAY_STAINED_GLASS:
           case CYAN_STAINED_GLASS:
           case PURPLE_STAINED_GLASS:
           case BLUE_STAINED_GLASS:
           case BROWN_STAINED_GLASS:
           case GREEN_STAINED_GLASS:
           case RED_STAINED_GLASS:
           case BLACK_STAINED_GLASS:
           case WHITE_STAINED_GLASS_PANE:
           case ORANGE_STAINED_GLASS_PANE:
           case MAGENTA_STAINED_GLASS_PANE:
           case LIGHT_BLUE_STAINED_GLASS_PANE:
           case YELLOW_STAINED_GLASS_PANE:
           case LIME_STAINED_GLASS_PANE:
           case PINK_STAINED_GLASS_PANE:
           case GRAY_STAINED_GLASS_PANE:
           case LIGHT_GRAY_STAINED_GLASS_PANE:
           case CYAN_STAINED_GLASS_PANE:
           case PURPLE_STAINED_GLASS_PANE:
           case BLUE_STAINED_GLASS_PANE:
           case BROWN_STAINED_GLASS_PANE:
           case GREEN_STAINED_GLASS_PANE:
           case RED_STAINED_GLASS_PANE:
           case BLACK_STAINED_GLASS_PANE:
           case TURTLE_EGG:
           case TUBE_CORAL:
           case BRAIN_CORAL:
           case BUBBLE_CORAL:
           case FIRE_CORAL:
           case HORN_CORAL:
           case DEAD_BRAIN_CORAL:
           case DEAD_BUBBLE_CORAL:
           case DEAD_FIRE_CORAL:
           case DEAD_HORN_CORAL:
           case DEAD_TUBE_CORAL:
           case TUBE_CORAL_FAN:
           case BRAIN_CORAL_FAN:
           case BUBBLE_CORAL_FAN:
           case FIRE_CORAL_FAN:
           case HORN_CORAL_FAN:
           case DEAD_TUBE_CORAL_FAN:
           case DEAD_BRAIN_CORAL_FAN:
           case DEAD_BUBBLE_CORAL_FAN:
           case DEAD_FIRE_CORAL_FAN:
           case DEAD_HORN_CORAL_FAN:
           case CONDUIT:
           case REDSTONE:
           case REDSTONE_TORCH:
           case REPEATER:
           case COMPARATOR:
           case LEVER:
           case LIGHTNING_ROD:
           case TRIPWIRE_HOOK:
           case STONE_BUTTON:
           case POLISHED_BLACKSTONE_BUTTON:
           case OAK_BUTTON:
           case SPRUCE_BUTTON:
           case BIRCH_BUTTON:
           case JUNGLE_BUTTON:
           case ACACIA_BUTTON:
           case DARK_OAK_BUTTON:
           case CRIMSON_BUTTON:
           case WARPED_BUTTON:
           case STONE_PRESSURE_PLATE:
           case POLISHED_BLACKSTONE_PRESSURE_PLATE:
           case LIGHT_WEIGHTED_PRESSURE_PLATE:
           case HEAVY_WEIGHTED_PRESSURE_PLATE:
           case OAK_PRESSURE_PLATE:
           case SPRUCE_PRESSURE_PLATE:
           case BIRCH_PRESSURE_PLATE:
           case JUNGLE_PRESSURE_PLATE:
           case ACACIA_PRESSURE_PLATE:
           case DARK_OAK_PRESSURE_PLATE:
           case CRIMSON_PRESSURE_PLATE:
           case WARPED_PRESSURE_PLATE:
           case IRON_DOOR:
           case OAK_DOOR:
           case SPRUCE_DOOR:
           case BIRCH_DOOR:
           case JUNGLE_DOOR:
           case ACACIA_DOOR:
           case DARK_OAK_DOOR:
           case CRIMSON_DOOR:
           case WARPED_DOOR:
           case IRON_TRAPDOOR:
           case OAK_TRAPDOOR:
           case SPRUCE_TRAPDOOR:
           case BIRCH_TRAPDOOR:
           case JUNGLE_TRAPDOOR:
           case ACACIA_TRAPDOOR:
           case DARK_OAK_TRAPDOOR:
           case CRIMSON_TRAPDOOR:
           case WARPED_TRAPDOOR:
           case OAK_FENCE_GATE:
           case SPRUCE_FENCE_GATE:
           case BIRCH_FENCE_GATE:
           case JUNGLE_FENCE_GATE:
           case ACACIA_FENCE_GATE:
           case DARK_OAK_FENCE_GATE:
           case CRIMSON_FENCE_GATE:
           case WARPED_FENCE_GATE:
           case RAIL:
           case ACTIVATOR_RAIL:
           case STRUCTURE_BLOCK:
           case WHEAT:
           case OAK_SIGN:
           case SPRUCE_SIGN:
           case BIRCH_SIGN:
           case JUNGLE_SIGN:
           case ACACIA_SIGN:
           case DARK_OAK_SIGN:
           case CRIMSON_SIGN:
           case WARPED_SIGN:
           case CAKE:
           case FLOWER_POT:
           case SKELETON_SKULL:
           case WITHER_SKELETON_SKULL:
           case PLAYER_HEAD:
           case ZOMBIE_HEAD:
           case CREEPER_HEAD:
           case DRAGON_HEAD:
           case WHITE_BANNER:
           case ORANGE_BANNER:
           case MAGENTA_BANNER:
           case LIGHT_BLUE_BANNER:
           case YELLOW_BANNER:
           case LIME_BANNER:
           case PINK_BANNER:
           case GRAY_BANNER:
           case LIGHT_GRAY_BANNER:
           case CYAN_BANNER:
           case PURPLE_BANNER:
           case BLUE_BANNER:
           case BROWN_BANNER:
           case GREEN_BANNER:
           case RED_BANNER:
           case BLACK_BANNER:
           case LANTERN:
           case SOUL_LANTERN:
           case CANDLE:
           case WHITE_CANDLE:
           case ORANGE_CANDLE:
           case MAGENTA_CANDLE:
           case LIGHT_BLUE_CANDLE:
           case YELLOW_CANDLE:
           case LIME_CANDLE:
           case PINK_CANDLE:
           case GRAY_CANDLE:
           case LIGHT_GRAY_CANDLE:
           case CYAN_CANDLE:
           case PURPLE_CANDLE:
           case BLUE_CANDLE:
           case BROWN_CANDLE:
           case GREEN_CANDLE:
           case RED_CANDLE:
           case BLACK_CANDLE:
           case SMALL_AMETHYST_BUD:
           case MEDIUM_AMETHYST_BUD:
           case LARGE_AMETHYST_BUD:
           case AMETHYST_CLUSTER:
           case POINTED_DRIPSTONE:
           case FIRE:
           case SOUL_FIRE:
           case OAK_WALL_SIGN:
           case SPRUCE_WALL_SIGN:
           case BIRCH_WALL_SIGN:
           case ACACIA_WALL_SIGN:
           case JUNGLE_WALL_SIGN:
           case DARK_OAK_WALL_SIGN:
           case PUMPKIN_STEM:
           case MELON_STEM:
           case TRIPWIRE:
           case POTTED_OAK_SAPLING:
           case POTTED_SPRUCE_SAPLING:
           case POTTED_BIRCH_SAPLING:
           case POTTED_JUNGLE_SAPLING:
           case POTTED_ACACIA_SAPLING:
           case POTTED_DARK_OAK_SAPLING:
           case POTTED_FERN:
           case POTTED_DANDELION:
           case POTTED_POPPY:
           case POTTED_BLUE_ORCHID:
           case POTTED_ALLIUM:
           case POTTED_AZURE_BLUET:
           case POTTED_RED_TULIP:
           case POTTED_ORANGE_TULIP:
           case POTTED_WHITE_TULIP:
           case POTTED_PINK_TULIP:
           case POTTED_OXEYE_DAISY:
           case POTTED_CORNFLOWER:
           case POTTED_LILY_OF_THE_VALLEY:
           case POTTED_WITHER_ROSE:
           case POTTED_RED_MUSHROOM:
           case POTTED_BROWN_MUSHROOM:
           case POTTED_DEAD_BUSH:
           case POTTED_CACTUS:
           case CARROTS:
           case POTATOES:
           case WHITE_WALL_BANNER:
           case ORANGE_WALL_BANNER:
           case MAGENTA_WALL_BANNER:
           case LIGHT_BLUE_WALL_BANNER:
           case YELLOW_WALL_BANNER:
           case LIME_WALL_BANNER:
           case PINK_WALL_BANNER:
           case GRAY_WALL_BANNER:
           case LIGHT_GRAY_WALL_BANNER:
           case CYAN_WALL_BANNER:
           case PURPLE_WALL_BANNER:
           case BLUE_WALL_BANNER:
           case BROWN_WALL_BANNER:
           case GREEN_WALL_BANNER:
           case RED_WALL_BANNER:
           case BLACK_WALL_BANNER:
           case BEETROOTS:
           case END_GATEWAY:
           case DEAD_TUBE_CORAL_WALL_FAN:
           case DEAD_BRAIN_CORAL_WALL_FAN:
           case DEAD_BUBBLE_CORAL_WALL_FAN:
           case DEAD_FIRE_CORAL_WALL_FAN:
           case DEAD_HORN_CORAL_WALL_FAN:
           case TUBE_CORAL_WALL_FAN:
           case BRAIN_CORAL_WALL_FAN:
           case BUBBLE_CORAL_WALL_FAN:
           case FIRE_CORAL_WALL_FAN:
           case HORN_CORAL_WALL_FAN:
           case BAMBOO_SAPLING:
           case POTTED_BAMBOO:
           case VOID_AIR:
           case CAVE_AIR:
           case SWEET_BERRY_BUSH:
           case WEEPING_VINES_PLANT:
           case TWISTING_VINES_PLANT:
           case CRIMSON_WALL_SIGN:
           case WARPED_WALL_SIGN:
           case POTTED_CRIMSON_FUNGUS:
           case POTTED_WARPED_FUNGUS:
           case POTTED_CRIMSON_ROOTS:
           case POTTED_WARPED_ROOTS:
           case CANDLE_CAKE:
           case WHITE_CANDLE_CAKE:
           case ORANGE_CANDLE_CAKE:
           case MAGENTA_CANDLE_CAKE:
           case LIGHT_BLUE_CANDLE_CAKE:
           case YELLOW_CANDLE_CAKE:
           case LIME_CANDLE_CAKE:
           case PINK_CANDLE_CAKE:
           case GRAY_CANDLE_CAKE:
           case LIGHT_GRAY_CANDLE_CAKE:
           case CYAN_CANDLE_CAKE:
           case PURPLE_CANDLE_CAKE:
           case BLUE_CANDLE_CAKE:
           case BROWN_CANDLE_CAKE:
           case GREEN_CANDLE_CAKE:
           case RED_CANDLE_CAKE:
           case BLACK_CANDLE_CAKE:
           case CAVE_VINES:
           case BIG_DRIPLEAF_STEM:
           case POTTED_AZALEA_BUSH:
           case POTTED_FLOWERING_AZALEA_BUSH: {
               return false;
           }
           default: {
               return true;
           }
       }
   }

   public Race getRace() {
      return this.race;
   }

   public int getX() {
      return this.x;
   }

   public int getZ() {
      return this.z;
   }

   public String getWorld() {
      return this.world;
   }

   public List<HTTPBlock> getBlocks() {
      return this.blocks;
   }
}
