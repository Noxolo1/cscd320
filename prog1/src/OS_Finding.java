import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Nate Wilson

public class OS_Finding {

    public int partition(final int[] A, final int left, final int right){

        int pivot, index, temp;

        // assign pivot
        pivot = A[right];
        index = left;

        // need <= (if subtracting 1 from right) in middle condition, so we touch every element except pivot
        for(int i = left; i <= right-1; i++){

            // if array at the ith element is <= to pivot we want to swap ith element and index element
            if(A[i] <= pivot){

                // perform swap of ith element and index element
                temp = A[i];
                A[i] = A[index];
                A[index] = temp;
                index++;
            }
        }

        // perform swap of the index and pivot and return index
        temp = A[index];
        A[index] = A[right];
        A[right] = temp;
        return index;
    }

    public int randomizedPartition(final int[] A, final int left, final int right){

        int i, temp;

        // inclusive on left, exclusive on right so add 1 to include right
        i = new Random().ints(left, right + 1).findFirst().getAsInt();

        // perform swap of right most element and element at new random index
        temp = A[i];
        A[i] = A[right];
        A[right] = temp;

        // call partition now with new pivot value
        return partition(A, left, right);
    }

    public Integer randomizedSelect(final int[] A, final int p, final int r, final int i){

        int q, k;

        // return null if order statistic cant be found in array A
        if(i > A.length || i <= 0){
            return null;
        }

        // case of size 1 array
        if(p == r){
            return A[p];
        }

        // q is pivot index
        q = randomizedPartition(A, p, r);

        //var for determining what part of array we should recurse on (if necessary)
        k = q - p;

        // case when ith order statistic is at pivot index, return array element at pivot index
        if(i == k){
            return A[q];
        }
        // case when ith order statistic is to the left of pivot index, recurse on left subarray
        else if (i < k){
            return randomizedSelect(A, p, q-1, i);
        }
        // case when ith order statistic is to the right of pivot index, recurse on right subarray
        else{
            return randomizedSelect(A, q+1, r, i-k);
        }

    }

    public static void main(String[] args) {

        // check to make sure user entered input correctly
        if (args.length != 2) {
            System.out.println("Format for command line input: java <program_name> <filename> <order_statistic>");
            return;
        }

        // file name is at position 0 in args
        String filename = args[0];

        // order statistic is at position 1 in args
        int orderStatistic = Integer.parseInt(args[1]);

        // create file object with user given file name
        File file = new File(filename);

        //use ArrayList to be able to hold any amount of integers in file
        ArrayList<Integer> intArrayList = new ArrayList<>();

        // read through file and populate intArrayList with file contents
        try {
            Scanner readFile = new Scanner(file);

            while (readFile.hasNext()) {
                intArrayList.add(Integer.parseInt(readFile.nextLine()));
            }

            readFile.close();

        } catch (FileNotFoundException f) {
            throw new IllegalArgumentException("File was not found.");
        }

        // create intArray with same size as intArrayList
        int[] intArray = new int[intArrayList.size()];

        // convert ArrayList into int[] array to pass as input into randomizedSelect
        for (int i = 0; i < intArrayList.size(); i++) {
            intArray[i] = intArrayList.get(i);
        }

        OS_Finding os = new OS_Finding();

        // Print the ith order statistic
        System.out.println(os.randomizedSelect(intArray, 0, intArray.length - 1, orderStatistic));
    }
}
