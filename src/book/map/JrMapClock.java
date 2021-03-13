/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;
import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
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
public class JrMapClock extends JrMap {
	private int clock = 6;
	
	public JrMapClock() {
		super();
	}

	public void save(JrFileSave file,String entry) {
		file.addEntry(entry,Integer.toString(clock));
	}
	
	public void read(JrFileRead file,String entry) {
		clock = file.getIntegerValue(entry,6);
	}
	
	public void readOld(JrFileRead file,String entry) {
		String str = file.getStringValue(entry,"0");
		String sp[] = str.split(";");
		clock = Integer.parseInt(sp[0]);
		clock = (clock / 30) + 3;
		clock -= (clock > 12)? 12 : 0;
	}
	
	public void readVeryOld(String[] data) {
		clock = 3;
	}
		
	public JrMap copy() {
		JrMapClock map = new JrMapClock();
		map.clock = clock;
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_CLOCK;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_CLOCK_NAME;
	}
	public int getOriginCount() {
		return 1;
	}
	public int getDestinationCount() {
		return 1;
	}

	public void inverse() {
		clock = (clock + 6) % 12;
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		clock = 1;
		return true;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		if (clock == 1)
			clock = 12;
		else
			clock--;
		return true;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		if (clock == 12)
			clock = 1;
		else
			clock++;
		return true;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		clock = 12;
		return true;
	}
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		  float pas = (pasx > pasy)? pasy : pasx;
		  float pas30 = pas * 3.0f;
		  float pas25 = pas * 2.5f;
		  int i, alpha;
		  float px1, py1, px2,py2;
		  double pcos,psin,angle;

		  float ptx[] = new float [4];
		  float pty[] = new float [4];
		  
		  dt.selectColor(JrColorName.COLOR_TRT_CLK_BOUTON,active);
		  dt.drawRectangle(ox+pas25,oy-(pas /2),(pas*3)/4,pas,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);
		  dt.drawRectangle(ox+pas25,oy-(pas /2),(pas*3)/4,pas,false);
		  dt.selectColor(JrColorName.COLOR_BG_CAP_PLAQUETTE,active);
		  dt.drawRectangle(ox-pas25,oy-(pas * 5),pas25 * 2,pas * 10,true);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);
		  dt.drawRectangle(ox-pas25,oy-(pas * 5),pas25 * 2,pas * 10,false);		  
		  dt.selectColor(JrColorName.COLOR_BG_CAP_COURONNE,active);
		  dt.drawEllipse(ox - pas30,oy - pas30,pas30 * 2,pas30 * 2,true);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);
		  dt.drawEllipse(ox - pas30,oy - pas30,pas30 * 2,pas30 * 2,false);

		  for(i = 0; i < 12; i++) {
		    angle = (double)(i * 30);
		    pcos = Math.cos(Math.toRadians(angle));
		    psin = Math.sin(Math.toRadians(angle));
		    px1 = (float)(((double)pas25)*pcos);
		    py1 = (float)(((double)pas25)*psin);
		    px2 = (float)(((double)pas30)*pcos);
		    py2 = (float)(((double)pas30)*psin);
		    dt.drawLine(ox+px1,oy+py1,ox+px2,oy+py2);
		  }

		  dt.selectColor(JrColorName.COLOR_BG_CAP_INTERIEUR,active);
		  dt.drawEllipse(ox - pas25,oy - pas25,pas25 * 2,pas25 * 2,true);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);
		  dt.drawEllipse(ox - pas25,oy - pas25,pas25 * 2,pas25 * 2,false);
		  
		  dt.selectColor(JrColorName.COLOR_TXT_CAP,active);
		  dt.drawText(GetWord("TxtAllezA"),ox-pas25,oy-(pas * 5),pas25 * 2,pas * 2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  dt.drawText(Integer.toString(clock) + "h00",ox-pas25,oy+(pas * 3),pas25 * 2,pas * 2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  
		  //---- 12 ----
		  dt.drawLine(ox - CPAS(pas, 12), oy - CPAS(pas, 196),ox - CPAS(pas, 12), oy - CPAS(pas, 220));
		  dt.drawLine(ox, oy - CPAS(pas, 220),ox + CPAS(pas, 12), oy - CPAS(pas, 220));
		  dt.drawLine(ox + CPAS(pas, 12), oy - CPAS(pas, 220),ox + CPAS(pas, 12), oy - CPAS(pas, 208));
		  dt.drawLine(ox + CPAS(pas, 12), oy - CPAS(pas, 208),ox, oy - CPAS(pas, 208));
		  dt.drawLine(ox, oy - CPAS(pas, 208),ox, oy - CPAS(pas, 196));
		  dt.drawLine(ox, oy - CPAS(pas, 196),ox + CPAS(pas, 12), oy - CPAS(pas, 196));

		  //---- 9 ----
		  dt.drawLine(ox - CPAS(pas, 196), oy, ox - CPAS(pas, 220), oy);
		  dt.drawLine(ox - CPAS(pas, 220), oy,ox - CPAS(pas, 220), oy - CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 220), oy - CPAS(pas, 12),ox - CPAS(pas, 196), oy - CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 196), oy - CPAS(pas, 12),ox - CPAS(pas, 196), oy + CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 196), oy + CPAS(pas, 12),ox - CPAS(pas, 220), oy + CPAS(pas, 12));

		  //---- 6 ----
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 196),ox - CPAS(pas, 12), oy + CPAS(pas, 196));
		  dt.drawLine(ox - CPAS(pas, 12), oy + CPAS(pas, 196),ox - CPAS(pas, 12), oy + CPAS(pas, 220));
		  dt.drawLine(ox - CPAS(pas, 12), oy + CPAS(pas, 220),ox + CPAS(pas, 12), oy + CPAS(pas, 220));
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 220),ox + CPAS(pas, 12), oy + CPAS(pas, 208));
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 208),ox - CPAS(pas, 12), oy + CPAS(pas, 208));

		  //---- 3 ----
		  dt.drawLine(ox + CPAS(pas, 196), oy - CPAS(pas, 12),ox + CPAS(pas, 220), oy - CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 220), oy - CPAS(pas, 12),ox + CPAS(pas, 220), oy + CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 220), oy + CPAS(pas, 12),ox + CPAS(pas, 196), oy + CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 208), oy,ox + CPAS(pas, 220), oy);
		  
		  dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		  alpha  = (clock * 30) + 270;
		  alpha -= (alpha >= 360)? 360 : 0;
		  pcos = Math.cos(Math.toRadians((double)alpha));
		  psin = Math.sin(Math.toRadians((double)alpha));
		  px1 = ox + (float)(((double)(pas25/2))*pcos);
		  py1 = oy + (float)(((double)(pas25/2))*psin);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_AIGUILLE2,active);
		  dt.drawLine(ox,oy,px1,py1);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_AIGUILLE2,active);
		  dt.drawLine(ox,oy,ox,oy+pas25);
	}

	public boolean handlePaletteEvent(int evt) {
		if ((evt > 0) && (evt < 13) && (evt != clock)) {
			clock = evt;
			return true;
		}
		return false;
	}

}
