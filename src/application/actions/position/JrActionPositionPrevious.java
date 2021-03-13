/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.position;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrGotoName;

import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPositionPrevious extends JrActionMenu {
	public JrActionPositionPrevious() {
		super("MenuPosPrevious",KeyEvent.VK_C,KeyEvent.VK_X, 
				ActionEvent.CTRL_MASK,"MenuPosPreviousHlp",
			"images/previous.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		GetBiblio().getCurrentBook().goToCase(JrGotoName.GOTO_PREVIOUS);
		refreshAllActions();
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCurrentCaseIndex() > 0)? true : false;
		}
		return false;
	}
}
