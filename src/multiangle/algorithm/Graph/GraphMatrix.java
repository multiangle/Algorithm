package multiangle.algorithm.Graph;

import java.util.ArrayList;

/**
 * Created by multiangle on 2016/5/13.
 */
public class GraphMatrix<TV,TE> {
    private ArrayList<Vertex<TV>> V ;
    private ArrayList<ArrayList<Edge<TE>>> E ;
    private int vertex_num = 0 ;
    private int edge_num = 0 ;

    GraphMatrix(){
        V = new ArrayList<Vertex<TV>>() ;
        E = new ArrayList<ArrayList<Edge<TE>>>() ;
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
            E.get(from).set(to,e) ;
        }
    }

    public void BFS(int start_vertex_id){
        
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

}
