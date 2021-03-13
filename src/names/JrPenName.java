/*
 * Created on 22 janv. 2005
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
public class JrPenName {
	public final static int PEN_DEFAULT = 0;
	public final static int PEN_CONTOUR_PANNEAU = 1;
	public final static int PEN_CONSEIL_PANNEAU = 2;
	public final static int PEN_CADRE_NORMAL = 3;
	public final static int PEN_CADRE_EPAIS = 4;
	public final static int PEN_PATH_BLACK = 5;
	public final static int PEN_PATH_BLUE = 6;
	public final static int PEN_SENTIER_BLACK = 7;
	public final static int PEN_SENTIER_BLUE = 8;
	public final static int PEN_ROAD_BLACK = 9;
	public final static int PEN_ROAD_BLUE = 10;
	public final static int PEN_ROAD_WHITE = 11;
	public final static int PEN_NATIONAL_BLACK = 12;
	public final static int PEN_NATIONAL_BLUE = 13;
	public final static int PEN_NATIONAL_WHITE = 14;
	public final static int PEN_MAP_BLACK = 15;
	public final static int PEN_MAP_BLUE = 16;
	public final static int PEN_MAP_WHITE = 17;
	public final static int PEN_MAP_LIGHTGRAY = 18;
	public final static int PEN_MAP_DARKGRAY = 19;
	public final static int PEN_MAP_GREEN = 20;
	public final static int PEN_MAP_RED = 21;
	public final static int PEN_MAP_ORANGE = 22;	
	public final static int PEN_MAP_EXTRAGRAY = 23;
	public final static int PEN_TRAIN_BLACK = 24;
	public final static int PEN_RIVER_BLUE = 25;
	public final static int PEN_COUNT = 26;
	
	public static float GetWidth(int name,int scale) {
		float sc = ((float)scale) / 100.0f;
		switch(name) {
		case PEN_CONTOUR_PANNEAU : 
		case PEN_CONSEIL_PANNEAU : return 0.501111f;
		case PEN_PATH_BLACK : 
		case PEN_PATH_BLUE : return 0.751667f * sc;
		case PEN_ROAD_BLACK : 
		case PEN_ROAD_BLUE : return 2.225f * sc;
		case PEN_ROAD_WHITE : return 1.252778f * sc;
		case PEN_TRAIN_BLACK :
		case PEN_NATIONAL_BLACK : 
		case PEN_NATIONAL_BLUE : return 4.51f * sc;
		case PEN_RIVER_BLUE :
		case PEN_NATIONAL_WHITE : return 3.507778f * sc;
		case PEN_CADRE_EPAIS : return 1.0f;
		case PEN_CADRE_NORMAL : return 0.4221f;
	    case PEN_SENTIER_BLACK : 
	    case PEN_SENTIER_BLUE : return 0.2846f * sc;
		default : break;
		}
		return 0.2846f;
	}
	
	public static boolean IsDashed(int name) {
		return ((name == PEN_SENTIER_BLACK) || (name == PEN_SENTIER_BLUE))? true : false;
	}
	public static boolean IsDashedTrain(int name) {
		return (name == PEN_TRAIN_BLACK)? true : false;
	}
}


