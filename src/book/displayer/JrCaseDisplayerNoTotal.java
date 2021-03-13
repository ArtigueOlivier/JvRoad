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
public class JrCaseDisplayerNoTotal extends JrCaseDisplayer {
	public int getName() {
		return JrCaseDisplayerName.CASE_DISPLAYER_NOTOTAL;
	}
	public boolean hasTotalColumn() {
		return false;
	}	
	public void apply(Rectangle rect,JrRegions reg,boolean showIndex) {		
		int x = rect.x;
		int y = rect.y;
		int pasx = rect.width / 5;
		int pasy = rect.height / 6;
		int bas = pasy * 4;
		int basm = pasy * 5;
		int pasp = bas / ((showIndex)? 4 : 3);
		int drw = pasx / 2;
		int drh = pasy;

		reg.set(JrRegionName.REGION_RAZ,x,y,pasx,pasp);
		reg.set(JrRegionName.REGION_PARTIEL,x,y+pasp,pasx,pasp);
		if (showIndex) {
			reg.set(JrRegionName.REGION_APPROX,x,y+pasp+pasp,pasx,pasp);
			reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,pasp*3);
			reg.set(JrRegionName.REGION_INDEX,x,y+(pasp*3),pasx,bas-(pasp*3));
		}
		else {
			reg.set(JrRegionName.REGION_APPROX,x,y+pasp+pasp,pasx,bas-pasp-pasp);
			reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,bas);
			reg.set(JrRegionName.REGION_INDEX,0,0,0,0);
		}
		reg.set(JrRegionName.REGION_MAP,x+pasx,y,rect.width-pasx,basm);
		reg.set(JrRegionName.REGION_TEXT,x+pasx,y+basm,rect.width-pasx,rect.height-basm);
		reg.set(JrRegionName.REGION_TOTAL,0,0,0,0);
		reg.set(JrRegionName.REGION_SYMBOL1,x,y+bas,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL2,x+drw,y+bas,pasx-drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL3,x,y+bas+drh,drw,rect.height-bas-drh);
		reg.set(JrRegionName.REGION_SYMBOL4,x+drw,y+bas+drh,pasx-drw,rect.height-bas-drh);
		reg.set(JrRegionName.REGION_SYMBOLS,x,y+bas,pasx,rect.height-bas);
		reg.computeMapParameters();
	}

}
