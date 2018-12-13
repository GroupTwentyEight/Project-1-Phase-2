import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class GraphDraw_restricted{
    //User cannot change colors that have already been selected in this mode.
    //Width of the vertex.
    private int width = 30;
    private ArrayList<Vertex> vertices;
    private ArrayList<edge> edges;
    //Stores the graph.
    static int[][] graph;
    private static int numberOfVertices;
    private static int tracker;
    //Used to initialaze the constructor.
    static int count;
    //Placeholder for the x-coordinate of the node to be colored.
    private int xSet=1800;
    //Placeholder for the y-coordinate of the node to be colored.
    private int ySet=1800;
    //Placeholder for the name of the node that was just colored.
    private static String currentName="0";
    //Placeholder for current color of all nodes.
    private Color selectColor= new Color(255,0,0);
    //Placeholder for the new color of the nodes.
    private Color pickedColor=new Color(255,0,0);
    //Color of the edge of the nodes connected to the selected node.
    private Color otherColor=new Color(0,0,0);
    private static String colorThis="";
    //Contains the vertices and the color assignment.
    private static Color[] colorRecorder;
    private Color currentSelection[];
    Graphics g;
    private Color bordercolor = new Color(255,255,255);

    GraphDraw_restricted(int numberOfVertices, int numberOfEdges) {
        if (count!=1){//Constructor
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
            graphGenerator currentGraph = new graphGenerator(numberOfVertices, numberOfEdges);
            graph = graphGenerator.getGraph();
            GraphDraw_restricted.numberOfVertices = numberOfVertices;
            colorRecorder=new Color[numberOfVertices+1];
            currentSelection= new Color[numberOfVertices];
            count++;
        }
        randomModeLabelBox randOrdGam= new randomModeLabelBox(numberOfVertices);
    }
    static int getNumberOfVertices(){
        return numberOfVertices;
    }
    //Returns the current graph.
    int[][] returnCurrentGraph(){
        return graph;
    }

    //Draws the graph when called and returns the name of the vertex just colored when repainted.
    void callEverything(Graphics2D g2) {
        addingEverything();
        paint(g2);
    }
    static Color[] getColorRecorder() {
        return colorRecorder;
    }

    public static int[][] getGraph() {
        return graph;
    }

    //Gets name of the node that was just colored.
    static String getCurrentName(){
        return currentName;
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
        if (name.equals(colorThis)){
            avertex.setBorder(otherColor);
            //For the node to be colored, will set the color of the node to the one determined by the user. And will record the name of the node.
            if((xSet+30>x && xSet-30<x) &&(ySet+30>y && ySet-30<y)){
                if(colorRecorder[Integer.parseInt(name)]==null){
                    avertex.setColor(pickedColor);
                    colorRecorder[Integer.parseInt(name)]=pickedColor;
                    currentName=name;
                }

            }
        }
        //we add vertex to the ArrayList with the vertices
        vertices.add(avertex);


    }
   /* public void setColorRecord(Color[] cRecord){
    	colorRecorder=cRecord;
    }*/

    void setColor(Color c, int horz, int vert, String nick){
        pickedColor=c;
        xSet=horz;
        ySet=vert;
    }

    void getPaint(Color c){
        currentSelection[tracker]=c;
        System.out.println(currentSelection[tracker]);
        tracker++;
    }

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
    String Hints(){
        HintSystem hintSmall= new HintSystem();
        return hintSmall.giveHint(0, currentSelection, colorRecorder, graph);
    }

    private void paint(Graphics g) { // draw the vertices and edges
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
    public static class randomModeLabelBox {
        JLabel randomVertex = new JLabel(" ");
        // counter is the point in the array that holds the vertices in a random order
        // constructor
        private int counter = 0;
        private int numOfVertices;
        int vertexy;

        randomModeLabelBox(int numOfVertices){
            this.numOfVertices = numOfVertices;
            makeLabel();
        }

        void makeLabel() {
            JFrame frame = new JFrame("Random Vertex Color");
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            frame.setSize(600, 400);

            int[] randomChoice = ChoosingRandomVertex(numOfVertices);
            JButton okayButton = new JButton("OK");
            //button action listener
            class OkayButtonListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    if (counter < randomChoice.length) {
                        randomVertex.setText("The next vertex to be colored is: " + randomChoice[counter]);
                        colorThis=String.valueOf(randomChoice[counter]);
                        counter++;
                        // colorThis=String.valueOf(randomChoice[counter]);
                    }
                }
            }
            ActionListener listener = new OkayButtonListener();
            okayButton.addActionListener(listener);

            //panel creation
            JPanel RandomModePanel = new JPanel();
            //adding buttons to panel
            RandomModePanel.add(randomVertex);
            RandomModePanel.add(okayButton);
            frame.add(RandomModePanel);
            frame.setVisible(true);
        }
        public int getNextVertex(){
            return vertexy;
        }

        //randomly making an array of vertices
        int[] ChoosingRandomVertex(int vert) {
            int[] verticesRandom = new int[vert];
            for (int q = 0; q < verticesRandom.length; q++) {
                verticesRandom[q] = q + 1;
            }
            for (int i = verticesRandom.length - 1; i > 0; i--) {
                // Random number is between 0 and the last index.
                int random = (int) Math.floor(Math.random() * (i + 1));
                // We save this number A
                int temporaryVar = verticesRandom[i];
                // We put, at the index of number A a random number B
                verticesRandom[i] = verticesRandom[random];
                // Then we put our saved number A at the place where we got our number B
                verticesRandom[random] = temporaryVar;
            }
            return verticesRandom;
        }
    }
}
