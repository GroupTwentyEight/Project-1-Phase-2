import java.awt.*;
import java.awt.geom.*;
import java.awt.Color;

public class Vertex { // a class for the vertices
        int x, y;
        private int width = 30;
        private int height = 30;
        String name;
        Color selectedColor = new Color(255,0,0);
        Color BorderColor = new Color(255,255,255);

        public Vertex(String name, int x, int y, Color c, Color bc) { //constructor
            this.x = x;
            this.y = y;
            this.name = name;
            selectedColor=c;
            BorderColor = bc;
        }
        public void draw(Graphics2D g2){
        	g2.setColor(selectedColor);
        	FontMetrics f = g2.getFontMetrics();
        	int vertexHeight = Math.max(height, f.getHeight());
        	int vertexWidth = Math.max(width, f.stringWidth(name) + width / 2);
            g2.fillOval(x - vertexWidth / 2, y - vertexHeight / 2, vertexWidth, vertexHeight);
            g2.setColor(BorderColor); //color of the edges of the vertex and its name
            g2.drawOval(x - vertexWidth / 2, y - vertexHeight / 2, vertexWidth, vertexHeight);
            g2.drawString(name, x - f.stringWidth(name) / 2, y + f.getHeight() / 2);
        }
        public void setColor(Color c){
        	selectedColor=c;
        }
        
        public void setBorder(Color color) {
            BorderColor = color;
        }
       
    }
