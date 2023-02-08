package fr.yezzipe.zelda.entity.npc;

import fr.yezzipe.zelda.territory.Waypoint;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NPCMemory {
    public static List<NPCMemory> NPCMemories = new ArrayList<>();

    public static HashMap<UUID, NPCMemory> NPCUUIDToMemory = new HashMap<>();

    private final Waypoint waypoint;

    private final String skin;

    private final String name;

    private UUID uuid;

    private boolean hasStable;

    private UUID stable;

    public NPCMemory(Waypoint waypoint, String name, String skin) {
	this.waypoint = waypoint;
	this.name = name;
	this.skin = skin;
	this.uuid = UUID.randomUUID();
	NPCMemories.add(this);
	NPCUUIDToMemory.put(this.uuid, this);
    }

    public void register() {
	if (!NPCMemories.contains(this))
	    NPCMemories.add(this);
	if (getUuid() == null)
	    setUuid(UUID.randomUUID());
	if (!NPCUUIDToMemory.containsKey(getUuid()))
	    NPCUUIDToMemory.put(this.uuid, this);
    }

    public void unregister() {
	if (NPCMemories.contains(this))
	    NPCMemories.remove(this);
	if (NPCUUIDToMemory.containsKey(getUuid()))
	    NPCUUIDToMemory.remove(this.uuid);
    }

    public Waypoint getWaypoint() {
	return this.waypoint;
    }

    public String getSkin() {
	return this.skin;
    }

    public String getName() {
	return this.name;
    }

    public UUID getUuid() {
	return this.uuid;
    }

    public void setUuid(UUID uuid) {
	this.uuid = uuid;
    }

    public StableMemory getStable() {
	StableMemory memory = (StableMemory) StableMemory.Stables.get(this.stable);
	return memory;
    }

    public void setStable(StableMemory stable) {
	this.stable = stable.getUuid();
	this.hasStable = true;
    }

    public void removeStable() {
	this.stable = null;
	this.hasStable = false;
    }

    public boolean hasStable() {
	return this.hasStable;
    }

    public boolean isMainNPC() {
	if (this.hasStable) {
	    StableMemory memory = (StableMemory) StableMemory.Stables.get(this.stable);
	    NPCMemory npcMemory = memory.getMainNPCMemory();
	    if (npcMemory.getUuid().equals(this.uuid))
		return true;
	    return false;
	}
	return false;
    }
}
