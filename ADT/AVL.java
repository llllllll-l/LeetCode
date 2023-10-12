package ADT;

import java.util.Iterator;

// own insensitive 
public class AVL<K extends Comparable<K>> implements Iterable<K>{
    
    private class Node {
        K key;
        Node left, right;
        int height;

        Node(K key) {
            this.key = key;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root;

    private int height(Node t) {
        return t == null ? -1 : t.height;
    }

    public void insert(K key) {
        root = insert(root, key);
    }
    private Node insert(Node node, K value) {
        if (node == null) {
            return new Node(value);
        }
        int balancing = value.compareTo(node.key); // balancing 
        
        if (balancing < 0) {
            node.left = insert(node.left, value);
        }


        return null;

    }

    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
}
