package ADT;

import java.util.Iterator;

public class SplayTree<K extends Comparable<K>> implements Iterable<K> {
    
    private static class SplayNode<K> {
            K key;
            SplayNode<K> left, right;
            int height;

            SplayNode(K key) {
                this.key = key;
                this.height = 1;
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
            //
        }
         
    }

    public SplayNode<K> root;

    public void insert(K key) {
        root = insert(root, key);
    }

    private SplayNode<K> insert(SplayNode<K> root, K key) {

        return null;
    }

    private SplayNode<K> zigRotation(SplayNode<K> k2) { // left rotation 
        SplayNode<K> k1 = k2.left;
        k2.right = k1.left;
        k1.left = k2;
        return k1;
    }

    private SplayNode<K> zagRotation(SplayNode<K> k2) { // right rotation
        SplayNode<K> k1 = k2.right;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    private void splay() {
    }

    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }


    public static void main(String[] args) {

        
    }
}
