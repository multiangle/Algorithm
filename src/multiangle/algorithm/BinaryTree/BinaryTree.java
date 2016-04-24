package multiangle.algorithm.BinaryTree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by multiangle on 2016/4/20.
 */

public class BinaryTree<T> extends BinaryTreeAbs<T>{
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
        Stack<BinaryTreeNode> r_child_stack = new Stack<BinaryTreeNode>() ;
        r_child_stack.push(this.root) ;
        ArrayList<BinaryTreeNode> trav_res = new ArrayList<BinaryTreeNode>() ;
        while (!r_child_stack.isEmpty()){
            BinaryTreeNode node = r_child_stack.pop() ;
            while (node!=null){
                trav_res.add(node);
                if (node.hasRChild()) r_child_stack.push(node.right) ;
                node = node.left ;
            }
        }
    }
    public void travPost(){

    }
}