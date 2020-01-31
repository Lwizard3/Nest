package Interface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.cameraserver.*;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import frc.robot.Egg.Pathfinding.*;
import frc.robot.Egg.Utility.Error;
import frc.robot.Egg.Utility.ErrorType;

public class Interface {
	
	NetworkTableEntry Schedule;
	NetworkTable table;
	NetworkTableInstance inst;
	
	boolean authenticated = false;
	
	public Interface() {
		Start();		 

	}
	
	public void Start() {
		 inst = NetworkTableInstance.getDefault();
		 table = inst.getTable("Nest");
		 Schedule = table.getEntry("Schedule");
		 inst.startClientTeam(589); 
		 inst.startDSClient();
	}
	
	public void Close() {		
		inst.stopClient();
		inst.stopDSClient();
		inst.stopClient();
	}
	
	public boolean send(Schedule schedule, boolean timeout) {
		if (timeout) {
			if (send(schedule, Timeout.Allow)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (send(schedule, Timeout.Deny)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean send(Schedule schedule, Timeout timeout) {
		
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
		
		byte[] stream = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);) {
		    oos.writeObject(schedule);
		    stream = baos.toByteArray();
		} catch (IOException e) {
		    new Error("Cannot transfer - Error in serialization: " + e.getMessage(), ErrorType.NonFatal);
		    return false;
		}
		
		Schedule.forceSetRaw(stream);		 	
		
		
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
