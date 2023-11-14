public class Main {

    public static void main(String[] args)
    {
        // create a BST with some arbitrary values
        BST bst = new BST();
        bst.insert(12);
        bst.insert(9);
        bst.insert(21);
        bst.insert(7);
        bst.insert(11);
        bst.insert(18);
        bst.insert(23);

        // convert BST to doubly Linked List
        bst.BSTToDoublyLinkedList(bst.root);

        // print doubly linked list after being converted
        bst.printDoublyLinkedList(bst.head);
    }

}