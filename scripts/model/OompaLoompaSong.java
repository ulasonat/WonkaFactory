package factory.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.lang.Integer;

public class OompaLoompaSong implements Singer {
	
	private int nLines;
	private ArrayList<String> song;

	public OompaLoompaSong(int nLines) {
		this.nLines = nLines;
		song = initializeSong("files/OompaLoompaSong.txt");
	}
	
	@Override
	public String toString() {
		return sing();
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		OompaLoompaSong o2 = (OompaLoompaSong) o;
		
		return nLines == o2.getNLines() && song.equals(o2.getSong());
	}
	
	public int getNLines() {
		return nLines;
	}
	
	public  ArrayList<String> getSong() {
		return song;
	}
	
	public void setNLines(int nLines) {
		this.nLines = nLines;
	}
	
	public void setSong(ArrayList<String> song) {
		this.song = song;
	}

	public String sing() {
		if(song != null) {
			String songAsString = "";
			for(String s : song) {
				songAsString += s + "\n";
			}
			if(!songAsString.isEmpty())
				return songAsString;
		}
		
		return "The number you type should have been between 1 and the number of lines of the song.";
		
	}
	
	private ArrayList<String> initializeSong(String fileName) {
		ArrayList<String> wholeSong = new ArrayList<>();
		File file;
		Scanner fileReader;
		
		try {
			file = new File(fileName);
			fileReader = new Scanner(file);
			
			while(fileReader.hasNext()) {
				wholeSong.add(fileReader.nextLine());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return randomLines(wholeSong);
		}
	}
	
	private ArrayList<String> randomLines(ArrayList<String> wholeSong) {
		if(nLines > wholeSong.size() || nLines <= 0) {
			return null;
		}
		
		Random random = new Random();
		ArrayList<Integer> addedLinesIndexes = new ArrayList<>(); // will store the index of lines that will be added to 'randomLines', to make sure that same line will not be added twice!
		
		ArrayList<String> randomLines = new ArrayList<>(); // the ArrayList object that we'll return.
		
		for(int i=0; i<nLines; i++) {
			int index;
			do {
			index = random.nextInt(wholeSong.size());
			} while(addedLinesIndexes.contains(index));
			randomLines.add(wholeSong.get(index));
		}
		
		return randomLines;
		
	}
	
	
	
	
}