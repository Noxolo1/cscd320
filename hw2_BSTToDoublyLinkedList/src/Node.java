public class Node {

    int key;

    //no parent link
    Node left, right;

    Node(final int k){
        this.key = k;
        this.left = this.right = null;
    }

}
