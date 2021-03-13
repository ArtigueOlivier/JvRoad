/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.tools;

import java.awt.event.ActionEvent;

import book.JrBook;

import application.actions.JrActionMenu;
import application.window.JrLangueWindow;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionLangue extends JrActionMenu {
	public static final int ACTION_NEW = 0;
	public static final int ACTION_OPEN = 1;
	public static final int ACTION_SAVE = 2;
	public static final int ACTION_SAVEAS = 3;
	public static final int ACTION_COUNT = 4;
	
	private JrLangueWindow window = null;
	private int action = ACTION_NEW;
	
	public JrActionLangue(JrLangueWindow w,int act,String hlp,String img) {
		super("",0,hlp,img);
		window = w;
		action = act;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (window != null)
			window.execute(action);
	}

	public boolean computeEnableAction(JrBook book) {
		return (window != null)? true : false;
	}
	
	public static JrActionLangue CreateAction(JrLangueWindow wnd,int act) {
		switch(act) {
		case ACTION_NEW :
			return new JrActionLangue(wnd,act,"MenuOutilsLangNewHlp","images/new.gif");
		case ACTION_OPEN :
			return new JrActionLangue(wnd,act,"MenuOutilsLangOpenHlp","images/open.gif");
		case ACTION_SAVE :
			return new JrActionLangue(wnd,act,"MenuOutilsLangSaveHlp","images/save.gif");
		default : break;
		}
		return new JrActionLangue(wnd,ACTION_SAVEAS,"MenuOutilsLangSaveAsHlp","images/saveas.gif");
	}
}
