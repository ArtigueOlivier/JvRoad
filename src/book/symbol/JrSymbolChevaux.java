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
import names.JrSymbolName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbolChevaux extends JrSymbol {
	public JrSymbolChevaux() {
		super(JrSymbolName.SYMBOL_CHEVAUX);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
		float ox = x + (w / 2.0f);;
		float oy = y + (h / 2.0f);		
		float pas = Math.min(w,h) / 10.0f;
		float pas2 = pas / 2.0f;
		
		dt.selectColor(JrColorName.COLOR_DSS_CHEVAL);
		dt.drawEllipse(ox-(5*pas),oy-(5*pas),10*pas,10*pas,true);

		dt.selectColor(JrColorName.COLOR_BG_SYMBOLES);
		dt.drawEllipse(ox-(3*pas),oy-(3*pas),6*pas,7*pas,true);

		dt.drawRectangle(ox-(2*pas),oy+pas,4*pas,4*pas,true);
		dt.drawRectangle(ox-(4*pas),oy+(4*pas),8*pas,2*pas,true);

		dt.drawEllipse(ox+(4*pas)-pas2,oy,pas,pas,true);
		dt.drawEllipse(ox+(2*pas),oy-(3*pas)-pas2,pas,pas,true);
		dt.drawEllipse(ox+(3*pas),oy-(2*pas),pas,pas,true);

		dt.drawEllipse(ox-(4*pas)-pas2,oy,pas,pas,true);
		dt.drawEllipse(ox-(3*pas),oy-(3*pas)-pas2,pas,pas,true);
		dt.drawEllipse(ox-(4*pas),oy-(2*pas),pas,pas,true);
		dt.selectColor(Color.BLACK);
	}
	
}
