package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// own insensitive 
public class BinarySearchTree<K extends Comparable<K>> implements Iterable<K> {

    private class Node {
        K key; 
        Node left, right;

        Node(K key) {
            this.key = key;
            this.left = this.right = null;
        }

        // Taken from https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
        public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if(right!=null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(key.toString()).append("\n");
            if(left!=null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }
        // 
    }

    private Node root;
    private int size;

    // initialize new BST
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int height() {
        return height(root);
    }

    // Helper method
    private int height(Node node) {
        if (node == null) {
            return -1;
        }

        int left_height = height(node.left);
        int right_height = height(node.right);

        return Math.max(left_height, right_height) +1; // +1 for root
    }

    public int size() {
        return size;
    }

    public void add(K key) {
        root = add(root, key);
    }

    // Helper method 
    private Node add(Node node, K key) {
        if (node == null) {
            size++;
            return new Node(key);
        }

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = add(node.left, key);
        } else if (comp > 0) {
            node.right = add(node.right, key);
        }

        return node;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = remove(node.left, key);
        } else if (comp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node minRight = findMin(node.right);
            node.key = minRight.key;
            node.right = remove(node.right, minRight.key);
        }

        return node;
         
    }

    // Helper
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean contaions(K key) {
        return contaions(root, key);
    }
    
    // Helper
    private boolean contaions(Node node, K key) {
        if (node == null) {
            return false;
        }

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            return contaions(node.left, key);
        } else if (comp > 0) {
            return contaions(node.right, key);
        } else {
            return true;
        }
    }

    public K removeKthLargest(int k) {
        int[] count = new int[1];
        return removeKthLargest(root, k, count);
    }

    // Helper
    private K removeKthLargest(Node node, int k, int[] count) {
        if (node == null || count[0] >= k) {
            return null;
        }

        K result = removeKthLargest(node.right, k, count);
        if (result != null) {
            return result;
        }
        
        count[0]++;
        if (count[0] == k) {
            remove(node.key);
            return node.key;
        }

        return removeKthLargest(node.left, k, count);
    }

    // Taken from https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        return root.toString(new StringBuilder(), true, result).toString();
    }
    //

    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        return new InOrderIterator();
    }
    
    private class InOrderIterator implements Iterator<K> {
        private Node current;
        private Stack<Node> stack;

        InOrderIterator(){
            current = root;
            stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public K next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                Node node = stack.pop();
                current= node.right;
                return node.key;
            }

            throw new NoSuchElementException("No more elements in the BST...");
        }
    }

    public Iterator<K> preOrderIterator() {
        return new PreOrderIterator();
    }

    private class PreOrderIterator implements Iterator<K> {
        private Node current;
        private Stack<Node> stack;

        PreOrderIterator() {
            stack = new Stack<>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
           return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the BST...");
            }

            current = stack.pop();
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }

            return current.key;
        }
    }
    
    public Iterator<K> postOrderIterator() {
        return new PostOrderIterator();
    }

    private class PostOrderIterator implements Iterator<K> {
        private Node current;
        private Stack<Node> stack;
        private Stack<Node> visited;

        private PostOrderIterator() {
            current = root;
            stack = new Stack<>();
            visited = new Stack<>();
        }
        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public K next() {
            while (current != null) {
                stack.push(current);
                visited.push(current);
                current = current.right;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.left;
            }

            return visited.pop().key;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println("Adding the following integers in order: 3, 1, 7, 2, 4, 8, 6, 9");
        bst.add(3);
        bst.add(1);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(8);
        bst.add(6);
        bst.add(9);
        // bst.add(1);
        // bst.add(2);
        // bst.add(3);
        // bst.add(4);
        // bst.add(5);
        // bst.add(6);
        // bst.add(7);
        // bst.add(8);
        // bst.add(9);
        // bst.add(10);
        
        System.out.println("BST Tree Structure:");
        System.out.println(bst);

        System.out.println("In-order traversal: ");
        for (Integer key : bst) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("Pre-order traversal: ");
        Iterator<Integer> preOrderIterator = bst.preOrderIterator();
        while (preOrderIterator.hasNext()) {
            System.out.print(preOrderIterator.next() + " ");
        }
        System.out.println();

        System.out.println("Post-order traversal: ");
        Iterator<Integer> postOrderIterator = bst.postOrderIterator();
        while (postOrderIterator.hasNext()) {
            System.out.print(postOrderIterator.next() + " ");
        }
        System.out.println();

        int k = 3;
        try {
            int kthLargest = bst.removeKthLargest(k);
            System.out.println("Removed " + k + "-th largest value: " + kthLargest);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }







}
