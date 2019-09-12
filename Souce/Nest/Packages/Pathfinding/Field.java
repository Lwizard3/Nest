package Pathfinding;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import Utility.Error;
import Utility.Error.ErrorType;

public class Field implements Serializable {

	private static final long serialVersionUID = -8949961942969196996L;

	public byte[] Map;
	
	public String Name;
	
	public Field(Image map, String name) {
		
		Map = ImageToArray(map);
		 			
		Name = name;
	}
	
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
	
	public Image Image() {
		return ArrayToImage(Map);		
	}
}
