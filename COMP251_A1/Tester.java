package A1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import A1.main.*;
public class Tester {

    public static void main(String[] args) {

        System.out.println("Running checks");
        Open_Addressing openMap = new Open_Addressing(10, 0);
        Chaining chainingMap = new Chaining(10, 0);

        System.out.print("A = 973, w = 10, r = 5\n");


        int chain0 = chainingMap.chain(0);
        int chain1 = chainingMap.chain(1);
        int chain2 = chainingMap.chain(2);
        int chain3 = chainingMap.chain(3);
        int chain10 = chainingMap.chain(10);
        if (chain0 == 0 && chain1 == 30 && chain2 == 28 && chain3 == 27 && chain10 == 16) System.out.println("All chaining tests passed.");
        else System.out.println("One or more chaining tests failed.");

        chainingMap.insertKey(1);
        chainingMap.insertKey(1);
        chainingMap.insertKey(1);
        chainingMap.insertKey(3);
        chainingMap.insertKey(10);
        chainingMap.insertKey(10);
        if (chainingMap.Table.get(30).size() == 3 && chainingMap.Table.get(27).size() == 1 && chainingMap.Table.get(16).size() == 2) {
            System.out.println("All chaining insert checks passed.");
        }
        else {
            System.out.println("One or more chaining insert tests failed.");
        }

        int openInsert1 = openMap.insertKey(0);
        int openInsert2 = openMap.insertKey(0);
        int openInsert3 = openMap.insertKey(0);
        int openInsert4 = openMap.insertKey(2);
        int openInsert5 = openMap.insertKey(2);
        int openInsert6 = openMap.insertKey(10);
        if ((openMap.Table[0]==0) && (openMap.Table[1]==0) && (openMap.Table[2]==0) && (openMap.Table[28]==2) && (openMap.Table[29]==2) && (openMap.Table[16]==10)) {
            System.out.println("All open addressing insertions succesful.");
        }
        if (openInsert1 == 0 && openInsert2 == 1 && openInsert3 == 2 && openInsert4 == 0 && openInsert5 == 1 && openInsert6 == 0) {
            System.out.println("All open addressing insertions returned the correct values.");
        }

        System.out.println(871>>5);
        System.out.println(openMap.Table[27]);
        System.out.println(openMap.Table[28]);
        System.out.println(openMap.Table[29]);
        System.out.println(openMap.Table[30]);

        int probe = openMap.probe(1, 1);
        System.out.println("Open Addressing probe check: "+(probe==31));

        //Prints table; uncomment to see. for (int i = 0; i<32 ; i ++ ) System.out.println(openMap.Table[i]);

        int openRemove1 = openMap.removeKey(0);
        int openRemove2 = openMap.removeKey(0);
        int openRemove3 = openMap.removeKey(0);
        int openRemove33 = openMap.removeKey(0);

        int openRemove4 = openMap.removeKey(2);
        int openRemove5 = openMap.removeKey(2);
        int openRemove6 = openMap.removeKey(2);
        int openRemove66 = openMap.removeKey(2);

        int openRemove7 = openMap.removeKey(3);
        int openRemove8 = openMap.removeKey(3);

        if (openRemove1 == 0 && openRemove2 == 1 && openRemove3 == 2 && openRemove33 == 4 && openRemove4 == 0 && openRemove5 == 1 && openRemove6 == 3 && openRemove66 == 3 && openRemove7 == 1 && openRemove8 == 1) {
            System.out.println("All removals returned correct value. Check table to make sure removals were performed correctly.");
        } else System.out.println("nope");


        System.out.println("---------------");
        for (int i = 0; i<32 ; i ++ ) System.out.println(openMap.Table[i]);
        System.out.println("---------------");

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("n_comparison.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            String firstLine = "";
            while( (line = br.readLine())!=null ) {
                if(firstLine.equals(""))
                    firstLine = line;
                lengths.add(line.split(",").length);
            }
            System.out.println("n_comparison file check:"+(
                    firstLine.equals("Alpha,0.0625,0.125,0.1875,0.25,0.3125,0.375,0.4375,0.5,0.5625,0.625,0.6875,0.75,0.8125,0.875,0.9375,1.0")
                            && lengths.stream()
                            .map(l->l==lengths.get(0))
                            .reduce((acc,x)->acc&&x)
                            .get()
            ));
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created n_comparison.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("remove_collisions.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            while( (line = br.readLine())!=null ) {
                lengths.add(line.split(",").length);
            }
            System.out.println("remove_collisions file check:"+lengths.stream()
                    .map(l->l==lengths.get(0))
                    .reduce((acc,x)->acc&&x)
                    .get());
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created remove_collisions.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("w_comparison.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            while( (line = br.readLine())!=null ) {
                lengths.add(line.split(",").length);
            }
            System.out.println("w_comparison file check:"+lengths.stream()
                    .map(l->l==lengths.get(0))
                    .reduce((acc,x)->acc&&x)
                    .get());
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created w_comparison.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}