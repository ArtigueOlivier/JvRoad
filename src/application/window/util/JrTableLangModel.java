/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import tools.files.JrFileSave;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTableLangModel extends AbstractTableModel {
	private Vector items = new Vector();
	private String filename = "";
	
	public JrTableLangModel() {
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return items.size(); 
	}

	public String getColumnName(int col) {
		if (col == 0)
			return " " + JrApplicationOption.GetWord("TxtReference");
		return " " + JrApplicationOption.GetWord("TxtMessage");
    }
	
	public Object getValueAt(int row, int col) {
		JrLangItem item = (JrLangItem)(items.get(row));
		return (col == 0)? item.getReference() : item.getValue();
	}
	
	public JrLangItem getItem(int index) {
		return (JrLangItem)(items.get(index));
	}
	
	public boolean setFilename(String nfile,boolean lire,boolean keep) {
		if (keep)
			filename = nfile;
		if (lire == false)
			return true;
		FileInputStream file = null;
		try {
			file = new FileInputStream(nfile);
		} catch (FileNotFoundException e) {}
		if (file == null)
			return false;
		DataInput di = new DataInputStream(file);
		if (di == null) {
			try {
				file.close();
			} catch (IOException e1) {}
			return false;
		}			
		items.clear();
		String line;
		int last,pos;
		try {
			while((line = di.readLine()) != null) {
				last = line.length() - 1;
				if (line.length() > 3) {
					if ((line.charAt(0) != '#') && (line.charAt(last) != '/')) {
						pos = line.indexOf("=");
						if (pos > 0)
							items.add(new JrLangItem(line.substring(0,pos),line.substring(pos+1,line.length())));
					}					
				}				
			}
		} catch (IOException e1) {}
		try {
			file.close();
		} catch (IOException e2) {}
		return true;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void save() {
		JrFileSave file = new JrFileSave(filename);
		int count = items.size();
		JrLangItem item = null;
		for(int i = 0; i < count; i++) {
			item = (JrLangItem)items.get(i);
			file.addEntry(item.getReference(),item.getValue());
		}
		file.close();
	}
}
