/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookPreview extends JrBookAbstractPrintable {

	public JrBookPreview(JrBook bk) {
		super(bk);
	}
		
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page >= getPageCount())
			return Printable.NO_SUCH_PAGE;
		int pw = (int)(pf.getWidth());
		int ph = (int)(pf.getHeight());
		int x,y,w,h; 
		
		if ((pw != refWidth) || (ph != refHeight)) {
			refWidth = pw;
			refHeight = ph;
			if (displayer.hasTwoPages()) {
				computeRegions(regions1,0,0,(pw-10)/2,ph);				
				computeRegions(regions2,((pw-10)/2)+10,0,(pw-10)/2,ph);				
			}
			else
				computeRegions(regions1,0,0,pw,ph);
		}
		dt.setDeviceGraphic(g);
		
		
		if (displayer.hasTwoPages()) {
			printPage(dt,regions1,(page * 2));
			printPage(dt,regions2,(page * 2) + 1);
		}
		else
			printPage(dt,regions1,page);
		return Printable.PAGE_EXISTS;
	}
	
}
