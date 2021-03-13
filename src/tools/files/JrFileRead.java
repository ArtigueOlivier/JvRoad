package tools.files;

import java.awt.Color;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFileRead extends JrFile {
	private HashMap sections = new HashMap();
	private JrFileSection currentSection = null;
	
	public JrFileRead() {
	}
	
	public JrFileRead(String n) {
		super(n);
	}

	public boolean read() throws IOException {
		if (getFilename() == null)
			return false;		
		FileInputStream file = new FileInputStream(getFilename());
		if (file == null)
			return false;
		DataInput di = new DataInputStream(file);
		if (di == null) {
			file.close();
			return false;
		}			
		String line;
		int last,pos;
		while((line = di.readLine()) != null) {
			last = line.length() - 1;
			if (last > 1) {
				if ((line.charAt(0) == '[') && (line.charAt(last) == ']')) {
					addSection(line.substring(1,last));					
				}
				if ((line.charAt(0) != '/') && (line.charAt(1) != '/')) {
					pos = line.indexOf("=");
					if (pos > 0) 
						addEntry(line.substring(0,pos),line.substring(pos+1,line.length()));
				}					
			}				
		}
		file.close();
		return true;
	}
	
	
	public boolean addSection(String n) {
		//System.out.println("[section:" + n + "]");
		currentSection = new JrFileSection();
		sections.put(n,currentSection);
		return true;
	}
	
	public boolean addEntry(String n,String v) {
		//System.out.println("Entry:" + n + "=" + v);
		currentSection.addEntry(n,v);
		return true;
	}
	
	public boolean addEntry(String n,int v) {
		if (currentSection != null) {
			currentSection.addEntry(n,Integer.toString(v));
			return true;
		}
		return false;
	}
	
	public boolean addEntry(String n,boolean v) {
		addEntry(n,(v == true)? 1 : 0);
		return true;
	}
	
		
	public boolean setCurrentSection(String section) {
		currentSection = (JrFileSection)sections.get(section);
		return (currentSection == null)? false : true;
	}
	
	public String getStringValue(String entry,String def) {
		if (currentSection != null) {
			JrFileEntry ent = currentSection.getEntry(entry);
			return (ent == null)? def : ent.getStringValue();
		}
		return def;
	}

	public int getIntegerValue(String entry,int def) {
		if (currentSection != null) {
			JrFileEntry ent = currentSection.getEntry(entry);
			return (ent == null)? def : ent.getIntegerValue();
		}
		return def;
	}
	
	public Color getColorValue(String entry,Color def) {
		if (currentSection != null) {
			JrFileEntry ent = currentSection.getEntry(entry);
			return (ent == null)? def : ent.getColorValue();
		}
		return def;
	}
}
