package A1;

import static A1.main.*;
import static A1.Chaining.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        //h function
        int multFunc = (int) ((A * key) % Math.pow(2, w)) >>> (w - r);
        int hashVal = (multFunc + i) % (int) Math.pow(2, r);

        return hashVal;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)

        //initializing i, which is the probe number in this case
        int i;

        //i is the number of times the hash value has to be calculated before it does not
        //hit a collision
        //the number of iterations of the loop (i) is equivalent to number of collisions.
        for (i = 0; i < (this.m); i++) {
            int hashVal = probe(key, i);

            //if the slot is empty, add key into hashvalue index and break out of loop.
            if (isSlotEmpty(hashVal)) {
                this.Table[hashVal] = key;
                return i;
            }

        }

        // returns -1 if there is an error
        return -1;

    }


    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)


        //i represents the number of slots visited before the key to be removed is found.
        //the number of iterations of the loop (i) is equivalent to number of slots visited.
        for (int i = 0; i < this.m; i++) {
            int hashVal = probe(key, i);

            //if the key at hash value index equals key given by user, set to -2 to represent it being "removed"
            if (!isSlotEmpty(hashVal)) {

                if (this.Table[hashVal] == key) {
                    this.Table[hashVal] = -2;
                    return i;
                }
            }

            else {
                return i;
            }

        }

        //returns -1 if there is an error
        return -1;

    }

}
