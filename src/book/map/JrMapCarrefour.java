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
public class JrMapCarrefour extends JrMap {
	private int lines[] = new int [48];
	private JrPoint zones[] = new JrPoint [48];
	
	public JrMapCarrefour() {
		super();		
		int i;
		
		for(i = 0; i < 48; i++) {
			lines[i] = JrTraitName.TRAIT_NONE;
			zones[i] = new JrPoint();
		}
	}

	public void save(JrFileSave file,String entry) {
		StringBuffer buff = new StringBuffer();
		buff.append(lines[0]);
		for(int i = 1; i < 48; i++) {
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
		for(i = 0; i < 48; i++) {
			lines[i] = (i >= count)? JrTraitName.TRAIT_NONE : Integer.parseInt(items[i]);
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		read(file,entry);
	}
	
	public void readVeryOld(String[] data) {
		int star[] = { 5, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 19, 20, 21, 23, 24,
                25, 26, 27, 29, 30, 31, 33, 34, 35, 36, 37, 39, 0, 1, 3, 4 };
        int carr[] = { 15, 13, 7, 5, 3, 37, 35, 33, 27, 25, 23, 17 };
        int i;
        int cpt = data.length;
        if (cpt < 2)
        	return;
        if ((Integer.parseInt(data[0]) == 0) && (cpt == 34)) {
        	for(i = 2; i < 34; i++) {
        		switch(Integer.parseInt(data[i])) {
        		case 1 : lines[star[i-2]] = JrTraitName.TRAIT_PATH; break;
        		case 2 : lines[star[i-2]] = JrTraitName.TRAIT_PATH_DEST; break;
        		case 3 : lines[star[i-2]] = JrTraitName.TRAIT_PATH_DESTINV; break;
        		case 4 : lines[star[i-2]] = JrTraitName.TRAIT_PATH_SRC; break;
        		case 5 : lines[star[i-2]] = JrTraitName.TRAIT_PATH_SRCINV; break;
        		case 6 :
        		case 7 :
        		case 8 : lines[star[i-2]] = JrTraitName.TRAIT_PATH; break;
        		default: lines[star[i-2]] = JrTraitName.TRAIT_NONE; break;
        		}
        	}
        }
		if ((Integer.parseInt(data[0]) == 5) && (cpt == 14)) {
		  for(i = 2; i < 14; i++) {
		    switch(Integer.parseInt(data[i])) {
		    case 1 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD; break;
		    case 2 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD_DEST; break;
		    case 3 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD_DESTINV; break;
		    case 4 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD_SRC; break;
		    case 5 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD_SRCINV; break;
		    case 6 :
		    case 7 :
		    case 8 : lines[carr[i-2]] = JrTraitName.TRAIT_ROAD; break;
		    default: lines[carr[i-2]] = JrTraitName.TRAIT_NONE; break;
		    }
		  }
		}
	}
		
	public JrMap copy() {
		JrMapCarrefour map = new JrMapCarrefour();
		for(int i = 0; i < 48; i++)
			map.lines[i] = lines[i];
		return map;
	}
	
	public int getName() {
		return JrMapName.MAP_CARREFOUR_NAME;
	}

	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_TRAITSROTA;
	}
	
	public void rotation() {
		int tmp [] = new int [48];
		int i;
		for(i = 0; i < 40; i++)
			tmp[i] = lines[(i+30)%40];
		for(i = 40; i < 48; i++)
			tmp[i] = lines[40 + (((i-40)+6)%8)];
		for(i = 0; i < 48; i++)
			lines[i] = tmp[i];
	}
	
	public void inverse() {
		int tmp [] = new int [48];
		int i;
		for(i = 0; i < 40; i++)
			tmp[i] = lines[(i+60)%40];
		for(i = 40; i < 48; i++)
			tmp[i] = lines[40 + (((i-40)+4)%8)];
			//tmp[i] = lines[(i+92)%48];
		for(i = 0; i < 48; i++) 
			lines[i] = inverseTrait(tmp[i]);		
	}
	
	public int getOriginCount() {
		int cpt = 0;		
		for(int i = 0; i < 48; i++) {
			cpt += (JrTraitName.IsAnOrigin(lines[i]) == true)? 1 : 0;
		}
		return cpt;
	}
	public int getDestinationCount() {
		int cpt = 0;		
		for(int i = 0; i < 48; i++) {
			cpt += (JrTraitName.IsAnDestination(lines[i]) == true)? 1 : 0;
		}
		return cpt;
	}
	
	public void initializeRegions(JrRegions regions) {
		super.initializeRegions(regions);
		int i;
		zones[ 0].x = -5; zones[ 0].y = -5; zones[ 1].x = -4; zones[ 1].y = -5;
		zones[ 2].x = -3; zones[ 2].y = -5; zones[ 3].x = -2; zones[ 3].y = -5;
		zones[ 4].x = -1; zones[ 4].y = -5; zones[ 5].x =  0; zones[ 5].y = -5;
		zones[ 6].x =  1; zones[ 6].y = -5; zones[ 7].x =  2; zones[ 7].y = -5;
		zones[ 8].x =  3; zones[ 8].y = -5; zones[ 9].x =  4; zones[ 9].y = -5;
		zones[10].x =  5; zones[10].y = -5; zones[11].x =  5; zones[11].y = -4;
		zones[12].x =  5; zones[12].y = -3; zones[13].x =  5; zones[13].y = -2;
		zones[14].x =  5; zones[14].y = -1; zones[15].x =  5; zones[15].y =  0;
		zones[16].x =  5; zones[16].y =  1; zones[17].x =  5; zones[17].y =  2;
		zones[18].x =  5; zones[18].y =  3; zones[19].x =  5; zones[19].y =  4;
		zones[20].x =  5; zones[20].y =  5; zones[21].x =  4; zones[21].y =  5;
		zones[22].x =  3; zones[22].y =  5; zones[23].x =  2; zones[23].y =  5;
		zones[24].x =  1; zones[24].y =  5; zones[25].x =  0; zones[25].y =  5;
		zones[26].x = -1; zones[26].y =  5; zones[27].x = -2; zones[27].y =  5;
		zones[28].x = -3; zones[28].y =  5; zones[29].x = -4; zones[29].y =  5;
		zones[30].x = -5; zones[30].y =  5; zones[31].x = -5; zones[31].y =  4;
		zones[32].x = -5; zones[32].y =  3; zones[33].x = -5; zones[33].y =  2;
		zones[34].x = -5; zones[34].y =  1; zones[35].x = -5; zones[35].y =  0;
		zones[36].x = -5; zones[36].y = -1; zones[37].x = -5; zones[37].y = -2;
		zones[38].x = -5; zones[38].y = -3; zones[39].x = -5; zones[39].y = -4;
		zones[40].x = -3; zones[40].y = -3; zones[41].x =  0; zones[41].y = -3;
		zones[42].x =  3; zones[42].y = -3; zones[43].x =  3; zones[43].y =  0;
		zones[44].x =  3; zones[44].y =  3; zones[45].x =  0; zones[45].y =  3;
		zones[46].x = -3; zones[46].y =  3; zones[47].x = -3; zones[47].y =  0;
		for(i = 0; i < 48; i++) {
		    zones[i].x = (zones[i].x * pasx) + ox;
		    zones[i].y = (zones[i].y * pasy) + oy;
		}
	}
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
		boolean draw;  
		dt.selectPen(1);
	  	dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(i = 0; i < 48; i++) {
			draw = (lines[i] == JrTraitName.TRAIT_NONE)? true : false;
			if ((draw == true) && (i >= 40)) {
				draw = (lines[(i-40)*5] != JrTraitName.TRAIT_NONE)? false : draw;
			}
			if (draw)
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
		}
	}
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {	
		int i;
		
		for(i = 0; i < 48; i++) {
			switch(filter.filterTrait(lines[i])) {
			case JrTraitName.TRAIT_PATH :
			case JrTraitName.TRAIT_PATH_DEST :
			case JrTraitName.TRAIT_PATH_DESTINV :
			case JrTraitName.TRAIT_PATH_SRC :
			case JrTraitName.TRAIT_PATH_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_TRAIN :
			case JrTraitName.TRAIT_ROAD :
			case JrTraitName.TRAIT_ROAD_DEST :
			case JrTraitName.TRAIT_ROAD_DESTINV :
			case JrTraitName.TRAIT_ROAD_SRC :
			case JrTraitName.TRAIT_ROAD_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_SENTIER :
			case JrTraitName.TRAIT_SENTIER_DEST :
			case JrTraitName.TRAIT_SENTIER_DESTINV :
			case JrTraitName.TRAIT_SENTIER_SRC :
			case JrTraitName.TRAIT_SENTIER_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_RIVER :
			case JrTraitName.TRAIT_DOUBLEROAD :
			case JrTraitName.TRAIT_DOUBLEROAD_DEST :
			case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
			case JrTraitName.TRAIT_DOUBLEROAD_SRC :
			case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
			case JrTraitName.TRAIT_NATIONAL :
			case JrTraitName.TRAIT_NATIONAL_DEST :
			case JrTraitName.TRAIT_NATIONAL_DESTINV :
			case JrTraitName.TRAIT_NATIONAL_SRC :
			case JrTraitName.TRAIT_NATIONAL_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			default : break;
			}
		}
	}
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
		
