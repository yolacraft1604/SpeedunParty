package de.yolacraft.speedrunparty.Scoreboard;

import de.yolacraft.speedrunparty.Games.ResultType;
import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.PlayerScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;

public class MainScoreboard {

    private static final MainScoreboard instance = new MainScoreboard();

    public static MainScoreboard getInstance() {
        return instance;
    }

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int serverState = SpeedrunParty.getMainManager().getServerState();
            int gameCycle = SpeedrunParty.getMainManager().getGameCycle();

            if (serverState == 0) {
                if (hasObjective(player, "LobbyBoard")) {
                    updateLobbyScoreboard(player);
                } else {
                    createLobbyScoreboard(player);
                }
            } else if (serverState == 1 && gameCycle == 0) {
                if (hasObjective(player, "BoardBoard")) {
                    updateBoardScoreboard(player);
                } else {
                    createBoardScoreboard(player);
                }
            } else if (serverState == 1 && gameCycle == 1) {
                if (hasObjective(player, "IngameBoard")) {
                    updateGameScoreboard(player);
                } else {
                    createGameScoreboard(player);
                }
            }
        }
    }

    private boolean hasObjective(Player player, String name) {
        return player.getScoreboard() != null
                && player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null
                && player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equals(name);
    }

    // ---------------- LOBBY ----------------

    private void createLobbyScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("LobbyBoard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.LIGHT_PURPLE + SpeedrunParty.getInstance().getName());

        obj.getScore(ChatColor.AQUA + "").setScore(5);
        obj.getScore(ChatColor.YELLOW + "Lobby Phase").setScore(4);

        Team players = board.registerNewTeam("players");
        String playersKey = ChatColor.GRAY.toString();
        players.addEntry(playersKey);
        obj.getScore(playersKey).setScore(3);

        obj.getScore(ChatColor.BLACK + "").setScore(2);

        Team waiting = board.registerNewTeam("waiting");
        String waitingKey = ChatColor.DARK_PURPLE.toString();
        waiting.addEntry(waitingKey);
        obj.getScore(waitingKey).setScore(1);
        obj.getScore(ChatColor.GOLD + "").setScore(0);

        player.setScoreboard(board);
        updateLobbyScoreboard(player);
    }

    private void updateLobbyScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();

        Team players = board.getTeam("players");
        players.setPrefix("Players: ");
        players.setSuffix(ChatColor.YELLOW + String.valueOf(SpeedrunParty.getPlayerStorage().getLen()) +
                ChatColor.WHITE + "/" + ChatColor.YELLOW + Bukkit.getMaxPlayers());

        Team waiting = board.getTeam("waiting");
        int rem = SpeedrunParty.getGameStart().getRemainingSeconds();
        if (rem >= 0) {
            waiting.setSuffix(ChatColor.GRAY + "Starting in: " + ChatColor.YELLOW + rem + " Seconds");
        } else {
            waiting.setSuffix(ChatColor.GRAY + "Waiting for Players...");
        }
    }

    // ---------------- DICE ----------------

    private void createBoardScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("BoardBoard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.DARK_PURPLE + SpeedrunParty.getInstance().getName());

        obj.getScore(ChatColor.BLACK + "").setScore(12);

        Team count = board.registerNewTeam("count");
        String countKey = ChatColor.YELLOW.toString();
        count.addEntry(countKey);
        obj.getScore(countKey).setScore(11);

        obj.getScore(ChatColor.RED + "").setScore(10);

        Team place1 = board.registerNewTeam("place1");
        String place1Key = ChatColor.DARK_RED + "";
        place1.addEntry(place1Key);
        obj.getScore(place1Key).setScore(9);

        Team place2 = board.registerNewTeam("place2");
        String place2Key = ChatColor.RED + "";
        place2.addEntry(place2Key);
        obj.getScore(place2Key).setScore(8);

        Team place3 = board.registerNewTeam("place3");
        String place3Key = ChatColor.GOLD + "";
        place3.addEntry(place3Key);
        obj.getScore(place3Key).setScore(7);

        Team place4 = board.registerNewTeam("place4");
        String place4Key = ChatColor.YELLOW + "";
        place4.addEntry(place4Key);
        obj.getScore(place4Key).setScore(6);

        Team place5 = board.registerNewTeam("place5");
        String place5Key = ChatColor.GREEN + "";
        place5.addEntry(place5Key);
        obj.getScore(place5Key).setScore(5);

        Team place6 = board.registerNewTeam("place6");
        String place6Key = ChatColor.DARK_GREEN + "";
        place6.addEntry(place6Key);
        obj.getScore(place6Key).setScore(4);

        Team place7 = board.registerNewTeam("place7");
        String place7Key = ChatColor.AQUA + "";
        place7.addEntry(place7Key);
        obj.getScore(place7Key).setScore(3);

        Team place8 = board.registerNewTeam("place8");
        String place8Key = ChatColor.DARK_AQUA + "";
        place8.addEntry(place8Key);
        obj.getScore(place8Key).setScore(2);

        Team place9 = board.registerNewTeam("place9");
        String place9Key = ChatColor.BLUE + "";
        place9.addEntry(place9Key);
        obj.getScore(place9Key).setScore(1);

        Team more = board.registerNewTeam("more");
        String moreKey = ChatColor.BLACK + "" + ChatColor.RESET;
        more.addEntry(moreKey);
        obj.getScore(moreKey).setScore(0);

        player.setScoreboard(board);
        updateBoardScoreboard(player);
    }


    private void updateBoardScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();
        Team count = board.getTeam("count");
        count.setPrefix(ChatColor.GRAY + "Player: ");
        count.setSuffix(ChatColor.YELLOW + "" + SpeedrunParty.getPlayerStorage().getLen() +
                ChatColor.GRAY + "/" + ChatColor.YELLOW + Bukkit.getMaxPlayers());

        Team more = board.getTeam("more");
        if(Bukkit.getOnlinePlayers().size() > 9) {
            more.setSuffix(ChatColor.GRAY + "and " + (Bukkit.getOnlinePlayers().size()-9) + " more");
        }else{
            more.setSuffix(ChatColor.GRAY + "");
        }

        for(int i = 0; i < 9; i++) {
            Team team = board.getTeam("place" + (i+1));
            if(Bukkit.getOnlinePlayers().size() > i) {
                team.setSuffix(ChatColor.YELLOW + "" + (i+1) + ". " + ChatColor.GRAY +
                        SpeedrunParty.getMainController().getData().get(i).getPlayer().getDisplayName() + " " +
                        ChatColor.YELLOW + SpeedrunParty.getMainController().getData().get(i).getScore());
            }else{
                team.setSuffix(ChatColor.YELLOW + "" + (i+1) + ". ");
            }
        }

    }

    // ---------------- GAME ----------------

    private void createGameScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("IngameBoard", "dummy", ChatColor.DARK_PURPLE + SpeedrunParty.getInstance().getName());
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(ChatColor.AQUA + " ").setScore(9);



        Team currentGame = board.registerNewTeam("currentGame");
        String gameKey = ChatColor.DARK_PURPLE + "";
        currentGame.addEntry(gameKey);
        obj.getScore(gameKey).setScore(8);

        Team timeGame = board.registerNewTeam("timeGame");
        String timeKey = ChatColor.DARK_BLUE + "";
        timeGame.addEntry(timeKey);
        obj.getScore(timeKey).setScore(7);

        obj.getScore(ChatColor.RED + " ").setScore(6);

        Team place1 = board.registerNewTeam("place1");
        String place1Key = ChatColor.DARK_RED + "";
        place1.addEntry(place1Key);
        obj.getScore(place1Key).setScore(5);

        Team place2 = board.registerNewTeam("place2");
        String place2Key = ChatColor.RED + "";
        place2.addEntry(place2Key);
        obj.getScore(place2Key).setScore(4);

        Team place3 = board.registerNewTeam("place3");
        String place3Key = ChatColor.GOLD + "";
        place3.addEntry(place3Key);
        obj.getScore(place3Key).setScore(3);

        Team place4 = board.registerNewTeam("place4");
        String place4Key = ChatColor.YELLOW + "";
        place4.addEntry(place4Key);
        obj.getScore(place4Key).setScore(2);

        Team place5 = board.registerNewTeam("place5");
        String place5Key = ChatColor.GREEN + "";
        place5.addEntry(place5Key);
        obj.getScore(place5Key).setScore(1);

        obj.getScore(ChatColor.BLACK + " ").setScore(0);

        player.setScoreboard(board);
        updateGameScoreboard(player);
    }


    private void updateGameScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();

        Team currentGame = board.getTeam("currentGame");
        if(SpeedrunParty.getGameClusterRoot().getTitle() != null){
            currentGame.setSuffix(ChatColor.GRAY + "Current Game: " + ChatColor.YELLOW + SpeedrunParty.getGameClusterRoot().getTitle());
        }else{
            currentGame.setSuffix(ChatColor.GRAY + "Current Game: " + ChatColor.YELLOW + "waiting...");
        }
        Team timeGame = board.getTeam("timeGame");
        if(SpeedrunParty.getGameClusterRoot().getRemainingTime() > -1) {
            timeGame.setSuffix(ChatColor.GRAY + "Time Remaining: " + ChatColor.YELLOW + SpeedrunParty.getGameClusterRoot().getRemainingTime() + ChatColor.GRAY + "s");
        }else{
            timeGame.setSuffix(ChatColor.GRAY + "Time Remaining: " + ChatColor.YELLOW + "0");
        }

        for (int i = 0; i < 5; i++){
            if(Bukkit.getOnlinePlayers().size() > i) {
                Team team = board.getTeam("place" + (i+1));
                List<ResultType> scores = SpeedrunParty.getGameClusterRoot().getScores();
                if (scores != null && i < scores.size()) {
                    ResultType entry = scores.get(i);
                    if (entry != null) {
                        team.setSuffix(ChatColor.YELLOW + "" + SpeedrunParty.getGameClusterRoot().getScores().get(i).getPlacement() + ". " + ChatColor.GRAY + SpeedrunParty.getGameClusterRoot().getScores().get(i).getPlayer().getDisplayName()
                                + " " + ChatColor.YELLOW + SpeedrunParty.getGameClusterRoot().getScores().get(i).getScore());

                    }else {
                        team.setSuffix(ChatColor.YELLOW + "" + (i + 1) + ". ");
                    }
                }
            }
        }
    }
}
