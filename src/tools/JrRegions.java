/*
 * Created on Dec 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

import java.awt.Rectangle;

import names.JrRegionName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrRegions {
	private Rectangle regions[] = new Rectangle [JrRegionName.REGION_COUNT];
	private Rectangle reference = new Rectangle(0,0,0,0);
	private float mapOrigineX = 0.0f;
	private float mapOrigineY = 0.0f;
	private float mapPasX = 1.0f;
	private float mapPasY = 1.0f;
	private float mapHandle = 2.0f;
	
	public JrRegions() {
		int i;
		for(i = 0; i < JrRegionName.REGION_COUNT; i++)
			regions[i] = new Rectangle(0,0,0,0);
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
	public int whichRegion(int x,int y) {
		int i;
		for(i = 0; i < JrRegionName.REGION_COUNT; i++) {
			if (regions[i].contains(x,y) && (i != JrRegionName.REGION_SYMBOLS))
				return i;
		}
		return -1;
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
	
	public void set(JrRegions regs) {
		reference = (Rectangle)(regs.reference.clone());
		for(int i = 0; i < JrRegionName.REGION_COUNT; i++)
			regions[i] = (Rectangle)(regs.regions[i].clone());
	    mapOrigineX = regs.mapOrigineX;
	    mapOrigineY = regs.mapOrigineY;
	    mapPasX = regs.mapPasX;
	    mapPasY = regs.mapPasY;
	}
	
	public boolean hasBeenChanged(Rectangle rect) {
		if (reference.equals(rect))
			return false;
		reference = rect;
		return true;
	}
	
	public Rectangle getReference() {
		return reference;
	}
	
	public void setReference(Rectangle rect) {
		reference = (Rectangle)(rect.clone());
	}
	public void computeMapParameters() {
	    Rectangle rect = regions[JrRegionName.REGION_MAP];
	    mapOrigineX = (float)rect.getCenterX();
	    mapOrigineY = (float)rect.getCenterY();
	    mapPasX = ((float)rect.width)  / 12.0f;
	    mapPasY = ((float)rect.height) / 12.0f;
	    mapHandle = Math.max(2.0f,Math.min(mapPasX,mapPasY)/4.0f);
	}
	public float getMapOrigineX() {
		return mapOrigineX;
	}
	public float getMapOrigineY() {
		return mapOrigineY;
	}
	public float getMapHandle() {
		return mapHandle;
	}
	public float getMapPasX() {
		return mapPasX;
	}
	public float getMapPasY() {
		return mapPasY;
	}
}
