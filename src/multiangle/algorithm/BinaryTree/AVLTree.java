package multiangle.algorithm.BinaryTree;

import multiangle.algorithm.Stack;

import java.util.LinkedList;

/**
 * Created by multiangle on 2016/5/22.
 */

public class AVLTree extends BinarySearchTree {
    private AVLNode root ;
    private AVLNode _hot ;
    private AVLNode start_node=null ;

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

    @Override
    public boolean remove(int value){
        start_node = null ;
        if (!removeNode(value)) return false ;
        if (start_node==null) return true ;
        AVLNode g = start_node ;
        while(g!=null){
            updateHeight(g);
            if (g.balFac>1||g.balFac<-1){ //如果g失衡
                AVLNode p,v ;
                p = g.balFac>0 ? g.left : g.right ;
                v = p.balFac>0 ? p.left : p.right ;
                rotateAt(g, p, v);
            }
            g = g.parent ;
        }
        return true ;
    }

    private boolean removeNode(int value){
        if (root==null) return false ; // 空树
        AVLNode res = search(value) ;
        if (res == null) return false ; // 不在树中
        return removeNode(res) ;
    }

    private boolean removeNode(AVLNode res){
        if (res.left==null && res.right==null){ // 左右结点为空，为叶节点
            if (res.parent==null) { // 只有一个节点的情况
                root = null ;
                return true ;
            }else{ //普通情况
                start_node = res.parent ;
                dropChild(res.parent,res) ;
            }
        }else if (res.left==null||res.right==null){ //左右结点一个空，一个不空
            if (res.left==null){ // 左树为空
                if (res.parent==null){ //且为根节点
                    root = res.right ;
                    root.parent = null ;
                    res = null ;
                    start_node = root ;
                }else{ // 不为根节点
                    start_node = res.parent ;
                    res.right.parent = res.parent ;
                    replaceChild(res.parent,res,res.right) ;
                    res = null ;
                }
            }
            else{ //右树为空
                if (res.parent==null){
                    root = res.left ;
                    root.parent = null ;
                    res = null ;
                    start_node = root ;
                }else{
                    start_node = res.parent ;
                    res.left.parent = res.parent ;
                    replaceChild(res.parent,res,res.left) ;
                    res = null ;
                }
            }
        }else{ // 左右结点都不空的情况下
            AVLNode next = nextNode(res.value) ; // 找到直接后继
            int temp = res.value ; // 交换节点顺序
            res.value = next.value ;
            next.value = temp ;
            removeNode(next) ;
        }
        return true ;
    }

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

    @Override
    public AVLNode nextNode(int value){
        AVLNode ret = null ;
        boolean finded = false ;
        boolean goon = true ;

        Stack<AVLNode> stack = new Stack() ;
        AVLNode node = root ;
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

    public boolean replaceChild(AVLNode parent, AVLNode old_child, AVLNode new_child){
        if (parent.isLeft(old_child)) {
            parent.left = new_child ;
            return true ;
        }
        else if (parent.isRight(old_child)){
            parent.right = new_child ;
            return true ;
        }
        return false ;
    }

    public boolean dropChild(AVLNode parent, AVLNode child){
        if (parent.isLeft(child)){
            parent.left = null ;
            child = null ;
            return true ;
        }else if (parent.isRight(child)){
            parent.right = null ;
            child = null ;
            return true ;
        }
        return false ;
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
        System.out.println(at);
        at.insert(3) ;
        System.out.println(at);
        at.insert(4) ;
        System.out.println(at);
        at.insert(5) ;
        System.out.println(at);
        at.remove(2) ;
        System.out.println(at);
    }

    public String toString(){
        String ret = "" ;
        LinkedList<AVLNode> list = new LinkedList<AVLNode>() ;
        AVLNode current ;
        list.add(root) ;
        while(!list.isEmpty()){
            current = list.remove(0) ;
            if (current==null) continue ;
            String temp = current.toString() + "->" ;
            if (current.parent==null) temp += "null" ;
            else temp += current.parent ;
            temp += "\t" ;
            ret = ret + temp ;
            list.add(current.left) ;
            list.add(current.right) ;
        }
        return ret ;
    }

}
