/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import java.awt.Rectangle;

import names.JrPageDisplayerName;
import names.JrRegionFontName;

import tools.JrPageRegions;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPageDisplayer extends JrDisplayer {
	public void apply(Rectangle rect,JrPageRegions regions,int caseColumn,JrCaseDisplayer disp) {		
	}
	public boolean hasTwoColumns() {
		return true;
	}
	public boolean hasTwoPages() {
		return false;
	}
	public boolean hasPageTotalText() {
		return true;
	}
	public int getPageFontForTotalPage() {
		return JrRegionFontName.REGION_FONT_DIST_SMALL;
	}
	
	public static JrPageDisplayer CreateDisplayer(int name) {
		switch(name) {
		case JrPageDisplayerName.PAGE_DISPLAYER_MONOCOLUMN :
			return new JrPageDisplayerMonoColumn();
		case JrPageDisplayerName.PAGE_DISPLAYER_TWOPAGES :
			return new JrPageDisplayerTwoPages();
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID :
			return new JrPageDisplayerRaid();
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID_MONOCOLUMN :
			return new JrPageDisplayerRaidMonoColumn();
		case JrPageDisplayerName.PAGE_DISPLAYER_RAID_TWOPAGES :
			return new JrPageDisplayerRaidTwoPages();
		default: break;		
		}
		return new JrPageDisplayerDefault();
	}
}
