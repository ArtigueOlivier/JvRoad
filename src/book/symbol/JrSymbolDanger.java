/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.symbol;

import java.awt.Color;

import tools.JrDrawTools;
import names.JrPenName;
import names.JrRegionFontName;
import names.JrSymbolName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbolDanger extends JrSymbol {
	public JrSymbolDanger() {
		super(JrSymbolName.SYMBOL_DANGER);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
		dt.selectColor(Color.WHITE);
		dt.drawTriangle(x,y,w,h,true);
		dt.selectDefinedPen(JrPenName.PEN_CONTOUR_PANNEAU,true);
		dt.drawTriangle(x,y,w,h,false);
		dt.selectColor(Color.BLACK);
		dt.drawText("!",x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL_BIG,1);
	}
	
}
