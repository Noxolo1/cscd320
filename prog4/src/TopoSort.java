import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TopoSort {

    public static void graph_DFS(LinkedList<Integer>[] G, int s){

        
    }

    public static void main(String[] args) {

        // check to make sure user entered input correctly
        if (args.length != 1) {
            System.out.println("Format for command line input: java <program_name> <filename> ");
            return;
        }

        // file name is at position 0 in args
        String filename = args[0];

        // create file object with user given file name
        File file = new File(filename);
        //String outputFile = "richest.output";

        ArrayList<Integer> p = new ArrayList<>();

        // read through file and populate intArrayList with file contents
        try {
            Scanner readFile = new Scanner(file);

            while(readFile.hasNextLine()){

                p.add(Integer.parseInt(readFile.nextLine()));
            }

            readFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int[] pInt = new int[p.size()];

        for(int i = 0; i < pInt.length; i++){

            pInt[i] = p.get(i);
        }

        //FastMatrixMulti fmm = new FastMatrixMulti();
        //TwoMatrixReturn tmr = fmm.matrixChainOrder(pInt);
        //fmm.printOptimalParens(tmr.s, 1, pInt.length - 1);
        //System.out.println();
        //System.out.println(tmr.m[1][pInt.length - 1]);
    }
}