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
import application.dialogs.JrCaseDialog;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionInsert extends JrActionMenu {
	public JrActionEditionInsert() {
		super("MenuEditInsererCase",KeyEvent.VK_I,KeyEvent.VK_I, 
				ActionEvent.CTRL_MASK,"MenuEditInsererCase","images/insert.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCaseDialog dlg = new JrCaseDialog(GetMainFrame(),null,0);
		if ((dlg.execute() == true) && (book != null)) {
			book.insertCase(dlg.getDistance(),dlg.isRaz(),dlg.isApprox(),
					dlg.isInconnuDistance(),dlg.getSymbol(0),dlg.getSymbol(1),
					dlg.getSymbol(2),dlg.getSymbol(3),dlg.getText());
			refreshAllActions();
		}		
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCaseCount() < book.getMaximumCase())? true : false;
		}
		return false;
	}
}
