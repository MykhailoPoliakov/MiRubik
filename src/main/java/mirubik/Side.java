package mirubik;

public class Side {
    int[] sq = new int[9];



    Side(int color) {
        this.sq = new int[9];
        for (int i = 0; i < 9; i++) {
            this.sq[i] = color;
        }
    }


    Side copy() {
        Side side = new Side(0);
        side.sq = this.sq.clone();
        return side;
        
    }

    Side mirrorV() {
        Side side = this.copy();
        side.sq[0] = this.sq[6];
        side.sq[1] = this.sq[7];
        side.sq[2] = this.sq[8];
        side.sq[6] = this.sq[0];
        side.sq[7] = this.sq[1];
        side.sq[8] = this.sq[2];
        return side;
    }

    Side mirrorH() {
        Side side = this.copy();
        side.sq[0] = this.sq[2];
        side.sq[3] = this.sq[5];
        side.sq[6] = this.sq[8];
        side.sq[2] = this.sq[0];
        side.sq[5] = this.sq[3];
        side.sq[6] = this.sq[6];
        return side;
    }


    Side rotate(int count) {
        if (count == 0) return this;

        Side side = this.copy();

        if (count < 0) {
            for (int i = 0; i < -count; i++) {
                side = side.rotate(false);
            }
        } else {
            for (int i = 0; i < count; i++) {
                side = side.rotate(true);
            }
        }
        return side;
    }


    Side rotate(boolean clockwise) {
        Side side = this.copy();
        int [] rotating = (clockwise)? new int[]{6, 3, 0, 7, 4, 1, 8 ,5 ,2} : new int[]{2, 5, 8, 1, 4, 7, 0 ,3 ,6};

        for (int i = 0; i < 9; i++) {
            side.sq[i] = this.sq[rotating[i]];
        }
        return side;
    }


    int getSq(int H, int V) {
        return this.sq[(H+1) + (V+1)*3];
    }


    void setSq(int sq, int H, int V) {
        this.sq[(H+1) + (V+1)*3] = sq;
    }   


    int[] getLine(Dir dir) {
        switch (dir) {
            case L  -> { return new int[]{ this.sq[6], this.sq[3], this.sq[0]}; }
            case R  -> { return new int[]{ this.sq[2], this.sq[5], this.sq[8]}; }
            case U  -> { return new int[]{ this.sq[0], this.sq[1], this.sq[2]}; }
            case D  -> { return new int[]{ this.sq[8], this.sq[7], this.sq[6]}; }
            default -> { throw new IllegalArgumentException(); }
        }
    }


    void setLine(int[] line, Dir dir) {
        switch (dir) {
            case L  -> { 
                this.sq[6] = line[0]; 
                this.sq[3] = line[1]; 
                this.sq[0] = line[2]; 
            }
            case R  -> { 
                this.sq[2] = line[0]; 
                this.sq[5] = line[1]; 
                this.sq[8] = line[2]; 
            }
            case U  -> { 
                this.sq[0] = line[0]; 
                this.sq[1] = line[1]; 
                this.sq[2] = line[2]; 
            }
            case D  -> { 
                this.sq[8] = line[0]; 
                this.sq[7] = line[1]; 
                this.sq[6] = line[2]; 
            }
            default -> { throw new IllegalArgumentException(); }
        }
    }

}
