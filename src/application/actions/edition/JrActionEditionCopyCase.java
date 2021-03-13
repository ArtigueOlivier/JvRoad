/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrClipboardOpeName;

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
public class JrActionEditionCopyCase extends JrActionMenu {
	public JrActionEditionCopyCase() {
		super("MenuEditCopyCase",KeyEvent.VK_E,KeyEvent.VK_C, 
				ActionEvent.CTRL_MASK,"MenuEditCopyCaseHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas  = book.getCurrentCase();
		JrApplicationClipboard.SetClipboard(cas,JrClipboardOpeName.CLIPBOARD_CASE);
	}
}
