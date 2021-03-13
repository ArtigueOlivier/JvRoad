/*
 * Created on 13 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.files;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFileFilter extends FileFilter {
	private String name = null;
	private String extension = null;
	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public JrFileFilter(String n,String ext) {
		name = n;
		extension = ext;
	}
	
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String ext = JrFileUtil.GetExtension(f);
		if ((ext != null) && (extension != null)) {
			if (ext.equals(extension)) {
				return true;
			} else {
			    return false;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription() {
		return name;
	}

}
