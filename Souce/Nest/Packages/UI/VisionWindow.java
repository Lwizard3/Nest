package UI;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Main.Nest;
import Pathfinding.Socket;

public class VisionWindow extends Thread {
	
	Nest Nest;
	ImageIcon NestIcon;
	JLabel ImageHolder;
	Socket Socket;
	JFrame Frame;
	
	public VisionWindow(Nest nest, Socket S) {
		
		Frame = new JFrame();

		Nest = nest;	
		Image I;
		Socket = S;
		
		NestIcon = Nest.NestIcon;
		if (NestIcon != null) {
			Frame.setIconImage(NestIcon.getImage());
		}
		
		Frame.setLayout(new FlowLayout());
		//GridBagConstraints c = new GridBagConstraints();		
		Frame.setTitle("Nest");		
		Frame.setSize(320, 240);
		Frame.setLocationRelativeTo(null);
		Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		ImageHolder = new JLabel(NestIcon);
		
		Frame.setVisible(true);
		
		this.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//ImageHolder.setIcon(new ImageIcon(Socket.getVisionOutput()));
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
