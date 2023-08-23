package fr.picsou.narutoduel.components.listener.Player;

public class ListAskDuel {
    private String playerName;
    private String opponentName;

    private Integer time;

    public ListAskDuel(String playerName, String opponentName, Integer time) {
        this.playerName = playerName;
        this.opponentName = opponentName;
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponent(String opponentName) {
        this.opponentName = opponentName;
    }

}
