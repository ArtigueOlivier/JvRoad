/*
 * Created on Jun 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.utils;

import java.awt.BasicStroke;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrDashItem {
	protected float dash[] = { 1.0f };
	private float width = 0.28346f;
	private BasicStroke dashedStroke = new BasicStroke(0.28346f,
			BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,1.0f, dash, 0.0f);	
	
	public JrDashItem(float w,float coef) {
		build(w,coef);
	}
	
	public float[] createDash(float coef) {
		float d[] = new float [1];
		d[0] = coef;
		return d;
	}
	
	public void build(float w,float coef) {
		dash = createDash(coef);
		width = w;
		dashedStroke = new BasicStroke(w,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,
				coef,dash,0.0f);		
	}
	
	public BasicStroke get() {
		return dashedStroke;
	}
}
