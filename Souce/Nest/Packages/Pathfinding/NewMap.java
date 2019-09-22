package Pathfinding;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import Main.Nest;
import UI.PathfindingWindow;
import Utility.Error.ErrorType;
import Utility.FileManager;
import Utility.Error;
import UI.Browse;

public class NewMap extends JFrame implements ActionListener {
	
	//This class is the field creation wizard displayed in Pathfinding > file > create

	private static final long serialVersionUID = -5977683333941870418L;

	ImageIcon NestIcon;
	
	Nest Nest;
	PathfindingWindow PW;
	
	JLabel Header;
	JLabel NameLabel;
	JTextField NameInput;
	JLabel ImageLabel;
	JTextField ImageInput;
	JButton BrowseButton;
	JButton Create;
	JButton Cancel;
	
	final JFileChooser fc = new JFileChooser();
	
	public File Image;
	
	public Image Map;
	
	
	public NewMap(Nest nest, PathfindingWindow pathfindingWindow) {
		PW = pathfindingWindow;
		Nest = nest;		
		
		NestIcon = Nest.NestIcon;
		if (NestIcon != null) {
			setIconImage(NestIcon.getImage());
		}
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setTitle("Nest");
		setSize(500, 250);
		setLocationRelativeTo(null);
		
		//This block of code is the fancy UI stuff to create the layout	
		
		Header = new JLabel("Field Creation Wizard", SwingConstants.CENTER);
		Header.setFont(new Font("Arial", Font.PLAIN, 30));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		add(Header, c);
		
		NameLabel = new JLabel("Name:", SwingConstants.LEFT);
		NameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		//c.weightx = 0.1;
		add(NameLabel, c);
		
		NameInput = new JTextField("", SwingConstants.LEFT);
		NameInput.setFont(new Font("Arial", Font.PLAIN, 12));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 0;
		//c.weightx = 0.9;
		c.insets = new Insets(0, 10, 0, 0);
		add(NameInput, c);
		
		ImageLabel = new JLabel("Field image:", SwingConstants.LEFT);
		ImageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		//c.weightx = 0.1;
		c.insets = new Insets(0, 0, 0, 0);
		add(ImageLabel, c);
		
		ImageInput = new JTextField("", SwingConstants.LEFT);
		ImageInput.setFont(new Font("Arial", Font.PLAIN, 12));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 4;
		//c.weightx = 0.8;
		c.insets = new Insets(0, 10, 0, 0);
		add(ImageInput, c);
		
		BrowseButton = new JButton("Browse");
		BrowseButton.setFont(new Font("Arial", Font.PLAIN, 12));
		BrowseButton.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 1;
		//c.weightx = 0.1;
		c.insets = new Insets(0, 0, 0, 0);
		add(BrowseButton, c);
		
		Cancel = new JButton("Cancel");
		Cancel.setFont(new Font("Arial", Font.PLAIN, 12));
		Cancel.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		//c.weightx = 0.5;
		c.insets = new Insets(0, 0, 0, 0);
		add(Cancel, c);
		
		Create = new JButton("Create");
		Create.setFont(new Font("Arial", Font.PLAIN, 12));
		Create.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		//c.weightx = 0.5;
		c.insets = new Insets(0, 0, 0, 0);
		add(Create, c);
	
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//This is the logic behind the wizard
		
		if (e.getSource().equals(BrowseButton)) {
			String temp = Browse.Browse().getAbsolutePath();
			if (temp != null) {
			ImageInput.setText(temp);
			}
		}
		
		//The two if statements check which button is 
		
		if (e.getSource().equals(Cancel)) {
			this.dispose();
		}
		
		if (e.getSource().equals(Create)) {
			
			//This code is more logic checking if the inputs are valid and then calling the
			//Create() method
					
			Image = new File(ImageInput.getText());
			
			if (NameInput.getText().length() > 0 && Image.exists()) {
				if (!new File("Data/Fields/" + NameInput.getText() + ".fld").exists()) {
					Create();
				} else {
					if (new Error().YesNo(NameInput.getText() + " already exists, overwrite?")) {
						Create();
					}
				}
				
			} else {
				if (NameInput.getText().length() < 1) {
					new Error("Please input name", ErrorType.NonFatal);
				}
				
				if (!Image.exists()) {
					new Error("Please specify image", ErrorType.NonFatal);
				}
				
			}
		}
	}

	
	
	void Create() {
		
		//This method reads the specified image, then creates a field and saves it before 
		//having the pathfinding window open it
		
		try {
			Map = ImageIO.read(Image);
		} catch (IOException e) {
			new Error("Cannot create field - cannot read image: " + e.getMessage(), ErrorType.NonFatal);
			return;
		}
		
		Field field = new Field(Map, NameInput.getText());
		
		field.FC = new FieldCanvas();
			
		FileManager.save(field, "Data/Fields/" + NameInput.getText() + ".fld");
				
		File fieldFile = new File("Data/Fields/" + NameInput.getText() + ".fld");
		
		
		PW.Open(fieldFile);
		
		this.dispose();
	}

}
