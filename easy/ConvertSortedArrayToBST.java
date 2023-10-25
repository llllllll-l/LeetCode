package easy;
// 108
public class ConvertSortedArrayToBST {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right -left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, left, mid-1);
        root.right = sortedArrayToBST(nums, mid+1, right);

        return root;
    }

     // Helper function to print the BST (in-order traversal)
     public void printBST(TreeNode root) {
        if (root != null) {
            printBST(root.left);
            System.out.print(root.val + " ");
            printBST(root.right);
        }
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBST bst = new ConvertSortedArrayToBST();
        int[] nums = {-10,-3,0,5,9};

        TreeNode root = bst.sortedArrayToBST(nums); 
        bst.printBST(root);
        
    }
}
