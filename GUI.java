
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;  

public class GUI{
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;
    public static void main(String[] args){
	
	JFrame f = new JFrame();
	
	f.setSize(WIDTH, HEIGHT);
	JPanel InstrumentPanel = new JPanel(new GridBagLayout());
	int totalcolumns = 4;
	int totalrows = 4;
	
	//top panel
	GridBagConstraints c  = new GridBagConstraints();
	c.gridx = 0;
	c.gridy = 0;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridwidth =totalcolumns;
	c.gridheight = totalrows-2;
	c.weightx =1;
	c.weighty =1;
	
	JButton DrawingPane = new JButton();
	//DrawingPane.setPreferredSize(new Dimension(WIDTH, (int)(.75*HEIGHT)));
	DrawingPane.setBorder(BorderFactory.createLineBorder(Color.black));
	DrawingPane.setText("drawing area");
	DrawingPane.setBackground(new Color(100,200,200));
	DrawingPane.setOpaque(true);
	
	InstrumentPanel.add(DrawingPane);
	
	//labels for bottom panels
	c = new GridBagConstraints();
	c.gridx = 0;
	c.gridy = totalrows-1;
	c.weightx = .5;
	c.fill = GridBagConstraints.HORIZONTAL;
	JLabel nextLabel = new JLabel("Drawing Tools");
	nextLabel.setBorder(BorderFactory.createLineBorder(Color.black));
	InstrumentPanel.add( nextLabel, c);
	c.gridx =1;
	c.gridy = totalrows -1;
	nextLabel = new JLabel("Instrument Adjuster");
	nextLabel.setBorder(BorderFactory.createLineBorder(Color.black));
	InstrumentPanel.add( nextLabel, c);
	c.gridx =2;
	c.gridy = totalrows -1;
	nextLabel = new JLabel("Instruments");
	nextLabel.setBorder(BorderFactory.createLineBorder(Color.black));
	InstrumentPanel.add( nextLabel, c);
	
	//testing
	
    
	
	//closing
	f.addWindowListener( new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    } );
	
	
	f.add(InstrumentPanel);
	f.pack();
	f.setVisible(true);
	
    }



}