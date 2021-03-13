/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

import java.awt.Rectangle;

import names.JrPageRegionName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPageRegions {
	private Rectangle regions[] = new Rectangle [JrPageRegionName.PAGE_REGION_COUNT];
	private JrRegions caseRegions[] = new JrRegions [JrPageRegionName.PAGE_REGION_CASEMAX];
	
	public JrPageRegions() {
		int i;
		
		for(i = 0; i < JrPageRegionName.PAGE_REGION_COUNT; i++)
			regions[i] = new Rectangle(0,0,0,0);
		for(i = 0; i < JrPageRegionName.PAGE_REGION_CASEMAX; i++)
			caseRegions[i] = new JrRegions();
	}
	public Rectangle get(int name) {
		return regions[name];
	}
	public int x(int name) {
		return regions[name].x;
	}
	public int y(int name) {
		return regions[name].y;
	}
	public int width(int name) {
		return regions[name].width;
	}
	public int height(int name) {
		return regions[name].height;
	}
	public boolean isNull(int name) {
		return ((regions[name].width == 0) || (regions[name].height == 0))? true : false;
	}	
	
	public void set(int name,int x,int y,int w,int h) {
		regions[name] = new Rectangle(x,y,w,h);
	}
	
	public void set(int name,Rectangle rect) {
		regions[name] = rect;
	}	
	
	public void setCaseRegions(int name,JrRegions regs) {
		caseRegions[name].set(regs);
	}
	
	public JrRegions getCaseRegions(int name) {
		return caseRegions[name];
	}
}
