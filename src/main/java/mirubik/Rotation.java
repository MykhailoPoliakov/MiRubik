package mirubik;

enum Cord {
    X,
    Y,
    Z
}

public record Rotation(Cord cord, int offset, boolean positive) {}