/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;


import java.awt.Color;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileSave;

import names.JrClipboardOpeName;
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrRegionFontName;
import names.JrRegionName;
import names.JrSymbolName;
import names.JrViewName;
import application.JrApplicationOption;
import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;
import book.map.*;
import book.symbol.JrSymbol;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCase {
	static final int CASE_SYMBOL_COUNT = 4;
	static final int CASE_MAP_COUNT = 5;
	
	private int distance = 0;
	private int total = 0;
	private boolean raz = false;
	private boolean approx = false;
	private boolean inconnuDistance = false;
	
	private String comments = "";
	private boolean centerComments = false;
	private JrSymbol[] symbols = new JrSymbol [CASE_SYMBOL_COUNT];
	private JrMap[] maps = new JrMap [CASE_MAP_COUNT];
	
	public JrCase() {
		initialize();
	}	
	
	public JrCase(int dist,boolean raz,boolean approx,boolean inc) {
		initialize();
		distance = dist;
		this.raz = raz;
		this.approx = approx;
		inconnuDistance = inc;
	}	
	
	public JrCase(JrCase src) {
		int i;
		
		distance = src.distance;
		total = 0;
		raz = src.raz;
		approx = src.approx;
		inconnuDistance = src.inconnuDistance;
		comments = new String(src.comments);
		centerComments = src.centerComments;
		for(i = 0; i < CASE_SYMBOL_COUNT; i++)
			symbols[i] = JrSymbol.Create(src.symbols[i].getName());
		for(i = 0; i < CASE_MAP_COUNT; i++)
			maps[i] = src.maps[i].copy();		
	}
	
	public JrCase(JrFileRead file) {
		readEntete(file);
		inconnuDistance = (file.getIntegerValue("Inconnue",0) == 0)? false : true;
		symbols[0] = JrSymbol.Create(file.getIntegerValue("Sym0",JrSymbolName.SYMBOL_NOTHING));
		symbols[1] = JrSymbol.Create(file.getIntegerValue("Sym1",JrSymbolName.SYMBOL_NOTHING));
		symbols[2] = JrSymbol.Create(file.getIntegerValue("Sym2",JrSymbolName.SYMBOL_NOTHING));
		symbols[3] = JrSymbol.Create(file.getIntegerValue("Sym3",JrSymbolName.SYMBOL_NOTHING));
		String vmaps = file.getStringValue("Maps","5;0;0;0;0;6");		
		String vals[] = vmaps.split(";");
		maps[0] = JrMap.CreateMap(Integer.parseInt(vals[1]));
		maps[1] = JrMap.CreateMap(Integer.parseInt(vals[2]));
		maps[2] = JrMap.CreateMap(Integer.parseInt(vals[3]));
		maps[0].read(file,"map0");
		maps[1].read(file,"map1");
		maps[2].read(file,"map2");
		if (Integer.parseInt(vals[0]) == 4) { 
			maps[3] = JrMap.CreateMap(0);
			maps[4] = JrMap.CreateMap(Integer.parseInt(vals[4]));
			maps[4].read(file,"map3");		
		}
		else {
			maps[3] = JrMap.CreateMap(Integer.parseInt(vals[4]));
			maps[4] = JrMap.CreateMap(Integer.parseInt(vals[5]));
			maps[3].read(file,"map3");
			maps[4].read(file,"map4");
		}
	}
	
	public void initialize() {
		int i;
		maps[0] = new JrMapCarrefour();
		for(i = 1; i < (CASE_MAP_COUNT-1); i++)
			maps[i] = new JrMapGrid();
		maps[CASE_MAP_COUNT-1] = new JrMapObjects();
		symbols[0] = JrSymbol.Create(JrSymbolName.SYMBOL_NOTHING);
		symbols[1] = JrSymbol.Create(JrSymbolName.SYMBOL_NOTHING);
		symbols[2] = JrSymbol.Create(JrSymbolName.SYMBOL_NOTHING);
		symbols[3] = JrSymbol.Create(JrSymbolName.SYMBOL_NOTHING);		
	}
	
	public void readVeryOld(JrFileRead file) {
		String str;
		readEntete(file);
		symbols[0] = JrSymbol.CreateVeryOld(file.getIntegerValue("Draw0",JrSymbolName.SYMBOL_NOTHING));
		symbols[1] = JrSymbol.CreateVeryOld(file.getIntegerValue("Draw1",JrSymbolName.SYMBOL_NOTHING));
		symbols[2] = JrSymbol.CreateVeryOld(file.getIntegerValue("Draw2",JrSymbolName.SYMBOL_NOTHING));
		symbols[3] = JrSymbol.CreateVeryOld(file.getIntegerValue("Draw3",JrSymbolName.SYMBOL_NOTHING));
		str = file.getStringValue("MAP","00;00;00;");
		maps[0] = JrMap.CreateOldMap(str);
		str = file.getStringValue("MA2","00;00;00;");
		maps[1] = JrMap.CreateOldMap(str);
		str = file.getStringValue("MA3","00;00;00;");
		maps[2] = JrMap.CreateOldMap(str);
		maps[3] = JrMap.CreateMap(0);
		str = file.getStringValue("SYM","00;00;00;");
		String strs[] = str.split(";");
		maps[4] = new JrMapObjects();
		maps[4].readVeryOld(strs);		
	}

	public void readOld(JrFileRead file) {
		readEntete(file);
		symbols[0] = JrSymbol.Create(file.getIntegerValue("Sym0",JrSymbolName.SYMBOL_NOTHING));
		symbols[1] = JrSymbol.Create(file.getIntegerValue("Sym1",JrSymbolName.SYMBOL_NOTHING));
		symbols[2] = JrSymbol.Create(file.getIntegerValue("Sym2",JrSymbolName.SYMBOL_NOTHING));
		symbols[3] = JrSymbol.Create(file.getIntegerValue("Sym3",JrSymbolName.SYMBOL_NOTHING));
		String vmaps = file.getStringValue("Maps","4;0;0;0;6");		
		String vals[] = vmaps.split(";");
		int map = Integer.parseInt(vals[1]);
		map = (map == 8)? 13 : map;
		maps[0] = JrMap.CreateMap(map);
		map = Integer.parseInt(vals[2]);
		map = (map == 8)? 13 : map;
		maps[1] = JrMap.CreateMap(map);
		map = Integer.parseInt(vals[3]);
		map = (map == 8)? 13 : map;
		maps[2] = JrMap.CreateMap(map);
		map = Integer.parseInt(vals[4]);
		map = (map == 8)? 13 : map;
		maps[3] = JrMap.CreateMap(0);
		maps[4] = JrMap.CreateMap(map);
		maps[0].readOld(file,"map0");
		maps[1].readOld(file,"map1");
		maps[2].readOld(file,"map2");
		maps[4].readOld(file,"map3");
	}
	
	public void readEntete(JrFileRead file) {
		distance = file.getIntegerValue("Distance",0);
		raz = (file.getIntegerValue("Raz",0) == 0)? false : true;
		approx = (file.getIntegerValue("Approx",0) == 0)? false : true;
		centerComments = (file.getIntegerValue("CenterComm",0) == 0)? false : true;
		String str = file.getStringValue("Comment","");
		comments = str.replaceAll("#13#","\n");
	}
	
	public void save(JrFileSave file) {
		file.addEntry("Distance",distance);
		file.addEntry("Raz",raz);
		file.addEntry("Approx",approx);
		file.addEntry("Inconnue",inconnuDistance);
		file.addEntry("CenterComm",centerComments);
		file.addEntry("Comment",comments.replaceAll("\n","#13#"));	
		file.addEntry("Sym0",symbols[0].getName());
		file.addEntry("Sym1",symbols[1].getName());
		file.addEntry("Sym2",symbols[2].getName());
		file.addEntry("Sym3",symbols[3].getName());
		String vmaps = "5;" + maps[0].getName() + ";" + maps[1].getName() + ";" + maps[2].getName() + ";" + maps[3].getName() + ";" + maps[4].getName();
		file.addEntry("Maps",vmaps);
		maps[0].save(file,"map0");
		maps[1].save(file,"map1");
		maps[2].save(file,"map2");
		maps[3].save(file,"map3");
		maps[4].save(file,"map4");
	}
	
	public int getOriginCount() {
		int cpt = 0;
		for(int i = 0; i < CASE_MAP_COUNT; i++)
		  cpt += maps[i].getOriginCount();
		return cpt;
	}

	public int getDestinationCount() {
		int cpt = 0;
		for(int i = 0; i < CASE_MAP_COUNT; i++)
		  cpt += maps[i].getDestinationCount();
		return cpt;
	}
	
	public void rotation(int currentView) {
		if (currentView < JrViewName.VIEW_OBJ)
			maps[currentView].rotation();
	}
	
	public void inverse() {
		int i;
		for(i = 0; i < CASE_MAP_COUNT; i++) {
			maps[i].inverse();
		}		
	}
	
	public JrCase copy() {
		return new JrCase(this);
	}
	
	public void paste(JrCase src,int typ) {
		int i;
		switch(typ) {
		case JrClipboardOpeName.CLIPBOARD_CASE :
			distance = src.distance;
			total = 0;
			centerComments = src.centerComments;
			raz = src.raz;
			approx = src.approx;
			inconnuDistance = src.inconnuDistance;
			comments = new String(src.comments);
			for(i = 0; i < CASE_SYMBOL_COUNT; i++)
				symbols[i] = JrSymbol.Create(src.symbols[i].getName());
			for(i = 0; i < CASE_MAP_COUNT; i++)
				maps[i] = src.maps[i].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAPS :
			for(i = 0; i < CASE_MAP_COUNT; i++)
				maps[i] = src.maps[i].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAP1 :
			maps[0] = src.maps[0].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAP2 :
			maps[1] = src.maps[1].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAP3 :
			maps[2] = src.maps[2].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAP4 :
			maps[3] = src.maps[3].copy();
			break;
		case JrClipboardOpeName.CLIPBOARD_MAP5 :
			maps[3] = src.maps[4].copy();
			break;
		default : break;
		}		
	}
	
	public boolean handleMapEvent(MouseEvent evt,JrContext cnt,JrDrawTools dt) {
		int view = cnt.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			if (evt.getClickCount() == 2) {
				maps[view].initializeRegions(dt.getRegions());
				return maps[view].handleDoubleClick(evt,cnt);
			}
			else {
				switch(evt.getButton()) {
				case MouseEvent.BUTTON1 : 
					maps[view].initializeRegions(dt.getRegions());
					return maps[view].handleLeftButton(evt,cnt);
				case MouseEvent.BUTTON3 : 
					maps[view].initializeRegions(dt.getRegions());
					return maps[view].handleRightButton(evt,cnt);
				default : break;
				}
			}
		}
		return false;
	}
	
	public boolean handlePaletteEvent(int evt,JrContext cnt,JrDrawTools dt) {
		int view = cnt.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			if (dt != null)
				maps[view].initializeRegions(dt.getRegions());
			return maps[view].handlePaletteEvent(evt);
		}
		return false;
	}
	
	public boolean handleBeginDrag(MouseEvent evt,JrContext cnt,JrDrawTools dt) {
		int view = cnt.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			maps[view].initializeRegions(dt.getRegions());
			return maps[view].handleBeginDrag(evt,cnt);
		}
		return false;
	}
	public boolean handleDrag(MouseEvent evt,JrContext cnt,JrDrawTools dt) {
		int view = cnt.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			maps[view].initializeRegions(dt.getRegions());
			return maps[view].handleDrag(evt,cnt);
		}
		return false;
	}
	public boolean handleEndDrag(MouseEvent evt,JrContext cnt,JrDrawTools dt) {
		int view = cnt.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			maps[view].initializeRegions(dt.getRegions());
			return maps[view].handleEndDrag(evt,cnt);
		}
		return false;
	}
	
	public boolean setMapName(int index,int name) {
		if ((index >= 0) && (index < (CASE_MAP_COUNT - 1))) {
			if (name != maps[index].getName()) {
				maps[index] = JrMap.CreateMap(name);
				return true;
			}
		}
		return false;
	}
	
	public int getMapName(int index) {
		if ((index >= 0) && (index < CASE_MAP_COUNT))
			return maps[index].getName();
		return JrMapName.MAP_CARREFOUR_NAME;
		
	}
	
	public JrMap getMap(int index) {
		if ((index >= 0) && (index < CASE_MAP_COUNT))
			return maps[index];
		return null;
	}
	
	public boolean hasCapMap() {
		for(int i = 0; i < (CASE_MAP_COUNT-1); i++)
			if (maps[i].getName() == JrMapName.MAP_CAP_NAME)
				return true;
		return false;
	}
	
	public boolean hasImgMap() {
		for(int i = 0; i < (CASE_MAP_COUNT-1); i++)
			if (maps[i].getName() == JrMapName.MAP_GIF_NAME)
				return true;
		return false;
	}

	public int getMapObjectPalette(int index) {
		if ((index >= 0) && (index < CASE_MAP_COUNT))
			return maps[index].getObjectPaletteName();
		return JrPaletteName.PALETTE_TRAITS;		
	}
	
	public void draw(JrDrawTools dt,JrCaseDisplayer disp,int index,int currentView,JrFilter filter) {
		int i;
		dt.selectColor(Color.BLACK);
		dt.selectFont(JrRegionFontName.REGION_FONT_DIST_NORMAL);
		dt.selectColor(JrColorName.COLOR_TXT_PARTIEL);
		if (disp.isSmallPartiel() == false)
			if (isInconnuDistance())
				dt.drawText("?",JrRegionName.REGION_PARTIEL,1);
			else {
				if (dt.getDisplayMeters() == 2)
					dt.drawInt(distance,JrRegionName.REGION_PARTIEL,1);
				else
					dt.drawDistance(distance,JrRegionName.REGION_PARTIEL,1);
			}
		dt.selectColor(JrColorName.COLOR_TXT_TOTAL);
		if (disp.isSmallTotal() == false)
			if (isInconnuDistance())
				dt.drawText("?",JrRegionName.REGION_TOTAL,1);
			else
				dt.drawDistance(total,JrRegionName.REGION_TOTAL,1);		
		dt.selectFont(JrRegionFontName.REGION_FONT_DIST_SMALL);
		dt.selectColor(JrColorName.COLOR_TXT_PARTIEL);
		if (disp.isSmallPartiel() == true)
			if (isInconnuDistance())
				dt.drawText("?",JrRegionName.REGION_PARTIEL,1);
			else
				dt.drawDistance(distance,JrRegionName.REGION_PARTIEL,1);
		dt.selectColor(JrColorName.COLOR_TXT_TOTAL);
		if (disp.isSmallTotal() == true)
			if (isInconnuDistance())
				dt.drawText("?",JrRegionName.REGION_TOTAL,1);
			else
				dt.drawDistance(total,JrRegionName.REGION_TOTAL,1);		
		if (raz) {
			dt.selectColor(JrColorName.COLOR_TXT_RAZ);
			dt.drawText(JrApplicationOption.GetWord("TxtRAZ"),JrRegionName.REGION_RAZ,1);
		}
		if (approx) {
			dt.selectColor(JrColorName.COLOR_TXT_APPROX);
			dt.drawText(JrApplicationOption.GetWord("TxtApprox"),JrRegionName.REGION_APPROX,1);
		}
		dt.selectFont(JrRegionFontName.REGION_FONT_INDEX);
		dt.selectColor(JrColorName.COLOR_TXT_CASE_NUM);
		dt.drawInt(index,JrRegionName.REGION_INDEX,1);
		
		if (comments.length() > 0) {
			int align = (centerComments)? 1 : 0;
			dt.selectColor(JrColorName.COLOR_TXT_COMMENT);
			if (disp.isFullText()) 
				dt.drawTextArea(JrRegionName.REGION_TEXT,
						JrRegionFontName.REGION_FONT_COMMENT_FULL2,comments,align);
			else
				dt.drawTextArea(JrRegionName.REGION_TEXT,JrRegionFontName.REGION_FONT_COMMENT2,
						comments,align);
		}
		symbols[0].draw(dt,JrRegionName.REGION_SYMBOL1);
		symbols[1].draw(dt,JrRegionName.REGION_SYMBOL2);
		symbols[2].draw(dt,JrRegionName.REGION_SYMBOL3);
		symbols[3].draw(dt,JrRegionName.REGION_SYMBOL4);
		
		drawMap(dt,disp,index,currentView,filter);
	}
	public void drawMap(JrDrawTools dt,JrCaseDisplayer disp,int index,int currentView,JrFilter filter) {
		int i;
		Shape oldClip = dt.setClip(JrRegionName.REGION_MAP);
		boolean active = (currentView >= JrViewName.VIEW_OBJ)? true : false;
	    maps[CASE_MAP_COUNT-1].initializeRegions(dt.getRegions());
	    maps[CASE_MAP_COUNT-1].drawBlack(dt,disp,active,currentView,filter);
	    maps[CASE_MAP_COUNT-1].drawWhite(dt,disp,active,currentView,filter);
	    maps[CASE_MAP_COUNT-1].drawObjects(dt,disp,active,currentView,filter);
		
	    active = (currentView == JrViewName.VIEW_REAL)? true : false;
		for(i = 0; i < (CASE_MAP_COUNT-1); i++) {
			if (i != currentView) {
				maps[i].initializeRegions(dt.getRegions());
				maps[i].drawBlack(dt,disp,active,currentView,filter);
			}
		}
		if (currentView < (CASE_MAP_COUNT-1)) {
			maps[currentView].initializeRegions(dt.getRegions());
			maps[currentView].drawFirstHandles(dt,currentView);
			maps[currentView].drawBlack(dt,disp,true,currentView,filter);
		}
		
		for(i = 0; i < (CASE_MAP_COUNT-1); i++) {
			if (i != currentView) {
				maps[i].drawWhite(dt,disp,active,currentView,filter);
			}
		}
		if (currentView < (CASE_MAP_COUNT-1)) {
			maps[currentView].drawWhite(dt,disp,true,currentView,filter);
		}
		
		for(i = 0; i < (CASE_MAP_COUNT-1); i++) {
			if (i != currentView) {
				maps[i].drawObjects(dt,disp,active,currentView,filter);
			}
		}
		if (currentView < (CASE_MAP_COUNT-1)) {
			maps[currentView].drawObjects(dt,disp,true,currentView,filter);
		}
		
		for(i = 0; i < (CASE_MAP_COUNT-1); i++) {
			if (i != currentView) {
				maps[i].drawOver(dt,disp,active,currentView,filter);
			}
		}
		if (currentView < (CASE_MAP_COUNT-1)) {
			maps[currentView].drawOver(dt,disp,true,currentView,filter);
		}
		
		if (currentView < JrViewName.VIEW_REAL)
			maps[currentView].drawFinalHandles(dt,currentView);
		
		active = (currentView >= JrViewName.VIEW_OBJ)? true : false;
	    maps[CASE_MAP_COUNT-1].drawOver(dt,disp,active,currentView,filter);
		for(i = 0; i < (CASE_MAP_COUNT-1); i++) {
		    maps[i].endDraw();
		}
		dt.restoreClip(oldClip);
	}
	/**
	 * @return Returns the approx.
	 */
	public boolean isApprox() {
		return approx;
	}
	/**
	 * @param approx The approx to set.
	 */
	public void setApprox(boolean approx) {
		this.approx = approx;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the distance.
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance The distance to set.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean hasDistance() {
		return ((distance > 0) || (inconnuDistance == true))? true : false;
	}
		
	/**
	 * @return Returns the raz.
	 */
	public boolean isRaz() {
		return raz;
	}
	/**
	 * @param raz The raz to set.
	 */
	public void setRaz(boolean raz) {
		this.raz = raz;
	}
	/**
	 * @return Returns the total.
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total The total to set.
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return Returns the inconnuDistance.
	 */
	public boolean isInconnuDistance() {
		return inconnuDistance;
	}
	/**
	 * @param inconnuDistance The inconnuDistance to set.
	 */
	public void setInconnuDistance(boolean inconnuDistance) {
		this.inconnuDistance = inconnuDistance;
	}

	public JrDistanceParam getDistanceParameters() {
		return new JrDistanceParam(distance,approx,inconnuDistance,raz);
	}
	
	public void setDistanceParameters(JrDistanceParam params) {
		distance = params.getDistance();
		approx = params.isApproximativeDistance();
		inconnuDistance = params.isInconnuDistance();
		raz = params.isRaz();
	}
		
	public boolean isCenterComments() {
		return centerComments;
	}
	public void setCenterComments(boolean center) {
		centerComments = center;
	}
	
	public boolean setSymbol(int name, int symb) {
		if (symbols[name].getName() != symb) {
			symbols[name] = JrSymbol.Create(symb);
			return true;
		}
		return false;
	}
	
	public int getSymbol(int name) {
		return symbols[name].getName();
	}
	
	public void selectNextSymbol(int name) {
		int symb = symbols[name].getName() + 1;
		symb = (symb >= JrSymbolName.SYMBOL_COUNT)? JrSymbolName.SYMBOL_NOTHING : symb;
		symbols[name] = JrSymbol.Create(symb);
	}
}
