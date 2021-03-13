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
import book.map.JrMapLineText;

import names.JrMapName;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditLineText extends JrActionMenu {
	public static final int OPE_LINETEXT_EDIT0 = 0;
	public static final int OPE_LINETEXT_EDIT1 = 1;
	public static final int OPE_LINETEXT_EDIT2 = 2;
	public static final int OPE_LINETEXT_EDIT3 = 3;
	public static final int OPE_LINETEXT_EDIT4 = 4;
	public static final int OPE_LINETEXT_EDIT5 = 5;
	public static final int OPE_LINETEXT_EDIT6 = 6;
	public static final int OPE_LINETEXT_EDIT7 = 7;
	public static final int OPE_LINETEXT_EDIT8 = 8;
	public static final int OPE_LINETEXT_EDIT9 = 9;
	public static final int OPE_LINETEXT_LEFT =  10;
	public static final int OPE_LINETEXT_CENTER = 11;
	public static final int OPE_LINETEXT_RIGHT = 12;
	public static final int OPE_LINETEXT_COUNT = 13;
	
	private int opeText = OPE_LINETEXT_EDIT0;
	public JrActionEditLineText(String txt, String hlp, String img,int ope) {
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
		if (map.getName() == JrMapName.MAP_LINETEXT_NAME) {
			switch(opeText) {
			case OPE_LINETEXT_EDIT0 : res = ((JrMapLineText)map).editText(0); break;
			case OPE_LINETEXT_EDIT1 : res = ((JrMapLineText)map).editText(1); break;
			case OPE_LINETEXT_EDIT2 : res = ((JrMapLineText)map).editText(2); break;
			case OPE_LINETEXT_EDIT3 : res = ((JrMapLineText)map).editText(3); break;
			case OPE_LINETEXT_EDIT4 : res = ((JrMapLineText)map).editText(4); break;
			case OPE_LINETEXT_EDIT5 : res = ((JrMapLineText)map).editText(5); break;
			case OPE_LINETEXT_EDIT6 : res = ((JrMapLineText)map).editText(6); break;
			case OPE_LINETEXT_EDIT7 : res = ((JrMapLineText)map).editText(7); break;
			case OPE_LINETEXT_EDIT8 : res = ((JrMapLineText)map).editText(8); break;
			case OPE_LINETEXT_EDIT9 : res = ((JrMapLineText)map).editText(9); break;
			case OPE_LINETEXT_LEFT : 
				res = ((JrMapLineText)map).setCenter(false);
				res |= ((JrMapLineText)map).setRight(false);
				break;
			case OPE_LINETEXT_CENTER : res = ((JrMapLineText)map).setCenter(true); break;
			case OPE_LINETEXT_RIGHT : res = ((JrMapLineText)map).setRight(true); break;
			default : break;
			}
		}
		if (res)
			book.fireCurrentViewChanged();
	}
	
	public static JrActionEditLineText CreateAction(int ope) {
		switch(ope) {
	    case OPE_LINETEXT_EDIT0 :
			return new JrActionEditLineText("","MenuLineTextEdit0Hlp",
					"images/edttxt1.gif",ope);
	    case OPE_LINETEXT_EDIT1 :
			return new JrActionEditLineText("","MenuLineTextEdit1Hlp",
					"images/edttxt2.gif",ope);
	    case OPE_LINETEXT_EDIT2 :
			return new JrActionEditLineText("","MenuLineTextEdit2Hlp",
					"images/edttxt3.gif",ope);
	    case OPE_LINETEXT_EDIT3 :
			return new JrActionEditLineText("","MenuLineTextEdit3Hlp",
					"images/edttxt4.gif",ope);
	    case OPE_LINETEXT_EDIT4 :
			return new JrActionEditLineText("","MenuLineTextEdit4Hlp",
					"images/edttxt5.gif",ope);
	    case OPE_LINETEXT_EDIT5 :
			return new JrActionEditLineText("","MenuLineTextEdit5Hlp",
					"images/edttxt6.gif",ope);
	    case OPE_LINETEXT_EDIT6 :
			return new JrActionEditLineText("","MenuLineTextEdit6Hlp",
					"images/edttxt7.gif",ope);
	    case OPE_LINETEXT_EDIT7 :
			return new JrActionEditLineText("","MenuLineTextEdit7Hlp",
					"images/edttxt8.gif",ope);
	    case OPE_LINETEXT_EDIT8 :
			return new JrActionEditLineText("","MenuLineTextEdit8Hlp",
					"images/edttxt9.gif",ope);
	    case OPE_LINETEXT_EDIT9 :
			return new JrActionEditLineText("","MenuLineTextEdit9Hlp",
					"images/edttxt10.gif",ope);
		case OPE_LINETEXT_LEFT : 
	    	return new JrActionEditLineText("","MenuMapAlignLeftHlp",
					"images/lefttxtm.gif",ope);
		case OPE_LINETEXT_RIGHT : 
	    	return new JrActionEditLineText("","MenuMapAlignRightHlp",
					"images/righttxtm.gif",ope);
	    default : break;
		}
    	return new JrActionEditLineText("","MenuMapAlignCenterHlp",
				"images/centertxtm.gif",OPE_LINETEXT_CENTER);
	}	
}
