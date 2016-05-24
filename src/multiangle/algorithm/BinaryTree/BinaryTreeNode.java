package multiangle.algorithm.BinaryTree;

/**
 * Created by multiangle on 2016/4/20.
 */
public class BinaryTreeNode {
    public int value ;
    public BinaryTreeNode left=null ;
    public BinaryTreeNode right=null ;

    BinaryTreeNode(int val){
        this.value = val ;
    }
    public boolean hasLChild(){
        return (this.left!=null) ;
    }
    public boolean hasRChild(){
        return (this.right!=null);
    }
    public boolean valueEqual(int i){
        return this.value==i;
    }
    public String toString(){
        return Integer.toString(value);
    }
    public boolean isLeft(BinaryTreeNode test){
        if (left==null||test==null) return false ;
        return left.value==test.value ;
    }
    public boolean isRight(BinaryTreeNode test){
        if (right==null||test==null) return false ;
        return right.value==test.value ;
    }
}
