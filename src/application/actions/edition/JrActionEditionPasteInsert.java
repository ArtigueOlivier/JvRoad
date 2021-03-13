/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;
import book.JrCase;

import application.JrApplicationClipboard;
import application.JrApplicationOption;
import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionPasteInsert extends JrActionMenu {
	public JrActionEditionPasteInsert() {
		super("MenuEditCollerInsert",KeyEvent.VK_P,KeyEvent.VK_E, 
				ActionEvent.CTRL_MASK,"MenuEditCollerInsertHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas  = new JrCase();
		cas.paste(JrApplicationClipboard.GetClipboard(),
				JrApplicationClipboard.GetClipboardType());
		book.insertCase(cas);
		JrActionCenter.RefreshActions();
	}

	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == false)
			return false;
		return (JrApplicationOption.GetCode() != 129834765)? false : true;
	}
	
}
