/*
 * Created on Jan 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package names;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrObjName {
	public final static int OBJ_NOTHING = 0;
	public final static int OBJ_HOUSE = 1;
	public final static int OBJ_CROIX = 2;
	public final static int OBJ_EGLISE = 3;
	public final static int OBJ_MOSQUEE = 4;
	public final static int OBJ_HOPITAL = 5;
	public final static int OBJ_STATION = 6;
	public final static int OBJ_PARKING = 7;
	public final static int OBJ_STOP = 8;
	public final static int OBJ_CEDER = 9;
	public final static int OBJ_FEUX = 10;
	public final static int OBJ_ARBRE = 11;
	public final static int OBJ_SAPIN = 12;
	public final static int OBJ_VRAIL = 13;
	public final static int OBJ_HRAIL = 14;
	public final static int OBJ_VRIVER = 15;
	public final static int OBJ_HRIVER = 16;
	public final static int OBJ_CERCLE = 17;
	public final static int OBJ_ROND = 18;
	public final static int OBJ_HOUSEG = 19;
	public final static int OBJ_HBARRIERE = 20;
	public final static int OBJ_VBARRIERE = 21;
	public final static int OBJ_SENSINTER = 22;
	public final static int OBJ_CHAPELLE = 23;
	public final static int OBJ_CP = 24;
	public final static int OBJ_COUNT = 25;
	
	public static int GetOrder(int ndx) {
		switch(ndx) {
		case  0 : return OBJ_NOTHING;
		case  1 : return OBJ_HOUSE;
		case  2 : return OBJ_HOUSEG;
		case  3 : return OBJ_CROIX;
		case  4 : return OBJ_CHAPELLE;
		case  5 : return OBJ_EGLISE;
		case  6 : return OBJ_MOSQUEE;
		case  7 : return OBJ_HOPITAL;
		case  8 : return OBJ_STATION;
		case  9 : return OBJ_PARKING;
		case 10 : return OBJ_STOP;
		case 11 : return OBJ_CEDER;
		case 12 : return OBJ_SENSINTER;
		case 13 : return OBJ_FEUX;
		case 14 : return OBJ_ARBRE;
		case 15 : return OBJ_SAPIN;
		case 16 : return OBJ_VRAIL;
		case 17 : return OBJ_HRAIL;
		case 18 : return OBJ_VRIVER;
		case 19 : return OBJ_HRIVER;
		case 20 : return OBJ_CERCLE;
		case 21 : return OBJ_ROND;
		case 22 : return OBJ_HBARRIERE;
		case 23 : return OBJ_VBARRIERE;
		case 24 : return OBJ_CP;
		default : break;
		}
        return ndx;	
	}
    public static int GetFirst() {
    	return OBJ_NOTHING;
    }
    public static int GetPrevious(int ndx) {
		switch(ndx) {
		case OBJ_HOUSE     : return OBJ_NOTHING;
		case OBJ_HOUSEG    : return OBJ_HOUSE;
		case OBJ_CROIX     : return OBJ_HOUSEG;
		case OBJ_CHAPELLE  : return OBJ_CROIX;
		case OBJ_EGLISE    : return OBJ_CHAPELLE;
		case OBJ_MOSQUEE   : return OBJ_EGLISE;
		case OBJ_HOPITAL   : return OBJ_MOSQUEE;
		case OBJ_STATION   : return OBJ_HOPITAL;
		case OBJ_PARKING   : return OBJ_STATION;
		case OBJ_STOP      : return OBJ_PARKING;
		case OBJ_CEDER     : return OBJ_STOP;
		case OBJ_SENSINTER : return OBJ_CEDER;
		case OBJ_FEUX      : return OBJ_SENSINTER;
		case OBJ_ARBRE     : return OBJ_FEUX;
		case OBJ_SAPIN     : return OBJ_ARBRE;
		case OBJ_VRAIL     : return OBJ_SAPIN;
		case OBJ_HRAIL     : return OBJ_VRAIL;
		case OBJ_VRIVER    : return OBJ_HRAIL;
		case OBJ_HRIVER    : return OBJ_VRIVER;
		case OBJ_CERCLE    : return OBJ_HRIVER;
		case OBJ_ROND      : return OBJ_CERCLE;
		case OBJ_HBARRIERE : return OBJ_ROND;
		case OBJ_VBARRIERE : return OBJ_HBARRIERE;
		case OBJ_CP        : return OBJ_VBARRIERE; 
		default: break;
		}
        return OBJ_VBARRIERE;	
    }
    public static int GetNext(int ndx) {
		switch(ndx) {
		case OBJ_NOTHING   : return OBJ_HOUSE;
		case OBJ_HOUSE     : return OBJ_HOUSEG;
		case OBJ_HOUSEG    : return OBJ_CROIX;
		case OBJ_CROIX     : return OBJ_CHAPELLE; 
		case OBJ_CHAPELLE  : return OBJ_EGLISE;
		case OBJ_EGLISE    : return OBJ_MOSQUEE;
		case OBJ_MOSQUEE   : return OBJ_HOPITAL;
		case OBJ_HOPITAL   : return OBJ_STATION;
		case OBJ_STATION   : return OBJ_PARKING;
		case OBJ_PARKING   : return OBJ_STOP;
		case OBJ_STOP      : return OBJ_CEDER;
		case OBJ_CEDER     : return OBJ_SENSINTER; 
		case OBJ_SENSINTER : return OBJ_FEUX;
		case OBJ_FEUX      : return OBJ_ARBRE;
		case OBJ_ARBRE     : return OBJ_SAPIN;
		case OBJ_SAPIN     : return OBJ_VRAIL;
		case OBJ_VRAIL     : return OBJ_HRAIL;
		case OBJ_HRAIL     : return OBJ_VRIVER;
		case OBJ_VRIVER    : return OBJ_HRIVER;
		case OBJ_HRIVER    : return OBJ_CERCLE;
		case OBJ_CERCLE    : return OBJ_ROND;
		case OBJ_ROND      : return OBJ_HBARRIERE;
		case OBJ_HBARRIERE : return OBJ_VBARRIERE;
		case OBJ_VBARRIERE : return OBJ_CP;
		default: break;
		}
        return OBJ_NOTHING;	
    }
	public static int GetLast() {
		return OBJ_CP;
	}
}
