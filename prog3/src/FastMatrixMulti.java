import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

// Nate Wilson

public class FastMatrixMulti{

    // input file has multiple lines with
    // each line being part of a dimension
    // of a matrix
    // 10
    // 100
    // 5
    // 50
    // gives 3 matrices with dimensions 10 x 100
    // 100 x 5, and 5 x 50,    p_{i-1} x p_{i}

    public TwoMatrixReturn matrixChainOrder(int[] p){

        // p stores the dimension sizes

        // number of matrices
        int n = p.length - 1;

        // arrays to store optimal time cost
        // and optimal position of parentheses respectively
        int[][] m = new int[p.length][p.length];
        int[][] s = new int[p.length][p.length];

        for(int i = 1; i <= n; i++){
            m[i][i] = 0;
        }

        // h is length of the subchain
        for(int h = 2; h <= n; h++){

            // starting position of subchain
            for(int i = 1; i <= (n - h + 1); i++){

                // ending position of the sub-chain
                int j = i+h-1;
                m[i][j] = 2147483647;

                for(int k = i; k <= j - 1; k++){

                    int q = m[i][k] + m[k+1][j] + (p[i-1] * p[k] * p[j]);

                    if(q < m[i][j]){

                        // store the optimal time cost for A_i ... A_j
                        m[i][j] = q;

                        // the position of the outmost parentheses for
                        // optimally multiplying A_i ... A_J
                        s[i][j] = k;
                    }
                }
            }
        }

        return new TwoMatrixReturn(m, s);
    }

    public void printOptimalParens(int[][] s, int i, int j){

        if(i == j){
            System.out.print("A" + i);
        }
        else{
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
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

        FastMatrixMulti fmm = new FastMatrixMulti();
        TwoMatrixReturn tmr = fmm.matrixChainOrder(pInt);
        fmm.printOptimalParens(tmr.s, 1, pInt.length - 1);
        System.out.println();
        System.out.println(tmr.m[1][pInt.length - 1]);
    }
}