//dummy file
import java.io.*;
import java.util.Scanner;

public class mancala {

    public static void main(String[] args){

        File f = new File("testMancala.txt");

        try{

            //reading file
            Scanner sc = new Scanner(f);


        } catch (IOException e) {
            System.out.println("file not found");
            System.exit(1);
        }

    }

}