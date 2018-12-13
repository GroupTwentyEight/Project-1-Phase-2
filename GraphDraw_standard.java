import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;


public class GraphDraw_standard extends JComponent {
    //Width of the vertex.
    private int width = 30;
    private ArrayList<Vertex> vertices;
    private ArrayList<edge> edges;
    //Stores the graph.
    private static int[][] graph;
    private static int numberOfVertices;
    //Used to initaliaze the constructor.
    static int count;
    private static int tracker;
    //Placeholder for the x-coordinate of the node.
    private int xSet=1800;
    //Placeholder for the y-coordinate of the node.
    private int ySet=1800;
    //Placeholder to use when evaluating if a node should be colored in the addVertex method.
    private String newName="0";
    //Placeholder for the name of the node that was just colored.
    private static String currentName="0";
    //Placeholder for the new color of the nodes.
    private Color pickedColor=new Color(255,0,0);
    private Color bordercolor = new Color(255,255,255);
    //Placeholder for current color of all nodes.
    private Color selectColor= new Color(255,0,0);
    private Color otherColor= new Color(0,0,0);
    private Color currentSelection[];
    //Contains the vertices and the color assignment.
    private static Color[] colorRecorder;
    private static int[][] sub;
    Graphics g;
    GraphDraw_standard(int numberOfVertices, int numberOfEdges) {
        if (count!=1){//Constructor
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
            graphGenerator currentGraph = new graphGenerator(numberOfVertices, numberOfEdges);
            graph = graphGenerator.getGraph();
            GraphDraw_standard.numberOfVertices = numberOfVertices;
            colorRecorder=new Color[numberOfVertices+1];
            currentSelection=new Color[numberOfVertices];
            sub=new int[graph.length][2];
            count++;
        }
    }
    GraphDraw_standard(int[][] graph){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        GraphDraw_standard.graph = graph;
    }
    static int getNumberOfVertices(){
        return numberOfVertices;
    }
    //Draws the graph when called and returns the name of the vertex just colored when repainted.
    void callEverything(Graphics2D g2) {
        addingEverything();
        paint(g2);
        getName();
    }
    static Color[] getColorRecorder() {
        return colorRecorder;
    }

    public static  int[][] getGraph() {
        return graph;
    }

    //Gets name of the node that was just colored.
    static String getCurrentName() {
        return currentName;
    }

    int[][] returnCurrentGraph(){
        return graph;
    }
    //Adds vertices to the graph and determines whether the color should be changed or not.
    private void addVertex(String name, int x, int y) {
        Vertex avertex=new Vertex(name,x,y,selectColor,bordercolor);

        //Creates a new vertex.
        for (int i=1; i<colorRecorder.length;i++){
            //Ensures that the nodes that have already been colored will keep the color assigned to them.
            if(colorRecorder[i]!=null){
                if(String.valueOf(i).equals(name)){
                    avertex.setColor(colorRecorder[i]);
                }
            }
        }

        //For the node to be colored, will set the color of the node to the one determined by the user. And will record the name of the node.
        if((xSet+30>x && xSet-30<x) &&(ySet+30>y && ySet-30<y)){
            avertex.setColor(pickedColor);
            avertex.setBorder(otherColor);
            colorRecorder[Integer.parseInt(name)]=pickedColor;
            currentName=name;
            int vertex= Integer.parseInt(name);
            int amount=0;
            for (int j= 0; j < graph.length; j++) {
                if (vertex==graph[j][0]  || vertex==graph[j][1]) {
                    sub[amount][0] = graph[j][0];
                    sub[amount][1] = graph[j][1];
                    amount++;
                }else{
                    sub[j][0]=0;
                    sub[j][1]=0;
                }
            }
        }
        int k=0;
        while(k<sub.length){
            if(String.valueOf(sub[k][0]).equals(name)|| String.valueOf(sub[k][1]).equals(name)){
                avertex.setBorder(otherColor);
            }
            k++;
        }
    //we add vertex to the ArrayList with the vertices
        vertices.add(avertex);
    }

