package goose.game.fb.match;

import java.util.List;

/**
 *
 * @author fabio.sgroi
 */
public interface IPlayersService {

    int getMinPlayers();

    int getMaxPlayers();

    List<Player> getPlayers();

    String addPlayer(String playerText);
}
