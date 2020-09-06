package goose.game.fb.match;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author fabio.sgroi
 */
public class Dice {

    public static Optional<Integer> diceRoll(String param) {
        if (param == null || param.equals("")) {
            return Optional.empty();
        }
        try {
            int value = Integer.valueOf(param.replaceAll(",", "").trim());
            if (value < 1 || value > 6) {
                return Optional.empty();
            }
            return Optional.of(value);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> diceRoll(String param1, String param2) {
        try {
            return Optional.of(Integer.valueOf(param1.replaceAll(",", "").trim()) + Integer.valueOf(param2.replaceAll(",", "").trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> diceRoll(Integer forceValueDice) {

        if (forceValueDice != null && forceValueDice > 0 && forceValueDice < 7) {
            return Optional.of(forceValueDice);
        }
        return Optional.of(ThreadLocalRandom.current().nextInt(1, 7));
    }
}
