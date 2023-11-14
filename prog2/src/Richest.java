// Nate Wilson

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Richest {


    // array containing minHeap

    // size of heap
    public int heapSize = 10000;

    // length of the array
    public int length = heapSize;

    public int[] a = new int[length];

    public int parentIndex(int index){
        return ((index - 1) / 2);
    }

    // return position of leftChilds seat index
    public int leftChildIndex(int index){
        return ((2 * index) + 1);
    }

    // return position of rightChilds seat index
    public int rightChildIndex(int index){
        return ((2 * index) + 2);
    }

    // method to check if node at index is a leaf node
    public boolean isLeafNode(final int index) {

        return leftChildIndex(index) >= this.heapSize && rightChildIndex(index) >= this.heapSize;
    }

    public void minHeapify(int[] a, int i) {

        int left = leftChildIndex(i);
        int right = rightChildIndex(i);
        int smallest = i;

        if(!isLeafNode(i)) {

            if (left < heapSize && a[left] < a[i]) {
                smallest = left;
            }

            if (right < heapSize && a[right] < a[smallest]) {
                smallest = right;
            }

            if (smallest != i) {
                // swap A[i] and A[smallest]
                int temp = a[i];
                a[i] = a[smallest];
                a[smallest] = temp;

                // minHeapify the subtree
                minHeapify(a, smallest);
            }
        }
    }

    public void buildMinHeap(int[] a) {
        for (int i = (a.length/2); i >= 0; i--) {
            minHeapify(a, i);
        }
    }

    public void heapSort(int[] a){

        buildMinHeap(a);
        for(int i = a.length - 1; i >= 1; i--){

            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapSize--;
            minHeapify(a, 0);
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
        String outputFile = "richest.output";

        Richest richest = new Richest();

        // read through file and populate intArrayList with file contents
        try {
            Scanner readFile = new Scanner(file);
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for(int i = 0; i < 10000; i++){

                richest.a[i] = Integer.parseInt(readFile.nextLine());
            }

            //System.out.println(Arrays.toString(richest.a));

            while(readFile.hasNextLine()){

                int currentIncome = Integer.parseInt(readFile.nextLine());

                if(currentIncome <= richest.a[0]){}
                else{
                    richest.a[0] = currentIncome;
                    System.out.println(Arrays.toString(richest.a));
                    richest.minHeapify(richest.a, 0);
                }
            }

            richest.heapSort(richest.a);
            //System.out.println(Arrays.toString(richest.a));

            //dump to text file
            for(int i: richest.a){

                writer.write(Integer.toString(i));
                writer.newLine();
            }

            readFile.close();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Richest richest = new Richest();

        //System.out.println(Arrays.toString(richest.a));
        //richest.buildMinHeap(richest.a);
        //System.out.println(Arrays.toString(richest.a));
        //richest.minHeapify(richest.a, 0);
        //System.out.println(Arrays.toString(richest.a));
        //richest.heapSort(richest.a);
        //System.out.println(Arrays.toString(richest.a));
    }
}
