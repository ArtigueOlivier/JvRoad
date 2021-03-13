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
import book.map.JrMapVille;

import names.JrMapName;

import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionVille extends JrActionMenu {
	public static final int OPE_VILLE1_ON = 0;
	public static final int OPE_VILLE1_OFF = 1;
	public static final int OPE_VILLE2_ON = 2;
	public static final int OPE_VILLE2_OFF = 3;
	public static final int OPE_VILLE3_ON = 4;
	public static final int OPE_VILLE3_OFF = 5;
	public static final int OPE_VILLE4_ON = 6;
	public static final int OPE_VILLE4_OFF = 7;
	public static final int OPE_VILLE5_ON = 8;
	public static final int OPE_VILLE5_OFF = 9;
	public static final int OPE_VILLE_COUNT = 10;
	
	private int opeText = OPE_VILLE1_ON;
	public JrActionVille(String txt, String hlp, String img,int ope) {
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
		if (map.getName() == JrMapName.MAP_VILLE_NAME) {
			switch(opeText) {
			case OPE_VILLE1_ON : res = ((JrMapVille)map).setEntree(0,1); break;
			case OPE_VILLE1_OFF : res = ((JrMapVille)map).setEntree(0,0); break;
			case OPE_VILLE2_ON : res = ((JrMapVille)map).setEntree(1,1); break;
			case OPE_VILLE2_OFF : res = ((JrMapVille)map).setEntree(1,0); break;
			case OPE_VILLE3_ON : res = ((JrMapVille)map).setEntree(2,1); break;
			case OPE_VILLE3_OFF : res = ((JrMapVille)map).setEntree(2,0); break;
			case OPE_VILLE4_ON : res = ((JrMapVille)map).setEntree(3,1); break;
			case OPE_VILLE4_OFF : res = ((JrMapVille)map).setEntree(3,0); break;
			case OPE_VILLE5_ON : res = ((JrMapVille)map).setEntree(4,1); break;
			case OPE_VILLE5_OFF : res = ((JrMapVille)map).setEntree(4,0); break;
			default : break;
			}
		}
		if (res)
			book.fireCurrentViewChanged();
	}
	
	public static JrActionVille CreateAction(int ope) {
		switch(ope) {
	    case OPE_VILLE1_ON :
			return new JrActionVille("","MenuVille1yHlp",
					"images/ville1y.gif",ope);
	    case OPE_VILLE1_OFF :
			return new JrActionVille("","MenuVille1nHlp",
					"images/ville1n.gif",ope);
	    case OPE_VILLE2_ON :
			return new JrActionVille("","MenuVille2yHlp",
					"images/ville2y.gif",ope);
	    case OPE_VILLE2_OFF :
			return new JrActionVille("","MenuVille2nHlp",
					"images/ville2n.gif",ope);
	    case OPE_VILLE3_ON :
			return new JrActionVille("","MenuVille3yHlp",
					"images/ville3y.gif",ope);
	    case OPE_VILLE3_OFF :
			return new JrActionVille("","MenuVille3nHlp",
					"images/ville3n.gif",ope);
	    case OPE_VILLE4_ON :
			return new JrActionVille("","MenuVille4yHlp",
					"images/ville4y.gif",ope);
	    case OPE_VILLE4_OFF :
			return new JrActionVille("","MenuVille4nHlp",
					"images/ville4n.gif",ope);
	    case OPE_VILLE5_ON :
			return new JrActionVille("","MenuVille5yHlp",
					"images/ville5y.gif",ope);
	    case OPE_VILLE5_OFF :
			return new JrActionVille("","MenuVille5nHlp",
					"images/ville5n.gif",ope);
	    default : break;
		}
    	return new JrActionVille("","MenuVille1yHlp",
				"images/ville1y.gif",OPE_VILLE1_ON);
	}	
}
