import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class randomModeLabelBox {
    private JLabel randomVertex = new JLabel(" ");
    // counter is the point in the array that holds the vertices in a random order.
    private int counter = 0;
    private int numOfVertices;

    // constructor
    randomModeLabelBox(int numOfVertices){
        this.numOfVertices = numOfVertices;
        makeLabel();
    }

    public void makeLabel() {
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
                    counter++;
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

    //randomly making an array of vertices
    public static int[] ChoosingRandomVertex(int vert) {
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
