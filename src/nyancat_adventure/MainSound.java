package nyancat_adventure;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MainSound {
	private float volume;
	AudioClip clip;
	Clip c;
	FloatControl gainControl;
	boolean Loop;
	MainSound(String file, boolean Loop){
		try {
			this.Loop = Loop;
			File music = new File(file);
			clip = Applet.newAudioClip(music.toURL());
			c = AudioSystem.getClip();
			volume = 20;
			if(Loop) clip.loop();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void play_MainSound() {
		clip.play();
		
	}
	public void stop_MainSound() {
		clip.stop();
	}
	public void volume_up() {
		volume++;
	}
	public void volumd_down() {
		if(volume>0) {
			volume--;
		}
	}
}
