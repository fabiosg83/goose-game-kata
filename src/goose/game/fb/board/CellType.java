package goose.game.fb.board;

/**
 *
 * @author fabio.sgroi
 */
public enum CellType {
    WIN(0),
    BRIDGE(1),
    HOME(2),
    PRISON(3),
    WELL(4),
    LABYRINTH(5),
    SKELETON(7),
    NORMAL(8),
    GOOSE(9),
    BOUNCES(10);

    private static final CellType[] VALUES = CellType.values();
    private final Integer code;

    private CellType(Integer code) {
        this.code = code;
    }

    public static CellType getStatus(Integer code) {
        return VALUES[code];
    }

    public Integer getCode() {
        return this.code;
    }
}
