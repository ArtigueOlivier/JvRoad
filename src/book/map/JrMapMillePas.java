/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.event.MouseEvent;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;

import application.actions.JrActionCenter;

import tools.JrContext;
import tools.JrDrawTools;
import tools.JrPoint;
import tools.JrRegions;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;
import names.JrTraitName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapMillePas extends JrMap {
	private final static int NB_POINTS = 36;
	private int lines[] = new int [NB_POINTS];
	private JrPoint[] zones = new JrPoint [NB_POINTS];
	private JrPoint[] dests = new JrPoint [NB_POINTS];
	
	public JrMapMillePas() {
		super();
		for(int i = 0; i < NB_POINTS; i++) {
			lines[i] = JrTraitName.TRAIT_NONE;
			zones[i] = new JrPoint();
			dests[i] = new JrPoint();
		}
	}

	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(lines[0]);
		for(int i = 1; i < NB_POINTS; i++) {
			buff.append(';');
			buff.append(lines[i]);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {		
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String items[] = str.split(";");
		
		count = items.length;
		for(i = 0; i < NB_POINTS; i++) {
			lines[i] = (i >= count)? JrTraitName.TRAIT_NONE : Integer.parseInt(items[i]);
		}
	}
		
	public JrMap copy() {
		JrMapMillePas map = new JrMapMillePas();
		for(int i = 0; i < NB_POINTS; i++)
			map.lines[i] = lines[i];
		return map;
	}

	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_MIL;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_MILLEPAS_NAME;
	}
	
	public int getOriginCount() {
		int cpt = 0;		
		for(int i = 0; i < NB_POINTS; i++) {
			cpt += (JrTraitName.IsAnOrigin(lines[i]) == true)? 1 : 0;
		}
		return cpt;
	}
	public int getDestinationCount() {
		int cpt = 0;		
		for(int i = 0; i < NB_POINTS; i++) {
			cpt += (JrTraitName.IsAnDestination(lines[i]) == true)? 1 : 0;
		}
		return cpt;
	}
	
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentTraitPas(JrTraitName.TRAIT_NONE);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		int current = cnt.getCurrentTraitPas();
		switch (current) {
		case JrTraitName.TRAIT_NONE :
			current = JrTraitName.TRAIT_SENTIER_SRCINV; break;
		case JrTraitName.TRAIT_SENTIER :
			current = JrTraitName.TRAIT_PATH_SRCINV; break;
		case JrTraitName.TRAIT_SENTIER_DEST :
			current = JrTraitName.TRAIT_SENTIER; break;
		default : current--; break;
		}
		cnt.setCurrentTraitPas(current);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		int current = cnt.getCurrentTraitPas();
		switch (current) {
		case JrTraitName.TRAIT_PATH_SRCINV :
			current = JrTraitName.TRAIT_SENTIER; break;
		case JrTraitName.TRAIT_SENTIER :
			current = JrTraitName.TRAIT_SENTIER_DEST; break;
		case JrTraitName.TRAIT_SENTIER_SRCINV :
			current = JrTraitName.TRAIT_NONE; break;
		default : current++; break;
		}
		cnt.setCurrentTraitPas(current);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentTraitPas(JrTraitName.TRAIT_SENTIER_SRCINV);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	
	public void initializeRegions(JrRegions regions) {
		super.initializeRegions(regions);
		int i;
		zones[ 0].x =  0; zones[ 0].y = -5; zones[ 1].x =  3; zones[ 1].y = -4;
		zones[ 2].x =  3; zones[ 2].y = -3; zones[ 3].x =  3; zones[ 3].y = -2;
		zones[ 4].x =  3; zones[ 4].y = -1; zones[ 5].x =  3; zones[ 5].y =  0;
		zones[ 6].x =  3; zones[ 6].y =  1; zones[ 7].x =  3; zones[ 7].y =  2;
		zones[ 8].x =  3; zones[ 8].y =  3; zones[ 9].x =  3; zones[ 9].y =  4;
		zones[10].x =  0; zones[10].y =  5; zones[11].x = -3; zones[11].y =  4;
		zones[12].x = -3; zones[12].y =  3; zones[13].x = -3; zones[13].y =  2;
		zones[14].x = -3; zones[14].y =  1; zones[15].x = -3; zones[15].y =  0;
		zones[16].x = -3; zones[16].y = -1; zones[17].x = -3; zones[17].y = -2;
		zones[18].x = -3; zones[18].y = -3; zones[19].x = -3; zones[19].y = -4;
		
		for(i = 0; i < 20; i++) {
			dests[i].x = 0;
			dests[i].y = zones[i].y;
		}
		for(i = 0; i < 20; i++) {
		    zones[i].x = (zones[i].x * pasx) + ox;
		    zones[i].y = (zones[i].y * pasy) + oy;
		    dests[i].x = (dests[i].x * pasx) + ox;
		    dests[i].y = (dests[i].y * pasy) + oy;
		}	
		
		computeDiagonale(2,20,true);
		computeDiagonale(2,21,false);
		computeDiagonale(4,22,true);
		computeDiagonale(4,23,false);
		computeDiagonale(6,24,true);
		computeDiagonale(6,25,false);
		computeDiagonale(8,26,true);
		computeDiagonale(8,27,false);
		computeDiagonale(12,28,true);
		computeDiagonale(12,29,false);
		computeDiagonale(14,30,true);
		computeDiagonale(14,31,false);
		computeDiagonale(16,32,true);
		computeDiagonale(16,33,false);
		computeDiagonale(18,34,true);
		computeDiagonale(18,35,false);		
	}
	
	public void inverse() {
		int tmp [] = new int [NB_POINTS];
		int off [] = { 29, 28, 31, 30, 33, 32, 35, 34, 21, 20, 23, 22, 25, 24, 27, 26 };
		int i;
		
		for(i = 0; i < 20; i++)
			if (i < 10)
			  tmp[i+10] = lines[i];
			else
			  tmp[i-10] = lines[i];
		for(i = 20; i < NB_POINTS; i++)			
			  tmp[off[i-20]] = lines[i];
		for(i = 0; i < NB_POINTS; i++)
			lines[i] = inverseTrait(tmp[i]);
	}
	
	public void computeDiagonale(int src,int dest,boolean above) {
		dests[dest].x = dests[src].x;
		dests[dest].y = dests[src].y;
		zones[dest].x = zones[src].x;
		zones[dest].y = zones[src].y + (((above)? (-handlew) : handlew) * 2);
	}
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
		  
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(i = 1; i < NB_POINTS; i++) {
			if (lines[i] == JrTraitName.TRAIT_NONE)
				dt.drawLine(dests[i].x,dests[i].y,zones[i].x,zones[i].y);
		}
	}	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {	
		for(int i = 0; i < NB_POINTS; i++) {
			switch(lines[i]) {
			case JrTraitName.TRAIT_PATH :
			case JrTraitName.TRAIT_PATH_DEST :
			case JrTraitName.TRAIT_PATH_DESTINV :
			case JrTraitName.TRAIT_PATH_SRC :
			case JrTraitName.TRAIT_PATH_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(dests[i].x,dests[i].y,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_SENTIER :
			case JrTraitName.TRAIT_SENTIER_DEST :
			case JrTraitName.TRAIT_SENTIER_DESTINV :
			case JrTraitName.TRAIT_SENTIER_SRC :
			case JrTraitName.TRAIT_SENTIER_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
				dt.drawLine(dests[i].x,dests[i].y,zones[i].x,zones[i].y);
				break;
			default : break;
			}
		}
		dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		dt.drawLine(zones[0].x,zones[0].y,zones[10].x,zones[10].y);
	}
	
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		float pas = dt.getPenWidth(JrPenName.PEN_ROAD_BLACK);
		for(int i = 0; i < NB_POINTS; i++) {
			switch(lines[i]) {
			case JrTraitName.TRAIT_SENTIER_DEST :
			case JrTraitName.TRAIT_SENTIER_DESTINV :
			case JrTraitName.TRAIT_PATH_DEST :
			case JrTraitName.TRAIT_PATH_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
			    dt.drawRectangle(zones[i].x-pas,zones[i].y-pas,pas*2,pas*2,true);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				if ((i == 0) || (i == 10))
					drawDestination(dt,ox,oy,zones[i].x,zones[i].y);
				else
					drawDestination(dt,dests[i].x,dests[i].y,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_SENTIER_SRC :
			case JrTraitName.TRAIT_SENTIER_SRCINV :
			case JrTraitName.TRAIT_PATH_SRC :
			case JrTraitName.TRAIT_PATH_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				drawOrigin(dt,zones[i].x,zones[i].y);
				break;
			default : break;
			}
		}
	}
	public void drawFinalHandles(JrDrawTools dt,int currentView) {		
		int i;
		  
		dt.selectPen(1);
  	    dt.selectColor(JrColorName.COLOR_BG_MAP_SELECTION);		  
		for(i = 0; i < NB_POINTS; i++) {
		  	dt.drawRectangle(zones[i].x-handlew,zones[i].y-handlew,(handlew*2),(handlew*2),true);
		}
  		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(i = 0; i < NB_POINTS; i++) {
		  	dt.drawRectangle(zones[i].x-handlew,zones[i].y-handlew,(handlew*2),(handlew*2),false);
		}
	}
	
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(zones,NB_POINTS,handlew,evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < NB_POINTS)) {
			lines[pos] = cnt.getCurrentTraitPas();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(zones,NB_POINTS,handlew,evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < NB_POINTS)) {
			lines[pos] = JrTraitName.TRAIT_NONE;
			return true;
		}
		return false;
	}

}
