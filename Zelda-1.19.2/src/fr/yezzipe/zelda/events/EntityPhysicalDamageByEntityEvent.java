package fr.yezzipe.zelda.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityPhysicalDamageByEntityEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final EntityDamageByEntityEvent event;

    private final Entity entity;

    private final Entity attacker;

    public HandlerList getHandlers() {
	return HANDLERS;
    }

    public static HandlerList getHandlerList() {
	return HANDLERS;
    }

    public EntityPhysicalDamageByEntityEvent(EntityDamageByEntityEvent event, Entity entity, Entity attacker) {
	this.event = event;
	this.entity = entity;
	this.attacker = attacker;
    }

    public Entity getEntity() {
	return this.entity;
    }

    public EntityDamageEvent getEvent() {
	return (EntityDamageEvent) this.event;
    }

    public void setDamage(double damage) {
	this.event.setDamage(damage);
    }

    public double getDamage() {
	return this.event.getDamage();
    }

    public Entity getAttacker() {
	return this.attacker;
    }
}
