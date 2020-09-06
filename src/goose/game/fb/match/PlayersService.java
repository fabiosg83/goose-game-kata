package goose.game.fb.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author fabio.sgroi
 */
public class PlayersService implements IPlayersService {

    String PLAYER_PREFIX = "add player";
    int MIN_PLAYERS = 2;
    int MAX_PLAYERS = 6;
    List<Player> players;

    @Override
    public int getMinPlayers() {
        return MIN_PLAYERS;
    }

    @Override
    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String addPlayer(String playerText) {

        Optional<String> oPlayerName = retrievePlayerName(playerText, PLAYER_PREFIX);
        if (!oPlayerName.isPresent()) {
            return "not valid input player text";
        }

        if (players != null && players.stream().map(p -> p.getPlayerName()).anyMatch(oPlayerName.get()::equalsIgnoreCase)) {
            return String.format("%s: already existing player", oPlayerName.get());
        }

        if (players == null) {
            players = new ArrayList<>();
        }

        if (players.size() == 10) {
            return "maximum number of players reached";
        }

        players.add(new Player(oPlayerName.get()));
        return toString();
    }

    private Optional<String> retrievePlayerName(String text, String prefix) {

        if (text.regionMatches(true, 0, prefix, 0, prefix.length())) {
            return Optional.of(text.replaceAll(String.format("(?i)%s", prefix), "").trim());
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        if (players == null || players.isEmpty()) {
            return "no players";
        }
        return String.format("players: %s", players.stream().map(p -> (p.getPlayerName())).collect(Collectors.joining(", ")));
    }

}
