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

import application.actions.JrActionMenu;
import application.dialogs.JrLineTextDialog;

import tools.JrContext;
import tools.JrDrawTools;
import tools.JrRegions;
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
public class JrMapVille extends JrMap {
	private final int LINE_COUNT = 5;
	private String textes[] = new String [LINE_COUNT];
	private int isEntree[] = new int [LINE_COUNT];
	
	private float vStartY;
	private float vHaut;
	private float vOffsetY;
	
	public JrMapVille() {
		super();
		for(int i = 0; i < LINE_COUNT; i++) {
			textes[i] = "";
			isEntree[i] = 1;
		}
	}
	
	public void save(JrFileSave file,String entry) {
		String s = "L;" + LINE_COUNT;
		file.addEntry(entry,s);
		for(int i = 0; i < LINE_COUNT; i++)
			file.addEntry(entry + "-" + i,isEntree[i] + ";" + textes[i]);
	}
	
	public void read(JrFileRead file,String entry) {
		int i,count,nb;
		String str = file.getStringValue(entry,"");
		String items[] = str.split(";");
		
		count = items.length;
		if (count == 2) {
			nb = Math.min(LINE_COUNT,Integer.parseInt(items[1]));
			for(i = 0; i < nb; i++) {
				str = file.getStringValue(entry + "-" + i,"");
				items = str.split(";");
				count = items.length;
				if (count == 2) {
					isEntree[i] = Integer.parseInt(items[0]);
					textes[i] = items[1];
				}
			}
		}
	}

	public void readOld(JrFileRead file,String entry) {
	}
	
	public void readVeryOld(String[] data) {
	}
	
	public JrMap copy() {
		JrMapVille map = new JrMapVille();
		for(int i = 0; i < LINE_COUNT; i++) {
			map.isEntree[i] = isEntree[i];
			map.textes[i] = new String(textes[i]);
		}
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_VILLE;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_VILLE_NAME;
	}
	
	public boolean setEntree(int index,int entree) {
		if (isEntree[index] != entree) {
			isEntree[index] = entree;
			return true;
		}
		return false;
	}
	
	public boolean selectFirstObjectName(JrContext cnt) {
		return false;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		return false;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		return false;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		return false;
	}

	public void initializeRegions(JrRegions regions) {
		super.initializeRegions(regions);
	    /*ox = regions.getMapOrigineX();
	    oy = regions.getMapOrigineY();
	    pasx = regions.getMapPasX();
	    pasy = regions.getMapPasY();
	    handlew = regions.getMapHandle();
	    Rectangle r = regions.get(JrRegionName.REGION_MAP);
	    originalW2 = r.width / 2;
	    originalH2 = r.height / 2;*/
		vStartY = oy - (5 * pasy);
		vHaut = 2 * pasy;
		vOffsetY = vHaut * 0.05f;
	}
	
	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		  
		dt.selectPen(2);
		dt.selectColor(JrColorName.COLOR_BG_VILLE);
	    for(i = 0; i < 5; i++) {
	    	dt.drawRectangle(ox-vx,vStartY+((vHaut*i)+vOffsetY),vx*2.0f,vHaut - (2.0f*vOffsetY),true);
	    }
	    
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);
	    for(i = 0; i < 5; i++) {
	    	dt.drawRectangle(ox-vx,vStartY+((vHaut*i)+vOffsetY),vx*2.0f,vHaut - (2.0f*vOffsetY),false);
	    }
	}
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int i;
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		  
		dt.selectPen(2);
		dt.selectColor(JrColorName.COLOR_BG_VILLE);
	    for(i = 0; i < LINE_COUNT; i++) {
	    	if (textes[i].length() > 0)
	    		dt.drawRectangle(ox-vx,vStartY+((vHaut*i)+vOffsetY),vx*2.0f,vHaut - (2.0f*vOffsetY),true);
	    }
	    
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectColor(JrColorName.COLOR_TXT_MAP_TEXT,active);
		for(i = 0; i < LINE_COUNT; i++) {
			if (textes[i].length() > 0) {
				float x = ox - (5.0f * pasx);
				float y = vStartY + (i * vHaut) + vOffsetY;
				float w = 10.0f * pasx;
				float h = vHaut - (2.0f * vOffsetY);
				if (disp.isFullText())
					dt.drawText(textes[i],x,y,w,h,JrRegionFontName.REGION_FONT_COMMENT_FULL2,1);
				else
					dt.drawText(textes[i],x,y,w,h,JrRegionFontName.REGION_FONT_COMMENT2,1);
			}
		}		
	}
	
	public void drawObjects(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {		
		int i;
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
	    
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.selectPen(2);
		dt.selectColor(JrColorName.COLOR_BARRE_VILLE,active);
	    for(i = 0; i < LINE_COUNT; i++) {
			if ((textes[i].length() > 0) && (isEntree[i] < 1)) {
				float x = ox - (5.0f * pasx);
				float y = vStartY + (i * vHaut) + vOffsetY;
				float w = 10.0f * pasx;
				float h = vHaut - (2.0f * vOffsetY);
				dt.drawLine(x,y,x+w,y+h);
			}
		}		
		dt.selectColor(JrColorName.COLOR_BORDER_VILLE);
	    for(i = 0; i < LINE_COUNT; i++) {
	    	if (textes[i].length() > 0)
	    		dt.drawRectangle(ox-vx,vStartY+((vHaut*i)+vOffsetY),vx*2.0f,vHaut - (2.0f*vOffsetY),false);
	    }

	}
	
	public boolean editText(int index) {		
		JrLineTextDialog dlg = new JrLineTextDialog(JrActionMenu.GetMainFrame(),textes[index],
				"TxtEditionText","MsgMaxCaracteres");
		if (dlg.execute() == true) {
			String str = dlg.getText();
			if (str.compareTo(textes[index]) != 0) {
				textes[index] = str;
				return true;
			}
		}
		
		return false;
	}
		
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		int pos = pointToIndex(evt.getX(),evt.getY());
		if (pos != -1)
			return editText(pos);
		return false;
	}
	public boolean handleDoubleClick(MouseEvent evt,JrContext cnt) {
		int pos = pointToIndex(evt.getX(),evt.getY());
		if (pos != -1)
			return editText(pos);
		return false;
	}
	
	public int pointToIndex(float x,float y) {
		float px = ox - (5.0f * pasx);
		float py = oy - (5.0f * pasy);

		int posx = (int)((x - px) / pasx);
		int posy = (int)((y - py) / vHaut);
		if ((posx >= 0) && (posx < 10) && (posy >= 0) && (posy < 5)) {
			return posy;
		}
		return -1;
	}
}
