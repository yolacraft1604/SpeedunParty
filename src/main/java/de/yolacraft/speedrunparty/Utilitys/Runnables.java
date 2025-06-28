package de.yolacraft.speedrunparty.Utilitys;
import de.yolacraft.speedrunparty.SpeedrunParty;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnables {
    public static void runLater(long delayTicks, Runnable codeBlock) {
        new BukkitRunnable() {
            @Override
            public void run() {
                codeBlock.run();
            }
        }.runTaskLater(SpeedrunParty.getInstance(), delayTicks);
    }
    public static void runTimer(long delay, long frequency, Runnable codeBlock) {
        new BukkitRunnable() {
            @Override
            public void run() {
                codeBlock.run();
            }
        }.runTaskTimer(SpeedrunParty.getInstance(), delay, frequency);
    }
    public static void runAmount(long delay, long frequency, long Amount, Runnable codeBlock) {
        new BukkitRunnable() {

            int counter = 0;
            @Override
            public void run() {
                if(counter < Amount) {
                    codeBlock.run();
                }else{
                    this.cancel();
                }
                counter++;
            }
        }.runTaskTimer(SpeedrunParty.getInstance(), delay, frequency);
    }
}
