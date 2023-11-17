import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TopoSort {

    /*public static class Node{

        int u, time, d, f;
        boolean visited;
        Node p;

        public Node(int u, int time, Node p, int d, int f, boolean visited) {
            this.u = u;
            this.time = time;
            this.p = p;
            this.d = d;
            this.f = f;
            this.visited = visited;
        }
    }*/

    /*public static void graph_DFS(LinkedList<Node>[] G, Node node){

        System.out.println(node.u);
        node.time ++;
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

    public static void dfs(LinkedList<Node>[] G){

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

        ArrayList<LL> adjList = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            adjList.add(new LL(null, 0));
        }

        // read through file and populate intArrayList with file contents
        try {

            Scanner readFile = new Scanner(file);

            while(readFile.hasNextLine()){

                // create empty list
                LL list = new LL(null, 0);

                //p.add(Integer.parseInt(readFile.nextLine()));

                // want to read through file and insert each line
                // into the array list where the value of the node on the
                // left of the : is stored in arraylist at the value

                // 1. read the line
                // 2. split at semi colon:
                // 3. populate linked list with right side values
                // by delimiting on a comma (if possible)
                // 4. take left value and call arraylist.get(value, linkedlist)

                // Split the line at colon
                String[] parts = readFile.nextLine().split(":");

                // Parse the left and right side values
                int leftValue = Integer.parseInt(parts[0]);

                String[] rightSideValues = new String[parts.length - 1];
                
                if(parts.length > 1) {
                    rightSideValues = parts[1].split(",");
                }

                for (String value : rightSideValues) {
                    LL.Node node = new LL.Node((Integer.parseInt(value)), 0, 0, false);
                    list.addFirst(node);
                }

                adjList.add(leftValue, list);
            }

            readFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (LL ll : adjList) {

            System.out.println(ll);
        }

    }
}