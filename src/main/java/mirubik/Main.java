package mirubik;

public class Main {
    public static void main(String[] args) {
        Rubik rubik = new Rubik();

        System.out.println("Solved: " + rubik.isSolved());

        rubik.rotate(new Rotation(Cord.Y,1, true));
        // rubik.rotate(new Rotation(Cord.Z, -1, true));
        // rubik.rotate(new Rotation(Cord.Y,1, false));
        // rubik.rotate(new Rotation(Cord.Z, -1, false));
        // rubik.rotate(new Rotation(Cord.Y,1, true));
        // rubik.rotate(new Rotation(Cord.Z, -1, true));
        // rubik.rotate(new Rotation(Cord.Y,1, false));
        // rubik.rotate(new Rotation(Cord.Z, -1, false));
        // rubik.rotate(new Rotation(Cord.X, -1, false));

        System.out.println(rubik.getSquare(0, 1, 1));


        System.out.println("Solved: " + rubik.isSolved());
        for (int[] side : rubik.sides) {
            for (int i = 0; i < 3; i++) {
                System.out.println(side[i*3] + " " + side[i*3 + 1] + " " + side[i*3 + 2]);
            }
            System.out.println();
            
        }
    }
}