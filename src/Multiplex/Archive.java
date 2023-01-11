package Multiplex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Archive {

	public Archive(File file) {
		this.save = true;
		this.file = file;
	}
	
	public void save() {
		if(save)
		{
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				output.writeObject(this.multiplex);
				output.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public Multiplex load() {
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			multiplex = (Multiplex) input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return multiplex;
	}
	
	public void setSave(boolean save) {
		this.save = save;
	}
	
	private Multiplex multiplex;
	public boolean save;
	private File file;
}
