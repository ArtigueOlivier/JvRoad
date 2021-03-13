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
import application.actions.JrActionMenu;
import application.dialogs.JrBorneDialog;

import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import names.JrBorneName;
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;
import names.JrRegionFontName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapBorne extends JrMap {
	private int bornes[] = new int [100];
	private int numero[] = new int [100];
	public JrMapBorne() {
		super();
		for(int i = 0; i < 0; i++) {
			bornes[i] = JrBorneName.BORNE_NONE;
			numero[i] = 1;
		}
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_BORNE;
	}

	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(bornes[0]);
		buff.append(';');
		buff.append(numero[0]);
		for(int i = 1; i < 100; i++) {
			buff.append(';');
			buff.append(bornes[i]);
			buff.append(';');
			buff.append(numero[i]);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {		
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String items[] = str.split(";");
		
		count = items.length;
		for(i = 0; i < 100; i++) {
			bornes[i] = (i >= count)? JrBorneName.BORNE_NONE : Integer.parseInt(items[2*i]);
			numero[i] = (i >= count)? 1 : Integer.parseInt(items[(2*i)+1]);
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		read(file,entry);
	}
	
	public JrMap copy() {
		JrMapBorne map = new JrMapBorne();
		for(int i = 0; i < 100; i++) {
			map.bornes[i] = bornes[i];
			map.numero[i] = numero[i];
		}
		return map;
	}
	
	public int getName() {
		return JrMapName.MAP_BORNE_NAME;
	}

	public void inverse() {
		int tmpb [] = new int [100];
		int tmpn [] = new int [100];
		int i;
		for(i = 0; i < 100; i++) {
			if (isBigCurrentBorne(bornes[i]) == false) {
				tmpb[99 - i] = bornes[i];
				tmpn[99 - i] = numero[i];
			}
		}
		for(i = 0; i < 99; i++) {
			if (isBigCurrentBorne(bornes[i]) == true) {
				tmpb[98 - i] = bornes[i];
				tmpn[98 - i] = numero[i];
			}
		}
		for(i = 0; i < 100; i++) {
			bornes[i] = tmpb[i];
			numero[i] = tmpn[i];
		}
	}

	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentBorne(JrBorneName.BORNE_NONE);
		JrActionCenter.RefreshActionsBorne();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		int current = cnt.getCurrentBorne();
		if (current == JrBorneName.BORNE_NONE)
			cnt.setCurrentBorne(JrBorneName.BORNE_COUNT - 1);
		else
			cnt.setCurrentBorne(current-1);
		JrActionCenter.RefreshActionsBorne();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		int current = cnt.getCurrentBorne();
		if (current == (JrBorneName.BORNE_COUNT - 1))
			cnt.setCurrentBorne(JrBorneName.BORNE_NONE);
		else
			cnt.setCurrentBorne(current+1);
		JrActionCenter.RefreshActionsBorne();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentBorne(JrBorneName.BORNE_COUNT - 1);
		JrActionCenter.RefreshActionsBorne();
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
	}
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
	}
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		float x,y,w;
		float dx = pasx * 0.03f; 
		float dy = pasy * 0.03f; 
		for(int i = 0; i < 100; i++) {
			if (bornes[i] != JrBorneName.BORNE_NONE) {
				x = ox + (((i % 10) - 5) * pasx) + dx;
				y = oy + (((i / 10) - 5) * pasy) + dy;
				w = ((isBigCurrentBorne(bornes[i]) == true)? (pasx*2) : pasx) - dx - dx;
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
				dt.drawRectangle(x,y,w,pasy-dy-dy,true);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				dt.drawRectangle(x,y,w,pasy-dy-dy,false);
				switch(bornes[i]) {
				case JrBorneName.BORNE_NATIONALEBIG :
				case JrBorneName.BORNE_NATIONALE :
					dt.drawText(GetWord("TxtSymbolNationale") + numero[i],x,y,w,pasy-dy-dy,JrRegionFontName.REGION_FONT_BORNE,1); break;
				case JrBorneName.BORNE_DEPARTEMENTALEBIG :
				case JrBorneName.BORNE_DEPARTEMENTALE :
					dt.drawText(GetWord("TxtSymbolDepartementale") + numero[i],x,y,w,pasy-dy-dy,JrRegionFontName.REGION_FONT_BORNE,1); break;
				case JrBorneName.BORNE_COMMUNALEBIG :
				case JrBorneName.BORNE_COMMUNALE :
					dt.drawText(GetWord("TxtSymbolCommunale") + numero[i],x,y,w,pasy-dy-dy,JrRegionFontName.REGION_FONT_BORNE,1); break;
				default :
					dt.drawText(GetWord("TxtSymbolGR"),x,y,w,pasy-dy-dy,JrRegionFontName.REGION_FONT_BORNE,1); break;				
				}
			}
		}		
	}
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
			if (isBigCurrentBorne(cnt.getCurrentBorne())) {
				int x = pos % 10;
				int y = pos / 10;
				x -= (x == 9)? 1 : 0;
				pos = (y * 10) + x;
			}
			switch(cnt.getCurrentBorne()) {
			case JrBorneName.BORNE_NONE :
				handleRightButton(evt,cnt);
				break;
			case JrBorneName.BORNE_GRBIG :
			case JrBorneName.BORNE_GR :
				bornes[pos] = cnt.getCurrentBorne();
				numero[pos] = 1;				
				break;
			default :
				JrBorneDialog dlg = new JrBorneDialog(JrActionMenu.GetMainFrame());
				if (dlg.execute() == true) {
					bornes[pos] = cnt.getCurrentBorne();
					numero[pos] = dlg.getNumero();
				}
			}			
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = getZoneIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 100)) {
			if (bornes[pos] != JrBorneName.BORNE_NONE) {
				bornes[pos] = JrBorneName.BORNE_NONE;
				numero[pos] = 1;
				return true;
			}
			int x = pos % 10;
			int y = pos / 10;
			int npos = (y * 10) + x - 1;
			if ((x > 0) && isBigCurrentBorne(bornes[npos])) {
				bornes[npos] = JrBorneName.BORNE_NONE;
				numero[npos] = 1;
				return true;				
			}
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
	public boolean isBigCurrentBorne(int br) {
		switch(br) {
		case JrBorneName.BORNE_NATIONALEBIG :
		case JrBorneName.BORNE_DEPARTEMENTALEBIG :
		case JrBorneName.BORNE_COMMUNALEBIG :
		case JrBorneName.BORNE_GRBIG :
			return true;
		default : break;
		}
		return false;
	}	

}
