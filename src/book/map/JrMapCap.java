/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.Rectangle;

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
public class JrMapCap extends JrMap {
	private int cap = 270;
	
	public JrMapCap() {
		super();
	}

	public void save(JrFileSave file,String entry) {
		file.addEntry(entry,Integer.toString(cap));
	}
	
	public void read(JrFileRead file,String entry) {
		cap = file.getIntegerValue(entry,270);
	}
	
	public void readOld(JrFileRead file,String entry) {
		String str = file.getStringValue(entry,"270");
		String sp[] = str.split(";");
		cap = Integer.parseInt(sp[0]);
	}
			
	public JrMap copy() {
		JrMapCap map = new JrMapCap();
		map.cap = cap;
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_CAP;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_CAP_NAME;
	}

	public int getOriginCount() {
		return 1;
	}
	public int getDestinationCount() {
		return 1;
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		cap = 270;
		return true;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		if (cap == 0)
			cap = 345;
		else
			cap -= 15;
		return true;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		if (cap == 345)
			cap = 0;
		else
			cap += 15;
		return true;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		cap = 255;
		return true;
	}
		
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		  Rectangle rect = new Rectangle();
		  float pas = (pasx > pasy)? pasy : pasx;
		  float pas30 = pas * 3.0f;
		  float pas25 = pas * 2.5f;
		  int i,alpha;
		  float px1, py1, px2, py2, px3, py3, px4, py4;
		  double pcos,psin,angle;

		  float ptx[] = new float [4];
		  float pty[] = new float [4];
		  
		  alpha = ((cap + 90) >= 360)? (cap-270) : (cap+90);

