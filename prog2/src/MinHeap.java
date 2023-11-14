// Nate Wilson

import java.util.Random;

public class MinHeap {

    // array containing minHeap
    private int[] array;

    // size of heap
    private int heapSize = 5;

    // length of the array
    private int length = 5;

    // heap constructor
    public MinHeap(final int[] array){

        this.array = array;
    }

    public int[] populateArray(){

        Random rd = new Random(); // creating Random object
        //int[] this.array = new int[this.length];
        for (int i = this.heapSize; i >= 1; i--) {
            this.array[this.heapSize - i] =  rd.nextInt(1,100); // storing random integers in an array
        }

        this.array = new int[] {5,4,3,2,1,0};

        return this.array;
    }

    public int[] getArray() {
        return this.array;
    }

    public int getHeapSize() {
        return this.heapSize;
    }

    public int getLength() {
        return this.length;
    }

    // return position of parents seat index
    private int parentIndex(int index){
        return ((index - 1) / 2);
    }

    // return position of leftChilds seat index
    private int leftChildIndex(int index){
        return ((2 * index) + 1);
    }

    // return position of rightChilds seat index
    private int rightChildIndex(int index){
        return ((2 * index) + 2);
    }

    // method to check if node at index is a leaf node
    private boolean isLeafNode(final int index){

        return rightChildIndex(index) >= this.heapSize && leftChildIndex(index) >= this.heapSize;
    }

    private void minHeapify(MinHeap minHeap, int i){

        int temp;
        int[] a = minHeap.array;
        int leftChildIndex = minHeap.leftChildIndex(i);
        int rightChildIndex = minHeap.rightChildIndex(i);

        // if the node is not a leaf node and any child is smaller
        // then compare each child node with parent and pick smallest one
        if (!isLeafNode(i)){

            if (rightChildIndex < a.length) {

                // compare parent to children
                if (a[i] > a[leftChildIndex] || a[i] > a[rightChildIndex]) {

                    // if left child smaller, then swap left child and parent
                    if (a[leftChildIndex] < a[rightChildIndex]) {
                        temp = a[i];
                        a[i] = a[leftChildIndex];
                        a[leftChildIndex] = temp;
                        minHeapify(minHeap, leftChildIndex);
                    }
                    //otherwise swap right child and parent
                    else {
                        temp = a[i];
                        a[i] = a[rightChildIndex];
                        a[rightChildIndex] = temp;
                        minHeapify(minHeap, rightChildIndex);
                    }
                }
            }
            if (rightChildIndex == a.length){

                if (!isLeafNode(i)){
                    temp = a[i];
                    a[i] = a[leftChildIndex];
                    a[leftChildIndex] = temp;
                    minHeapify(minHeap, leftChildIndex);
                }
            }
        }
    }

    public void buildMinHeap(MinHeap minHeap) {
        for (int i = (this.heapSize / 2); i >= 0; i--) {
            minHeapify(minHeap, i);
        }
    }

    public void heapSort(MinHeap minHeap){

        //buildMinHeap(minHeap);
        //for()

        //uses buildMinHeap as a helper
    }

    //may need a few other methods
}
