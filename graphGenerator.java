import java.util.Random;

public class graphGenerator {
    private static int[][] graph;
    private static int edges;
    private static int vertices;

    public graphGenerator(int vertices, int edges) {

        this.vertices = vertices;
        this.edges = edges;

        // The base cases: graph is not possible in these cases
        if (vertices <= 1 || edges < 1 || edges < vertices - 1 || !graphPossible()) {
            System.out.println("Graph cannot be generated");
        } else {
            graph = generate();
        }
    }

    public static int[][] getGraph() {
        return graph;
    }

    /**
     * This method generates a graph with the asked number of vertices and edges.
     *
     * @return the generated graph
     */
    public static int[][] generate() {
        // Boolean matrix which will hold 'true' at [a][b] and [b][a] if there is an edge between a and b.
        boolean[][] connections = new boolean[vertices + 1][vertices + 1];
        // the matrix that will hold the connected vertices in the format the we use everywhere
        int[][] graph = new int[edges][2];
        // Loop reads through the rightly formatted matrix where the edges are put and so at the same time keeps track of the number of edges we generated.
        int i = 0;

        // Two randomly generated numbers which will be the indexes in the 'connections' matrix where a connections is put to 'true'.
        // 0 is not included, because there are no vertices named 0 so we keep this row and column empty.
        Random rand = new Random();
        int a = rand.nextInt(vertices) + 1;
        int b = rand.nextInt(vertices) + 1;
        // For loop to go through the rows in the boolean matrix. We start with j=1 because our 0 row is empty and should stay empty and then of course we only stop when we have reached the number of vertices itself.
        for (int j = 1; j <= vertices; j++) {
            // We need to make sure we are not connecting the vertex to itself (because random number could be the same as the vertex)
            while (j == a) {
                a = rand.nextInt(vertices) + 1;
            }
            // if no connection exists in this row and j is not equal to the random number
            if (!connectionExist(connections, j)) {
                // Make a connection between the vertex and a random number and put it in both places in the matrix.
                connections[j][a] = true;
                connections[a][j] = true;
                // Put this connection also in the rightly formatted graph.
                graph[i][0] = j;
                graph[i][1] = a;
                i++;
                // make 'a' a (different) random number
                a = rand.nextInt(vertices) + 1;
            }
        }

        while (i < edges) {
            // Make (different) random numbers
            a = rand.nextInt(vertices) + 1;
            b = rand.nextInt(vertices) + 1;
            // if a is not the same as b (vertices can't be connected to themselves) and if there is not already a connection there (because we don't want to count this edge double)
            if (a != b && !connections[a][b]) {
                connections[a][b] = true;
                connections[b][a] = true;
                // edge is put in the rightly formatted matrix
                graph[i][0] = a;
                graph[i][1] = b;
                i++;
            }
        }
        return graph;
    }

    /**
     * This method tests to see if there is a connection to a certain vertex.
     *
     * @param connections boolean matrix which holds the connections between vertices
     * @return returns whether there was a connection (true) or not (false)
     */
    public static boolean connectionExist(boolean[][] connections, int vertex) {
        boolean conExist = false;
        // for loop that goes through all vertices: the columns that are in the row of the vertex in the adjacency matrix
        for (int i = 1; i <= vertices; i++) {
            // if there is a connection the boolean should be returned true
            if (connections[vertex][i]) {
                conExist = true;
            }
        }
        return conExist;
    }

    /**
     * This method checks if the graph can be generated because if the input number of edges is bigger than the maximum number of edges it returns false.
     *
     * @return true if the graph is possible, false if not possible.
     */
    public static boolean graphPossible() {
        int maxCon = 0;
        for (int n = 1; n <= vertices; n++) {
            maxCon += n;
        }
        if (edges > maxCon) {
            return false;
        } else {
            return true;
        }
    }
}