    //Brings in the array that records the colors that have been assigned to the nodes.

    void setColor(Color c, int horz, int vert, String nick){
        pickedColor=c;
        xSet=horz;
        ySet=vert;
        newName=nick;
    }
    void getPaint(Color c){
        currentSelection[tracker]=c;
        System.out.println(currentSelection[tracker]);
        tracker++;
    }

    /*public void setBorder(int x, int y, Color color) {
        bordercolor = color;
        xSet = x;
        ySet =y;
    }*/
    private void addEdge(int i, int j) {
        //add an edge between vertices i and j
        edges.add(new edge(i - 1, j - 1));

    }
    class edge {
        int i, j;

        public edge(int i, int j) { //constructor
            this.i = i;
            this.j = j;
        }

    }
    public Color[] returnColorRecord(){
        return colorRecorder;
    }

    String groteHints(){
        HintSystem hinty= new HintSystem();
        return HintSystem.giveHint(1, currentSelection, colorRecorder, graph);
    }
    String Hints(){
        HintSystem hintSmall= new HintSystem();
        return HintSystem.giveHint(0, currentSelection, colorRecorder, graph);
    }

    public void paint(Graphics g) { // draw the vertices and edges
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setBackground(Color.blue);
        FontMetrics f = g2.getFontMetrics(); //Gets the font metrics of the current font
        //Height of vertex.
        int height = 30;
        int vertexHeight = Math.max(height, f.getHeight());
        g2.setColor(Color.blue); //color of the edges
        for (edge e : edges) {
            g2.drawLine(vertices.get(e.i).x, vertices.get(e.i).y, vertices.get(e.j).x, vertices.get(e.j).y);
        }
        for (Vertex n : vertices) {
            n.draw(g2);
        }
    }

    private void addingEverything() {
        for (int i = 0; i < graph.length; i++) {
            addEdge(graph[i][0], graph[i][1]);
        }
        // the angle at which we start with (so we start now at the top)
        double angle = Math.PI;
        // If the number of vertices is smaller than 15 we just put them in one big circle.
        if (numberOfVertices <= 15) {
            for (int i = 1; i <= numberOfVertices; i++) {
                // The radius of the circle
                int r = 310;
                String m = "" + i;
                int x = (int) (r * Math.sin(angle)) + 600;
                int y = (int) (r * Math.cos(angle)) + 400;
                addVertex(m, x, y);
                // angle is updated every time so that the angle changes for the placement of the next vertex.
                angle = (angle + ((2*Math.PI) / numberOfVertices)) % (2*Math.PI);
            }
            // If the number of Vertices is bigger than 15 but smaller than 35 we make two circles.
        } else if (numberOfVertices <= 35) {
            for (int i = 1; i <= numberOfVertices; i++) {
                // The first twenty vertices will be placed in the outer circle.
                if (i <= 20) {
                    int r = 310;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + ((2*Math.PI) / 20)) % (2*Math.PI);
                    // The rest will be placed in the circle with smaller radius.
                } else {
                    int r = 200;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + ((2*Math.PI) / (numberOfVertices - 20))) % (2*Math.PI);
                }
            }
        } else if (numberOfVertices > 35) {
            for (int i = 1; i <= numberOfVertices; i++) {
                if (i <= 20) {
                    int r = 330;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + (Math.PI/10)) % (2*Math.PI);
                } else if (i <= 35) {
                    int r = 250;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + (2*Math.PI/15)) % (2*Math.PI);
                } else if (i <= 45) {
                    int r = 130;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + (2*Math.PI/10)) % (2*Math.PI);
                } else {
                    int r = 40;
                    String m = "" + i;
                    int x = (int) (r * Math.sin(angle)) + 600;
                    int y = (int) (r * Math.cos(angle)) + 400;
                    addVertex(m, x, y);
                    angle = (angle + (2*Math.PI/5)) % (2*Math.PI);
                }
            }
        }

    }
}
