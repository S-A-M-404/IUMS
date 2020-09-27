package dev.aumi.IUMS;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class PlaySound extends Thread {
	
	private String file;
	
	public PlaySound(String file) {
		
		this.file = file;
		
	}
	
	public synchronized void playSound() {
		
  		File sound = new File(file);
  		try {
  			
			AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
			AudioFormat format = audio.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audio);
			clip.start();
			Thread.sleep(1000);
			
		} catch (UnsupportedAudioFileException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (LineUnavailableException e) {
			
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
  		
  	}
	
	public void run() {
		
		playSound();
		
	}
		
}
