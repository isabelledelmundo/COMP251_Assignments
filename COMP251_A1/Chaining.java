package A1;

import java.util.*;
import static A1.main.*;

public class Chaining {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public ArrayList<ArrayList<Integer>> Table;

    //Constructor for the class. sets up the data structure for you
    protected Chaining(int w, int seed) {
        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.Table = new ArrayList<ArrayList<Integer>>(m);
        for (int i = 0; i < m; i++) {
            Table.add(new ArrayList<Integer>());
        }
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
    }

    /**
     * Implements the hash function h(k)
     */
    public int chain(int key) {
        //ADD YOUR CODE HERE (change return statement)

        return (int) ((A * key) % Math.pow(2, w)) >>> (w-r);
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table.get(hashValue).size() == 0;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (chane return statement)

        //initialized number of collisions
        int numColl = 0;
        int indexNum = chain(key); //getting hash value for key, and storing it as indexNum of hashtable

        //if slot is empty, add key
        if(isSlotEmpty(indexNum)){
            Table.get(indexNum).add(key);
            numColl = 0;
        }

        //if not empty, add key to array list at indexnum
        else{
            Table.get(indexNum).add(key);
            numColl = Table.get(indexNum).size();
        }

        //return num of collisions
        return numColl;
    }

}
