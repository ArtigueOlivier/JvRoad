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
import names.JrViewName;
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
public class JrActionEditionCopyCurrentMap extends JrActionMenu {
	public JrActionEditionCopyCurrentMap() {
		super("MenuEditCopyMap",KeyEvent.VK_F,KeyEvent.VK_O, 
				ActionEvent.CTRL_MASK,"MenuEditCopyMapHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas  = book.getCurrentCase();
		int typ = JrClipboardOpeName.CLIPBOARD_MAP1;
		switch(book.getCurrentView()) {
		case JrViewName.VIEW_TWO : 
			typ = JrClipboardOpeName.CLIPBOARD_MAP2; break;
		case JrViewName.VIEW_THREE :
			typ = JrClipboardOpeName.CLIPBOARD_MAP3; break;
		case JrViewName.VIEW_FOUR :
			typ = JrClipboardOpeName.CLIPBOARD_MAP4; break;
		case JrViewName.VIEW_OBJ :
			typ = JrClipboardOpeName.CLIPBOARD_MAP5; break;
		default : break;
		}
		JrApplicationClipboard.SetClipboard(cas,typ);
	}
}
