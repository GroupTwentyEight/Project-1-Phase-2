import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TimeSelection {
    public double amountOfMinutes;

    public TimeSelection() {
        JSpinner spinner;
        SpinnerNumberModel spinnerModel;
        spinnerModel = new SpinnerNumberModel(5.0, 0.0, 20.0, 0.5);
        spinner = new JSpinner(spinnerModel);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane optionPane = new JOptionPane();
        optionPane.setPreferredSize(new Dimension(350, 170));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(frame, "Set timer");
        optionPane.setMessage(new Object[] { "How many minutes do you think you need?", spinner });
        dialog.setVisible(true);
        amountOfMinutes = (double) spinner.getValue();
    }
}
