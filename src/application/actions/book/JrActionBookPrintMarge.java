/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import book.JrBook;

import application.JrApplicationOption;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookPrintMarge extends JrActionMenu {
	public JrActionBookPrintMarge() {
		super("MenuLivreConfig",KeyEvent.VK_M,"MenuLivreConfigHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		PrinterJob printjob = PrinterJob.getPrinterJob();
		PageFormat pg = JrApplicationOption.GetPageFormat();
		PageFormat p2 = printjob.pageDialog(pg);
		JrApplicationOption.SetPageFormat(p2);
	}
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == false)
			return false;
		return (JrApplicationOption.GetPageFormat() != null)? true : false;
	}
}
