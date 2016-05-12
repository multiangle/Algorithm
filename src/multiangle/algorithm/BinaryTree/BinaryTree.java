package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/4/20.
 */

public class BinaryTree extends BinaryTreeAbs{
    // _size,root

    public int getSize(BinaryTreeNode root){
        if (root==null) return 0 ;
        int left = getSize(root.left) ;
        int right = getSize(root.right) ;
        return left + right + 1 ;
    }
    public boolean isEmpty(BinaryTreeNode root){
        if (root == null) return true ;
        return false ;
    }
    public int getHeight(BinaryTreeNode root){
        if (root==null) return -1 ;
        int left = getSize(root.left) ;
        int right = getSize(root.right) ;
        return Math.max(left,right) + 1 ;
    }

    public void travLevel(){

    }
    public void travIn(){

    }
    public void travPre(){

    }
    public void travPost(){

    }
}