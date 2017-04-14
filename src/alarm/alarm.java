package alarm;

import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class alarm extends JApplet{
	private ArrayList<String> musicName = new ArrayList<>();
	private String name = "";
	private AudioClip audio;
	private String[] mName;
	private int musicIndex;
	
	public alarm() throws IOException{
		File dir = new File("D:/Important/EXE/MyAlarm/Wav");
		File[] allFile = dir.listFiles();
		
		for(int i = 0;i < allFile.length;i++){
			
			musicName.add("file:" + allFile[i]);
			if(musicName.get(i).substring
					(musicName.get(i).length() - 4,
					musicName.get(i).length()).equals(".wav")){
			}
			else{
				musicName.remove(i);
			}
		}
		
		mName = new String[musicName.size()];
		
		for(int i = 0;i < musicName.size();i++){
			int index = musicName.get(i).lastIndexOf('\\');
			mName[i] = musicName.get(i).substring
					(index + 1,musicName.get(i).length() - 4);
		}
		
		name = musicName.get(0);
		audio = Applet.newAudioClip(new URL(name));
	}

	public AudioClip getAudio() {
		return audio;
	}

	public void setAudio(AudioClip audio) {
		this.audio = audio;
	}

	public String[] getmName() {
		return mName;
	}

	public void setmName(String[] mName) {
		this.mName = mName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		try {
			audio = Applet.newAudioClip(new URL(musicName.get(musicIndex)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getMusicIndex() {
		return musicIndex;
	}

	public void setMusicIndex(int musicIndex) {
		this.musicIndex = musicIndex;
	}
	
}
