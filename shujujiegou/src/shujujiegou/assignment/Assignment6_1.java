package shujujiegou.assignment;

import java.util.Scanner;

public class Assignment6_1 {

    private static int N = 1000;
    private static int[][] Graph = {
            { 0, 1, 5, N, N, N, N, N, N },
            { 1, 0, 3, 7, 5, N, N, N, N },
            { 5, 3, 0, N, 1, 7, N, N, N },
            { N, 7, N, 0, 2, N, 3, N, N },
            { N, 5, 1, 2, 0, 3, 6, 9, N },
            { N, N, 7, N, 3, 0, N, 5, N },
            { N, N, N, 3, 6, N, 0, 2, 7 },
            { N, N, N, N, 9, 5, 2, 0, 4 },
            { N, N, N, N, N, N, 7, 4, 0 } };
 
    public static void main(String[] args) {   	
    	 //该函数第一个参数是根节点，第二个参数为图，图可在最上方的邻接矩阵处修改修改
    	 transferStation(0, Graph);
    }
    
    public static void transferStation(int vs, int[][] g){
    	System.out.print("选择算法，Dijkstra算法输入1， floyd算法输入2,退出输入其他任意键: ");
    	Scanner scan = new Scanner(System.in);
    	boolean stop = false;
        switch (scan.nextInt()) {
 		case 1:
 			 //往最小生成树中加入第一个点
 			dijkstra(vs, Graph);
 			break;
 		case 2:
 			floyd(vs,Graph);
 			break;
 		default:
 			stop = true;
 			break;
 		}
        if(stop == false)transferStation(vs, g);
    }    
 
    /**
     * Dijkstra最短路径。
     * 即图中"节点vs"到其它各个节点的最短路径。
     * @param vs 起始节点
     * @param Graph 图
     */
    public static void dijkstra(int vs, int[][] Graph) {
        int NUM = Graph[0].length;
        // 前驱节点数组
        int[] prenode = new int[NUM];
        // 最短距离数组
        int[] mindist = new int[NUM];
        // 该节点是否已经找到最短路径
        boolean[] find = new boolean[NUM];
         
        int vnear = 0;
         
        for (int i = 0; i < mindist.length; i++) {
            prenode[i] = i;
            mindist[i] = Graph[vs][i];
            find[i] = false;
        }
 
        find[vs] = true;
 
        for (int v = 1; v < Graph.length; v++) {
 
            // 每次循环求得距离vs最近的节点vnear和最短距离min
            int min = N;
            for (int j = 0; j < Graph.length; j++) {
                if (!find[j] && mindist[j] < min) {
                    min = mindist[j];
                    vnear = j;
                }
            }
            find[vnear] = true;
 
            // 根据vnear修正vs到其他所有节点的前驱节点及距离
            for (int k = 0; k < Graph.length; k++) {
                if (!find[k] && (min + Graph[vnear][k]) < mindist[k]) {
                    prenode[k] = vnear;
                    mindist[k] = min + Graph[vnear][k];
                }
            }
        }
         
        for (int i = 0; i < NUM; i++) {
            System.out.println("v" + vs + "...v" + prenode[i] + "->v" + i + ", s=" + mindist[i]);
        }
    }
    /**
     * floyd最短路径。
     * 即图中"节点vs"到其它各个节点的最短路径。
     * @param vs 起始节点
     * @param Graph 图
     */    

    public static void floyd(int vs,int[][] matrix){
    	int MAX_WEIGHT = Integer.MAX_VALUE;
    	int[][] pathMatirx;
    	int[][] preTable;
        //路径矩阵（D），表示顶点到顶点的最短路径权值之和的矩阵，初始时，就是图的邻接矩阵。
        pathMatirx = new int[matrix.length][matrix.length];
        //前驱表（P），P[m][n] 的值为 m到n的最短路径的前驱顶点，如果是直连，值为n。也就是初始值
        preTable = new int[matrix.length][matrix.length];
        
        //初始化D,P
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                pathMatirx[i][j] = matrix[i][j];
                preTable[i][j] = j;
            }
        }
        
        //循环 中间经过顶点
        for (int k = 0; k < matrix.length; k++) {
            //循环所有路径
            for (int m = 0; m < matrix.length; m++) {
                
                for (int n = 0; n < matrix.length; n++) {
                    
                    int mn = pathMatirx[m][n];
                    int mk = pathMatirx[m][k];
                    int kn = pathMatirx[k][n];
                    int addedPath = (mk == MAX_WEIGHT || kn == MAX_WEIGHT)? MAX_WEIGHT : mk + kn;
                    
                    if (mn > addedPath) {
                        //如果经过k顶点路径比原两点路径更短，将两点间权值设为更小的一个
                        pathMatirx[m][n] = addedPath;
                        //前驱设置为经过下标为k的顶点
                        preTable[m][n] = preTable[m][k];
                    }
                    
                }
            }
        }
        
       
            for (int n = vs + 1; n < matrix.length; n++) {
                
                int k = preTable[vs][n];
                System.out.print("(" + vs + "," + n + ")" + pathMatirx[vs][n] + ":  ");
                System.out.print(vs);
                while (k != n) {
                    System.out.print("->" + k);
                    k = preTable[k][n];
                }
                
                System.out.println("->" + n);
            }
            System.out.println();
        
    }


}
