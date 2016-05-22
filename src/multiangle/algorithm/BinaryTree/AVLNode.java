package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/5/22.
 */

public class AVLNode extends BinaryTreeNode {

    public int balFac ;
    public AVLNode left ;
    public AVLNode right ;

    public AVLNode(int value){
        super(value) ;
        balFac = 0;
    }
}
