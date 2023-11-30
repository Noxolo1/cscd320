import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dijkstra {

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
        if (args.length != 1) {
            System.out.println("Format for command line input: java <program_name> <filename> ");
            return;
        }

        // file name is at position 0 in args
        String filename = args[0];

        File file = new File(filename);

        // adjList to represent graph
        LL[] adjList;
        // info array to hold info about nodes in the graph
        LL.Node[] info;

        // want to read through file and insert each line
        // into the array list where the value of the node on the
        // left of the colon is stored in arraylist at the value

        // 1. read the line
        // 2. split at semicolon
        // 3. populate linked list with right side values
        // (if possible) by delimiting on a comma
        // 4. use left value as index for array list

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

                String[] rightSideValues = parts.length > 1 ? parts[1].split(",") : new String[0];

                // check if a node with the same value already exists in the info array
                LL.Node existingNode = info[leftValue];

                // if it exists use the existing node, otherwise create a new one
                LL.Node node;

                if(existingNode != null){
                    node = existingNode;
                }
                else{
                    node = new LL.Node(leftValue);
                    info[leftValue] = node;
                }

                for (String value : rightSideValues) {

                    int neighborValue = Integer.parseInt(value);

                    // add the neighbor node to the linked list
                    list.addFirst(new LL.Node(neighborValue));
                }

                // set the linked list at the correct index in the array
                adjList[leftValue] = list;
            }

            readFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}