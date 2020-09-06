package goose.game.fb.test;

import goose.game.fb.match.Move;
import goose.game.fb.play.Game;
import goose.game.fb.match.PlayersService;
import goose.game.fb.match.Turn;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabio.sgroi
 */
public class MovePlayerTest {

    PlayersService players = new PlayersService();
    Game game = new Game();

    public MovePlayerTest() {
        players.addPlayer("add player Pippo");
        players.addPlayer("add player Pluto");
        game.newMatch(players.getPlayers());
    }

    @Test
    public void movePlayerPippoFromStartTo5Test() {
        assertEquals(game.moveString("move Pippo 3, 1", false, null, null), "Pippo rolls 3, 1. Pippo moves from Start to 4.");
    }

    @Test
    public void movePlayerPippoFromStartTo4Test() {
        assertEquals(game.moveString("move Pippo 2, 2", false, null, null), "Pippo rolls 2, 2. Pippo moves from Start to 4.");
    }

    @Test
    public void movePlayerPippoFromStartTo6Test() {
        assertNotEquals(game.moveString("move Pippo 4, 2", false, null, null), "Pippo rolls 4, 2. Pippo moves from Start to 6.");
    }

    @Test
    public void movePlayerPippoFrom6To11Test() {
        game.move("move Pippo 3, 1", false, null, null);
        assertEquals(game.moveString("move Pippo 3, 3", false, null, null), "Pippo rolls 3, 3. Pippo moves from 4 to 10.");
    }

    @Test
    public void movePippoWinTest() {
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        assertEquals(game.moveString("move Pippo 1, 2", false, null, null), "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }

    @Test
    public void movePippoBouncesTest() {
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 6, 6", false, null, null);
        assertEquals(game.moveString("move Pippo 3, 2", false, null, null), "Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61");
    }

    @Test
    public void movePippoTheBridgeTest() {
        game.move("move Pippo 2, 2", false, null, null);
        assertEquals(game.moveString("move Pippo 1, 1", false, null, null), "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12");
    }

    @Test
    public void movePippoDiceRollTest() {
        game.move("move Pippo 2, 2", false, null, null);
        Optional<Turn> oTurn = game.move("move Pippo", false, null, null);
        Move lastMove = oTurn.get().getLastMove().get();
        assertEquals(oTurn.get().printMoves(), String.format("Pippo rolls %d, %d. Pippo moves from 4 to %s.%s",
                lastMove.getValueDice1(), lastMove.getValueDice2(), lastMove.getCurrentCellLabel(), lastMove.getAdditionalMessage()));
    }

    @Test
    public void movePippoSingleJumpTest() {
        game.move("move Pippo 2, 1", false, null, null);
        assertEquals(game.moveString("move Pippo 1, 1", false, 1, 1), "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7.");
    }

    @Test
    public void movePippoMultiJumpTest() {
        assertEquals(game.moveString("move Pippo 4, 1", false, 5, 4), "Pippo rolls 4, 1. Pippo moves from Start to 5, The Goose. Pippo moves again and goes to 14, The Goose. Pippo moves again and goes to 23, The Goose. Pippo moves again and goes to 32.");
    }

    @Test
    public void movePippoPrankTest() {
        game.move("move Pippo 6, 6", false, null, null);
        game.move("move Pippo 2, 1", false, null, null);

        game.move("move Pluto 6, 6", false, null, null);
        game.move("move Pluto 3, 2", false, null, null);

        assertEquals(game.moveString("move Pippo 1, 1", false, null, null), "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15.");
    }
}
