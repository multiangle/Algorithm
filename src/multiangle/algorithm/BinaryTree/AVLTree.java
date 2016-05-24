package multiangle.algorithm.BinaryTree;

import multiangle.algorithm.Stack;

/**
 * Created by multiangle on 2016/5/22.
 */

public class AVLTree extends BinarySearchTree {
    private AVLNode root ;
    private AVLNode _hot ;

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

    @Override
    //如果失衡，失衡的节点再低也不会低于祖父辈
    //一旦最低级的节点恢复平衡，则所有其祖先节点都会恢复平衡
    public boolean insert(int value){
        if (root==null){
            root = new AVLNode(value) ;
            return true ;
        }
        AVLNode res = search(value) ;
        if (res!=null) return false ;
        AVLNode node = new AVLNode(value) ;
        node.parent = _hot ;
        if (value<_hot.value) _hot.left = node ;
        else _hot.right = node ;

        AVLNode g=_hot,p=node,v=null ; // 对应于旋转算法中的g,p,v g->p->v
        while (g!=null){
            updateHeight(g); // 更新该节点树的高度和balFac
            if (g.balFac>1||g.balFac<-1){
                rotateAt(g, p, v) ;
                break ;
            }
            v = p ;
            p = g ;
            g = g.parent ;
        }
        return true ;
    }
//
//    @Override
//    public boolean remove(int value){
//        return true ;
//    }

    @Override
    public AVLNode search(int target){
        if (root==null) return null ;
        _hot = null ; // 清空_hot
        AVLNode node = root ;
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

    // 注意3+4重构
    public void rotateAt(AVLNode g, AVLNode p, AVLNode v){
        AVLNode g_parent = g.parent ;
        AVLNode new_top = null ;
        if (g.isLeft(p)){
            if (p.isLeft(v)){ // zig-zig
                new_top = connect34(v, p, g, v.left, v.right, p.right, g.right);
            }else{ // zig-zag
                new_top = connect34(p, v, g, p.left, v.left, v.right, g.right);
            }
        }else{
            if (p.isLeft(v)){ // zag-zig
                new_top = connect34(g, v, p, g.left, v.left, v.right, p.right);
            }else{ // zag-zag
                new_top = connect34(g, p, v, g.left, p.left, v.left, v.right);
            }
        }
        new_top.parent = g_parent ;
        if (g_parent==null) root = new_top ;
        else{
            if (g_parent.isLeft(new_top)) g_parent.left = new_top ;
            else g_parent.right = new_top ;
        }
    }

    public AVLNode connect34(AVLNode a, AVLNode b, AVLNode c, AVLNode t1, AVLNode t2 , AVLNode t3, AVLNode t4){
        // 前三个节点分别表示g,p,v 其中 a < b < c, t1~4 分别表示从左到右4个子树
        a.left = t1 ; if(a.left!=null) a.left.parent = a ;
        a.right = t2 ; if(a.right!=null) a.right.parent = a ;updateHeight(a);
        c.left = t3 ; if(c.left!=null) c.left.parent = c ;
        c.right = t4 ; if (c.right!=null) c.right.parent = c ;updateHeight(c);
        b.left = a ; a.parent = b ;
        b.right = c ; c.parent = b ;updateHeight(b);
        return b ;
    }

    public void updateHeight(AVLNode node){
        int l_h = node.left==null? 0 : node.left.height ;
        int r_h = node.right == null? 0 : node.right.height ;
        node.height = Math.max(l_h, r_h) + 1 ;
        node.balFac = l_h - r_h ;
    }

    public static void main(String[] args){

        AVLTree at = new AVLTree() ;
        at.insert(1) ;
        at.insert(2) ;
        System.out.println("root " + at.root + " left " + at.root.left + " right " + at.root.right);
        at.insert(3) ;
        System.out.println("root " + at.root + " left "+at.root.left + " right " + at.root.right);
        at.insert(4) ;
        System.out.println("root " + at.root + " left "+at.root.left + " right " + at.root.right);
        at.insert(5) ;
        System.out.println("root " + at.root + " left "+at.root.left + " right " + at.root.right);
        System.out.println(at.travIn(at.root));
        System.out.println(at.root.left);
    }

}
