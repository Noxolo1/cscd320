import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TopoSort {

    int time;

    public void graph_DFS(ArrayList<LL> G, LL.Node node){

        System.out.println(node.value);
        this.time ++;
        node.d = node.time;

        //dont think we need this assignment
        //node.visited = true;

        for(Node n: G[node.u]){

            if(!node.visited){
                n.p = node;
                graph_DFS(G, n);
            }
        }

        node.time++;
        node.f = node.time;
        node.visited = true;
    }

    /*public static void dfs(LinkedList<Node>[] G){

        for (LinkedList<Node> nodes : G) {

            for (Node n : nodes) {

                n.visited = false;
                n.p = null;
            }
        }

        
    }*/

    public static void main(String[] args) {
        // check to make sure user entered input correctly
        /*if (args.length != 1) {
            System.out.println("Format for command line input: java <program_name> <filename> ");
            return;
        }*/

        // file name is at position 0 in args
        //String filename = args[0];

        // create file object with user given file name
        File file = new File("/Users/nwilson/Documents/GitHub/cscd320/prog4/DAG2.txt");

        ArrayList<LL> adjList = new ArrayList<>();

        // want to read through file and insert each line
        // into the array list where the value of the node on the
        // left of the : is stored in arraylist at the value

        // 1. read the line
        // 2. split at semicolon
        // 3. populate linked list with right side values
        // (if possible) by delimiting on a comma
        // 4. use left value as index for array list

        // read through file and populate intArrayList with file contents
        try (Scanner readFile = new Scanner(file)) {

            while (readFile.hasNextLine()) {

                // create empty list
                LL list = new LL(null, 0);

                // split the line at colon
                String[] parts = readFile.nextLine().split(":");

                // parse left and right side values
                // left side is where we want to store ll
                int leftValue = Integer.parseInt(parts[0]);

                String[] rightSideValues = parts.length > 1 ? parts[1].split(",") : new String[0];

                for (String value : rightSideValues) {
                    LL.Node node = new LL.Node(Integer.parseInt(value), 0, 0, false);
                    list.addFirst(node);
                }

                // Ensure the ArrayList is large enough
                while (adjList.size() <= leftValue) {
                    adjList.add(null);
                }

                // Set the linked list at the correct index in the ArrayList
                adjList.set(leftValue, list);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (LL ll : adjList) {
            System.out.println(ll);
        }
    }
}