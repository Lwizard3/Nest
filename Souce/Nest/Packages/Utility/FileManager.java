package Utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import Utility.Error.ErrorType;

public class FileManager {

	public static <T> void save(T object, String path) {
		byte[] stream = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);) {
		    oos.writeObject(object);
		    stream = baos.toByteArray();
		} catch (IOException e) {
		    new Error("Cannot save - Error in serialization: " + e.getMessage(), ErrorType.NonFatal);
		    return;
		}
		
		try { 
			
			new File(path).delete();
			
            OutputStream os = new FileOutputStream(path);   
            
           
            os.write(stream);   
            os.close(); 
        } 
  
        catch (Exception e) { 
        	new Error("Cannot save - Error writting to file: " + e.getMessage(), ErrorType.NonFatal);
        	return;
        } 
	}
	
	public static <T> void save(T object, File path) {
		save(object, path.getPath());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T load(String path) {
		
		byte[] stream = null;
		
		try {
			InputStream is = new FileInputStream(path);
			stream = is.readAllBytes();
			is.close();
		} catch (FileNotFoundException e1) {
			new Error("Cannot load - Error reading file: " + e1.getMessage(), ErrorType.NonFatal);
		} catch (IOException e) {
			new Error("Cannot load - Error reading file: " + e.getMessage(), ErrorType.NonFatal);
		}
		
		T obj = null;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(stream);
                ObjectInputStream ois = new ObjectInputStream(bais);) {
            obj = (T) ois.readObject();
        } catch (IOException e) {
            new Error("Cannot load - Error in Deserialization: " + e.getMessage(), ErrorType.NonFatal);
        } catch (ClassNotFoundException e) {
            new Error("Cannot load - Error in class casting: " + e.getMessage(), ErrorType.NonFatal);
        }
        return obj;
	}
	
	public static <T> T load(File path) {
		return load(path.getPath());
	}
	
}
