/* Decompiler 23887ms, total 24619ms, lines 1824 */
package fr.yezzipe.zelda.territory.structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed.Part;

public class PaletteManager {

   public static void handleBed(Location loc, StructureType type, Block targetBlock, Part bedPart) {
      Biome biome = loc.getBlock().getBiome();
      switch(biome) {
      case NETHER_WASTES:
      case SOUL_SAND_VALLEY:
      case CRIMSON_FOREST:
      case WARPED_FOREST:
      case BASALT_DELTAS:
         if (bedPart == Part.HEAD) {
            targetBlock.setType(Material.WHITE_CARPET);
         } else {
            targetBlock.setType(Material.RED_CARPET);
         }
         break;
      case THE_END:
      case SMALL_END_ISLANDS:
      case END_MIDLANDS:
      case END_HIGHLANDS:
      case END_BARRENS:
      case THE_VOID:
         if (bedPart == Part.HEAD) {
            targetBlock.setType(Material.WHITE_CARPET);
         } else {
            targetBlock.setType(Material.BLACK_CARPET);
         }
         break;
      default:
		break;
      }

   }

   public static Material get(Location loc, StructureType type, Material mat, Block targetBlock) {
      Biome biome = loc.getBlock().getBiome();
      Block below;
      Double r;
      double d;
      switch(biome) {
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
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.OAK_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.STRIPPED_BIRCH_WOOD;
            }

            return Material.BIRCH_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.BIRCH_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.BIRCH_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.BIRCH_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.CYAN_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.LIGHT_BLUE_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.BLUE_TERRACOTTA;
               }

