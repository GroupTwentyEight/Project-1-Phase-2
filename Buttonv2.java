import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
public class Buttonv2 extends Button{

	//Stores the color value of each button
	private Color currentColor;
	
	//Values used to initialize colors and location of the swatches. 
	private static int y=0;
	private static int x=0;
	private static int z=0;
	
	
	public Buttonv2(){
		super();
		y++;
		z++;
		//z controls the y position of the swatch. Here, when swatch 16, 31 and 46 are created, the y coordinate is reset to 1. 		
		if (y==15 || y==29 || y==43){
				z=1;
		}
				
				if (y<=14) {
					x=30;
				}
				
				
				if (y > 14 && y<=28) {
					x=80;
				} 
				
				if (y >= 29 && y<=44){
					x=130;
					
				}
				if(y>42 ){
					x=180;
				}
		super.repaint();
	}
	public void setSize(){
		super.setSize(50,50);
	}
	public void setBackground(){
		
    int[] colorpalette1 = {0, 0, 1, 213, 255, 158, 14, 255, 0, 0, 149, 255, 164, 0, 145, 98, 107, 0, 0, 106, 0, 194, 190, 0, 95, 255, 255, 255, 104, 255, 150, 152, 167, 1, 255, 254, 189, 1, 187, 117, 165, 255, 119, 122, 38, 0, 67, 181, 255, 255, 144};
    int[] colorpalette2 = {0, 0, 0, 255, 0, 0, 76, 229, 95, 255, 0, 147, 36, 21, 208, 14, 104, 0, 125, 130, 174, 140, 153, 143, 173, 0, 0, 2, 61, 116, 138, 255, 87, 255, 238, 137, 198, 208, 136, 68, 255, 166, 77, 71, 52, 71, 0, 0, 177, 219, 251};
    int[] colorpalette3 = {0, 0, 103, 0, 86, 142, 161, 2, 57, 0, 58, 126, 0, 68, 203, 0, 130, 255, 181, 108, 126, 159, 112, 156, 78, 0, 246, 157, 59, 163, 232, 82, 64, 254, 232, 0, 255, 255, 0, 177, 210, 254, 0, 130, 0, 84, 44, 255, 103, 102, 146};
		Color newPaint = new Color((int) colorpalette1[y], (int) colorpalette2[y], (int) colorpalette3[y]);
		super.setBackground(newPaint);
		currentColor=newPaint;
	}
	public Color getColor(){
		return currentColor;
	}
}
