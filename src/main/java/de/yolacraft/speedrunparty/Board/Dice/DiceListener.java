package de.yolacraft.speedrunparty.Board.Dice;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import de.yolacraft.speedrunparty.SpeedrunParty;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DiceListener implements Listener {
    @EventHandler
    void onThrow(PlayerLaunchProjectileEvent event){
        if(SpeedrunParty.getMainManager().getServerState() == 1 && SpeedrunParty.getMainManager().getGameCycle() == 0){
            if(event.getProjectile().getType() == EntityType.SNOWBALL){
                SpeedrunParty.getMainController().getDice().setPlayersScore(event.getPlayer());
            }
        }
    }
}
