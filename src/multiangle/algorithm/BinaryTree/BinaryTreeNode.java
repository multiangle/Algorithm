package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/4/20.
 */
public class BinaryTreeNode {
    public int value ;
    public BinaryTreeNode left ;
    public BinaryTreeNode right ;

    BinaryTreeNode(int val){
        this.value = val ;
    }

    public String toString(){
        return Integer.toString(value);
    }
    public boolean hasLChild(){
        return (left!=null) ;
    }
    public boolean hasRChild(){
        return (right!=null);
    }
}
