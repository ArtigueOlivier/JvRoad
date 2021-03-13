/*
 * Created on Dec 22, 2004
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
public class JrTraitName {
	public final static int TRAIT_NONE = 0;
	public final static int TRAIT_PATH = 1;
	public final static int TRAIT_PATH_DEST = 2;
	public final static int TRAIT_PATH_DESTINV = 3;
	public final static int TRAIT_PATH_SRC = 4;
	public final static int TRAIT_PATH_SRCINV = 5;
	public final static int TRAIT_ROAD = 6;
	public final static int TRAIT_ROAD_DEST = 7;
	public final static int TRAIT_ROAD_DESTINV = 8;
	public final static int TRAIT_ROAD_SRC = 9;
	public final static int TRAIT_ROAD_SRCINV = 10;
	public final static int TRAIT_SENTIER = 11;
	public final static int TRAIT_NATIONAL = 12;
	public final static int TRAIT_NATIONAL_DEST = 13;
	public final static int TRAIT_NATIONAL_DESTINV = 14;
	public final static int TRAIT_NATIONAL_SRC = 15;
	public final static int TRAIT_NATIONAL_SRCINV = 16;
	public final static int TRAIT_TRAIN = 17;
	public final static int TRAIT_RIVER = 18;
	public final static int TRAIT_SENTIER_DEST = 19;
	public final static int TRAIT_SENTIER_DESTINV = 20;
	public final static int TRAIT_SENTIER_SRC = 21;
	public final static int TRAIT_SENTIER_SRCINV = 22;
	public final static int TRAIT_DOUBLEROAD = 23;
	public final static int TRAIT_DOUBLEROAD_DEST = 24;
	public final static int TRAIT_DOUBLEROAD_DESTINV = 25;
	public final static int TRAIT_DOUBLEROAD_SRC = 26;
	public final static int TRAIT_DOUBLEROAD_SRCINV = 27;
	public final static int TRAIT_COUNT = 28;
	
	public static boolean IsClassChemin(int trait) {
		switch(trait) {
		case TRAIT_NONE :
		case TRAIT_PATH :
		case TRAIT_PATH_DEST :
		case TRAIT_PATH_DESTINV :
		case TRAIT_PATH_SRC :
		case TRAIT_PATH_SRCINV :
		case TRAIT_SENTIER :
		case TRAIT_SENTIER_DEST :
		case TRAIT_SENTIER_DESTINV :
		case TRAIT_SENTIER_SRC :
		case TRAIT_SENTIER_SRCINV :
		case TRAIT_TRAIN : 
		case TRAIT_RIVER : return true;
		default : break;
		}
		return false;
	}
	
	public static boolean IsClassRoute(int trait) {
		switch(trait) {
		case TRAIT_NONE :
		case TRAIT_ROAD :
		case TRAIT_ROAD_DEST :
		case TRAIT_ROAD_DESTINV :
		case TRAIT_ROAD_SRC :
		case TRAIT_ROAD_SRCINV : return true;
		default : break;
		}
		return false;
	}
	
	public static boolean IsClassNationale(int trait) {
		switch(trait) {
		case TRAIT_NONE :
		case TRAIT_NATIONAL :
		case TRAIT_NATIONAL_DEST :
		case TRAIT_NATIONAL_DESTINV :
		case TRAIT_NATIONAL_SRC :
		case TRAIT_NATIONAL_SRCINV : return true;
		case TRAIT_DOUBLEROAD :
		case TRAIT_DOUBLEROAD_DEST :
		case TRAIT_DOUBLEROAD_DESTINV :
		case TRAIT_DOUBLEROAD_SRC :
		case TRAIT_DOUBLEROAD_SRCINV : return true;
		default : break;
		}
		return false;
	}
	
	public static boolean IsAnOrigin(int trait) {
		switch(trait) {
		case TRAIT_SENTIER_SRC :
		case TRAIT_SENTIER_SRCINV :
		case TRAIT_PATH_SRC :
		case TRAIT_PATH_SRCINV :
		case TRAIT_ROAD_SRC :
		case TRAIT_ROAD_SRCINV :
		case TRAIT_NATIONAL_SRC :
		case TRAIT_NATIONAL_SRCINV :
		case TRAIT_DOUBLEROAD_SRC :
		case TRAIT_DOUBLEROAD_SRCINV :
			return true;
		default : break;
		}
		return false;
	}

	public static boolean IsAnDestination(int trait) {
		switch(trait) {
		case TRAIT_SENTIER_DEST :
		case TRAIT_SENTIER_DESTINV :
		case TRAIT_PATH_DEST :
		case TRAIT_PATH_DESTINV :
		case TRAIT_ROAD_DEST :
		case TRAIT_ROAD_DESTINV :
		case TRAIT_NATIONAL_DEST :
		case TRAIT_NATIONAL_DESTINV :
		case TRAIT_DOUBLEROAD_DEST :
		case TRAIT_DOUBLEROAD_DESTINV :
			return true;
		default : break;
		}
		return false;
	}
}
