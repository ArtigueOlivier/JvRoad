/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import java.awt.Rectangle;

import names.JrCaseDisplayerName;
import names.JrRegionName;

import tools.JrRegions;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseDisplayer extends JrDisplayer {
	public void apply(Rectangle rect,JrRegions regions,boolean showIndex) {		
	}
	public boolean hasTotalColumn() {
		return true;
	}
	public boolean hasPartielColumn() {
		return true;
	}
	public boolean isDrawFrameTotal() {
		return true;
	}
	public boolean isSmallPartiel() {
		return false;
	}
	public boolean isSmallTotal() {
		return false;
	}
	public boolean isTotalPartielSameColumn() {
		return false;
	}
	public boolean isFullText() {
		return false;
	}
	
	public int computerPatielWidth(int w) {
		return w / 5;
	}
	
	public int computeCaseWidth(JrRegions regions) {
		int w = regions.width(JrRegionName.REGION_MAP);
		w += regions.width(JrRegionName.REGION_PARTIEL);
		return w;
	}
	
	public  boolean isVisible(int name,boolean editionMode) {
		switch(name) {
		case JrRegionName.REGION_RAZ :
		case JrRegionName.REGION_PARTIEL :
		case JrRegionName.REGION_APPROX : return false; 
		case JrRegionName.REGION_SYMBOL1 :
		case JrRegionName.REGION_SYMBOL2 :
		case JrRegionName.REGION_SYMBOL3 :
		case JrRegionName.REGION_SYMBOL4 : return editionMode;
		case JrRegionName.REGION_TOTAL : return isDrawFrameTotal();
		case JrRegionName.REGION_DISTANCE : break;
		case JrRegionName.REGION_MAP : break;
		case JrRegionName.REGION_TEXT : break;
		case JrRegionName.REGION_INDEX : break;
		case JrRegionName.REGION_SYMBOLS : break;
		default: break;
		}
		return true;
	}	

	public static JrCaseDisplayer CreateDisplayer(int name) {
		switch(name) {
		case JrCaseDisplayerName.CASE_DISPLAYER_NOTOTAL :
			return new JrCaseDisplayerNoTotal();
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE :
			return new JrCaseDisplayerRallye();
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE_PARTIEL :
			return new JrCaseDisplayerRallyePartiel();
		case JrCaseDisplayerName.CASE_DISPLAYER_RALLYE_TOTAL :
			return new JrCaseDisplayerRallyeTotal();
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW :
			return new JrCaseDisplayerMaxDraw();
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW_PARTIEL :
			return new JrCaseDisplayerMaxDrawPartiel();
		case JrCaseDisplayerName.CASE_DISPLAYER_MAX_DRAW_TOTAL :
			return new JrCaseDisplayerMaxDrawTotal();
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO :
			return new JrCaseDisplayerMoto();
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO_PARTIEL :
			return new JrCaseDisplayerMotoPartiel();
		case JrCaseDisplayerName.CASE_DISPLAYER_MOTO_TOTAL :
			return new JrCaseDisplayerMotoTotal();
		case JrCaseDisplayerName.CASE_DISPLAYER_RAID_PARTIEL :
			return new JrCaseDisplayerRaidPartiel();
		case JrCaseDisplayerName.CASE_DISPLAYER_RAID_TOTAL :
			return new JrCaseDisplayerRaidTotal();
		default: break;		
		}
		return new JrCaseDisplayerDefault();
	}
}
