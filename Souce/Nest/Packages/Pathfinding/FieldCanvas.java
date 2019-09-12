package Pathfinding;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class FieldCanvas extends Canvas {
	
	public Image Background;

	@Override
	public void paint(Graphics g) {
        setBackground(Color.WHITE);  
		setForeground(Color.RED);  
		g.fillRect(130, 30,100, 80); 
		if (Background != null) {
			g.drawImage(Background, 0, 0, this.getWidth(), this.getHeight(), null);
			
		}
		
	}
	
	public FieldCanvas() {}
	
	public FieldCanvas(Image Image) {
		Background = Image;		
	}
	
	
	
}
