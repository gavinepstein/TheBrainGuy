 
//central thing. Handles all the stuff.                                                                                                      
public class TheBrainGuy(){
    public enum Mode {
        DRAW, KEYBOARD, PLAY
	    }
    public enum Shape{
	CIRCLE, BRUSH, SQUARE, STAR
	    }
    //GENERAL 
    public Mode currentmode;
    public void changeMode(Mode mode);
 
    //DRAW MODE VARS:                                                                                                                        
    private Instrument currentInstrument;
 
    //DRAW MODE FUNCTIONS                                                                                                                    
    //adjust instruments. Called by GUI, call Instrument.                                                                                    
    public void adjustPitch(float pitch);
    public void adjustVolume(float volume);
    public void adjustReverb(float reverb);
    public void adjustDelay(float delay);
    //adjust drawing tool. Called by GUI, calls                                                                                              
    public void adjustColor( int r, int b, int g);
    public void selectShape(Shape s);
    public void selectSize(float size);
 
    //From Gui to Instrument.drawing,                                                                                                        
    public void Draw( int r, int b, int g, float x, float y);
 
 
    //getters for GUI                                                                                                                        
    public float getPitch();
    public float getVolume();
    public float getReverb();  
    public float getDelay();
    
    //gets the drawing from the current instrument;                                                                                          
    public PImage getDrawing();
 
 
    //KEYBOARD MODE VARS       
                                                                                                                 
 
    //KEYBOARD MODE FUNCTIONS                                                                                                                
    //need Instrument, interact with GUI
    public void assignKey(Instrument instrument, char key); 
    //to do this we should just make the file an Instrument, and then pass into other method
    public void assignFileToKey(typeTBD file, char key); 
    public Instrument convertFileToInstrument(typeTBD file);
    public void playKey(char key);
    public void overrideKey(Instrument newInstrument, char key);
 
    //load keyboard functions
    public void loadKeyboard(String name); //will call assign key functions
    public void saveKeyboard(void); //it probably will be void bc the key bindings will be saved to a Java InputMap
 
    //PLAY MODE VARS                                                                                                                         
    boolean isRecording;
    //PLAY MODE FUNCTIONS                                                                                                                                                                           
    
}
