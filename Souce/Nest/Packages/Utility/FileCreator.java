package Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utility.Error.ErrorType;

public final class FileCreator {
	
	public static void CreateFiles(List<String> Files, List<String> Directories) {
		List<String> DefaultFiles = Files; 
		List<String> DefaultDirectories = Directories;
		
		List<String> FilesNotFound = new ArrayList<String>();
		
		for (int i = 0; i < DefaultDirectories.size(); i++) {
			File dir = new File(DefaultDirectories.get(i));
			if (!dir.exists()) {			
				dir.mkdir();
				FilesNotFound.add(dir.toString());
			} 
		}
		
		for (int i = 0; i < DefaultFiles.size(); i++) {
			
			File file = new File(DefaultFiles.get(i));
			try {				
				if (file.createNewFile()) {		
					FilesNotFound.add(DefaultFiles.get(i));
				} 
			} catch (IOException e) {
				new Error("Error creating file " + DefaultFiles.get(i) + " ," + e.getMessage(), ErrorType.Fatal);
			} 
		}
		
		if (FilesNotFound.size() > 0) {
			String MissingFiles = "";
			for (int i = 0; i < FilesNotFound.size(); i++) {
				MissingFiles += FilesNotFound.get(i) + "\n";
			}			
			
			new Error("Missing files and/or directories: \n" + MissingFiles + "[Creating]", ErrorType.NonFatal);
		}
	}
	
	public static void CreateFiles(List<String> DataToCreate) {
		
		List<String> FilesNotFound = new ArrayList<String>();
		
		if (DataToCreate.get(0).contains(".")) {
			List<String> DefaultFiles = DataToCreate; 
			
			for (int i = 0; i < DefaultFiles.size(); i++) {
				
				File file = new File(DefaultFiles.get(i));
				try {				
					if (file.createNewFile()) {	
						FilesNotFound.add(DefaultFiles.get(i));
					} 
				} catch (IOException e) {
					new Error("Error creating file " + DefaultFiles.get(i) + ", " + e.getMessage(), ErrorType.Fatal);
				} 
			}
		} else {
			List<String> DefaultDirectories = DataToCreate;
					
			for (int i = 0; i < DefaultDirectories.size(); i++) {
				File dir = new File(DefaultDirectories.get(i));
				if (!dir.exists()) {			
					dir.mkdir();
					FilesNotFound.add(DefaultDirectories.get(i));
				} 
			}
		}
		
		if (FilesNotFound.size() > 0) {
			String MissingFiles = "";
			for (int i = 0; i < FilesNotFound.size(); i++) {
				MissingFiles += FilesNotFound.get(i) + "\n";
			}			
			
			new Error("Missing files and/or directories: \n" + MissingFiles + "[Creating]", ErrorType.NonFatal);
		}
		

	}
	
	public static void CreateFile(String DataToCreate) {
		
		if (DataToCreate.contains(".")) {
			
			File file = new File(DataToCreate);
			
			try {				
				if (file.createNewFile()) {		
				} 
			} catch (IOException e) {
				new Error("Error creating file " + file + " ," + e.getMessage(), ErrorType.Fatal);
			} 			
			
		} else {
			File dir = new File(DataToCreate);
			if (!dir.exists()) {			
				dir.mkdir();
			} 
		}
		

	}

	
}
