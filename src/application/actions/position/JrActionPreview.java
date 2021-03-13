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
import application.dialogs.JrPreview;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPreview extends JrActionMenu {
	private JrPreview navig = null;
	private int operator = JrGotoName.GOTO_FIRST;
	
	public JrActionPreview(String txt,String hlp,String img,
			JrPreview wnd,int ope) {
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
	
	public static JrActionPreview CreateAction(JrPreview wnd,int ope) {
		switch(ope) {
		case JrGotoName.GOTO_PREVIOUS :
			return new JrActionPreview("MenuPosPreviousPage",
					"MenuPosPreviousPageHlp","images/previous.gif",
					wnd,ope);
		case JrGotoName.GOTO_NEXT :
			return new JrActionPreview("MenuPosNextPage",
					"MenuPosNextPage","images/next.gif",wnd,ope);
		case JrGotoName.GOTO_LAST :
			return new JrActionPreview("MenuPosLastPage",
					"MenuPosLastPageHlp","images/last.gif",wnd,ope);
		default : break;
		}
		return new JrActionPreview("MenuPosFirstPage",
				"MenuPosFirstPageHlp","images/first.gif",wnd,
				JrGotoName.GOTO_FIRST);		
	}
}
