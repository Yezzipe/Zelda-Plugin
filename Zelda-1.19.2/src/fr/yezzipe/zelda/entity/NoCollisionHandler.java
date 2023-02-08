package fr.yezzipe.zelda.entity;

import java.util.UUID;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NoCollisionHandler {
    private Team team;

    public NoCollisionHandler(Scoreboard board) {
	this.team = board.registerNewTeam("no_collision");
	this.team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
    }

    public void addEntity(UUID uniqueId) {
	this.team.addEntry(uniqueId.toString());
    }
}
