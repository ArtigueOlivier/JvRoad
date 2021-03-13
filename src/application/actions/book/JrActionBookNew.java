/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import application.actions.JrActionMenu;
import application.dialogs.JrBookDialog;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookNew extends JrActionMenu {
	public JrActionBookNew() {
		super("MenuLivreNouveau",KeyEvent.VK_N,
				KeyEvent.VK_N, ActionEvent.CTRL_MASK,
				"MenuLivreNouveauHlp","images/new.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		String str = GetWord("TxtARenseigner");
		JrBook book = new JrBook(str,str);
		JrBookDialog dlg = new JrBookDialog(GetMainFrame(),book,0);
		if ((dlg.execute() == true) && (book != null)) {
			GetBiblio().newBook(book);
			refreshAllActions();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
