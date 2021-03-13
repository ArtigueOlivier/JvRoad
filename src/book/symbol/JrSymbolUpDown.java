/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.symbol;

import java.awt.Color;

import tools.JrDrawTools;
import names.JrColorName;
import names.JrPenName;
import names.JrSymbolName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbolUpDown extends JrSymbol {
	public JrSymbolUpDown(boolean up) {
		super((up)? JrSymbolName.SYMBOL_UP : JrSymbolName.SYMBOL_DOWN);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {
		dt.selectColor(JrColorName.COLOR_DSS_OBSTACLE_SYMBOL);
		float ox = x + (w / 2.0f);
		float oy = y + (h / 2.0f);		
		float pa = Math.min(w,h) / 2.0f;
		float pas = pa / 5.0f;
		float pas3 = pas * 3.0f;
				
		if (getName() == JrSymbolName.SYMBOL_UP) {
			dt.drawTriangle(ox-pa,oy+pa,ox+pa,oy-pa,ox+pa,oy+pa,true);
			dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
			dt.selectColor(Color.BLACK);
			dt.drawTriangle(ox-pa,oy+pa,ox+pa,oy-pa,ox+pa,oy+pa,false);
			dt.drawLine(ox-pas3-pas,oy+pas,ox,oy-pas3);
			dt.drawTriangle(ox-pas,oy-pas3-pas,ox+pas,oy-pas3-pas,ox+pas,oy-pas-pas,true);
		}
		else {
			dt.drawTriangle(ox-pa,oy-pa,ox-pa,oy+pa,ox+pa,oy+pa,true);
			dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
			dt.selectColor(Color.BLACK);
			dt.drawTriangle(ox-pa,oy-pa,ox-pa,oy+pa,ox+pa,oy+pa,false);
			dt.drawLine(ox+pas3,oy,ox-pas,oy-pas3-pas);
			dt.drawTriangle(ox+pas3+pas,oy-pas,ox+pas3+pas,oy+pas,ox+pas+pas,oy+pas,true);
		}
	}
}
