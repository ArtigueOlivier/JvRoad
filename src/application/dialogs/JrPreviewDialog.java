/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.Container;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import application.actions.JrActionCenter;
import book.JrBookListener;
import book.JrBookPreview;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPreviewDialog extends JDialog {
	private Book book = null;
	private JrPreview canvas = null;
	private JrBookListener listener = null;
	private JrBookPreview bookprn = null;
	private PageFormat pf = null;

	public JrPreviewDialog(JFrame frame,String title,JrBookPreview p) {
		super(frame,title);
		PrinterJob printJob = PrinterJob.getPrinterJob();
		pf = printJob.defaultPage();		
		book = new Book();
		book.append(p,pf,p.getPageCount());		
		buildPanel();
		bookprn = p;
		listener = new JrBookListener(){

			public void titleChanged() {
				connect();
			}

			public void copyrightChanged() {
				connect();
			}

			public void positionChanged() {
				connect();
			}

			public void caseAdded() {
				connect();
			}

			public void caseDeleted() {
				connect();
			}

			public void currentViewChanged() {
				connect();
			}

			public void currentMapChanged() {
				connect();
			}

			public void saved() {
				connect();
			}

			public void showIndexChanged() {
				connect();				
			}
		};
		bookprn.getBook().addListener(listener);
	}
	
	public JrPreviewDialog(JFrame frame,String title,Book bk) {
		super(frame,title);
		book = bk;		
		buildPanel();
	}

	public void disconnectAll() {
		JrActionCenter.GetActionMenuCenter().freePreviewActions();
		if (bookprn != null)
			bookprn.getBook().removeListener(listener);
	}
	
	public void connect() {
		bookprn.resetRegions();
		book = new Book();
		book.append(bookprn,pf,bookprn.getPageCount());		
		canvas.setBook(book);
	}
	
	public void buildPanel() {
		setSize(600,800);
		Container contentPane = getContentPane();
        canvas = new JrPreview(book);
		JToolBar toolbar = JrActionCenter.GetActionMenuCenter().createToolbarPreview(canvas);
		contentPane.add(toolbar,"North");
        contentPane.add(canvas,"Center");
        JrActionCenter.RefreshActionsPreview();
	}
	
	
}
