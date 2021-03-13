/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

import application.actions.JrActionCenter;

import names.JrGotoName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPreview extends JPanel {
	private Book book = null;
	private int currentPage = 0;
	private int pageCount = 0;
	
	public JrPreview(Book bk) {
		book = bk;
		if (book != null)
			pageCount = book.getNumberOfPages();
	}

	public void setBook(Book bk) {
		book = bk;
		if (book != null)
			pageCount = book.getNumberOfPages();
		currentPage = 0;
		repaint();
		JrActionCenter.RefreshActionsPreview();
	}
	
	public void goToCase(int ope) {
		switch(ope) {
		case JrGotoName.GOTO_PREVIOUS :
			currentPage--; break;
		case JrGotoName.GOTO_NEXT :
			currentPage++; break;
		case JrGotoName.GOTO_LAST :
			currentPage = (pageCount - 1); break;
		default : currentPage = 0; break;
		}		
		repaint();
	}
	
	public boolean isOperatorEnabled(int ope) {
		switch(ope) {
		case JrGotoName.GOTO_NEXT :			
		case JrGotoName.GOTO_LAST :
			return (currentPage < (pageCount - 1))? true : false; 
		default : break;
		}
		return (currentPage > 0)? true : false;
	}
			
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		PageFormat pageFormat = book.getPageFormat(currentPage);		
		double xoff,yoff,scale;
		double px = pageFormat.getWidth();
		double py = pageFormat.getHeight();
		double sx = getWidth() - 1;
		double sy = getHeight() - 1;
		if (px / py < sx / sy) {
			scale = sy / py;
			xoff = 0.5 * (sx -scale * px);
			yoff = 0.0;
		}
		else {
			scale = sx / px;
			xoff = 0.0;
			yoff = 0.5 * (sy - scale * py);
		}
		g2.translate(xoff,yoff);
		g2.scale(scale,scale);
		Rectangle2D page = new Rectangle2D.Double(0,0,px,py);
		g2.setPaint(Color.WHITE);
		g2.fill(page);
		g2.setPaint(Color.BLACK);
		g2.draw(page);
		if (book != null) {
			Printable prn = book.getPrintable(currentPage);
			try {
				prn.print(g2,pageFormat,currentPage);
			} catch (PrinterException e) {
			}
		}
	}
}
