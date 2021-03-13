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
public class JrSymbolSilence extends JrSymbol {
	public JrSymbolSilence() {
		super(JrSymbolName.SYMBOL_SILENCE);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {	
		float ox = x + (w / 2.0f);
		float oy = y + (h / 2.0f);		
		float pa = Math.min(w,h) / 2.0f;
		float pas = Math.min(w,h) / 10.0f;
		float pas2 = pas / 2.0f;
		float ptx[] = new float [10];
		float pty[] = new float [10];

		ptx[0] = -5; pty[0] = -3; ptx[1] = -3; pty[1] = -1;
		ptx[2] =  1; pty[2] =  0; ptx[3] =  4; pty[3] =  0;
		ptx[4] =  5; pty[4] = -1; ptx[5] =  5; pty[5] =  2;
		ptx[6] =  4; pty[6] =  1; ptx[7] = -3; pty[7] =  1;
		ptx[8] = -5; pty[8] =  3; ptx[9] = -5; pty[9] = -3;
		
		for(int i = 0; i < 10; i++) {
			ptx[i] = (ptx[i] * pas) + ox;
		    pty[i] = (pty[i] * pas) + oy;
		}
		dt.selectColor(JrColorName.COLOR_DSS_TROMPETTE);
		dt.drawPolygon(ptx,pty,10,true);
		dt.drawRectangle(ox -(3*pas),oy+pas,pas,2*pas,true);
		dt.drawRectangle(ox +(2*pas),oy+pas,pas,2*pas,true);
		dt.drawRectangle(ox -(2*pas),oy+(2*pas),4*pas,pas,true);
		
		dt.selectColor(JrColorName.COLOR_DSS_POIGNET_TROMPETTE);
		dt.drawRectangle(ox-pas,oy+(2*pas),2*pas,pas,true);
		dt.selectDefinedPen(JrPenName.PEN_CONTOUR_PANNEAU,true);
		dt.drawLine(ox-w,oy-w,ox+w,oy+w);
		dt.drawLine(ox+w,oy-w,ox-w,oy+w);
		dt.selectColor(Color.BLACK);
	}
	
}
