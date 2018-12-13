import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
//User cannot change color of the node if they already colored it.
public class palletteGraphDraw_restricted  {
	static Color selectedColor=new Color(255,255,255);
	static int number=1;
	static String tmp;
	static int verticesInGraph;
	static Color holdColor=new Color(255,255,255);
	static Color assignedColor=new Color(255,255,255);
    //Here is some example syntax for the GraphDraw class
    public static void main(String[] args) {
    	try{
	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	}
	catch (UnsupportedLookAndFeelException e){
	}
	catch(ClassNotFoundException e){
	}
	catch (InstantiationException e){
	}
	catch(IllegalAccessException e){
	}
    	
    	JPanel panelGraph = new JPanel();
        JPanel panelRect = new JPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panelGraph);
        mainPanel.add(panelRect);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(5,5));
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(1500, 820);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Adding the main panel to the frame
        frame.getContentPane().add(mainPanel);

        putStuffInPanel(frame.getContentPane());
        frame.setVisible(true);
           
	//Creates the button that adds colors to the palette ("Color Maker").
	Button colorMaker= new Button("+");
	colorMaker.setSize(30,30);
	colorMaker.setLocation(1100,5);
	
	//Adds the button to the frame.
	frame.add(colorMaker);
	
	//Mouse Listener for each swatch.
	class ColorHolder implements MouseListener{
		public void mousePressed(MouseEvent event){
			Component jo= event.getComponent();
			Buttonv2 hy=(Buttonv2) jo;
			selectedColor=hy.getColor();
		}
		public void mouseReleased(MouseEvent event){}
		public void mouseClicked(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}
	//Mouse Listener for the "Color Maker" button.
	class SwatchParty implements MouseListener
	{
		public void mousePressed(MouseEvent event){
			Buttonv2 swatch = new Buttonv2();
			MouseListener hi= new ColorHolder();
			swatch.setBackground();
			swatch.setLabel(tmp.valueOf(number));
			swatch.addMouseListener(hi);
			frame.add(swatch, BorderLayout.LINE_END);
			number++;
		}
	
		public void mouseReleased(MouseEvent event){}
		public void mouseClicked(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}
	//Adds the mouse listener to the Color Maker button.
	MouseListener secondaryListener= new SwatchParty();
	colorMaker.addMouseListener(secondaryListener);
    }
    public static void putStuffInPanel(Container pane) {
        pane.setLayout(new BorderLayout(5,5));

        JLabel text = new JLabel("Header panel thing");
        pane.add(text, BorderLayout.PAGE_START);
		chooseGraph testChoice = new chooseGraph();
        chooseGraph.GraphDrawComponent_restricted gdc = testChoice.new GraphDrawComponent_restricted();
        
        verticesInGraph=gdc.getNumberOfVerts();
        Color[] counterOfColors=new Color[verticesInGraph+1];
        pane.add(gdc,BorderLayout.CENTER);
        JLabel sideText = new JLabel("Side panel");
        //Colors the graph with the user selected color.
        class ColorListener implements MouseListener{
		public void mousePressed(MouseEvent event){
			Object ha= event.getSource();
			chooseGraph.GraphDrawComponent_restricted trialy= (chooseGraph.GraphDrawComponent_restricted) ha;
			int xLoc=event.getX();
			int yLoc=event.getY();
			trialy.setColor(selectedColor,xLoc,yLoc,"10");
			trialy.repaint();
			
			holdColor=selectedColor;

		}
		public void mouseReleased(MouseEvent event){}
		public void mouseClicked(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}
	 ColorListener hi = new ColorListener();
        gdc.addMouseListener(hi);
    }

}
