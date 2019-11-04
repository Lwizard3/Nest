package Main;
import javax.imageio.ImageIO;

import javax.swing.*;

import org.opencv.core.Core;

import Pathfinding.NewMap;
import Pathfinding.Path;
import Robot.Robot;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import UI.*;
import Utility.*;
import Utility.Error;
import Utility.ErrorType;
import Utility.Math.*;
import Utility.Math.DoublePoint;
//import sun.security.util.IOUtils;


public class Nest {
	
	//Welcome to the Nest class!  This is the main class that drives the code
	//You will notice variables declared around my code that appear similar,
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
		
		try {
			NestIcon = new ImageIcon(ImageIO.read(getClass().getResource("/NestLogo.png")));
		} catch (IOException e) {
			new Error("Cannot load nest logo: " + e.getMessage(), ErrorType.NonFatal);			
		}		
		
		//System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	
		
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
		
		/*
		NewMap M = new NewMap(this, new PathfindingWindow(this));
		
		M.Image = new File("C:\\Users\\lukas\\Pictures\\Map1.png");
		M.Name = "Test";
		M.Create();
		*/
		
			
	}
	
	

}
