public class MinWeightGraph {
    public static int min(int a, int b, int c) {
        int smallest = a;
        if (smallest > b) smallest = b;
        if (smallest > c) smallest = c;
        return smallest;
    }
    public static int minCostPath(int[][] W) {

        // dimensions of 2d array
        int m = W.length;
        int n = W[0].length;

        //create array to hold minimum total weights
        int[][] mtw = new int[m][n];

        // populate first vertex weight
        mtw[0][0] = W[0][0];

        // populate the first row and column
        // as we already know these are the minimum
        // total weights
        for (int j = 1; j < m; j++) {
            mtw[j][0] = mtw[j - 1][0] + W[j][0];
        }
        for (int k = 1; k < n; k++) {
            mtw[0][k] = mtw[0][k - 1] + W[0][k];
        }

        // fill mtw using bottom up table driven approach
        // loop through rows
        for (int j = 1; j < m; j++) {

            // loop through columns
            for (int k = 1; k < n; k++) {

                // use recursive formula
                // add current weight and take min(up, left, diagonal)
                mtw[j][k] = W[j][k] + min(mtw[j - 1][k], mtw[j][k - 1], mtw[j - 1][k - 1]);
            }
        }

        // return minimum cost of last index, aka the destination
        return mtw[m - 1][n - 1];
    }

    public static void main(String[] args) {

        int[][] W = {
                {5, 3, 8, 2},
                {7, 2, 1, 9},
                {8, 2, 1, 5}
        };

        System.out.println("Minimum cost: " + minCostPath(W));
    }
}