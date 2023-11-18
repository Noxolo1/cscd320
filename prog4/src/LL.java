import java.util.*;

public class LL {


    /*private static class Node
    {
        public
        public Node next;

        public Node()
        {
            this.data = null;
            this.next = null;
        }

        public Node(final T data)
        {
            this(data, null);
        }

        public Node(final T data, final Node next)
        {
            this.data = data;
            this.next = next;
        }
    }*/

    public static class Node{

        public int value, d, f;
        public boolean visited;
        public Node p;

        public Node next;

        public Node(int value, int d, int f, boolean visited) {
            this.value = value;
            this.d = d;
            this.f = f;
            this.visited = visited;
            this.next = null;
            this.p = null;
        }
    }

    public Node head;
    public int size;

    public LL(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    /*
     * Constructs a list with no dummy head node containing the elements of the
     * specified array, that are in the order the array.
     *
     * @param array Representing the T array
     *
     * @throws IllegalArgumentException if the array is null
     */
    /*public LinkedList(final T [] array){

        if(array == null){
            throw new IllegalArgumentException("Parameter can't be null");
        }

        for(int i = 0; i < array.length; i++){

            add(array[i]);
        }

    }*/


    /*
     * Inserts the specified element at the beginning of this list.
     *
     * @param item The item to add
     *
     * @throws IllegalArgumentException if item is null
     */

    public void addFirst(Node node){

        //check if list is empty
        if (head != null){

            node.next = head;
        }

        head = node;
        size++;

    }// end addFirst


    /*
     * Appends the specified element to the end of this list.
     *
     * @param item The element to be appended to this list
     *
     * @throws IllegalArgumentException if item is null
     */
    /*public void add(final T item){

        if(item == null){
            throw new IllegalArgumentException("Parameter can't be null");
        }

        if(head == null){

            addFirst(item);
        }
        //need to iterate through list and attach newNode to end
        else{

            Node<T> current = head;

            for(int i = 0; i < size - 1; i++){

                current = current.next;
            }

            //add new node to end of the list
            current.next = new Node<>(item);
            size++;
        }

    }*/

    /*
     * Inserts all of the elements in the specified array into this list,
     * starting at the specified position. Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (increases their indices).
     * The new elements will appear in the list in the order that they were in the array
     *
     * @param index at which to insert the first element from the specified array
     * @param array containing elements to be added to this list
     *
     * @throws IllegalArgumentException if the array is null
     * @throws IndexOutOfBoundsException if index less than 0 or greater than size
     */
    /*public void addAll(final int index, final Node [] array){

        if(array == null){
            throw new IllegalArgumentException("Parameter can't be null");
        }

        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        //case where list is empty
        if(head == null){

            for(int i = 0; i < array.length; i++){

                add(array[i]);
            }
        }
        //case where you are inserting at end of list
        else if(index == size){

            for(int i = 0; i < array.length; i++){

                add(array[i]);
            }
        }
        //case where you are inserting at the start of list
        else if(index == 0){

            //can use add first, just reserve order in which elements are being taken from the array
            for(int i = array.length - 1; i > -1; i--){

                addFirst(array[i]);
            }
        }
        //case where we insert somewhere in the list not at start or end
        else{

            Node<T> current = head;

            //iterate through list to node before insertion point
            for (int i = 0; i < index - 1; i++){

                current = current.next;
            }

            Node temp = current.next;

            //add nodes from array
            for(int k = 0; k < array.length; k++){

                current.next = new Node(array[k]);
                current = current.next;
                size++;
            }

            //complete connection here
            current.next = temp;

        }

    }*/

    /*
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear(){

        if (head != null) {

            head = null;
        }

        size = 0;
    }// end clear

    /*
     * Returns the element at the specified position in this list.
     *
     * @param index The index of the element to return
     * @return T The element at the specified position in this list
     *
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to size
     */
    public Node get(final int index){

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node current = head;

        for(int i = 0; i < index; i++){

            current = current.next;
        }

        return current;
    }// end get

    /*
     * Returns the last element in this list.
     *
     * @return The last element in the list
     *
     * @throws NoSuchElementException if the list is empty
     */
    public Node getLast(){

        if(head == null){
            throw new NoSuchElementException("The list is empty");
        }

        Node current = head;

        for(int i = 0; i < size - 1; i++){

            current = current.next;
        }

        return current;
    }// end getLast

    /*
     * Retrieves and removes the head (first element) of this list.
     *
     * @return The head element in the list
     *
     * @throws NoSuchElementException if the list is empty
     */
    public Node remove(){

        if(head == null){
            throw new NoSuchElementException("The list is empty");
        }

        Node temp = head;

        head = head.next;
        size--;

        return temp;
    }// end remove

    /*
     * Returns true if all occurrences of data are removed from the list or false otherwise
     *
     * @param data The value for all occurrences to be removed from the list
     * @return boolean Representing if the list was modified or not
     *
     * @throws IllegalArgumentException if data is null
     */
    public boolean removeAllOccurrences(final Node node){

        if(node == null){
            throw new IllegalArgumentException("Parameter can't be null");
        }

        Node current = head;
        Node temp = null;

        for(int i = 0; i < size; i++){

            if(current.value == node.value){

                //keeping track of whether a node is removed or not
                temp = current;
                current = current.next;
                remove(i);
                //need to decrement i after removing a node otherwise index will be messed up
                i--;
            }
            //case where current data doesn't match, keep iterating through list
            else{

                current = current.next;
            }
        }

        //return based off whether element was removed or not
        return temp != null;

    }// end removeAllOccurrences

    /*
     * Removes and returns the last element from this list.
     *
     * @return T the last element from the list
     *
     * @throws NoSuchElementException if this list is empty
     */
    public Node removeLast(){

        if(head == null){
            throw new NoSuchElementException("The list is empty");
        }

        Node current = head;
        Node temp = head;

        for(int i = 0; i < size - 2; i++){

            current = current.next;
        }

        temp = current.next;

        //sever link between penultimate node and last node
        current.next = null;
        size--;

        return temp;
    }// end removeLast

    /*
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return T The element previously at the specified position
     *
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to size
     */
    public Node remove(int index){

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node prev = head;
        Node temp = head;

        if(index == 0){
            return remove();
        }
        else if(index == size - 1){
            return removeLast();
        }
        else{

            for(int i = 0; i < index - 1; i++){

                prev = prev.next;
            }

            temp = prev.next;

            //skip over temp node
            prev.next = temp.next;
            temp.next = null;
            size--;
        }

        return temp;
    }// end remove

    /*
     * Returns the number of elements in this list.
     *
     * @return int The size
     */
    public int size(){

        return this.size;
    }// end size


    /*
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <br> The returned array will be "safe" in that no references to it are maintained
     * by this list. (In other words, this method must allocate a new array).
     * The caller is thus free to modify the returned array.
     *
     * @return Object [] an array containing all of the elements in this list in proper sequence
     */
    /*public Object [] toArray(){

        Object[] newArray = new Object[size];

        Node<T> current = head;

        for(int i = 0; i < newArray.length; i++){

            newArray[i] = current.data;
            current = current.next;
        }

        return newArray;
    } */

    /*
     * Returns the contents of the list in the format [item, item, item]
     * or Empty List if the list is empty
     *
     * @return String Representing the contents of the list
     */
    public String toString(){

        if(head == null){
            return "";
        }
        else{

            Node current = head;

            StringBuilder result = new StringBuilder("[");

            for(int i=0;i<size;i++){

                result.append(current.value);
                current = current.next;
                if(current != null)
                    result.append(", ");
                else
                    result.append("]");
            }
            return result.toString();
        }

    }

    public static void main(String[] args){

        LL list = new LL(null, 0);

        Node node1 = new Node(1, 0, 0, false);
        Node node2 = new Node(2, 0, 0, false);
        Node node3 = new Node(3, 0, 0, false);
        Node node4 = new Node(4, 0, 0, false);
        Node node5 = new Node(5, 0, 0, false);
        list.addFirst(node1);
        list.addFirst(node2);
        list.addFirst(node3);
        list.addFirst(node4);
        list.addFirst(node5);
    }

}// end list