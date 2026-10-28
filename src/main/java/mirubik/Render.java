package mirubik;

public class Render {
    String[] colors = new String[]{"\u001B[31m", "\u001B[32m", "\u001B[38;5;208m", "\u001B[34m", "\u001B[97m","\u001B[33m"}; 


    String niceSq(int sq) {
        return colors[sq] + sq + "\u001B[0m";
    }


    String niceLine(Side side,int lineIndex) {
        return niceSq(side.sq[lineIndex*3]) + " " + niceSq(side.sq[lineIndex*3 + 1]) + " " + niceSq(side.sq[lineIndex*3 + 2]);
    }


    void print(Rubik rubik) {
        for (int i = 0; i < 3; i++) {
            for (Side side : rubik.sides) {
                System.out.print(niceSq(side.sq[i*3]) + " " + niceSq(side.sq[i*3 + 1]) + " " + niceSq(side.sq[i*3 + 2]) + "  ");
            }
            System.out.println();   
        }
    }





    void printRel(Rubik rubik, int sideIndex) {

        Side[] rotSides = new Side[6];
        Dir[] dirs = new Dir[]{ Dir.F, Dir.L, Dir.R, Dir.U, Dir.D, Dir.B };
        rotSides[0] = rubik.getRelSide(sideIndex, dirs[0]);
        rotSides[1] = rubik.getRelSide(sideIndex, dirs[1]).rotate(-1);
        rotSides[2] = rubik.getRelSide(sideIndex, dirs[2]).rotate(1);
        rotSides[3] = rubik.getRelSide(sideIndex, dirs[3]);
        rotSides[4] = rubik.getRelSide(sideIndex, dirs[4]).rotate(2);
        rotSides[5] = rubik.getRelSide(sideIndex, dirs[5]);

        for (int i = 0; i < 3; i++) {
            System.out.println("       " + niceLine(rotSides[3], i));
        }

        for (int i = 0; i < 3; i++) {
            System.out.print(niceLine(rotSides[1], i) + "  ");
            System.out.print(niceLine(rotSides[0], i) + "  ");
            System.out.print(niceLine(rotSides[2], i) + "  ");
            System.out.println(niceLine(rotSides[5], i));
        }


        for (int i = 0; i < 3; i++) {
            System.out.println("       " + niceLine(rotSides[4], i));
        }
    }


    void printSide(Side side) {
        for (int i = 0; i < 3; i++) {
            System.out.println(niceSq(side.sq[i*3]) + " " + niceSq(side.sq[i*3 + 1]) + " " + niceSq(side.sq[i*3 + 2]));
        }
    }

}
