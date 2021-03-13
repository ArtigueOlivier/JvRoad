/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import book.JrBook;
import book.JrCase;

import names.JrClipartName;


import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionClipart extends JrActionMenu {
	private int codeClipart = JrClipartName.CLIPART_NAVIG;

	public JrActionClipart(String hlp, String img,int clp) {
		super("", 0, hlp, img);
		codeClipart = clp;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();		
		if (cas.handlePaletteEvent(codeClipart,book.getContext(),null))
			book.fireCurrentViewChanged();
	}
	
	public static JrActionClipart CreateAction(int clp) {
		switch(clp) {
		case JrClipartName.CLIPART_START :
			return new JrActionClipart("ClipartStartHlp","images/clpdep.gif",clp);
		case JrClipartName.CLIPART_END :
			return new JrActionClipart("ClipartEndHlp","images/clparr.gif",clp);
		case JrClipartName.CLIPART_REPAS :
			return new JrActionClipart("ClipartRepasHlp","images/clprepas.gif",clp);
		case JrClipartName.CLIPART_PAUSE :
			return new JrActionClipart("ClipartPauseHlp","images/clppause.gif",clp);
		case JrClipartName.CLIPART_CP :
			return new JrActionClipart("ClipartCPHlp","images/clpcp.gif",clp);
		case JrClipartName.CLIPART_CH :
			return new JrActionClipart("ClipartCHHlp","images/clpch.gif",clp);
		default: break;
		}
		return new JrActionClipart("ClipartNavigHlp",
				"images/clpbous.gif",JrClipartName.CLIPART_NAVIG);
	}
}
