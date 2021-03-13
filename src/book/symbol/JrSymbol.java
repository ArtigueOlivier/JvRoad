/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.symbol;

import java.awt.Rectangle;
import java.awt.Shape;

import tools.JrDrawTools;
import names.JrSymbolName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbol {
	private int name = JrSymbolName.SYMBOL_NOTHING;
	
	public JrSymbol() {		
	}
	
	public JrSymbol(int name) {
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
	
	public void draw(JrDrawTools dt,int rectname) {		
		if (dt.isNullRegion(rectname) == false) {
			Rectangle rect = (Rectangle)(dt.getRegion(rectname).clone());
			draw(dt,rect);
		}
	}
	
	public void draw(JrDrawTools dt, Rectangle rect) {
		Shape oldClip = dt.setClip(rect.x,rect.y,rect.width,rect.height);
		float dx = ((float)rect.width) * 0.03f;
		float dy = ((float)rect.height) * 0.03f;
		float x = ((float)rect.x) + dx;
		float y = ((float)rect.y) + dx;
		float w = ((float)rect.width) - dx - dx;
		float h = ((float)rect.height) - dx - dx;
		draw(dt,x,y,w,h);
		dt.restoreClip(oldClip);	
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h) {		
	}
	
	public static JrSymbol Create(int name) {
		switch(name) {
		case JrSymbolName.SYMBOL_DANGER : return new JrSymbolDanger();
		case JrSymbolName.SYMBOL_SAIGNEE : return new JrSymbolSaignee();
		case JrSymbolName.SYMBOL_BOSSE : return new JrSymbolBosse();
		case JrSymbolName.SYMBOL_SILENCE : return new JrSymbolSilence();
		case JrSymbolName.SYMBOL_CHEVAUX : return new JrSymbolChevaux();
		case JrSymbolName.SYMBOL_10MAX : 
		case JrSymbolName.SYMBOL_30MAX : 
		case JrSymbolName.SYMBOL_50MAX : 
		case JrSymbolName.SYMBOL_70MAX : 
		case JrSymbolName.SYMBOL_90MAX : 
		case JrSymbolName.SYMBOL_110MAX : 
		case JrSymbolName.SYMBOL_130MAX : return new JrSymbolVitesse(name);
		case JrSymbolName.SYMBOL_4X4 : return new JrSymbol4x4();
		case JrSymbolName.SYMBOL_DOWN : return new JrSymbolUpDown(false);
		case JrSymbolName.SYMBOL_UP : return new JrSymbolUpDown(true);
		case JrSymbolName.SYMBOL_STOP : return new JrSymbolStop();
		case JrSymbolName.SYMBOL_FIM_ATTENTION  	: 
		case JrSymbolName.SYMBOL_FIM_DANGER     	: 
		case JrSymbolName.SYMBOL_FIM_GROSDANGER 	: 
		case JrSymbolName.SYMBOL_FIM_PHOTO      	: 
		case JrSymbolName.SYMBOL_FIM_VILLAGE    	: 
		case JrSymbolName.SYMBOL_FIM_DEBUTZONE    	: 
		case JrSymbolName.SYMBOL_FIM_FINZONE    	: 
		case JrSymbolName.SYMBOL_FIM_AD     		:
		case JrSymbolName.SYMBOL_FIM_AG     		:
		case JrSymbolName.SYMBOL_FIM_D     			:
		case JrSymbolName.SYMBOL_FIM_G     			:
		case JrSymbolName.SYMBOL_FIM_GD    			:
		case JrSymbolName.SYMBOL_FIM_DG    			:
		case JrSymbolName.SYMBOL_FIM_EMP    		:
		case JrSymbolName.SYMBOL_FIM_DEF     		:
		case JrSymbolName.SYMBOL_FIM_ORN     		:
		case JrSymbolName.SYMBOL_FIM_SER     		:
		case JrSymbolName.SYMBOL_FIM_HP     		:
		case JrSymbolName.SYMBOL_FIM_NOHP     		:
		case JrSymbolName.SYMBOL_FIM_OUED     		:
		case JrSymbolName.SYMBOL_FIM_E3     		:
		case JrSymbolName.SYMBOL_FIM_DANS     		:
		case JrSymbolName.SYMBOL_FIM_NBX     		:
		case JrSymbolName.SYMBOL_FIM_RLT     		:
		case JrSymbolName.SYMBOL_FIM_PP     		:
		case JrSymbolName.SYMBOL_FIM_TD     		:
		case JrSymbolName.SYMBOL_FIM_TDSPP     		:
		case JrSymbolName.SYMBOL_FIM_TDSRP     		:
		case JrSymbolName.SYMBOL_FIM_PPARA     		:
		case JrSymbolName.SYMBOL_FIM_P     			:
		case JrSymbolName.SYMBOL_FIM_MVS     		:
		case JrSymbolName.SYMBOL_FIM_IMP     		: return new JrSymbolFim(name);		
		default : break;
		}
		return new JrSymbol();
	}

	public static JrSymbol CreateVeryOld(int name) {
	    switch(name) {
	    case 5  : return Create(JrSymbolName.SYMBOL_50MAX);
	    case 6  : return Create(JrSymbolName.SYMBOL_CHEVAUX);
	    case 7  : return Create(JrSymbolName.SYMBOL_10MAX);
	    case 8  : return Create(JrSymbolName.SYMBOL_UP);
	    case 9  : return Create(JrSymbolName.SYMBOL_DOWN);
	    case 10 : return Create(JrSymbolName.SYMBOL_4X4);
	    case 11 : return Create(JrSymbolName.SYMBOL_30MAX);
	    case 12 : return Create(JrSymbolName.SYMBOL_50MAX);
	    case 13 : return Create(JrSymbolName.SYMBOL_70MAX);
	    case 14 : return Create(JrSymbolName.SYMBOL_90MAX);
	    case 15 : return Create(JrSymbolName.SYMBOL_110MAX);
	    case 16 : return Create(JrSymbolName.SYMBOL_130MAX);
	    default: break;
	    }
		return Create(name);
	}
}
