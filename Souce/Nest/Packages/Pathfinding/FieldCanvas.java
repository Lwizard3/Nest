package Pathfinding;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import frc.robot.Egg.Pathfinding.Path;
import frc.robot.Egg.Utility.ColorFlux;
import frc.robot.Egg.Utility.Math.DoublePoint;

public class FieldCanvas extends Canvas {
	
	//This is the custom canvas that will allow drawing of fields
	
	public Image Background;
	public ArrayList<Path> paths = new ArrayList<Path>();
	public ArrayList<Point> points = new ArrayList<Point>();
	public float pathResolution = 25000;
	Random R = new Random();
		
	ColorFlux F = new ColorFlux();

	@Override
	public void paint(Graphics g) {		
        setBackground(Color.WHITE);  
		setForeground(Color.RED);  
		 
		if (Background != null) {
			g.drawImage(Background, 0, 0, this.getWidth(), this.getHeight(), null);
			
		}
		
		Point temp;
		int c = 0;		
		
		
		F.Flux();
		
		/*
		
		while (F.color != new Color(255, 0, 0)) {
			c++;
			g.setColor(F.color);
			F.Flux();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.fillRect(0, 0, 1000, 700);
		}
		
		*/
		
		//g.drawLine(0, 300, 1000, 300);
		
		for (Point P : points) {
			if (!(P.x < 0 || P.y < 0)) {
				g.fillOval(P.x-(10/2), P.y-(10/2), 10, 10);
				//drawNode(P.x, P.y, 10, 0, g);
			}
		}
		
		
		for (Path P : paths) {
						
			c = 0;			
			
			for (DoublePoint Point : P.navPoints) {
				temp = Point.getPoint();
				c++;
				drawNode(temp.x, temp.y, 10, c, g);
			}
			
			c = 0;
			
			g.setColor(F.Flux(100));
			
			for (int i = 0; i < pathResolution; i++) {
				temp = P.get((double)i / pathResolution).getPoint();
				double T2 = 300 + (5 / -P.getD1((double)i / pathResolution));
				
				//g.setColor(F.Flux(1));
				
				if (c != P.f) {				
					g.setColor(F.Flux(100));
					c++;
				}
				
				//g.fillOval(temp.x-(10/2), (int)T2-(10/2), 10, 10);
				
				g.drawRect(temp.x, temp.y, 1, 1);					
				
			}		
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			//g.setColor(Color.WHITE);
			//g.fillRect(0, 0, 1000, 700);
		}		
		
	}
	
	public FieldCanvas() {
	}
	
	public FieldCanvas(Image Image) {
		Background = Image;	
	}
	
	public void drawNode(int x, int y, int r, int p, Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(x - r, y, x, y);
		g.drawLine(x + r, y, x, y);
		g.drawLine(x, y - r, x, y);
		g.drawLine(x, y + r, x, y);
		g.drawString(String.valueOf(p), x + r, y - r);
	}
	
	
	
}
