
import java.io.File;
import java.time.Clock;
import java.util.Random;
import java.io.ByteArrayInputStream;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class SoundModule {
	private Sound currentsound;
	private static final int sliceWidth = 5;
	private static final int fftsize = 10;
	private static final int pixelsamplesize = 2;



	//sin table (for fast synthesis)
	static float[]             sintable;
	static float               sinstep;
	static float               sininvStep;
	static int                  sinsize;
	private final static float tau   = (float)Math.PI * 2;
	static
	{
		sinsize = 10000;
		sintable = new float[sinsize];
		sinstep = tau / sinsize;
		sininvStep = 1.0f / sinstep;
		for (int i = 0; i < sinsize; ++i)
		{
			sintable[i] = (float) Math.sin(sinstep * i);
		}
	}


	private float sin(float a){
		float t = a%tau ;
		int indexA = (int) (t *sininvStep);
		int indexB = indexA + 1;
		if (indexB >= sinsize) return sintable[sinsize-1];
		float alpha = t *sininvStep - indexA;
		//linear interp.
		return (alpha) * sintable[indexA] + (1-alpha) *sintable[indexB];
	}


	//Most of this code taken from https://introcs.cs.princeton.edu/java/15inout/Player.java 
	private class Sound{
		private AudioFormat format;
		private int frames;         // total number of frames
		private int frameSize;      // bytes per frame (1 for 8-bit, 2 for 16-bit)
		private int bytes;          // total number of bytes
		private float frameRate;    // frames per second (44100 for CD audio)
		private double seconds;     // duration in seconds
		private byte[] data;

		// create new object from wav file
		public Sound(String filename) {
			File file = new File(filename);
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				AudioFileFormat  aff = AudioSystem.getAudioFileFormat(file);

				// get info
				format    = ais.getFormat();
				frames    = aff.getFrameLength();
				frameSize = format.getFrameSize();
				frameRate = format.getFrameRate();
				bytes     = frames * frameSize;
				seconds   = frames / frameRate;

				// read data
				data = new byte[bytes];
				ais.read(data);
			}
			catch (Exception e)  {
				System.out.println("File not Found");

			}
		}

		// create new object from byte array
		public Sound(byte[] data) {
			this.data = data.clone();
			// 44100Hz, 8 bit, 1 channel, PCM signed, little endian
			format = new AudioFormat(44100, 8, 1, true, false);
			frames    = data.length;
			frameSize = 1;   // 8-bit
			frameRate = 44100;
			bytes     = data.length;
			seconds   = frames / frameRate;
		}


		// play the sound on the sound card
		public void play() {
			SourceDataLine line = null;
			DataLine.Info info  = new DataLine.Info(SourceDataLine.class, format);
			try {
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(format);
			}
			catch (Exception e) {
				e.printStackTrace();
				return;
			}
			line.start();
			line.write(data, 0, bytes);
			line.drain();
			line.close();
		}

		// save to a wav file
		public void save(File file) {

			AudioFileFormat.Type type = AudioFileFormat.Type.WAVE;
			try {
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
				BufferedInputStream  bis  = new BufferedInputStream(bais);
				AudioInputStream ais = new AudioInputStream(bis, format, (long) frames);
				AudioSystem.write(ais, type, file);
			}
			catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}


		// return duration
		public double duration() {
			return seconds;
		}

		// return string representation
		public String toString() {
			return "" + format;
		}

	}




	public void interpretImage(BufferedImage img, float timelength){
		long starttime= System.currentTimeMillis();
		int width = img.getWidth();
		int height = img.getHeight();
		float[] samples = new float[(int) (timelength * 44100) ];
		float[] newsamples = new float[(int) (timelength * 44100) ];
		newsamples[0]=0;
		for(int i = 0; i<samples.length; i++){
			samples[i]=0;
		}
		float[] fft = new float[fftsize];
		int samplesPerPixel = samples.length / width ;
		Random rand = new Random();
		for (int s=0; s<width - sliceWidth; s+=pixelsamplesize){
			//each slice
			//loop over colors and add them up
			for (int y = 0; y<height; y+=pixelsamplesize){
				int red =0;
				int blue = 0;
				int green = 0;
				for (int x = s; x<s+sliceWidth; x++){
					Color RGB = new Color(img.getRGB(x, y));

					red+= RGB.getRed();
					green+=RGB.getGreen();
					blue+= RGB.getBlue();
				}
				//average the colors
				red = red/sliceWidth;
				blue = blue/sliceWidth;
				green = green/sliceWidth;
				//get hue sat and value
				float[] hsv = new float[3];
				Color.RGBtoHSB((int)red, (int)green, (int)blue, hsv);
				//make red actually do something
				if (hsv[0]==0) hsv[0] = .001f;
				
				//frequency
				float basefreq = (12f*(float)(height-y))/height;

				
				//basefreq+= (12*hsv[0]);
				basefreq -= 10;
				//overtones
				fft[0] = 1/tau* 110f *(float) Math.pow(2f, (float)((int)basefreq)/12f);
				int fi  = 0;
				for (fi =1; fi<(int)(fft.length); fi++){
					fft[fi] = fft[fi-1] * hsv[0];

				}
				/*fft[fi-1] = hsv[0]*fft[0];
				for (;fi< fft.length;fi++ ){
					fft[fi] = fft[fi-1] /hsv[0];
					
				}*/
				
				//	if(hsv[2] > 0)System.out.printf("%f, %f, %f\n",fft[0], hsv[1], hsv[2]);
				//amplitude
				float amp = hsv[2];
				float noiselevel = 1 - hsv[1];
				if (hsv[2]> 0.01){
					for (int j = 1; j< fftsize; j++){
						amp = amp*.7f;
						float LPFalpha = tau*fft[j]/(tau*fft[j]+1); 
						
						for (int i = Math.max(1,s*samplesPerPixel) ; i< Math.min((s+pixelsamplesize)*samplesPerPixel , samples.length) ;i++){
							
							
							//LFO
							newsamples[i] = LPFalpha*amp*hsv[1]*sin(fft[j] *i)+ (1-LPFalpha)*newsamples[i-1]  ;// *rand.nextFloat();


						}
						for (int i = s*samplesPerPixel ; i< Math.min((s+pixelsamplesize)*samplesPerPixel , samples.length) ;i++){
							samples[i]+=newsamples[i];

						}

					}
					
					//noise
					for (int i = s*samplesPerPixel ; i< Math.min((s+pixelsamplesize)*samplesPerPixel , samples.length) ;i++){
						//samples[i]+=(rand.nextFloat()*2-1)*(1-hsv[1]);
					
					}
					
				}
				

			}

		}
		float maxamp  = Math.abs(samples[0]);
		for (int i  = 0; i< samples.length; i++){
			maxamp = Math.max(Math.abs(samples[i]) ,maxamp  );    			
		}
		float ampmult  = 30f / maxamp;
		byte[] bytes = new byte[samples.length];
		for (int i  = 0; i< samples.length; i++){
			bytes[i] = (byte) (samples[i] * ampmult);
		}

		this.currentsound = new Sound(bytes);
		System.out.println(.001 *  (System.currentTimeMillis()-starttime ));

	}
	public void playSound(){

		if( currentsound!=null){
			currentsound.play();
		}
		System.out.println("played");

	}
	public void saveFile(File file){
		currentsound.save(file);
	}

}

