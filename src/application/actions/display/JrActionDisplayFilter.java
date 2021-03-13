/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.display;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import application.dialogs.JrBookDialog;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionDisplayFilter extends JrActionMenu {
	public JrActionDisplayFilter() {
		super("MenuDisplayFilter",KeyEvent.VK_F,"MenuDisplayFilterHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrBookDialog dlg = new JrBookDialog(GetMainFrame(),book,3);
		if ((dlg.execute() == true) && (book != null))
			book.fireCurrentViewChanged();
	}

	public boolean computeEnableAction(JrBook book) {
		if (JrApplicationOption.GetCode() == 129834765)
			return super.computeEnableAction(book);
		return false;
	}
}
