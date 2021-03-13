/*
 * Created on Jun 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.utils;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrDashTrain extends JrDashItem {
	public JrDashTrain(float w,float coef) {
		super(w,coef);
	}

	public float[] createDash(float coef) {
		float d[] = new float [2];
		d[0] = coef / 2.0f;
		d[1] = coef * 2.0f;
		return d;
	}
	
}
