import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class askVertice {
    private int inputVertices;

    public askVertice() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel optionPanel = new JPanel(new GridLayout(3, 1));
        JOptionPane optionPane = new JOptionPane();
        JSlider slider = getSlider(optionPane);
        //JSlider slider2 = getSlider2(optionPane);
        optionPane.setMessage(new Object[]{"Select the number of vertices: ", slider});
        //optionPane.setMessage(new Object[] { "Select the number of edges: ", slider2 });
        optionPane.setPreferredSize(new Dimension(900, 400));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPanel.add(optionPane);
        JDialog dialog = optionPane.createDialog(frame, "Graph Creation Options");
        dialog.setVisible(true);
        System.out.println(optionPane.getInputValue());

    }

    public JSlider getSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        // Have the starting point be at 1
        slider.setValue(1);
        slider.setMaximum(50);
        slider.setMinimum(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setInputValue(theSlider.getValue());
                    inputVertices = theSlider.getValue();
                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }

    public int getInputVertices() {
        return inputVertices;
    }

}
