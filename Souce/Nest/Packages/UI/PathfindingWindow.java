package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Main.Nest;
import Pathfinding.*;
import Utility.Error.ErrorType;
import Utility.FileManager;
import Utility.Math.DoublePoint;
import Utility.DragListener;
import Utility.Error;
import UI.Browse;



public class PathfindingWindow extends JFrame implements ActionListener {
	
	//This class describes the pathfinding window, and also handles some of the logic too

	private static final long serialVersionUID = -8844945104786086385L;

	Nest Nest;
	
	ImageIcon NestIcon;
	
	JLayeredPane layeredPane;
	JMenuBar menuBar;
	JMenu fileMenu, waypointMenu, colorMenu, taskMenu;
	JMenuItem newMap, openMap, saveMap, exit;
	JMenuItem newNavPoint;
	
	JLabel ImgHolder;
	
	public Field field;
	
	PathfindingWindow PW;
	
	FieldCanvas FC;
		
	public PathfindingWindow(Nest nest) {

		Nest = nest;	
		PW = this;
		
		NestIcon = Nest.NestIcon;
		if (NestIcon != null) {
			setIconImage(NestIcon.getImage());
		}
		
		setLayout(new FlowLayout());
		//GridBagConstraints c = new GridBagConstraints();		
		setTitle("Nest");		
		setSize(1000, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
    
		menuBar = new JMenuBar();		
		
		fileMenu = new JMenu("File");
		waypointMenu = new JMenu("Waypoints");
		colorMenu = new JMenu("Colors");
		
		fileMenu.setToolTipText("File menu");
		waypointMenu.setToolTipText("Create and manage waypoints");
		colorMenu.setToolTipText("Manage colors of the map");
						
		newMap = new JMenuItem("New");
		openMap = new JMenuItem("Open");
		saveMap = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		
		newMap.addActionListener(this);
		openMap.addActionListener(this);
		saveMap.addActionListener(this);
		exit.addActionListener(this);
		
		newMap.setToolTipText("Create a new map");
		openMap.setToolTipText("Open a created map");
		saveMap.setToolTipText("Save current map");
		exit.setToolTipText("Return to Main Menu");
		
		
		fileMenu.add(newMap);
		fileMenu.add(openMap);
		fileMenu.add(saveMap);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		menuBar.add(fileMenu);
		menuBar.add(waypointMenu);
		menuBar.add(colorMenu);
		
		setJMenuBar(menuBar);
		ImgHolder = new JLabel();
		//add(ImgHolder);

		ArrayList<DoublePoint> P = new ArrayList<DoublePoint>();	
				
		FC = new FieldCanvas(NestIcon.getImage());
		FC.setSize(1000, 700);
		
		
		this.add(FC);
		setVisible(true);	
		
		Random R = new Random();
		
		for (int k = 0; k < 100; k++) {
		
		int x;
		int y;
		
		P = new ArrayList<DoublePoint>();	
		
		for (int i = 0; i < 5; i++) {
			
			x = R.nextInt(800) + 100;
			y = R.nextInt(500) + 100;
			
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
		
		FC.paths.add(path);
		
		System.out.println("Done");	

		
		}
		
		FC.repaint();

		

	}	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == newMap) {
			new NewMap(Nest, this);				

		}
		
		if (e.getSource() == openMap) {
			File temp = Browse.Browse("Data/Fields");
			if (temp != null) {
				Open(temp);
			} 
		}

		if (e.getSource() == saveMap) {
			Save();
		}
		
		if(e.getSource() == exit) {
			Nest.changeWindow(Window.MainMenu);
			this.dispose();
		}
		
		
	}
	
	//This method opens a field file of the .fld type, then draws the field image onto the 
	//canvas
	
	public void Open(File file) {
		
	
		field = FileManager.load(file);
		
		if (field == null) {
			new Error("File selected not valid, please select .fld file", ErrorType.NonFatal);
			return;
					
		}
				
		PW.remove(ImgHolder);
		ImgHolder = new JLabel(new ImageIcon(field.Image()));	
		PW.add(ImgHolder);
		FC.Background = field.Image();
		FC.repaint();
		PW.setVisible(false);
		PW.setVisible(true);
		
	}
	
	public void Open(String file) {
		Open(new File(file));
	}
	
	void Save() {
		FileManager.save(field, "Data/Fields/" + field.Name + ".fld");
	}
	
}
