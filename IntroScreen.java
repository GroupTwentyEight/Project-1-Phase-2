import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class IntroScreen {
    private static chooseGraph.GraphDrawComponent_standard FirstGraph;
    private static chooseGraph.GraphDrawComponent_standard SecondGraph;
    private static chooseGraph.GraphDrawComponent_restricted ThirdGraph;
    private static JPanel colorPanel;
    private static JButton colorMaker;
    private static JPanel colorPanel2;
    private static JButton colorMaker2;
    private static JPanel colorPanel3;
    private static JButton colorMaker3;
    // static JOpeningFrame FToBitterEnd;
    private static Color selectedColor = new Color(255, 255, 255);
    static int number = 1;
    private static int verticesInGraph;
    private static Color holdColor = new Color(255, 255, 255);
    static Color assignedColor = new Color(255, 255, 255);
    private static Color[] palette = new Color[50];
    static Color[] record;
    static int[][] graph;
    private static int[][] recentGraph;
    private static int chromatic;
    private static String currentName;


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ignored) {
        }
        // main frame creation
        JFrame OpeningFrame = new JFrame();
        OpeningFrame.setSize(1800, 870);
        OpeningFrame.setTitle("Graph Coloring Opening Screen");
        OpeningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OpeningFrame.setVisible(true);
        // a frame consist of 3 game modes
        JFrame Modes = new JFrame();

        // "to the bitter end" frame
        JFrame FtoBitterEnd = new JFrame();

        // "Best upper bound" frame
        JFrame FBestUpperBound = new JFrame();

        // "random order" frame
        JFrame FRandomOrder = new JFrame();

        // panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.PINK);
        GridBagConstraints c = new GridBagConstraints();

        // title creation and layout
        JLabel title = new JLabel("Graph Coloring");
        title.setFont(title.getFont().deriveFont(90.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(-900, 0, 0, 0);
        panel.add(title, c);

        // start button creation and layout
        JButton start = new JButton("Start");
        start.setFont(title.getFont().deriveFont(36.0f));
        start.setPreferredSize(new Dimension(200, 100));
        c.insets = new Insets(20, 100, 20, 100);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(start, c);

        // action listener for the "start button" to open the "mode" frame
        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                Modes.setVisible(true);
                OpeningFrame.setVisible(false);
            }

        }
        ActionListener listener = new ButtonListener();
        start.addActionListener(listener);

        // quit button creation and layout
        JButton quit = new JButton("Quit");
        quit.setFont(title.getFont().deriveFont(36.0f));
        quit.setPreferredSize(new Dimension(200, 100));
        c.gridy = 2;
        panel.add(quit, c);
        // an action listener to terminate the program
        class ButtonListener2 implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                System.exit(0);
            }

        }
        ActionListener listener2 = new ButtonListener2();
        quit.addActionListener(listener2);

        OpeningFrame.add(panel);

        // now we create the components of the "modes" frame
        // panel has the 3 buttons of the 3 game modes
        Modes.setSize(1800, 870);
        Modes.setTitle("Graph Coloring Main Screen");
        Modes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setOpaque(true); 
        panel3.setBackground(Color.PINK);
        GridBagConstraints s = new GridBagConstraints();

        // title creation and layout
        JLabel title2 = new JLabel("Modes");
        title2.setFont(title2.getFont().deriveFont(90.0f));
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        s.gridx = 0;
        s.gridy = 0;
        s.gridwidth = 1;
        s.fill = GridBagConstraints.BOTH;
        s.anchor = GridBagConstraints.CENTER;
        s.insets = new Insets(-900, 0, 0, 0);
        panel3.add(title2, s);

        // bitter end button creation
        JButton bitterButton = new JButton("To The Bitter End");
        bitterButton.setFont(title2.getFont().deriveFont(36.0f));
        bitterButton.setPreferredSize(new Dimension(400, 100));
        bitterButton.setToolTipText("Information on Bitter Button");
        s.insets = new Insets(20, 100, 20, 100);
        s.gridx = 0;
        s.gridy = 1;
        panel3.add(bitterButton, s);

        // upper bound button creation
        JButton upperButton = new JButton("Best Upper Bound");
        upperButton.setFont(title2.getFont().deriveFont(36.0f));
        upperButton.setPreferredSize(new Dimension(200, 100));
        upperButton.setToolTipText("Information on Upper Button");
        s.gridy = 2;
        panel3.add(upperButton, s);

        // random order button creation
        JButton rand = new JButton("Random Order");
        rand.setFont(title2.getFont().deriveFont(36.0f));
        rand.setPreferredSize(new Dimension(200, 100));
        rand.setToolTipText("Information on Random Button");
        s.gridy = 3;
        panel3.add(rand, s);
        JPanel RulePanel = new JPanel();
        RulePanel.setLayout(new BorderLayout(12, 50));
        RulePanel.setBackground(Color.PINK);
        // a back button goes to the previous frame
        JButton rules = new JButton("Rules");
        rules.setFont(rules.getFont().deriveFont(36.0f));
        rules.setPreferredSize(new Dimension(200, 50));
        RulePanel.add(rules, BorderLayout.EAST);

        JPanel rulesP = new JPanel();
        rulesP.setVisible(false);
        rulesP.setPreferredSize(new Dimension(400, 870));
        rulesP.setLayout(new GridLayout(8,1,0,0));
        rulesP.setBackground(Color.PINK);
        rulesP.setBorder(new EmptyBorder(0, 20, 0, 0));
        Modes.add(rulesP, BorderLayout.WEST);
    
        String ttbe = "You will have to colour the graph using the least amount of colours possible. You will be timed, so make sure you do it as quickly as possible!";
        String bub = "You will have N minutes to colour the graph. \n Your score will be measured on how many colours you will use (the lower the better!)";
        String ro = "Colour the graph in a random order of the vertices.\n You will be scored based on the amount of colours used (the lower the better!). Once the colour of the vertex has been chosen, it cannot be changed, so plan ahead!";
    
        Font rulesF = new Font("default", Font.BOLD, 36);
        Font rulesF2 = new Font("default", Font.BOLD, 25);
    
        JTextArea rulesT1 = new JTextArea("RULES:");
        rulesT1.setFont(rulesF);
        rulesT1.setLineWrap(true);
        rulesT1.setWrapStyleWord(true);
        //rulesT1.setColumns(0);
        rulesT1.setVisible(true);
        rulesT1.setEditable(false);
        rulesT1.setBackground(Color.PINK);
    
        JTextArea rulesT2_1 = new JTextArea("To The Bitter End:");
        rulesT2_1.setLineWrap(true);
        rulesT2_1.setWrapStyleWord(true);
       // rulesT2_1.setColumns(0);
        rulesT2_1.setFont(rulesF2);
        rulesT2_1.setVisible(true);
        rulesT2_1.setEditable(false);
        rulesT2_1.setBackground(Color.PINK);
        rulesT2_1.setSize(400, 50);
    
        JTextArea rulesT2 = new JTextArea(ttbe);
        rulesT2.setLineWrap(true);
        rulesT2.setWrapStyleWord(true);
        //rulesT2.setColumns(0);
        rulesT2.setFont(rulesT2.getFont().deriveFont(15.0f));
        rulesT2.setVisible(true);
        rulesT2.setEditable(false);
        rulesT2.setBackground(Color.PINK);
    
        JTextArea rulesT3_1 = new JTextArea("Best Upper Bound:");
        rulesT3_1.setLineWrap(true);
        rulesT3_1.setWrapStyleWord(true);
        //rulesT3_1.setColumns(0);
        rulesT3_1.setFont(rulesF2);
        rulesT3_1.setVisible(true);
        rulesT3_1.setEditable(false);
        rulesT3_1.setBackground(Color.PINK);
    
        JTextArea rulesT3 = new JTextArea(bub);
        rulesT3.setLineWrap(true);
        rulesT3.setWrapStyleWord(true);
       // rulesT3.setColumns(0);
        rulesT3.setFont(rulesT3.getFont().deriveFont(15.0f));
        rulesT3.setVisible(true);
        rulesT3.setEditable(false);
        rulesT3.setBackground(Color.PINK);
    
        JTextArea rulesT4_1 = new JTextArea("Random Order:");
        rulesT4_1.setCaretPosition(rulesT4_1.getDocument().getLength());
        rulesT4_1.setLineWrap(true);
        rulesT4_1.setWrapStyleWord(true);
        //rulesT4_1.setColumns(0);
        rulesT4_1.setFont(rulesF2);
        rulesT4_1.setVisible(true);
        rulesT4_1.setEditable(false);
        rulesT4_1.setBackground(Color.PINK);
    
        JTextArea rulesT4 = new JTextArea(ro);
        rulesT4.setLineWrap(true);
        rulesT4.setWrapStyleWord(true);
        //rulesT4.setColumns(0);
        rulesT4.setFont(rulesT4.getFont().deriveFont(15.0f));
        rulesT4.setVisible(true);
        rulesT4.setEditable(false);
        rulesT4.setBackground(Color.PINK);
    
      
        rulesP.add(rulesT2_1);
        rulesP.add(rulesT2);
        rulesP.add(rulesT3_1);
        rulesP.add(rulesT3);
        rulesP.add(rulesT4_1);
        rulesP.add(rulesT4);
        
        class rulesListener implements ActionListener {
            int rulesClicked;
            public void actionPerformed(ActionEvent event) {
              rulesClicked++;
              if (rulesClicked % 2 == 0) {
                rulesP.setVisible(false);
              } else {
                rulesP.setVisible(true);
              }
            }
          }
      
        ActionListener ruleList = new rulesListener();
        rules.addActionListener(ruleList);
        // adding all the panel to the frame
        Modes.add(panel3);
        Modes.add(RulePanel, BorderLayout.NORTH);


        //now we create the components of "To The bitter end" frame
        FtoBitterEnd.setSize(1800, 870);
        FtoBitterEnd.setTitle("To The Bitter End");
        FtoBitterEnd.setLayout(new BorderLayout());
        FtoBitterEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // action Listen that opens the "To the bitter end" frame
        class BitterListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                Modes.setVisible(false);
                OpeningFrame.setVisible(false);
                FtoBitterEnd.setVisible(true);
                // popup creation
                chooseGraph testChoice = new chooseGraph();
                // graph visulaization
                FirstGraph = testChoice.new GraphDrawComponent_standard();
                // adding the graph to the frame
                FtoBitterEnd.add(FirstGraph, BorderLayout.CENTER);
                GraphListening();
                PaletteCreation();

            }

        }

        ActionListener listener4 = new BitterListener();
        bitterButton.addActionListener(listener4);

        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        pan.setOpaque(true);
        pan.setBackground(Color.PINK);

        // title creation
        JLabel titl = new JLabel("To The Bitter End ");
        titl.setFont(titl.getFont().deriveFont(50.0f));
        titl.setHorizontalAlignment(SwingConstants.CENTER);
        pan.add(titl);

        colorPanel = new JPanel();
        colorPanel.setSize(300, 870);
        colorPanel.setLayout(new GridLayout(15, 4));

        // color maker button
        colorMaker = new JButton("+");
        colorMaker.setSize(50, 30);
        colorPanel.add(colorMaker);
        FtoBitterEnd.add(colorPanel, BorderLayout.WEST);

        // a panel consist of a timer label, timer
        JPanel pal = new JPanel();
        pal.setLayout(new BorderLayout());
        pal.setBackground(Color.PINK);


        JLabel stopwatch = new JLabel("Stopwatch");
        stopwatch.setFont(stopwatch.getFont().deriveFont(36.0f));
        stopwatch.setHorizontalAlignment(SwingConstants.CENTER);
        pal.add(stopwatch, BorderLayout.NORTH);

        // adding the timer itself to the center of the panel
        pal.add(new StopWatch(), BorderLayout.CENTER);

        // a grid panel has two hint buttons
        JPanel HintPanel = new JPanel();
        HintPanel.setBackground(Color.PINK);
        HintPanel.setLayout(new GridLayout(3, 1));

        JButton stop = new JButton("Stop");
        stop.setFont(stop.getFont().deriveFont(36.0f));
        stop.setPreferredSize(new Dimension(230, 100));
        HintPanel.add(stop);

        class BackListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "You were inside the bitter end game mode. \n Thank you for playing our game! \n By the way, the chromatic number is: " + chromatic);
                OpeningFrame.setVisible(false);
                Modes.setVisible(true);
                FtoBitterEnd.dispose();
                System.exit(0);

            }

        }
        ActionListener backListen = new BackListener();
        stop.addActionListener(backListen);

        JButton smallHintBitter = new JButton("Small Hint");
        smallHintBitter.setFont(smallHintBitter.getFont().deriveFont(36.0f));
        smallHintBitter.setPreferredSize(new Dimension(230, 100));
        HintPanel.add(smallHintBitter);

        JButton bigHintBitter = new JButton("Big Hint");
        bigHintBitter.setFont(bigHintBitter.getFont().deriveFont(36.0f));
        bigHintBitter.setPreferredSize(new Dimension(230, 50));
        HintPanel.add(bigHintBitter);

        // adding the "HintPanel" which has two buttons, in the south of "pal"
        pal.add(HintPanel, BorderLayout.SOUTH);

        // mouse listener for "smallHintBitter" button shows the hint on a popup
        class HintListenerBitter implements MouseListener {
            private Color[] usedColors;

            public void mousePressed(MouseEvent event) {
                usedColors = getPalette();
                getGraph();
                String smallAssist = FirstGraph.giveKleinHints();
                JLabel hintS = new JLabel(smallAssist);
                JDialog popUpColor = new JDialog();
                popUpColor.setSize(400, 50);
                popUpColor.setLocation(900, 50);
                popUpColor.setLayout(new BorderLayout());
                popUpColor.add(hintS);
                popUpColor.setVisible(true);
                popUpColor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        HintListenerBitter tinyHints = new HintListenerBitter();
        smallHintBitter.addMouseListener(tinyHints);

        // mouse listener for "bigHintBitter" button shows the hint on a popup
        class HintListenerBitter2 implements MouseListener {
            private Color[] usedColors;
            String result;

            public void mousePressed(MouseEvent event) {
                //getRecords();
                usedColors = getPalette();
                getGraph();
                String bigAssist = FirstGraph.giveGroteHints();
                JLabel hint1 = new JLabel(bigAssist);
                JDialog popUpColor = new JDialog();
                popUpColor.setSize(400, 50);
                popUpColor.setLocation(900, 50);
                popUpColor.setLayout(new BorderLayout());
                popUpColor.add(hint1);
                popUpColor.setVisible(true);
                popUpColor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        HintListenerBitter2 biggieHints = new HintListenerBitter2();
        bigHintBitter.addMouseListener(biggieHints);

        // adding all the panels to "FtoBitterEnd" frame
        FtoBitterEnd.add(pan, BorderLayout.NORTH);
        FtoBitterEnd.add(pal, BorderLayout.EAST);

        // now we create all the components of the BestUpperBound frame
        FBestUpperBound.setTitle("Best Upper Bound");
        FBestUpperBound.setSize(1800, 870);
        FBestUpperBound.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // action listener for "upperButton" to open "FBestUpperBound" frame
        class upperBoundListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                Modes.setVisible(false);
                OpeningFrame.setVisible(false);
                FtoBitterEnd.setVisible(false);
                FBestUpperBound.setVisible(true);
                // calling for the popups
                chooseGraph testChoice2 = new chooseGraph();
                // graph visualization
                TimeSelection spinnerFrame = new TimeSelection();
                SecondGraph = testChoice2.new GraphDrawComponent_standard();
                // adding the graph to the frame
                FBestUpperBound.add(SecondGraph);
                GraphListening2();
                PalleteCreation2();

            }

        }
        ActionListener UpperListener = new upperBoundListener();
        upperButton.addActionListener(UpperListener);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.PINK);

        // label creation
        JLabel titleB = new JLabel("Best Upper Bound");
        titleB.setFont(titleB.getFont().deriveFont(50.0f));
        panel4.add(titleB);

        colorPanel2 = new JPanel();
        colorPanel2.setSize(300, 870);
        colorPanel2.setLayout(new GridLayout(15, 4));

        // a button to make the colors
        colorMaker2 = new JButton("+");
        colorMaker2.setSize(50, 30);
        colorPanel2.add(colorMaker2);

        // panel in the east of "UpperBound" frame has timer label, timer and a panel
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBackground(Color.PINK);

        JLabel timer2 = new JLabel("Timer");
        timer2.setFont(timer2.getFont().deriveFont(36.0f));
        outerPanel.add(timer2, BorderLayout.NORTH);
        outerPanel.add(new CountDown(), BorderLayout.CENTER);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(3, 1));
        innerPanel.setBackground(Color.PINK);

        JButton stopUpperBound = new JButton("Stop");
        stopUpperBound.setFont(stopUpperBound.getFont().deriveFont(36.0f));
        stopUpperBound.setPreferredSize(new Dimension(230, 100));
        innerPanel.add(stopUpperBound);

        class BacklistenerUpperBound implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "You were inside the best upper bound game mode. \n Thank you for playing our game! \n By the way, the chromatic number is: " + chromatic);
                OpeningFrame.setVisible(false);
                Modes.setVisible(true);
                FBestUpperBound.dispose();
                System.exit(0);

            }

        }
        ActionListener backListenUB = new BacklistenerUpperBound();
        stopUpperBound.addActionListener(backListenUB);

        JButton smallHintUpper = new JButton("Small Hint");
        smallHintUpper.setFont(smallHintUpper.getFont().deriveFont(36.0f));
        smallHintUpper.setPreferredSize(new Dimension(230, 50));
        innerPanel.add(smallHintUpper);

        JButton bigHintUpper = new JButton("Big Hint");
        bigHintUpper.setFont(bigHintUpper.getFont().deriveFont(36.0f));
        bigHintUpper.setPreferredSize(new Dimension(230, 50));
        innerPanel.add(bigHintUpper);

        outerPanel.add(innerPanel, BorderLayout.SOUTH);

        class smallUpperHintListener implements MouseListener {
            private Color[] usedColors2;
            String result;

            public void mousePressed(MouseEvent event) {
                usedColors2 = getPalette2();
                getGraph2();
                String smallAssist2 = SecondGraph.giveKleinHints();
                JLabel hintS2 = new JLabel(smallAssist2);
                JDialog popUpColor = new JDialog();
                popUpColor.setSize(400, 50);
                popUpColor.setLocation(900, 50);
                popUpColor.setLayout(new BorderLayout());
                popUpColor.add(hintS2);
                popUpColor.setVisible(true);
                popUpColor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        smallUpperHintListener tiny = new smallUpperHintListener();
        smallHintUpper.addMouseListener(tiny);

        class BigUpperHintListener implements MouseListener {
            private Color[] usedColors2;
            String result;

            public void mousePressed(MouseEvent event) {
                usedColors2 = getPalette2();
                getGraph2();
                String bigAssist2 = SecondGraph.giveGroteHints();
                JLabel hint1 = new JLabel(bigAssist2);
                JDialog popUpColor = new JDialog();
                popUpColor.setSize(400, 50);
                popUpColor.setLocation(900, 50);
                popUpColor.setLayout(new BorderLayout());
                popUpColor.add(hint1);
                popUpColor.setVisible(true);
                popUpColor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        BigUpperHintListener biggie1 = new BigUpperHintListener();
        bigHintUpper.addMouseListener(biggie1);

        // adding all of the panels to the "FBestUpperBound" frame
        FBestUpperBound.add(panel4, BorderLayout.NORTH);
        FBestUpperBound.add(colorPanel2, BorderLayout.WEST);
        FBestUpperBound.add(outerPanel, BorderLayout.EAST);

        // now we create the components of the "RandomOrder" frame
        FRandomOrder.setTitle("Random Order");
        FRandomOrder.setSize(1800, 870);
        FRandomOrder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // action listener to the "random order button" to open the "Random order frame"
        class randomListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                OpeningFrame.setVisible(false);
                Modes.setVisible(false);
                FtoBitterEnd.setVisible(false);
                FBestUpperBound.setVisible(false);
                FRandomOrder.setVisible(true);
                // calling the popups
                chooseGraph testChoice3 = new chooseGraph();
                // visualizing the graph
                ThirdGraph = testChoice3.new GraphDrawComponent_restricted();
                // adding the graph to the frame
                FRandomOrder.add(ThirdGraph);
                GraphListening3();
                PalleteCreation3();
            }

        }
        randomListener RandomListen = new randomListener();
        rand.addActionListener(RandomListen);

        // panel and layout
        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setOpaque(true);
        panel5.setBackground(Color.PINK);

        // title creation
        JLabel titleR = new JLabel("Random Order");
        titleR.setFont(titleR.getFont().deriveFont(50.0f));
        panel5.add(titleR);

        colorPanel3 = new JPanel();
        colorPanel3.setSize(300, 870);
        colorPanel3.setLayout(new GridLayout(15, 4));

        colorMaker3 = new JButton("+");
        colorMaker3.setSize(50, 30);
        colorPanel3.add(colorMaker3);

        JPanel OuterPanel2 = new JPanel();
        OuterPanel2.setLayout(new BorderLayout());
        OuterPanel2.setBackground(Color.PINK);

        JLabel timer3 = new JLabel("Timer");
        timer3.setFont(timer3.getFont().deriveFont(36.0f));
        OuterPanel2.add(timer3, BorderLayout.NORTH);
        OuterPanel2.add(new CountDown(), BorderLayout.CENTER);

        JPanel innerPanel2 = new JPanel();
        innerPanel2.setLayout(new GridLayout(3, 1));
        innerPanel2.setBackground(Color.PINK);

        JButton stopRandomOrder = new JButton("Stop");
        stopRandomOrder.setFont(stopRandomOrder.getFont().deriveFont(36.0f));
        stopRandomOrder.setPreferredSize(new Dimension(230, 100));
        innerPanel2.add(stopRandomOrder);

        class BackListenerRandomOrder implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "You were inside the random order game mode. \n Thank you for playing our game! \n By the way, the chromatic number is: " + chromatic);
                OpeningFrame.setVisible(false);
                Modes.setVisible(true);
                FRandomOrder.dispose();
                System.exit(0);

            }

        }
        ActionListener backListenRO = new BackListenerRandomOrder();
        stopRandomOrder.addActionListener(backListenRO);

        JButton smallHintRandom = new JButton("Small Hint");
        smallHintRandom.setFont(titleR.getFont().deriveFont(36.0f));
        smallHintRandom.setPreferredSize(new Dimension(230, 50));
        innerPanel2.add(smallHintRandom);

        JButton bigHintRandom = new JButton("Big Hint");
        bigHintRandom.setFont(bigHintRandom.getFont().deriveFont(36.0f));
        bigHintRandom.setPreferredSize(new Dimension(230, 50));
        innerPanel2.add(bigHintRandom);

        OuterPanel2.add(innerPanel2, BorderLayout.SOUTH);

        // mounse listener for "smallHintBitter" button shows the hint on a popup
        class HintListenerRandom implements MouseListener {
            Color[] usedColors;
            String result;

            public void mousePressed(MouseEvent event) {
                usedColors = getPalette3();
                getGraph3();
                String smallAssist = ThirdGraph.giveKleinHints();
                JLabel hintS = new JLabel(smallAssist);
                JDialog popUpColor = new JDialog();
                popUpColor.setSize(400, 50);
                popUpColor.setLocation(900, 50);
                popUpColor.setLayout(new BorderLayout());
                popUpColor.add(hintS);
                popUpColor.setVisible(true);
                popUpColor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        HintListenerRandom tinyHintRand = new HintListenerRandom();
        smallHintRandom.addMouseListener(tinyHintRand);

        // mounse listener for "bigHintBitter" button shows the hint on a popup
        class HintListenerRandom2 implements MouseListener {
            public void mousePressed(MouseEvent event) {
                initialCN hintsgraph = new initialCN(verticesInGraph, recentGraph);
                chromatic = initialCN.findCN();
                JOptionPane.showMessageDialog(null, "By the way, the chromatic number is: " + chromatic);
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        HintListenerRandom2 biggie = new HintListenerRandom2();
        bigHintRandom.addMouseListener(biggie);

        // adding all panels to the "FRandomOrder" frame
        FRandomOrder.add(colorPanel3, BorderLayout.WEST);
        FRandomOrder.add(panel5, BorderLayout.NORTH);
        FRandomOrder.add(OuterPanel2, BorderLayout.EAST);

        Modes.validate();
        FtoBitterEnd.validate();
        FBestUpperBound.validate();
        FRandomOrder.validate();
        OpeningFrame.validate();

    }


    private static void GraphListening() {

        verticesInGraph = FirstGraph.getNumberOfVerts();
        //counterOfColors = new Color[verticesInGraph + 1];

        class ColorListener implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Object ha = event.getSource();
                chooseGraph.GraphDrawComponent_standard trialy = (chooseGraph.GraphDrawComponent_standard) ha;
                int xLoc = event.getX();
                int yLoc = event.getY();
                // Sets color for the node that the user has chosen.
                trialy.setColor(selectedColor, xLoc, yLoc, "10");
                trialy.repaint();
                holdColor = selectedColor;
                record=FirstGraph.receiveColorRecord();

            }

            public void mouseReleased(MouseEvent event) {
                warning warningPopUp1 = new warning();
                warningPopUp1.warning_standard();
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }

        ColorListener hi = new ColorListener();
        FirstGraph.addMouseListener(hi);
        recentGraph = FirstGraph.receiveGraph();
        currentName = FirstGraph.getName();

        initialCN hintsgraph = new initialCN(verticesInGraph, recentGraph);
        chromatic = initialCN.findCN();


    }

    private static void getGraph() {
        recentGraph = FirstGraph.receiveGraph();
    }


    private static void PaletteCreation() {

        record = new Color[verticesInGraph + 1];

        class ColorHolder implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Component jo = event.getComponent();
                Buttonv2 hy = (Buttonv2) jo;
                selectedColor = hy.getColor();

            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        class SwatchParty implements MouseListener {
            int count = 0;
            int number = 1;

            public void mousePressed(MouseEvent event) {
                record = FirstGraph.receiveColorRecord();
                // int index=Integer.parseInt(currentName);
                if (number < 51) {
                    Buttonv2 swatch = new Buttonv2();
                    MouseListener hi = new ColorHolder();
                    swatch.setBackground();
                    palette[count] = swatch.getColor();
                    FirstGraph.receivePaint(palette[count]);
                    count++;
                    swatch.setLabel(String.valueOf(number));
                    swatch.addMouseListener(hi);
                    colorPanel.add(swatch, BorderLayout.WEST);
                    number++;
                    //record[index]=swatch.getColor();
                    //record[index]=counterOfColors[index];
                }

            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {

            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        // Adds the mouse listener to the Color Maker button.
        MouseListener secondaryListener = new SwatchParty();
        colorMaker.addMouseListener(secondaryListener);
        recentGraph = FirstGraph.receiveGraph();
        //record = FirstGraph.receiveColorRecord();

        //HintSystem hints= new HintSystem();
        //hinty=hints.giveHint(0,palette,record,recentGraph);
        //System.out.println(palette[0]);
    }

    private static Color[] getPalette() {
        return palette;
    }


    private static void GraphListening2() {

        verticesInGraph = SecondGraph.getNumberOfVerts();
        //record = new Color[verticesInGraph + 1];

        class ColorListener implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Object ha = event.getSource();
                chooseGraph.GraphDrawComponent_standard trialy = (chooseGraph.GraphDrawComponent_standard) ha;
                int xLoc = event.getX();
                int yLoc = event.getY();
                // Sets color for the node that the user has chosen.
                trialy.setColor(selectedColor, xLoc, yLoc, "10");
                trialy.repaint();
                holdColor = selectedColor;
                record=SecondGraph.receiveColorRecord();


            }

            public void mouseReleased(MouseEvent event) {
                warning warningPopUp2 = new warning();
                warningPopUp2.warning_standard();
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

        }

        ColorListener hi = new ColorListener();
        SecondGraph.addMouseListener(hi);
        recentGraph = SecondGraph.receiveGraph();
        currentName = SecondGraph.getName();
        //record = SecondGraph.receiveColorRecord();

        initialCN hintsgraph = new initialCN(verticesInGraph, recentGraph);
        chromatic = initialCN.findCN();
        //System.out.println("chromatic: " + chromatic);
    }

    public static void getGraph2() {
        recentGraph = SecondGraph.receiveGraph();
    }

    public static void PalleteCreation2() {
        record = new Color[verticesInGraph + 1];
        class ColorHolder implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Component jo = event.getComponent();
                Buttonv2 hy = (Buttonv2) jo;
                selectedColor = hy.getColor();
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        class SwatchParty implements MouseListener {
            int number = 1;
            int count = 0;

            public void mousePressed(MouseEvent event) {
                record = SecondGraph.receiveColorRecord();
                if (number < 51) {
                    Buttonv2 swatch = new Buttonv2();
                    MouseListener hi = new ColorHolder();
                    swatch.setBackground();
                    palette[count] = swatch.getColor();
                    SecondGraph.receivePaint(palette[count]);
                    count++;
                    swatch.setLabel(String.valueOf(number));
                    swatch.addMouseListener(hi);

                    colorPanel2.add(swatch, BorderLayout.WEST);
                    number++;
                }
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        // Adds the mouse listener to the Color Maker button.
        MouseListener secondaryListener = new SwatchParty();
        colorMaker2.addMouseListener(secondaryListener);
        recentGraph = SecondGraph.receiveGraph();
    }

    public static Color[] getPalette2() {
        return palette;
    }

    public static void GraphListening3() {

        verticesInGraph = ThirdGraph.getNumberOfVerts();
        Color[] counterOfColors = new Color[verticesInGraph + 1];

        // Colors the graph with the user selected color.
        class ColorListener implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Object ha = event.getSource();
                chooseGraph.GraphDrawComponent_restricted trialy = (chooseGraph.GraphDrawComponent_restricted) ha;
                int xLoc = event.getX();
                int yLoc = event.getY();
                // Sets color for the node that the user has chosen.
                trialy.setColor(selectedColor, xLoc, yLoc, "10");
                trialy.repaint();
                // Holds the recently selected color.
                holdColor = selectedColor;
                record=ThirdGraph.receiveColorRecord();
            }

            public void mouseReleased(MouseEvent event) {
                warning warningPopUp3 = new warning();
                warningPopUp3.warning_restricted();
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        ColorListener hi = new ColorListener();
        ThirdGraph.addMouseListener(hi);
        currentName = ThirdGraph.getName();
        // Gets the graph from the Graph Draw Component class.
        recentGraph = ThirdGraph.receiveGraph();
        //record = ThirdGraph.receiveColorRecord();

        initialCN hintsgraph = new initialCN(verticesInGraph, recentGraph);
        chromatic = initialCN.findCN();
        //System.out.println("chromatic: " + chromatic);
    }

    private static void getGraph3() {
        recentGraph = ThirdGraph.receiveGraph();
    }

    private static void PalleteCreation3() {
        record = new Color[verticesInGraph + 1];
        class ColorHolder implements MouseListener {
            public void mousePressed(MouseEvent event) {
                Component jo = event.getComponent();
                Buttonv2 hy = (Buttonv2) jo;
                selectedColor = hy.getColor();
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        // Mouse Listener for the "Color Maker" button.
        class SwatchParty implements MouseListener {
            int count = 0;
            int number = 1;

            public void mousePressed(MouseEvent event) {
                record = ThirdGraph.receiveColorRecord();
                Buttonv2 swatch = new Buttonv2();
                MouseListener hi = new ColorHolder();
                swatch.setBackground();
                // NEW ADDITION!!!!!
                // Keeps track of the different colors the user has generated.
                palette[count] = swatch.getColor();
                ThirdGraph.receivePaint(palette[count]);
                count++;
                swatch.setLabel(String.valueOf(number));
                swatch.addMouseListener(hi);
                colorPanel3.add(swatch);
                number++;
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        }
        // Adds the mouse listener to the Color Maker button.
        MouseListener secondaryListener = new SwatchParty();
        colorMaker3.addMouseListener(secondaryListener);
        recentGraph = ThirdGraph.receiveGraph();
    }

    private static Color[] getPalette3() {
        return palette;
    }
}
