/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionDel extends JrActionMenu {
	public JrActionEditionDel() {
		super("MenuEditDelCase",KeyEvent.VK_S,KeyEvent.VK_Y, ActionEvent.CTRL_MASK,
				"MenuEditDelCaseHlp","images/del.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GetBiblio().getCurrentBook().removeCase();
		refreshAllActions();
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCaseCount() > 1)? true : false;
		}
		return false;
	}
}
