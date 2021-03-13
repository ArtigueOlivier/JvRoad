/*
 * Created on Jun 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.filter;

import java.util.Vector;

import tools.files.JrFileRead;
import tools.files.JrFileSave;

import names.JrArcName;
import names.JrPontName;
import names.JrTraitName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilter {
	public static final int ORDER_SAME = 0;
	public static final int ORDER_INVERSE = 1;
	public static final int ORDER_SHAKER = 2;
	public static final int ORDER_COUNT = 3;
	
	public static final int DRAW_NORMAL = 0;
	public static final int DRAW_ROADTOPATH = 1;
	public static final int DRAW_COUNT = 2;
	
	private int page = 0;
	private int orderType = ORDER_SAME;
	private int drawMode = DRAW_NORMAL;
	
	private int caseIndex[] = null;
	private int currentCaseIndex = -1;
	
	public JrFilter() {		
	}
	
	public JrFilter(JrFilter src) {
		page = src.page;
		orderType = src.orderType;
		drawMode = src.drawMode;
	}
	
	public JrFilter(JrFileRead file,String entry) {
		String str = file.getStringValue(entry,"0;0;0");
		String items[] = str.split(";");
		if (items.length == 3) {
			page = Integer.parseInt(items[0]);
			orderType = Integer.parseInt(items[1]);
			drawMode = Integer.parseInt(items[2]);
		}
	}
	
	public void save(JrFileSave file,String entry) {		
		file.addEntry(entry,page + ";" + orderType + ";" + drawMode);		
	}
	
	public int filterTrait(int trait) {
		if (drawMode == DRAW_ROADTOPATH) {
			switch(trait) {
		    case JrTraitName.TRAIT_ROAD :
		    case JrTraitName.TRAIT_NATIONAL : return JrTraitName.TRAIT_PATH; 
		    case JrTraitName.TRAIT_ROAD_DEST :
		    case JrTraitName.TRAIT_NATIONAL_DEST : return JrTraitName.TRAIT_PATH_DEST;
		    case JrTraitName.TRAIT_ROAD_DESTINV :
		    case JrTraitName.TRAIT_NATIONAL_DESTINV : return JrTraitName.TRAIT_PATH_DESTINV;
		    case JrTraitName.TRAIT_ROAD_SRC :
		    case JrTraitName.TRAIT_NATIONAL_SRC : return JrTraitName.TRAIT_PATH_SRC;
		    case JrTraitName.TRAIT_ROAD_SRCINV :
		    case JrTraitName.TRAIT_NATIONAL_SRCINV : return JrTraitName.TRAIT_PATH_SRCINV;
			default : break;
			}
		}
		return trait;			
	}
	public int filterPont(int pont) {
		if (drawMode == DRAW_ROADTOPATH) {
			switch(pont) {
		    case JrPontName.PONT_ROAD_V :
		    case JrPontName.PONT_NATIONAL_V : return JrPontName.PONT_PATH_V; 
		    case JrPontName.PONT_ROAD_H :
		    case JrPontName.PONT_NATIONAL_H : return JrPontName.PONT_PATH_H;
			default : break;
			}
		}
		return pont;			
		
	}
	public int filterArc(int arc) {
		if (drawMode == DRAW_ROADTOPATH) {
			switch(arc) {
			default : break;
			case JrArcName.ARC_ROAD_NO : return JrArcName.ARC_PATH_NO;
			case JrArcName.ARC_ROAD_BIGNO : return JrArcName.ARC_PATH_BIGNO;
			case JrArcName.ARC_ROAD_NE : return JrArcName.ARC_PATH_NE;
			case JrArcName.ARC_ROAD_BIGNE : return JrArcName.ARC_PATH_BIGNE;
			case JrArcName.ARC_ROAD_SO : return JrArcName.ARC_PATH_SO;
			case JrArcName.ARC_ROAD_BIGSO : return JrArcName.ARC_PATH_BIGSO;
			case JrArcName.ARC_ROAD_SE : return JrArcName.ARC_PATH_SE;
			case JrArcName.ARC_ROAD_BIGSE : return JrArcName.ARC_PATH_BIGSE;
			case JrArcName.ARC_ROAD_NOSE : return JrArcName.ARC_PATH_NOSE;
			case JrArcName.ARC_ROAD_NESO : return JrArcName.ARC_PATH_NESO;
			case JrArcName.ARC_PATHROAD_CROSS :
			case JrArcName.ARC_ROAD_CROSS :
			case JrArcName.ARC_ROADPATH_CROSS : return JrArcName.ARC_PATH_CROSS;
			}
		}
		return arc;
	}
	
	public void load() {		
	}
	
	public void save() {		
	}
	
	public void beginOrderCase(int count,int from,boolean hautVersBas,boolean twoColonnes,int casePerColumn) {
		int i,num,val;
		int tab[];
		Vector lst = new Vector();
		caseIndex = new int [Math.max(count,casePerColumn) * 2];
		currentCaseIndex = -1;
		switch(orderType) {
		case ORDER_SHAKER :
			for(i = 0; i < count; i++)
				lst.add(new Integer(i + from));
			for(i = 0; i < count; i++) {
				num = ((int)(Math.random() * lst.size()));
				val = ((Integer)(lst.get(num))).intValue();
				lst.remove(num);
				caseIndex[i] = val;
			}
			break;
		case ORDER_INVERSE : hautVersBas = (hautVersBas)? false : true;
		default :			
			if ((hautVersBas == false) && twoColonnes) {
				num = casePerColumn;
				val = Math.min(count,casePerColumn);
			}
			else {
				num = count;
				val = count;
			}
			for(i = 0; i < num; i++) {
				if (hautVersBas)
					caseIndex[i] = from + i;
				else {
					if (twoColonnes) {
						caseIndex[i] = from + val - 1 - i;
						caseIndex[i+casePerColumn] = from + count - 1 - i;
					}
					else
						caseIndex[i] = from + count - 1 - i;
				}
			}
			break;				
		}		
	}
	
	public int getNextCase() {
		currentCaseIndex++;
		if (caseIndex.length <= currentCaseIndex)
			return 0;
		return caseIndex[currentCaseIndex];
	}
	
	public void endOrderCase() {
		caseIndex = null;
		currentCaseIndex = -1;		
	}
	
	public int getDrawMode() {
		return drawMode;
	}
	public void setDrawMode(int drawMode) {
		this.drawMode = drawMode;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
