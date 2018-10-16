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

    //called by brainguy, interacts with Max
    PlayNote (float pitchshift=0);

}