		for(i = 0; i < 48; i++) {
			switch(filter.filterTrait(lines[i])) {
			case JrTraitName.TRAIT_TRAIN :
			case JrTraitName.TRAIT_ROAD :
			case JrTraitName.TRAIT_ROAD_DEST :
			case JrTraitName.TRAIT_ROAD_DESTINV :
			case JrTraitName.TRAIT_ROAD_SRC :
			case JrTraitName.TRAIT_ROAD_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_DOUBLEROAD :
			case JrTraitName.TRAIT_DOUBLEROAD_DEST :
			case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
			case JrTraitName.TRAIT_DOUBLEROAD_SRC :
			case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
			case JrTraitName.TRAIT_NATIONAL :
			case JrTraitName.TRAIT_NATIONAL_DEST :
			case JrTraitName.TRAIT_NATIONAL_DESTINV :
			case JrTraitName.TRAIT_NATIONAL_SRC :
			case JrTraitName.TRAIT_NATIONAL_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_RIVER :
				dt.selectDefinedPen(JrPenName.PEN_RIVER_BLUE,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			default : break;
			}
		}
	}
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
		float pasn = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK) / 1.1f;
		float pasr = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK) / 1.8f;
		for(i = 0; i < 48; i++) {
			switch(filter.filterTrait(lines[i])) {
			case JrTraitName.TRAIT_TRAIN :
				dt.selectDefinedPen(JrPenName.PEN_TRAIN_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_DOUBLEROAD :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_NATIONAL :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_DEST :
			case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				dt.selectColor(JrColorName.COLOR_BG_MAP);
			    dt.drawEllipse(zones[i].x-pasn,zones[i].y-pasn,pasn*2,pasn*2,true);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				drawDestination(dt,ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_NATIONAL_DEST :
			case JrTraitName.TRAIT_NATIONAL_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				dt.selectColor(JrColorName.COLOR_BG_MAP);
			    dt.drawEllipse(zones[i].x-pasn,zones[i].y-pasn,pasn*2,pasn*2,true);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				drawDestination(dt,ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_SENTIER_DEST :
			case JrTraitName.TRAIT_SENTIER_DESTINV :
			case JrTraitName.TRAIT_PATH_DEST :
			case JrTraitName.TRAIT_PATH_DESTINV :
			case JrTraitName.TRAIT_ROAD_DEST :
			case JrTraitName.TRAIT_ROAD_DESTINV :
				dt.selectColor(JrColorName.COLOR_BG_MAP);
			    dt.drawEllipse(zones[i].x-pasr,zones[i].y-pasr,pasr*2,pasr*2,true);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				drawDestination(dt,ox,oy,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_SRC :
			case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				drawOrigin(dt,zones[i].x,zones[i].y);
				break;
			case JrTraitName.TRAIT_NATIONAL_SRC :
			case JrTraitName.TRAIT_NATIONAL_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
				dt.drawLine(ox,oy,zones[i].x,zones[i].y);
			case JrTraitName.TRAIT_SENTIER_SRC :
			case JrTraitName.TRAIT_SENTIER_SRCINV :
			case JrTraitName.TRAIT_PATH_SRC :
			case JrTraitName.TRAIT_PATH_SRCINV :
			case JrTraitName.TRAIT_ROAD_SRC :
			case JrTraitName.TRAIT_ROAD_SRCINV :
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
		  for(i = 0; i < 48; i++) {
		  	dt.drawRectangle(zones[i].x-handlew,zones[i].y-handlew,(handlew*2),(handlew*2),true);
		  }
		  dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);		  
		  for(i = 0; i < 48; i++) {
		  	dt.drawRectangle(zones[i].x-handlew,zones[i].y-handlew,(handlew*2),(handlew*2),false);
		  }
	}
	
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(zones,48,handlew,evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 48)) {
			lines[pos] = cnt.getCurrentTrait();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(zones,48,handlew,evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 48)) {
			lines[pos] = JrTraitName.TRAIT_NONE;
			return true;
		}
		return false;
	}

	public void setLine(int ndx,int val) {
		lines[ndx] = val;
	}
	
	public int getLine(int ndx) {
		return lines[ndx];
	}
}
