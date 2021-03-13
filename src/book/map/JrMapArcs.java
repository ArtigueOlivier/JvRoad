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
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import names.JrArcName;
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapArcs extends JrMap {
	private int draws[] = new int [100];
	
	public JrMapArcs() {
		super();
		for(int i = 0; i < 100; i++) {
			draws[i] = JrArcName.ARC_NOTHING;
		}
	}

	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(draws[0]);
		for(int i = 1; i < 100; i++) {
			buff.append(';');
			buff.append(draws[i]);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {		
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String items[] = str.split(";");
		
		count = items.length;
		for(i = 0; i < 100; i++) {
			draws[i] = (i >= count)? JrArcName.ARC_NOTHING : Integer.parseInt(items[i]);
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		read(file,entry);
	}
	
	public void readVeryOld(String[] data) {
		int small[]  = { 1, 1, 0, 1, 1, 0, 0, 0 };
		int large[] = { 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77 };

		int i,val,pos;
		int cpt = data.length;
		if (cpt < 2)
		    return;
		for(i = 0; i < 100; i++) 
		    draws[i] = JrArcName.ARC_NOTHING;
		if ((Integer.parseInt(data[0]) == 8) && (cpt == 18)) {
		    for(i = 2; i < 18; i++) {
		    	val = Integer.parseInt(data[i]) - 1;
		    	pos = large[i-2] + small[val * 2] + (small[(val * 2)+1] * 10);
		    	switch(val) {
		        case 0 :
		          draws[pos] = JrArcName.ARC_PATH_NO;
		          draws[large[i-2]] = JrArcName.ARC_PATH_BIGNO;
		          break;
		        case 1 :
		          draws[pos] = JrArcName.ARC_PATH_NE;
		          draws[large[i-2]] = JrArcName.ARC_PATH_BIGNE;
		          break;
		        case 2 :
		          draws[pos] = JrArcName.ARC_PATH_SO;
		          draws[large[i-2]] = JrArcName.ARC_PATH_BIGSO;
		          break;
		        case 3 :
		          draws[pos] = JrArcName.ARC_PATH_SE;
		          draws[large[i-2]] = JrArcName.ARC_PATH_BIGSE;
		          break;
		        default: break;
		    	}
		    }
		}
	}	
	
	public JrMap copy() {
		JrMapArcs map = new JrMapArcs();
		for(int i = 0; i < 100; i++)
			map.draws[i] = draws[i];
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_ARCS;
	}

	public int getName() {
		return JrMapName.MAP_ARCS_NAME;
	}
	
	public void rotation() {
		int x,y,i,rota;
		int tmp [] = new int [100];
		for(i = 0; i < 100; i++)
			tmp [i] = JrArcName.ARC_NOTHING;
		for(i = 0; i < 100; i++) {
			x = i % 10;
			y = i / 10;
			if (isBigArc(draws[i])) {
				x++; 
				rota = (x * 10) + (9 - y);
				x = rota % 10;
				y = rota / 10;
				x--;
				y--;
				tmp[x + (y * 10)] = draws[i];
			}
			else {
				if (draws[i] != JrArcName.ARC_NOTHING)
					tmp[(x * 10) + (9 - y)] = draws[i];
			}
		}
		for(i = 0; i < 100; i++) {
			switch(tmp[i]) {
			case JrArcName.ARC_PATH_NO :
				draws[i] = JrArcName.ARC_PATH_NE; break;
			case JrArcName.ARC_PATH_BIGNO :
				draws[i] = JrArcName.ARC_PATH_BIGNE; break;
			case JrArcName.ARC_PATH_NE :
				draws[i] = JrArcName.ARC_PATH_SE; break;
			case JrArcName.ARC_PATH_BIGNE :
				draws[i] = JrArcName.ARC_PATH_BIGSE; break;
			case JrArcName.ARC_PATH_SO :
				draws[i] = JrArcName.ARC_PATH_NO; break;
			case JrArcName.ARC_PATH_BIGSO :
				draws[i] = JrArcName.ARC_PATH_BIGNO; break;
			case JrArcName.ARC_PATH_SE :
				draws[i] = JrArcName.ARC_PATH_SO; break;
			case JrArcName.ARC_PATH_BIGSE :
				draws[i] = JrArcName.ARC_PATH_BIGSO; break;
			case JrArcName.ARC_ROAD_NO :
				draws[i] = JrArcName.ARC_ROAD_NE; break;
			case JrArcName.ARC_ROAD_BIGNO :
				draws[i] = JrArcName.ARC_ROAD_BIGNE; break;
			case JrArcName.ARC_ROAD_NE :
				draws[i] = JrArcName.ARC_ROAD_SE; break;
			case JrArcName.ARC_ROAD_BIGNE :
				draws[i] = JrArcName.ARC_ROAD_BIGSE; break;
			case JrArcName.ARC_ROAD_SO :
				draws[i] = JrArcName.ARC_ROAD_NO; break;
			case JrArcName.ARC_ROAD_BIGSO :
				draws[i] = JrArcName.ARC_ROAD_BIGNO; break;
			case JrArcName.ARC_ROAD_SE :
				draws[i] = JrArcName.ARC_ROAD_SO; break;
			case JrArcName.ARC_ROAD_BIGSE :
				draws[i] = JrArcName.ARC_ROAD_BIGSO; break;
			case JrArcName.ARC_PATH_NOSE :
				draws[i] = JrArcName.ARC_PATH_NESO; break;
			case JrArcName.ARC_PATH_NESO :
				draws[i] = JrArcName.ARC_PATH_NOSE; break;
			case JrArcName.ARC_ROAD_NOSE :
				draws[i] = JrArcName.ARC_ROAD_NESO; break;
			case JrArcName.ARC_ROAD_NESO :
				draws[i] = JrArcName.ARC_ROAD_NOSE; break;
			case JrArcName.ARC_PATHROAD_CROSS :
				draws[i] = JrArcName.ARC_ROADPATH_CROSS; break;
			case JrArcName.ARC_ROADPATH_CROSS :
				draws[i] = JrArcName.ARC_PATHROAD_CROSS; break;
            default : draws[i] = tmp[i]; break;
			}
		}
	}
	
	public void inverse() {
		int tmp [] = new int [100];
		int i;
		for(i = 0; i < 100; i++) {
			if (isBigArc(draws[i]) == false) {
				tmp[99 - i] = draws[i];
			}
		}
		for(i = 0; i < 89; i++) {
			if (isBigArc(draws[i]) == true) {
				tmp[88 - i] = draws[i];
			}
		}
		for(i = 0; i < 100; i++) {
			switch(tmp[i]) {
			case JrArcName.ARC_PATH_NO :
				draws[i] = JrArcName.ARC_PATH_SE; break;
			case JrArcName.ARC_PATH_BIGNO :
				draws[i] = JrArcName.ARC_PATH_BIGSE; break;
			case JrArcName.ARC_PATH_NE :
				draws[i] = JrArcName.ARC_PATH_SO; break;
			case JrArcName.ARC_PATH_BIGNE :
				draws[i] = JrArcName.ARC_PATH_BIGSO; break;
			case JrArcName.ARC_PATH_SO :
				draws[i] = JrArcName.ARC_PATH_NE; break;
			case JrArcName.ARC_PATH_BIGSO :
				draws[i] = JrArcName.ARC_PATH_BIGNE; break;
			case JrArcName.ARC_PATH_SE :
				draws[i] = JrArcName.ARC_PATH_NO; break;
			case JrArcName.ARC_PATH_BIGSE :
				draws[i] = JrArcName.ARC_PATH_BIGNO; break;
			case JrArcName.ARC_ROAD_NO :
				draws[i] = JrArcName.ARC_ROAD_SE; break;
			case JrArcName.ARC_ROAD_BIGNO :
				draws[i] = JrArcName.ARC_ROAD_BIGSE; break;
			case JrArcName.ARC_ROAD_NE :
				draws[i] = JrArcName.ARC_ROAD_SO; break;
			case JrArcName.ARC_ROAD_BIGNE :
				draws[i] = JrArcName.ARC_ROAD_BIGSO; break;
			case JrArcName.ARC_ROAD_SO :
				draws[i] = JrArcName.ARC_ROAD_NE; break;
			case JrArcName.ARC_ROAD_BIGSO :
				draws[i] = JrArcName.ARC_ROAD_BIGNE; break;
			case JrArcName.ARC_ROAD_SE :
				draws[i] = JrArcName.ARC_ROAD_NO; break;
			case JrArcName.ARC_ROAD_BIGSE :
				draws[i] = JrArcName.ARC_ROAD_BIGNO; break;
            default : draws[i] = tmp[i]; break;
			}
		}
		
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentArc(JrArcName.ARC_NOTHING);
		JrActionCenter.RefreshActionsArc();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		int current = cnt.getCurrentArc();
		if (current == JrArcName.ARC_NOTHING)
			cnt.setCurrentArc(JrArcName.ARC_COUNT - 1);
		else
			cnt.setCurrentArc(current-1);
		JrActionCenter.RefreshActionsArc();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		int current = cnt.getCurrentArc();
		if (current == (JrArcName.ARC_COUNT - 1))
			cnt.setCurrentArc(JrArcName.ARC_NOTHING);
		else
			cnt.setCurrentArc(current+1);
		JrActionCenter.RefreshActionsArc();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentArc(JrArcName.ARC_COUNT - 1);
		JrActionCenter.RefreshActionsArc();
		return false;
	}
	
	
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		  
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		
	    for(i = -5; i < 6; i++) {
	    	dt.drawLine(ox - vx,oy + (i * pasy),ox + vx,oy + (i * pasy));
	        dt.drawLine(ox + (i * pasx),oy - vy,ox + (i * pasx),oy + vy);
	    }
	}
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int i;
		float x,y;
		float h,w;
		float px = ox - (5.0f * pasx);
		float py = oy - (5.0f * pasy);
		float pasx2 = pasx * 2.0f;
		float pasy2 = pasy * 2.0f;
		for(i = 0; i < 100; i++) {
			x = px + ((i % 10) * pasx);
			y = py + ((i / 10) * pasy);
			h = pasy2;
			w = pasx2;
			switch(filter.filterArc(draws[i])) {
			case JrArcName.ARC_PATH_BIGNO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_PATH_NO :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawArc(x,y,w,h,90,90);
				break;
			case JrArcName.ARC_PATH_BIGNE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_PATH_NE :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawArc(x-(w/2),y,w,h,0,90);
				break;
			case JrArcName.ARC_PATH_BIGSO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_PATH_SO :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawArc(x,y-(h/2),w,h,180,90);
				break;
			case JrArcName.ARC_PATH_BIGSE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_PATH_SE :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawArc(x-(w/2),y-(h/2),w,h,270,90);
				break;
			case JrArcName.ARC_ROAD_BIGNO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_NO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawArc(x,y,w,h,90,90);
				break;
			case JrArcName.ARC_ROAD_BIGNE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_NE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawArc(x-(w/2),y,w,h,0,90);
				break;
			case JrArcName.ARC_ROAD_BIGSO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_SO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawArc(x,y-(h/2),w,h,180,90);
				break;
			case JrArcName.ARC_ROAD_BIGSE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_SE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawArc(x-(w/2),y-(h/2),w,h,270,90);
				break;
			case JrArcName.ARC_PATH_NOSE :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_PATH_NESO :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			case JrArcName.ARC_ROAD_NOSE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_ROAD_NESO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			case JrArcName.ARC_PATH_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_PATHROAD_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			case JrArcName.ARC_ROAD_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_ROADPATH_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			default : break;
			}
		}
	}
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
		float x,y;
		float h,w;
		float px = ox - (5.0f * pasx);
		float py = oy - (5.0f * pasy);
		float pasx2 = pasx * 2.0f;
		float pasy2 = pasy * 2.0f;
		for(i = 0; i < 100; i++) {
			x = px + ((i % 10) * pasx);
			y = py + ((i / 10) * pasy);
			h = pasy2;
			w = pasx2;
			switch(filter.filterArc(draws[i])) {
			case JrArcName.ARC_ROAD_BIGNO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_NO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawArc(x,y,w,h,90,90);
				break;
			case JrArcName.ARC_ROAD_BIGNE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_NE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawArc(x-(w/2),y,w,h,0,90);
				break;
			case JrArcName.ARC_ROAD_BIGSO :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_SO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawArc(x,y-(h/2),w,h,180,90);
				break;
			case JrArcName.ARC_ROAD_BIGSE :
				h += pasy2; w += pasx2;
			case JrArcName.ARC_ROAD_SE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawArc(x-(w/2),y-(h/2),w,h,270,90);
				break;
			case JrArcName.ARC_ROAD_NOSE :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_ROAD_NESO :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			case JrArcName.ARC_PATHROAD_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				break;
			case JrArcName.ARC_ROAD_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(x,y+pasy,x+pasx,y);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			case JrArcName.ARC_ROADPATH_CROSS :
				dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE,active);
				dt.drawLine(x,y,x+pasx,y+pasy);
				break;
			default : break;
			}
		}
	}
		
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
			if (isBigArc(cnt.getCurrentArc())) {
				int x = pos % 10;
				int y = pos / 10;
				x -= (x == 9)? 1 : 0;
				y -= (y == 9)? 1 : 0;
				pos = (y * 10) + x;
			}
			draws[pos] = cnt.getCurrentArc();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
			draws[pos] = JrArcName.ARC_NOTHING;
			return true;
		}
		return false;
	}
	public int getZoneIndex(int x,int y) {
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		if ((x < ox-vx) || (x > (ox+vx)) || (y < (oy-vy)) || (y > (oy+vy)))
			return -1;
		if ((pasx < 1) || (pasy < 1))
			return -1;
		int px = (int)((((float)x) - (ox-vx)) / pasx);
		int py = (int)((((float)y) - (oy-vy)) / pasy);
		return (py * 10) + px;
	}
	public boolean isBigArc(int arc) {
		switch(arc) {
		case JrArcName.ARC_PATH_BIGNO :
		case JrArcName.ARC_PATH_BIGNE :
		case JrArcName.ARC_PATH_BIGSO :
		case JrArcName.ARC_PATH_BIGSE :
		case JrArcName.ARC_ROAD_BIGNO :
		case JrArcName.ARC_ROAD_BIGNE :
		case JrArcName.ARC_ROAD_BIGSO :
		case JrArcName.ARC_ROAD_BIGSE :
			return true;
		default : break;
		}
		return false;
	}	
}
