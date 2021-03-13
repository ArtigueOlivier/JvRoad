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

import application.JrApplicationOption;
import application.actions.JrActionCenter;
import application.actions.JrActionMenu;
import application.dialogs.JrLicenceDialog;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionHelpLicence extends JrActionMenu {
	public JrActionHelpLicence() {
		super("MenuAideLicence",KeyEvent.VK_L,"MenuAideLicenceHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrLicenceDialog dlg = new JrLicenceDialog(GetMainFrame());
		if (dlg.execute() == true) {
			if (JrApplicationOption.Check(dlg.getValues())) {
				JOptionPane.showMessageDialog(GetMainFrame(),GetWord("TxtLicenceValide"),GetWord("TxtMessage"),
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif")));
				
			}
			else {
				JOptionPane.showMessageDialog(GetMainFrame(),GetWord("TxtLicenceInvalide"),GetWord("TxtMessage"),
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif")));
				
			}
			JrActionCenter.RefreshActions();
		}
	}

	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
