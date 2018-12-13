import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class chooseGraph extends JComponent {
    private JButton generate;
    private JButton uploadFile;
    private JFrame frame;
    private static int numberOfVertices;
    private int numberOfEdges;
    private JDialog dialog;
    private JOptionPane optionPane;
    private static int[][] graph;
    static Color[] paints;

    chooseGraph() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionPane = new JOptionPane();
        optionPane.setPreferredSize(new Dimension(450, 170));
        dialog = optionPane.createDialog(frame, "Generate a graph");
        createButtons();
        optionPane.setMessage(new Object[] { "Would you like to generate your own graph or upload an existing one?", generate, uploadFile });
        dialog.setVisible(true);

    }

    private void createButtons(){
        generate = new JButton("Randomly generate a graph");
        uploadFile = new JButton("Upload a graph file");
        class generateListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                askVertice test = new askVertice();
                askEdge testEdge = new askEdge(test.getInputVertices());
                dialog.setVisible(false);
                optionPane.setVisible(false);
                numberOfVertices =  test.getInputVertices();
                numberOfEdges =  testEdge.GetnumberOfEdges();


            }
        }
        // We create a new listener and add it to the button.
        ActionListener generateListener = new generateListener();
        generate.addActionListener(generateListener);
        frame.add(generate);
        class uploadFileListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                final JFileChooser fc = new JFileChooser();
                fc.setFileFilter(filter);
                //Handle open button action.
                if (event.getSource() == uploadFile) {
                    int returnVal = fc.showOpenDialog(frame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fc.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                        ReadFile read = new ReadFile(selectedFile.getAbsolutePath());
                        graph = read.getGraph();
                        UploadGraph upload = new UploadGraph();
                        frame.add(upload);
                    }
                }
            }
        }
        // We create a new listener and add it to the button.
        ActionListener uploadFileListener = new uploadFileListener();
        uploadFile.addActionListener(uploadFileListener);
        frame.add(uploadFile);
    }
    class UploadGraph extends JComponent{
        /*int xPos= 100;
        String currentNode;
        Color choosenColor=new Color(255,255,255);
        String hi="3000";*/

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            //gd.setColorRecord(colorRecord);
            // upload.setColor(choosenColor, xPos, yPos,hi);
            GraphDraw_standard upload = new GraphDraw_standard(graph);
            upload.callEverything(g2);
            //currentNode=upload.getName();
        }
    }

    class GraphDrawComponent_standard extends JComponent {
        //Placeholder for the color chosen by the user.
        Color choosenColor=new Color(255,255,255);
        //Calls the command to draw the graph.
        GraphDraw_standard gd = new GraphDraw_standard(numberOfVertices, numberOfEdges);
        //Contains the array of vertices and the color assigned to them.
        Color[] colorRecord;
        Color currentColor;
        //Name of the vertex that has just been colored.
        String currentNode;
        int numberOfVerts;
        //X position of where the user has clicked.
        int xPos;
        //Y position of where the user has clicked.
        int yPos;
        String hi="3000";
        //Draws the graph, and returns the name of the node.
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            gd.setColor(choosenColor, xPos, yPos,hi);
            gd.callEverything(g2);
            currentNode=gd.getName();
        }
        void setColor(Color c, int horz, int vert, String bud){
            choosenColor=c;
            xPos=horz;
            yPos=vert;
            hi=bud;
        }
        public String getName(){
            return currentNode;
        }
        int getNumberOfVerts(){
            numberOfVerts= GraphDraw_standard.getNumberOfVertices();
            return numberOfVerts;
        }
        int[][] receiveGraph(){
            return gd.returnCurrentGraph();
        }
        Color[] receiveColorRecord(){
            return colorRecord;
        }
        void receivePaint(Color c){
            currentColor=c;
            gd.getPaint(currentColor);
        }
        String giveGroteHints(){
            return gd.groteHints();
        }
        String giveKleinHints(){
            return gd.Hints();
        }

    }
    class GraphDrawComponent_restricted extends JComponent {
        Color choosenColor=new Color(255,255,255);
        GraphDraw_restricted gd = new GraphDraw_restricted(numberOfVertices, numberOfEdges);
        Color[] colorRecord;
        Color currentColor;
        String currentNode;
        int numberOfVerts;
        int xPos;
        int yPos;
        String hi="3000";
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            gd.setColor(choosenColor, xPos, yPos,hi);
            gd.callEverything(g2);
            currentNode= GraphDraw_restricted.getCurrentName();
        }
        void setColor(Color c, int horz, int vert, String bud){
            choosenColor=c;
            xPos=horz;
            yPos=vert;
            hi=bud;
        }
        public String getName(){
            return currentNode;
        }
        int getNumberOfVerts(){
            numberOfVerts= GraphDraw_restricted.getNumberOfVertices();
            return numberOfVerts;
        }
        int[][] receiveGraph(){
            return gd.returnCurrentGraph();
        }

        //Gets the record of color assignments.
        Color[] receiveColorRecord(){
            return colorRecord;
        }
        void receivePaint(Color c){
            currentColor=c;
            gd.getPaint(currentColor);
        }
        String giveKleinHints(){
            return gd.Hints();
        }
    }
    public int[][] getGraph(){
        return graph;
    }
}
