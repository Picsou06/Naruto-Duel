package fr.picsou.narutoduel.components.list;

public class PlayerInDuel {
    private String playerName;
    private String opponentName;

    public PlayerInDuel(String playerName, String opponentName) {
        this.playerName = playerName;
        this.opponentName = opponentName;
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
