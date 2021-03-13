/*
 * Created on 4 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.files;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFileSave extends JrFile {
	private FileWriter file = null;
	
	public JrFileSave(String name) {
		super(name);
		try {
			file = new FileWriter(name);
		} catch (IOException e) {
			file = null;
		}		
	}

	public boolean addSection(String section){
		if (file != null) {
			try {
				file.write("[" + section + "]\n");
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean addEntry(String entry,String val){
		if (file != null) {
			try {
				file.write(entry + "=" + val + "\n");
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean addEntry(String entry,int val){
		return addEntry(entry,Integer.toString(val));
	}

	public boolean addEntry(String entry,boolean val){
		return addEntry(entry,(val == true)? 1 : 0);
	}
	
	public boolean addEntry(String entry,Color val){
		long v = ((long)(val.getRed())) * 1000000L;
		v += ((long)(val.getGreen())) * 1000L;
		v += (long)(val.getBlue());
		return addEntry(entry,Long.toString(v));
	}

	public void close() {
		if (file != null)
			try {
				file.close();
			} catch (IOException e) {
			}
	}
}
