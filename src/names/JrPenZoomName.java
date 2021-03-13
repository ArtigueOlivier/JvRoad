/*
 * Created on 15 févr. 2005
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
public class JrPenZoomName {
	public static final int ZOOM_NORMAL = 0;
	public static final int ZOOM_BIG = 1;
	public static final int ZOOM_VERYBIG = 2;
	public static final int ZOOM_HUGE = 3;
	public static final int ZOOM_COUNT = 4;
	
	public static float GetZoomValue(int level) {
		switch(level) {
		case ZOOM_BIG     : return 2.0f;
		case ZOOM_VERYBIG : return 3.0f;
		case ZOOM_HUGE    : return 4.0f;
		default : break;
		}
		return 1.0f;
	}
}
