package guiPanels;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import audio.AudioManager;

public class Test2 {
	
	static long count = 0L;
	static ArrayList<Clip> clips = new ArrayList<>();

	public static void main(String[] args) {
		while(true){
			count ++;
			System.out.println(count);
		    try {
		    	Clip clip1 = AudioSystem.getClip();
				clip1.open(loadSound());
		    	clips.add(clip1);
				
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}

	}
	
	public static AudioInputStream loadSound(){
		
		AudioInputStream stream = null;
		
		try {
			stream = AudioSystem.getAudioInputStream(AudioManager.class.getClass().getResource("/bigboom.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
		
	}

}