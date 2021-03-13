/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.help;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionHelpDoc extends JrActionMenu {
	public JrActionHelpDoc() {
		super("MenuAideDoc",KeyEvent.VK_D,KeyEvent.VK_F1,0,"MenuAideDocHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(GetMainFrame(),GetWord("TxtAccesDoc") + " www.road.fr",GetWord("TxtDocumentation"),
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif")));
	}

	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
