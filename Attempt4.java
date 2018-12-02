import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.stream.Stream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Keymap;

import processing.core.PApplet;


public class Attempt4 extends JFrame implements ChangeListener{




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean clearNow = false;
	private static boolean  isplaying = false;
	// set up buttons for the top of the screen
	JButton exportButton = new JButton("Export");
	JButton saveButton = new JButton("Save"); 
	JButton clearButton = new JButton("Clear");
	JButton goToKeyboardButton = new JButton("Go to Keyboard");
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	JButton loadButton = new JButton ("Load Image");
	//shape buttons
	JButton squareToggle = new JButton("Square"); 
	JButton lineToggle = new JButton("Line"); 
	//number of seconds in the playback
	private static int timelength = 5;
	//handles all the audio

	SoundModule audioPlayer = new SoundModule();
	// set up title text at the top of the screen
	JLabel screenTop = new JLabel("Create Instrument Mode");

	// set up title text for the lower areas
	JLabel drawingToolsLabel = new JLabel("Drawing Tools");
	JLabel adjusterLabel = new JLabel("Adjuster Tools");
	

	//panelz
	JPanel mainPanel = new JPanel();
	// create top panel for box 
	JPanel topPanel = new JPanel();
	// create the center panel to hold the image 
	JPanel centerPanel = new JPanel();
	// create the bottom panel to hold the box 
	JPanel bottomPanel = new JPanel(); 

	JPanel bottomLeft = new JPanel();
	JPanel bottomCenter = new JPanel();
	

	

	// create dimensions for reference
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


