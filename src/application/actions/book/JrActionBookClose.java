/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import book.JrBook;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookClose extends JrActionMenu {

	public JrActionBookClose() {
		super("MenuLivreFermer",KeyEvent.VK_F,KeyEvent.VK_F4,
				ActionEvent.CTRL_MASK,"MenuLivreFermerHlp");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		if (book.isModified()) {
			String str = GetWord("TxtFichierModifie");
			if (JOptionPane.showOptionDialog(GetMainFrame(),str,
			    GetWord("TxtDemandeConfirm"),JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE, null, null, book) == JOptionPane.NO_OPTION)
				return;
		}
		GetBiblio().closeBook();
		refreshAllActions();
	}
}
