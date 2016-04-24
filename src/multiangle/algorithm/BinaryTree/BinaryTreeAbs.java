package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/4/20.
 */
public abstract class BinaryTreeAbs<T> {
    protected BinaryTreeNode<T> root ;

    public abstract int getSize(BinaryTreeNode root);
    public abstract boolean isEmpty(BinaryTreeNode root) ;

    public abstract void travLevel() ;
    public abstract void travIn() ;
    public abstract void travPre() ;
    public abstract void travPost() ;
}
