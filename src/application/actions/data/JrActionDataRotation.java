/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.data;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrViewName;

import book.JrBook;

import application.JrApplicationOption;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionDataRotation extends JrActionMenu {
	public JrActionDataRotation() {
		super("MenuDataRotation",KeyEvent.VK_R,KeyEvent.VK_J, ActionEvent.CTRL_MASK,
				"MenuDataRotationHlp","images/caserota.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		book.getCurrentCase().rotation(book.getCurrentView());
		book.fireCurrentViewChanged();
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (JrApplicationOption.GetCode() != 129834765)
			return false;
		if (super.computeEnableAction(book) == true) {
			return (book.getCurrentView() >= JrViewName.VIEW_OBJ)? false : true;
		}
		return false;
	}	
}
