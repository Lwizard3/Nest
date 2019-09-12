package Utility;
import javax.swing.*;

public class Error extends JOptionPane {

	private static final long serialVersionUID = -7123583803352886038L;


	public enum ErrorType {
		Fatal,
		NonFatal,
		Information;
	}

	
	public Error(String message, ErrorType errorType)  {
				
		switch (errorType) {
		case Fatal:
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			break;
		case NonFatal:
			JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
			break;
		case Information:
			JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.DEFAULT_OPTION);
			break;
		default:
    	    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			break;
		}
	}
	
	public Error() {
		
	}
	
	public static Boolean YesNo(String Message) {
		int dialogResult = JOptionPane.showConfirmDialog(null, Message, "Warning", JOptionPane.YES_NO_OPTION);
		if(dialogResult == 0) {
		  return true;
		} else {
		  return false;
		} 
	}
}
