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
	NetworkTableEntry comm;
	NetworkTable table;
	
	boolean authenticated = false;
	
	public Interface() {
		Start();		 
		 
		 auth.setValue(0);
		 
		 auth.addListener(event -> {
			 authenticated = false;
				if (auth.getDouble(0) == 2.0) {
					auth.setValue(0);
					authenticated = true;
				}
			}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
	}
	
	public void Start() {
		inst = NetworkTableInstance.getDefault();
		 table = inst.getTable("Nest");
		 Right = table.getEntry("Right");
		 Left = table.getEntry("Left");
		 auth = table.getEntry("auth");
		 comm = table.getEntry("comm");
		 inst.startClientTeam(589); 
		 inst.startDSClient();
	}
	
	public void Close() {
		while (!(auth.getDouble(0) == 0)) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		inst.stopClient();
		inst.stopDSClient();
		inst.stopClient();
	}
	
	public boolean send(double left, double right, boolean timeout) {
		if (timeout) {
			if (send(left, right, true)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (send(left, right, false)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean send(double left, double right, Timeout timeout) {
		
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {			
			}
			
			//System.out.println(auth.getDouble(0));
			
			if (inst.isConnected()) {
				break;
			}
		}
		
		if (!inst.isConnected()) {
			new Error("Tables not connected", ErrorType.Temporary);
			return false;
		}
		
		
		Left.setValue(left);
		Right.setValue(right);
		auth.setValue(1);	
		
		if (timeout == Timeout.Deny) {
		
			while (!(auth.getDouble(0) == 2.0)) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {			
				}
				
				//System.out.println(auth.getDouble(0));
				
				if ((auth.getDouble(0) == 2.0)) {
					auth.forceSetDouble(0);
					authenticated = false;
					return true;
				}
			}
		} else {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {			
				}
				
				//System.out.println(auth.getDouble(0));
				
				if ((auth.getDouble(0) == 2.0)) {
					auth.forceSetDouble(0);
					authenticated = false;
					return true;
				}
			}
			
			System.out.println(auth.getDouble(0));
			
			auth.setValue(0);
			
			new Error("message timed out", ErrorType.Temporary);	
		 	
		}
		
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
