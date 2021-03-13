/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import book.JrBook;
import book.JrBookPrintWhite;

import application.JrApplicationOption;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookPrintWhite extends JrActionMenu {
	public JrActionBookPrintWhite() {
		super("MenuLivreFeuille",KeyEvent.VK_R,"MenuLivreFeuille");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook().whiteCopy();
		PrinterJob printjob = PrinterJob.getPrinterJob();
		PageFormat pg = JrApplicationOption.GetPageFormat();
		Book bk = new Book();
		bk.append(new JrBookPrintWhite(book),pg,1);		
		printjob.setPageable(bk);
		if (printjob.printDialog()) {
			try {
				printjob.print();
			} catch (PrinterException e) {
			}
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == false)
			return false;
		if (JrApplicationOption.GetCode() != 129834765)
			return false;
		return (JrApplicationOption.GetPageFormat() != null)? true : false;
	}
}
