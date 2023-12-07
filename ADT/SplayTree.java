package ADT;

import java.util.Iterator;
import java.util.Stack;

public class SplayTree<K extends Comparable<K>> implements Iterable<K> {
    
    public SplayNode<K> root;

    private static class SplayNode<K> {
            K key;
            SplayNode<K> left, right;
            int height;

            SplayNode(K key) {
                this.key = key;
                this.left = this.right = null;
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

    

    // public void insert(K key) {
    //     root = insert(root, key);
    //     root = splay(root, key);
        
    // }

    public void insert(K key) {
        root = splay(root, key);
        if (root == null || root.key.compareTo(key) != 0) {
            SplayNode<K> newNode = new SplayNode<K>(key);
            if (root == null) {
                root = newNode;
            } else if (key.compareTo(root.key) < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = null;
                root = newNode;
            } else {
                newNode.right = root.right;
                newNode.left = root;
                root.right = null;
                root = newNode;
            }
        }
    }

    // private SplayNode<K> insert(SplayNode<K> node, K key) {
    //     if (node == null) { // new root
    //         return new SplayNode<K>(key);
    //     }

    //     if (key.compareTo(node.key) < 0) {
    //         node.left = insert(node.left, key);
    //     } else if (key.compareTo(node.key) > 0) {
    //         node.right = insert(node.right, key);
    //     } else {
    //         // key already exists in the tree
    //         return node;
    //     }
    //     System.out.println("LALALLA");
    //     // after inserting, splay the node to root
    //     return splay(node, key);
    // }

    private SplayNode<K> zig(SplayNode<K> k2) { // right rotation 
        SplayNode<K> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    private SplayNode<K> zag(SplayNode<K> k2) { // left rotation
        SplayNode<K> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        return k1;
    }

    private SplayNode<K> splay(SplayNode<K> node, K key) {
        if (node == null || node.key.compareTo(key) != 0) {
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            if (node.left == null) {
                return node;
            }

            if (key.compareTo(node.left.key) < 0) {
                node.left.left = splay(node.left.left, key);
                node = zig(node);
            } else if (key.compareTo(node.left.key) > 0) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = zag(node.left);
                }
            }
            return (node.left == null) ? node : zig(node);
        } else {
            if (node.right == null) {
                return node;
            }

            if (key.compareTo(node.right.key) < 0) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null) {
                    node.right = zig(node.right);
                }
            } else if (key.compareTo(node.right.key) > 0) {
                node.right.right = splay(node.right.right, key);
                node = zag(node);
            }
            return (node.right != null) ? node : zag(node);
        }
    }

    public boolean contains(K key) {
        root = splay(root, key);
       
        return root != null && root.key.compareTo(key) == 0;
    }

    public void delete(K key) {
        if (root == null) {
            return;
        }

        root = splay(root, key);

        if (root.key.compareTo(key) != 0) {
            return;
        }

        if (root.left == null) {
            root = root.right;
        } else {
            SplayNode<K> newRoot = root.right;
            root = splay(root.left, key);
            root.right = newRoot;
        }
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
        return new privateIterator();
    }

    private class privateIterator implements Iterator<K> {
        private SplayNode<K> current;
        private Stack<SplayNode<K>> stack;

        privateIterator() {
            stack = new Stack<>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            // TODO Auto-generated method stub
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


    public static void main(String[] args) {
        SplayTree<Integer> splayTree = new SplayTree<>();
        splayTree.insert(5);
        splayTree.insert(7);
        splayTree.insert(9);
        splayTree.insert(6);
        splayTree.insert(1);
        splayTree.insert(3);

        System.out.println("SplayTree Structure:");
        splayTree.toString();

        
        // System.out.println("SplayTree Structure:");
        System.out.println(splayTree);

        //splayTree.contains(6);

        

        
    }
}
