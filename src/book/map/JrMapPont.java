/*
 * Created on 23 févr. 2006
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
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;
import names.JrPontName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapPont extends JrMap {
	private int ponts[] = new int [100];
	public JrMapPont() {
		super();
		for(int i = 0; i < 100; i++)
			ponts[i] = JrPontName.PONT_NONE;
	}
	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(ponts[0]);
		for(int i = 1; i < 100; i++) {
			buff.append(';');
			buff.append(ponts[i]);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {		
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String items[] = str.split(";");
		
		count = items.length;
		for(i = 0; i < 100; i++) {
			ponts[i] = (i >= count)? JrPontName.PONT_NONE : Integer.parseInt(items[i]);
		}
	}
	public void inverse() {
		int tmp [] = new int [100];
		int i;
		boolean bigH,bigW;
		
		for(i = 0; i < 100; i++) {
			bigH = isBigHeightPont(ponts[i]);
			bigW = isBigWidthPont(ponts[i]);
			if ((bigH == false) && (bigW == false)) {
				tmp[99 - i] = ponts[i];
			}
		}
		for(i = 0; i < 99; i++) {
			bigH = isBigHeightPont(ponts[i]);
			bigW = isBigWidthPont(ponts[i]);
			if ((bigH == false) && (bigW == true)) {
				tmp[98 - i] = ponts[i];
			}
		}
		for(i = 0; i < 90; i++) {
			bigH = isBigHeightPont(ponts[i]);
			bigW = isBigWidthPont(ponts[i]);
			if ((bigH == true) && (bigW == false)) {
				tmp[89 - i] = ponts[i];
			}
		}
		for(i = 0; i < 89; i++) {
			bigH = isBigHeightPont(ponts[i]);
			bigW = isBigWidthPont(ponts[i]);
			if ((bigH == true) && (bigW == true)) {
				tmp[88 - i] = ponts[i];
			}
		}
		for(i = 0; i < 100; i++)
			ponts[i] = tmp[i];
	}

	public void rotation() {
		int x,y,i,rota;
		int tmp [] = new int [100];
		for(i = 0; i < 100; i++)
			tmp [i] = JrPontName.PONT_NONE;
		for(i = 0; i < 100; i++) {
			x = i % 10;
			y = i / 10;
			if (isBigWidthPont(ponts[i])) {
				x++; 
				rota = (x * 10) + (9 - y);
				x = rota % 10;
				y = rota / 10;
				x--;
				y--;
				tmp[x + (y * 10)] = ponts[i];
			}
			else {
				if (ponts[i] != JrPontName.PONT_NONE)
					tmp[(x * 10) + (9 - y)] = ponts[i];
			}
		}
		for(i = 0; i < 100; i++) {
			switch(tmp[i]) {
			case JrPontName.PONT_SENTIER_H :
				ponts[i] = JrPontName.PONT_SENTIER_V; break;
			case JrPontName.PONT_SENTIER_V :
				ponts[i] = JrPontName.PONT_SENTIER_H; break;
			case JrPontName.PONT_PATH_H :
				ponts[i] = JrPontName.PONT_PATH_V; break;
			case JrPontName.PONT_PATH_V :
				ponts[i] = JrPontName.PONT_PATH_H; break;
			case JrPontName.PONT_ROAD_H :
				ponts[i] = JrPontName.PONT_ROAD_V; break;
			case JrPontName.PONT_ROAD_V :
				ponts[i] = JrPontName.PONT_ROAD_H; break;
			case JrPontName.PONT_NATIONAL_H :
				ponts[i] = JrPontName.PONT_NATIONAL_V; break;
			case JrPontName.PONT_NATIONAL_V :
				ponts[i] = JrPontName.PONT_NATIONAL_H; break;
			case JrPontName.PONT_DOUBLEROAD_H :
				ponts[i] = JrPontName.PONT_DOUBLEROAD_V; break;
			case JrPontName.PONT_DOUBLEROAD_V :
				ponts[i] = JrPontName.PONT_DOUBLEROAD_H; break;
			case JrPontName.PONT_TRAIN_H :
				ponts[i] = JrPontName.PONT_TRAIN_V; break;
			case JrPontName.PONT_TRAIN_V :
				ponts[i] = JrPontName.PONT_TRAIN_H; break;
			case JrPontName.PONT_RIVER_H :
				ponts[i] = JrPontName.PONT_RIVER_V; break;
			case JrPontName.PONT_RIVER_V :
				ponts[i] = JrPontName.PONT_RIVER_H; break;
            default : ponts[i] = tmp[i]; break;
			}
		}
	}	
	public JrMap copy() {
		JrMapPont map = new JrMapPont();
		for(int i = 0; i < 100; i++) 
			map.ponts[i] = ponts[i];
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_PONTS;		
	}
	public int getName() {
		return JrMapName.MAP_PONT_NAME;
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentPont(JrPontName.GetFirst());
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		cnt.setCurrentPont(JrPontName.GetPrevious(cnt.getCurrentObject()));
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		cnt.setCurrentPont(JrPontName.GetNext(cnt.getCurrentObject()));
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentPont(JrPontName.GetLast());
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	public void drawOver(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int i;
		float x,y,w,h;
		for(i = 0; i < 100; i++) {
			x = ox + (((i % 10) - 5) * pasx);
			y = oy + (((i / 10) - 5) * pasy);
			w = pasx * ((isBigWidthPont(ponts[i]))? 2 : 1);
			h = pasy * ((isBigHeightPont(ponts[i]))? 2 : 1);
			if (ponts[i] != JrPontName.PONT_NONE) 
				drawPont(filter.filterPont(ponts[i]),dt,x,y,w,h,active,currentView);			
		}
	}

	public void drawFinalHandles(JrDrawTools dt,int currentView) {		
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		  
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(int i = -5; i < 6; i++) {
	    	dt.drawLine(ox - vx,oy + (i * pasy),ox + vx,oy + (i * pasy));
	        dt.drawLine(ox + (i * pasx),oy - vy,ox + (i * pasx),oy + vy);
	    }
	}
	
	public void drawPont(int pont,JrDrawTools dt,float x,float y,float w,float h,boolean active,int currentView) {
		float pasn = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK);
		float dw = (w - pasn) / 4;
		float dh = (h - pasn) / 4;
		
		dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
		dt.drawRectangle(x+dw,y+dh,w-(2*dw),h-(2*dh),true);		
        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
    	dt.drawLine(x,y,x+dw,y+dh);
    	dt.drawLine(x,y+h,x+dw,y+h-dh);
    	dt.drawLine(x+w,y,x+w-dw,y+dh);
    	dt.drawLine(x+w,y+h,x+w-dw,y+h-dh);  
    	int trait = JrPontName.toTrait(pont);
        if (JrPontName.IsVertical(pont) == true) {
        	dt.drawLine(x+dw,y+dh,x+dw,y+h-dh);
        	dt.drawLine(x+w-dw,y+dh,x+w-dw,y+h-dh);
        	drawRoadBlack(trait,dt,x+(w/2),y+dh,x+(w/2),y+h-dh,active);
        	drawRoadWhite(trait,dt,x+(w/2),y,x+(w/2),y+h,active);
        	drawRoadDeco(trait,dt,x+(w/2),y,x+(w/2),y+h,active);
        }
        else {        	
           	dt.drawLine(x+dw,y+dh,x+w-dw,y+dh);
           	dt.drawLine(x+dw,y+h-dh,x+w-dw,y+h-dh);
        	drawRoadBlack(trait,dt,x+dw,y+(h/2),x+w-dw,y+(h/2),active);
        	drawRoadWhite(trait,dt,x,y+(h/2),x+w,y+(h/2),active);
        	drawRoadDeco(trait,dt,x,y+(h/2),x+w,y+(h/2),active);
        }
	}

	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		if (cnt.getCurrentPont() == JrPontName.PONT_NONE)
			return handleRightButton(evt,cnt);
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
		    if (isBigHeightPont(cnt.getCurrentPont()))
		        pos -= (pos >= 90)? 10 : 0;
		    if (isBigWidthPont(cnt.getCurrentPont()))
		        pos -= ((pos % 10) == 9)? 1 : 0;
			ponts[pos] = cnt.getCurrentPont();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {	
			if (ponts[pos] != JrPontName.PONT_NONE) {
				ponts[pos] = JrPontName.PONT_NONE;
				return true;
			}
			if ((pos > 10) && (ponts[pos-10] != JrPontName.PONT_NONE)) {
				if (isBigHeightPont(ponts[pos-10])) {
					ponts[pos-10] = JrPontName.PONT_NONE;
					return true;
				}
			}
			if (((pos%10) > 0) && (ponts[pos-1] != JrPontName.PONT_NONE)) {
				if (isBigWidthPont(ponts[pos-1])) {
					ponts[pos-1] = JrPontName.PONT_NONE;
					return true;
				}
			}
			if ((pos > 10) && ((pos%10) > 0) && (ponts[pos-11] != JrPontName.PONT_NONE)) {
				if (isBigWidthPont(ponts[pos-11]) && isBigHeightPont(ponts[pos-11])) {
					ponts[pos-11] = JrPontName.PONT_NONE;
					return true;
				}
			}
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
	public boolean isBigHeightPont(int pont) {
		return (pont == JrPontName.PONT_NONE)? false : true;
	}	
	public boolean isBigWidthPont(int pont) {
		return (pont == JrPontName.PONT_NONE)? false : true;
	}	

}
