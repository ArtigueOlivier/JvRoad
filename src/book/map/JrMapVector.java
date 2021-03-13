/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.ListIterator;

import application.actions.JrActionCenter;
import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;
import book.map.utils.JrItemVector;

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
import names.JrVectorOperatorName;
import names.JrVectorTypeName;
import names.JrViewName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapVector extends JrMap {
	private JrItemVector current = new JrItemVector();
	private JrItemVector currentEdt = null;
	private int currentPoint = 0;
	private ArrayList items = new ArrayList();
	
	private JrPoint[] zones = new JrPoint [121];
	
	public JrMapVector() {
		super();
		for(int i = 0; i < 121; i++)
			zones[i] = new JrPoint();
	}
	
	public void save(JrFileSave file,String entry) {
		String str;
		StringBuffer buff = new StringBuffer();
		buff.append(items.size());
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			str = ";" + item.getFrom() + ";" + item.getTo() + ";" + item.getAux() + ";" + item.getType() + ";" + item.getTrait();
			buff.append(str);
		}
		file.addEntry(entry,buff.toString());
	}
	
	public void read(JrFileRead file,String entry) {	
		int i, count;
		String str = file.getStringValue(entry,"0;0");
		String s[] = str.split(";");
		
		count = s.length;
		for(i = 1; i < count; i+=5) {
			items.add(new JrItemVector(s[i],s[i+1],s[i+2],s[i+3],s[i+4]));
		}
	}
	
	public void readOld(JrFileRead file,String entry) {
		int i,j,x,y,count,trait,from,to;
		int cpt = file.getIntegerValue(entry,0);
		String se,str;
		
		for(i = 0; i < cpt; i++) {
			se = entry + "-" + i;
			str = file.getStringValue(se,"0;0;1");
			String sp[] = str.split(";");
			count = sp.length;
			if(count > 5) {
				trait = Integer.parseInt(sp[1]);
				switch(trait) {
				case JrTraitName.TRAIT_PATH_DEST :
					trait = JrTraitName.TRAIT_PATH_DESTINV; break;
				case JrTraitName.TRAIT_PATH_DESTINV :
					trait = JrTraitName.TRAIT_PATH_DEST; break;
				case JrTraitName.TRAIT_PATH_SRC :
					trait = JrTraitName.TRAIT_PATH_SRCINV; break;
				case JrTraitName.TRAIT_PATH_SRCINV :
					trait = JrTraitName.TRAIT_PATH_SRC; break;
				case JrTraitName.TRAIT_ROAD_DEST :
					trait = JrTraitName.TRAIT_ROAD_DESTINV; break;
				case JrTraitName.TRAIT_ROAD_DESTINV :
					trait = JrTraitName.TRAIT_ROAD_DEST; break;
				case JrTraitName.TRAIT_ROAD_SRC :
					trait = JrTraitName.TRAIT_ROAD_SRCINV; break;
				case JrTraitName.TRAIT_ROAD_SRCINV :
					trait = JrTraitName.TRAIT_ROAD_SRC; break;
				case JrTraitName.TRAIT_NATIONAL_DEST :
					trait = JrTraitName.TRAIT_NATIONAL_DESTINV; break;
				case JrTraitName.TRAIT_NATIONAL_DESTINV :
					trait = JrTraitName.TRAIT_NATIONAL_DEST; break;
				case JrTraitName.TRAIT_NATIONAL_SRC :
					trait = JrTraitName.TRAIT_NATIONAL_SRCINV; break;
				case JrTraitName.TRAIT_NATIONAL_SRCINV :
					trait = JrTraitName.TRAIT_NATIONAL_SRC; break;
				default: break;
				}
				for(j = 2; j < (count-2); j+=2) {
					x = Integer.parseInt(sp[j]) + 5;
					y = Integer.parseInt(sp[j+1]) + 5;
					from = (y * 11) + x;
					x = Integer.parseInt(sp[j+2]) + 5;
					y = Integer.parseInt(sp[j+3]) + 5;
					to = (y * 11) + x;
					items.add(new JrItemVector(from,to,trait));
				}
			}
		}
	}
	
	public JrMap copy() {
		JrMapVector map = new JrMapVector();
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			map.items.add(item.copy());
		}
		return map;
	}

	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_VECTOR;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_VECTOR_NAME;
	}

	public void rotation() {		
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			item.rotation();
		}
		if (current != null)
			current.rotation();
	}

	public void inverse() {		
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			item.inverse();
			item.setTrait(inverseTrait(item.getTrait()));
		}
		if (current != null) {
			current.inverse();
			current.setTrait(inverseTrait(current.getTrait()));
		}
	}
	
	public int getOriginCount() {
		int cpt = 0;	
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			cpt += (JrTraitName.IsAnOrigin(item.getTrait()) == true)? 1 : 0;
		}
		return cpt;
	}
	public int getDestinationCount() {
		int cpt = 0;		
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			cpt += (JrTraitName.IsAnDestination(item.getTrait()) == true)? 1 : 0;
		}
		return cpt;
	}
	
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentTraitVector(JrTraitName.TRAIT_PATH);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		int current = cnt.getCurrentTraitVector();
		if (current == JrTraitName.TRAIT_PATH)
			cnt.setCurrentTraitVector(JrTraitName.TRAIT_COUNT - 1);
		else
			cnt.setCurrentTraitVector(current-1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		int current = cnt.getCurrentTraitVector();
		if (current == (JrTraitName.TRAIT_COUNT - 1))
			cnt.setCurrentTraitVector(JrTraitName.TRAIT_PATH);
		else
			cnt.setCurrentTraitVector(current+1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentTraitVector(JrTraitName.TRAIT_COUNT - 1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public void initializeRegions(JrRegions regions) {
		super.initializeRegions(regions);
		for(int i = 0; i < 121; i++) {
		    zones[i].x = (((i % 11) - 5) * pasx) + ox;
		    zones[i].y = (((i / 11) - 5) * pasy) + oy;
		}
	}
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
	    float vx = 11 * pasx;
	    float vy = 11 * pasy;		
	    float px = ox - ((pasx * 11) / 2);
	    float py = oy - ((pasy * 11) / 2);
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(i = 0; i < 12; i++) {
	    	dt.drawLine(px+(i * pasx),py,px+(i * pasx),py+vy);
	    	dt.drawLine(px,py+(i * pasy),px+vx,py+(i * pasy));
	    }
	}
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		float xi,yi,xf,yf,xa,ya;
		JrItemVector item;
		ListIterator iter = items.listIterator();
		boolean marker = (active && currentView < JrViewName.VIEW_OBJ)? true : false;
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			xi = ox + (((item.getFrom() % 11) - 5) * pasx);
			yi = oy + (((item.getFrom() / 11) - 5) * pasy);
			xf = ox + (((item.getTo() % 11) - 5) * pasx);
			yf = oy + (((item.getTo() / 11) - 5) * pasy);
			xa = ox + (((item.getAux() % 11) - 5) * pasx);
			ya = oy + (((item.getAux() / 11) - 5) * pasy);
		    switch(filter.filterTrait(item.getTrait())) {
		    case JrTraitName.TRAIT_PATH :
		    case JrTraitName.TRAIT_PATH_DEST :
		    case JrTraitName.TRAIT_PATH_DESTINV :
		    case JrTraitName.TRAIT_PATH_SRC :
		    case JrTraitName.TRAIT_PATH_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),marker);
		        break;
		    case JrTraitName.TRAIT_TRAIN :
		    case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_ROAD_DEST :
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK, active);
	    		drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),marker);
		        break;
		    case JrTraitName.TRAIT_SENTIER :
		    case JrTraitName.TRAIT_SENTIER_DEST :
		    case JrTraitName.TRAIT_SENTIER_DESTINV :
		    case JrTraitName.TRAIT_SENTIER_SRC :
		    case JrTraitName.TRAIT_SENTIER_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),marker);
		        break;
		    case JrTraitName.TRAIT_RIVER :
		    case JrTraitName.TRAIT_NATIONAL :
		    case JrTraitName.TRAIT_NATIONAL_DEST :
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
		    case JrTraitName.TRAIT_NATIONAL_SRC :
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
		    case JrTraitName.TRAIT_DOUBLEROAD :
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),marker);
		        break;
		    default: break;
		    }
		}
	}
	
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		float xi,yi,xf,yf,xa,ya;
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			xi = ox + (((item.getFrom() % 11) - 5) * pasx);
			yi = oy + (((item.getFrom() / 11) - 5) * pasy);
			xf = ox + (((item.getTo() % 11) - 5) * pasx);
			yf = oy + (((item.getTo() / 11) - 5) * pasy);
			xa = ox + (((item.getAux() % 11) - 5) * pasx);
			ya = oy + (((item.getAux() / 11) - 5) * pasy);
		    switch(filter.filterTrait(item.getTrait())) {
		    case JrTraitName.TRAIT_PATH :
		    case JrTraitName.TRAIT_PATH_DEST :
		    case JrTraitName.TRAIT_PATH_DESTINV :
		    case JrTraitName.TRAIT_PATH_SRC :
		    case JrTraitName.TRAIT_PATH_SRCINV :
		        //dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	//drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType());
		        break;
		    case JrTraitName.TRAIT_TRAIN :
		    case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_ROAD_DEST :
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE, active);
	    		drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		        break;
		    case JrTraitName.TRAIT_NATIONAL :
		    case JrTraitName.TRAIT_NATIONAL_DEST :
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
		    case JrTraitName.TRAIT_NATIONAL_SRC :
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
		    case JrTraitName.TRAIT_DOUBLEROAD :
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		        break;
		    case JrTraitName.TRAIT_RIVER :
		        dt.selectDefinedPen(JrPenName.PEN_RIVER_BLUE, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		        break;
		    default: break;
		    }
		}
	}
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		float pas = dt.getPenWidth(JrPenName.PEN_ROAD_BLACK);
		float xi,yi,xf,yf,xa,ya;
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			xi = ox + (((item.getFrom() % 11) - 5) * pasx);
			yi = oy + (((item.getFrom() / 11) - 5) * pasy);
			xf = ox + (((item.getTo() % 11) - 5) * pasx);
			yf = oy + (((item.getTo() / 11) - 5) * pasy);
			xa = ox + (((item.getAux() % 11) - 5) * pasx);
			ya = oy + (((item.getAux() / 11) - 5) * pasy);
		    switch(filter.filterTrait(item.getTrait())) {
		    //case JrTraitName.TRAIT_PATH :
		    //case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_TRAIN :
		        dt.selectDefinedPen(JrPenName.PEN_TRAIN_BLACK, active);
	    		drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
	    		break;
		    case JrTraitName.TRAIT_NATIONAL :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
	    		drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
	    		break;
		    case JrTraitName.TRAIT_NATIONAL_SRC :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_SENTIER_SRC :
		    case JrTraitName.TRAIT_PATH_SRC :
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	drawOrigin(dt,xf,yf);
		    	break;
		    case JrTraitName.TRAIT_NATIONAL_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		    case JrTraitName.TRAIT_SENTIER_SRCINV :
		    case JrTraitName.TRAIT_PATH_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	drawOrigin(dt,xi,yi);
		    	break;
		    case JrTraitName.TRAIT_NATIONAL_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		    case JrTraitName.TRAIT_ROAD_DEST :
		    case JrTraitName.TRAIT_SENTIER_DEST :
		    case JrTraitName.TRAIT_PATH_DEST :
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
		    	dt.drawRectangle(xf-pas,yf-pas,pas*2,pas*2,true);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	if (item.getType() == JrVectorTypeName.VECTOR_SEGMENT)
		    		drawDestination(dt,xi,yi,xf,yf);
		    	else
		    		drawDestination(dt,xa,ya,xf,yf);
		    	break;
		    case JrTraitName.TRAIT_NATIONAL_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		    case JrTraitName.TRAIT_SENTIER_DESTINV :
		    case JrTraitName.TRAIT_PATH_DESTINV :
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
	    		dt.drawRectangle(xi-pas,yi-pas,pas*2,pas*2,true);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	if (item.getType() == JrVectorTypeName.VECTOR_SEGMENT)
		    		drawDestination(dt,xf,yf,xi,yi);
		    	else
		    		drawDestination(dt,xa,ya,xi,yi);
		    	break;
		    case JrTraitName.TRAIT_DOUBLEROAD :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
	    		drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
	    		break;
		    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	drawOrigin(dt,xf,yf);
		    	break;
		    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	drawOrigin(dt,xi,yi);
		    	break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
		    	dt.drawRectangle(xf-pas,yf-pas,pas*2,pas*2,true);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	if (item.getType() == JrVectorTypeName.VECTOR_SEGMENT)
		    		drawDestination(dt,xi,yi,xf,yf);
		    	else
		    		drawDestination(dt,xa,ya,xf,yf);
		    	break;
		    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
		        dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);
		    	drawItem(dt,active,xi,yi,xf,yf,xa,ya,item.getType(),false);
				dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
	    		dt.drawRectangle(xi-pas,yi-pas,pas*2,pas*2,true);
		        dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK, active);
		    	if (item.getType() == JrVectorTypeName.VECTOR_SEGMENT)
		    		drawDestination(dt,xf,yf,xi,yi);
		    	else
		    		drawDestination(dt,xa,ya,xi,yi);
		    	break;		    
		    default: break;
		    }
		}

	}
	
	public void drawFinalHandles(JrDrawTools dt,int currentView) {		
		int i;
		float xi,yi,xf,yf,xa,ya;
		  
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
		for(i = 0; i < 121; i++) {
			dt.drawLine(zones[i].x,zones[i].y-handlew,zones[i].x,zones[i].y+handlew);
			dt.drawLine(zones[i].x-handlew,zones[i].y,zones[i].x+handlew,zones[i].y);
		}
		if (!current.isNull()) {
		    dt.selectDefinedPen(JrPenName.PEN_PATH_BLUE, true);
			xi = ox + (((current.getFrom() % 11) - 5) * pasx);
			yi = oy + (((current.getFrom() / 11) - 5) * pasy);
			xf = ox + (((current.getTo() % 11) - 5) * pasx);
			yf = oy + (((current.getTo() / 11) - 5) * pasy);
			xa = ox + (((current.getAux() % 11) - 5) * pasx);
			ya = oy + (((current.getAux() / 11) - 5) * pasy);
			drawItem(dt,true,xi,yi,xf,yf,xa,ya,current.getType(),true);
		}
	}
	
	public void drawItem(JrDrawTools dt,boolean active,float xi,float yi,float xf,float yf,float xa,float ya,int type,boolean marker) {
		if (type == JrVectorTypeName.VECTOR_SEGMENT) {
			dt.drawLine(xi,yi,xf,yf);
		}
		else {
			QuadCurve2D.Float quad = new QuadCurve2D.Float();
			Point2D.Float start = new Point2D.Float();
			Point2D.Float end = new Point2D.Float();
			Point2D.Float control = new Point2D.Float();
            start.setLocation(xi,yi);
            end.setLocation(xf,yf);
            control.setLocation(xa,ya);

            quad.setCurve(start, control, end);
            dt.drawShape(quad);
            if (marker) {
            	dt.selectPen(1);
            	dt.drawLine(xi,yi,xa,ya);
            	dt.drawLine(xf,yf,xa,ya);
            }            
		}
	}
	
	public boolean handleBeginDrag(MouseEvent evt,JrContext cnt) {
		int pos = getIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 121)) {
			switch(cnt.getVectorMode()) {
			case JrVectorOperatorName.VECTOR_OPE_MOVE :
				currentEdt = getItem(pos);
				if (currentEdt == null)
					return false;
				current.initialize(currentEdt);
				currentPoint = 0;
				currentPoint = (pos == currentEdt.getAux())? 2 : currentPoint;
				currentPoint = (pos == currentEdt.getTo())? 1 : currentPoint;
				return true;
			case JrVectorOperatorName.VECTOR_OPE_CURVE :
			case JrVectorOperatorName.VECTOR_OPE_SEGMENT :
				current.nullify();
				currentPoint = 0;
				current.setFrom(pos);
			  	current.setType(cnt.getCurrentVector());
			  	if (current.getType() == JrVectorTypeName.VECTOR_CURVE) {
			  		int x = (pos % 11);
			  		x = (x == 10)? 9 : x+1;
			  		current.setAux(((pos / 11) * 11) + x);
			  	}
				current.setTrait(cnt.getCurrentTraitVector());
				return true;
			default : break;
			}
		}
		return false;
	}
	public boolean handleDrag(MouseEvent evt,JrContext cnt) {
		int pos = getIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 121)) {
			switch(cnt.getVectorMode()) {
			case JrVectorOperatorName.VECTOR_OPE_MOVE :
				if (currentEdt == null)
					return false;
				switch(currentPoint) {
				case 1 : current.setTo(pos); break;
				case 2 : current.setAux(pos); break;
				default: current.setFrom(pos); break;
				}								
				return true;
			case JrVectorOperatorName.VECTOR_OPE_CURVE :
			case JrVectorOperatorName.VECTOR_OPE_SEGMENT :
				current.setTo(pos);
				return true;
			default : break;
			}
		}
		return false;
	}
	public boolean handleEndDrag(MouseEvent evt,JrContext cnt) {
		int pos = getIndex(evt.getX(),evt.getY());
		if ((pos >= 0) && (pos < 121)) {
			switch(cnt.getVectorMode()) {
			case JrVectorOperatorName.VECTOR_OPE_MOVE :
				if (currentEdt == null)
					return false;
				currentEdt.initialize(current);
				return true;
			case JrVectorOperatorName.VECTOR_OPE_CURVE :
			case JrVectorOperatorName.VECTOR_OPE_SEGMENT :
				if (current.isValid())
					items.add(new JrItemVector(current));
				return true;
			default : break;
			}
		}
		current.nullify();
		currentEdt = null;
		currentPoint = 0;
		return false;
	}
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		if (cnt.getVectorMode() == JrVectorOperatorName.VECTOR_OPE_SELECT) {
			int pos = getIndex(evt.getX(),evt.getY());
			if ((pos >= 0) && (pos < 121)) {
				currentEdt = getItem(pos);
				if (currentEdt != null) {
					current.initialize(currentEdt);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean deleteCurrent() {
		if (current.isNull() == false) {
			JrItemVector item;
			JrItemVector found = null;
			ListIterator iter = items.listIterator();
			while(iter.hasNext() && (found == null)) {
				item = (JrItemVector)(iter.next());
				if (item.isEqual(current)) {
					found = item;
				}
			}
			if (found != null) {
				items.remove(found);
				currentEdt = null;
				if (items.isEmpty())
					current.nullify();
				else
					current.initialize((JrItemVector)(items.get(0)));
				return true;
			}
		}
		return false;
	}
	
	public JrItemVector getItem(int pos) {
		JrItemVector item;
		ListIterator iter = items.listIterator();
		while(iter.hasNext()) {
			item = (JrItemVector)(iter.next());
			if (item.isThis(pos))
				return item;
		}
		return null;
	}
	public int getIndex(int x,int y) {		
		if ((pasx > 0) && (pasy > 0)) {
			int px = (int)((x - (ox - ((pasx * 11)/2))) / pasx);
			int py = (int)((y - (oy - ((pasy * 11)/2))) / pasy);
			if ((px >= 0) && (px < 11) && (py >= 0) && (py < 11)) 
				return (py * 11) + px;
		}
		return -1;		
	}
}
