/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.position;

import java.awt.event.ActionEvent;

import names.JrGotoName;

import application.actions.JrActionMenu;
import application.window.JrNavigDrawWindow;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionNavigator extends JrActionMenu {
	private JrNavigDrawWindow navig = null;
	private int operator = JrGotoName.GOTO_FIRST;
	
	public JrActionNavigator(String txt,String hlp,String img,
			JrNavigDrawWindow wnd,int ope) {
		super(txt,0,hlp,img);
		navig = wnd;
		operator = ope;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (navig != null) {
			navig.goToCase(operator);
			refreshAllActions();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			if (navig != null)
				return navig.isOperatorEnabled(operator);
		}
		return false;
	}
	
	public static JrActionNavigator CreateAction(JrNavigDrawWindow wnd,int ope) {
		switch(ope) {
		case JrGotoName.GOTO_PREVIOUS :
			return new JrActionNavigator("MenuPosPrevious","MenuPosPreviousHlp",
					"images/previous.gif",wnd,ope);
		case JrGotoName.GOTO_NEXT :
			return new JrActionNavigator("MenuPosNext","MenuPosNextHlp",
					"images/next.gif",wnd,ope);
		case JrGotoName.GOTO_LAST :
			return new JrActionNavigator("MenuPosLast","MenuPosLastHlp",
					"images/last.gif",wnd,ope);
		default : break;
		}
		return new JrActionNavigator("MenuPosFirst","MenuPosFirstHlp",
				"images/first.gif",wnd,JrGotoName.GOTO_FIRST);		
	}
}
