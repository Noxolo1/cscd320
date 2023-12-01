import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Dijkstra {

    public static void DijkstraAlgo(LL[] adjList, LL.Node[] info, int sourceVertex){

        for(int i = 0; i < adjList.length; i++){
            info[i].d = Integer.MAX_VALUE;
            info[i].p = null;
        }

        info[sourceVertex].d = 0;

        LL.Node[] S = new LL.Node[info.length];

        MinHeap Q = new MinHeap(info);
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
        /*if (args.length != 1) {
            System.out.println("Format for command line input: java <program_name> <filename> <sourceVertex>");
            return;
        }*/

        // file name is at position 0 in args
        //String filename = args[0];
        //int sourceValue = args[2];

        File file = new File("/Users/nwilson/Documents/GitHub/cscd320/prog5/src/graph1.txt");

        // adjList to represent graph
        LL[] adjList;
        // info array to hold info about nodes in the graph
        LL.Node[] info;

        // read through file and populate intArrayList with file contents
        try {
            Scanner readFile = new Scanner(file);

            // calculate size needed for arrays
            int lines = countRecords(readFile, 1);

            adjList = new LL[lines];
            info = new LL.Node[lines];

            readFile = new Scanner(file);

            while (readFile.hasNextLine()) {

                // create empty list
                LL list = new LL(null, 0);

                // split the line at colon
                String[] parts = readFile.nextLine().split(":");

                // parse left and right side values
                int leftValue = Integer.parseInt(parts[0]);

                String[] rightSideValues = parts.length > 1 ? parts[1].split(";") : new String[0];

                // check if a node with the same value already exists in the info array
                LL.Node existingNode = info[leftValue];

                // if it exists, use the existing node; otherwise, create a new one
                LL.Node node;

                if (existingNode != null) {
                    node = existingNode;
                } else {
                    node = new LL.Node(leftValue, 0); // assuming weight is not provided in the input
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

        System.out.println();
    }
}