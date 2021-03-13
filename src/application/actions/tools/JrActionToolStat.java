/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.tools;

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
public class JrActionToolStat extends JrActionMenu {
	public JrActionToolStat() {
		super("MenuOutilsStat",KeyEvent.VK_S,KeyEvent.VK_S, ActionEvent.CTRL_MASK,
				"MenuOutilsStatHlp","images/stat.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrBookDialog dlg = new JrBookDialog(GetMainFrame(),book,2);
		if ((dlg.execute() == true) && (book != null))
			book.fireCurrentViewChanged();
	}
}
