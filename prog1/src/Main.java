public class Main {

    public static void main(String[] args) {

        OS_Finding os1 = new OS_Finding();

        int[] array1 = {3,1,-1, 10, 6, 4};

        // should print out 1st order statistic which is -1 here
        System.out.println(os1.randomizedSelect(array1, 0, array1.length - 1, 1));

        // should print out 2nd order statistic which is 1 here
        System.out.println(os1.randomizedSelect(array1, 0, array1.length - 1, 5));
    }
}