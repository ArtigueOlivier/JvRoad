/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;
import tools.JrDrawTools;
import names.JrColorName;
import names.JrMapName;
import names.JrPenName;
import names.JrTraitName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapRondPoint extends JrMapCarrefour {
	public JrMapRondPoint() {
		super();
	}

	public void readVeryOld(String[] data) {
		int star[] = { 5, 10, 15, 20, 25, 30, 35, 0 };
		int cpt = data.length;
		if (cpt < 2)
			return;
		if ((Integer.parseInt(data[0]) == 4) && (cpt == 10)) {
		    for(int i = 2; i < 10; i++) {
		    	switch(Integer.parseInt(data[i])) {
		    	case 1 : setLine(star[i-2],JrTraitName.TRAIT_ROAD); break;
		    	case 2 : setLine(star[i-2],JrTraitName.TRAIT_ROAD_DEST); break;
		    	case 3 : setLine(star[i-2],JrTraitName.TRAIT_ROAD_DESTINV); break;
		    	case 4 : setLine(star[i-2],JrTraitName.TRAIT_ROAD_SRC); break;
		    	case 5 : setLine(star[i-2],JrTraitName.TRAIT_ROAD_SRCINV); break;
		    	case 6 :
		    	case 7 :
		    	case 8 : setLine(star[i-2],JrTraitName.TRAIT_ROAD); break;
		    	default: setLine(star[i-2],JrTraitName.TRAIT_NONE); break;
		    	}
		    }
		}
	}
	
	public JrMap copy() {
		JrMapRondPoint map = new JrMapRondPoint();
		for(int i = 0; i < 48; i++)
			map.setLine(i,getLine(i));
		return map;
	}
	
	public int getName() {
		return JrMapName.MAP_RONDPOINT_NAME;
	}
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {	
		super.drawBlack(dt,disp,active,currentView,filter);
		float pas = Math.min(pasx,pasy) * 1.5f;
		dt.selectColor(JrColorName.COLOR_TRT_MAP,active);
		dt.drawEllipse(ox-pas,oy-pas,pas*2.0f,pas*2.0f,true);
	}

	public void drawWhite(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		super.drawWhite(dt,disp,active,currentView,filter);
	}

	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		super.drawObjects(dt,disp,active,currentView,filter);
		float esp = dt.getPenWidth(JrPenName.PEN_PATH_BLACK);
		float pas = (Math.min(pasx,pasy) * 1.5f) - esp;
		dt.selectColor(JrColorName.COLOR_TRT_MAP_ROAD,active);
		dt.drawEllipse(ox-pas,oy-pas,pas*2.0f,pas*2.0f,true);
		pas = Math.min(pasx,pasy);
		dt.selectColor(JrColorName.COLOR_TRT_MAP,active);
		dt.drawEllipse(ox-pas,oy-pas,pas*2.0f,pas*2.0f,true);
		pas -= esp;
		dt.selectColor(JrColorName.COLOR_DSS_CENTRE_ROND_POINT,active);
		dt.drawEllipse(ox-pas,oy-pas,pas*2.0f,pas*2.0f,true);
	}
}
