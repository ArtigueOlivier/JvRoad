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
import names.JrClipartName;
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
public class JrMapClipart extends JrMap {
	private int clipartCode = JrClipartName.CLIPART_NAVIG;
	
	public JrMapClipart() {
		super();
	}

	public void save(JrFileSave file,String entry) {
		file.addEntry(entry,Integer.toString(clipartCode));
	}
	
	public void read(JrFileRead file,String entry) {
		clipartCode = file.getIntegerValue(entry,JrClipartName.CLIPART_NAVIG);
	}
	
	public void readOld(JrFileRead file,String entry) {
		String str = file.getStringValue(entry,"0");
		String sp[] = str.split(";");
		clipartCode = Integer.parseInt(sp[0]);
	}
	
	public void readVeryOld(String[] data) {
		int cpt = data.length;
		if ((cpt > 2) && (Integer.parseInt(data[0]) == 3)) {
		    switch(Integer.parseInt(data[2])) {
		      case 1 : clipartCode = 2; break;
		      case 2 : clipartCode = 1; break;
		      default: clipartCode = Integer.parseInt(data[2]);
		    }
		}
	}
		
	public JrMap copy() {
		JrMapClipart map = new JrMapClipart();
		map.clipartCode = clipartCode;
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_CLIPART;
	}

	public int getName() {
		return JrMapName.MAP_CLIPART_NAME;
	}

	public int getOriginCount() {
		return 1;
	}
	public int getDestinationCount() {
		return 1;
	}

