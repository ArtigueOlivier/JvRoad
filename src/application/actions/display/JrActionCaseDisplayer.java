/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.display;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBook;
import book.displayer.JrCaseDisplayer;

import names.JrCaseDisplayerName;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionCaseDisplayer extends JrActionMenu {
	private int codeDisplayer = 0;
	
	public JrActionCaseDisplayer(String txt,int mnemo,String hlp,String img,int disp) {
		super(txt,mnemo,hlp);
		codeDisplayer = disp;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		if (book.getCaseDisplayer().getName() != codeDisplayer) {
			book.setCaseDisplayer(JrCaseDisplayer.CreateDisplayer(codeDisplayer));
			JrActionCenter.RefreshActionsDisplayer();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCaseDisplayer().getName() == codeDisplayer)? false : true;
		}
		return false;
	}
	
	public static JrActionCaseDisplayer CreateAction(int disp) {
		switch(disp) {
		case JrCaseDisplayerName.CASE_DISPLAYER_NOTOTAL :
			return new JrActionCaseDisplayer("CaseDisplayerNoTotal",
					KeyEvent.VK_S,"CaseDisplayerNoTotalHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE :
			return new JrActionCaseDisplayer("CaseDisplayerRallye",
					KeyEvent.VK_R,"CaseDisplayerRallyeHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE_PARTIEL :
			return new JrActionCaseDisplayer("CaseDisplayerRallyePartiel",
					KeyEvent.VK_P,"CaseDisplayerRallyePartielHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE_TOTAL :
			return new JrActionCaseDisplayer("CaseDisplayerRallyeTotal",
					KeyEvent.VK_T,"CaseDisplayerRallyeTotalHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW :
			return new JrActionCaseDisplayer("CaseDisplayerMaxDraw",
					KeyEvent.VK_D,"CaseDisplayerMaxDrawHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW_PARTIEL :
			return new JrActionCaseDisplayer("CaseDisplayerMaxDrawPartiel",
					KeyEvent.VK_X,"CaseDisplayerMaxDrawPartielHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW_TOTAL :
			return new JrActionCaseDisplayer("CaseDisplayerMaxDrawTotal",
					KeyEvent.VK_O,"CaseDisplayerMaxDrawTotalHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO :
			return new JrActionCaseDisplayer("CaseDisplayerMoto",
					KeyEvent.VK_M,"CaseDisplayerMotoHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO_PARTIEL :
			return new JrActionCaseDisplayer("CaseDisplayerMotoPartiel",
					KeyEvent.VK_A,"CaseDisplayerMotoPartielHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO_TOTAL :
			return new JrActionCaseDisplayer("CaseDisplayerMotoTotal",
					KeyEvent.VK_E,"CaseDisplayerMotoTotalHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_RAID_PARTIEL :
			return new JrActionCaseDisplayer("CaseDisplayerRaidPartiel",
					KeyEvent.VK_I,"CaseDisplayerRaidPartielHlp","",disp);
		case JrCaseDisplayerName.CASE_DISPLAYER_RAID_TOTAL :
			return new JrActionCaseDisplayer("CaseDisplayerRaidTotal",
					KeyEvent.VK_L,"CaseDisplayerRaidTotalHlp","",disp);
		default: break;		
		}
		return new JrActionCaseDisplayer("CaseDisplayerDefault",KeyEvent.VK_D,
				"CaseDisplayerDefaultHlp","",JrCaseDisplayerName.CASE_DISPLAYER_DEFAULT);
	}
	
}
