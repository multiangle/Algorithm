package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/5/18.
 */

import multiangle.algorithm.BinaryTree.BinaryTree ;
import multiangle.algorithm.BinaryTree.BinaryTreeNode ;

public class BinarySearchTree extends BinaryTree{
    private BinaryTreeNode root ;
    private BinaryTreeNode _hot = null ;
    BinarySearchTree(BinaryTreeNode root){
        this.root = root ;
    }

    public BinaryTreeNode search(int target){
        _hot = null ; // 清空_hot
        BinaryTreeNode node = root ;
        while(true){
            if (target == node.value) return node ;
            if (target < node.value){
                if (node.hasLChild()) {
                    _hot = node ;
                    node = node.left ;
                }
                else return null ;
            }else{
                if (node.hasRChild()) {
                    _hot = node ;
                    node = node.right ;
                }
                else return null ;
            }
        }
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
        System.out.println(travIn(btn1));
        System.out.println(getHeight(btn1));
    }
}
