/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import tools.files.JrFileSave;

import book.JrBook;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookSave extends JrActionMenu {
	public JrActionBookSave() {
		super("MenuLivreSave",KeyEvent.VK_E,KeyEvent.VK_F2,0,"MenuLivreSaveHlp",
				"images/save.gif");
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		String sfile = book.getFilename();
		JrFileSave file = null;
		if (sfile.length() < 5) 
			sfile = getAFilename();
		if (sfile != null) {
            file = new JrFileSave(sfile);
			if (file != null) {
				if (book.save(file) == false)			
					System.out.println(GetWord("ErrEchecSave"));
				else {
	    			refreshAllActions();
	    			GetBiblio().fireBookSaved();
				}
				file.close();		
			}
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return book.isModified();
		}
		return false;
	}
	
}
