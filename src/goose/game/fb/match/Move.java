package goose.game.fb.match;

import goose.game.fb.board.Board;
import goose.game.fb.board.CellType;

/**
 *
 * @author fabio.sgroi
 */
public class Move {

    protected Integer previousCell;
    protected Integer currentCell;
    protected String previousCellLabel;
    protected String currentCellLabel;
    protected Integer valueDice1;
    protected Integer valueDice2;
    protected CellType cellType;
    protected String additionalMessage;

    public Move(Integer previousCell, Integer valueDice1, Integer valueDice2, String playerName) {

        int destPosition = previousCell + valueDice1 + valueDice2;
        this.cellType = Board.getCellType(destPosition);

        this.previousCell = previousCell;
        this.currentCell = (destPosition > 63) ? (63 - (destPosition - 63)) : destPosition;

        this.previousCellLabel = (previousCell == 0) ? "Start" : String.valueOf(previousCell);
        this.currentCellLabel = (destPosition > 63) ? "63" : String.valueOf(destPosition);

        this.valueDice1 = valueDice1;
        this.valueDice2 = valueDice2;

        switch (cellType) {
            case WIN:
                additionalMessage = String.format(" %s Wins!!", playerName);
                break;
            case BOUNCES:
                additionalMessage = String.format(" %s bounces! %s returns to %s", playerName, playerName, String.valueOf(currentCell));
                break;
            case BRIDGE:
                this.currentCell = 12;
                this.currentCellLabel = "The Bridge";
                additionalMessage = String.format(" %s jumps to 12", playerName);
                break;
            case GOOSE:
                this.currentCellLabel = String.format("%d, The Goose", currentCell);
                break;
            default:
                additionalMessage = "";
        }
    }

    public Integer getPreviousCell() {
        return previousCell;
    }

    public Integer getCurrentCell() {
        return currentCell;
    }

    public String getPreviousCellLabel() {
        return previousCellLabel;
    }

    public String getCurrentCellLabel() {
        return currentCellLabel;
    }

    public Integer getValueDice1() {
        return valueDice1;
    }

    public Integer getValueDice2() {
        return valueDice2;
    }

    public CellType getCellType() {
        return cellType;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    @Override
    public String toString() {
        return "[" + previousCell + " -> " + currentCell + " - {" + previousCellLabel + ":" + currentCellLabel + "} Dice1=" + valueDice1 + ", Dice2=" + valueDice2
                + ", cellType=" + cellType + " - MSG:{" + additionalMessage + "}" + "]";
    }

}
