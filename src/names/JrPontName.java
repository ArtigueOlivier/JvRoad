/*
 * Created on 23 févr. 2006
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
public class JrPontName {
	public final static int PONT_NONE = 0;
	public final static int PONT_PATH_V = 1;
	public final static int PONT_PATH_H = 2;
	public final static int PONT_ROAD_V = 3;
	public final static int PONT_ROAD_H = 4;
	public final static int PONT_NATIONAL_V = 5;
	public final static int PONT_NATIONAL_H = 6;
	public final static int PONT_TRAIN_V = 7;
	public final static int PONT_TRAIN_H = 8;
	public final static int PONT_RIVER_V = 9;
	public final static int PONT_RIVER_H = 10;
	public final static int PONT_SENTIER_V = 11;
	public final static int PONT_SENTIER_H = 12;
	public final static int PONT_DOUBLEROAD_V = 13;
	public final static int PONT_DOUBLEROAD_H = 14;
public final static int PONT_COUNT = 15;
	
	public static int GetOrder(int ndx) {
		switch(ndx) {
		case  1 : return PONT_SENTIER_V;
		case  2 : return PONT_SENTIER_H;
		case  3 : return PONT_PATH_V;
		case  4 : return PONT_PATH_H;
		case  5 : return PONT_ROAD_V;
		case  6 : return PONT_ROAD_H;
		case  7 : return PONT_NATIONAL_V;
		case  8 : return PONT_NATIONAL_H;
		case  9 : return PONT_DOUBLEROAD_V;
		case 10 : return PONT_DOUBLEROAD_H;
		case 11 : return PONT_TRAIN_V;
		case 12 : return PONT_TRAIN_H;
		case 13 : return PONT_RIVER_V;
		case 14 : return PONT_RIVER_H;
		default: break;
		}
        return PONT_NONE;	
	}
    public static int GetFirst() {
    	return PONT_NONE;
    }
    public static int GetPrevious(int ndx) {
		switch(ndx) {
		case PONT_SENTIER_V    : return PONT_NONE;
		case PONT_SENTIER_H    : return PONT_SENTIER_V;
		case PONT_PATH_V       : return PONT_SENTIER_H;
		case PONT_PATH_H       : return PONT_PATH_V;
		case PONT_ROAD_V       : return PONT_PATH_H;
		case PONT_ROAD_H       : return PONT_ROAD_V;
		case PONT_NATIONAL_V   : return PONT_ROAD_H;
		case PONT_NATIONAL_H   : return PONT_NATIONAL_V;
		case PONT_DOUBLEROAD_V : return PONT_NATIONAL_H;
		case PONT_DOUBLEROAD_H : return PONT_DOUBLEROAD_V;
		case PONT_TRAIN_V      : return PONT_DOUBLEROAD_H;
		case PONT_TRAIN_H      : return PONT_TRAIN_V; 
		case PONT_RIVER_V      : return PONT_TRAIN_H;
		case PONT_RIVER_H      : return PONT_RIVER_V; 
		default: break;
		}
        return PONT_RIVER_H;	
    }
    public static int GetNext(int ndx) {
		switch(ndx) {
		case PONT_NONE         : return PONT_SENTIER_V;
		case PONT_SENTIER_V    : return PONT_SENTIER_H;
		case PONT_SENTIER_H    : return PONT_PATH_V;
		case PONT_PATH_V       : return PONT_PATH_H;
		case PONT_PATH_H       : return PONT_ROAD_V;
		case PONT_ROAD_V       : return PONT_ROAD_H; 
		case PONT_ROAD_H       : return PONT_NATIONAL_V;
		case PONT_NATIONAL_V   : return PONT_NATIONAL_H;
		case PONT_NATIONAL_H   : return PONT_DOUBLEROAD_V;
		case PONT_DOUBLEROAD_V : return PONT_DOUBLEROAD_H; 
		case PONT_DOUBLEROAD_H : return PONT_TRAIN_V;
		case PONT_TRAIN_V      : return PONT_TRAIN_H;
		case PONT_TRAIN_H      : return PONT_RIVER_V;
		case PONT_RIVER_V      : return PONT_RIVER_H;
		default: break;
		}
        return PONT_NONE;	
    }
	public static int GetLast() {
		return PONT_TRAIN_H;
	}
	public static boolean IsVertical(int ndx) {
		switch(ndx) {
		case PONT_SENTIER_V :
		case PONT_PATH_V :
		case PONT_ROAD_V :
		case PONT_NATIONAL_V : 
		case PONT_DOUBLEROAD_V :
		case PONT_TRAIN_V    :	
		case PONT_RIVER_V : return true;
		default: break;
		}
        return false;	
	}
    public static int toTrait(int ndx) {
		switch(ndx) {
		case PONT_SENTIER_V : 
		case PONT_SENTIER_H : return JrTraitName.TRAIT_SENTIER;
		case PONT_ROAD_V : 
		case PONT_ROAD_H : return JrTraitName.TRAIT_ROAD;
		case PONT_NATIONAL_V :
		case PONT_NATIONAL_H : return JrTraitName.TRAIT_NATIONAL;
		case PONT_DOUBLEROAD_V : 
		case PONT_DOUBLEROAD_H : return JrTraitName.TRAIT_DOUBLEROAD;
		case PONT_TRAIN_V    : 
		case PONT_TRAIN_H    : return JrTraitName.TRAIT_TRAIN; 
		case PONT_RIVER_V    : 
		case PONT_RIVER_H    : return JrTraitName.TRAIT_RIVER; 
		default: break;
		}
        return JrTraitName.TRAIT_PATH;	
    }

}
