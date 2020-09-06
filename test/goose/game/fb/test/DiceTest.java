package goose.game.fb.test;

import goose.game.fb.match.Dice;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabio.sgroi
 */
public class DiceTest {

    @Test
    public void valueDiceValidFromStringTest() {
        Optional<Integer> dice = Dice.diceRoll("4");
        assertEquals(dice.get(), Integer.valueOf(4));
    }

    @Test
    public void valueDiceNotValidFromStringTest() {
        Optional<Integer> dice = Dice.diceRoll("9");
        assertEquals(dice, Optional.empty());
    }

    @Test
    public void valueDiceRandomTest() {
        Integer forceValueDice = null;
        Optional<Integer> dice = Dice.diceRoll(forceValueDice);
        assertTrue(0 < dice.get() && dice.get() < 7);
    }
    @Test
    public void valueDiceForceTest() {
        Integer forceValueDice = 5;
        Optional<Integer> dice = Dice.diceRoll(forceValueDice);
        assertTrue(0 < dice.get() && dice.get() < 7);
        assertEquals(dice.get(), Integer.valueOf(5));
    }
}
