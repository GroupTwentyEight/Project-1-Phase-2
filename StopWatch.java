import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



    public class StopWatch extends JLabel {

        private Timer timer;
        private long startTime = -1;
        private long duration = 3600000;

        private JLabel label;

        public StopWatch() {
            setLayout(new GridBagLayout());
            timer = new Timer(10, new ActionListener() {
                //@Override
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
                    label.setText(df.format(clockTime));
                }
            });
            timer.setInitialDelay(0);
            addMouseListener(new MouseAdapter() {
                //@Override
                public void mouseClicked(MouseEvent e) {
                    long now = System.currentTimeMillis();
                    long clockTime = now - startTime;
                    if (!timer.isRunning()) {
                        startTime = -1;
                        timer.start();
                    }
                    if (clockTime < duration) {
                        timer.stop();
                    }
                }
            });
            label = new JLabel("Click to start \n 00:00:000");
            label.setFont(label.getFont().deriveFont(36.0f));
            add(label);
        }

        //@Override
        public Dimension getPreferredSize() { return new Dimension(200, 200);
        }

    }
