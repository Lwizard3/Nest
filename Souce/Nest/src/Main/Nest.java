package Main;
import javax.imageio.ImageIO;

import javax.swing.*;

import Pathfinding.Path;
import Robot.Robot;

import java.io.IOException;
import java.util.*;
import UI.*;
import Utility.*;
import Utility.Error;
import Utility.Error.ErrorType;
import Utility.Math.*;
import Utility.Math.DoublePoint;


public class Nest {
	
	
	
	public Window WindowLocation = Window.MainMenu;
	
	public ImageIcon NestIcon;
	
	public Robot Robot;
	
	public static void main(String[] args) {
		
		Nest Nest = new Nest();	
		
		Nest.startup();		

		Nest.changeWindow(Window.MainMenu);
	}
	
	public void changeWindow(Window window) {
		
		WindowLocation = window;
		
		switch(WindowLocation) {
		case MainMenu:
			new MainMenu(this);			
			break;
		case Pathfinding:
			new PathfindingWindow(this);			
			break;
		case Schedule:
			new ScheduleWindow(this);			
			break;			
		case Robot:
			new RobotWindow(this);
			break;
		default:
			new Error("Window file for " + window + " not found", ErrorType.Fatal);
		}
	}
	
	public void startup() {		
		
		/*
		String S  = "";
		
		ArrayList<DoublePoint> P = new ArrayList<DoublePoint>();
		
		P.add(new DoublePoint(1, 1));
		P.add(new DoublePoint(2, 3));
		P.add(new DoublePoint(5, 4));
		P.add(new DoublePoint(6, 5));
		P.add(new DoublePoint(1, 4));
		P.add(new DoublePoint(4, 2));
		P.add(new DoublePoint(6, 4));
		P.add(new DoublePoint(4, 0));
		P.add(new DoublePoint(0, 2));
		P.add(new DoublePoint(5, 4));
		
		Path path = new Path(P);
		
		path.calculate();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		for (int i = 0; i < path.Xconstants.size(); i++) {
			S = "";
			S += "(";
			for(int k = 0; k < 4; k++) {
				S += path.Xconstants.get(i)[k] + "t^" + (3-k);
				
				if (k != 3) {					
					S += " + ";
				}
				
			}
			
			S += ", ";
			
			for(int k = 0; k < 4; k++) {
				S += path.Yconstants.get(i)[k] + "t^" + (3-k);
				
				if (k != 3) {					
					S += " + ";
				}
				
			}
			
			S += ")";
			
			System.out.println(S);
			
		}
		
		
		for (float i = 0; i < 1; i += 0.01f) {
			System.out.println("(" + path.get(i).getX() + "," + path.get(i).getY() + ")");
		}
		*/
		
		
		
		//System.exit(0);
		
		
		try {
			NestIcon = new ImageIcon(ImageIO.read(getClass().getResource("/NestLogo.png")));
		} catch (IOException e) {
			new Error("Cannot load nest logo: " + e.getMessage(), ErrorType.NonFatal);			
		}	
		
		List<String> DefaultFiles = new ArrayList<String>(); 
		List<String> DefaultDirectories = new ArrayList<String>();
		DefaultDirectories.add("Data");		
		DefaultDirectories.add("Data/Fields");
		DefaultDirectories.add("Data/Eggs");
		DefaultDirectories.add("Data/Eggs/Default");
		DefaultDirectories.add("Data/Eggs/Generated");
		//DefaultFiles.add("Data/Fields/index.xml");
		//DefaultFiles.add("Data/Eggs/Generated/index.xml");
		
		FileCreator.CreateFiles(DefaultFiles, DefaultDirectories);
		

		
		
			
	}
	
	

}
