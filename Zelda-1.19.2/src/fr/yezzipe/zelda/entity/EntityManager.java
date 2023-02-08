package fr.yezzipe.zelda.entity;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.yezzipe.zelda.entity.player.TeamHandler;

public class EntityManager {
    public static NoCollisionHandler noCollisionHandler;

    public static TeamHandler teamHandler;

    public static Scoreboard board;

    public static void init() {
	ScoreboardManager sm = Bukkit.getScoreboardManager();
	board = sm.getNewScoreboard();
	noCollisionHandler = new NoCollisionHandler(board);
	teamHandler = new TeamHandler(board);
    }
}
