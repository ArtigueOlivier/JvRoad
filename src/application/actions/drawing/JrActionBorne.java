/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import tools.JrContext;
import book.JrBook;

import names.JrBorneName;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBorne extends JrActionMenu {	
	private int codeBorne;
	
	public JrActionBorne(String hlp, String img,int borne) {
		super("", 0, hlp, img);
		codeBorne = borne;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrContext context = book.getContext();
		if (context.getCurrentBorne() != codeBorne) {
			context.setCurrentBorne(codeBorne);
			JrActionCenter.RefreshActionsBorne();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			JrContext context = book.getContext();
			return (context.getCurrentBorne() == codeBorne)? false : true;
		}
		return false;
	}
	
	public static JrActionBorne CreateAction(int borne) {
		switch(borne) {
		case JrBorneName.BORNE_NATIONALE :
			return new JrActionBorne("BorneNationaleHlp","images/bornenat.gif",borne);
		case JrBorneName.BORNE_DEPARTEMENTALE :
			return new JrActionBorne("BorneDepartementaleHlp","images/bornedep.gif",borne);
		case JrBorneName.BORNE_COMMUNALE :
			return new JrActionBorne("BorneCommunaleHlp","images/bornecom.gif",borne);
		case JrBorneName.BORNE_GR :
			return new JrActionBorne("BorneGRHlp","images/bornegr.gif",borne);
		case JrBorneName.BORNE_NATIONALEBIG :
			return new JrActionBorne("BorneNationaleBigHlp","images/bornegdnat.gif",borne);
		case JrBorneName.BORNE_DEPARTEMENTALEBIG :
			return new JrActionBorne("BorneDepartementaleBigHlp","images/bornegddep.gif",borne);
		case JrBorneName.BORNE_COMMUNALEBIG :
			return new JrActionBorne("BorneCommunaleBigHlp","images/bornegdcom.gif",borne);
		case JrBorneName.BORNE_GRBIG :
			return new JrActionBorne("BorneGRBigHlp","images/bornegdgr.gif",borne);
		default: break;
		}
		return new JrActionBorne("BorneNothingHlp","images/efface.gif",JrBorneName.BORNE_NONE);
	}
}
