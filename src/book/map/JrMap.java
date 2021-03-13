/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;

import application.JrApplicationOption;
import application.actions.JrActionCenter;

import tools.JrContext;
import tools.JrDrawTools;
import tools.JrMath;
import tools.JrPoint;
import tools.JrRegions;
import tools.files.JrFileRead;
import tools.files.JrFileSave;

import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;
import names.JrRegionName;
import names.JrTraitName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMap {
	protected float ox = 0.0f;
	protected float oy = 0.0f;
	protected float pasx = 0.0f;
	protected float pasy = 0.0f;
	protected float handlew = 0.0f;
	protected float originalW2 = 0.0f;
	protected float originalH2 = 0.0f;
	
	public JrMap() {	
	}
	
	public void save(JrFileSave file,String entry) {		
	}
	
	public void read(JrFileRead file,String entry) {		
	}
	
	public void readOld(JrFileRead file,String entry) {
	}
	
	public void readVeryOld(String[] data) {
	}
	
	public JrMap copy() {
		return new JrMap();
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_TRAITS;
	}
	
	public void rotation() {		
	}
	
	public void inverse() {
	}
	
	public int inverseTrait(int trait) {
		switch(trait) {
		case JrTraitName.TRAIT_SENTIER_SRC : return JrTraitName.TRAIT_SENTIER_DEST;
		case JrTraitName.TRAIT_SENTIER_SRCINV : return JrTraitName.TRAIT_SENTIER_DESTINV;
		case JrTraitName.TRAIT_PATH_SRC : return JrTraitName.TRAIT_PATH_DEST;
		case JrTraitName.TRAIT_PATH_SRCINV : return JrTraitName.TRAIT_PATH_DESTINV;
		case JrTraitName.TRAIT_ROAD_SRC : return JrTraitName.TRAIT_ROAD_DEST;
		case JrTraitName.TRAIT_ROAD_SRCINV : return JrTraitName.TRAIT_ROAD_DESTINV;
		case JrTraitName.TRAIT_NATIONAL_SRC : return JrTraitName.TRAIT_NATIONAL_DEST;
		case JrTraitName.TRAIT_NATIONAL_SRCINV : return JrTraitName.TRAIT_NATIONAL_DESTINV;
		case JrTraitName.TRAIT_DOUBLEROAD_SRC : return JrTraitName.TRAIT_DOUBLEROAD_DEST;
		case JrTraitName.TRAIT_DOUBLEROAD_SRCINV : return JrTraitName.TRAIT_DOUBLEROAD_DESTINV;
		case JrTraitName.TRAIT_SENTIER_DEST : return JrTraitName.TRAIT_SENTIER_SRC;
		case JrTraitName.TRAIT_SENTIER_DESTINV : return JrTraitName.TRAIT_SENTIER_SRCINV;
		case JrTraitName.TRAIT_PATH_DEST : return JrTraitName.TRAIT_PATH_SRC;
		case JrTraitName.TRAIT_PATH_DESTINV : return JrTraitName.TRAIT_PATH_SRCINV;
		case JrTraitName.TRAIT_ROAD_DEST : return JrTraitName.TRAIT_ROAD_SRC;
		case JrTraitName.TRAIT_ROAD_DESTINV : return JrTraitName.TRAIT_ROAD_SRCINV;
		case JrTraitName.TRAIT_NATIONAL_DEST : return JrTraitName.TRAIT_NATIONAL_SRC;
		case JrTraitName.TRAIT_NATIONAL_DESTINV : return JrTraitName.TRAIT_NATIONAL_SRCINV;
		case JrTraitName.TRAIT_DOUBLEROAD_DEST : return JrTraitName.TRAIT_DOUBLEROAD_SRC;
		case JrTraitName.TRAIT_DOUBLEROAD_DESTINV : return JrTraitName.TRAIT_DOUBLEROAD_SRCINV;
		default : break; 
		}
		return trait;
	}

	public int inverseTraitAndSens(int trait) {
		switch(trait) {
		case JrTraitName.TRAIT_SENTIER_SRC : return JrTraitName.TRAIT_SENTIER_DESTINV;
		case JrTraitName.TRAIT_SENTIER_SRCINV : return JrTraitName.TRAIT_SENTIER_DEST;
		case JrTraitName.TRAIT_PATH_SRC : return JrTraitName.TRAIT_PATH_DESTINV;
		case JrTraitName.TRAIT_PATH_SRCINV : return JrTraitName.TRAIT_PATH_DEST;
		case JrTraitName.TRAIT_ROAD_SRC : return JrTraitName.TRAIT_ROAD_DESTINV;
		case JrTraitName.TRAIT_ROAD_SRCINV : return JrTraitName.TRAIT_ROAD_DEST;
		case JrTraitName.TRAIT_NATIONAL_SRC : return JrTraitName.TRAIT_NATIONAL_DESTINV;
		case JrTraitName.TRAIT_NATIONAL_SRCINV : return JrTraitName.TRAIT_NATIONAL_DEST;
		case JrTraitName.TRAIT_DOUBLEROAD_SRC : return JrTraitName.TRAIT_DOUBLEROAD_DESTINV;
		case JrTraitName.TRAIT_DOUBLEROAD_SRCINV : return JrTraitName.TRAIT_DOUBLEROAD_DEST;
		case JrTraitName.TRAIT_SENTIER_DEST : return JrTraitName.TRAIT_SENTIER_SRCINV;
		case JrTraitName.TRAIT_SENTIER_DESTINV : return JrTraitName.TRAIT_SENTIER_SRC;
		case JrTraitName.TRAIT_PATH_DEST : return JrTraitName.TRAIT_PATH_SRCINV;
		case JrTraitName.TRAIT_PATH_DESTINV : return JrTraitName.TRAIT_PATH_SRC;
		case JrTraitName.TRAIT_ROAD_DEST : return JrTraitName.TRAIT_ROAD_SRCINV;
		case JrTraitName.TRAIT_ROAD_DESTINV : return JrTraitName.TRAIT_ROAD_SRC;
		case JrTraitName.TRAIT_NATIONAL_DEST : return JrTraitName.TRAIT_NATIONAL_SRCINV;
		case JrTraitName.TRAIT_NATIONAL_DESTINV : return JrTraitName.TRAIT_NATIONAL_SRC;
		case JrTraitName.TRAIT_DOUBLEROAD_DEST : return JrTraitName.TRAIT_DOUBLEROAD_SRCINV;
		case JrTraitName.TRAIT_DOUBLEROAD_DESTINV : return JrTraitName.TRAIT_DOUBLEROAD_SRC;
		default : break; 
		}
		return trait;
	}

	public boolean selectFirstObjectName(JrContext cnt) {
		cnt.setCurrentTrait(JrTraitName.TRAIT_NONE);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		int current = cnt.getCurrentTrait();
		if (current == JrTraitName.TRAIT_NONE)
			cnt.setCurrentTrait(JrTraitName.TRAIT_COUNT - 1);
		else
			cnt.setCurrentTrait(current-1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		int current = cnt.getCurrentTrait();
		if (current == (JrTraitName.TRAIT_COUNT - 1))
			cnt.setCurrentTrait(JrTraitName.TRAIT_NONE);
		else
			cnt.setCurrentTrait(current+1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cnt.setCurrentTrait(JrTraitName.TRAIT_COUNT - 1);
		JrActionCenter.RefreshActionsTrait();
		return false;
	}
	
	public int getName() {
		return JrMapName.MAP_CARREFOUR_NAME;
	}
	
	public int getOriginCount() {
		return 0;
	}
	public int getDestinationCount() {
		return 0;
	}
	
	public void initializeRegions(JrRegions regions) {
	    ox = regions.getMapOrigineX();
	    oy = regions.getMapOrigineY();
	    pasx = regions.getMapPasX();
	    pasy = regions.getMapPasY();
	    handlew = regions.getMapHandle();
	    Rectangle r = regions.get(JrRegionName.REGION_MAP);
	    originalW2 = r.width / 2;
	    originalH2 = r.height / 2;
	}
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
	}
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
	}
	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
	}
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
	}
	public void drawOver(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
	}
	public void drawFinalHandles(JrDrawTools dt,int currentView) {		
	}
	public void endDraw() {		
	}
	
	public void drawOrigin(JrDrawTools dt,float x,float y) {
		float p = Math.min(pasx,pasy) / 1.8f;
		float w = Math.max(p,dt.getPenWidth(JrPenName.PEN_ROAD_BLACK));
		dt.drawEllipse(x-w,y-w,w*2.0f,w*2.0f,true);
	}

	public void drawDestination(JrDrawTools dt,float xi,float yi,float xf,float yf) {
		float ptx[] = new float [3];
		float pty[] = new float [3];

		float deltax = (Math.max(pasx,pasy) * 2.0f) / 2.2f;
		float deltay = Math.max(pasx,pasy) / 2.2f;

		if (yi == yf) {
		    float dx = xf - xi;
		    float delta = (dx < 0.0f)? -deltax : deltax;
		    ptx[0] = xi + dx;
		    pty[0] = yi;
		    ptx[1] = xi + dx - delta;
		    pty[1] = yi - deltay;
		    ptx[2] = ptx[1];
		    pty[2] = yi + deltay;
		}
		else {
			if(xi == xf) {
				float dy = yf - yi;
				float delta = (dy < 0.0f)? -deltax : deltax;
				ptx[0] = xi;
				pty[0] = yi + dy;
				ptx[1] = xi + deltay;
				pty[1] = yi + dy - delta;
				ptx[2] = xi - deltay;
				pty[2] = pty[1];
		    }
		    else {
		    	double width  = (double)(xf - xi);
		    	double height = (double)(yf - yi);
		    	double f = (double)Math.sqrt(width*width + height*height);
		    	double c = (double)width  / f;
		    	double s = (double)height / f;
		    	double x1 = (double)xi + ((f - deltax) * c);
		    	double y1 = (double)yi + ((f - deltax) * s);
		        int tmp1 = JrMath.ArrondirDouble(deltay * s);
		        int tmp2 = JrMath.ArrondirDouble(deltay * c);
		        ptx[0] = xf;
		        pty[0] = yf;
		        ptx[1] = JrMath.ArrondirDouble(x1 - tmp1);
		        pty[1] = JrMath.ArrondirDouble(y1 + tmp2);
		        ptx[2] = JrMath.ArrondirDouble(x1 + tmp1);
		        pty[2] = JrMath.ArrondirDouble(y1 - tmp2);
		    }
		}
		dt.drawPolygon(ptx, pty, 3,true);
	}
	
	public void drawGraduation(JrDrawTools dt,int rayon,int angle,int pas) {
	  double currentAngle = 0.0;
	  double sizestep = (double)angle;
	  int steps = (360 / angle);
	  int rstep;
	  double cosa, sina;
	  rstep  = (pas == -1)? (rayon - (rayon/8)) : pas;
	  rayon--;
	  int xf, yf, xt, yt;
	  for(int loop = 0; loop < steps; loop++) {
	  	cosa = Math.cos(Math.toRadians((double)currentAngle));
	  	sina = Math.sin(Math.toRadians((double)currentAngle));
	    xf = (int)(ox + cosa*rayon);
	    yf = (int)(oy - sina*rayon);
	    xt = (int)(ox + cosa*rstep);
	    yt = (int)(oy - sina*rstep);
	    dt.drawLine(xf,yf,xt,yt);
	    currentAngle += sizestep;
	  }
	}

	public void drawRoadBlack(int trait,JrDrawTools dt,float xi,float yi,float xf,float yf,boolean active) {
	    switch(trait) {
	    case JrTraitName.TRAIT_TRAIN :
	    case JrTraitName.TRAIT_ROAD_DEST :
	    case JrTraitName.TRAIT_ROAD_DESTINV :
	    case JrTraitName.TRAIT_ROAD :
	    case JrTraitName.TRAIT_ROAD_SRC :
	    case JrTraitName.TRAIT_ROAD_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_ROAD_BLACK, active);
	        break;
	    case JrTraitName.TRAIT_SENTIER :
	    case JrTraitName.TRAIT_SENTIER_DEST :
	    case JrTraitName.TRAIT_SENTIER_DESTINV :
	    case JrTraitName.TRAIT_SENTIER_SRC :
	    case JrTraitName.TRAIT_SENTIER_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
	        break;
	    case JrTraitName.TRAIT_RIVER :
	    case JrTraitName.TRAIT_NATIONAL_DEST :
	    case JrTraitName.TRAIT_NATIONAL_DESTINV :
	    case JrTraitName.TRAIT_NATIONAL :
	    case JrTraitName.TRAIT_NATIONAL_SRC :
	    case JrTraitName.TRAIT_NATIONAL_SRCINV :
	    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
	    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
	    case JrTraitName.TRAIT_DOUBLEROAD :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_BLACK, active);
	        break;
	    default:
	    	dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);	    	
	    	break;
	    }
        dt.drawLine(xi,yi,xf,yf);		
	}

	public void drawRoadWhite(int trait,JrDrawTools dt,float xi,float yi,float xf,float yf,boolean active) {
	    switch(trait) {
	    case JrTraitName.TRAIT_TRAIN :
	    case JrTraitName.TRAIT_ROAD_DEST :
	    case JrTraitName.TRAIT_ROAD_DESTINV :
	    case JrTraitName.TRAIT_ROAD :
	    case JrTraitName.TRAIT_ROAD_SRC :
	    case JrTraitName.TRAIT_ROAD_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_ROAD_WHITE, active);
	        break;
	    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
	    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
	    case JrTraitName.TRAIT_DOUBLEROAD :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
	    case JrTraitName.TRAIT_NATIONAL_DEST :
	    case JrTraitName.TRAIT_NATIONAL_DESTINV :
	    case JrTraitName.TRAIT_NATIONAL :
	    case JrTraitName.TRAIT_NATIONAL_SRC :
	    case JrTraitName.TRAIT_NATIONAL_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_NATIONAL_WHITE, active);
	        break;
	    case JrTraitName.TRAIT_RIVER :
	        dt.selectDefinedPen(JrPenName.PEN_RIVER_BLUE, active);
	        break;
	    default: 
	    	return;
	    }
        dt.drawLine(xi,yi,xf,yf);		
	}
	public void drawRoadDeco(int trait,JrDrawTools dt,float xi,float yi,float xf,float yf,boolean active) {
	    switch(trait) {
	    case JrTraitName.TRAIT_DOUBLEROAD_DEST :
	    case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
	    case JrTraitName.TRAIT_DOUBLEROAD :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRC :
	    case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
	    	dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK, active);	    	
	    	break;
	    case JrTraitName.TRAIT_NATIONAL_DEST :
	    case JrTraitName.TRAIT_NATIONAL_DESTINV :
	    case JrTraitName.TRAIT_NATIONAL :
	    case JrTraitName.TRAIT_NATIONAL_SRC :
	    case JrTraitName.TRAIT_NATIONAL_SRCINV :
	        dt.selectDefinedPen(JrPenName.PEN_SENTIER_BLACK, active);
	        break;
	    case JrTraitName.TRAIT_TRAIN :
	        dt.selectDefinedPen(JrPenName.PEN_TRAIN_BLACK, active);
        	break;
	    default: 
	    	return;
	    }
        dt.drawLine(xi,yi,xf,yf);		
	}
	
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		return false;
	}
	public boolean handleRightButton(MouseEvent evt,JrContext cnt) {
		return false;
	}
	public boolean handleDoubleClick(MouseEvent evt,JrContext cnt) {
		return false;
	}
	public boolean handlePaletteEvent(int evt) {
		return false;
	}
	public boolean handleBeginDrag(MouseEvent evt,JrContext cnt) {		
		return false;
	}
	public boolean handleDrag(MouseEvent evt,JrContext cnt) {
		return false;
	}
	public boolean handleEndDrag(MouseEvent evt,JrContext cnt) {
		return false;
	}
	
	public int getZoneIndex(JrPoint[] pts,int count,float delta,int x,int y) {
		int i;
		for(i = 0; i < count; i++) {
			if (((pts[i].x - delta) <= x) && ((pts[i].x + delta) >= x))
				if (((pts[i].y - delta) <= y) && ((pts[i].y + delta) >= y))
					return i;
		}
		return -1;
	}
	public static JrMap CreateMap(int name) {
		switch(name) {
		case JrMapName.MAP_ARCS_NAME : return new JrMapArcs();
		case JrMapName.MAP_BORNE_NAME : return new JrMapBorne();
		case JrMapName.MAP_CAP_NAME : return new JrMapCap();
		case JrMapName.MAP_CLIPART_NAME : return new JrMapClipart();
		case JrMapName.MAP_CLOCK_NAME : return new JrMapClock();
		case JrMapName.MAP_OBJECTS_NAME : return new JrMapObjects();
		case JrMapName.MAP_GIF_NAME : return new JrMapGif();
		case JrMapName.MAP_GRID_NAME : return new JrMapGrid();
		case JrMapName.MAP_MILLEPAS_NAME : return new JrMapMillePas();
		case JrMapName.MAP_RONDPOINT_NAME : return new JrMapRondPoint();
		case JrMapName.MAP_TEXT_NAME : return new JrMapText();
		case JrMapName.MAP_VECTOR_NAME : return new JrMapVector();
		case JrMapName.MAP_LINETEXT_NAME : return new JrMapLineText();
		case JrMapName.MAP_PONT_NAME : return new JrMapPont();
		case JrMapName.MAP_VILLE_NAME : return new JrMapVille();
		default: break;
		}
		return new JrMapCarrefour();
	}
	
	public static JrMap CreateOldMap(String data) {
		int name = Integer.parseInt(data.substring(0,2));
		String datas[] = data.split(";");
		JrMap map = null;

	    switch(name) {
	    case 0 : map = CreateMap(JrMapName.MAP_CARREFOUR_NAME); break;
	    case 3 : if ((datas.length > 2) && (Integer.parseInt(datas[2]) == 5))
	    		 	map = CreateMap(JrMapName.MAP_CLOCK_NAME);
	             else
	             	map = CreateMap(JrMapName.MAP_CLIPART_NAME);
	             break;
	    case 4 : map = CreateMap(JrMapName.MAP_RONDPOINT_NAME); break;
	    case 5 : map = CreateMap(JrMapName.MAP_CARREFOUR_NAME); break;
	    case 6 : map = CreateMap(JrMapName.MAP_LINETEXT_NAME); break;
	    case 7 : map = CreateMap(JrMapName.MAP_CAP_NAME); break;
	    case 8 : map = CreateMap(JrMapName.MAP_ARCS_NAME); break;
	    default: map = CreateMap(JrMapName.MAP_GRID_NAME); break;
	    }
	    map.readVeryOld(datas);
	    return map;
	}
	
	public static float CPAS(float val, float coef) {
		return ((val * coef)/100);
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
}
