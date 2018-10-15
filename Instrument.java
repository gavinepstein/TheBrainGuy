protected class Instrument{
    //PImage is processing class
    PImage drawing;
    //getters

    //interacts with processing
    PImage getDrawing();

    //called by brainguy, interacts with Max
    PlayNote (float pitchshift=0);

}