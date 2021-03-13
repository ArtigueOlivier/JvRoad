/*
 * Created on 15 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package names;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSymbolName {
	public final static int SYMBOL_NOTHING = 0;
	public final static int SYMBOL_DANGER = 1;
	public final static int SYMBOL_SAIGNEE = 2;
	public final static int SYMBOL_BOSSE = 3;
	public final static int SYMBOL_SILENCE = 4;
	public final static int SYMBOL_CHEVAUX = 5;
	public final static int SYMBOL_10MAX = 6;
	public final static int SYMBOL_30MAX = 7;
	public final static int SYMBOL_50MAX = 8;
	public final static int SYMBOL_70MAX = 9;
	public final static int SYMBOL_90MAX = 10;
	public final static int SYMBOL_110MAX = 11;
	public final static int SYMBOL_130MAX = 12;
	public final static int SYMBOL_4X4 = 13;
	public final static int SYMBOL_DOWN = 14;
	public final static int SYMBOL_UP = 15;
	public final static int SYMBOL_STOP = 16;
	public final static int SYMBOL_FIM_ATTENTION = 17;
	public final static int SYMBOL_FIM_DANGER = 18;
	public final static int SYMBOL_FIM_GROSDANGER = 19;
	public final static int SYMBOL_FIM_PHOTO = 20;
	public final static int SYMBOL_FIM_VILLAGE = 21;
	public final static int SYMBOL_FIM_DEBUTZONE = 22;
	public final static int SYMBOL_FIM_FINZONE = 23;
	public final static int SYMBOL_FIM_AD = 24;
	public final static int SYMBOL_FIM_AG = 25;
	public final static int SYMBOL_FIM_D = 26;
	public final static int SYMBOL_FIM_G = 27;
	public final static int SYMBOL_FIM_GD = 28;
	public final static int SYMBOL_FIM_DG = 29;
	public final static int SYMBOL_FIM_EMP = 30;
	public final static int SYMBOL_FIM_DEF = 31;
	public final static int SYMBOL_FIM_ORN = 32;
	public final static int SYMBOL_FIM_SER = 33;
	public final static int SYMBOL_FIM_HP = 34;
	public final static int SYMBOL_FIM_NOHP = 35;
	public final static int SYMBOL_FIM_OUED = 36;
	public final static int SYMBOL_FIM_E3 = 37;
	public final static int SYMBOL_FIM_DANS = 38;
	public final static int SYMBOL_FIM_NBX = 39;
	public final static int SYMBOL_FIM_RLT = 40;
	public final static int SYMBOL_FIM_PP = 41;
	public final static int SYMBOL_FIM_TD = 42;
	public final static int SYMBOL_FIM_TDSPP = 43;
	public final static int SYMBOL_FIM_TDSRP = 44;
	public final static int SYMBOL_FIM_PPARA = 45;
	public final static int SYMBOL_FIM_P = 46;
	public final static int SYMBOL_FIM_MVS = 47;
	public final static int SYMBOL_FIM_IMP = 48;
	public final static int SYMBOL_COUNT = 49;
	
	public static String GetSymbolText(int name) {
		switch(name) {
	  	case SYMBOL_DANGER 			: return "SymbolDanger";
		case SYMBOL_SAIGNEE			: return "SymbolSaignee";
		case SYMBOL_BOSSE  			: return "SymbolBosse";
		case SYMBOL_SILENCE			: return "SymbolSilence";
		case SYMBOL_CHEVAUX			: return "SymbolChevaux";
		case SYMBOL_10MAX  			: return "Symbol10Max";
		case SYMBOL_30MAX  			: return "Symbol30Max";
		case SYMBOL_50MAX  			: return "Symbol50Max";
		case SYMBOL_70MAX  			: return "Symbol70Max";
		case SYMBOL_90MAX  			: return "Symbol90Max";
		case SYMBOL_110MAX 			: return "Symbol110Max";
		case SYMBOL_130MAX 			: return "Symbol130Max";
		case SYMBOL_4X4    			: return "Symbol4X4";
		case SYMBOL_DOWN   			: return "SymbolDown";
		case SYMBOL_UP     			: return "SymbolUp";
		case SYMBOL_STOP   			: return "SymbolStop";
		case SYMBOL_FIM_ATTENTION  	: return "SymbolFimAttention";
		case SYMBOL_FIM_DANGER     	: return "SymbolFimDanger";
		case SYMBOL_FIM_GROSDANGER 	: return "SymbolFimGrosDanger";
		case SYMBOL_FIM_PHOTO      	: return "SymbolFimPhoto";
		case SYMBOL_FIM_VILLAGE    	: return "SymbolFimVillage";
		case SYMBOL_FIM_DEBUTZONE   : return "SymbolFimDebutZone";
		case SYMBOL_FIM_FINZONE     : return "SymbolFimFinZone";
		case SYMBOL_FIM_AD     		: return "SymbolFimAD";
		case SYMBOL_FIM_AG     		: return "SymbolFimAG";
		case SYMBOL_FIM_D     		: return "SymbolFimD";
		case SYMBOL_FIM_G     		: return "SymbolFimG";
		case SYMBOL_FIM_GD    		: return "SymbolFimGD";
		case SYMBOL_FIM_DG    		: return "SymbolFimDG";
		case SYMBOL_FIM_EMP    		: return "SymbolFimEMP";
		case SYMBOL_FIM_DEF     	: return "SymbolFimDEF";
		case SYMBOL_FIM_ORN     	: return "SymbolFimORN";
		case SYMBOL_FIM_SER     	: return "SymbolFimSER";
		case SYMBOL_FIM_HP     		: return "SymbolFimHP";
		case SYMBOL_FIM_NOHP     	: return "SymbolFimNOHP";
		case SYMBOL_FIM_OUED     	: return "SymbolFimOUED";
		case SYMBOL_FIM_E3     		: return "SymbolFimE3";
		case SYMBOL_FIM_DANS     	: return "SymbolFimDANS";
		case SYMBOL_FIM_NBX     	: return "SymbolFimNBX";
		case SYMBOL_FIM_RLT     	: return "SymbolFimRLT";
		case SYMBOL_FIM_PP     		: return "SymbolFimPP";
		case SYMBOL_FIM_TD     		: return "SymbolFimTD";
		case SYMBOL_FIM_TDSPP     	: return "SymbolFimTDSPP";
		case SYMBOL_FIM_TDSRP     	: return "SymbolFimTDSRP";
		case SYMBOL_FIM_PPARA     	: return "SymbolFimPPARA";
		case SYMBOL_FIM_P     		: return "SymbolFimP";
		case SYMBOL_FIM_MVS     	: return "SymbolFimMVS";
		case SYMBOL_FIM_IMP     	: return "SymbolFimIMP";
		default : break;
		}
	  	return "SymbolNone";
	}	
}
