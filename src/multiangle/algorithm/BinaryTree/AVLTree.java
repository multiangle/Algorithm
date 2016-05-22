package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/5/22.
 */

public class AVLTree extends BinarySearchTree {
    private AVLNode root ;
    private AVLNode _hot ;

    public AVLTree(AVLNode root){
        this.root = root ;
        _hot = null ;
    }
    public AVLTree(){
        root = null ;
        _hot = null;
    }

    public void calBalFac(){
        calBalFac(root);
    }
    private void calBalFac(AVLNode root){
        if ((root.left==null)&&(root.right==null)) {root.balFac = 0; return ;}
        if (root.left==null) {root.balFac = -1; return ;}
        if (root.right==null){root.balFac = 1; return ;}
        // 此时是左右结点均不为空的情况
        calBalFac(root.left);
        calBalFac(root.right);
        root.balFac = root.left.balFac-root.right.balFac ;
    }

//    @Override
//    public boolean insert(int value){
//        return true ;
//    }
//
//    @Override
//    public boolean remove(int value){
//        return true ;
//    }

    public static void main(String[] args){

        AVLTree at = new AVLTree() ;
        at.insert(1) ;
        at.insert(2) ;
        at.insert(3) ;
        at.insert(4) ;
        at.insert(5) ;
        System.out.println(at.travIn());
    }

}
