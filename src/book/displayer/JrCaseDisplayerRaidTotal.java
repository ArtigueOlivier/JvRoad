/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import java.awt.Rectangle;

import tools.JrRegions;
import names.JrCaseDisplayerName;
import names.JrRegionName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseDisplayerRaidTotal extends JrCaseDisplayer {
	public int getName() {
		return JrCaseDisplayerName.CASE_DISPLAYER_RAID_TOTAL;
	}
	
	public boolean hasTotalColumn() {
		return false;
	}
	public boolean hasPartielColumn() {
		return true;
	}
	public boolean isDrawFrameTotal() {
		return false;
	}
	public boolean isTotalPartielSameColumn() {
		return true;
	}

	public boolean isSmallPartiel() {
		return true;
	}

	public boolean isFullText() {
		return true;
	}	
	
	public int computerPatielWidth(int w) {
		return (3 * w) / 10;
	}	
	
	public  boolean isVisible(int name,boolean editionMode) {
		switch(name) {
		case JrRegionName.REGION_PARTIEL : return true;
		case JrRegionName.REGION_RAZ :
		case JrRegionName.REGION_APPROX : return false; 
		case JrRegionName.REGION_SYMBOL1 :
		case JrRegionName.REGION_SYMBOL2 :
		case JrRegionName.REGION_SYMBOL3 :
		case JrRegionName.REGION_SYMBOL4 : return editionMode;
		case JrRegionName.REGION_TOTAL : return isDrawFrameTotal();
		case JrRegionName.REGION_DISTANCE : break;
		case JrRegionName.REGION_MAP : break;
		case JrRegionName.REGION_TEXT : return false;
		case JrRegionName.REGION_INDEX : break;
		case JrRegionName.REGION_SYMBOLS : break;
		default: break;
		}
		return true;
	}	
	
	public void apply(Rectangle rect,JrRegions reg,boolean showIndex) {		
		int x = rect.x;
		int y = rect.y;
		int pasx = (3 * rect.width) / 10;
		int pasy = rect.height / 4;
		int pasy2 = pasy / 2;
		int pasy3 = pasy / 3;
		int wmap = (rect.width - pasx) / 2;
		int drw = (rect.width - wmap - pasx) / 2;
		int drh = rect.height / 5;
		int dry = y + rect.height - drh;
		int wtxt = rect.width - wmap - pasx;

		reg.set(JrRegionName.REGION_TOTAL,x,y,pasx,pasy);		
		reg.set(JrRegionName.REGION_RAZ,x,y+pasy,pasx,pasy);
		reg.set(JrRegionName.REGION_APPROX,x,y+pasy+pasy,pasx,pasy);
		reg.set(JrRegionName.REGION_PARTIEL,x+(pasx/2),y+(pasy*3)+pasy2,(pasx/2),rect.height-(pasy*3)-pasy2);
		reg.set(JrRegionName.REGION_DISTANCE,x,y,pasx,rect.height);
		
		if (showIndex)
			reg.set(JrRegionName.REGION_INDEX,x,y+rect.height-pasy3,(pasx/3),pasy3);
		else 
			reg.set(JrRegionName.REGION_INDEX,0,0,0,0);
		reg.set(JrRegionName.REGION_MAP,x+pasx,y,wmap,rect.height);
		reg.set(JrRegionName.REGION_TEXT,x+pasx+wmap,y+drh,wtxt,rect.height-drh-drh);

		reg.set(JrRegionName.REGION_SYMBOL1,x+pasx+wmap,y,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL2,x+pasx+wmap+drw,y,rect.width-drw-pasx-wmap,drh);
		
		reg.set(JrRegionName.REGION_SYMBOL3,x+pasx+wmap,dry,drw,drh);
		reg.set(JrRegionName.REGION_SYMBOL4,x+pasx+wmap+drw,dry,rect.width-drw-pasx-wmap,drh);
		
		reg.set(JrRegionName.REGION_SYMBOLS,x+pasx+wmap,y,rect.width-wmap-pasx,rect.height);
		reg.computeMapParameters();
	}

	public int computeCaseWidth(JrRegions regions) {
		int w = regions.width(JrRegionName.REGION_MAP);
		w += regions.width(JrRegionName.REGION_DISTANCE);
		w += regions.width(JrRegionName.REGION_TEXT);
		return w;
	}
}

