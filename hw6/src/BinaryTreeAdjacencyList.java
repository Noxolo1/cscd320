import java.util.LinkedList;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}

public class BinaryTreeAdjacencyList {

    public static LinkedList<Integer>[] createAdjacencyList(TreeNode root, LinkedList<Integer>[] adjacencyList) {

        if (root != null) {
            // If the linked list at this index is null, initialize it
            if (adjacencyList[root.key] == null) {
                adjacencyList[root.key] = new LinkedList<>();
            }

            // Recursively process left and right children
            if (root.left != null) {
                adjacencyList[root.key].add(root.left.key);
                createAdjacencyList(root.left, adjacencyList);
            }

            if (root.right != null) {
                adjacencyList[root.key].add(root.right.key);
                createAdjacencyList(root.right, adjacencyList);
            }
        }

        return adjacencyList;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Assuming n is the number of nodes in the tree
        int n = 8; // assuming keys are from 1 to n

        // Create an array of linked lists for the adjacency list
        LinkedList<Integer>[] adjacencyList = new LinkedList[n];

        // Create the adjacency list
        createAdjacencyList(root, adjacencyList);

        // Print the adjacency list
        /*for (int i = 1; i < n; i++) {
            System.out.print(i + ": ");
            if (adjacencyList[i] != null) {
                for (int neighbor : adjacencyList[i]) {
                    System.out.print(neighbor + " ");
                }
            }
            System.out.println();
        }*/

        System.out.println();
    }
}