	public Attempt4() {
		// set up the main window size, location, close type, title
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("AV: Instrument Mode");
		//Button stuff
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveImage();
			}
		});
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportSound();
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});
		squareToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeSquare();
			}
		});
		lineToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeLine();
			}
		});

		
		// sliders baybeeeee
		 		// first slider: Drawing tool size adjuster 
		 		final int SIZE_MIN = 1;
		 		final int SIZE_MAX = 100;
		 		
		 		JSlider drawSize = new JSlider(JSlider.HORIZONTAL,
		 	           SIZE_MIN, SIZE_MAX, 15);
		 		drawSize.addChangeListener(this);
		 		
		 		drawSize.setMajorTickSpacing(24);
		 		drawSize.setMinorTickSpacing(14);
		 		drawSize.setPaintTicks(true);
		 		drawSize.setPaintLabels(true);
		 		
		 		// second slider: Time adjuster
		 		final int Time_MIN = 1;
		 		final int Time_MAX = 60;
		 		
		 		JSlider TimeSlider = new JSlider(JSlider.HORIZONTAL,
		 	           Time_MIN, Time_MAX, 5);
		 		TimeSlider.addChangeListener( new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						timelength = ((JSlider) e.getSource()).getValue();
					
					}
		 			
		 			
		 		}

		 		);
		 		
		 		TimeSlider.setMajorTickSpacing(24);
		 		TimeSlider.setMinorTickSpacing(14);
		 		TimeSlider.setPaintTicks(true);
		 		TimeSlider.setPaintLabels(true);
		 		
		 		// third slider: volume adjuster
		 		final int VOL_MIN = 1;
		 		final int VOL_MAX = 100;
		 		
		 		JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL,
		 	           VOL_MIN, VOL_MAX, 50);
		 		volumeSlider.addChangeListener(this);
		 		
		 		volumeSlider.setMajorTickSpacing(24);
		 		volumeSlider.setMinorTickSpacing(14);
		 		volumeSlider.setPaintTicks(true);
		 		volumeSlider.setPaintLabels(true);
		
		
		
		/** 
		 * Create three body panels: Main back panel, top panel for labels, mid label for 
		 *  drawing screen, bottom label for adjustment screens 
		 */

		// create the first body panel
		mainPanel.setLayout(new BorderLayout());
		// create top panel for box 
		topPanel.setBackground(new Color(230,230,250));

		// temporarily set bottom panel color 
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBackground(new Color (184, 168, 221));


		/** 
		 * Create three bottom panels that have matte filled borders. 
		 */

		// create bottom left panel and set an etched border
		bottomLeft.setBackground(new Color(230,230,250));
		bottomLeft.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));


		// create bottom center panel and set an etched border
		bottomCenter.setBackground(new Color(230,230,250));
		bottomCenter.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));

		

		/** LTButtons (top of screen button options)  
		 * Section includes: Creating the box to hold the top buttons and labels  
		 */

		// create box to hold middle top descriptor
		Box LTButtons = Box.createHorizontalBox();
		// box that holds the labels for the drawing screen area
		Box box2 = Box.createHorizontalBox();

		/** Left-hand top buttons (save, clear)
		 * Section includes: Creating spacing between left-hand top buttons; adding to the box  
		 */

		// add save button to the top box
		LTButtons.add(Box.createHorizontalGlue());
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(saveButton);
		LTButtons.add(Box.createHorizontalStrut(4));

		// add clear button to the top box
		LTButtons.add(clearButton);
		LTButtons.add(Box.createHorizontalStrut(280));


		/** Create Instrument Mode  
		 * Section includes: Adding the instrument mode label to the top bar's box 
		 */


		// add screen top label "Create Instrument Mode"
		LTButtons.add(screenTop);
		LTButtons.add(Box.createHorizontalStrut(100));

		/** Right-hand top buttons (play, pause, go to keyboard)
		 * Section includes: Creating spacing between the right-hand top buttons
		 */




		LTButtons.add(playButton);
		LTButtons.add(Box.createHorizontalStrut(4));


		LTButtons.add(loadButton);
		LTButtons.add(Box.createHorizontalStrut(4));

		LTButtons.add(Box.createHorizontalGlue());		
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(exportButton);



		/** Bottom panel label bar  
		 * Section includes: Adding drawing tools, adjuster tools, and instrument select labels to the purple mid-bar
		 */

		// add all of the bottom labels to box2 and place box2 at the north of bottomPanel
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));
		box2.add(drawingToolsLabel);
		box2.add(Box.createHorizontalStrut(300));
		box2.add(adjusterLabel);
		box2.add(Box.createHorizontalGlue());
		
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));



		/** LTButtons (top of screen button options)  
		 * Section includes: Adding the existing LTButtons box, which contains the buttons themselves, to the top 
		 */

		// add buttons to the very top of the screen 
		topPanel.add(LTButtons);

		/** Color Chooser 
		 * Section includes: Adding the color chooser in to the bottom left of the screen 
		 */

		//add color chooser to bottom panel 
		ColorChooser cc = new ColorChooser();
		bottomLeft.add(cc);

		/** TODO: Adjuster buttons (TEMPORARY PLACEMENT IN THE WRONG SECTION, UNTIL I'VE FIGURED OUT HOW TO ADD THEM TO THE 
		 * DRAWING TOOLS BOX THAT ALREADY CONTAINS THE COLOR CHOOSER) 
		 * Section includes: Adding adjuster buttons from separate class
		 */
		
		bottomCenter.add(squareToggle);
		
		bottomCenter.add(lineToggle);
		//sliders
		JLabel sizeSliderLabel = new JLabel("Pen Size"); 
		JLabel timeSliderLabel = new JLabel("Playback Time"); 
		bottomCenter.add(sizeSliderLabel);
		bottomCenter.add(drawSize);
		bottomCenter.add(timeSliderLabel);
		bottomCenter.add(TimeSlider);
		
		
		/** Processing display 
		 * Section includes: Adding processing display from VisualStuff to the center panel for use
		 */

		//add processing display

		JPanel display = VisualStuff.getDrawing(200, 200);
		centerPanel.add(display);

		//Play button
		playButton.setMultiClickThreshhold(5400);

		playButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				JButton source= (JButton)e.getSource();
				if (source.getModel().isPressed() &&!isplaying){
					Thread worker = new Thread() {
				          public void run() {
				            // Something that takes a long time . . . in real life,
				            // this
				            // might be a DB query, remote method invocation, etc.
				            runSound();

				            // Report the result using invokeLater().
				            SwingUtilities.invokeLater(new Runnable() {
					              public void run() {
					                playButton.setText("Play");
					                playButton.setEnabled(true);
					              }
							});
				              
				            }
				          
				          };
				          worker.start();
				
				} 
			}


		});


		/** Bottom panel specifics & main panel additions 
		 * Section includes: Adding the box of labels, the east/west/center panels on the bottom panel that contain 
		 * adjusters and selectors, and adding the top, center, and bottom panels to the main panels 
		 */


		// add in all of the bottom panel specifics: box with labels and three panels
		bottomPanel.add(box2, BorderLayout.NORTH);
		
		bottomPanel.add(bottomLeft, BorderLayout.WEST);
		bottomPanel.add(bottomCenter, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);

		//topButtons.add(topButtonsNest, BorderLayout.NORTH);
		this.setVisible(true); 
	}

	protected void makeLine() {
		VisualStuff.isSquare = false;
		
	}

	protected void makeSquare() {
		VisualStuff.isSquare = true;
		
	}

	private void clear() {
		VisualStuff.clearNow = true;
	}

	private void exportSound(){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".wav", "WAVE");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (!file.getName().endsWith(".wav" )){
				file =new File(file.getAbsolutePath()+".wav");
			}
			audioPlayer.saveFile(file);
			;
		}

	}
	private void saveImage(){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filterJPG = new FileNameExtensionFilter("JPEG", "jpg");
		FileNameExtensionFilter filterPNG= new FileNameExtensionFilter("PNG", "png");
		
		fileChooser.addChoosableFileFilter(filterJPG);
		fileChooser.addChoosableFileFilter(filterPNG);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			if (fileChooser.getTypeDescription(file).equals(filterPNG)){
				if (!file.getName().endsWith(".png" )){
					file =new File(file.getAbsolutePath()+".png");
				}
				
				try {
					ImageIO.write(VisualStuff.img, "png", file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else{
				if (!file.getName().endsWith(".jpg")){
					file =new File(file.getAbsolutePath()+".jpg");
				}
				

				try {
					ImageIO.write(VisualStuff.img, "jpg", file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
	}
	private void loadImage(){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filterImage = new FileNameExtensionFilter("Images", "jpg", "bmp", "tif", "gif", "png");
		
		fileChooser.addChoosableFileFilter(filterImage);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			
		VisualStuff.loadimage(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	
	public void stateChanged(ChangeEvent e) {
 	    JSlider drawSource = (JSlider)e.getSource();
 	    int newSize = (int)drawSource.getValue();
 	    if (!drawSource.getValueIsAdjusting()) {
 	       VisualStuff.sizeValue = newSize; 
 	    }
 	}
	

	public static void main(String[] args) {
		new Attempt4();
	}
	
	public  void runSound(){
		
		if (!isplaying){
			isplaying = true;
			
			
			SwingUtilities.invokeLater(new Runnable() {
	              public void run() {
	                playButton.setText("Analyzing");
	                playButton.setEnabled(false);
	              }
			});

			audioPlayer.interpretImage(VisualStuff.img, timelength);
			
			SwingUtilities.invokeLater(new Runnable() {
	              public void run() {
	                playButton.setText("Playing");
	                playButton.setEnabled(false);
	              }
			});
			
			audioPlayer.playSound();
			playButton.setText("Play");
			
			//isplaying=false;
		}
		
		
	}
		
	
}
