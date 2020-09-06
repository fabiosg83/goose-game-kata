package goose.game.fb.play;

import goose.game.fb.match.Player;
import goose.game.fb.match.Turn;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author fabio.sgroi
 */
public interface IGame {

    static final String PRANK_MESSAGE = " On %d there is %s, who returns to %s.";

    boolean newMatch(List<Player> players);

    boolean canBeStart();

    boolean isFinishedMatch();

    Optional<Turn> move(String moveText, boolean sameTurn, Integer forceValueDice1, Integer forceValueDice2);

    String moveString(String moveText, boolean sameTurn, Integer forceValueDice1, Integer forceValueDice2);
}
