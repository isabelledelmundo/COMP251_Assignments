//worked with miki sugita and nima chatlani

import java.io.*;
import java.util.*;

public class balloon {

    //method to find number of arrows it takes to
    public static int numArrows(ArrayList<Integer> balloonsArr){

        int numArrows = 0;

        //a loop that continues while there are still balloons
        //left to pop
        while (0 < balloonsArr.size()){

            numArrows++;
            int arrHeight = Collections.max(balloonsArr);

            for (int i = 0; i < balloonsArr.size(); i++) {

                int currBalloon = balloonsArr.get(i);

                //if arrow can hit balloon at that height
                if (arrHeight == currBalloon){
                    balloonsArr.remove(i);
                    arrHeight--;
                    i--;
                }

            }

        }

        return numArrows;

    }

    public static void main(String[] args){
        //initialize reader and int array for storing solutions
//        ArrayList<Integer> balloonTest = new ArrayList<Integer>();
//        //4 4 4 4 5 3 3 3 2 1 1
//        balloonTest.add(4);
//        balloonTest.add(4);
//        balloonTest.add(4);
//        balloonTest.add(4);
//        balloonTest.add(5);
//        balloonTest.add(3);
//        balloonTest.add(3);
//        balloonTest.add(3);
//        balloonTest.add(2);
//        balloonTest.add(1);
//        balloonTest.add(1);
//
//
//
//        System.out.println(numArrows(balloonTest));

        //reading file
        File f = new File("testBalloons.txt");
        BufferedReader r = null;

        int[] solutions = null;
        ArrayList<Integer> heights = new ArrayList<Integer>();
        ArrayList<Integer> balloons = new ArrayList<Integer>();

        try{

            r = new BufferedReader(new FileReader(f));

            int numProblems = Integer.parseInt(r.readLine());
            String[] numBalloon = r.readLine().split("\\s+");
            solutions = new int[numProblems];

            for(int i = 0; i < numBalloon.length; i++){
                balloons.add(Integer.parseInt(numBalloon[i]));
            }

            for(int j = 0; j < numProblems; j++){
                String[] height = r.readLine().split("\\s+");

                for(int k = 0; k < balloons.get(j); k++){
                    heights.add(Integer.parseInt(height[k]));
                }

                int numOfArrows = numArrows(heights);
                solutions[j] = numOfArrows;

            }


        } catch (IOException e) {
           System.out.println("file not found");
           System.exit(1);
        }

        //writing file
        BufferedReader br = null;
        File file = new File("." + File.separator + "testBalloons_solution.txt");

        try{
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < solutions.length; i++){
                System.out.println(solutions[i]);
                bw.write(solutions[i] + "\n");
            }

            bw.close();

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(br != null)br.close();
            } catch (IOException err){
                err.printStackTrace();
            }
        }
    }


}
