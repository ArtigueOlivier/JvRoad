/*
 * Created on 11 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

import java.awt.Rectangle;
import java.awt.print.Printable;

import names.JrDrawPortName;
import names.JrPageRegionName;

import tools.JrDrawTools;
import tools.JrPageRegions;
import application.JrApplicationOption;
import book.displayer.JrCaseDisplayer;
import book.displayer.JrPageDisplayer;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class JrBookAbstractPrintable implements Printable {
	protected JrBook book = null;
	protected JrPageDisplayer displayer = null;
	protected JrPageRegions regions1 = new JrPageRegions();
	protected JrPageRegions regions2 = new JrPageRegions();
	protected JrDrawTools dt = new JrDrawTools(null,JrDrawPortName.PORT_PRINT,100,0);
	protected int refWidth = 0;
	protected int refHeight = 0;

	public JrBookAbstractPrintable(JrBook bk) {
		book = bk;
		if (book != null) {
			displayer = book.getPageDisplayer();
			dt = new JrDrawTools(book.getColorBook(),JrDrawPortName.PORT_PRINT,book.getScalePath(),0);
			dt.setDisplayMeters(book.getDisplayMeters());
		}
	}
	
	public JrBook getBook() {
		return book;
	}
	
	public void resetRegions() {
		refWidth = 0;
		refHeight = 0;
		displayer = (book != null)? book.getPageDisplayer() : null;
	}	
	
	public void computeRegions(JrPageRegions regions,int x,int y,int w,int h) {
		Rectangle r = new Rectangle();		
		Rectangle rect = new Rectangle(x,y,w,h);
		JrCaseDisplayer cdisp = book.getCaseDisplayer();
		displayer.apply(rect,regions,book.getCasePerColumn(),book.getCaseDisplayer());
		for(int i = 0; i < JrPageRegionName.PAGE_REGION_CASEMAX; i++) {
			r = regions.get(JrPageRegionName.PAGE_REGION_CASE1+i);
			if (r.width > 0)
				cdisp.apply(r,regions.getCaseRegions(i),book.isShowIndex());
		}		
	}
	
	public void printPage(JrDrawTools dt,JrPageRegions regions,int page) {
		if (book != null) {
			dt.setRegions(regions.getCaseRegions(0));
			dt.adjustFonts(book.getCaseDisplayer());
			dt.adjustPens();
			book.print(dt,regions,page);
		}		
	}
	
	public int getPageCount() {
		return (book != null)? book.getPageCountForPrinting() : 0;
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
}
