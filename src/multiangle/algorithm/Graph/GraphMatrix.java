package multiangle.algorithm.Graph;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by multiangle on 2016/5/13.
 */
public class GraphMatrix<TV,TE> {
    private ArrayList<Vertex<TV>> V ;
    private ArrayList<ArrayList<Edge<TE>>> E ;
    private int vertex_num = 0 ;
    private int edge_num = 0 ;
    private boolean directed = true ;
    private int clock = 0;

    GraphMatrix(){
        V = new ArrayList<Vertex<TV>>() ;
        E = new ArrayList<ArrayList<Edge<TE>>>() ;
        directed = true ;
    }
    GraphMatrix(boolean directed){
        V = new ArrayList<Vertex<TV>>() ;
        E = new ArrayList<ArrayList<Edge<TE>>>() ;
        this.directed = directed ;
    }
    public int addVertex(){
        return addVertex(null);
    }
    public int addVertex(TV data){
        Vertex v = new Vertex() ;
        v.id = vertex_num++ ;
        v.data = data ;
        v.inDegree = v.outDegree = 0;
        v.status = VStatus.UNDISCOVERED ;
        v.dTime = v.fTime = 0 ;
        v.parent = -1 ;
        v.priority = 0 ;

        V.add(v);
        for (int i=0; i<vertex_num-1; i++){
            ArrayList<Edge<TE>> temp = E.get(i) ;
            temp.add(null) ;
        }
        ArrayList<Edge<TE>> edge_list = new ArrayList<Edge<TE>>() ;
        for (int i=0; i<vertex_num; i++){
            edge_list.add(null) ;
        }
        E.add(edge_list) ;
        return vertex_num-1 ;
    }
    public void addEdge(int from,int to){
        Edge<TE> e = new Edge<TE>() ;
        e.data = null ;
        e.weight = 1 ;
        e.status = Estatus.UNDETERMINED ;
        if (E.get(from).get(to)==null){
            if (directed){
                E.get(from).set(to,e) ;
                edge_num++ ;
            }
            else{
                E.get(from).set(to,e) ;
                E.get(to).set(from,e) ;
                edge_num++ ;
            }
        }
    }

    public void BFS(int start_vertex_id){
        ArrayList<Vertex<TV>> queue = new ArrayList<Vertex<TV>>() ;
        queue.add(V.get(start_vertex_id)) ;
        while (!queue.isEmpty()){
            Vertex v = queue.remove(0) ;
            System.out.println(v.data);
            ArrayList<Edge<TE>> e_l = E.get(v.id) ;
            for (int j=0; j<e_l.size(); j++){
                if ((e_l.get(j)!=null)&&(V.get(j).status==VStatus.UNDISCOVERED)){
                    V.get(j).status=VStatus.DISCOVERED ;
                    queue.add(V.get(j));
                }
            }
        }
    }

    public void DFS(int start_vertex_id){
        Vertex<TV> node = V.get(start_vertex_id) ;
        node.status = VStatus.DISCOVERED ;
        System.out.println(node.data);
        node.dTime = clock++ ;
        for (int i=0;i<vertex_num;i++){
            if (E.get(start_vertex_id).get(i)!=null){
                switch (V.get(i).status){
                    case UNDISCOVERED: {
                        E.get(start_vertex_id).get(i).status = Estatus.TREE ; //这条边属于支撑树
                        V.get(i).parent = start_vertex_id ;
                        DFS(i);
                        break ;
                    }
                    case DISCOVERED:{
                        E.get(start_vertex_id).get(i).status = Estatus.BACKWARD ; //后向边，连向直系祖先
                        break ;
                    }
                    default:{
                        // 处理连向节点是visited的情况
                        if (node.dTime<V.get(i).fTime){ // dTime(v) < fTime(u)
                            E.get(start_vertex_id).get(i).status = Estatus.FORWARD ;
                        }else{
                            E.get(start_vertex_id).get(i).status = Estatus.CROSS ;
                        }
                    }
                }
            }
        }
        node.fTime = clock++ ;
        node.status = VStatus.VISITED ;
    }

    public int getVertexSize(){return vertex_num ;}
    public TV getData(int i){return V.get(i).data ;}
    public int getInDegree(int i){return V.get(i).inDegree;}
    public int getOutDegree(int i){return V.get(i).outDegree;}
    public VStatus getVertexStatus(int i){return V.get(i).status ;}
    public int getDTime(int i){return V.get(i).dTime ;}
    public int getFTime(int i){return V.get(i).fTime ;}
    public int getParent(int i){return V.get(i).parent ;}
    public int getPriority(int i){return V.get(i).priority ;}

    public static void main(String[] args){
        GraphMatrix<String,Integer> g = new GraphMatrix<String, Integer>(true) ;
        g.addVertex("a") ; //0
        g.addVertex("b") ; //1
        g.addVertex("c") ; //2
        g.addVertex("d") ; //3
        g.addVertex("e") ; //4
        g.addVertex("f") ; //5
        g.addVertex("g") ; //6
        g.addEdge(0, 1);
        g.addEdge(0,2);
        g.addEdge(0,5);
        g.addEdge(1,2);
        g.addEdge(5,4);
        g.addEdge(4,0);
        g.addEdge(4,2);
        g.addEdge(3,0);
        g.addEdge(3,6);
        g.addEdge(6,5);
        g.DFS(3);
        System.out.println();
    }
}
