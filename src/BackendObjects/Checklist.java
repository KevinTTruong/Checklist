package BackendObjects;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.ErrorPopUp;

public class Checklist {
	private ArrayList<Item> items = new ArrayList<Item>();
	private String filename = null;
	private String resetTime = "00:00:00";
	
	public Checklist(String filename) {
		this.filename = filename;
		File file = new File(filename);
		if(!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				save();
			} catch (IOException e) {
				displayError("Could not create file.");
			}
		}else {
			load();
		}
	}
	
	public Checklist() {
		
	}
	
	public void save() {
		if(filename==null) return;
		try {
			FileWriter writer = new FileWriter(new File(filename),false);
			
			String header = java.time.LocalDate.now().toString()+" "+resetTime+"\n";
			writer.write(header);
			writer.write(this.toString());
			writer.close();
			
		} catch (IOException e) {
			displayError("Could not save file.");
		}
	}
	
	public void load() {
		try {
			File file = new File(filename);
			Scanner reader = new Scanner(file);
			String[] header = reader.nextLine().split(" ");
			//Check if reset needed
			boolean reset = false;
			resetTime = header[1];
			if(!LocalDate.parse(header[0]).isEqual(LocalDate.now())
			&&LocalTime.parse(resetTime).compareTo(LocalTime.now())<0) {
				reset = true;
			}
			//Parse file
			while(reader.hasNext()) {
				String line = reader.nextLine();
				char checkChar = line.charAt(0);
				String desc = line.substring(2);
				boolean check = false;
				if(!reset&&checkChar=='X') check = true;
				items.add(new Item(desc,check));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			displayError("Could not find file.");
		}
	}

	
	public void addItem(String desc) {
		items.add(new Item(desc));
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public void checkItem(int index) {
		items.get(index).enableCheck();
	}
	
	public void uncheckItem(int index) {
		items.get(index).disableCheck();
	}	
	
	public String getResetTime() {
		return resetTime;
	}
	
	public void setResetTime(String time) {
		this.resetTime = time;
	}
	
	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}
	
	public String toString() {
		String output = "";
		for(Item item:items) {
			char check = 'O';
			if(item.getCheck()) check = 'X';
			output += check+" "+item.getDescription()+"\n";
		}
		return output;
	}
	
	private void displayError(String msg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorPopUp frame = new ErrorPopUp(msg);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
