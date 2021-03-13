/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.data;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;

import application.actions.JrActionMenu;
import application.dialogs.JrBookDialog;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionDataTitle extends JrActionMenu {
	public JrActionDataTitle() {
		super("MenuDataTitre",KeyEvent.VK_T,KeyEvent.VK_T, ActionEvent.CTRL_MASK,
				"MenuDataTitreHlp","images/title.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrBookDialog dlg = new JrBookDialog(GetMainFrame(),book,0);
		if ((dlg.execute() == true) && (book != null))
			book.fireCurrentViewChanged();
	}
}
