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

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import application.window.JrLangueWindow;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionToolLangueEdit extends JrActionMenu {
	public JrActionToolLangueEdit() {
		super("MenuOutilsLangEdit",KeyEvent.VK_E,"MenuOutilsLangEditHlp",
				"images/lang.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrLangueWindow wnd = new JrLangueWindow();
		wnd.show();
	}

	public boolean computeEnableAction(JrBook book) {
		return (JrApplicationOption.GetCode() != 129834765)? false : true;
	}
}
