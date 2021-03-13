/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.position;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;
import application.dialogs.JrNumberDialog;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPositionGoto extends JrActionMenu {
	public JrActionPositionGoto() {
		super("MenuPosGoto",KeyEvent.VK_A,KeyEvent.VK_G, ActionEvent.CTRL_MASK,
				"MenuPosGotoHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrNumberDialog dlg = new JrNumberDialog(GetMainFrame(),1,
				book.getCaseCount(),book.getCurrentCaseIndex()+1,
				"TxtAllerCase","TxtNumeroCase");
		if (dlg.execute() == true) {
			int pos = dlg.getValue();
			book.goToCase(pos-1);
			JrActionCenter.RefreshActions();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCaseCount() > 1)? true : false;
		}
		return false;
	}

}
