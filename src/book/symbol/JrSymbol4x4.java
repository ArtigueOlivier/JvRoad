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
public class JrSymbol4x4 extends JrSymbol {
	public JrSymbol4x4() {
		super(JrSymbolName.SYMBOL_4X4);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
		dt.selectColor(Color.BLUE);
		dt.drawCircle(x,y,w,h,true);
		dt.selectColor(Color.WHITE);
		dt.drawText("4x4",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
	}
	
}
