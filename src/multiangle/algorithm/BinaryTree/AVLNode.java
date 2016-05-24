package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/5/22.
 */

public class AVLNode extends BinaryTreeNode {

    public AVLNode left ;
    public AVLNode right ;
    public AVLNode parent ;
    public int balFac ;
    public int height ;

    public AVLNode(int value){
        super(value) ;
        balFac = 0;
        height = 1;
        parent = null ;
    }
    public boolean hasLChild(){
        return (this.left!=null) ;
    }
    public boolean hasRChild(){
        return (this.right!=null);
    }
    public boolean isRight(AVLNode test){
        if (right==null||test==null) return false ;
        return right.value==test.value ;
    }
    public boolean isLeft(AVLNode test){
        if (left==null||test==null) return false ;
        return left.value==test.value ;
    }
}
