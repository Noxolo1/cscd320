public class Node{

    public int value, d, f;
    public boolean visited;
    public Node p;
    public Node next;

    public Node(int value, int d, int f, boolean visited, Node p) {
        this.value = value;
        this.d = d;
        this.f = f;
        this.visited = visited;
        this.p = p;
    }
}