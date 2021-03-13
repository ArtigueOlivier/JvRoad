/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import book.symbol.JrSymbol;

import tools.JrDrawTools;

import names.JrPenName;
import names.JrSymbolName;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookLegende extends JrBookAbstractPrintable {
	private Rectangle [] regions = null;
	private Font fontText = null;
	
	public JrBookLegende(JrBook bk) {
		super(bk);
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page >= getPageCount())
			return Printable.NO_SUCH_PAGE;
		int pw = (int)(pf.getImageableWidth());
		int ph = (int)(pf.getImageableHeight());
		int x,y,w,h; 
		Graphics2D g2 = (Graphics2D)g; 
		dt.setDeviceGraphic(g2);
		g2.translate(pf.getImageableX(),pf.getImageableY());
		if ((pw != refWidth) || (ph != refHeight)) {
			refWidth = pw;
			refHeight = ph;
			computeRegions(regions1,5,5,refWidth-10,refHeight-10);
			computeRegions(5,5,refWidth-10,refHeight-10);
		}
		dt.selectColor(Color.WHITE);
		dt.drawRectangle(0,0,refWidth,refHeight,true);
		
		printPage(dt,regions);
		return Printable.PAGE_EXISTS;
	}
	
	public void computeRegions(int x,int y,int w,int h) {
		int he = h / 15;
		int hc = (h - he) / JrSymbolName.SYMBOL_COUNT;
		int ht = he + (hc * JrSymbolName.SYMBOL_COUNT);
		int wc = hc;
		regions = new Rectangle [3 + (2 * JrSymbolName.SYMBOL_COUNT)];
		regions[0] = new Rectangle(x,y,w,ht);
		regions[1] = new Rectangle(x,y,w,he);
		regions[2] = new Rectangle(x,y+he,wc,ht-he);
		for(int i = 0; i < JrSymbolName.SYMBOL_COUNT; i++) {
			regions[3+(2*i)] = new Rectangle(x,y+he+(i*hc),wc,hc);
			regions[4+(2*i)] = new Rectangle(x+wc,y+he+(i*hc),w-wc,hc);
		}
		fontText = dt.createFont(Math.min(he,(w-wc)/40),true);
	}
	
	public void printPage(JrDrawTools dt,Rectangle[] regs) {
		JrSymbol symb;
		dt.selectDefinedPen(JrPenName.PEN_CADRE_EPAIS,true);
		drawRectangle(dt,regs[0]);
		drawRectangle(dt,regs[1]);
		dt.selectDefinedPen(JrPenName.PEN_CADRE_NORMAL,true);
		drawRectangle(dt,regs[2]);
		dt.selectFont(fontText);
		dt.drawText(GetWord("TxtLegende"),regs[1],1);
		for(int i = 0; i < JrSymbolName.SYMBOL_COUNT; i++) {
			dt.selectDefinedPen(JrPenName.PEN_CADRE_NORMAL,true);
			drawRectangle(dt,regs[3+(2*i)]);
			drawRectangle(dt,regs[4+(2*i)]);
			dt.selectFont(fontText);
			dt.drawText(GetWord(JrSymbolName.GetSymbolText(i)),regs[4+(2*i)],0);
			symb = JrSymbol.Create(i);
			symb.draw(dt,regs[3+(2*i)]);
		}		
	}
	
	public void drawRectangle(JrDrawTools dt,Rectangle r) {
		dt.drawRectangle((float)r.x,(float)r.y,(float)r.width,(float)r.height,false);	
	}
}
