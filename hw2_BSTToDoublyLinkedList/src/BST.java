public class BST {
    Node root, head;

    // set previous node to null here to use as a check for an empty linked list
    // in BSTToDoublyLinkedList method
    Node previous = null;

    BST(){
        this.root = null;
    }

    // all keys are distinct in BST
    // no parent link, just right and left child links
    // doubly linked list should be in ascending order

    // can not allocate new space for the doubly linked list
    // must instead recycle/reuse existing tree nodes

    // re-link all the tree nodes using the existing left/right
    // child pointers to build up the double linked list

    // want to use inorder traversal (left, root, right) for ascending order
    // while converting bst to doubly linked list

    // method to convert BST to Doubly Linked list
    public void BSTToDoublyLinkedList(final Node root) {

        // all keys are distinct in BST
        // no parent link, just right and left child links
        // doubly linked list should be in ascending order

        // can not allocate new space for the doubly linked list
        // must instead recycle/reuse existing tree nodes

        // re-link all the tree nodes using the existing left/right
        // child pointers to build up the double linked list

        // want to use inorder traversal (left, root, right) for ascending order
        // while converting bst to doubly linked list

        // base case, when root is null we have reached bottom of BST
        // and should return (i.e. bounce back up to previous node)
        if(root == null){
            return;
        }

        // need to recurse down left subtree first
        // to find the smallest number to start the doubly linked list
        // this node will be the head of doubly linked list
        // first step of inorder traversal
        BSTToDoublyLinkedList(root.left);

        // want to check if previous is null after recursing down left subtree
        // if previous is null then we know that we are at the smallest node in BST,
        // and thus we assign the head to the root node
        if (previous == null) {
            head = root;
        }

        // this is the crucial step for creating the links for doubly linked list.
        // previous nodes right pointer should be assigned to root node
        // (i.e. smaller node's right pointer is assigned to next smallest node)
        // then root nodes left pointer should be assigned to previous node
        // (i.e. next smallest node's left pointer is assigned to the smallest node)
        // this gives the ascending order for the doubly linked list
        // see pictures above for image of linking process
        else {
            previous.right = root;
            root.left = previous;
        }

        //the else block above and previous = root statement below
        // are the second step of inorder traversal where we look at root node

        // before recursing on right subtree of root node,
        // set previous node to root node to reset previous node for linking process
        // (i.e. we are done with the root node and now what to consider that root
        // node the "new" previous node for the right subtree traversal)
        previous = root;

        // recurse on right subtree for
        // last step of inorder traversal
        BSTToDoublyLinkedList(root.right);
    }

    // insertion method for BST
    public Node insert(final int k){

        Node nn = new Node(k);

        Node y, x;

        y = null; x = root;

        while(x != null){
            y = x;

            // case for duplicate keys, dont insert if key already in BST
            if(k == x.key){
                return null;
            }
            // go down left subtree if k smaller than root
            else if (k < x.key){
                x = x.left;
            }
            // otherwise go right
            else{
                x = x.right;
            }
        }

        if(y == null){
            root = nn;
        }
        else if(k < y.key){
            y.left = nn;
        }
        else{
            y.right = nn;
        }

        return nn;
    }

    // print doubly linked list
    void printDoublyLinkedList(Node node)
    {
        while (node != null) {

            System.out.print(node.key + " ");
            node = node.right;
        }

        System.out.println();
    }
}
