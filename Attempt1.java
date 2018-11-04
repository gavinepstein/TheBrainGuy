import java.awt.*;

import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
  

public class Attempt1 extends JFrame{
	
	// set up buttons for the top of the screen 
	JButton saveButton = new JButton("Save"); 
	JButton clearButton = new JButton("Clear");
	JButton goToKeyboardButton = new JButton("Go to Keyboard");
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	
	// set up title text at the top of the screen
	JLabel screenTop = new JLabel("Create Instrument Mode");
	
	// set up title text for the lower areas
	JLabel drawingToolsLabel = new JLabel("Drawing Tools");
	JLabel adjusterLabel = new JLabel("Adjuster Tools");
	JLabel instrumentSelectLabel = new JLabel("Instrument Select");
	
	// create default list & scroller for the instrument select scroller
	DefaultListModel instrumentSelectList = new DefaultListModel();
	JScrollPane instrumentScroller;
	JList instrumentsList;
	
	// create dimensions for reference
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args) {
		new Attempt1();
	}
	
	public Attempt1() {
		// set up the main window size, location, close type, title
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("AV: Instrument Mode");
		
		
		/** 
		 * Create three body panels: Main back panel, top panel for labels, mid label for 
		 *  drawing screen, bottom label for adjustment screens 
		 */
		
		// create the first body panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		// create top panel for box 
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(230,230,250));
		// create the center panel to hold the image 
		JPanel centerPanel = new JPanel();
		// temporarily set the center panel as a color so it's visible; this will hold the drawing screen
		centerPanel.setBackground(new Color (0, 0, 0));
		// create the bottom panel to hold the box 
		JPanel bottomPanel = new JPanel(); 
		
		// temporarily set bottom panel color 
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBackground(new Color (184, 168, 221));
		
		
		/** 
		 * Create three bottom panels that have matte filled borders. 
		 */
		
		// create bottom left panel and set an etched border
		JPanel bottomLeft = new JPanel();
		bottomLeft.setBackground(new Color(230,230,250));
		bottomLeft.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));
				
		
		// create bottom center panel and set an etched border
		JPanel bottomCenter = new JPanel();
		bottomCenter.setBackground(new Color(230,230,250));
		bottomCenter.setBorder( new CompoundBorder(
					BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
					BorderFactory.createEtchedBorder()));
					
		// create bottom right panel and set an etched border
		JPanel bottomRight = new JPanel();
		bottomRight.setBackground(new Color(230,230,250));
		bottomRight.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));
		
		
		// create box to hold middle top descriptor
		Box LTButtons = Box.createHorizontalBox();
		// box that holds the labels for the drawing screen area
		Box box2 = Box.createHorizontalBox();

		
		// create border to hold top buttons and label
		JPanel topButtons = new JPanel();
		topButtons.setLayout(new BorderLayout());	
		
		
		// add save button to the top box
		LTButtons.add(Box.createHorizontalGlue());
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(saveButton);
		LTButtons.add(Box.createHorizontalStrut(4));

		
		// add clear button to the top box
		LTButtons.add(clearButton);
		LTButtons.add(Box.createHorizontalStrut(280));
		
		
		// add screen top label "Create Instrument Mode"
		LTButtons.add(screenTop);
		LTButtons.add(Box.createHorizontalStrut(100));

		
		// assign text to the right hand buttons
		LTButtons.add(playButton);
		LTButtons.add(Box.createHorizontalStrut(4));
		
		LTButtons.add(pauseButton);
		LTButtons.add(Box.createHorizontalStrut(4));
		
		LTButtons.add(Box.createHorizontalGlue());		
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(goToKeyboardButton);
		
		// add all of the bottom labels to box2 and place box2 at the north of bottomPanel
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));
		box2.add(drawingToolsLabel);
		box2.add(Box.createHorizontalStrut(150));
		box2.add(adjusterLabel);
		box2.add(Box.createHorizontalGlue());
		box2.add(instrumentSelectLabel);
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));
		
		
		// create instrument scroller panel 
		String[] instruments = {"Instrument 1", "Instrument 2", "Instrument 3", "Instrument 4", "Instrument 5"
				 , "Instrument 6", "Instrument 7"};
		for(String instrument: instruments) {
			instrumentSelectList.addElement(instrument);
		}
		instrumentsList = new JList(instrumentSelectList);
		//instrumentsList = setVisibleRowCount(3);
		instrumentsList.setFixedCellHeight(20);
		instrumentsList.setFixedCellWidth(135);
		instrumentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// stick the list into the scrolling box 
		instrumentScroller = new JScrollPane(instrumentsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// add the scrolling box to the main panel 
		bottomRight.add(instrumentScroller, BorderLayout.EAST);

		topPanel.add(LTButtons);
		// add in all of the bottom panel specifics: box with labels and three panels
		bottomPanel.add(box2, BorderLayout.NORTH);
		bottomPanel.add(bottomRight, BorderLayout.EAST);
		bottomPanel.add(bottomLeft, BorderLayout.WEST);
		bottomPanel.add(bottomCenter, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		
		//topButtons.add(topButtonsNest, BorderLayout.NORTH);
		this.setVisible(true);
	}

}
