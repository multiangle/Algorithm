package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/5/18.
 */

import multiangle.algorithm.BinaryTree.BinaryTree ;
import multiangle.algorithm.BinaryTree.BinaryTreeNode ;
import multiangle.algorithm.Stack;

public class BinarySearchTree extends BinaryTree{
    // root
    private BinaryTreeNode _hot = null ;

    public BinarySearchTree(BinaryTreeNode root){
        super(root);
        _hot = null ;
    }
    public BinarySearchTree(){
        super();
        _hot = null ;
    }

    public BinaryTreeNode search(int target){
        _hot = null ; // 清空_hot
        BinaryTreeNode node = root ;
        while(true){
            if (target == node.value) return node ;
            if (target < node.value){
                _hot = node ;
                if (node.hasLChild()) {
                    node = node.left ;
                }
                else return null ;
            }else{
                _hot = node ;
                if (node.hasRChild()) {
                    node = node.right ;
                }
                else return null ;
            }
        }
    }

    public boolean insert(int value){
        if (this.root==null){
            this.root = new BinaryTreeNode(value) ;
            System.out.println(this.root.getClass());
            return true ;
        }

        BinaryTreeNode res = search(value) ;
        if (res==null) {
            if (value<this._hot.value) this._hot.left = new BinaryTreeNode(value) ;
            else this._hot.right = new BinaryTreeNode(value) ;
            return true ;
        }else{
            return false ;
        }

    }

    public boolean remove(int value){
        BinaryTreeNode res = search(value) ;
        if (res==null) return false ;
        if (res==root) {root=null ;return true ;}
        if (res.left==null) {
            if (_hot.left == res) _hot.left = res.right ;
            else _hot.right = res.right ;
        }
        else if (res.right==null){
            if (_hot.left == res) _hot.left = res.left ;
            else _hot.right = res.left ;
        }
        else{
            // 若左右结点均不为空
            BinaryTreeNode next = nextNode(value) ;
            int temp = res.value ;
            res.value = next.value ;
            next.value = temp ;
            remove(value) ;
        }
        return true ;

    }

    public BinaryTreeNode nextNode(int value){
        BinaryTreeNode ret = null ;
        boolean finded = false ;
        boolean goon = true ;

        Stack<BinaryTreeNode> stack = new Stack() ;
        BinaryTreeNode node = root ;
        while (goon){
            // go along left tree
            while (node!=null) {
                stack.push(node) ;
                node = node.left ;
            }
            if (stack.isEmpty()) break ;
            // visit node
            node = stack.pop() ;
            if (finded) {ret = node; goon=false; }
            if (node.value==value) finded=true ;
            node = node.right ;
        }
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
        BinarySearchTree bst = new BinarySearchTree(btn1) ;
        System.out.println(bst.nextNode(4));
    }
}
