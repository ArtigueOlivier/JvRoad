/*
 * Created on 2 déc. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrDistanceParam {
	private int distance = 0;
	private boolean approx = false;
	private boolean inconnuDistance = false;
	private boolean raz = false;

	public JrDistanceParam() {		
	}
	
	public JrDistanceParam(int dist,boolean app,boolean inc,boolean r) {
		distance = dist;
		approx = app;
		inconnuDistance = inc;
		raz = r;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public boolean isApproximativeDistance() {
		return approx;
	}
	
	public boolean isInconnuDistance() {
		return inconnuDistance;
	}
	
	public boolean isRaz() {
		return raz;
	}
}