               return Material.LIGHT_GRAY_WOOL;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.STRIPPED_SPRUCE_WOOD;
         }

         return Material.SPRUCE_PLANKS;
      case PLAINS:
      case FOREST:
      case RIVER:
      case WINDSWEPT_FOREST:
      case FLOWER_FOREST:
      case MEADOW:
      case GROVE:
      case CUSTOM:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.OAK_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.STRIPPED_OAK_WOOD;
            }

            return Material.OAK_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat == Material.EMERALD_BLOCK || mat == Material.OXIDIZED_CUT_COPPER || mat == Material.SEA_LANTERN) {
            r = Math.random() * 100.0D;
            return r <= 20.0D ? Material.STRIPPED_SPRUCE_WOOD : Material.SPRUCE_PLANKS;
         }

         if (mat == Material.LAPIS_BLOCK) {
            below = targetBlock.getRelative(BlockFace.DOWN);
            if (below.getType() == Material.AIR) {
               return Material.AIR;
            }

            if (below.getType() == Material.BARREL) {
               r = Math.random() * 100.0D;
               if (r <= 50.0D) {
                  return Material.BARREL;
               }

               return Material.AIR;
            }

            if (below.getType() == Material.HAY_BLOCK) {
               r = Math.random() * 100.0D;
               if (r <= 50.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            r = Math.random() * 100.0D;
            if (r <= 30.0D) {
               return Material.BARREL;
            }

            if (r <= 60.0D) {
               return Material.HAY_BLOCK;
            }

            return Material.AIR;
         }

         if (mat == Material.IRON_BLOCK) {
            d = Math.random() * 100.0D;
            if (d <= 10.0D) {
               return Material.CRAFTING_TABLE;
            }

            if (d <= 20.0D) {
               return Material.FLETCHING_TABLE;
            }

            if (d <= 30.0D) {
               return Material.SMITHING_TABLE;
            }

            return Material.AIR;
         }

         if (mat == Material.OAK_STAIRS) {
            return Material.OAK_STAIRS;
         }

         if (mat == Material.OAK_FENCE) {
            return Material.OAK_FENCE;
         }

         if (mat == Material.OAK_FENCE_GATE) {
            return Material.OAK_FENCE_GATE;
         }

         if (mat == Material.WHITE_BED) {
            return Material.GREEN_BED;
         }

         if (mat == Material.DIAMOND_BLOCK) {
            d = Math.random() * 100.0D;
            if (d <= 25.0D) {
               return Material.BROWN_TERRACOTTA;
            }

            if (d <= 75.0D) {
               return Material.GREEN_TERRACOTTA;
            }

            return Material.GREEN_WOOL;
         }

         if (mat == Material.WAXED_COPPER_BLOCK) {
            below = targetBlock.getRelative(BlockFace.DOWN);
            if (below.getType() == Material.AIR) {
               return Material.AIR;
            }

            if (below.getType() == Material.BOOKSHELF) {
               r = Math.random() * 100.0D;
               if (r <= 50.0D) {
                  return Material.BOOKSHELF;
               }

               return Material.AIR;
            }

            return Material.BOOKSHELF;
         }
         break;
      case DESERT:
      case BADLANDS:
      case WOODED_BADLANDS:
      case ERODED_BADLANDS:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.STRIPPED_BIRCH_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 70.0D) {
               return Material.SMOOTH_SANDSTONE;
            }

            return Material.SMOOTH_RED_SANDSTONE;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.BIRCH_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.BIRCH_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.BIRCH_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.YELLOW_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.YELLOW_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.ORANGE_TERRACOTTA;
               }

               return Material.YELLOW_WOOL;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.STRIPPED_OAK_WOOD;
         }

         return Material.OAK_PLANKS;
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
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.SPRUCE_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.STRIPPED_SPRUCE_WOOD;
            }

            return Material.SPRUCE_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.SPRUCE_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.SPRUCE_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.SPRUCE_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.LIGHT_BLUE_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.WHITE_WOOL;
               }

               if (d <= 75.0D) {
                  return Material.LIGHT_GRAY_WOOL;
               }

               return Material.LIGHT_BLUE_TERRACOTTA;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.STRIPPED_DARK_OAK_WOOD;
         }

         return Material.DARK_OAK_PLANKS;
      case TAIGA:
      case SWAMP:
      case MANGROVE_SWAMP:
      case MUSHROOM_FIELDS:
      case DARK_FOREST:
      case OLD_GROWTH_PINE_TAIGA:
      case OLD_GROWTH_SPRUCE_TAIGA:
      case DRIPSTONE_CAVES:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.DARK_OAK_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 30.0D) {
               return Material.STRIPPED_DARK_OAK_WOOD;
            }

            return Material.DARK_OAK_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.DARK_OAK_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.DARK_OAK_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.DARK_OAK_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.GRAY_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.BROWN_WOOL;
               }

               if (d <= 75.0D) {
                  return Material.BROWN_TERRACOTTA;
               }

               return Material.GRAY_WOOL;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.BLACK_TERRACOTTA;
         }

         return Material.GRAY_TERRACOTTA;
      case NETHER_WASTES:
      case SOUL_SAND_VALLEY:
      case CRIMSON_FOREST:
      case WARPED_FOREST:
      case BASALT_DELTAS:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.STRIPPED_CRIMSON_STEM;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.CRIMSON_HYPHAE;
            }

            return Material.NETHER_BRICKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.CRYING_OBSIDIAN) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.CRYING_OBSIDIAN;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.CRYING_OBSIDIAN;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.CRIMSON_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.CRIMSON_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.CRIMSON_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.WHITE_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 40.0D) {
                  return Material.RED_TERRACOTTA;
               }

               if (d <= 70.0D) {
                  return Material.BROWN_WOOL;
               }

               return Material.BLACK_TERRACOTTA;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.BROWN_TERRACOTTA;
         }

         return Material.GRAY_TERRACOTTA;
      case THE_END:
      case SMALL_END_ISLANDS:
      case END_MIDLANDS:
      case END_HIGHLANDS:
      case END_BARRENS:
      case THE_VOID:
      case DEEP_DARK:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.PURPUR_PILLAR;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.SMOOTH_SANDSTONE;
            }

            return Material.END_STONE_BRICKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.CRYING_OBSIDIAN) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.CRYING_OBSIDIAN;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.CRYING_OBSIDIAN;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.CRIMSON_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.CRIMSON_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.CRIMSON_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.WHITE_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.MAGENTA_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.PURPLE_TERRACOTTA;
               }

               return Material.WHITE_TERRACOTTA;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.END_STONE_BRICKS;
         }

         return Material.SMOOTH_SANDSTONE;
      case JUNGLE:
      case SPARSE_JUNGLE:
      case BAMBOO_JUNGLE:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.JUNGLE_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.STRIPPED_JUNGLE_WOOD;
            }

            return Material.JUNGLE_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.JUNGLE_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.JUNGLE_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.JUNGLE_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.BROWN_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.LIME_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.GREEN_TERRACOTTA;
               }

               return Material.GREEN_WOOL;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.STRIPPED_ACACIA_WOOD;
         }

         return Material.ACACIA_PLANKS;
      case BIRCH_FOREST:
      case SUNFLOWER_PLAINS:
      case OLD_GROWTH_BIRCH_FOREST:
      case LUSH_CAVES:
         if (type != StructureType.STABLE1 && type != StructureType.STABLE2 && type != StructureType.STABLE3) {
            break;
         }

         if (mat == Material.REDSTONE_BLOCK) {
            return Material.OAK_LOG;
         }

         if (mat == Material.GOLD_BLOCK) {
            r = Math.random() * 100.0D;
            if (r <= 20.0D) {
               return Material.STRIPPED_BIRCH_WOOD;
            }

            return Material.BIRCH_PLANKS;
         }

         if (mat == Material.COAL_BLOCK) {
            return targetBlock.getType();
         }

         if (mat == Material.NETHERITE_BLOCK) {
            return Material.AIR;
         }

         if (mat != Material.EMERALD_BLOCK && mat != Material.OXIDIZED_CUT_COPPER && mat != Material.SEA_LANTERN) {
            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.BIRCH_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.BIRCH_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.BIRCH_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.GREEN_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.LIGHT_GRAY_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.GREEN_TERRACOTTA;
               }

               return Material.GREEN_WOOL;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
            break;
         }

         r = Math.random() * 100.0D;
         if (r <= 20.0D) {
            return Material.STRIPPED_SPRUCE_WOOD;
         }

         return Material.SPRUCE_PLANKS;
      case SAVANNA:
      case SAVANNA_PLATEAU:
      case WINDSWEPT_SAVANNA:
         if (type == StructureType.STABLE1 || type == StructureType.STABLE2 || type == StructureType.STABLE3) {
            if (mat == Material.REDSTONE_BLOCK) {
               return Material.ACACIA_LOG;
            }

            if (mat == Material.GOLD_BLOCK) {
               r = Math.random() * 100.0D;
               if (r <= 40.0D) {
                  return Material.STRIPPED_ACACIA_WOOD;
               }

               return Material.SMOOTH_RED_SANDSTONE;
            }

            if (mat == Material.COAL_BLOCK) {
               return targetBlock.getType();
            }

            if (mat == Material.NETHERITE_BLOCK) {
               return Material.AIR;
            }

            if (mat == Material.EMERALD_BLOCK || mat == Material.OXIDIZED_CUT_COPPER || mat == Material.SEA_LANTERN) {
               r = Math.random() * 100.0D;
               if (r <= 20.0D) {
                  return Material.STRIPPED_OAK_WOOD;
               }

               return Material.SMOOTH_SANDSTONE;
            }

            if (mat == Material.LAPIS_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BARREL) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BARREL;
                  }

                  return Material.AIR;
               }

               if (below.getType() == Material.HAY_BLOCK) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.HAY_BLOCK;
                  }

                  return Material.AIR;
               }

               r = Math.random() * 100.0D;
               if (r <= 30.0D) {
                  return Material.BARREL;
               }

               if (r <= 60.0D) {
                  return Material.HAY_BLOCK;
               }

               return Material.AIR;
            }

            if (mat == Material.IRON_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 10.0D) {
                  return Material.CRAFTING_TABLE;
               }

               if (d <= 20.0D) {
                  return Material.FLETCHING_TABLE;
               }

               if (d <= 30.0D) {
                  return Material.SMITHING_TABLE;
               }

               return Material.AIR;
            }

            if (mat == Material.OAK_STAIRS) {
               return Material.ACACIA_STAIRS;
            }

            if (mat == Material.OAK_FENCE) {
               return Material.ACACIA_FENCE;
            }

            if (mat == Material.OAK_FENCE_GATE) {
               return Material.ACACIA_FENCE_GATE;
            }

            if (mat == Material.WHITE_BED) {
               return Material.ORANGE_BED;
            }

            if (mat == Material.DIAMOND_BLOCK) {
               d = Math.random() * 100.0D;
               if (d <= 25.0D) {
                  return Material.GRAY_TERRACOTTA;
               }

               if (d <= 75.0D) {
                  return Material.ORANGE_TERRACOTTA;
               }

               return Material.YELLOW_TERRACOTTA;
            }

            if (mat == Material.WAXED_COPPER_BLOCK) {
               below = targetBlock.getRelative(BlockFace.DOWN);
               if (below.getType() == Material.AIR) {
                  return Material.AIR;
               }

               if (below.getType() == Material.BOOKSHELF) {
                  r = Math.random() * 100.0D;
                  if (r <= 50.0D) {
                     return Material.BOOKSHELF;
                  }

                  return Material.AIR;
               }

               return Material.BOOKSHELF;
            }
         }
      }

      return null;
   }

   public static boolean isBed(final Material newMat) {
       switch (newMat) {
           case WHITE_BED:
           case ORANGE_BED:
           case MAGENTA_BED:
           case LIGHT_BLUE_BED:
           case LIME_BED:
           case PINK_BED:
           case GRAY_BED:
           case LIGHT_GRAY_BED:
           case CYAN_BED:
           case PURPLE_BED:
           case BLUE_BED:
           case BROWN_BED:
           case GREEN_BED:
           case RED_BED:
           case BLACK_BED: {
               return true;
           }
           default: {
               return false;
           }
       }
   }

   public static boolean canBed(Location origin) {
      Biome biome = origin.getBlock().getBiome();
      switch(biome) {
      case NETHER_WASTES:
      case THE_END:
      case SMALL_END_ISLANDS:
      case END_MIDLANDS:
      case END_HIGHLANDS:
      case END_BARRENS:
      case THE_VOID:
      case SOUL_SAND_VALLEY:
      case CRIMSON_FOREST:
      case WARPED_FOREST:
      case BASALT_DELTAS:
         return false;
      default:
         return true;
      }
   }
}