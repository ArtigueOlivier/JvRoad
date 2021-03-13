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
public class JrSymbolFim extends JrSymbol {
	public JrSymbolFim(int genre) {
		super(genre);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {	
		switch(getName()) {
		case JrSymbolName.SYMBOL_FIM_ATTENTION  	: drawOnlyBigText(dt,x,y,w,h,"!"); break;
		case JrSymbolName.SYMBOL_FIM_DANGER     	: drawOnlyBigText(dt,x,y,w,h,"!!"); break;
		case JrSymbolName.SYMBOL_FIM_GROSDANGER 	: drawOnlyBigText(dt,x,y,w,h,"!!!"); break;
		case JrSymbolName.SYMBOL_FIM_PHOTO      	: drawPanneauBigText(dt,x,y,w,h,"PH"); break;
		case JrSymbolName.SYMBOL_FIM_VILLAGE    	: drawPanneauBigText(dt,x,y,w,h,"V"); break;
		case JrSymbolName.SYMBOL_FIM_DEBUTZONE    	: drawPanneauBigText(dt,x,y,w,h,"DZ"); break;
		case JrSymbolName.SYMBOL_FIM_FINZONE    	: drawPanneauBigText(dt,x,y,w,h,"FZ"); break;
		case JrSymbolName.SYMBOL_FIM_AD     		: drawOnlyBigText(dt,x,y,w,h,"AD"); break;
		case JrSymbolName.SYMBOL_FIM_AG     		: drawOnlyBigText(dt,x,y,w,h,"AG"); break;
		case JrSymbolName.SYMBOL_FIM_D     			: drawOnlyBigText(dt,x,y,w,h,"D"); break;
		case JrSymbolName.SYMBOL_FIM_G     			: drawOnlyBigText(dt,x,y,w,h,"G"); break;
		case JrSymbolName.SYMBOL_FIM_GD    			: drawOnlyBigText(dt,x,y,w,h,"G/D"); break;
		case JrSymbolName.SYMBOL_FIM_DG    			: drawOnlyBigText(dt,x,y,w,h,"D/G"); break;
		case JrSymbolName.SYMBOL_FIM_EMP    		: drawOnlyText(dt,x,y,w,h,"EMP."); break;
		case JrSymbolName.SYMBOL_FIM_DEF     		: drawOnlyText(dt,x,y,w,h,"DEF"); break;
		case JrSymbolName.SYMBOL_FIM_ORN     		: drawOnlyText(dt,x,y,w,h,"ORN."); break;
		case JrSymbolName.SYMBOL_FIM_SER     		: drawOnlyText(dt,x,y,w,h,"SER."); break;
		case JrSymbolName.SYMBOL_FIM_HP     		: drawOnlyBigText(dt,x,y,w,h,"HP"); break;
		case JrSymbolName.SYMBOL_FIM_NOHP     		: drawOnlyBigTextBarre(dt,x,y,w,h,"HP"); break;
		case JrSymbolName.SYMBOL_FIM_OUED     		: drawOnlyText(dt,x,y,w,h,"OUED"); break;
		case JrSymbolName.SYMBOL_FIM_E3     		: drawOnlyBigText(dt,x,y,w,h,"E3"); break;
		case JrSymbolName.SYMBOL_FIM_DANS     		: drawOnlyText(dt,x,y,w,h,"DANS"); break;
		case JrSymbolName.SYMBOL_FIM_NBX     		: drawOnlyText(dt,x,y,w,h,"NBX"); break;
		case JrSymbolName.SYMBOL_FIM_RLT     		: drawOnlyText(dt,x,y,w,h,"RLT"); break;
		case JrSymbolName.SYMBOL_FIM_PP     		: drawOnlyBigText(dt,x,y,w,h,"PP"); break;
		case JrSymbolName.SYMBOL_FIM_TD     		: drawOnlyBigText(dt,x,y,w,h,"TD"); break;
		case JrSymbolName.SYMBOL_FIM_TDSPP     		: drawOnlySmallText(dt,x,y,w,h,"TDSPP"); break;
		case JrSymbolName.SYMBOL_FIM_TDSRP     		: drawOnlySmallText(dt,x,y,w,h,"TDSRP"); break;
		case JrSymbolName.SYMBOL_FIM_PPARA     		: drawOnlyBigText(dt,x,y,w,h,"P//"); break;
		case JrSymbolName.SYMBOL_FIM_P     			: drawOnlyBigText(dt,x,y,w,h,"P"); break;
		case JrSymbolName.SYMBOL_FIM_MVS     		: drawOnlyText(dt,x,y,w,h,"MVS"); break;
		case JrSymbolName.SYMBOL_FIM_IMP     		: drawOnlyBigText(dt,x,y,w,h,"IMP"); break;
		default: break;
		}
	}

	public void drawOnlySmallText(JrDrawTools dt,float x,float y,float w,float h,String txt) {	
		dt.selectColor(Color.BLACK);
		dt.drawText(txt,x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL_SMALL,1);
	}
	
	public void drawOnlyText(JrDrawTools dt,float x,float y,float w,float h,String txt) {	
		dt.selectColor(Color.BLACK);
		dt.drawText(txt,x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL,1);
	}
	
	public void drawOnlyBigText(JrDrawTools dt,float x,float y,float w,float h,String txt) {	
		dt.selectColor(Color.BLACK);
		dt.drawText(txt,x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL_BIG,1);
	}
	
	public void drawOnlyBigTextBarre(JrDrawTools dt,float x,float y,float w,float h,String txt) {	
		dt.selectDefinedPen(JrPenName.PEN_CONTOUR_PANNEAU,true);
		dt.selectColor(Color.BLACK);
		dt.drawText(txt,x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL_BIG,1);
		dt.drawLine(x,y+h,x+w,y);
	}

	public void drawPanneauBigText(JrDrawTools dt,float x,float y,float w,float h,String txt) {	
		dt.selectColor(Color.WHITE);
		dt.drawCircle(x,y,w,h,true);
		dt.selectDefinedPen(JrPenName.PEN_CONTOUR_PANNEAU,true);
		dt.selectColor(Color.BLACK);
		dt.drawCircle(x,y,w,h,false);
		dt.drawText(txt,x,y+(h / 10.0f),w,h * 0.9f,JrRegionFontName.REGION_FONT_SYMBOL_BIG,1);
	}
}
