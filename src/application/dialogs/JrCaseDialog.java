/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.dialogs.panels.JrCasePanel;
import application.dialogs.panels.JrTextPanel;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseDialog extends JrTabbedDialog  {
	private JrBook book = null;
	
	private JrCasePanel casePanel;
	private JrTextPanel textPanel;
	
	public JrCaseDialog(JFrame frame, JrBook bk,int page) {
		super(frame,"TxtInfoCase",2,page);
		book = bk;
	}
	
	public JPanel buildPage(int pageno) {
		switch(pageno) {
		case 0 : return buildPageCase();  
		case 1 : return buildPageText();
		}
		return null;
	}
	
	public JPanel buildPageCase() {
		casePanel = new JrCasePanel(book);
		return casePanel;
	}
		
	public JPanel buildPageText() {
		textPanel = new JrTextPanel(book);
		return textPanel;
	}
		
	public String getPageName(int pageno) {
		switch(pageno) {
		case 1 : return "TxtTexte";
		default: break;
		}
		return "TxtGeneral";
	}
	public Icon getPageIcon(int pageno) {
		return null;
	}
	public String getPageTooltip(int pageno) {
		switch(pageno) {
		case 0 : return "TxtModifierGeneralCase";  
		case 1 : return "TxtModifierTexteCase";
		}
		return "";
	}
	
	public boolean validatePage(int pageno) {
		switch(pageno) {
		case 0 : return casePanel.validatePage();
		case 1 : return textPanel.validatePage();
		default: break;
		}
		return false;
	}	
	
	public int getDistance() {
		return casePanel.getDistance();
	}
	public boolean isRaz() {
		return casePanel.isRaz();
	}	
	public boolean isApprox() {
		return casePanel.isApprox();
	}
	public boolean isInconnuDistance() {
		return casePanel.isInconnuDistance();
	}
	public int getSymbol(int num) {
		return casePanel.getSymbol(num);
	}
	public String getText() {
		return textPanel.getComments();
	}
}
