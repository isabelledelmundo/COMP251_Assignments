package A1;

import A1.Chaining.*;
import A1.Open_Addressing.*;
import java.io.*;
import java.util.*;

//collabed with Nima Chatlani and Miki Sugita


public class main {

    /**
     * Calculate 2^w
     */
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    /**
     * Uniformly generate a random integer between min and max, excluding both
     */
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        //if the seed is equal or above 0, we use the input seed, otherwise not.
        if (seed >= 0) {
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max - min - 1);
        return i + min + 1;
    }

    /**
     * export CSV file
     */
    public static void generateCSVOutputFile(String filePathName, ArrayList<Double> alphaList, ArrayList<Double> avColListChain, ArrayList<Double> avColListProbe) {
        File file = new File(filePathName);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            int len = alphaList.size();
            fw.append("Alpha");
            for (int i = 0; i < len; i++) {
                fw.append("," + alphaList.get(i));
            }
            fw.append('\n');
            fw.append("Chain");
            for (int i = 0; i < len; i++) {
                fw.append("," + avColListChain.get(i));
            }
            fw.append('\n');
            fw.append("Open Addressing");
            for (int i = 0; i < len; i++) {
                fw.append(", " + avColListProbe.get(i));
            }
            fw.append('\n');
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*===========PART 1 : Experimenting with n===================*/
        //Initializing the three arraylists that will go into the output 
        ArrayList<Double> alphaList = new ArrayList<Double>();
        ArrayList<Double> avColListChain = new ArrayList<Double>();
        ArrayList<Double> avColListProbe = new ArrayList<Double>();

        //Keys to insert into both hash tables
        int[] keysToInsert = {164, 127, 481, 132, 467, 160, 205, 186, 107, 179,
            955, 533, 858, 906, 207, 810, 110, 159, 484, 62, 387, 436, 761, 507,
            832, 881, 181, 784, 84, 133, 458, 36};

        //values of n to test for in the experiment
        int[] nList = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        //value of w to use for the experiment on n
        int w = 10;

        for (int n : nList) {

            //initializing two hash tables with a seed
            Chaining MyChainTable = new Chaining(w, 137);
            Open_Addressing MyProbeTable = new Open_Addressing(w, 137);

            /*Use the hash tables to compute the average number of 
                        collisions over keys keysToInsert, for each value of n. 
                        The format of the three arraylists to fillis as follows:
                        
                        alphaList = arraylist of all tested alphas 
                                   (corresponding to each tested n)
                        avColListChain = average number of collisions for each
                                         Chain experiment 
                                         (make sure the order matches alphaList)
                        avColListProbe =  average number of collisions for each
                                         Linear Probe experiment
                                           (make sure the order matches)
                        The CSV file will output the result which you can visualize
             */
            //ADD YOUR CODE HERE
            //initializing collision variables
            int probColl = 0;
            int chainColl = 0;

            //storing number of collisions while inserting keys in corresponding hash tables
            for(int i = 0; i < n; i++){
                chainColl += MyChainTable.insertKey(keysToInsert[i]);
                probColl += MyProbeTable.insertKey(keysToInsert[i]);
            }

            //initializing load factor
            double alpha = (double) n / (double) MyChainTable.m;
            alphaList.add(alpha); //adding

            //calculating avg num of collisions per hash function
            double avgChainCol = (double) chainColl/ (double) n;
            double avgProbCol = (double) probColl/ (double) n;

            //adding avg num of collisions into the list
            avColListChain.add(avgChainCol);
            avColListProbe.add(avgProbCol);
        }

        generateCSVOutputFile("n_comparison.csv", alphaList, avColListChain, avColListProbe);

        /*===========    PART 2 : Test removeKey  ===================*/
 /* In this exercise, you apply your removeKey method on an example.
        Make sure you use the same seed, 137, as you did in part 1. You will
        be penalized if you don't use the same seed.
         */
        //Please not the output CSV will be slightly wrong; ignore the labels.
        ArrayList<Double> removeCollisions = new ArrayList<Double>();
        ArrayList<Double> removeIndex = new ArrayList<Double>();
        int[] keysToRemove = {6, 8, 164, 180, 127, 3, 481, 132, 4, 467, 5, 160,
            205, 186, 107, 179};

        //ADD YOUR CODE HERE

        //intizialing new probe table with same seed of 137
        Open_Addressing probeRmTable = new Open_Addressing(w, 137);

        //inserting keys into table
        for (int i = 0; i < 16; i++){

            probeRmTable.insertKey(keysToInsert[i]);

        }

        //removing n from keysToRemove from the probe table
        for (int i = 0; i < keysToRemove.length - 1; i++){

            removeCollisions.add((double) probeRmTable.removeKey(keysToRemove[i]));
            removeIndex.add((double) i);

        }

        generateCSVOutputFile("remove_collisions.csv", removeIndex, removeCollisions, removeCollisions);

        /*===========PART 3 : Experimenting with w===================*/

 /*In this exercise, the hash tables are random with no seed. You choose 
                values for the constant, then vary w and observe your results.
         */
        //generating random hash tables with no seed can be done by sending -1
        //as the seed. You can read the generateRandom method for detail.
        //randomNumber = generateRandom(0,55,-1);
        //Chaining MyChainTable = new Chaining(w, -1);
        //Open_Addressing MyProbeTable = new Open_Addressing(w, -1);
        //Lists to fill for the output CSV, exactly the same as in Task 1.
        ArrayList<Double> alphaList2 = new ArrayList<Double>();
        ArrayList<Double> avColListChain2 = new ArrayList<Double>();
        ArrayList<Double> avColListProbe2 = new ArrayList<Double>();

        //ADD YOUR CODE HERE

        //initializing number of elems
        int numOfElems = 16;
        int[] varyWArr = new int[numOfElems]; //initializing array

        //initializing collision variables
        int chainColl;
        int probeColl;

        for(int i = 0; i < numOfElems; i++){

            varyWArr[i] = generateRandom(0,1000,-1);

        }

        for(int varyW = 5; varyW <= 15; ++varyW){

            Chaining MyChainVaryWTable = new Chaining(varyW, -1);
            Open_Addressing MyProbeVaryWTable = new Open_Addressing(varyW, -1);

            //resetting collisions to 0 after inserting key to varying tables
            chainColl = 0;
            probeColl = 0;

            for(int i = 0; i < numOfElems; i++){

                int temp = MyChainVaryWTable.insertKey(varyWArr[i]);
                chainColl += temp;

                temp = MyProbeVaryWTable.insertKey(varyWArr[i]);
                probeColl += temp;

            }

            //load factor alpha = number of keys inserted/number of slots in hash table
            double alpha = (double) numOfElems / (double) MyChainVaryWTable.m;
            alphaList2.add(alpha);

            //calculating avg num of collisions per hash function
            double avgChainCol = (double) chainColl/(double) numOfElems;
            double avgProbCol = (double) probeColl/ (double) numOfElems;

            //adding avg num of collisions into the list
            avColListChain2.add(avgChainCol);
            avColListProbe2.add(avgProbCol);
        }

        generateCSVOutputFile("w_comparison.csv", alphaList2, avColListChain2, avColListProbe2);

    }

}
