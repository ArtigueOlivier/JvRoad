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
import book.map.objets.JrObjects;

import application.actions.JrActionCenter;
import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import names.JrColorName;
import names.JrMapName;
import names.JrObjName;
import names.JrPaletteName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapObjects extends JrMap {
	private int objects[] = new int [100];
	public JrMapObjects() {
		super();
		for(int i = 0; i < 100; i++)
			objects[i] = JrObjName.OBJ_NOTHING;
	}
	
	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(objects[0]);
		for(int i = 1; i < 100; i++) {
			buff.append(';');
			buff.append(objects[i]);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {		
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String items[] = str.split(";");
		
		count = items.length;
		for(i = 0; i < 100; i++) {
			objects[i] = (i >= count)? JrObjName.OBJ_NOTHING : Integer.parseInt(items[i]);
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		read(file,entry);
	}
	
	public void readVeryOld(String[] data) {
		int i,val,pos;
		int cpt = data.length;
		for(i = 0; i < 100; i++)
			objects[i] = JrObjName.OBJ_NOTHING;
		if (cpt == 64) {
		    for(i = 0; i < 64; i++) {
		    	pos = (((i / 8) + 1) * 10) + ((i % 8) + 1);
		    	switch(Integer.parseInt(data[i])) {
		    	case  1 : 
		    	case  2 : objects[pos] = JrObjName.OBJ_HOUSE; break;
		    	case  3 : 
		    	case  4 : objects[pos] = JrObjName.OBJ_CROIX; break;
		    	case 23 : 
		    	case  6 : objects[pos] = JrObjName.OBJ_ARBRE; break;
		    	case 24 : 
		    	case  7 : objects[pos] = JrObjName.OBJ_SAPIN; break;
		    	case  8 : objects[pos] = JrObjName.OBJ_HRAIL; break;
		    	case  9 : objects[pos] = JrObjName.OBJ_VRAIL; break;
		    	case 10 : objects[pos] = JrObjName.OBJ_ROND; break;
		    	case 11 : objects[pos] = JrObjName.OBJ_CERCLE; break;
		    	case 22 :
		    	case 25 : objects[pos] = JrObjName.OBJ_FEUX; break;
		    	case 40 : objects[pos] = JrObjName.OBJ_HRIVER; break;
		    	case 43 : objects[pos] = JrObjName.OBJ_HOPITAL; break;
		    	case 44 : objects[pos] = JrObjName.OBJ_EGLISE; break;
		    	case 45 : objects[pos] = JrObjName.OBJ_MOSQUEE; break;
		    	case 46 : objects[pos] = JrObjName.OBJ_STATION; break;
		    	case 47 : objects[pos] = JrObjName.OBJ_PARKING; break;
		    	case 48 : objects[pos] = JrObjName.OBJ_VRIVER; break;
		    	case 51 : 
		    	case 53 : objects[pos] = JrObjName.OBJ_STOP; break;
		    	case 52 : 
		    	case 54 : objects[pos] = JrObjName.OBJ_CEDER; break;
		    	default : break;
		    	}
		    }
		}
	}
	
	public void inverse() {
		int tmp [] = new int [100];
		int i;
		boolean bigH,bigW;
		
		for(i = 0; i < 100; i++) {
			bigH = isBigHeightObject(objects[i]);
			bigW = isBigWidthObject(objects[i]);
			if ((bigH == false) && (bigW == false)) {
				tmp[99 - i] = objects[i];
			}
		}
		for(i = 0; i < 99; i++) {
			bigH = isBigHeightObject(objects[i]);
			bigW = isBigWidthObject(objects[i]);
			if ((bigH == false) && (bigW == true)) {
				tmp[98 - i] = objects[i];
			}
		}
		for(i = 0; i < 90; i++) {
			bigH = isBigHeightObject(objects[i]);
			bigW = isBigWidthObject(objects[i]);
			if ((bigH == true) && (bigW == false)) {
				tmp[89 - i] = objects[i];
			}
		}
		for(i = 0; i < 89; i++) {
			bigH = isBigHeightObject(objects[i]);
			bigW = isBigWidthObject(objects[i]);
			if ((bigH == true) && (bigW == true)) {
				tmp[88 - i] = objects[i];
			}
		}
		for(i = 0; i < 100; i++)
			objects[i] = tmp[i];
	}
	
	public JrMap copy() {
		JrMapObjects map = new JrMapObjects();
		for(int i = 0; i < 100; i++) 
			map.objects[i] = objects[i];
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_OBJECTS;		
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_OBJECTS_NAME;
	}
	
	public int getOriginCount() {
		int cpt = 0;		
		for(int i = 0; i < 100; i++) 
			cpt += (objects[i] == JrObjName.OBJ_ROND)? 1 : 0;
		return cpt;
	}	
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentObject(JrObjName.GetFirst());
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		cnt.setCurrentObject(JrObjName.GetPrevious(cnt.getCurrentObject()));
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		cnt.setCurrentObject(JrObjName.GetNext(cnt.getCurrentObject()));
		JrActionCenter.RefreshActionsObject();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentObject(JrObjName.GetLast());
		JrActionCenter.RefreshActionsObject();
		return false;
	}
		
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int i;
		float x,y,w,h;
		for(i = 0; i < 100; i++) {
			x = ox + (((i % 10) - 5) * pasx);
			y = oy + (((i / 10) - 5) * pasy);
			w = pasx * ((isBigWidthObject(objects[i]))? 2 : 1);
			h = pasy * ((isBigHeightObject(objects[i]))? 2 : 1);
			if (objects[i] != JrObjName.OBJ_NOTHING) {
				if (JrObjects.IsDrawOver(objects[i]) == false) 
					JrObjects.Draw(objects[i],dt,x,y,w,h,active,currentView);
			}
		}
	}
	
	public void drawOver(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int i;
		float x,y,w,h;
		for(i = 0; i < 100; i++) {
			x = ox + (((i % 10) - 5) * pasx);
			y = oy + (((i / 10) - 5) * pasy);
			w = pasx * ((isBigWidthObject(objects[i]))? 2 : 1);
			h = pasy * ((isBigHeightObject(objects[i]))? 2 : 1);
			if (JrObjects.IsDrawOver(objects[i]) == true) 
				JrObjects.Draw(objects[i],dt,x,y,w,h,active,currentView);			
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
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		if (cnt.getCurrentObject() == JrObjName.OBJ_NOTHING)
			return handleRightButton(evt,cnt);
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
		    if (isBigHeightObject(cnt.getCurrentObject()))
		        pos -= (pos >= 90)? 10 : 0;
		    if (isBigWidthObject(cnt.getCurrentObject()))
		        pos -= ((pos % 10) == 9)? 1 : 0;
			objects[pos] = cnt.getCurrentObject();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {	
			if (objects[pos] != JrObjName.OBJ_NOTHING) {
				objects[pos] = JrObjName.OBJ_NOTHING;
				return true;
			}
			if ((pos > 10) && (objects[pos-10] != JrObjName.OBJ_NOTHING)) {
				if (isBigHeightObject(objects[pos-10])) {
					objects[pos-10] = JrObjName.OBJ_NOTHING;
					return true;
				}
			}
			if (((pos%10) > 0) && (objects[pos-1] != JrObjName.OBJ_NOTHING)) {
				if (isBigWidthObject(objects[pos-1])) {
					objects[pos-1] = JrObjName.OBJ_NOTHING;
					return true;
				}
			}
			if ((pos > 10) && ((pos%10) > 0) && (objects[pos-11] != JrObjName.OBJ_NOTHING)) {
				if (isBigWidthObject(objects[pos-11]) && isBigHeightObject(objects[pos-11])) {
					objects[pos-11] = JrObjName.OBJ_NOTHING;
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
	public boolean isBigHeightObject(int obj) {
		return JrObjects.IsBigHeight(obj);
	}	
	public boolean isBigWidthObject(int obj) {
		return JrObjects.IsBigWidth(obj);
	}	

}
