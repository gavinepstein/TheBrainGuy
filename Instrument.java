protected class Instrument{
    //PImage is processing class
    PImage drawing;
    MaxObj /*Todo is this type correct?*/ Patch;
    string name;
    //getters

    Instrument();

    //interacts with processing
    PImage getDrawing();
    
    MaxObj getPatch();
    
    //
    private DrawingToPatch();

    //called by brainguy, interacts with Max; also used by SoundAction class
    public void playNote (float pitchshift=0);

}