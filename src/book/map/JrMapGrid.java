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
public class JrMapGrid extends JrMap {
	private int lines[] = new int [220];
	
	public JrMapGrid() {
		super();		
		int i;
		
		for(i = 0; i < 220; i++) {
			lines[i] = JrTraitName.TRAIT_NONE;
		}
	}

	public void save(JrFileSave file,String entry) {		
		StringBuffer buff = new StringBuffer();
		buff.append(lines[0]);
		for(int i = 1; i < 220; i++) {
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
		for(i = 0; i < 220; i++) {
			lines[i] = (i >= count)? JrTraitName.TRAIT_NONE : Integer.parseInt(items[i]);
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		read(file,entry);
	}
	
	public void readVeryOld(String[] data) {
		int x,y,i;
		int cpt = data.length;
		if (cpt < 2)
		    return;
		if ((Integer.parseInt(data[0]) == 1) && (cpt == 114)) {
		    for(i = 2; i < 114; i++) {
		    	if (i < 58) {
		    		x = ((i - 2) % 7) + 112;
		    		y = (((i - 2) / 7) + 1) * 11;
		    	}
		    	else {
		    		x = ((i - 58) % 8) + 1;
		    		y = (((i - 58) / 8) + 2) * 10;
		    	}
		    	switch(Integer.parseInt(data[i])) {
		    	case 1 : lines[x+y] = JrTraitName.TRAIT_PATH; break;
		    	case 2 : lines[x+y] = JrTraitName.TRAIT_PATH_DEST; break;
		    	case 3 : lines[x+y] = JrTraitName.TRAIT_PATH_DESTINV; break;
		    	case 4 : lines[x+y] = JrTraitName.TRAIT_PATH_SRC; break;
		    	case 5 : lines[x+y] = JrTraitName.TRAIT_PATH_SRCINV; break;
		    	case 6 :
		    	case 7 :
		    	case 8 : lines[x+y] = JrTraitName.TRAIT_PATH; break;
		    	default: lines[x+y] = JrTraitName.TRAIT_NONE; break;
		    	}
		    }
		}
	}
		
	public JrMap copy() {
		JrMapGrid map = new JrMapGrid();
		for(int i = 0; i < 220; i++)
			map.lines[i] = lines[i];
		return map;
	}
	
	public int getName() {
		return JrMapName.MAP_GRID_NAME;
	}

	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_TRAITS;
	}
	
	public void rotation() {
		int tmp [] = new int [220];
		int x,y,i;
		
		for(x = 0; x < 10; x++) {
			for(y = 0; y < 11; y++) {
				tmp[x + (y * 10)] = lines[209+y-(x*11)];
			}
		}
		for(x = 0; x < 11; x++) {
			for(y = 0; y < 10; y++) {
				tmp[110 + x + (y * 11)] = lines[100+y-(x*10)];
			}
		}
		
		for(i = 0; i < 110; i++) {
			switch(tmp[i]) {
			case JrTraitName.TRAIT_SENTIER_DEST :
				lines[i] = JrTraitName.TRAIT_SENTIER_DESTINV;
				break;
			case JrTraitName.TRAIT_SENTIER_DESTINV :
				lines[i] = JrTraitName.TRAIT_SENTIER_DEST;
				break;
			case JrTraitName.TRAIT_SENTIER_SRC :
				lines[i] = JrTraitName.TRAIT_SENTIER_SRCINV;
				break;
			case JrTraitName.TRAIT_SENTIER_SRCINV :
				lines[i] = JrTraitName.TRAIT_SENTIER_SRC;
				break;
			case JrTraitName.TRAIT_PATH_DEST :
				lines[i] = JrTraitName.TRAIT_PATH_DESTINV;
				break;
			case JrTraitName.TRAIT_PATH_DESTINV :
				lines[i] = JrTraitName.TRAIT_PATH_DEST;
				break;
			case JrTraitName.TRAIT_PATH_SRC :
				lines[i] = JrTraitName.TRAIT_PATH_SRCINV;
				break;
			case JrTraitName.TRAIT_PATH_SRCINV :
				lines[i] = JrTraitName.TRAIT_PATH_SRC;
				break;
			case JrTraitName.TRAIT_ROAD_DEST :
				lines[i] = JrTraitName.TRAIT_ROAD_DESTINV;
				break;
			case JrTraitName.TRAIT_ROAD_DESTINV :
				lines[i] = JrTraitName.TRAIT_ROAD_DEST;
				break;
			case JrTraitName.TRAIT_ROAD_SRC :
				lines[i] = JrTraitName.TRAIT_ROAD_SRCINV;
				break;
			case JrTraitName.TRAIT_ROAD_SRCINV :
				lines[i] = JrTraitName.TRAIT_ROAD_SRC;
				break;
			case JrTraitName.TRAIT_NATIONAL_DEST :
				lines[i] = JrTraitName.TRAIT_NATIONAL_DESTINV;
				break;
			case JrTraitName.TRAIT_NATIONAL_DESTINV :			
				lines[i] = JrTraitName.TRAIT_NATIONAL_DEST;
				break;
			case JrTraitName.TRAIT_NATIONAL_SRC :
				lines[i] = JrTraitName.TRAIT_NATIONAL_SRCINV;
				break;
			case JrTraitName.TRAIT_NATIONAL_SRCINV :			
				lines[i] = JrTraitName.TRAIT_NATIONAL_SRC;
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_DEST :
				lines[i] = JrTraitName.TRAIT_DOUBLEROAD_DESTINV;
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
				lines[i] = JrTraitName.TRAIT_DOUBLEROAD_DEST;
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_SRC :
				lines[i] = JrTraitName.TRAIT_DOUBLEROAD_SRCINV;
				break;
			case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
				lines[i] = JrTraitName.TRAIT_DOUBLEROAD_SRC;
				break;
			default : lines[i] = tmp[i]; 
			}
			
		}
		for(i = 110; i < 220; i++)
			lines[i] = tmp[i];
	}
	
	public void inverse() {
		int tmp [] = new int [220];
		int i;
		for(i = 0; i < 110; i++)
			tmp[109 - i] = lines[i];
		for(i = 110; i < 220; i++)
			tmp[219 -i +110] = lines[i];
		for(i = 0; i < 220; i++)
			lines[i] = inverseTraitAndSens(tmp[i]);
	}
	
	public int getOriginCount() {
		int cpt = 0;		
		for(int i = 0; i < 220; i++) {
			cpt += (JrTraitName.IsAnOrigin(lines[i]) == true)? 1 : 0;
		}
		return cpt;
	}
	public int getDestinationCount() {
		int cpt = 0;		
		for(int i = 0; i < 220; i++) {
			cpt += (JrTraitName.IsAnDestination(lines[i]) == true)? 1 : 0;
		}
		return cpt;
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
		JrPoint pti = new JrPoint();
		JrPoint ptf = new JrPoint();		
		float pasr = dt.getPenWidth(JrPenName.PEN_ROAD_BLACK);
		float pasn = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK);

		for(i = 0; i < 220; i++) {
		    if (lines[i] != JrTraitName.TRAIT_NONE) 
		    	indexToPoint(i,pti,ptf);
		    switch(filter.filterTrait(lines[i])) {
		    case JrTraitName.TRAIT_PATH :
		    case JrTraitName.TRAIT_PATH_DEST :
		    case JrTraitName.TRAIT_PATH_DESTINV :
		    case JrTraitName.TRAIT_PATH_SRC :
		    case JrTraitName.TRAIT_PATH_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_ROAD_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK, active);
		        if (i < 110)
		         	ptf.x -= pasr;
		        else
		         	ptf.y -= pasr;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK, active);
		        if (i < 110)
		         	pti.x += pasr;
		        else
		         	pti.y += pasr;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_TRAIN :
		    case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		    	dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK, active);
	        	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_SENTIER :
		    case JrTraitName.TRAIT_SENTIER_DEST :
		    case JrTraitName.TRAIT_SENTIER_DESTINV :
		    case JrTraitName.TRAIT_SENTIER_SRC :
		    case JrTraitName.TRAIT_SENTIER_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
	        	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
		    case JrTraitName.TRAIT_NATIONAL_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK, active);
		        if (i < 110)
		         	ptf.x -= pasn;
		        else
		         	ptf.y -= pasn;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK, active);
		        if (i < 110)
		         	pti.x += pasn;
		        else
		         	pti.y += pasn;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_RIVER :
		    case JrTraitName.TRAIT_NATIONAL :
		    case JrTraitName.TRAIT_NATIONAL_SRC :
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
		    case JrTraitName.TRAIT_DOUBLEROAD :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK, active);
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    default: break;
		    }
		}
	}
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
		JrPoint pti = new JrPoint();
		JrPoint ptf = new JrPoint();		
		float pasr = dt.getPenWidth(JrPenName.PEN_ROAD_BLACK);
		float pasn = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK);

		for(i = 0; i < 220; i++) {
		    if (lines[i] != JrTraitName.TRAIT_NONE) 
		    	indexToPoint(i,pti,ptf);
		    switch(filter.filterTrait(lines[i])) {
		    case JrTraitName.TRAIT_ROAD_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE, active);
		        if (i < 110)
		         	ptf.x -= pasr;
		        else
		         	ptf.y -= pasr;
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE, active);
		        if (i < 110)
		         	pti.x += pasr;
		        else
		         	pti.y += pasr;
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_TRAIN :
		    case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		    	dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE, active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL_DEST :
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE, active);
		        if (i < 110)
		         	ptf.x -= pasn;
		        else
		         	ptf.y -= pasn;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE, active);
		        if (i < 110)
		         	pti.x += pasn;
		        else
		         	pti.y += pasn;
		        dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL :
		    case JrTraitName.TRAIT_NATIONAL_SRC :
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
		    case JrTraitName.TRAIT_DOUBLEROAD :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE, active);
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_RIVER :
		        dt.selectDefinedPen(JrPenName.PEN_RIVER_BLUE, active);
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    default: break;
		    }
		}
	}
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
		JrPoint pti = new JrPoint();
		JrPoint ptf = new JrPoint();		

		float pasn = dt.getPenWidth(JrPenName.PEN_NATIONAL_BLACK);
		
		for(i = 0; i < 220; i++) {
		    if (lines[i] != JrTraitName.TRAIT_NONE) 
		    	indexToPoint(i,pti,ptf);
		    switch(filter.filterTrait(lines[i])) {
		    case JrTraitName.TRAIT_NATIONAL_DEST :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
		        if (i < 110)
		        	dt.drawLine(pti.x,pti.y,ptf.x-pasn,ptf.y);
		        else
		        	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y-pasn);
		    case JrTraitName.TRAIT_ROAD_DEST :
		    case JrTraitName.TRAIT_SENTIER_DEST :
		    case JrTraitName.TRAIT_PATH_DEST :
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawDestination(dt,pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
		        if (i < 110)
		        	dt.drawLine(pti.x+pasn,pti.y,ptf.x,ptf.y);
		        else
		        	dt.drawLine(pti.x,pti.y+pasn,ptf.x,ptf.y);
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		    case JrTraitName.TRAIT_SENTIER_DESTINV :
		    case JrTraitName.TRAIT_PATH_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
	    		drawDestination(dt,ptf.x,ptf.y,pti.x,pti.y);
		        break;
		    case JrTraitName.TRAIT_TRAIN :
				dt.selectDefinedPen(JrPenName.PEN_TRAIN_BLACK,active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
	    		break;
		    case JrTraitName.TRAIT_NATIONAL_SRC :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_SENTIER_SRC :
		    case JrTraitName.TRAIT_PATH_SRC :
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawOrigin(dt,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		    case JrTraitName.TRAIT_SENTIER_SRCINV :
		    case JrTraitName.TRAIT_PATH_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawOrigin(dt,pti.x,pti.y);
		        break;
		    case JrTraitName.TRAIT_NATIONAL :
				dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK,active);
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		    	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		        if (i < 110)
		        	dt.drawLine(pti.x,pti.y,ptf.x-pasn,ptf.y);
		        else
		        	dt.drawLine(pti.x,pti.y,ptf.x,ptf.y-pasn);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawDestination(dt,pti.x,pti.y,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		        if (i < 110)
		        	dt.drawLine(pti.x+pasn,pti.y,ptf.x,ptf.y);
		        else
		        	dt.drawLine(pti.x,pti.y+pasn,ptf.x,ptf.y);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
	    		drawDestination(dt,ptf.x,ptf.y,pti.x,pti.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawOrigin(dt,ptf.x,ptf.y);
		        break;
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
				dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
	    		dt.drawLine(pti.x,pti.y,ptf.x,ptf.y);
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		    	drawOrigin(dt,pti.x,pti.y);
		        break;
		    default: break;
		    }
		}
	}
	
		public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = pointToIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 220)) {
			lines[pos] = cnt.getCurrentTrait();
			return true;
		}
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		int pos = pointToIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 220)) {
			lines[pos] = JrTraitName.TRAIT_NONE;
			return true;
		}
		return false;
	}

	public void indexToPoint(int index,JrPoint pti,JrPoint ptf) {
	  if (index < 110) {
	    int x = (index % 10);
	    int y = (index / 10);
	    pti.x = ox + ((x - 5) * pasx);
	    pti.y = oy + ((y - 5) * pasy);
	    ptf.x = ox + ((x - 4) * pasx);
	    ptf.y = oy + ((y - 5) * pasy);
	  }
	  else {
	    int x = ((index-110) % 11);
	    int y = ((index-110) / 11);
	    pti.x = ox + ((x - 5) * pasx);
	    pti.y = oy + ((y - 5) * pasy);
	    ptf.x = ox + ((x - 5) * pasx);
	    ptf.y = oy + ((y - 4) * pasy);
	  }
	}
	public int pointToIndex(float x,float y) {
		int xi = -111;
		int yi = -111;
		float vx = -5.0f;
		float vy = -5.0f;
		float px = ox - (5.0f * pasx);
		float py = oy - (5.0f * pasy);
		int i,index;

		for(i = -5; i < 6; i++) {
			if ((x >= (px -2)) && (x <= (px + 2)))
				xi = i;
		    if ((y >= (py -2)) && (y <= (py + 2)))
		    	yi = i;
		    vx = (x > (px -2))? i : vx;
		    vy = (y > (py -2))? i : vy;
		    px += pasx;
		    py += pasy;
		}
		if ((xi != -111) && (yi != -111))
		    return -1;
		if ((xi != -111) || (yi != -111)) {
		    if (xi == -111) {
		    	return (int)(vx + 5) + ((yi + 5) * 10);
		    }
		    else {
		    	return (int)(110 + ((xi + 5) + ((vy + 5) * 11)));
		    }
		}
		return -1;		
	}
}
