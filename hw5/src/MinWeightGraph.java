import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinWeightGraph {
<<<<<<< Updated upstream

    public static int[][] minCostPath(int[][] W) {
=======
    public static int min(int a, int b, int c) {
        int smallest = a;

        if (smallest > b) {
            smallest = b;
        }

        if (smallest > c) {
            smallest = c;
        }
        return smallest;
    }
    public static int minCostPath(int[][] W) {
>>>>>>> Stashed changes

        // dimensions of 2d array
        int m = W.length;
        int n = W[0].length;

        //create array to hold minimum total weights
        int[][] mtw = new int[m][n];

        // populate the first column and row respectively
        // as these minimum total weights are trivially calculated
        mtw[0][0] = W[0][0];
        for (int j = 1; j < m; j++) {
            mtw[j][0] = mtw[j - 1][0] + W[j][0];
        }
        for (int k = 1; k < n; k++) {
            mtw[0][k] = mtw[0][k - 1] + W[0][k];
        }

        // fill the remaining elements of mtw

        // loop through rows
        for (int j = 1; j < m; j++) {

            // loop through columns
            for (int k = 1; k < n; k++) {

                // use recursive formula
                // add current weight and take min(up, left, diagonal)
                mtw[j][k] = W[j][k] + Math.min(Math.min(mtw[j - 1][k], mtw[j][k - 1]), mtw[j - 1][k - 1]);
            }
        }

        // print the resulting 2d array
        for (int[] x : mtw)
        {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }

        // return minimum cost of last index, aka the destination
        return mtw;
    }

    public static void printOptimalPath(int[][] mtw, int j, int k) {

        // add starting index as this
        // must be a part of the optimal path
        if (j == 0 && k == 0) {
            System.out.print("(0,0)");
        }

        // otherwise check the previous 3 vertices and
        // determine the smallest minimum weight
        else {
            int up, left, diagonal;

            // up
            if((j > 0)){
                up = mtw[j - 1][k];
            }
            else{
                // if we cant move up just
                // set (up = infinity) for comparison below
                up = Integer.MAX_VALUE;
            }
            // left
            if((k > 0)){
                left = mtw[j][k - 1];
            }
            else{
                left = Integer.MAX_VALUE;
            }
            // diagonal
            if((j > 0 && k > 0)){
                diagonal = mtw[j - 1][k - 1];
            }
            else{
                diagonal = Integer.MAX_VALUE;
            }

            // now compare weights to determine
            // which way to travel
            if (up <= left && up <= diagonal) {
                // move up
                printOptimalPath(mtw, j - 1, k);
            }
            else if (left <= up && left <= diagonal) {
                // move left
                printOptimalPath(mtw, j, k - 1);
            }
            else {
                // move diagonal
                printOptimalPath(mtw, j - 1, k - 1);
            }

            System.out.print(" -> (" + j + "," + k + ")");
        }
    }

    public static void main(String[] args) {

        int[][] W = {
                {5, 3, 8, 2},
                {7, 2, 1, 9},
                {8, 2, 1, 5}
        };

        int[][] mtw = minCostPath(W);
        int m = W.length;
        int n = W[0].length;
        System.out.println("Minimum cost: " + mtw[m-1][n-1]);
        printOptimalPath(mtw, m-1, n-1);
    }
}