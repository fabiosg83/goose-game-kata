package goose.game.fb.test;

import goose.game.fb.board.Board;
import goose.game.fb.board.CellType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabio.sgroi
 */
public class BoardTest {

    @Test
    public void cellTypeWinTest() {
        CellType cellType = Board.getCellType(63);
        assertEquals(cellType, CellType.WIN);
    }

    @Test
    public void cellTypeBridgeTest() {
        CellType cellType = Board.getCellType(6);
        assertEquals(cellType, CellType.BRIDGE);
    }

    @Test
    public void cellTypeBouncesTest() {
        CellType cellType = Board.getCellType(65);
        assertEquals(cellType, CellType.BOUNCES);
    }

    @Test
    public void cellTypeGooseTest() {
        CellType cellType = Board.getCellType(5);
        assertEquals(cellType, CellType.GOOSE);
    }

    @Test
    public void cellTypeNormalTest() {
        CellType cellType = Board.getCellType(7);
        assertEquals(cellType, CellType.NORMAL);
    }
}
