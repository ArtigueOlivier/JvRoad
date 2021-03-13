/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.orientation;

import java.awt.event.ActionEvent;

import book.JrBook;
import book.JrCase;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionCap extends JrActionMenu {
	private int cap;
	/**
	 * @param txt
	 * @param mnemo
	 * @param hlp
	 */
	public JrActionCap(int cap) {
		super(Integer.toString(cap), 0, GetWord("TxtAllerCap") + " " + cap);
		this.cap = cap;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();		
		if (cas.handlePaletteEvent(cap,book.getContext(),null))
			book.fireCurrentViewChanged();
	}
}
