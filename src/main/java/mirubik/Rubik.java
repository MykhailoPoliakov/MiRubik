package mirubik;

import java.util.Arrays;

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



    int[] rotateSide(int[] side, int count) {
        if (count == 0) return side;

        if (count < 0) {
            for (int i = 0; i < -count; i++) {
                side = rotateSide(side, false);
            }
        } else {
            for (int i = 0; i < count; i++) {
                side = rotateSide(side, true);
            }
        }
        return side;
    }


    int[] rotateSide(int[] side, boolean positive) {
        int[] rotatingSide = Arrays.copyOf(side, side.length);

        int [] rotating;
        if (positive) {
            rotating = new int[]{2, 5, 8, 1, 4, 7, 0 ,3 ,6};
        } else {
            rotating = new int[]{6, 3, 0, 7, 4, 1, 8 ,5 ,2};
        }

        for (int i = 0; i < 9; i++) {
            rotatingSide[i] = side[rotating[i]];

        }
        return rotatingSide;
    }



    void rotate(Rotation rot) {
        // fill up new sides
        int[][] newSides = new int[sides.length][];
        for (int i = 0; i < sides.length; i++) {
            newSides[i] = sides[i].clone();
        }

        // make rotation
        int[] indV = new int[]{1+rot.offset(), 4+rot.offset(), 7+rot.offset()};
        int[] indH = new int[]{3+rot.offset()*3, 4+rot.offset()*3, 5+rot.offset()*3};
        int[] indVInv = new int[]{7-rot.offset(), 4-rot.offset(), 1-rot.offset()};
        int[] indHInv = new int[]{5-rot.offset()*3, 4-rot.offset()*3, 3-rot.offset()*3};
        
        


        int[] s;
        int[][] indList;
        
        switch (rot.cord()) {
            case X -> { // X
                s = rot.positive() ? new int[]{3, 4, 1, 5, 3} : new int[]{3, 5, 1, 4, 3};
                indList = new int[][]{null, indVInv, null, indV, indH, indHInv};
            }
            case Y -> { // Y
                s = rot.positive() ? new int[]{0, 4, 2, 5, 0} : new int[]{0, 5, 2, 4, 0};
                indList = new int[][]{indV, null, indVInv, null, indV, indVInv};
            }
            default -> { // Z
                s = rot.positive() ? new int[]{0, 1, 2, 3, 0} : new int[]{0, 3, 2, 1, 0};
                indList = new int[][]{indH, indH, indH, indH, null, null};
            }

        }

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j < 3; j++) {
                newSides[s[i+1]][indList[s[i+1]][j]] = this.sides[s[i]][indList[s[i]][j]];
            }
        }

        
        if (rot.offset() != 0) {
            int rotating_side;
            int[] rotating;
            int[] left  = new int[]{2, 5, 8, 1, 4, 7, 0 ,3 ,6};
            int[] right = new int[]{6, 3, 0, 7, 4, 1, 8 ,5 ,2};

            if (rot.cord() == Cord.X && rot.offset() == -1) {
                rotating_side = 0;
                rotating = rot.positive() ? left : right;
            } else if (rot.cord() == Cord.X && rot.offset() == 1) {
                rotating_side = 2;
                rotating = rot.positive() ? right : left;
            } else if (rot.cord() == Cord.Y && rot.offset() == 1) {
                rotating_side = 3;
                rotating = rot.positive() ? right : left;
            } else if (rot.cord() == Cord.Y && rot.offset() == -1) {
                rotating_side = 1;
                rotating = rot.positive() ? left : right;
            } else if (rot.cord() == Cord.Z && rot.offset() == -1) {
                rotating_side = 4;
                rotating = rot.positive() ? right : left;
            } else {
                rotating_side = 5;
                rotating = rot.positive() ? left : right;
            }


            for (int i = 0; i < 9; i++) {
                newSides[rotating_side][i] = this.sides[rotating_side][rotating[i]];
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



    int[] getRelativeSide(int sideIndex, int dirH, int dirV) {

        // int[] sideIndexes;
        // switch (sideIndex) {
        //     case 0  -> {
        //         sideIndexes = new int[]{1, 3, 4, 5};
        //         if (dirH == -1) return this.sides[sideIndexes[0]];
        //         if (dirH ==  1) return this.sides[sideIndexes[0]];
        //         if (dirV == -1) return this.sides[sideIndexes[0]];
        //         if (dirV ==  1) return this.sides[sideIndexes[0]];
        //     }
        //     case 1  -> sides = new int[]{1, 3, 4, 5};
        //     case 2  -> sides = new int[]{1, 3, 4, 5};
        //     case 3  -> sides = new int[]{1, 3, 4, 5};
        //     case 4  -> sides = new int[]{1, 3, 4, 5};
        //     default -> sides = new int[]{1, 3, 4, 5};
        // }



        return this.sides[0];
    }

    int getSideSquare(int sideIndex, int dirH, int dirV, int sqDirH, int sqDirV) {
        return 0;
    }




    int getSquare(int sideIndex, int dirH, int dirV) {
        int[] side = this.sides[sideIndex];
        int squareIndex = (1+dirH) + (1+dirV)*3;

        return side[squareIndex];
    }

    // make an fn that returns side on choosen dir and its rotation


    void getSide() {

    }





    void autoRotate() {
        for (int i =0 ; i < 6; i++) {
            int center = getSquare(i, 0, 0);
            if (!(getSquare(i, -1, 0) == center && getSquare(i, 1, 0) == center && 
                    getSquare(i, 0, -1) == center && getSquare(i, 0, 1) == center)) {
                // stage 1
            } 

        }   
    }


}
