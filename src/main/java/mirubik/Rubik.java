package mirubik;

enum Dir {
    F,
    L,
    R,
    U,
    D,
    B
}


public class Rubik {
    Side[] sides;


    Rubik() {
        this.sides = new Side[6];
        for (int i = 0; i < 6; i++) {
            this.sides[i] = new Side(i);
        }
    }

    
    public Rubik copy() {
        Rubik rubik = new Rubik();
        for (int i = 0; i < 6; i++) {
            rubik.sides[i] = this.sides[i].copy();
        }
        return rubik;
    }


    // TODO
    void rotateRel(int sideIndex, int count) {
        if (count == 0) return;

        Rubik rubik = this.copy();

        // rotate main side
        rubik.sides[sideIndex] = this.sides[sideIndex].rotate(count);
        

        if (count > 0) {
            Dir[] DirIndexes = new Dir[]{Dir.L, Dir.U, Dir.R, Dir.D,  Dir.L, Dir.U, Dir.R, Dir.D };

            for (int i = 0; i < 4; i++) {
                int rotSideIndex = this.getRelSideIndex(sideIndex, DirIndexes[i]);
                rubik.sides[rotSideIndex] = rubik.getRelRotation(rotSideIndex, DirIndexes[i], false);
                
                rubik.sides[rotSideIndex].setLine(this.getRelSide(sideIndex, DirIndexes[i+count]).getLine(Dir.D), Dir.D);

                rubik.sides[rotSideIndex] = rubik.getRelRotation(rotSideIndex, DirIndexes[i], true);
            }
        } else {

        }







    
        this.sides = rubik.sides;
    }


    boolean isSolved() {
        for (Side side : sides) {
            for (int square : side.sq) {
                if (square != side.sq[0]) {
                    return false;
                }
            }
        }
        return true;
    }



    int getRelSideIndex(int sideIndex, Dir dir) {
        int[] sideIndexes;
        switch (sideIndex) {
            case 0  -> { sideIndexes = new int[]{ 1, 3, 4, 5, 2}; }
            case 1  -> { sideIndexes = new int[]{ 0, 2, 4, 5, 3}; }
            case 2  -> { sideIndexes = new int[]{ 3, 1, 4, 5, 0}; }
            case 3  -> { sideIndexes = new int[]{ 0, 2, 4, 5, 1}; }
            case 4  -> { sideIndexes = new int[]{ 1, 3, 2, 0, 5}; }
            default -> { sideIndexes = new int[]{ 3, 1, 2, 0, 4}; }
        }
        switch (dir) {
            case L  -> { return sideIndexes[0]; }
            case R  -> { return sideIndexes[1]; }
            case U  -> { return sideIndexes[2]; }
            case D  -> { return sideIndexes[3]; }
            default -> { return sideIndexes[4]; }
        }
    }


    Side getRelRotation(int sideIndex, Dir dir, boolean reverse) {
        int[] rotationCounts;
        switch (sideIndex) {
            // L R U D
            case 0  -> { rotationCounts = new int[]{ 1,-1, 0, 0}; }
            case 1  -> { rotationCounts = new int[]{ 1,-1,-1, 1}; }
            case 2  -> { rotationCounts = new int[]{ 1,-1, 2, 2}; }
            case 3  -> { rotationCounts = new int[]{ 1,-1, 1,-1}; }
            case 4  -> { rotationCounts = new int[]{ 2, 2, 2, 2}; }
            default -> { rotationCounts = new int[]{ 0, 0, 0, 0}; }
        }

        if (!reverse) {
            switch (dir) {
                case F  -> { return this.sides[sideIndex]; }
                case L  -> { return this.sides[sideIndex].rotate(rotationCounts[0]); }
                case R  -> { return this.sides[sideIndex].rotate(rotationCounts[1]); }
                case U  -> { return this.sides[sideIndex].rotate(rotationCounts[2]); }
                case D  -> { return this.sides[sideIndex].rotate(rotationCounts[3]); }
                default -> { return this.sides[sideIndex].mirrorH(); }
            }
        } else {
            switch (dir) {
                case F  -> { return this.sides[sideIndex]; }
                case L  -> { return this.sides[sideIndex].rotate(-rotationCounts[0]); }
                case R  -> { return this.sides[sideIndex].rotate(-rotationCounts[1]); }
                case U  -> { return this.sides[sideIndex].rotate(-rotationCounts[2]); }
                case D  -> { return this.sides[sideIndex].rotate(-rotationCounts[3]); }
                default -> { return this.sides[sideIndex].mirrorH(); }
            }
        }
    }


    Side getRelSide(int sideIndex, Dir dir) { 
        if (dir == Dir.F) return this.sides[sideIndex];
        return this.getRelRotation(this.getRelSideIndex(sideIndex, dir), dir, false);
    }
}
