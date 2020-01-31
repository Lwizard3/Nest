package Pathfinding;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import UI.PathfindingWindow;
import frc.robot.Egg.Utility.Error;
import frc.robot.Egg.Utility.ErrorType;


public class Field implements Serializable {
	
	//This is the Field class, note how it is Serializable.  This allows the Field class
	//to be saved and loaded.  The Field class stores an image, though for serialization
	//it needs to be converted to a byte array.  Once the code progresses farther it will
	//store waypoint and color data too

	private static final long serialVersionUID = -8949961942969196996L;

	public byte[] Image;
			
	public String Name;
	
	public int Resolution;
	
	public int Width;
	public int Height;	
	
	public Map map;
	
	public ArrayList<Color> BadColors = new ArrayList<Color>();
	
	
	public Field(Image image, String name) {
		
		BadColors.add(Color.black);
		
		Image = ImageToArray(image);
		Width = image.getWidth(null);
		Height = image.getHeight(null);
		
		map = new Map(Width, Height, 10, Image(), BadColors);
		map.Print();
		for (int i = 0; i < 50; i++) {
			map.SmoothMap();
		}
		//System.out.println("-------------");
		map.Print();
		 			
		Name = name;
	}
	
	//These two methods convert images to byte array and visa versa
	
	public static byte[] ImageToArray(Image img) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      try {
			ImageIO.write((BufferedImage) img, "png", bos );
			byte [] data = bos.toByteArray();			      
		    return data;
		} catch (IOException e) {
			new Error("Cannot convert image to byte array: " + e.getMessage(), ErrorType.NonFatal);
		}
	      
	    return null;
	}
	
	public static Image ArrayToImage(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		try {
			BufferedImage img2 = ImageIO.read(bis);
			return img2;
		} catch (IOException e) {
			new Error("Cannot convert byte array to image: " + e.getMessage(), ErrorType.NonFatal);
		}
		
		return null;
	}
	
	//This method allows you to call Field.Image() to get the image as by default we store 
	//the images as byte arrays
	
	public Image Image() {
		return ArrayToImage(Image);		
	}
}
