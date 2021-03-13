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
public class JrActionBookSaveAs extends JrActionMenu {
	public JrActionBookSaveAs() {
		super("MenuLivreSaveAs",KeyEvent.VK_S,KeyEvent.VK_F2,
				ActionEvent.CTRL_MASK,"MenuLivreSaveAsHlp","images/saveas.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		String sfile = getAFilename();
		JrFileSave file = null;
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
}
