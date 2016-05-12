package multiangle.algorithm.BinaryTree;

import multiangle.algorithm.Stack;

/**
 * Created by multiangle on 2016/4/20.
 */

public class BinaryTree{
    // _size,root

    public boolean isEmpty(BinaryTreeNode root){
        if (root == null) return true ;
        return false ;
    }
    public static int getSize(BinaryTreeNode root){
        if (root==null) return 0 ;
        int left = getSize(root.left) ;
        int right = getSize(root.right) ;
        return left + right + 1 ;
    }
    public static int getHeight(BinaryTreeNode root){
        if (root==null) return -1 ;
        int left = getHeight(root.left) ;
        int right = getHeight(root.right) ;
        return Math.max(left,right) + 1 ;
    }

    public static String travPre(BinaryTreeNode root){
        Stack stack = new Stack<BinaryTreeNode>() ;
        BinaryTreeNode node = null ;
        stack.push(root) ;
        String ret_str = "" ;
        while (!stack.isEmpty()){
            node = (BinaryTreeNode)stack.pop() ;
            while (node!=null) {
                ret_str = ret_str.concat(Integer.toString(node.value)) ;
                if (node.right!=null) stack.push(node.right) ;
                node = node.left ;
            }
        }
        return ret_str ;
    }
    public static String travIn(BinaryTreeNode root){
        Stack stack = new Stack<BinaryTreeNode>() ;
        BinaryTreeNode node = null ;
        stack.push(root) ;
        String ret_str = "" ;
        while (!stack.isEmpty()){
            node = (BinaryTreeNode)stack.pop() ;
            // go along left tree
            while (node!=null) {
                stack.push(node) ;
                node = node.left ;
            }
            node = (BinaryTreeNode)stack.pop() ;
            while (!stack.isEmpty() && node.right==null){
                ret_str = ret_str.concat(Integer.toString(node.value)) ;
                node = (BinaryTreeNode)stack.pop() ;
            }
            if (stack.isEmpty()) return ret_str ;
            stack.push(node.right) ;
            System.out.println(stack);
        }
        return ret_str ;
    }
    public void travLevel(){

    }
    public void travPost(){

    }

    public static void main(String[] args){
        BinaryTreeNode btn1 = new BinaryTreeNode(5) ;
        BinaryTreeNode btn2 = new BinaryTreeNode(4) ;
        BinaryTreeNode btn3 = new BinaryTreeNode(7) ;
        BinaryTreeNode btn4 = new BinaryTreeNode(2) ;
        btn1.left = btn2 ;
        btn1.right = btn3 ;
        btn2.left = btn4 ;
        System.out.println(travPre(btn1));
        System.out.println(travIn(btn1));
    }
}