package Interface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Utility.Error;
import Utility.ErrorType;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.cameraserver.*;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Interface {
	
	NetworkTableEntry Right;
	NetworkTableEntry Left;
	NetworkTableEntry auth;	
	NetworkTableInstance inst;
	NetworkTable table;
	
	boolean authenticated = false;
	
	public Interface() {
		 inst = NetworkTableInstance.getDefault();
		 table = inst.getTable("Nest");
		 Right = table.getEntry("Right");
		 Left = table.getEntry("Left");
		 auth = table.getEntry("auth");
		 inst.startClientTeam(589); 
		 inst.startDSClient();
		 
		 
		 auth.setValue(0);
		 
		 auth.addListener(event -> {
			 authenticated = false;
				if (auth.getDouble(0) == 2.0) {
					auth.setValue(0);
					authenticated = true;
				}
			}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
	}
	
	public boolean send(double left, double right) {
		Left.setValue(left);
		Right.setValue(right);
		auth.setValue(1);	
		
		while (!authenticated) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {			
			}
			
			System.out.println(auth.getDouble(0));
			
			if (authenticated) {
				authenticated = false;
				return true;
			}
		}
		
		/*
		
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {			
			}
			
			System.out.println(auth.getDouble(0));
			
			if (authenticated) {
				authenticated = false;
				return true;
			}
		}
		
		System.out.println(auth.getDouble(0));
		
		auth.setValue(0);
		
		//new Error("message timed out", ErrorType.NonFatal);	
		 	
		 */
		
		return false;
		
		
	}
	
	/*
	@SuppressWarnings("deprecation")
	public Image getImage() {
		Mat source = new Mat(320, 240, 1);		
				
		CvSink cvSink = CameraServer.getInstance().getVideo();
		cvSink.grabFrame(source);
		
		MatOfByte mob=new MatOfByte();
	    Imgcodecs.imencode(".jpg", source, mob);
	    byte ba[]=mob.toArray();

	    BufferedImage bi = null;
		try {
			bi = ImageIO.read(new ByteArrayInputStream(ba));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return bi;
		
	}
	*/
}
