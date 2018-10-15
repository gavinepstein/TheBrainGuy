/*
/ TTTTTTT  H    H  EEEEEE         BBBB     RRRR         A      IIIII  N    N         GGGGG   U     U   Y   Y 
/    T     H    H  E              B   B    R   R       A A       I    NN   N        G        U     U    Y Y
/    T     HHHHHH  EEEEE          BBBB     RRRR       AAAAA      I    N N  N        G  GGG   U     U     Y
/    T     H    H  E              B   B    R   R     A     A     I    N  N N        G    G   U     U     Y
/    T     H    H  EEEEEE         BBBB     R    R   A       A  IIIII  N   NN         GGGG     UUUUU      Y
*/
//central thing. Handles all the stuff. 
public class TheBrainGuy(){
    public enum Mode { 
	DRAW, KEYBOARD, PLAY
    }
    //DRAW MODE VARS:
    private Instrument currentInstrument;
    
    //DRAW MODE FUNCTIONS
    //adjust instruments. Called by GUI, call Instrument. 
    public void adjustPitch(float pitch);
    public void adjustPitch(float volume);
    public void adjustPitch(float reverb);
    public void adjustPitch(float delay);

    //adjust drawing tool. Called by GUI, calls 
    public void adjustColor( /*TODO: args */);
    /*TODO: other adjusts*/
    
    //From Gui to Instrument.drawing, 
    public void Draw(Color color, float x, float y);
    

    //getters for GUI
    public float getPitch();
    /*TODO the rest of these*/
    //gets the drawing from the current instrument;
    public Drawing getDrawing();
    

    //KEYBOARD MODE VARS 

    //KEYBOARD MODE FUNCTIONS

    //PLAY MODE VARS
    boolean isRecording;

    //PLAY MODE FUNCTIONS

    //plays a note, called by GUI KeyPress, calls Fractal and Sound
    public void Note (int keycode);
}