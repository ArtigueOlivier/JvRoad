/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.display;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrPageDisplayerName;

import book.JrBook;
import book.displayer.JrPageDisplayer;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPageDisplayer extends JrActionMenu {
	private int codeDisplayer = 0;
	
	public JrActionPageDisplayer(String txt,int mnemo,String hlp,String img,int disp) {
		super(txt,mnemo,hlp,img);
		codeDisplayer = disp;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		if (book.getPageDisplayer().getName() != codeDisplayer) {
			book.setPageDisplayer(JrPageDisplayer.CreateDisplayer(codeDisplayer));
			JrActionCenter.RefreshActionsDisplayer();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getPageDisplayer().getName() == codeDisplayer)? false : true;
		}
		return false;
	}
	
	public static JrActionPageDisplayer CreateAction(int disp) {
		switch(disp) {
		case JrPageDisplayerName.PAGE_DISPLAYER_MONOCOLUMN :
			return new JrActionPageDisplayer("PageDisplayerMonoColumn",
					KeyEvent.VK_U,"PageDisplayerMonoColumnHlp",
					"images/dispmono.gif",disp);
		case JrPageDisplayerName.PAGE_DISPLAYER_TWOPAGES :
			return new JrActionPageDisplayer("PageDisplayerTwoPages",
					KeyEvent.VK_P,"PageDisplayerTwoPagesHlp",
					"images/disptwop.gif",disp);
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID :
			return new JrActionPageDisplayer("PageDisplayerRaid",
					KeyEvent.VK_R,"PageDisplayerRaidHlp",
					"images/disprwoc.gif",disp);
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID_MONOCOLUMN :
			return new JrActionPageDisplayer("PageDisplayerRaidMonoColumn",
					KeyEvent.VK_A,"PageDisplayerRaidMonoColumnHlp",
					"images/disprono.gif",disp);
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID_TWOPAGES :
			return new JrActionPageDisplayer("PageDisplayerRaidTwoPages",
					KeyEvent.VK_I,"PageDisplayerRaidTwoPagesHlp",
					"images/disprwop.gif",disp);
		default: break;		
		}
		return new JrActionPageDisplayer("PageDisplayerDefault",KeyEvent.VK_D,
				"PageDisplayerDefaultHlp","images/disptwoc.gif",
				JrPageDisplayerName.PAGE_DISPLAYER_DEFAULT);
	}
}
