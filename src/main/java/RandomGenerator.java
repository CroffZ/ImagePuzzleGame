import java.util.Random;
import java.util.Vector;

public class RandomGenerator {

    private Vector[] moving_directions;

    RandomGenerator() {
        moving_directions = new Vector[9];

        for (int i = 0; i < 9; i++) {
            moving_directions[i] = new Vector();
        }

        moving_directions[0].add(3); // for each position, tell the piece-8
        // which one in the array that it can be
        // swapped with.
        // So only two pieces are considered each time, when the piece-8 is
        // moving.
        moving_directions[0].add(1);

        moving_directions[1].add(0);
        moving_directions[1].add(4);
        moving_directions[1].add(2);

        moving_directions[2].add(1);
        moving_directions[2].add(5);

        moving_directions[3].add(6);
        moving_directions[3].add(4);
        moving_directions[3].add(0);

        moving_directions[4].add(3);
        moving_directions[4].add(7);
        moving_directions[4].add(5);
        moving_directions[4].add(1);

        moving_directions[5].add(4);
        moving_directions[5].add(8);
        moving_directions[5].add(2);

        moving_directions[6].add(7);
        moving_directions[6].add(3);

        moving_directions[7].add(6);
        moving_directions[7].add(8);
        moving_directions[7].add(4);

        moving_directions[8].add(7);
        moving_directions[8].add(5);
    }

    int getRandomSeq(int[] num, int steps) {

        Random random = new Random();
        int missing = random.nextInt(8);
        //num = new int[9];
        for (int i = 0; i < 9; i++) {
            num[i] = i;
        }

        int cur_pos = missing; //The position of the 8-piece
        int swap_pos; //The position that is used to be swapped with the 8-piece
        int temp; //Used for data swap
        int choices; //The number of choiece for different positions of 8-piece
        int x; //The choice randomly made from different choieces
        for (int i = 0; i < steps; i++) {
            int options = moving_directions[cur_pos].size();
            x = random.nextInt(options - 1);
            swap_pos = (int) moving_directions[cur_pos].get(x);
            temp = num[cur_pos];
            num[cur_pos] = num[swap_pos];
            num[swap_pos] = temp;
            cur_pos = swap_pos;
        }

        return missing;
    }
}