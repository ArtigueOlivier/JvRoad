/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import book.JrBook;
import book.JrCase;
import book.map.JrMap;
import book.map.JrMapText;

import names.JrMapName;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditText extends JrActionMenu {
	public static final int OPE_TEXT_EDIT = 0;
	public static final int OPE_TEXT_DEL = 1;
	public static final int OPE_TEXT_LEFT = 2;
	public static final int OPE_TEXT_CENTER = 3;
	public static final int OPE_TEXT_RIGHT = 4;
	
	private int opeText = OPE_TEXT_EDIT;
	public JrActionEditText(String txt, String hlp, String img,int ope) {
		super(txt, 0, hlp, img);
		opeText = ope;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		boolean res = false;
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();
		JrMap map = cas.getMap(book.getCurrentView());
		if (map.getName() == JrMapName.MAP_TEXT_NAME) {
			switch(opeText) {
			case OPE_TEXT_EDIT : res = ((JrMapText)map).editText(); break;
			case OPE_TEXT_DEL : res = ((JrMapText)map).cancelText(); break;
			case OPE_TEXT_LEFT : res = ((JrMapText)map).setCenter(false); break;
			case OPE_TEXT_CENTER : res = ((JrMapText)map).setCenter(true); break;
			case OPE_TEXT_RIGHT : 
				res = ((JrMapText)map).setCenter(true); 
				res |= ((JrMapText)map).setRight(true); 
				break;
			default : break;
			}
		}
		if (res)
			book.fireCurrentViewChanged();
	}
	
	public static JrActionEditText CreateAction(int ope) {
		switch(ope) {
	    case OPE_TEXT_EDIT :
			return new JrActionEditText("MenuTextEdit","MenuTextEditHlp",
					"images/edttxt.gif",ope);
	    case OPE_TEXT_DEL :
	    	return new JrActionEditText("MenuTextEfface","MenuTextEffaceHlp",
				"images/deltxt.gif",ope);
		case OPE_TEXT_LEFT : 
	    	return new JrActionEditText("","MenuMapAlignLeftHlp",
					"images/lefttxtm.gif",ope);
		case OPE_TEXT_RIGHT : 
	    	return new JrActionEditText("","MenuMapAlignRightHlp",
					"images/righttxtm.gif",ope);
	    default : break;
		}
    	return new JrActionEditText("","MenuMapAlignCenterHlp",
				"images/centertxtm.gif",OPE_TEXT_CENTER);
	}	
}
