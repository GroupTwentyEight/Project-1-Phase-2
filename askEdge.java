import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class askEdge {
    private JLabel displayValue;
    private int numberOfEdges;

    public askEdge(int inputVertices) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // displayValue will show what number the slider is currently on.
        displayValue = new JLabel();
        JOptionPane optionPane = new JOptionPane();
        JSlider slider2 = getSlider(optionPane, inputVertices);
        optionPane.setMessage(new Object[]{"Select the number of edges: ", slider2, displayValue});
        optionPane.setPreferredSize(new Dimension(900, 400));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(frame, "My Slider");
        dialog.setVisible(true);
       // System.out.println("Input: " + optionPane.getInputValue());
        // Retrieving the input.
        numberOfEdges = slider2.getValue();
        System.out.println(numberOfEdges);
    }

    JSlider getSlider(final JOptionPane optionPane, int inputVertices) {
        JSlider slider2 = new JSlider();
        if (inputVertices < 10)
            slider2.setMajorTickSpacing(1);
        else if (inputVertices < 20)
            slider2.setMajorTickSpacing(5);
        else if (inputVertices < 25)
            slider2.setMajorTickSpacing(15);
        else if (inputVertices < 30)
            slider2.setMajorTickSpacing(20);
        else if (inputVertices < 35)
            slider2.setMajorTickSpacing(30);
        else if (inputVertices < 40)
            slider2.setMajorTickSpacing(40);
        else if (inputVertices < 45)
            slider2.setMajorTickSpacing(50);
        else if (inputVertices < 50)
            slider2.setMajorTickSpacing(60);

        slider2.setMaximum(inputVertices * ((inputVertices - 1) / 2));
        slider2.setMinimum(inputVertices-1);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                displayValue.setText("Number you've chosen: " + slider2.getValue());
            }
        };
        slider2.addChangeListener(changeListener);
        return slider2;

    }
    public int GetnumberOfEdges(){
        return numberOfEdges;
    }
}