package Egg;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Main.Nest;
import Utility.Error;
import Utility.ErrorType;

public class EggGenerator {

	String template;
		
	
	public EggGenerator() {
		
		try {			
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		    try (InputStream is = classLoader.getResourceAsStream("EggTemplate.txt")) {

		        try (InputStreamReader isr = new InputStreamReader(is);
		             BufferedReader reader = new BufferedReader(isr)) {
		            template = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		        }
		    }
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			new Error("Error creating egg template: " + e1.getLocalizedMessage(), ErrorType.Fatal);
		}
		
		//System.out.println(template);
		
		File file = new File("Data/Eggs/Default/Template/EggTemplate.java");
		File dir = new File("Data/Eggs/Default/Template");
		try {	
			if (dir.mkdir()) {
				
			}
			
			if (file.createNewFile()) {		
				new Error("Generating Egg Template", ErrorType.Information);
				PrintWriter out = new PrintWriter(file);
				out.print(template);
				out.close();
			} 
		} catch (IOException e) {
			new Error("Error creating egg template: " + e.getMessage(), ErrorType.Fatal);
		} 
		
		Generate("Test");
	}
	
	public boolean Generate(String name) {
		String data;
		File dir = new File("Data/Eggs/Generated/" + name);
		if (dir.mkdir()) {
			
		}
		File file = new File("Data/Eggs/Generated/" + name + "/Egg.java");
		try {
			if (file.createNewFile()) {
				data = new String(Files.readAllBytes(Paths.get("Data/Eggs/Default/Template/EggTemplate.java")));
				PrintWriter out = new PrintWriter(file);
				out.print(data);
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
