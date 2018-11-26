 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
import java.util.List;

public class ColorChooser extends JPanel
                              implements ChangeListener {
 
	// changed to static from protected, this may be a mistake
    static JColorChooser tcc;
    
    public ColorChooser() {
        super(new BorderLayout());

        tcc = new JColorChooser(Color.blue);
        tcc.setPreviewPanel(new JPanel());
        tcc.getSelectionModel().addChangeListener(this);
        AbstractColorChooserPanel[] panels = tcc.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (! accp.getDisplayName().equals("HSV")) {
         		tcc.removeChooserPanel(accp);
            }
        }
        add(tcc, BorderLayout.PAGE_END);
    }
 
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooser();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}