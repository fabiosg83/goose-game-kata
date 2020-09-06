package goose.game.fb.match;

/**
 *
 * @author fabio.sgroi
 */
public class Player {

    protected String playerName;
    protected int currentCell;

    public Player(String playerName) {
        this.playerName = playerName;
        this.currentCell = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public String toString() {
        return "Player{" + "playerName=" + playerName + ", currentCell=" + currentCell + '}';
    }

}
