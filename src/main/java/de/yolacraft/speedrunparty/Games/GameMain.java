package de.yolacraft.speedrunparty.Games;

import java.util.List;

public interface GameMain {
    void start();
    void boot();
    int getRemainingTime();
    String getName();
    List<ResultType> getResults();
    String getWorld();
}
