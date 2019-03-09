package shujujiegou.assignment;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * 2018.12.11
 * @author LJJ
 *最小生成树的算法实现
 */
public class Assignment6 {
    private static List<Vertex> visitedVertexs,leftedVertexs; //分别为添加到集合U中的节点集和剩余的集合V中的节点集
    private static List<Edge> searchEdges;
    
    public static void main(String[] args) {
        Graph g = new Graph();
        //初始化图的信息
        initGraph(g);
        
        System.out.println("=================================");
        System.out.print("选择算法，Prim算法输入1， Kruscal算法输入2: ");
        transferStation(g);
        
       
    }
    
    public static void output(Graph g) {
        System.out.print("\n最短路径包含的边: ");
        for(int i=0;i<searchEdges.size();i++){
            System.out.print("("+searchEdges.get(i).startVertex.vName+","+searchEdges.get(i).endVertex.vName+")"+" ");
        }
        System.out.println("\n最短路径长度: "+g.minWeight);
        System.out.println("=================================");
        System.out.print("是否测试另一种算法，Prim算法输入1， Kruscal算法输入2,退出输入其他任意值: ");
        transferStation(g);
    }
    
    public static void transferStation(Graph g){
    	Scanner scan = new Scanner(System.in);
    	boolean stop = false;
        switch (scan.nextInt()) {
 		case 1:
 			 //往最小生成树中加入第一个点
 	        onChangeVertex(g.vertex[0]);
 	        //往最小生成树中不断加入点，并在最小生成树成型后输出
 	        prim(g);
 			break;
 		case 2:
 			searchEdges = getMinSpanTree(g);
 			break;
 		default:
 			stop = true;
 			break;
 		}
        if(stop == false)output(g);
    }
    
    public static void onChangeVertex(Vertex vertex){
        visitedVertexs.add(vertex); //添加初始节点,作为默认的开始节点
        leftedVertexs.remove(vertex);//从剩余节点中移除已添加节点
    }
    
    public static void prim(Graph g){
        while(leftedVertexs.size()>0){ //直到剩余节点集为空时结束循环
            Vertex findVertex = findOneVertex(g);
            onChangeVertex(findVertex);
        }
    }  
    
    //初始化图的信息
    public static void initGraph(Graph g){
        visitedVertexs = new ArrayList<Vertex>();
        leftedVertexs = new ArrayList<Vertex>();
        searchEdges = new ArrayList<Edge>();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("输入顶点数: ");
        int vertexNumber = sc.nextInt();
        System.out.print("请输入边数: ");
        int edgeNumber = sc.nextInt();
        String[] allVertex = new String[vertexNumber];
        String[] allEdge = new String[edgeNumber];
        
        System.out.println("=================================");
        System.out.println("请输入各个顶点:");
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<vertexNumber;i++){
            System.out.print("顶点"+(i+1)+":");
            allVertex[i] = scanner.nextLine();
        }
        System.out.println("=================================");
        for(int i=0;i<edgeNumber;i++){
            System.out.print("输入边(Vi,Vj)中的顶点名称和权值W，以空格隔开(如:A B 7): ");
            allEdge[i] = scanner.nextLine();
        }
        
        g.vertex = new Vertex[allVertex.length];
        g.edge = new Edge[allEdge.length];
        g.minWeight = 0;
        
        for(int i=0;i<allVertex.length;i++){
            g.vertex[i] = new Vertex();
            g.vertex[i].vName = allVertex[i];
            leftedVertexs.add(g.vertex[i]); //初始化剩余点集合
        }
        
