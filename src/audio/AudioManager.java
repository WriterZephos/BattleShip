package audio;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {

	private static ExecutorService backgroundPool = Executors.newFixedThreadPool(1);
	private static Future<?> backgroundStatus;
	private static int loopCounter = 2;
	private static String[] backgroundFiles = {
		"/40_Avalon.wav","/13_Glatisant.wav",
		"/31_Lying_In_Deceit.wav","/43_Return_to_Base.wav"};
	private static String[] files = {
		"/bigboom.wav","/Robot_blip.wav",
		"/battleStations.WAV","/beep1.wav",
		"/button-47.wav","/button-35.wav",
		"/beep-23.wav","/Sonar_pings.wav",
		"/button-21.wav","/SONAR.WAV"};
	private static AudioInputStream currentBackgroundMusic;
	private static boolean backgroundOn = false;
	private static boolean canStart = true;
	private static int[] clipIndex = new int[10];
	
	private static Clip[][] clips = new Clip[10][10];
	
	private static void initializeClips(int sound){
		
		clipIndex[sound] = 0;
		
		for (int i = 0 ; i < 10 ; i++)
			try {
				clips[sound][i] = AudioSystem.getClip();
				clips[sound][i].open(loadSound(sound));
				clips[sound][i].addLineListener(new LineListener(){

					@Override
					public void update(LineEvent event) {
						if(event.getType() == javax.sound.sampled.LineEvent.Type.STOP){
							clips[sound][clipIndex[sound]].setFramePosition(0);
						}
					}});
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
	
	public static AudioInputStream loadSound(int s){
		
		AudioInputStream stream = null;
		
		try {
			stream = AudioSystem.getAudioInputStream(AudioManager.class.getClass().getResource(files[s]));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
		
	}
	
	private static AudioInputStream loadBackground(int s){
		
		AudioInputStream stream = null;
		
		try {
			stream = AudioSystem.getAudioInputStream(AudioManager.class.getClass().getResource(backgroundFiles[s]));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
		
	}
	
	
	public static void setSound(int s){
		if(clips[s] == null){
			initializeClips(s);
		}
		playSound(s);
	}
	
	private static void continueMusic(){
		setBackgroundMusic(loopCounter);
		loopCounter++;
		if(loopCounter > 3) loopCounter = 0;
	}

	public static void playSound(int sound){
		if(clips[sound][0] == null){
			initializeClips(sound);
		}
		clips[sound][clipIndex[sound]].start();
		clipIndex[sound]++;
		if(clipIndex[sound] == 10){
			clipIndex[sound] = 0;
		}
		clips[sound][clipIndex[sound]].drain();
		clips[sound][clipIndex[sound]].flush();
		clips[sound][clipIndex[sound]].setFramePosition(0);
		
	}
	
	
	public static void setBackgroundMusic(int s){
		
		canStart = false;
		
		if (backgroundOn) {
			backgroundOn = false;
		}
		currentBackgroundMusic = loadBackground(s);
		backgroundStatus = backgroundPool.submit(new MusicPlayer());
		canStart = true;
	}
	
	private static void playSound2(AudioInputStream audio) {
		
		backgroundOn = true;
		AudioFormat     audioFormat = audio.getFormat();
		SourceDataLine  line = null;
		DataLine.Info   info = new DataLine.Info(SourceDataLine.class,audioFormat);
		
		try{
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
  
		line.start();
       
		int     nBytesRead = 0;
		byte[]  abData = new byte[128000];
		
		while (nBytesRead != -1 && backgroundOn){
			try{
				nBytesRead = audio.read(abData, 0, abData.length);
			} catch (IOException e){
				e.printStackTrace();
			}
			
			if (nBytesRead == -1) break;
			
			line.write(abData, 0, nBytesRead);

		}
        
		line.drain();
		line.stop();
		line.close();
		line = null;
		backgroundOn = false;
    }
	
	private static class MusicPlayer implements Runnable{

		@Override
		public void run() {
			playSound2(currentBackgroundMusic);	
		}
	}
	
	public static void loopMusic(){
		
		Thread loop = new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					if(backgroundStatus.isDone() && canStart){
						continueMusic();
					}
				}
				
			}});
		
		loop.start();
	}
	public static void reset(){
		loopCounter = 2;
	}
	
	
}
