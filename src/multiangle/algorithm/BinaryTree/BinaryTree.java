package multiangle.algorithm.BinaryTree;

import multiangle.algorithm.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by multiangle on 2016/4/20.
 */

public class BinaryTree{
    protected BinaryTreeNode root ;

    BinaryTree(){
        this.root = null ;
    }
    BinaryTree(BinaryTreeNode root){
        this.root = root ;
    }

    public boolean isEmpty(){
        if (root == null) return true ;
        return false ;
    }
    public int getSize(){
        return getSize(root) ;
    }
    public int getSize(BinaryTreeNode root){
        if (root==null) return 0 ;
        int left = getSize(root.left) ;
        int right = getSize(root.right) ;
        return left + right + 1 ;
    }
    public int getHeight(){
        return getHeight(root) ;
    }
    public int getHeight(BinaryTreeNode root){
        if (root==null) return -1 ;
        int left = getHeight(root.left) ;
        int right = getHeight(root.right) ;
        return Math.max(left,right) + 1 ;
    }
    public String travLevel(){
        return travLevel(root) ;
    }
    public String travLevel(BinaryTreeNode root){
        ArrayList<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>() ;
        String ret_str = "" ;
        BinaryTreeNode node = null ;
        list.add(root);
        while (!list.isEmpty()){
            node = list.remove(0) ;
            ret_str = ret_str.concat(Integer.toString(node.value)) ;
            if (node.left!=null) list.add(node.left) ;
            if (node.right!=null) list.add(node.right) ;
        }
        return ret_str ;
    }
    public String travIn(){
        return travIn(root) ;
    }
    public String travIn(BinaryTreeNode in){
        Stack<BinaryTreeNode> stack = new Stack() ;
        BinaryTreeNode node = in ;
        String ret_str = "" ;
        while (true){
            // go along left tree
            while (node!=null) {
                stack.push( node) ;
                node = node.left ;
            }
            if (stack.isEmpty()) break ;
            // visit node
            node = stack.pop() ;
            ret_str = ret_str.concat(Integer.toString(node.value)) ;
            node = node.right ;
        }
        return ret_str ;
    }
    public String travPre(){
        return travPre(root) ;
    }
    public String travPre(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack() ;
        stack.push(root);
        BinaryTreeNode node = null ;
        String ret_str = "" ;
        while (!stack.isEmpty()){
            node = stack.pop() ;
            while (node!=null) {
                ret_str = ret_str.concat(Integer.toString(node.value)) ;
                if (node.right!=null) stack.push(node.right) ;
                node = node.left ;
            }
        }
        return ret_str ;
    }
    public String travPost(){
        return travPost(root) ;
    }
    public String travPost(BinaryTreeNode root){
        if (root == null) return "" ;
        String r_l = travPost(root.left) ;
        String r_r = travPost(root.right) ;
        String ret = r_l.concat(r_r).concat(Integer.toString(root.value)) ;
        return ret ;
    }
    public static void main(String[] args){
        BinaryTreeNode btn1 = new BinaryTreeNode(1) ;
        BinaryTreeNode btn2 = new BinaryTreeNode(2) ;
        BinaryTreeNode btn3 = new BinaryTreeNode(3) ;
        BinaryTreeNode btn4 = new BinaryTreeNode(4) ;
        BinaryTreeNode btn5 = new BinaryTreeNode(5) ;
        btn1.left = btn2 ;
        btn1.right = btn3 ;
        btn2.left = btn4 ;
        btn3.right = btn5 ;
        BinaryTree bt = new BinaryTree(btn1) ;
        System.out.println(bt.getSize());
    }
}