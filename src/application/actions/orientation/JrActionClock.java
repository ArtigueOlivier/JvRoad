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
public class JrActionClock extends JrActionMenu {
	private int clock;
	/**
	 * @param txt
	 * @param mnemo
	 * @param hlp
	 */
	public JrActionClock(int clock) {
		super(Integer.toString(clock) + "h00", 0, GetWord("TxtAllerA") + " " + clock + "h00");
		this.clock = clock;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();		
		if (cas.handlePaletteEvent(clock,book.getContext(),null))
			book.fireCurrentViewChanged();
	}
}
