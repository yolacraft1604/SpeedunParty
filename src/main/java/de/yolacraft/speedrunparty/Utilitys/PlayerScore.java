package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.entity.Player;

public class PlayerScore{
    Player player;
    int Score;
    public PlayerScore(Player p, int i){
        this.Score = i;
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
