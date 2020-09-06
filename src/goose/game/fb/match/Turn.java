package goose.game.fb.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author fabio.sgroi
 */
public class Turn implements ITurn {

    protected Player player;
    protected List<Move> moves;

    public Turn(Player player) {
        this.player = player;
        this.moves = new ArrayList<>();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Optional<Move> getLastMove() {
        if (this.moves.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.moves.get(this.moves.size() - 1));
    }

    @Override
    public Move addMove(Integer previousCell, Integer valueDice1, Integer valueDice2, String playerName) {
        Move move = new Move(previousCell, valueDice1, valueDice2, playerName);
        moves.add(move);
        return move;
    }

    @Override
    public String printMoves() {
        if (moves.isEmpty()) {
            return "---moves-empty---";
        } else if (moves.size() == 1) {
            return String.format(MOVE_MESSAGE, player.getPlayerName(),
                    moves.get(0).getValueDice1(), moves.get(0).getValueDice2(),
                    player.getPlayerName(), moves.get(0).getPreviousCellLabel(),
                    moves.get(0).getCurrentCellLabel(), moves.get(0).getAdditionalMessage()).trim();
        } else {
            String ret = String.format(MOVE_MESSAGE, player.getPlayerName(),
                    moves.get(0).getValueDice1(), moves.get(0).getValueDice2(),
                    player.getPlayerName(), moves.get(0).getPreviousCellLabel(),
                    moves.get(0).getCurrentCellLabel(), "").trim();
            for (int i = 1; i < moves.size(); i++) {
                ret += String.format(MOVE_AGAIN_MESSAGE, player.getPlayerName(), moves.get(i).getCurrentCellLabel());
            }
            return ret;
        }
    }
}
