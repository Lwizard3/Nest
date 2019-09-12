package UI;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import Main.Nest;

public class RobotWindow extends JFrame {

	public RobotWindow(Nest Nest) {
		
		if (Nest.Robot != null) {
			YesRobot();
		} else {
			NoRobot();
		}
		
		setLayout(new FlowLayout());
		setTitle("Nest");
		setSize(600, 600);
		setLocationRelativeTo(null);
		
		
	}
	
	public void NoRobot() {
		
	}
	
	public void YesRobot() {
		
	}
	
}
