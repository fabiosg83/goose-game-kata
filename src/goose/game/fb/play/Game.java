package goose.game.fb.play;

import goose.game.fb.board.Board;
import goose.game.fb.board.CellType;
import goose.game.fb.match.Dice;
import goose.game.fb.match.Move;
import goose.game.fb.match.Player;
import goose.game.fb.match.Turn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author fabio.sgroi
 */
public class Game implements IGame {

    List<Player> players;
    HashMap<String, Turn> playerTurns;
    boolean finishMatch;

    @Override
    public boolean newMatch(List<Player> players) {
        if (players == null || players.size() < 2) {
            System.out.println("The game cannot start, enter at least two players!!!");
            return false;
        }
        this.players = players;
        playerTurns = new HashMap<>();
        finishMatch = false;

        return true;
    }

    @Override
    public boolean canBeStart() {
        return playerTurns.isEmpty();
    }

    @Override
    public boolean isFinishedMatch() {
        return finishMatch;
    }

    @Override
    public Optional<Turn> move(String moveText, boolean sameTurn, Integer forceValueDice1, Integer forceValueDice2) {

        Optional<Turn> oTurn = Optional.empty();

        Optional<String[]> oParams = parserText(moveText);
        if (!oParams.isPresent()) {
            return oTurn;
        }

        Optional<Integer> oNumDice1, oNumDice2;
        if (oParams.get().length == 4) {
            oNumDice1 = Dice.diceRoll(oParams.get()[2]);
            oNumDice2 = Dice.diceRoll(oParams.get()[3]);
        } else {
            oNumDice1 = Dice.diceRoll(forceValueDice1);
            oNumDice2 = Dice.diceRoll(forceValueDice2);
        }

        if (!oNumDice1.isPresent() || !oNumDice2.isPresent()) {
            return oTurn;
        }

        Player player = players.stream().filter(p -> p.getPlayerName().equalsIgnoreCase(oParams.get()[1])).findFirst().get();

        Turn turn;
        if (playerTurns.containsKey(player.getPlayerName()) && sameTurn) {
            turn = playerTurns.get(player.getPlayerName());
        } else {
            turn = new Turn(player);
            playerTurns.put(player.getPlayerName(), turn);
        }

        Move move = turn.addMove(player.getCurrentCell(), oNumDice1.get(), oNumDice2.get(), player.getPlayerName());
        turn.getPlayer().setCurrentCell(move.getCurrentCell());

        CellType cellType = Board.getCellType(move.getCurrentCell());
        if (cellType == CellType.GOOSE) {
            move(String.format("move %s", player.getPlayerName()), true, forceValueDice1, forceValueDice2);
        }

        if (player.getCurrentCell() == CellType.WIN.getCode()) {
            finishMatch = true;
        }

        return Optional.of(turn);
    }

    public Optional<String> getAnotherPlayerInCell(Integer position, String excludePlayerName) {
        Optional<String> oPlayerNameKey = playerTurns.entrySet().stream()
                .filter(p -> (p.getValue().getPlayer().getCurrentCell() == position && !p.getValue().getPlayer().getPlayerName().equals(excludePlayerName)))
                .map(Map.Entry::getKey)
                .findFirst();

        return oPlayerNameKey;
    }

    @Override
    public String moveString(String moveText, boolean sameTurn, Integer forceValueDice1, Integer forceValueDice2) {

        Optional<Turn> oTurn = move(moveText, sameTurn, forceValueDice1, forceValueDice2);

        if (oTurn.isPresent()) {
            Optional<String> oPlayerNameKey = getAnotherPlayerInCell(oTurn.get().getPlayer().getCurrentCell(), oTurn.get().getPlayer().getPlayerName());
            if (oPlayerNameKey.isPresent()) {
                int prevCell = oTurn.get().getLastMove().get().getPreviousCell();
                Turn turnAnothePlayer = playerTurns.get(oPlayerNameKey.get());
                turnAnothePlayer.getPlayer().setCurrentCell(prevCell);

                return oTurn.get().printMoves() + String.format(PRANK_MESSAGE, oTurn.get().getPlayer().getCurrentCell(), oPlayerNameKey.get(), prevCell);
            }

            return oTurn.get().printMoves();
        } else {
            return "---error---";
        }
    }

    private Optional<String[]> parserText(String moveText) {

        Optional<String[]> oParams = Optional.empty();
        if (moveText == null || moveText.equals("")) {
            //return "move text not valid";
            return oParams;
        }

        String[] params = moveText.split(" ");
        if (!params[0].trim().equalsIgnoreCase("move")) {
            //return "move text not valid";
            return oParams;
        } else if (players.isEmpty() || !players.stream().anyMatch(p -> p.getPlayerName().equalsIgnoreCase(params[1]))) {
            //return "player not found";
            return oParams;
        }

        if (params.length != 4 && params.length != 2) {
            return oParams;
        }

        return Optional.of(params);
    }

}
