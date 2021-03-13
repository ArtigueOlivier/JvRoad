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

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookQuit extends JrActionMenu {
	public JrActionBookQuit() {
		super("MenuLivreQuitter",KeyEvent.VK_Q,KeyEvent.VK_F4, ActionEvent.ALT_MASK,
				"MenuLivreQuitterHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (GetBiblio().hasModifiedBook()) {
			String msg = JrApplicationOption.GetWord("TxtVousPasSauverModif") + "\n" + JrApplicationOption.GetWord("TxtAnnulerModifications");
			if (JOptionPane.showOptionDialog(JrActionMenu.GetMainFrame(),
				    msg,JrApplicationOption.GetWord("TxtDemandeConfirm"),
					JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.NO_OPTION)
					return;
			
		}
		GetMainFrame().dispose();
	}
	
	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
