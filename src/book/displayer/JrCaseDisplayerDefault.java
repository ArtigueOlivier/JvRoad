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
public class JrCaseDisplayerDefault extends JrCaseDisplayer {
	public int getName() {
		return JrCaseDisplayerName.CASE_DISPLAYER_DEFAULT;
	}
	public void apply(Rectangle rect,JrRegions reg,boolean showIndex) {		
		int x = rect.x;
		int y = rect.y;
		int pasx = rect.width / 5;
		int pasy = rect.height / 6;
		int bas = pasy * 4;
		int basm = pasy * 5;
		int pasp = bas / 3;
		int drw = pasx / 2;
		int drh = pasy;
		reg.set(JrRegionName.REGION_RAZ,x,y,pasx,pasp);
		reg.set(JrRegionName.REGION_PARTIEL,x,y+pasp,pasx,pasp);
		reg.set(JrRegionName.REGION_APPROX,x,y+pasp+pasp,pasx,bas-pasp-pasp);
		reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,bas);
		reg.set(JrRegionName.REGION_MAP,x+pasx,y,rect.width-pasx-pasx,basm);
		reg.set(JrRegionName.REGION_TEXT,x+pasx,y+basm,rect.width-pasx-pasx,rect.height-basm);
		if (showIndex) {
			reg.set(JrRegionName.REGION_TOTAL,x+rect.width-pasx,y,pasx,bas);
			reg.set(JrRegionName.REGION_INDEX,x+rect.width-pasx,y+bas,pasx,rect.height-bas);
		}
		else {
			reg.set(JrRegionName.REGION_TOTAL,x+rect.width-pasx,y,pasx,rect.height);
			reg.set(JrRegionName.REGION_INDEX,0,0,0,0);
		}
		reg.set(JrRegionName.REGION_SYMBOL1,x,y+bas,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL2,x+drw,y+bas,pasx-drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL3,x,y+bas+drh,drw,rect.height-bas-drh);
		reg.set(JrRegionName.REGION_SYMBOL4,x+drw,y+bas+drh,pasx-drw,rect.height-bas-drh);
		reg.set(JrRegionName.REGION_SYMBOLS,x,y+bas,pasx,rect.height-bas);
		reg.computeMapParameters();
	}
	
	public int computeCaseWidth(JrRegions regions) {
		int w = regions.width(JrRegionName.REGION_MAP);
		w += regions.width(JrRegionName.REGION_TOTAL);
		w += regions.width(JrRegionName.REGION_PARTIEL);
		return w;
	}
	
	
}
