// Nate Wilson

import java.util.Random;

public class MinHeap {

    // array containing minHeap
    public LL.Node[] array;

    // size of heap
    public int heapSize = Dijkstra.adjList.length;

    // length of the array
    public int length = Dijkstra.adjList.length;

    // heap constructor
    public MinHeap(final LL.Node[] array){

        this.array = array;
    }

    public LL.Node[] getArray() {
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

    /*public void minHeapify(MinHeap minHeap, int i){

        LL.Node temp;
        LL.Node[] a = minHeap.array;
        int leftChildIndex = minHeap.leftChildIndex(i);
        int rightChildIndex = minHeap.rightChildIndex(i);

        // if the node is not a leaf node and any child is smaller
        // then compare each child node with parent and pick smallest one
        if (!isLeafNode(i)){

            if (rightChildIndex < heapSize) {

                // compare parent to children
                if (a[i].d > a[leftChildIndex].d || a[i].d > a[rightChildIndex].d) {

                    // if left child smaller, then swap left child and parent
                    if (a[leftChildIndex].d < a[rightChildIndex].d) {
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
            if (rightChildIndex == heapSize){

                if (!isLeafNode(i)){
                    temp = a[i];
                    a[i] = a[leftChildIndex];
                    a[leftChildIndex] = temp;
                    minHeapify(minHeap, leftChildIndex);
                }
            }
        }
    }*/

    // heapify the node at i
    public void minHeapify(int i) {

        LL.Node temp;
        int leftChildIndex = leftChildIndex(i);
        int rightChildIndex = rightChildIndex(i);

        // Check if left child index is within bounds
        if (leftChildIndex < heapSize) {
            // Check if right child index is within bounds
            if (rightChildIndex < heapSize) {
                // If the node is a non-leaf node and any of its child is smaller
                if (array[i].d > array[leftChildIndex].d || array[i].d > array[rightChildIndex].d) {
                    if (array[leftChildIndex].d < array[rightChildIndex].d) {
                        temp = array[i];
                        array[i] = array[leftChildIndex];
                        array[leftChildIndex] = temp;
                        minHeapify(leftChildIndex);
                    } else {
                        temp = array[i];
                        array[i] = array[rightChildIndex];
                        array[rightChildIndex] = temp;
                        minHeapify(rightChildIndex);
                    }
                }
            }
            // If only the left child is present
            else if (array[i].d > array[leftChildIndex].d) {
                temp = array[i];
                array[i] = array[leftChildIndex];
                array[leftChildIndex] = temp;
                minHeapify(leftChildIndex);
            }
        }
    }
    
    public void buildMinHeap(MinHeap minHeap) {
        for (int i = (this.heapSize / 2); i >= 0; i--) {
            minHeapify(i);
        }
    }

    public LL.Node extractMin() {

        if (this.heapSize < 1) {
            // Heap underflow, return an error value or handle it accordingly
            System.out.println("Heap underflow");
            return null;
        }

        LL.Node min = this.array[0];  // The minimum element is at the root
        this.array[0] = this.array[this.heapSize - 1]; // Replace root with the last element
        this.heapSize--;

        // Maintain the min-heap property by calling minHeapify
        minHeapify(0);

        return min;
    }

    public void heapSort(MinHeap minHeap){

        //buildMinHeap(minHeap);
        //for()

        //uses buildMinHeap as a helper
    }

    /*public void heapSort(int[] a){

        buildMinHeap(a);
        for(int i = a.length - 1; i >= 1; i--){

            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapSize--;
            minHeapify(a, 0);
        }
    }*/

    //may need a few other methods
}