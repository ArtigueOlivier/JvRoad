/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import names.JrCaseDisplayerName;
import names.JrRegionName;

import java.awt.Rectangle;

import tools.JrRegions;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseDisplayerRallye extends JrCaseDisplayer {
	public int getName() {
		return JrCaseDisplayerName.CASE_DISPLAYER_RALLYE;
	}
	
	public boolean hasTotalColumn() {
		return false;
	}
	public boolean isDrawFrameTotal() {
		return true;
	}
	public boolean isTotalPartielSameColumn() {
		return true;
	}
	public boolean isFullText() {
		return true;
	}	
	public void apply(Rectangle rect,JrRegions reg,boolean showIndex) {		
		int x = rect.x;
		int y = rect.y;
		int pasx = rect.width / 5;
		int pasy = rect.height / ((showIndex)? 5 : 4);
		int drw = (rect.width - pasx) / 4;
		int drh = rect.height / 5;
		int dry = y + rect.height - drh;
		int wmap = (rect.width - pasx) / 2;
		int wtxt = rect.width - wmap - pasx;

		reg.set(JrRegionName.REGION_RAZ,x,y,pasx,pasy);		
		reg.set(JrRegionName.REGION_PARTIEL,x,y+pasy,pasx,pasy);
		reg.set(JrRegionName.REGION_APPROX,x,y+pasy+pasy,pasx,pasy);
		if (showIndex) {
			reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,(pasy*4));
			reg.set(JrRegionName.REGION_TOTAL,x,y+(pasy*3),pasx,pasy);
			reg.set(JrRegionName.REGION_INDEX,x,y+(pasy*4),pasx,rect.height-(pasy*4));
		}
		else {
			reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,rect.height);
			reg.set(JrRegionName.REGION_TOTAL,x,y+(pasy*3),pasx,rect.height-(pasy*3));
			reg.set(JrRegionName.REGION_INDEX,0,0,0,0);
		}
		reg.set(JrRegionName.REGION_MAP,x+pasx,y,wmap,rect.height-drh);
		reg.set(JrRegionName.REGION_TEXT,x+pasx+wmap,y,wtxt,rect.height-drh);
		reg.set(JrRegionName.REGION_SYMBOL1,x+pasx,dry,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL2,x+pasx+drw,dry,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL3,x+pasx+drw+drw,dry,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL4,x+pasx+(drw*3),dry,rect.width-(drw*3)-pasx,drh);
		reg.set(JrRegionName.REGION_SYMBOLS,x+pasx,dry,rect.width-pasx,drh);
		reg.computeMapParameters();
	}
	
	public int computeCaseWidth(JrRegions regions) {
		int w = regions.width(JrRegionName.REGION_MAP);
		w += regions.width(JrRegionName.REGION_DISTANCE);
		w += regions.width(JrRegionName.REGION_TEXT);
		return w;
	}
	
}
