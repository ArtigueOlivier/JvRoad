/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.symbol;

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
public class JrSymbolStop extends JrSymbol {
	public JrSymbolStop() {
		super(JrSymbolName.SYMBOL_STOP);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
		float ox = x + (w / 2.0f);
		float oy = y + (h / 2.0f);		
		float pa = Math.min(w,h) / 2.0f;
		float pa2 = pa / 2;
		float px[] = { ox-pa2,ox+pa2,ox+pa,ox+pa,ox+pa2,ox-pa2,ox-pa,ox-pa,ox-pa2 };
		float py[] = { oy-pa,oy-pa,oy-pa2,oy+pa2,oy+pa,oy+pa,oy+pa2,oy-pa2,oy-pa };

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,false);
		dt.drawPolygon(px,py,9,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,true);
		dt.drawText("Stop",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
	}
	
}