	public void inverse() {
		switch(clipartCode) {
		case JrClipartName.CLIPART_END : clipartCode = JrClipartName.CLIPART_START; break;
		case JrClipartName.CLIPART_START : clipartCode = JrClipartName.CLIPART_END; break;
		default : break;
		}
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		clipartCode = JrClipartName.CLIPART_NAVIG;
		return true;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		if (clipartCode == JrClipartName.CLIPART_NAVIG)
			clipartCode = JrClipartName.CLIPART_COUNT - 1;
		else
			clipartCode--;
		return true;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		if (clipartCode == (JrClipartName.CLIPART_COUNT - 1))
			clipartCode = JrClipartName.CLIPART_NAVIG;
		else
			clipartCode++;
		return true;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		clipartCode = JrClipartName.CLIPART_COUNT - 1;
		return true;
	}
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		switch(clipartCode) {
		case JrClipartName.CLIPART_START : drawStart(dt,active,true); break;
		case JrClipartName.CLIPART_END : drawStart(dt,active,false); break;
		case JrClipartName.CLIPART_REPAS : drawRepas(dt,active); break;
		case JrClipartName.CLIPART_PAUSE : drawPause(dt,active); break;
		case JrClipartName.CLIPART_CP : drawCP(dt,active); break;
		case JrClipartName.CLIPART_CH : drawCH(dt,active); break;
		default : drawNavig(dt,active); break;
		}
	}
		
	public void drawNavig(JrDrawTools dt,boolean active) {
		  float pas = Math.min(pasx,pasy);
		  float pas40 = pas * 5.0f;
		  float pas45 = pas * 5.5f;
		  int i,alpha;
		  float px1, py1, px2, py2, px3, py3, px4, py4;
		  double pcos,psin,angle;

		  float ptx[] = new float [3];
		  float pty[] = new float [3];
		  
		  dt.selectColor(JrColorName.COLOR_CLP_VENT_CADRANT,active);
		  dt.drawEllipse(ox - pas45,oy - pas45,pas45 * 2,pas45 * 2,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_VENT_CONTOUR,active);
		  dt.drawEllipse(ox - pas45,oy - pas45,pas45 * 2,pas45 * 2,false);
		  
		  for(i = 0; i < 24; i++) {
		    angle = (double)(i * 15);
		    pcos = Math.cos(Math.toRadians(angle));
		    psin = Math.sin(Math.toRadians(angle));
		    px1 = (int)(((double)pas40)*pcos);
		    py1 = (int)(((double)pas40)*psin);
		    px2 = (int)(((double)pas45)*pcos);
		    py2 = (int)(((double)pas45)*psin);
		    dt.drawLine(ox+px1,oy+py1,ox+px2,oy+py2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_VENT_FOND,active);
		  dt.drawEllipse(ox - pas40,oy - pas40,pas40 * 2,pas40 * 2,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_VENT_CONTOUR,active);
		  dt.drawEllipse(ox - pas40,oy - pas40,pas40 * 2,pas40 * 2,false);

		  ptx[0] = 0; pty[0] = 0; ptx[1] = 0; pty[1] = -5; ptx[2] = 1; pty[2] = -1;
		  drawPale(dt,active,ptx,pty,pas,false);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = 1; pty[1] = -1; ptx[2] = 5; pty[2] = 0;
		  drawPale(dt,active,ptx,pty,pas,true);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = 5; pty[1] = 0; ptx[2] = 1; pty[2] = 1;
		  drawPale(dt,active,ptx,pty,pas,false);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = 1; pty[1] = 1; ptx[2] = 0; pty[2] = 5;
		  drawPale(dt,active,ptx,pty,pas,true);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = 0; pty[1] = 5; ptx[2] = -1; pty[2] = 1;
		  drawPale(dt,active,ptx,pty,pas,false);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = -5; pty[1] = 0; ptx[2] = -1; pty[2] = 1;
		  drawPale(dt,active,ptx,pty,pas,true);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = -5; pty[1] = 0; ptx[2] = -1; pty[2] = -1;
		  drawPale(dt,active,ptx,pty,pas,false);
		  ptx[0] = 0; pty[0] = 0; ptx[1] = -1; pty[1] = -1; ptx[2] = 0; pty[2] = -5;
		  drawPale(dt,active,ptx,pty,pas,true);
	}
	public void drawStart(JrDrawTools dt,boolean active,boolean start) {
		float pasy2 = pasy / 2;
		float pasy4 = pasy / 4;
		float pasx2 = pasx / 2;
		float px;
		float py = oy + (pasy * 7) / 2;
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_POTEAU,active);
		dt.drawEllipse(ox-(5*pasx),oy+(5*pasy)-pasy4,pasx,pasy2, true);
		dt.drawEllipse(ox+(4*pasx),oy+(5*pasy)-pasy4,pasx,pasy2, true);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_CONTOUR);
		dt.drawEllipse(ox-(5*pasx),oy+(5*pasy)-pasy4,pasx,pasy2, false);
		dt.drawEllipse(ox+(4*pasx),oy+(5*pasy)-pasy4,pasx,pasy2, false);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_POTEAU,active);
		dt.drawRectangle(ox-(5*pasx),oy-(5*pasy),pasx,pasy*10,true);
		dt.drawRectangle(ox+(4*pasx),oy-(5*pasy),pasx,pasy*10,true);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_POTEAU_SOMBRE,active);
		dt.drawEllipse(ox-(5*pasx),oy-(5*pasy)-pasy4,pasx,pasy2, true);
		dt.drawEllipse(ox+(4*pasx),oy-(5*pasy)-pasy4,pasx,pasy2, true);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_DAMIER_BLANC,active);
		dt.drawRectangle(ox - (4 * pasx),oy + (pasy * 7) / 2,pasx*8,pasy2 * 3,true);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_BANDE,active);
		dt.drawRectangle(ox-(4*pasx),oy-(5*pasy),pasx*8,pasy * 2,true);		
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectColor(JrColorName.COLOR_CLP_DEP_CONTOUR);
		dt.drawLine(ox-(5*pasx),oy-(5*pasy),ox-(5*pasx),oy+(5*pasy));
		dt.drawLine(ox-(4*pasx),oy-(5*pasy),ox-(4*pasx),oy+(5*pasy));
		dt.drawLine(ox+(5*pasx),oy-(5*pasy),ox+(5*pasx),oy+(5*pasy));
		dt.drawLine(ox+(4*pasx),oy-(5*pasy),ox+(4*pasx),oy+(5*pasy));
		dt.drawLine(ox-(4*pasx),oy-(5*pasy),ox+(4*pasx),oy-(5*pasy));
		dt.drawLine(ox-(4*pasx),oy-(3*pasy),ox+(4*pasx),oy-(3*pasy));
		dt.drawLine(ox-(4*pasx),oy+(3*pasy)+pasy2,ox+(4*pasx),oy+(3*pasy)+pasy2);
		dt.drawLine(ox-(4*pasx),py+pasy+pasy2,ox+(4*pasx),py+pasy+pasy2);
		dt.drawEllipse(ox-(5*pasx),oy-(5*pasy)-pasy4,pasx,pasy2, false);
		dt.drawEllipse(ox+(4*pasx),oy-(5*pasy)-pasy4,pasx,pasy2, false);
		
		dt.selectColor(JrColorName.COLOR_CLP_DEP_DAMIER_NOIR);		
		for(int i = 0; i < 8; i++) {
			px = ox - ((4 - i) * pasx);
			dt.drawRectangle(px,py,pasx2,pasy2,true);
			dt.drawRectangle(px+pasx2,py+pasy2,pasx2,pasy2,true);
			dt.drawRectangle(px,py+pasy,pasx2,pasy2,true);
		}
		dt.selectColor(JrColorName.COLOR_CLP_DEP_TEXTE);
		if (start)
			dt.drawText(GetWord("TxtDepart"),ox-(4*pasx),oy-(5*pasy),pasx*8,pasy*2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		else
			dt.drawText(GetWord("TxtArrivee"),ox-(4*pasx),oy-(5*pasy),pasx*8,pasy*2,JrRegionFontName.REGION_FONT_MAP_BIG,1);
	}
	
	public void drawRepas(JrDrawTools dt,boolean active) {
		  int i;
		  float pas = (pasx > pasy)? pasy : pasx;
		  float pas25 = (pas * 5) / 2;
		  float pas20 = pas * 2;
		  float ptx[] = new float [8];
		  float pty[] = new float [8];

		  dt.selectColor(JrColorName.COLOR_CLP_REPAS_CONTOUR,active);
		  dt.drawEllipse(ox-pas25,oy-pas25,(pas*5),(pas*5),true);
		  dt.selectColor(JrColorName.COLOR_CLP_REPAS_FOND,active);
		  dt.drawEllipse(ox-pas20,oy-pas20,(pas*4),(pas*4),true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_REPAS_CONTOUR,active);
		  
		  ptx[0] = 6; pty[0] = -5; ptx[1] = 7; pty[1] = -4;
		  ptx[2] = 8; pty[2] =  0; ptx[3] = 7; pty[3] =  0;
		  ptx[4] = 7; pty[4] =  5; ptx[5] = 6; pty[5] =  5;
		  ptx[6] = 6; pty[6] = -5;
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.drawPolygon(ptx,pty,7,true);
		  
		  ptx[0] = -6; pty[0] =  5; ptx[1] = -7; pty[1] =  5;
		  ptx[2] = -7; pty[2] = -1; ptx[3] = -8; pty[3] = -3;
		  ptx[4] = -5; pty[4] = -3; ptx[5] = -6; pty[5] = -1;
		  ptx[6] = -6; pty[6] =  5;
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.drawPolygon(ptx,pty,7,true);

		  dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_REPAS_CONTOUR,active);
		  ptx[0] = -8; pty[0] = -5; ptx[1] = -8; pty[1] = -3;
		  ptx[2] = -7; pty[2] = -3; ptx[3] = -7; pty[3] = -5;
		  ptx[4] = -6; pty[4] = -5; ptx[5] = -6; pty[5] = -3;
		  ptx[6] = -5; pty[6] = -3; ptx[7] = -5; pty[7] = -5;
		  for(i = 0; i < 8; i += 2) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		    ptx[i+1] = ox + ((ptx[i+1] * pas) / 2);
		    pty[i+1] = oy + ((pty[i+1] * pas) / 2);
		    dt.drawLine(ptx[i],pty[i],ptx[i+1],pty[i+1]);
		  }

		  dt.selectColor(JrColorName.COLOR_CLP_REPAS_TEXTE,active);
		  dt.drawText(GetWord("TxtPause"),ox-(5*pasx),oy-(5*pasy),10*pasx,2*pasy,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  dt.drawText(GetWord("TxtRepas"),ox-(5*pasx),oy+(3*pasy),10*pasx,2*pasy,JrRegionFontName.REGION_FONT_MAP_BIG,1);	
	}
	
	public void drawPause(JrDrawTools dt,boolean active) {
		float pasx2 = pasx / 2;
		float pasy2 = pasy / 2;
		
		dt.selectColor(JrColorName.COLOR_CLP_TASSE,active);
		dt.drawEllipse(ox-(5*pasx),oy+pasy,(pasx*10),(pasy*4),true);
		dt.selectColor(JrColorName.COLOR_CLP_TASSE_SOUCOUPE,active);
		dt.drawEllipse(ox-(4*pasx)-pasx2,oy+pasy+pasy2,(pasx*9),(pasy*3),true);
		dt.selectColor(JrColorName.COLOR_CLP_TASSE,active);
		dt.drawEllipse(ox-(3*pasx)-pasx2,oy+(pasy*2),(pasx*7),(pasy*2),true);
		dt.drawEllipse(ox+(2*pasx),oy-(4*pasy),(pasx*3),(pasy*4),true);
		dt.selectColor(JrColorName.COLOR_BG_MAP);
		dt.drawEllipse(ox+(2*pasx)+pasx2,oy-(3*pasy)-pasy2,(pasx*2),(pasy*3),true);
		
		
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectColor(JrColorName.COLOR_CLP_TASSE_CONTOUR,active);
		dt.drawEllipse(ox-(5*pasx),oy+pasy,(pasx*10),(pasy*4),false);
		dt.drawEllipse(ox-(4*pasx)-pasx2,oy+pasy+pasy2,(pasx*9),(pasy*3),false);
		dt.drawEllipse(ox-(3*pasx)-pasx2,oy+(2*pasy),(pasx*7),(pasy*2),false);
		
		dt.drawEllipse(ox+(2*pasx),oy-(4*pasy),(pasx*3),(pasy*4),false);
		dt.drawEllipse(ox+(2*pasx)+pasx2,oy-(3*pasy)-pasy2,(pasx*2),(pasy*3),false);
		
		dt.selectColor(JrColorName.COLOR_CLP_TASSE,active);
		dt.drawRectangle(ox-(3*pasx)-pasx2,oy-(4*pasy),(pasx*7),(pasy*7),true);
		
		dt.selectColor(JrColorName.COLOR_CLP_TASSE_INTERIEUR,active);
		dt.drawEllipse(ox-(3*pasx)-pasx2,oy-(5*pasy),(pasx*7),pasy*2,true);
		dt.selectColor(JrColorName.COLOR_CLP_TASSE_CONTOUR,active);
		dt.drawLine(ox-(3*pasx)-pasx2,oy+(3*pasy),ox-(3*pasx)-pasx2,oy-(4*pasy));
		dt.drawLine(ox+(3*pasx)+pasx2,oy+(3*pasy),ox+(3*pasx)+pasx2,oy);	
		dt.drawLine(ox+(3*pasx)+pasx2,oy-pasy2,ox+(3*pasx)+pasx2,oy-pasy2-(3*pasy));		
		dt.drawEllipse(ox-(3*pasx)-pasx2,oy-(5*pasy),(pasx*7),pasy*2,false);
		dt.selectColor(JrColorName.COLOR_CLP_TASSE_TEXTE,active);
		dt.drawText(GetWord("TxtPause"),ox-(3*pasx),oy-(3*pasy),pasx*6,pasy*4,JrRegionFontName.REGION_FONT_MAP_BIG,1);
	}
	public void drawCP(JrDrawTools dt,boolean active) {
		  int i;
		  float pas = (pasx > pasy)? pasy : pasx;
		  float pas2;
		  float ptx[] = new float [9];
		  float pty[] = new float [9];
		  
		  ptx[0] = -4; pty[0] = -9; ptx[1] =  4; pty[1] = -9;
		  ptx[2] =  5; pty[2] = -8; ptx[3] =  5; pty[3] = -7;
		  ptx[4] =  4; pty[4] = -6; ptx[5] = -4; pty[5] = -6;
		  ptx[6] = -5; pty[6] = -7; ptx[7] = -5; pty[7] = -8;
		  ptx[8] = -4; pty[8] = -9; 
		  for(i = 0; i < 9; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_DESSUS,active);
		  dt.drawPolygon(ptx,pty,9,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,9,false);
		  ptx[0] =  2; pty[0] = -6; ptx[1] =  2; pty[1] =  0;
		  ptx[2] =  3; pty[2] =  1; ptx[3] = -3; pty[3] =  1;
		  ptx[4] = -2; pty[4] =  0; ptx[5] = -2; pty[5] = -6;
		  ptx[6] =  2; pty[6] = -6; 
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_TRONC,active);
		  dt.drawPolygon(ptx,pty,7,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,7,false);
		  ptx[0] =  6; pty[0] =  1; ptx[1] =  8; pty[1] =  2;
		  ptx[2] =  8; pty[2] =  4; ptx[3] = -8; pty[3] =  4;
		  ptx[4] = -8; pty[4] =  2; ptx[5] = -6; pty[5] =  1;
		  ptx[6] =  6; pty[6] =  1;
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_DESSUS,active);
		  dt.drawPolygon(ptx,pty,7,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,7,false);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_ENCRE,active);		  
		  dt.drawRectangle(ox-((7*pas)/2),oy+(2*pas),pas*7,pas/2,true);
		  dt.selectDefinedPen(JrPenName.PEN_PATH_BLUE,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_TEXTE,active);		  
		  dt.drawText(GetWord("TxtCP"),ox-(4*pas),oy+(3*pas),8*pas,2*pas,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  		  
		  pas2 = (pas * 7)/2;
		  dt.drawLine(ox - pas2,oy + pas2,ox - pas2,oy + (3*pas));
		  dt.drawLine(ox - pas2,oy + (3*pas),ox - (3*pas),oy + (3*pas));

		  dt.drawLine(ox - pas2+pas,oy + (3*pas),ox + pas2 -pas,oy + (3*pas));
		  dt.drawLine(ox + (3*pas),oy + (3*pas),ox + pas2,oy + (3*pas));
		  dt.drawLine(ox + pas2,oy + (3*pas),ox + pas2,oy + pas2);
		  dt.drawLine(ox - pas2+pas,oy + (5*pas),ox + pas2-pas,oy + (5*pas));

		  dt.drawLine(ox + pas2,oy + pas2 + pas,ox + pas2,oy + (5*pas));
		  dt.drawLine(ox + pas2,oy + (5*pas),ox + (3*pas),oy + (5*pas));
		  dt.drawLine(ox - pas2,oy + pas2 + pas,ox - pas2,oy + (5*pas));
		  dt.drawLine(ox - pas2,oy + (5*pas),ox - (3*pas),oy + (5*pas));
	}
	
	public void drawCH(JrDrawTools dt,boolean active) {
		  int i;
		  float pas = (pasx > pasy)? pasy : pasx;
		  float pas2;
		  float ptx[] = new float [9];
		  float pty[] = new float [9];
		  
		  ptx[0] = -4; pty[0] = -9; ptx[1] =  4; pty[1] = -9;
		  ptx[2] =  5; pty[2] = -8; ptx[3] =  5; pty[3] = -7;
		  ptx[4] =  4; pty[4] = -6; ptx[5] = -4; pty[5] = -6;
		  ptx[6] = -5; pty[6] = -7; ptx[7] = -5; pty[7] = -8;
		  ptx[8] = -4; pty[8] = -9; 
		  for(i = 0; i < 9; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_DESSUS,active);
		  dt.drawPolygon(ptx,pty,9,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,9,false);
		  ptx[0] =  2; pty[0] = -6; ptx[1] =  2; pty[1] =  0;
		  ptx[2] =  3; pty[2] =  1; ptx[3] = -3; pty[3] =  1;
		  ptx[4] = -2; pty[4] =  0; ptx[5] = -2; pty[5] = -6;
		  ptx[6] =  2; pty[6] = -6; 
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_TRONC,active);
		  dt.drawPolygon(ptx,pty,7,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,7,false);
		  ptx[0] =  6; pty[0] =  1; ptx[1] =  8; pty[1] =  2;
		  ptx[2] =  8; pty[2] =  4; ptx[3] = -8; pty[3] =  4;
		  ptx[4] = -8; pty[4] =  2; ptx[5] = -6; pty[5] =  1;
		  ptx[6] =  6; pty[6] =  1;
		  for(i = 0; i < 7; i++) {
		    ptx[i] = ox + ((ptx[i] * pas) / 2);
		    pty[i] = oy + ((pty[i] * pas) / 2);
		  }
		  dt.selectColor(JrColorName.COLOR_CLP_CP_DESSUS,active);
		  dt.drawPolygon(ptx,pty,7,true);
		  dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_CONTOUR,active);
		  dt.drawPolygon(ptx,pty,7,false);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_ENCRE,active);		  
		  dt.drawRectangle(ox-((7*pas)/2),oy+(2*pas),pas*7,pas/2,true);
		  dt.selectDefinedPen(JrPenName.PEN_PATH_BLUE,active);
		  dt.selectColor(JrColorName.COLOR_CLP_CP_TEXTE,active);		  
		  dt.drawText(GetWord("TxtCH"),ox-(4*pas),oy+(3*pas),8*pas,2*pas,JrRegionFontName.REGION_FONT_MAP_BIG,1);
		  		  
		  pas2 = (pas * 7)/2;
		  dt.drawLine(ox - pas2,oy + pas2,ox - pas2,oy + (3*pas));
		  dt.drawLine(ox - pas2,oy + (3*pas),ox - (3*pas),oy + (3*pas));

		  dt.drawLine(ox - pas2+pas,oy + (3*pas),ox + pas2 -pas,oy + (3*pas));
		  dt.drawLine(ox + (3*pas),oy + (3*pas),ox + pas2,oy + (3*pas));
		  dt.drawLine(ox + pas2,oy + (3*pas),ox + pas2,oy + pas2);
		  dt.drawLine(ox - pas2+pas,oy + (5*pas),ox + pas2-pas,oy + (5*pas));

		  dt.drawLine(ox + pas2,oy + pas2 + pas,ox + pas2,oy + (5*pas));
		  dt.drawLine(ox + pas2,oy + (5*pas),ox + (3*pas),oy + (5*pas));
		  dt.drawLine(ox - pas2,oy + pas2 + pas,ox - pas2,oy + (5*pas));
		  dt.drawLine(ox - pas2,oy + (5*pas),ox - (3*pas),oy + (5*pas));
	}
	

	public void drawPale(JrDrawTools dt,boolean active,float ptx[],float pty[],float pas,boolean white) {
		for(int i = 0; i < 3; i++) {
			ptx[i] = ox + (ptx[i] * pas); 
			pty[i] = oy + (pty[i] * pas); 
		}
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		if (white)
			dt.selectColor(JrColorName.COLOR_CLP_VENT_AIGUILLE1,active);
		else
			dt.selectColor(JrColorName.COLOR_CLP_VENT_AIGUILLE2,active);
		dt.drawPolygon(ptx,pty,3,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectColor(JrColorName.COLOR_CLP_VENT_CONTOUR,active);
		dt.drawPolygon(ptx,pty,3,false);			
	}
	
	public boolean handlePaletteEvent(int evt) {
		if ((evt >= 0) && (evt < JrClipartName.CLIPART_COUNT) && (evt != clipartCode)) {
			clipartCode = evt;
			return true;
		}
		return false;
	}

}
