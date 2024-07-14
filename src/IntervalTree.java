import java.util.ArrayList;
import java.util.List;

class IntervalTree {
    private class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private class Node {
        Interval interval;
        int max;
        Node left, right;
        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.end;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public IntervalTree() {
        root = null;
    }

    public void insertInterval(int start, int end) {
        root = insert(root, new Interval(start, end));
    }

    private Node insert(Node node, Interval interval) {
        if (node == null)
            return new Node(interval);

        int low = node.interval.start;
        if (interval.start < low)
            node.left = insert(node.left, interval);
        else
            node.right = insert(node.right, interval);

        if (node.max < interval.end)
            node.max = interval.end;

        return node;
    }

    public void deleteInterval(int start, int end) {
        root = delete(root, new Interval(start, end));
    }

    private Node delete(Node node, Interval interval) {
        if (node == null)
            return null;

        if (interval.start < node.interval.start)
            node.left = delete(node.left, interval);
        else if (interval.start > node.interval.start)
            node.right = delete(node.right, interval);
        else if (interval.end == node.interval.end) {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Node min = findMin(node.right);
                node.interval = min.interval;
                node.right = delete(node.right, min.interval);
            }
        }

        node.max = Math.max(node.interval.end, Math.max(max(node.left), max(node.right)));

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private int max(Node node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }

    public List<int[]> findOverlappingIntervals(int start, int end) {
        List<int[]> result = new ArrayList<>();
        findOverlapping(root, new Interval(start, end), result);
        return result;
    }

    private void findOverlapping(Node node, Interval interval, List<int[]> result) {
        if (node == null)
            return;

        if (isOverlapping(node.interval, interval))
            result.add(new int[]{node.interval.start, node.interval.end});

        if (node.left != null && node.left.max >= interval.start)
            findOverlapping(node.left, interval, result);

        findOverlapping(node.right, interval, result);
    }

    private boolean isOverlapping(Interval a, Interval b) {
        return a.start <= b.end && b.start <= a.end;
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        tree.insertInterval(15, 20);
        tree.insertInterval(10, 30);
        tree.insertInterval(17, 19);
        tree.insertInterval(5, 20);
        tree.insertInterval(12, 15);
        tree.insertInterval(30, 40);

        System.out.println("Overlapping intervals with [14, 16]:");
        for (int[] interval : tree.findOverlappingIntervals(14, 16)) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

        tree.deleteInterval(10, 30);
        System.out.println("Overlapping intervals with [14, 16] after deleting [10, 30]:");
        for (int[] interval : tree.findOverlappingIntervals(14, 16)) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
