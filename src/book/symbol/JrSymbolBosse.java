/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.symbol;

import tools.JrDrawTools;
import names.JrColorName;
import names.JrSymbolName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbolBosse extends JrSymbol {
	public JrSymbolBosse() {
		super(JrSymbolName.SYMBOL_BOSSE);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
		dt.selectColor(JrColorName.COLOR_DSS_OBSTACLE_SYMBOL);
		float ox  = x + (w / 2.0f);
		float oy  = y + (h / 2.0f);
		float pas = Math.min(w,h) / 2.0f;
		float pa2 = Math.min(w,h) / 4.0f;
		dt.drawCircle(ox -pa2,oy - pa2,pas,pas,true);
		dt.drawRectangle(ox-pas,oy,pas * 2.0f, pas,true);
	}	
}
