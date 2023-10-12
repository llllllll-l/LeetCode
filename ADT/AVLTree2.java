package ADT;

class AVLNode<K> {
    K key;
    int height;
    AVLNode<K> left, right;

    AVLNode(K key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLTree2<K extends Comparable<K>> {
    private AVLNode<K> root;

    private int height(AVLNode<K> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(AVLNode<K> node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode<K> node) {
        if (node != null)
            node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private AVLNode<K> rightRotate(AVLNode<K> node) {
        AVLNode<K> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private AVLNode<K> leftRotate(AVLNode<K> node) {
        AVLNode<K> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    public void insert(K key) {
        root = insert(root, key);
    }

    private AVLNode<K> insert(AVLNode<K> node, K key) {
        if (node == null)
            return new AVLNode<>(key);

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1) {
            if (key.compareTo(node.left.key) < 0)
                return rightRotate(node);
            else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balance < -1) {
            if (key.compareTo(node.right.key) > 0)
                return leftRotate(node);
            else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private AVLNode<K> delete(AVLNode<K> node, K key) {
        if (node == null)
            return node;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            if ((node.left == null) || (node.right == null)) {
                AVLNode<K> temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                AVLNode<K> temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null)
            return node;

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0)
                return rightRotate(node);
            else {
                node.left = leftRotate(node);
                return rightRotate(node);
            }

                
        }
        if (balance < -1) {
            if (getBalance(node.right) <= 0)
                return leftRotate(node);
            else {
                node.right = rightRotate(node);
                return leftRotate(node);
            }
                
        }

        return node;
    }

    private AVLNode<K> minValueNode(AVLNode<K> node) {
        AVLNode<K> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(AVLNode<K> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree2<Integer> avlTree = new AVLTree2<>();

        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);

        System.out.println("In-order traversal of the AVL tree:");
        avlTree.inOrderTraversal();

        System.out.println();

        avlTree.delete(30);

        System.out.println("In-order traversal after deleting 30:");
        avlTree.inOrderTraversal();
    }
}
