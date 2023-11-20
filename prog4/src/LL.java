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

        public Node(int value) {
            this.value = value;
            this.d = 0;
            this.f = 0;
            this.visited = false;
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

    public void addFirst(Node node){

        //check if list is empty
        if (head != null){

            node.next = head;
        }

        head = node;
        size++;

    }

    public Node find(int value){

        Node cur = head;

        while(cur != null){
            if(cur.value == value){
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }

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


    public int size(){

        return this.size;
    }// end size

}// end list