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
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionPaste extends JrActionMenu {
	public JrActionEditionPaste() {
		super("MenuEditColler",KeyEvent.VK_C,KeyEvent.VK_V, 
				ActionEvent.CTRL_MASK,"MenuEditCollerHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas  = book.getCurrentCase();
		cas.paste(JrApplicationClipboard.GetClipboard(),
				JrApplicationClipboard.GetClipboardType());
		book.updateTotal(true);
	}

}
