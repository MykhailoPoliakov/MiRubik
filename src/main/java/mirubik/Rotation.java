package mirubik;

enum Cord {
    X,
    Y,
    Z
}

enum Pos {
    MIN(-1),
    CEN(0),
    PLS(1);

    private final int value;

    Pos(int value) {
        this.value = value;
    }

    int get() {
        return value;
    }
}

public class Rotation {
    Cord cord;
    Pos pos;
    boolean positive;
}
