/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.data;

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
public class JrActionDataAlign extends JrActionMenu {
	private boolean center = false;
	
	public JrActionDataAlign(String hlp,String img,boolean cnt) {
		super("",0,hlp,img);
		center = cnt;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();
		if (cas != null) {
			cas.setCenterComments(center);
			book.fireCurrentViewChanged();
		}
	}
	
	public static JrActionDataAlign CreateAction(boolean cnter) {
		if (cnter)
			return new JrActionDataAlign("MenuDataAlignCenterHlp","images/centertxt.gif",true);
		return new JrActionDataAlign("MenuDataAlignLeftHlp","images/lefttxt.gif",false);
	}
}
