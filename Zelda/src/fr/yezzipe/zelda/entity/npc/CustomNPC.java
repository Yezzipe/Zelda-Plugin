package fr.yezzipe.zelda.entity.npc;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import fr.yezzipe.zelda.entity.npc.ai.PathfinderGoalMeleeAttackCustom;
import fr.yezzipe.zelda.entity.npc.ai.PathfinderGoalRandomStrollStayNear;

import java.lang.reflect.Field;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.BehaviorController;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMoveTowardsTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;
import org.bukkit.Location;

public class CustomNPC extends EntityVillager {
  public CustomNPC(EntityTypes<? extends EntityVillager> entitytypes, World world) {
    super(entitytypes, world);
    removeAI();
  }
  
  public static CustomNPC CustomGuardNPC(EntityTypes<? extends EntityVillager> entitytypes, World world, @Nullable Location loc) {
    CustomNPC npc = new CustomNPC(entitytypes, world);
    npc.bQ.a(9, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)npc, EntityHuman.class, 4.0F, 1.0F));
    npc.bQ.a(8, (PathfinderGoal)new PathfinderGoalRandomLookaround((EntityInsentient)npc));
    if (loc != null)
      npc.bQ.a(7, new PathfinderGoalRandomStrollStayNear((EntityCreature)npc, loc, 0.6D, 7.0D)); 
    npc.bQ.a(1, new PathfinderGoalMeleeAttackCustom((EntityCreature)npc, 0.6D, false));
    npc.bQ.a(2, (PathfinderGoal)new PathfinderGoalMoveTowardsTarget((EntityCreature)npc, 0.6D, 0.5F));
    npc.bR.a(1, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget<EntityInsentient>((EntityInsentient)npc, EntityInsentient.class, 0, true, true, entityliving -> entityliving instanceof net.minecraft.world.entity.monster.IMonster));
    return npc;
  }
  
  public static CustomNPC CustomStableNPC(EntityTypes<? extends EntityVillager> entitytypes, World world, @Nullable Location loc) {
    CustomNPC npc = new CustomNPC(entitytypes, world);
    npc.bQ.a(9, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)npc, EntityHuman.class, 4.0F, 1.0F));
    npc.bQ.a(8, (PathfinderGoal)new PathfinderGoalRandomLookaround((EntityInsentient)npc));
    if (loc != null)
      npc.bQ.a(7, new PathfinderGoalRandomStrollStayNear((EntityCreature)npc, loc, 0.6D, 7.0D)); 
    return npc;
  }
  
  private void removeAI() {
    try {
      Field availableGoalsField = PathfinderGoalSelector.class.getDeclaredField("d");
      Field priorityBehaviorsField = BehaviorController.class.getDeclaredField("f");
      Field coreActivitysField = BehaviorController.class.getDeclaredField("j");
      availableGoalsField.setAccessible(true);
      priorityBehaviorsField.setAccessible(true);
      coreActivitysField.setAccessible(true);
      availableGoalsField.set(this.bQ, Sets.newLinkedHashSet());
      availableGoalsField.set(this.bR, Sets.newLinkedHashSet());
      priorityBehaviorsField.set(du(), Maps.newTreeMap());
      coreActivitysField.set(du(), Sets.newHashSet());
    } catch (IllegalAccessException|NoSuchFieldException|IllegalArgumentException exception) {
      exception.printStackTrace();
    } 
  }
  
  public static Object getPrivateField(String fieldName, Class<?> clazz, Object object) {
    Object o = null;
    try {
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      o = field.get(object);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } 
    return o;
  }
}

