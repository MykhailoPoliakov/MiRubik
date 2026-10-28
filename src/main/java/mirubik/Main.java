package mirubik;

public class Main {
    public static void main(String[] args) {
        Rubik rubik = new Rubik();
        Render render = new Render();

        System.out.println("Solved: " + rubik.isSolved());

        rubik.rotateRel(0, 1);


        render.print(rubik);

        System.out.println();

        render.printRel(rubik, 0);

        int[] line = rubik.sides[0].getLine(Dir.D);
        System.out.println(" " + line[0] + " " + line[1] + " " + line[2]);

        render.printSide(rubik.sides[0]);
        render.printSide(rubik.sides[0].rotate(1));


    }
}