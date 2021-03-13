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
import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionHelpAbout extends JrActionMenu {
	public JrActionHelpAbout() {
		super("MenuAideAbout",KeyEvent.VK_A,KeyEvent.VK_F1,
				ActionEvent.ALT_MASK,"MenuAideAboutHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		int v = JrApplicationOption.GetVersion();
		String cl = JrApplicationOption.GetStrVer();
		String svr = (((v%100) < 10)? ".0" : ".") + (v % 100);
		String str = "JvRoad\n" + GetWord("TxtVersion") + " " + (v/100) + svr;
		String ver = " - EVA";
		if (JrApplicationOption.IsReaderMode() > 0)
			ver = " - READER MODE";
		else {
			switch(JrApplicationOption.GetCode()) {
			case 129834765 : ver = " - PRO"; break;
			case 127895623 : ver = " - LIGHT"; break;
			}
		}
		String cop = "\nCopyright Now Editions 2018-2022\n";
		String msg = "";
		if (cl.length() > 0)
			msg = str + ver + cop + "\n\n" + GetWord("TxtLicence") + ": " + cl;
		else
			msg = str + ver + cop;
		JOptionPane.showMessageDialog(GetMainFrame(),msg,GetWord("TxtAPropos"),
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif")));
	}

	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
