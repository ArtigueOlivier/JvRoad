/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.files;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFileSection {
	private HashMap entries = new HashMap();
	/**
	 * @param n
	 * @param v
	 */
	JrFileSection() {
	}

	public boolean read() {
		return true;
	}
	
	public boolean write(FileWriter file) {
		Set items = entries.entrySet();
		Iterator iter = items.iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			String name = (String)entry.getKey();
			String value = (String)entry.getValue();
			System.out.println(name + "=" + value);
			/*
			try {
				file.write(name + "=" + value + "\n");	
			} catch (IOException e) {
				return false;
			}
			*/
		}
		return true;
	}

	public void addEntry(String n,String v) {
		entries.put(n,new JrFileEntry(v));
	}
	
	public JrFileEntry getEntry(String n) {
		return (JrFileEntry)entries.get(n);
	}
}
