package mirubik;

public class Cord {
    int V;
    int H;

    Cord(int V, int H) {
        if (V > 1 || V < -1 || H > 1 || H < -1) {
            throw new IllegalArgumentException();
        }
        this.V = V;
        this.H = H;
    }

    Cord rotate(int count) {


        return this;
    }
}
