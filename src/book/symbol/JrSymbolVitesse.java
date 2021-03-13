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
public class JrSymbolVitesse extends JrSymbol {
	public JrSymbolVitesse(int vitesse) {
		super(vitesse);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {
		dt.selectColor(Color.WHITE);
		dt.drawCircle(x,y,w,h,true);
		dt.selectDefinedPen(JrPenName.PEN_CONTOUR_PANNEAU,true);
		dt.drawCircle(x,y,w,h,false);
		dt.selectColor(Color.BLACK);
		switch(getName()) {
		case JrSymbolName.SYMBOL_10MAX  : dt.drawText("10",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_30MAX  : dt.drawText("30",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_50MAX  : dt.drawText("50",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_70MAX  : dt.drawText("70",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_90MAX  : dt.drawText("90",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_110MAX : dt.drawText("110",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		case JrSymbolName.SYMBOL_130MAX : dt.drawText("130",x,y,w,h,JrRegionFontName.REGION_FONT_SYMBOL,1); break;
		default : break;
		}		
	}	
}
