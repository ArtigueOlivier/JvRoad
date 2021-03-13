/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

import tools.JrDrawTools;
import tools.JrPageRegions;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookPrintWhite extends JrBookPrintable {
	public JrBookPrintWhite(JrBook bk) {
		super(bk);
	}
		
	public void printPage(JrDrawTools dt,JrPageRegions regions,int page) {
		if (book != null) {
			dt.setRegions(regions.getCaseRegions(0));
			dt.adjustFonts(book.getCaseDisplayer());
			dt.adjustPens();
			book.printWhite(dt,regions,page);
		}		
	}
	
}
