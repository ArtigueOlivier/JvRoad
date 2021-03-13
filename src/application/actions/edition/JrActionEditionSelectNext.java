/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import tools.JrContext;

import names.JrViewName;
import book.JrBook;
import book.JrCase;
import book.map.JrMap;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionSelectNext extends JrActionMenu {
	public JrActionEditionSelectNext() {
		super("MenuEditSelectNext",KeyEvent.VK_C,KeyEvent.VK_RIGHT,
				ActionEvent.CTRL_MASK,"MenuEditSelectNextHlp");
	}

	public boolean computeEnableAction(JrBook book) {
		if ((book != null) && (book.getContext().getCurrentView() < JrViewName.VIEW_REAL))
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();
		JrContext cnt = book.getContext();
		int view = cnt.getCurrentView();
		JrMap map = cas.getMap(view);
		if (map.selectNextObjectName(cnt))
			book.fireCurrentViewChanged();
	}

}
