package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Main.Nest;
import frc.robot.Egg.Utility.Colors;
import frc.robot.Egg.Utility.DragListener;

public class ScheduleWindow extends JFrame implements ActionListener {
	
	//This will be the schedule window, where you can create and edit schedules
	
	ImageIcon NestIcon;
	
	public ScheduleWindow(Nest Nest) {
		
		setTitle("Nest");		
		setSize(1000, 750);
		setLocationRelativeTo(null);
		setVisible(true);
		
		NestIcon = Nest.NestIcon;
		if (NestIcon != null) {
			setIconImage(NestIcon.getImage());
		}
		
		DragListener DL = new DragListener();
		
		JLabel Test = new JLabel("Jeff");
		Test.setBackground(Colors.FalkonBlue.getColor());
		Test.setOpaque(true);
		Test.setSize(300, 200);
		Test.setLocation(0, 0);
		DL.Add(Test);
		add(Test);
		
		JLabel Test2 = new JLabel("Bob");
		Test2.setBackground(Color.GREEN);
		Test2.setOpaque(true);
		Test2.setSize(300, 200);
		Test2.setLocation(0, 0);
		DL.Add(Test2);
		Test2.addMouseMotionListener(DL);
		add(Test2);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
