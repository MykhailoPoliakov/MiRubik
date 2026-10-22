package mirubik;

public class Rubik {
    int[][] sides;


    Rubik() {
        this.sides = new int[6][9];
        for (int i = 0; i < sides.length; i++) {
            for (int j = 0; j < sides[i].length; j++) {
                this.sides[i][j] = i;
            }
        }
    }

    void rotate(Rotation rot) {
        // fill up new sides
        int[][] newSides = new int[sides.length][];
        for (int i = 0; i < sides.length; i++) {
            newSides[i] = sides[i].clone();
        }

        // make rotation`
        int[] indV = new int[]{1+rot.offset(), 4+rot.offset(), 7+rot.offset()};
        int[] indH = new int[]{3+rot.offset()*3, 4+rot.offset()*3, 5+rot.offset()*3};
        int[] indVInvV = new int[]{7+rot.offset(), 4+rot.offset(), 1+rot.offset()};
        int[] indHInvH = new int[]{5+rot.offset()*3, 4+rot.offset()*3, 3+rot.offset()*3};
        int[] indVInvH = new int[]{1-rot.offset(), 4-rot.offset(), 7-rot.offset()};
        int[] indVInvHV = new int[]{7-rot.offset(), 4-rot.offset(), 1-rot.offset()};
        int[] indHInvV = new int[]{3-rot.offset()*3, 4-rot.offset()*3, 5-rot.offset()*3};
        int[] indHInvHV = new int[]{5-rot.offset()*3, 4-rot.offset()*3, 3-rot.offset()*3};
        
        int[] s;
        int[][] indList;
        
        switch (rot.cord()) {
            case X -> { // X
                s = rot.positive() ? new int[]{3, 4, 1, 5, 3} : new int[]{3, 5, 1, 4, 3};
                indList = new int[][]{null, indVInvHV, null, indV, indH, indHInvHV};
            }
            case Y -> { // Y
                s = rot.positive() ? new int[]{0, 4, 2, 5, 0} : new int[]{0, 5, 2, 4, 0};
                indList = new int[][]{indV, null, indVInvHV, null, indV, indVInvHV};
            }
            default -> { // Z
                s = rot.positive() ? new int[]{0, 1, 2, 3, 0} : new int[]{0, 3, 2, 1, 0};
                indList = new int[][]{indV, indV, indV, indV, null, null};
            }

        }

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j < 3; j++) {
                newSides[s[i]][indList[s[i]][j]] = this.sides[s[i+1]][indList[s[i+1]][j]];
            }
        }

        this.sides = newSides;
    }


    boolean isSolved() {
        for (int[] side : sides) {
            for (int square : side) {
                if (square != side[0]) {
                    return false;
                }
            }
        }
        return true;
    }


}
