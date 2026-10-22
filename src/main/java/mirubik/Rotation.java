package mirubik;

enum Cord {
    X,
    Y,
    Z
}

public record Rotation(Cord cord, int offset, boolean positive) {}


enum Dir {
    F,
    B,
    L,
    R,
    U,
    D
}
