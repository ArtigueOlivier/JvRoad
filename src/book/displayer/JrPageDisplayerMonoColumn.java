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
public class JrPageDisplayerMonoColumn extends JrPageDisplayer {
	public int getName() {
		return JrPageDisplayerName.PAGE_DISPLAYER_MONOCOLUMN;
	}
	public int getPageFontForTotalPage() {
		return JrRegionFontName.REGION_FONT_DIST_SMALL;
	}	
	public void apply(Rectangle rect,JrPageRegions regions,int caseColumn,JrCaseDisplayer disp) {		
		int x = rect.x;
		int y = rect.y;
		int h = rect.height;
		int w = rect.width;
		int hco = h / 60;
		int he = (h - hco) / 10;
		int hc = (h - hco - he) / caseColumn;
		int wcol = disp.computerPatielWidth(w);
		int wn = w / 5;
		
		regions.set(JrPageRegionName.PAGE_REGION_GLOBAL,rect);
		regions.set(JrPageRegionName.PAGE_REGION_TITRE,x,y,w-wn,he/2);
		regions.set(JrPageRegionName.PAGE_REGION_NUMBER,x+w-wn,y,wn,he/2);
		regions.set(JrPageRegionName.PAGE_REGION_PARTIEL1,x,y+(he/2),wcol,he-(he/2));
		regions.set(JrPageRegionName.PAGE_REGION_PARTIEL2,0,0,0,0);
		if (disp.hasTotalColumn()) {
		    regions.set(JrPageRegionName.PAGE_REGION_TOTAL1,x+w-wcol,y+(he/2),wcol,he-(he/2));
		    regions.set(JrPageRegionName.PAGE_REGION_TOTAL2,0,0,0,0);
		    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE1,x+wcol,y+(he/2),w-(2*wcol),he-(he/2));
		    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE2,0,0,0,0);
		}
		else {
		    regions.set(JrPageRegionName.PAGE_REGION_TOTAL1,0,0,0,0);
		    regions.set(JrPageRegionName.PAGE_REGION_TOTAL2,0,0,0,0);
		    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE1,x+wcol,y+(he/2),w-wcol,he-(he/2));
		    regions.set(JrPageRegionName.PAGE_REGION_TOTALPAGE2,0,0,0,0);
		}
		int i;
		for(i = 0; i < 20; i++) {
		    if (i < caseColumn)
		    	regions.set(JrPageRegionName.PAGE_REGION_CASE1+i,x,y+he+(i*hc),w,hc);
		    else
		    	regions.set(JrPageRegionName.PAGE_REGION_CASE1+i,0,0,0,0);
		}
		regions.set(JrPageRegionName.PAGE_REGION_COPYRIGHT,x,y+he+(caseColumn*hc),w,h-he-(caseColumn*hc));
	}
	
	public boolean hasTwoColumns() {
		return false;
	}
}
