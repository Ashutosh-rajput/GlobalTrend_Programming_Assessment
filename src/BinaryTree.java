import java.util.*;

public class BinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //tree to string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    // String to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode node = queue.poll();
            if (!nodes[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.left);
            }
            i++;
            if (i < nodes.length && !nodes[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }

    // For testing purposes
    public static void main(String[] args) {
        BinaryTree codec = new BinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserializedRoot = codec.deserialize(serialized);
        String reserialized = codec.serialize(deserializedRoot);
        System.out.println("Reserialized: " + reserialized);
    }
}
