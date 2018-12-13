import javax.swing.*;
import java.awt.*;

public class warning {
    public void warning_standard() {
        if (warningNeeded(Integer.parseInt(GraphDraw_standard.getCurrentName()), GraphDraw_standard.getGraph(), GraphDraw_standard.getColorRecorder(), GraphDraw_standard.getNumberOfVertices())) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "The vertex is connected to a vertex with the same color!");
        }
    }
    public void warning_restricted() {
        if (warningNeeded(Integer.parseInt(GraphDraw_restricted.getCurrentName()), GraphDraw_restricted.getGraph(), GraphDraw_restricted.getColorRecorder(), GraphDraw_restricted.getNumberOfVertices())) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "The vertex is connected to a vertex with the same color!");
        }
    }

    /**
     * Determines whether a warning is needed, i.e. the colored vertex is connected to a vertex with the same color.
     * Call on this method every time a color has been chosen for a vertex.
     *
     * @param vert  the vertex that has been given a color
     * @param graph the matrix with the connections
     * @return true if a warning is needed, false if not
     */
    public static boolean warningNeeded(int vert, int[][] graph, Color[] colors, int numberOfVertices) {
        int[] connections = new int[numberOfVertices];
        boolean result = false;
        // loop goes through array that stores all the nodes this vertex is connected to
        int p = 0;
        // read through the matrix
        for (int i = 0; i < graph.length; i++) {
            // if we find this vertex in the graph matrix and it's in the first column we save the value that's in the second column
            if (graph[i][0] == vert) {
                connections[p] = graph[i][1];
                p++;
            } // if we find this vertex in the graph matrix and it's in the second column we save the value that's in the first column
            if (graph[i][1] == vert) {
                connections[p] = graph[i][0];
                p++;
            }
        }
        // Go through the array with the connected vertices after it is filled
        for (int q = 0; q < connections.length; q++) {
            // If the color of the vertex is the same as the color of the connected vertex, give a warning.
            if (colors[connections[q]] == colors[vert]) {
                result = true;
            }
        }
        return result;
    }

}
