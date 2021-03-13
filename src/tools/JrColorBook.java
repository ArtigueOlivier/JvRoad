/*
 * Created on 21 mai 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

import java.awt.Color;

import tools.files.JrFileRead;
import tools.files.JrFileSave;

import names.JrColorName;
import names.JrPageRegionName;
import names.JrPenName;
import names.JrRegionName;

/**
 * @author olivier
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrColorBook {
	private Color[] colors;
	
	public JrColorBook() {
		colors = new Color [JrColorName.COLOR_COUNT];
		for(int i = 0; i < JrColorName.COLOR_COUNT; i++) {
			colors[i] = JrColorName.GetDefaultValue(i);
		}		
	}
	
	public JrColorBook(JrColorBook cbk) {
		colors = new Color [JrColorName.COLOR_COUNT];
		for(int i = 0; i < JrColorName.COLOR_COUNT; i++) {
			colors[i] = cbk.colors[i];
		}				
	}
	
	public void read(JrFileRead file) {
		file.setCurrentSection("Couleurs");
		for(int i = 0; i < JrColorName.COLOR_COUNT; i++) {
			colors[i] = file.getColorValue(JrColorName.GetName(i),JrColorName.GetDefaultValue(i));
		}		
	}
	
	public void save(JrFileSave file) {
		file.addSection("Couleurs");
		for(int i = 0; i < JrColorName.COLOR_COUNT; i++) {
			file.addEntry(JrColorName.GetName(i),colors[i]);			
		}		
	}
	
	public void set(int name,Color c) {
		colors[name] = c;
	}
	
	public Color get(int name) {
		return colors[name];
	}
	
	public Color getPenColor(int penname,boolean active) {		
		switch(penname) {
		case JrPenName.PEN_CADRE_NORMAL :
		case JrPenName.PEN_CADRE_EPAIS :
			return Color.BLACK;
		case JrPenName.PEN_CONTOUR_PANNEAU :
			return Color.RED;
    	case JrPenName.PEN_MAP_RED :
			return colors[JrColorName.COLOR_TRT_MAP_INACTIF];
		case JrPenName.PEN_CONSEIL_PANNEAU :
			return Color.BLUE;
	    case JrPenName.PEN_SENTIER_BLUE :
		case JrPenName.PEN_MAP_BLUE :
	    case JrPenName.PEN_PATH_BLUE :
	    case JrPenName.PEN_ROAD_BLUE :
	    case JrPenName.PEN_NATIONAL_BLUE :
	    	return (active == true)? colors[JrColorName.COLOR_TRT_MAP_VECTOR] : colors[JrColorName.COLOR_TRT_MAP_INACTIF];
	    case JrPenName.PEN_MAP_WHITE :
	    case JrPenName.PEN_ROAD_WHITE :
	    case JrPenName.PEN_NATIONAL_WHITE :	
    		return colors[JrColorName.COLOR_TRT_MAP_ROAD];
	   	case JrPenName.PEN_MAP_EXTRAGRAY :
    		return (active == true)? Color.GRAY : colors[JrColorName.COLOR_TRT_MAP_INACTIF_CLAIR];
    	case JrPenName.PEN_MAP_LIGHTGRAY :
    		return (active == true)? Color.LIGHT_GRAY : colors[JrColorName.COLOR_TRT_MAP_INACTIF_CLAIR];
    	case JrPenName.PEN_MAP_GREEN :
    		return (active == true)? Color.GREEN : colors[JrColorName.COLOR_TRT_MAP_INACTIF_CLAIR];
    	case JrPenName.PEN_MAP_ORANGE :
    		return (active == true)? Color.ORANGE : colors[JrColorName.COLOR_TRT_MAP_INACTIF_CLAIR];
    	case JrPenName.PEN_MAP_DARKGRAY :
    		return (active == true)? Color.DARK_GRAY : colors[JrColorName.COLOR_TRT_MAP_INACTIF_SOMBRE];
    	case JrPenName.PEN_RIVER_BLUE :
    		return (active == true)? colors[JrColorName.COLOR_TRT_MAP_RIVER] : colors[JrColorName.COLOR_TRT_MAP_INACTIF_CLAIR];
	    default: 
	    	break;
		}
		return (active == true)? colors[JrColorName.COLOR_TRT_MAP] : colors[JrColorName.COLOR_TRT_MAP_INACTIF];		
	}
	
	public Color getRegionColor(int regname) {
		switch(regname) {
		case JrRegionName.REGION_MAP : return colors[JrColorName.COLOR_BG_MAP];
		case JrRegionName.REGION_DISTANCE :
		case JrRegionName.REGION_RAZ :
		case JrRegionName.REGION_PARTIEL :
		case JrRegionName.REGION_APPROX : return colors[JrColorName.COLOR_BG_PARTIEL];
		case JrRegionName.REGION_TEXT : return colors[JrColorName.COLOR_BG_COMMENT];
		case JrRegionName.REGION_TOTAL : return colors[JrColorName.COLOR_BG_TOTAL];
		case JrRegionName.REGION_INDEX : return colors[JrColorName.COLOR_BG_CASE_NUM];
		case JrRegionName.REGION_SYMBOL1 :
		case JrRegionName.REGION_SYMBOL2 :
		case JrRegionName.REGION_SYMBOL3 :
		case JrRegionName.REGION_SYMBOL4 :
		case JrRegionName.REGION_SYMBOLS : return colors[JrColorName.COLOR_BG_SYMBOLES];
		default : break;
		}
		return Color.WHITE;
	}
	
	public Color getPageRegionColor(int regname) {
		switch(regname) {
	    case JrPageRegionName.PAGE_REGION_TITRE : return colors[JrColorName.COLOR_BG_TITRE];
	    case JrPageRegionName.PAGE_REGION_COPYRIGHT : return colors[JrColorName.COLOR_BG_COPYRIGHT];
	    case JrPageRegionName.PAGE_REGION_NUMBER : return colors[JrColorName.COLOR_BG_PAGE];
	    case JrPageRegionName.PAGE_REGION_PARTIEL1 : 
	    case JrPageRegionName.PAGE_REGION_PARTIEL2 : return colors[JrColorName.COLOR_BG_COL_PARTIEL];
	    case JrPageRegionName.PAGE_REGION_TOTAL1 :
	    case JrPageRegionName.PAGE_REGION_TOTAL2 : return colors[JrColorName.COLOR_BG_COL_TOTAL];
	    case JrPageRegionName.PAGE_REGION_TOTALPAGE1 :
	    case JrPageRegionName.PAGE_REGION_TOTALPAGE2 : return colors[JrColorName.COLOR_BG_COL_TOTALPAGE];
		default: break;
		}
		return Color.WHITE;
	}
}
