package de.yolacraft.speedrunparty.Games;

import org.bukkit.entity.Player;

public class ResultType {
    Player player;
    int placement;
    int score;
    public ResultType(Player player, int placement, int score){
        this.placement = placement;
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
