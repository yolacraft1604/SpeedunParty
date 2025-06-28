package de.yolacraft.speedrunparty.Utilitys;

import de.yolacraft.speedrunparty.Games.ResultType;
import de.yolacraft.speedrunparty.SpeedrunParty;

import java.util.ArrayList;

public class CreateEmptyResults {
    public static ArrayList<ResultType> getEmptyResults(){
        ArrayList<ResultType> Results = new ArrayList<>();
        SpeedrunParty.getPlayerStorage().getPlayers().forEach(player -> {
            Results.add(new ResultType(player, 0, 0));
        });
        return(Results);
    }
}
