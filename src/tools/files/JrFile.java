/*
 * Created on 4 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.files;


/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class JrFile {
	private String filename = "";
	
	public JrFile() {		
	}

	public JrFile(String name) {
		filename = name;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public abstract boolean addSection(String section);	
	public abstract boolean addEntry(String entry,String val);	
	public abstract boolean addEntry(String entry,int val);
	public abstract boolean addEntry(String entry,boolean val);	
}
