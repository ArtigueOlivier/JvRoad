/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import java.awt.Rectangle;

import names.JrPageDisplayerName;
import names.JrPageRegionName;
import names.JrRegionFontName;

import tools.JrPageRegions;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPageDisplayerRaid extends JrPageDisplayer {
	public int getName() {
		return JrPageDisplayerName.PAGE_DISPLAYER_RAID;
	}
	public int getPageFontForTotalPage() {
		return JrRegionFontName.REGION_FONT_TITRE_PAGE;
	}
	public boolean hasPageTotalText() {
		return false;
	}
	
	public void apply(Rectangle rect,JrPageRegions regions,int caseColumn,JrCaseDisplayer disp) {
		int x = rect.x;
		int y = rect.y;
		int h = rect.height;
		int w = rect.width;
		int hco = h / 60;
		int he = (h - hco) / 20;
		int hc = (h - hco - he) / caseColumn;
		int wc = w / 2;
		int wcol = (7 * w) / 20;
		int wn = (3 * w) / 20;
		
		regions.set(JrPageRegionName.PAGE_REGION_GLOBAL,rect);
		regions.set(JrPageRegionName.PAGE_REGION_NUMBER,x,y,wn,he);
		regions.set(JrPageRegionName.PAGE_REGION_TITRE,x+wn,y,w-wn-wcol,he);
	    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE1,x+w-wcol,y,wcol,he);
	    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE2,0,0,0,0);
		regions.set(JrPageRegionName.PAGE_REGION_PARTIEL1,0,0,0,0);
		regions.set(JrPageRegionName.PAGE_REGION_PARTIEL2,0,0,0,0);
	    regions.set(JrPageRegionName.PAGE_REGION_TOTAL1,0,0,0,0);
	    regions.set(JrPageRegionName.PAGE_REGION_TOTAL2,0,0,0,0);
		int i,d;
		for(i = 0; i < 20; i++) {
		    if (i < (caseColumn * 2)) {
		    	if (i < caseColumn) {
		    		regions.set(JrPageRegionName.PAGE_REGION_CASE1+i,x,y+he+(i*hc),wc,hc);
		    	}
		    	else {
		    		d = i - caseColumn;
		    		regions.set(JrPageRegionName.PAGE_REGION_CASE1+i,x+wc,y+he+(d*hc),w-wc,hc);
		    	}
		    }
		    else
		    	regions.set(JrPageRegionName.PAGE_REGION_CASE1+i,0,0,0,0);
		}
		regions.set(JrPageRegionName.PAGE_REGION_COPYRIGHT,x,y+he+(caseColumn*hc),w,h-he-(caseColumn*hc));
	}

}
