package de.yolacraft.speedrunparty.Board.Dice;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.Runnables;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DiceListener implements Listener {
    @EventHandler
    void onThrow(PlayerLaunchProjectileEvent event){
        if(SpeedrunParty.getMainManager().getServerState() == 1 && SpeedrunParty.getMainManager().getGameCycle() == 0){
            if(event.getProjectile().getType() == EntityType.SNOWBALL){
                if(event.getItemStack().equals(DiceStacks.GoldDice())){
                    SpeedrunParty.getMainController().getDice().setPlayersScoreAndMerge(event.getPlayer(), 1);
                }else if(event.getItemStack().equals(DiceStacks.SilverDice())){
                    SpeedrunParty.getMainController().getDice().setPlayersScoreAndMerge(event.getPlayer(), 2);
                }else if(event.getItemStack().equals(DiceStacks.BronzeDice())){
                    SpeedrunParty.getMainController().getDice().setPlayersScoreAndMerge(event.getPlayer(), 3);
                }else{
                    if(SpeedrunParty.getMainController().getFirst().contains(event.getPlayer())){
                        SpeedrunParty.getMainController().getDice().setPlayersScoreBuffer(event.getPlayer());
                        Runnables.runLater(16, () -> {
                            event.getPlayer().getInventory().setItem(4, DiceStacks.GoldDice());
                        });
                    }else if(SpeedrunParty.getMainController().getSecond().contains(event.getPlayer())){
                        SpeedrunParty.getMainController().getDice().setPlayersScoreBuffer(event.getPlayer());

                        Runnables.runLater(16, () -> {
                            event.getPlayer().getInventory().setItem(4, DiceStacks.SilverDice());
                        });
                    }else if(SpeedrunParty.getMainController().getThird().contains(event.getPlayer())){
                        SpeedrunParty.getMainController().getDice().setPlayersScoreBuffer(event.getPlayer());
                        Runnables.runLater(16, () -> {
                            event.getPlayer().getInventory().setItem(4, DiceStacks.BronzeDice());
                        });
                    }else{
                        SpeedrunParty.getMainController().getDice().setPlayersScore(event.getPlayer());
                    }
                }
            }
        }
    }
}
