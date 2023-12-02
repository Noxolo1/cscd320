// Nate Wilson

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Dijkstra {

    // adjList to represent graph
    public static LL[] adjList;
    // info array to hold info about nodes in the graph
    public static LL.Node[] info;

    public static void DijkstraAlgo(LL[] adjList, LL.Node[] info, int sourceVertex) {
        for (int i = 0; i < info.length; i++) {
            info[i].d = 2047483647;
            info[i].p = null;
        }

        info[sourceVertex].d = 0;

        LL.Node[] S = new LL.Node[info.length];
        MinHeap Q = new MinHeap(info);
        Q.buildMinHeap(Q);

        while (Q.heapSize != 0) {

            LL.Node u = Q.extractMin();
            S[u.value] = u;

            for (int i = 0; i < adjList[u.value].size; i++) {

                LL.Node v = adjList[u.value].get(i);
                LL.Node z = info[adjList[u.value].get(i).value];

                if (z.d > (u.d + v.weight)) {
                    z.d = u.d + v.weight;
                    z.p = u;
                    Q.minHeapify(0); // Rebuild the min-heap after updating distances
                }
            }
        }

        // Print the result
        for (int i = 0; i < S.length; i++) {
            if (i != sourceVertex) {
                if (S[i].d == 2047483647) {
                    System.out.println("[" + i + "]unreachable");
                } else {
                    System.out.print("[" + i + "]shortest path:(");
                    printShortestPath(S[i]);
                    System.out.println(") shortest distance:" + S[i].d);
                }
            }
        }
    }

    // Helper method to print the shortest path
    // Helper method to print the shortest path
    private static void printShortestPath(LL.Node node) {
        if (node.p != null) {
            printShortestPath(node.p);
            System.out.print("," + node.value);
        } else {
            System.out.print(node.value);
        }
    }


    // method to count how many lines are in a file
    public static int countRecords(final Scanner fin, final int linesPer) {

        //throw exceptions
        if(fin == null)
            throw new IllegalArgumentException("Scanner can't be null");

        if(linesPer <= 0)
            throw new IllegalArgumentException("Parameter can't be less than or equal to 0");

        //variable to count lines of file
        int count = 0;

        //read through file counting each line
        while(fin.hasNext()) {

            //read next line and count it
            fin.nextLine();
            count ++;
        }

        //exception for 0 lines
        if(count == 0){
            throw new java.lang.RuntimeException("Count is 0");
        }

        //return the count divided by the number of lines per record
        return count/linesPer;
    }

    public static void main(String[] args) {

        // check to make sure user entered input correctly
        /*if (args.length != 2) {
            System.out.println("Format for command line input: java <program_name> <filename> <sourceVertex>");
            return;
        }*/

        // file name is at position 0 in args
        //String filename = args[0];
        //int sourceVertex = Integer.parseInt(args[1]);

        File file = new File("C:\\Users\\Nate\\Documents\\GitHub\\cscd320\\prog5\\src\\graph1.txt");

        // read through file and populate intArrayList with file contents
        try {
            Scanner readFile = new Scanner(file);

            int lines = countRecords(readFile, 1);

            adjList = new LL[lines];
            info = new LL.Node[lines];

            // Initialize the adjacency list and info array
            for (int i = 0; i < lines; i++) {
                adjList[i] = new LL(null, 0);
                info[i] = new LL.Node(i, 0);
            }

            readFile = new Scanner(file);

            while (readFile.hasNextLine()) {
                LL list = new LL(null, 0);

                String[] parts = readFile.nextLine().split(":");
                int leftValue = Integer.parseInt(parts[0]);

                String[] rightSideValues = parts.length > 1 ? parts[1].split(";") : new String[0];

                // check if a node with the same value already exists in the info array
                LL.Node existingNode = info[leftValue];

                // if it exists, use the existing node; otherwise, create a new one
                LL.Node node;

                if (existingNode != null) {
                    node = existingNode;
                } else {
                    node = new LL.Node(leftValue, 0);
                    info[leftValue] = node;
                }

                for (String valueWithWeight : rightSideValues) {
                    String[] valueAndWeight = valueWithWeight.split(",");
                    int neighborValue = Integer.parseInt(valueAndWeight[0]);
                    int weight = valueAndWeight.length > 1 ? Integer.parseInt(valueAndWeight[1]) : 0;

                    // add the neighbor node to the linked list
                    list.addFirst(new LL.Node(neighborValue, weight));
                }

                // set the linked list at the correct index in the array
                adjList[leftValue] = list;
            }

            readFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DijkstraAlgo(adjList, info, 0);
    }
}