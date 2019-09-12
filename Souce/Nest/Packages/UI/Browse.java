package UI;

import java.io.File;

import javax.swing.JFileChooser;

public class Browse {	
	
	public static File Browse(String location) {
		JFileChooser fc = new JFileChooser(new File(location));
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();			
		}
		
		return null;
	}
	
	public static File Browse() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();			
		}
		
		return null;
	}
}
