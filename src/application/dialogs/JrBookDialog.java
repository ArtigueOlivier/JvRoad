/*
 * Created on 18 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.JrApplicationOption;
import application.dialogs.panels.JrFilterPanel;
import application.dialogs.panels.JrIdentityPanel;
import application.dialogs.panels.JrStatPanel;
import book.JrBook;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookDialog extends JrTabbedDialog {
	private JrBook book;

	//---- Panels
	private JrIdentityPanel identityPanel;
	private JrStatPanel statPanel;
	private JrFilterPanel filterPanel = null;
	
	public JrBookDialog(JFrame frame,JrBook bk,int page) {
		super(frame, "TxtInfoLivre",4,page);
		book = bk;
	}

	public JPanel buildPage(int pageno) {
		switch(pageno) {
		case 0 : return buildPageIdentity();
		case 1 : return buildPageStat();
		case 2 : return buildPageFilter();
		}
		return null;
	}
	
	public JPanel buildPageIdentity() {
		identityPanel = new JrIdentityPanel(book);
		return identityPanel;
	}
	
	public JPanel buildPageStat() {
		statPanel = new JrStatPanel(book);
		return statPanel;
	}
	
	public JPanel buildPageFilter() {
		if (JrApplicationOption.GetCode() == 129834765) {
			filterPanel = new JrFilterPanel(book);
			return filterPanel;
		}
		return null;
	}
	
	public String getPageName(int pageno) {
		switch(pageno) {
		case 1 : return "TxtStatistiques";
		case 2 : return "TxtFiltres";
		default: break;
		}
		return "TxtIdentification";
	}
	public Icon getPageIcon(int pageno) {
		return null;
	}
	public String getPageTooltip(int pageno) {
		switch(pageno) {
		case 0 : return "TxtIdentificationHlp";  
		case 1 : return "TxtStatistiquesHlp";
		case 2 : return "TxtFiltresHlp";
		}
		return "";
	}
	
	public boolean validatePage(int pageno) {
		if (pageno == 1)
			return true;
		if (filterPanel != null) {
			if (filterPanel.validatePage() == false)
				return false;
		}
		return identityPanel.validatePage();
	}	
}
