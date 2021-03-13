/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.position;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;
import application.dialogs.JrSearchDialog;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPositionSearch extends JrActionMenu {
	public JrActionPositionSearch() {
		super("MenuPosSearch",KeyEvent.VK_R,KeyEvent.VK_L, ActionEvent.CTRL_MASK,
				"MenuPosSearch");
	}
	public JrActionPositionSearch(String txt,int letter,String hlp,String img) {
		super(txt,KeyEvent.VK_R,letter, ActionEvent.CTRL_MASK,hlp,img);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrSearchDialog dlg = new JrSearchDialog(GetMainFrame(),book);
		if ((dlg.execute() == true) && (book != null)) {
			book.goToCase(dlg.getIndex());
			JrActionCenter.RefreshActions();
		}
	}
}
