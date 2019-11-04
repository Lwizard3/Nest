package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Main.Nest;
import Pathfinding.*;
import Utility.ErrorType;
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
	
	public FieldCanvas FC;
	
	Socket S;
		
	public PathfindingWindow(Nest nest) {

		Nest = nest;	
		PW = this;
		S = new Socket(PW);
		
		NestIcon = Nest.NestIcon;
		if (NestIcon != null) {
			setIconImage(NestIcon.getImage());
		}
		
		//new VisionWindow(Nest, S);
		
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
				
		FC = new FieldCanvas();//NestIcon.getImage());
		FC.setSize(1000, 700);
				
		this.add(FC);
		setVisible(true);	
		
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
		//ImgHolder = new JLabel(new ImageIcon(field.Image()));	
		PW.add(ImgHolder);
		FC.Background = field.Image();
		//PW.setSize(field.Image().getWidth(null), field.Image().getHeight(null));
		PW.setVisible(false);
		PW.setVisible(true);
		
		S.mount(field);		
		
		FC.setSize(field.Image().getWidth(null), field.Image().getHeight(null));
		
		FC.paths.add(S.FindPath(new DoublePoint(50, 50), new DoublePoint(900, 900)));
		S.drive(new DoublePoint(50, 50), new DoublePoint(900, 900));

	}
	
	public void Open(String file) {
		Open(new File(file));
	}
	
	void Save() {
		if (field == null) {
			return;
		}
		
		FileManager.save(field, "Data/Fields/" + field.Name + ".fld");
	}
	
}
