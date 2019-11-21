import java.util.LinkedList;

class Graph {

    int vertices;
    LinkedList<Integer>[] adjacencyList;

    Graph(int vertices){
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices+1];

        for(int i=0; i <= vertices; i++){
            adjacencyList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest){
        if(src < 0 || dest < 0 || src > vertices || dest > vertices){
            return;
        }
        adjacencyList[src].add(dest);
    }

    LinkedList<Integer>[] getAdjacencyList(){
        return adjacencyList;
    }
}

public class Judge {

    public int findJudge(int N, int[][] trust){
        Graph graph = new Graph(N);

        for(int i=0; i < trust.length; i++){
            graph.addEdge(trust[i][0], trust[i][1]);
        }

        LinkedList<Integer>[] adjacencyList = graph.getAdjacencyList();

        for(int i=1; i < adjacencyList.length; i++) {
            if (adjacencyList[i].size() == 0) {
                int judge = i;
                boolean isJudge = true;
                for (int j = 1; j < adjacencyList.length; j++) {
                    if(i == j){
                        continue;
                    }
                    LinkedList<Integer> edges = adjacencyList[j];
                    if (!edges.contains(judge)) {
                        isJudge = false;
                        break;
                    }
                }
                if(isJudge){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){

        Judge judge = new Judge();
        int N1 = 4;
        int N2 = 2;
        int N3  = 3;
        int[][] trust1 = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        int[][] trust2 = {{1, 2}};
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};
        System.out.println("TEST 1:" + judge.findJudge(N1, trust1));
        System.out.println("TEST 2:" + judge.findJudge(N2, trust2));
        System.out.println("TEST 3 :" + judge.findJudge(N3, trust3));
    }

}