		  dt.selectColor(JrColorName.COLOR_BG_CAP_PLAQUETTE,active);
		  dt.drawRectangle(ox-pas25,oy-(pas * 5),pas25 * 2,pas * 10,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);		  
		  dt.drawRectangle(ox-pas25,oy-(pas * 5),pas25 * 2,pas * 10,false);		  
		  dt.selectColor(JrColorName.COLOR_BG_CAP_COURONNE,active);
		  dt.drawEllipse(ox - pas30,oy - pas30,pas30 * 2,pas30 * 2,true);
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);		  
		  dt.drawEllipse(ox - pas30,oy - pas30,pas30 * 2,pas30 * 2,false);
		  
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);		  
		  for(i = 0; i < 24; i++) {
		    angle = (double)(i * 15);
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
		  dt.drawText(GetWord("TxtCapAu"),ox-pas25,oy-(pas * 5),pas25 * 2,pas * 2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  dt.drawText(Integer.toString(alpha),ox-pas25,oy+(pas * 3),pas25 * 2,pas * 2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  
		  //---- North ----
		  dt.drawLine(ox - CPAS(pas, 12), oy - CPAS(pas, 196),ox - CPAS(pas, 12), oy - CPAS(pas, 220));
		  dt.drawLine(ox - CPAS(pas, 12), oy - CPAS(pas, 220),ox + CPAS(pas, 12), oy - CPAS(pas, 196));
		  dt.drawLine(ox + CPAS(pas, 12), oy - CPAS(pas, 196),ox + CPAS(pas, 12), oy - CPAS(pas, 220));

		  //---- West ----
		  dt.drawLine(ox - CPAS(pas, 220), oy - CPAS(pas, 12),ox - CPAS(pas, 196), oy - CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 196), oy - CPAS(pas, 12),ox - CPAS(pas, 196), oy + CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 196), oy + CPAS(pas, 12),ox - CPAS(pas, 220), oy + CPAS(pas, 12));
		  dt.drawLine(ox - CPAS(pas, 220), oy + CPAS(pas, 12),ox - CPAS(pas, 220), oy - CPAS(pas, 12));

		  //---- South ----
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 196),ox - CPAS(pas, 12), oy + CPAS(pas, 196));
		  dt.drawLine(ox - CPAS(pas, 12), oy + CPAS(pas, 196),ox - CPAS(pas, 12), oy + CPAS(pas, 208));
		  dt.drawLine(ox - CPAS(pas, 12), oy + CPAS(pas, 208),ox + CPAS(pas, 12), oy + CPAS(pas, 208));
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 208),ox + CPAS(pas, 12), oy + CPAS(pas, 220));
		  dt.drawLine(ox + CPAS(pas, 12), oy + CPAS(pas, 220),ox - CPAS(pas, 12), oy + CPAS(pas, 220));

		  //---- East ----
		  dt.drawLine(ox + CPAS(pas, 220), oy - CPAS(pas, 12),ox + CPAS(pas, 196), oy - CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 196), oy - CPAS(pas, 12),ox + CPAS(pas, 196), oy + CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 196), oy + CPAS(pas, 12),ox + CPAS(pas, 220), oy + CPAS(pas, 12));
		  dt.drawLine(ox + CPAS(pas, 208), oy,ox + CPAS(pas, 196), oy);

		  alpha = cap;
		  angle = (double)cap;
		  pcos = Math.cos(Math.toRadians(angle));
		  psin = Math.sin(Math.toRadians(angle));
		  px1 = ox + (float)(((double)pas25)*pcos);
		  py1 = oy + (float)(((double)pas25)*psin);
		  alpha += ((alpha + 90) >= 360)? -270 : 90;
		  angle = (double)alpha;
		  pcos = Math.cos(Math.toRadians(angle));
		  psin = Math.sin(Math.toRadians(angle));
		  px2 = ox + (float)(((double)(pas/2))*pcos);
		  py2 = oy + (float)(((double)(pas/2))*psin);
		  alpha += ((alpha + 90) >= 360)? -270 : 90;
		  angle = (double)alpha;
		  pcos = Math.cos(Math.toRadians(angle));
		  psin = Math.sin(Math.toRadians(angle));
		  px3 = ox + (float)(((double)pas)*pcos);
		  py3 = oy + (float)(((double)pas)*psin);
		  alpha += ((alpha + 90) >= 360)? -270 : 90;
		  angle = (double)alpha;
		  pcos = Math.cos(Math.toRadians(angle));
		  psin = Math.sin(Math.toRadians(angle));
		  px4 = ox + (float)(((double)(pas/2))*pcos);
		  py4 = oy + (float)(((double)(pas/2))*psin);

		  dt.selectColor(JrColorName.COLOR_TRT_CAP_AIGUILLE1,active);		  
		  ptx[0] = (float)px1; pty[0] = (float)py1;
		  ptx[1] = (float)px4; pty[1] = (float)py4;
		  ptx[2] = (float)ox;  pty[2] = (float)oy;
		  ptx[3] = (float)px1; pty[3] = (float)py1;
		  dt.drawPolygon(ptx,pty,4,true);
		  ptx[0] = (float)px3; pty[0] = (float)py3;
		  ptx[1] = (float)px2; pty[1] = (float)py2;
		  ptx[2] = (float)ox;  pty[2] = (float)oy;
		  ptx[3] = (float)px3; pty[3] = (float)py3;
		  dt.drawPolygon(ptx,pty,4,true);

		  dt.selectColor(JrColorName.COLOR_TRT_CAP_AIGUILLE2,active);		  
		  ptx[0] = (float)px1; pty[0] = (float)py1;
		  ptx[1] = (float)px2; pty[1] = (float)py2;
		  ptx[2] = (float)ox;  pty[2] = (float)oy;
		  ptx[3] = (float)px1; pty[3] = (float)py1;
		  dt.drawPolygon(ptx,pty,4,true);

		  ptx[0] = (float)px3; pty[0] = (float)py3;
		  ptx[1] = (float)px4; pty[1] = (float)py4;
		  ptx[2] = (float)ox;  pty[2] = (float)oy;
		  ptx[3] = (float)px3; pty[3] = (float)py3;
		  dt.drawPolygon(ptx,pty,4,true);
		  
		  dt.selectColor(JrColorName.COLOR_TRT_CAP_CONTOUR,active);		  
		  dt.drawLine(px1,py1,px3,py3);
		  dt.drawLine(px3,py3,px2,py2);
		  dt.drawLine(px2,py2,px4,py4);
		  dt.drawLine(px4,py4,px1,py1);
		  dt.drawLine(px2,py2,px1,py1);
		  dt.drawLine(px4,py4,px3,py3);
	}
	
	public boolean handlePaletteEvent(int evt) {
		evt = evt + 270;
		evt -= (evt >= 360)? 360 : 0;
		if ((evt >= 0) && (evt < 360) && (evt != cap) && ((evt % 15) == 0)) {
			cap = evt;
			return true;
		}
		return false;
	}
}
