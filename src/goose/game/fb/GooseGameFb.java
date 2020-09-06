package goose.game.fb;

import goose.game.fb.play.Game;
import goose.game.fb.match.PlayersService;

/**
 *
 * @author fabio.sgroi
 */
public class GooseGameFb {

    public static void main(String[] args) {
        System.out.println("GOOSE-GAME-START");

        PlayersService players = new PlayersService();

        System.out.println(players.addPlayer("add player Pippo"));
        System.out.println(players.addPlayer("add player pippo"));
        System.out.println(players.addPlayer("add player pippo"));
        System.out.println(players.addPlayer("add player PIPPO"));

        System.out.println(players.addPlayer("add player Pluto"));
        System.out.println(players.addPlayer("add player Topolino"));
        System.out.println(players.addPlayer("add player Mark"));
        System.out.println(players.addPlayer("add player Jack"));
        System.out.println(players.addPlayer("add player Fabio"));

        System.out.println("");

        Game game = new Game();

        if (game.newMatch(players.getPlayers())) {
            System.out.println("---PRANK---");
            System.out.println(game.moveString("move Pippo 6, 6", false, null, null));
            System.out.println(game.moveString("move Pippo 2, 1", false, null, null));
            System.out.println(game.moveString("move Pluto 6, 6", false, null, null));
            System.out.println(game.moveString("move Pluto 3, 2", false, null, null));
            System.out.println(game.moveString("move Pippo 1, 1", false, null, null));
            System.out.println("");

            System.out.println("---MULTI JUMP---");
            System.out.println(game.moveString("move Topolino 4, 1", false, 5, 4));
            System.out.println(game.moveString("move Topolino 1, 1", false, null, null));
            System.out.println("");

            System.out.println("---BRIDGE---");
            System.out.println(game.moveString("move Mark 4, 2", false, null, null));
            System.out.println("");

            System.out.println("---WIN---");
            game.move("move jack 6, 6", false, null, null);
            game.move("move jack 6, 6", false, null, null);
            game.move("move jack 6, 6", false, null, null);
            game.move("move jack 6, 6", false, null, null);
            game.move("move jack 6, 6", false, null, null);
            System.out.println(game.moveString("move Jack 1, 2", false, null, null));
            System.out.println("");

            System.out.println("---BOUNCES---");
            game.move("move Fabio 6, 6", false, null, null);
            game.move("move Fabio 6, 6", false, null, null);
            game.move("move Fabio 6, 6", false, null, null);
            game.move("move Fabio 6, 6", false, null, null);
            game.move("move Fabio 6, 6", false, null, null);
            System.out.println(game.moveString("move Fabio 4, 1", false, null, null));
        } else {
            System.out.println("the match cannot start");
        }

        System.out.println("GOOSE-GAME-FINISH");
    }

}
