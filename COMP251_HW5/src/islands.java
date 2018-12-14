//worked with miki sugita and nima chatlani

import java.io.*;
import java.util.*;

public class islands {

    //function that returns number of islands in a problem
    public static int numOfIslands(int height, int width, char[][] world) {

        int numIslands = 0;

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                //calls fill function
                numIslands += fill(i, j, world);

            }

        }

        return numIslands;

    }


    private static int fill(int i, int j, char[][] world) {

        //if water is found, return 0
        if (i < 0 || i == world.length || j < 0 || j == world[i].length || world[i][j] == '#') {
            return 0;
        }

        //if land is found
        else{

            //keeps track of checked pixels
            world[i][j] = '#';

            //recursively calls for area around pixel
            fill(i + 1, j, world);
            fill(i - 1, j, world);
            fill(i, j + 1, world);
            fill(i, j - 1, world);

            //returns 1 to indicate island is found
            return 1;
        }



    }

    //read and write file in main method
    public static void main(String[] args) {

        //reading file
        File f = new File("testIslands.txt");
        int[] solutions = null;
        BufferedReader r = null;

        try {
            r = new BufferedReader(new FileReader(f));
            int numbProblems = Integer.parseInt(r.readLine());
            solutions = new int[numbProblems];

            for (int i = 0; i < numbProblems; i++) {

                String heightWidth = r.readLine();
                String[] split = heightWidth.split(" ");

                int h = Integer.parseInt(split[0]);
                int w = Integer.parseInt(split[1]);

                char[][] world = new char[h][w];

                for (int j = 0; j < h; j++) {
                    String line = r.readLine();
                    world[j] = line.toCharArray();
                }

                solutions[i] = numOfIslands(h, w, world);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //writing file
        BufferedReader br = null;
        File file = new File("." + File.separator + "testIslands_solution.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < solutions.length; i++) {
                System.out.println(solutions[i]);
                bw.write(solutions[i] + "\n");
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}