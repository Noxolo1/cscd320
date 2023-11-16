import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TopoSort {

    public static class Node{

        int u, time, p, d, f;
        boolean visited;

        public Node(int u, int time, int p, int d, int f, boolean visited) {
            this.u = u;
            this.time = time;
            this.p = p;
            this.d = d;
            this.f = f;
            this.visited = visited;
        }
    }

    public static void graph_DFS(LinkedList<Node>[] G, Node node){

        System.out.println(node.u);
        node.time ++;
        node.d = node.time;

        //dont think we need this assignment
        //node.visited = true;

        for(Node n: G[node.u]){

            if(!node.visited){
                n.p = node.u;
                graph_DFS(G, n);
            }
        }
        node.time++;
        node.f = node.time;
        node.visited = true;
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