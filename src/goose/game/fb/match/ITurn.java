package goose.game.fb.match;

import java.util.Optional;

/**
 *
 * @author fabio.sgroi
 */
public interface ITurn {

    static final String MOVE_MESSAGE = "%s rolls %d, %d. %s moves from %s to %s.%s";
    static final String MOVE_AGAIN_MESSAGE = " %s moves again and goes to %s.";

    Player getPlayer();

    Optional<Move> getLastMove();

    Move addMove(Integer previousCell, Integer valueDice1, Integer valueDice2, String playerName);

    String printMoves();
}
