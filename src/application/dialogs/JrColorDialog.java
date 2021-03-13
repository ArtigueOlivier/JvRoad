/*
 * Created on 18 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import names.JrColorName;

import application.dialogs.panels.JrColorPanel;
import book.JrBook;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrColorDialog extends JrTabbedDialog {
	private JrBook book;

	//---- Panels
	private Vector panels = new Vector();
	
	public JrColorDialog(JFrame frame,JrBook bk) {
		super(frame, "TxtCouleurs",JrColorName.COLOR_TYPE_COUNT,0);
		book = bk;
	}

	public JPanel buildPage(int pageno) {
		JrColorPanel panel = new JrColorPanel(book,pageno); 
		panels.add(panel);
		return panel;
	}
	
	public String getPageName(int pageno) {
		return JrColorName.GetTypeName(pageno);
	}
	
	public Icon getPageIcon(int pageno) {
		return null;
	}
	public String getPageTooltip(int pageno) {
		return JrColorName.GetTypeHelp(pageno);
	}
	
	public boolean validatePage(int pageno) {
		JrColorPanel pnl = (JrColorPanel)panels.get(pageno);
		return pnl.validatePage();
	}	
}
