package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// own insensitive 
public class AVLTree<K extends Comparable<K>> implements Iterable<K>{
    
    private class AVLNode<K> {
        K key;
        AVLNode<K> left, right;
        int height;

        AVLNode(K key) {
            this.key = key;
            this.height = 1;
        }
    }

    private AVLNode<K> root;

    private int height(AVLNode<K> t) {
        return t == null ? 0 : t.height;
    }

    public void insert(K key) {
        root = insert(root, key);
    }
    
    private AVLNode<K> insert(AVLNode<K> node, K key) {
        if (node == null) {
            return new AVLNode<K>(key); // setting a root
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.right) > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        // updating tree height
        updateHeight(node);
        
        int balance = getBalance(node);

        if (balance > 1) { // 1 is th maximun deviation of the subtrees height
            if (key.compareTo(node.left.key) < 0) {
                return rightRotation(node); // singel right rotation
            } else {
                node.left = leftRotation(node.left); // double left-right rotation | node.left = k3.left
                return rightRotation(node);
            }
        } else if (balance < -1) {
            if (key.compareTo(node.right.key) > 0) {
                return leftRotation(node);
            } else {
                node.right = rightRotation(node.right); // double right-left rotation | node.right = k3.right
                return leftRotation(node);
            }
        }
        return node;
    }

    public void delete(K key) {
        root = delete(root, key); 
    }

   
    private AVLNode<K> delete(AVLNode<K> node, K key) {
        if (node == null) {
            return node;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if ((node.left == null) || (node.right == null)) {
                AVLNode<K> temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode<K> temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }
        if (node == null) {
            return node;
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1) {
            if (key.compareTo(node.left.key) < 0) {
                return rightRotation(node);
            } else {
                node.left = leftRotation(node.left); // double left-right rotation | node.left = k3.left
                return rightRotation(node);
            }
        } else if (balance < -1) {
            if (key.compareTo(node.right.key) > 0) {
                return leftRotation(node);
            } else {
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }
        return node;
       
    }

    
    private AVLNode<K> minValueNode(AVLNode<K> node) {
        AVLNode<K> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void updateHeight(AVLNode<K> node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) +1;
        }
    }

    private AVLNode<K> leftRotation(AVLNode<K> k2) {
        if (k2.right != null || k2.left != null) {
            AVLNode<K> k1 = k2.right;
            k2.right = k1.left;
            k1.left = k2;
            updateHeight(k2);
            updateHeight(k1);
            return k1;
        }
        return null;
        
    }

    private AVLNode<K> rightRotation(AVLNode<K> k2) {
        if (k2.left != null || k2.right != null) {
            AVLNode<K> k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            updateHeight(k2);
            updateHeight(k1);
            return k1;
        }
        return null;
    }

    private int getBalance(AVLNode<K> node) {
        return node == null ? 0 : height(node.left) - height(node.right); 
    }

    @Override
    public Iterator<K> iterator() {
        return new InOrderIterator();
    }
    
    public Iterator<K> postOrderIterator() {
        return new PostOrderIterator();
    }

    public Iterator<K> preOrderIterator() {
        return new PreOrderIterator();
    }

    private class InOrderIterator implements Iterator<K> {
        private AVLNode<K> current;
        private Stack<AVLNode<K>> stack;

        InOrderIterator() {
            current = root;
            stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current != null || !stack.isEmpty();
        }

        @Override
        public K next() {
            // TODO Auto-generated method stub
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                AVLNode<K> node = stack.pop();
                current = node.right;
                return node.key;
            }

            throw new NoSuchElementException("No more elements in AVLTree");
        }
    }


    private class PostOrderIterator implements Iterator<K> {
        private AVLNode<K> current;
        private Stack<AVLNode<K>> stack;
        private Stack<AVLNode<K>> visited;

        PostOrderIterator() {
            current = root;
            stack = new Stack<>();
            visited = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current != null || !stack.isEmpty();
        }

        @Override
        public K next() {
            // TODO Auto-generated method stub
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

    private class PreOrderIterator implements Iterator<K> {
        private AVLNode<K> current;
        private Stack<AVLNode<K>> stack;

        PreOrderIterator() {
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
        AVLTree<Integer> avlTree2 = new AVLTree<>();
        avlTree2.insert(10);
        avlTree2.insert(20);
        avlTree2.insert(30);
        avlTree2.insert(40);
        avlTree2.insert(50);

        System.out.println("In-order traversal of the AVL tree:");
        Iterator<Integer> inOrder = avlTree2.iterator();
        while (inOrder.hasNext()) {
            System.out.println(inOrder.next() + " ");
        }
        
        
    }

	
}
