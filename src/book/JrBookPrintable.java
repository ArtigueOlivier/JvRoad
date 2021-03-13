/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookPrintable extends JrBookAbstractPrintable {
	public JrBookPrintable(JrBook bk) {
		super(bk);
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page >= getPageCount())
			return Printable.NO_SUCH_PAGE;
		int pw = (int)(pf.getImageableWidth());
		int ph = (int)(pf.getImageableHeight());
		int x,y,w,h; 
		Graphics2D g2 = (Graphics2D)g; 
		g2.translate(pf.getImageableX(),pf.getImageableY());
		if ((pw != refWidth) || (ph != refHeight)) {
			refWidth = pw;
			refHeight = ph;
			if (displayer.hasTwoPages()) {
				computeRegions(regions1,5,5,(refWidth-20)/2,refHeight-10);				
				computeRegions(regions2,(((refWidth-20)/2)+10),5,((refWidth-20)/2),refHeight-10);				
			}
			else
				computeRegions(regions1,5,5,refWidth-10,refHeight-10);
		}
		dt.setDeviceGraphic(g2);
		dt.selectColor(Color.WHITE);
		dt.drawRectangle(0,0,refWidth,refHeight,true);
		
		if (displayer.hasTwoPages()) {
			printPage(dt,regions1,(page * 2));
			printPage(dt,regions2,(page * 2) + 1);
		}
		else
			printPage(dt,regions1,page);
		return Printable.PAGE_EXISTS;
	}
}
