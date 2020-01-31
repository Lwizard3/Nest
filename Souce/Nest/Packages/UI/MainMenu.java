package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import Main.Nest;
import frc.robot.Egg.Utility.*;
import frc.robot.Egg.Utility.Error;

public class MainMenu extends JFrame implements ActionListener {
	
	//This is the main menu.  Not to be mixed up with Nest its self main menu describes
	//The graphical interface

	private static final long serialVersionUID = 1952929057373855832L;

	private static final String SwingConstraints = null;
	
	ImageIcon NestIcon;
	Nest Nest;
	
	Button[] Buttons = { 
		new Button("Pathfinding"), 
		new Button("Simulation"), 
		new Button("Schedule"), 
		new Button("Eggs"),
		new Button("Robot"),
		new Button("Interface"),
		new Button("Settings"),
		new Button("Quit") 
	};
	
	JPanel ButtonHolder;
	
	JLabel LogoImage; 

	public MainMenu(Nest nest) {
		
		setLayout(new GridBagLayout());
		setTitle("Nest");
		setSize(600, 600);
		setLocationRelativeTo(null);
		
		ButtonHolder = new JPanel();
		ButtonHolder.setLayout(new GridLayout(4, 2));
		
		Nest = nest;		
				
		LogoImage = new JLabel();		
		
		NestIcon = Nest.NestIcon;
		
		if (NestIcon != null) {		
			
			BufferedImage img = (BufferedImage)NestIcon.getImage();			
			Image img2 = (Image)img.getScaledInstance(this.getWidth() / 5, this.getHeight() / 5, Image.SCALE_SMOOTH); 
			
			LogoImage = new JLabel(new ImageIcon(img2));

			setIconImage(NestIcon.getImage());
		}
		
		
		
		
		JLabel Title = new JLabel("589 Falkon robotics utility program");
		Title.setVerticalAlignment(SwingConstants.TOP);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Arial", Font.PLAIN, 30));
		Title.setForeground(Colors.FalkonBlue.getColor());
			
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 60;
		add(Title, c);		

		for (int i = 0; i < Buttons.length; i++) {
			Buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
			Buttons[i].addActionListener(this);
			ButtonHolder.add(Buttons[i]);
		}
		
		c.gridy = 1;
		c.gridheight = 3;
		c.ipady = 0;
		add(ButtonHolder, c);
		c.gridy = 4;
		c.gridheight = 1;		
		add(LogoImage, c);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//This is the action listener which checks the series of buttons, and calls the Nest.
	//ChangeWindow for what ever window was selected
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == Buttons[0]) {
			Nest.changeWindow(Window.Pathfinding);
			this.dispose();
		}
		
		if (e.getSource() == Buttons[1]) {
			Nest.changeWindow(Window.Simulation);		
			this.dispose();
		}
		
		if (e.getSource() == Buttons[2]) {
			Nest.changeWindow(Window.Schedule);			
			this.dispose();
		}
		
		if (e.getSource() == Buttons[3]) {
			Nest.changeWindow(Window.Eggs);			
			this.dispose();
		}
		
		if (e.getSource() == Buttons[4]) {
			Nest.changeWindow(Window.Robot);
			this.dispose();
		}
		
		if (e.getSource() == Buttons[5]) {
			Nest.changeWindow(Window.Interface);
			this.dispose();
		}
		
		if (e.getSource() == Buttons[Buttons.length - 2]) {
			Nest.changeWindow(Window.Settings);
			this.dispose();
		}
		
		if(e.getSource() == Buttons[Buttons.length - 1]) {
			if (Error.YesNo("Quit?")) {
				this.dispose();
			}
		}
		
	}

	
	
}