        for(int i=0;i<allEdge.length;i++){
            g.edge[i] = new Edge();
            g.edge[i].startVertex = new Vertex();
            g.edge[i].endVertex = new Vertex();
            
            String edgeInfo[] = allEdge[i].split(" ");
            g.edge[i].startVertex.vName = edgeInfo[0];
            g.edge[i].endVertex.vName = edgeInfo[1];
            g.edge[i].weight = Integer.parseInt(edgeInfo[2]);
        }
    }
    //找到下一个加入最小生成树的点  
    public static Vertex findOneVertex(Graph g){
        int minValue = Integer.MAX_VALUE;
        
        Vertex findVertex = new Vertex();
        Edge findEdge = new Edge();
        
        for(int i=0;i<visitedVertexs.size();i++){
            for(int j=0;j<leftedVertexs.size();j++){
                Vertex v1 = visitedVertexs.get(i);
                Vertex v2 = leftedVertexs.get(j); //获取两个顶点的名称
                
                for(int k=0;k<g.edge.length;k++){
                    String startName = g.edge[k].startVertex.vName;
                    String endName = g.edge[k].endVertex.vName;
                    //判断边k是否能将已连接的树与未连接的点相连通            //此处会调用对象的equals方法比较对象,故在后面重写equals方法
                    if((v1.vName.equals(startName) && v2.vName.equals(endName))||(v1.vName.equals(endName) && v2.vName.equals(startName))){
                        //寻找权重最小的边
                    	if(g.edge[k].weight < minValue){
                            findEdge = g.edge[k];
                            minValue = g.edge[k].weight;
                            if(leftedVertexs.contains(v1)){ 
                                findVertex = v1;
                            }else if(leftedVertexs.contains(v2)){
                                findVertex = v2;
                            }
                        }
                    }
                }
            }
        }
        g.minWeight+= minValue;
        searchEdges.add(findEdge);
        
        return findVertex;
    }
    
    //使用合并排序,把数组A按照其value值进行从小到大排序
    public static void edgeSort(Edge[] A){
        if(A.length > 1) {
            Edge[] leftA = getHalfEdge(A, 0);
            Edge[] rightA = getHalfEdge(A, 1);
            edgeSort(leftA);
            edgeSort(rightA);
            mergeEdgeArray(A, leftA, rightA);
        }
    }
    //judge = 0返回数组A的左半边元素,否则返回右半边元素
    public static Edge[] getHalfEdge(Edge[] A, int judge) {
        Edge[] half;
        if(judge == 0) {
            half = new Edge[A.length / 2];
            for(int i = 0;i < A.length / 2;i++)
                half[i] = A[i];
        } else {
            half = new Edge[A.length - A.length / 2];
            for(int i = 0;i < A.length - A.length / 2;i++)
                half[i] = A[A.length / 2 + i];
        }
        return half;
    }
    //合并leftA和rightA,并按照从小到大顺序排列
    public static void mergeEdgeArray(Edge[] A, Edge[] leftA, Edge[] rightA) {
        int i = 0;
        int j = 0;
        int len = 0;
        while(i < leftA.length && j < rightA.length) {
            if(leftA[i].weight < rightA[j].weight) {
                A[len++] = leftA[i++];
            } else {
                A[len++] = rightA[j++];
            }
        }
        while(i < leftA.length) A[len++] = leftA[i++];
        while(j < rightA.length) A[len++] = rightA[j++];
    }
    
    //获取节点a的根节点编号
    public static int find(int[] id, int a) {
        int i, root, k;
        root = a;
        while(id[root] >= 0) root = id[root];  //此处,若id[root] >= 0,说明此时的a不是根节点,因为唯有根节点的值小于0
        k = a;
        while(k != root) {  //将a节点所在树的所有节点,都变成root的直接子节点
            i = id[k];
            id[k] = root;
            k = i;
        }
        return root;
    }
    //判断顶点a和顶点b的根节点大小,根节点值越小,代表其对应树的节点越多,将节点少的树的节点添加到节点多的树上
    public static void union(int[] id, int a, int b) {
        int ida = find(id, a);   //得到顶点a的根节点
        int idb = find(id, b);   //得到顶点b的根节点
        int num = id[ida] + id[idb];  //由于根节点值必定小于0,此处num值必定小于零
        if(id[ida] < id[idb]) {
            id[idb] = ida;    //将顶点b作为顶点a根节点的直接子节点
            id[ida] = num;   //更新根节点的id值
        } else {
            id[ida] = idb;    //将顶点a作为顶点b根节点的直接子节点
            id[idb] = num;    //更新根节点的id值
        }
    }
    //获取图A的最小生成树
    public static ArrayList<Edge> getMinSpanTree(Graph g) {
        ArrayList<Edge> list = new ArrayList<Edge>();
        int n = g.vertex.length;
        int[] id = new int[n];
        for(int i = 0;i < n;i++) 
            id[i] = -1;        //初始化id(x),令所有顶点的id值为-1,即表示为根节点
        edgeSort(g.edge);   //使用合并排序,对于图中所有边权值进行从小到大排序
        int count = 0;
        for(int i = 0;i < g.edge.length;i++) { 
            int a = Arrays.asList(g.vertex).indexOf(g.edge[i].startVertex); 
            int b = Arrays.asList(g.vertex).indexOf(g.edge[i].endVertex);
            int ida = find(id, a);
            int idb = find(id, b);
            if(ida != idb) {
                list.add(g.edge[i]);
                count++;
                union(id, a, b);
            }
            
            if(count >= n - 1)
                break;
        }
        g.minWeight = list.stream().mapToInt(Edge :: getWight).sum();
        return list;
    }

}



/**
 * 顶点类Vertex
 */
class Vertex{
    String vName; //顶点的名称
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertex){
            Vertex vertex = (Vertex)obj;
            return this.vName.equals(vertex.vName);
        }
        return super.equals(obj);
    }
}

/**
 * 边类Edge
 */
class Edge{
    Vertex startVertex;
    Vertex endVertex;
    int weight;
    public int getWight() {
		return this.weight;
	}
}

/**
 * 图的存储结构
 */
class Graph{
    Vertex[] vertex; //顶点集
    Edge[] edge; //边集
    int minWeight; //最短路径
}