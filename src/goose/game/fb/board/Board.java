package goose.game.fb.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author fabio.sgroi
 */
public class Board {

    static final Map<CellType, Set<Integer>> board = new HashMap<>();

    static {
        board.put(CellType.GOOSE, new HashSet<>(Arrays.asList(5, 9, 14, 18, 23, 27)));
        board.put(CellType.WIN, new HashSet<>(Arrays.asList(63)));
        board.put(CellType.BRIDGE, new HashSet<>(Arrays.asList(6)));
        board.put(CellType.HOME, new HashSet<>(Arrays.asList(19)));
        board.put(CellType.PRISON, new HashSet<>(Arrays.asList(31)));
        board.put(CellType.WELL, new HashSet<>(Arrays.asList(52)));
        board.put(CellType.LABYRINTH, new HashSet<>(Arrays.asList(42)));
        board.put(CellType.SKELETON, new HashSet<>(Arrays.asList(58)));
    }

    public static CellType getCellType(Integer position) {
        if (position > 63) {
            return CellType.BOUNCES;
        }
        return board.entrySet().stream()
                .filter(p -> p.getValue().contains(position))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(CellType.NORMAL);
    }
}
