package goose.game.fb.test;

import goose.game.fb.match.PlayersService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabio.sgroi
 */
public class PlayerTest {

    PlayersService players = new PlayersService();

    @Test
    public void addPlayerPippoTest() {
        assertEquals(players.addPlayer("add player Pippo"), "players: Pippo");
    }

    @Test
    public void addPlayerPippoAndPlutoTest() {
        players.addPlayer("add player Pippo");
        assertEquals(players.addPlayer("add player Pluto"), "players: Pippo, Pluto");
    }

    @Test
    public void addPlayerPippoAndPlutoMultiStepTest() {
        assertEquals(players.addPlayer("add player Pippo"), "players: Pippo");
        assertEquals(players.addPlayer("add player Pluto"), "players: Pippo, Pluto");
    }

    @Test
    public void alreadyExistPlayerPippoTest() {
        players.addPlayer("add player Pippo");
        assertEquals(players.addPlayer("add player Pippo"), "Pippo: already existing player");
    }
}
