/*
 * Created on 24 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMath {
	public static int ArrondirDouble(double d) {
		return (((d) >= 0.) ? (int)(0.5+(d)) : -(int)(0.5 - (d)));
	}	
	
	public static String formatDistance(int distance) {
		distance /= 10;
		String str = (((distance % 100) < 10)? "0" : "") + Integer.toString(distance % 100);
		return Integer.toString(distance/100) + "." + str;		
	}
}
