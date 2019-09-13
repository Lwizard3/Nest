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
	
	//Welcome to the Nest class!  This is the main class that drives the code
	//You will notice variables declared arround my code that appear similar,
	//such as the NestIcon icon which holds the nest image
	
	public Window WindowLocation = Window.MainMenu;
	
	public ImageIcon NestIcon;
	
	public Robot Robot;
	
	public static void main(String[] args) {
		
		Nest Nest = new Nest();	
		
		Nest.startup();		

		Nest.changeWindow(Window.MainMenu);
	}
	
	//This method is the switchboard of nest.  Each window class has a refrence to Nest and
	//will call changeWindow to switch to a diffrent window
	
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
	
	//This is the startup folder, where code that needs to run at the beginning are called.
	//Sorry for the mess, it is also a testing ground for new code.  Note the file creation
	//at the bottom, as Nest is a fully packaged jar file it generates its own folder system
	//where the jar is located
	
	
	public void startup() {		
		
		/*
		
		String S  = "";
		
		ArrayList<DoublePoint> P = new ArrayList<DoublePoint>();	
		
		Random R = new Random();
		
		int x;
		int y;
		
		for (int i = 0; i < 100; i++) {
			
			x = R.nextInt(200) - 100;
			y = R.nextInt(200) - 100;
			
			System.out.println("(" + x + "," + y + ")");
			
			P.add(new DoublePoint(x, y));
			
		}
		
		Path path = new Path(P);
		
		path.calculate();
		
		try {
			path.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		for (int i = 0; i < path.Xconstants.size(); i++) {
			S = "";
			S += "(";
			for(int k = 0; k < 4; k++) {
				S += E.Clean(path.Xconstants.get(i)[k]) + "t^" + E.Clean((3-k));
				
				if (k != 3) {					
					S += " + ";
				}
				
			}
			
			S += ", ";
			
			for(int k = 0; k < 4; k++) {
				S += E.Clean(path.Yconstants.get(i)[k]) + "t^" + E.Clean((3-k));
				
				if (k != 3) {					
					S += " + ";
				}
				
			}
			
			S += ")";
			
			System.out.println(S);
			
		}
		
		/*
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
