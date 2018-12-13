import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;



public class CountDown extends JLabel {
    TimeSelection ts = new TimeSelection();
    private Timer timer;
    private long startTime = -1;
    private long duration = 60000 * (long) ts.amountOfMinutes;

    private JLabel label;

    public CountDown() {
        setLayout(new GridBagLayout());
        timer = new Timer(10, new ActionListener() {
            //Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                label.setText(df.format(duration - clockTime));
            }
        });
        timer.setInitialDelay(0);
        addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseClicked(MouseEvent e) {
                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }
            }
        });
        label = new JLabel("Click to start");
        label.setFont(label.getFont().deriveFont(36.0f));
        add(label);
    }

    //@Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
