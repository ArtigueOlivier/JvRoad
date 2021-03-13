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
public class JrMapLineText extends JrMap {
	private final int LINE_COUNT = 10;
	private String textes[] = new String [LINE_COUNT];
	private boolean center = false;
	private boolean adroite = false;
	
	public JrMapLineText() {
		super();
		for(int i = 0; i < LINE_COUNT; i++) {
			textes[i] = "";
		}
	}
	
	public void save(JrFileSave file,String entry) {
		String s = ((center)? "C;" : "L;") + LINE_COUNT;
		if (adroite == true)
			s = "R;" + LINE_COUNT;
		file.addEntry(entry,s);
		for(int i = 0; i < LINE_COUNT; i++)
			file.addEntry(entry + "-" + i,textes[i]);
	}
	
	public void read(JrFileRead file,String entry) {
		int i,count,nb;
		String str = file.getStringValue(entry,"");
		String items[] = str.split(";");
		
		count = items.length;
		if (count == 2) {
			center = (items[0].compareTo("C") == 0)? true : false;
			adroite = (items[0].compareTo("R") == 0)? true : false;
			nb = Math.min(LINE_COUNT,Integer.parseInt(items[1]));
			for(i = 0; i < nb; i++) {
				textes[i] = file.getStringValue(entry + "-" + i,"");
			}
		}
	}

	public void readOld(JrFileRead file,String entry) {
		String s = file.getStringValue(entry,"");
		String items[] = s.split("�");
		
		if (items.length <= 10) {
			for(int i = 0; i < items.length; i++) {
				if (items[i].length() > 0)
					textes[i] = new String(items[i]);
				else
					textes[i] = "";
			}
		}
	}
	
	public void readVeryOld(String[] data) {
		int i,val = 1;
		int cpt = data.length;
		StringBuffer buff = new StringBuffer();
		if (cpt < 2)
		    return;
				
		if ((Integer.parseInt(data[0]) == 6) && (cpt == 126)) {
			for(i = 2; (i < 33) && (val > 0); i++) {
				val = convertOldChar(Integer.parseInt(data[i]));
				if (val > 0)
					buff.append((char)val);
			}
			textes[3] = buff.toString();
			buff = new StringBuffer();
			val = 1;
			for(i = 33; (i < 64) && (val > 0); i++) {
				val = convertOldChar(Integer.parseInt(data[i]));
				if (val > 0)
					buff.append((char)val);
			}
			textes[4] = buff.toString();
			buff = new StringBuffer();
			val = 1;
			for(i = 64; (i < 95) && (val > 0); i++) {
				val = convertOldChar(Integer.parseInt(data[i]));
				if (val > 0)
					buff.append((char)val);
			}
			textes[5] = buff.toString();
			buff = new StringBuffer();
			val = 1;
			for(i = 95; (i < 126) && (val > 0); i++) {
				val = convertOldChar(Integer.parseInt(data[i]));
				if (val > 0)
					buff.append((char)val);
			}
			textes[6] = buff.toString();
		}
	}
	
	public int convertOldChar(int val) {
		switch(val) {
		case 0   : return 0;
		case -95 : return ((int)('�'));
		case -94 : return ((int)('�'));
		case -93 : return ((int)('�'));
		case -92 : return ((int)('�'));
		case -91 : return ((int)('�'));
		case -90 : return ((int)('�'));
		case -89 : return ((int)('�'));
		case -88 : return ((int)('�'));
		case -87 : return ((int)('�'));
		case -86 : return ((int)('�'));
		case -85 : return ((int)('�'));
		case -84 : return ((int)('�'));
		case -83 : return ((int)('�'));
		case -82 : return ((int)('�'));
		case -81 : return ((int)('�'));
		case -80 : return ((int)('�'));
		case -79 : return ((int)('�'));
		case -78 : return ((int)('�'));
		case -77 : return ((int)('�'));
		case -76 : return ((int)('�'));
		case -75 : return ((int)('�'));
		case -74 : return ((int)('�'));
		case -73 : return ((int)('�'));
		case -72 : return ((int)('�'));
		case -71 : return ((int)('�'));
		case -70 : return ((int)('�'));
		case -69 : return ((int)('�'));
		case -68 : return ((int)('�'));
		case -67 : return ((int)('�'));
		case -66 : return ((int)('�'));
		case -65 : return ((int)('�'));
		case -64 : return ((int)('�'));
		case -63 : return ((int)('�'));
		case -62 : return ((int)('�'));
		case -61 : return ((int)('�'));
		case -60 : return ((int)('�'));
		case -59 : return ((int)('�'));
		case -58 : return ((int)('�'));
		case -57 : return ((int)('�'));
		case -56 : return ((int)('�'));
		case -55 : return ((int)('�'));
		case -54 : return ((int)('�'));
		case -53 : return ((int)('�'));
		case -52 : return ((int)('�'));
		case -51 : return ((int)('�'));
		case -50 : return ((int)('�'));
		case -49 : return ((int)('�'));
		case -48 : return ((int)('�'));
		case -47 : return ((int)('�'));
		case -46 : return ((int)('�'));
		case -45 : return ((int)('�'));
		case -44 : return ((int)('�'));
		case -43 : return ((int)('�'));
		case -42 : return ((int)('�'));
		case -41 : return ((int)('�'));
		case -40 : return ((int)('�'));
		case -39 : return ((int)('�'));
		case -38 : return ((int)('�'));
		case -37 : return ((int)('�'));
		case -36 : return ((int)('�'));
		case -35 : return ((int)('�'));
		default  : break;
		}
		if (val > 0)
			return val+31;
		return val + 128 + 31;		
	}
		
	public JrMap copy() {
		JrMapLineText map = new JrMapLineText();
		for(int i = 0; i < LINE_COUNT; i++)
			map.textes[i] = new String(textes[i]);
		map.center = center;
		map.adroite = adroite;
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_LINETEXT;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_LINETEXT_NAME;
	}
	
	public boolean setCenter(boolean cnter) {
		if (cnter != center) {
			center = cnter;
			adroite = (center)? false : adroite;
			return true;
		}
		return false;
	}
	
	public boolean setRight(boolean right) {
		if (right != adroite) {
			adroite = right;
			center = (adroite)? false : center;
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

	public void drawFirstHandles(JrDrawTools dt,int currentView) {		
		int i;
	    float vx = 5.0f * pasx;
	    float vy = 5.0f * pasy;
		  
		dt.selectPen(1);
		dt.selectColor(JrColorName.COLOR_TRT_MAP_GUIDE);	  	
	    for(i = -5; i < 6; i++) {
	    	dt.drawLine(ox - vx,oy + (i * pasy),ox + vx,oy + (i * pasy));
	    }
        dt.drawLine(ox - vx,oy - vy,ox - vx,oy + vy);
        dt.drawLine(ox + vx,oy - vy,ox + vx,oy + vy);
	}
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		int align = (center)? 1 : 0;
		align = (adroite)? 2 : align;
		for(int i = 0; i < LINE_COUNT; i++) {
			if (textes[i].length() > 0) {
				float x = ox - (5.0f * pasx);
				float y = oy - (((float)(5 - i)) * pasy);
				float w = 10.0f * pasx;
				float h = pasy;
				dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
				dt.selectColor(JrColorName.COLOR_TXT_MAP_TEXT,active);
				if (disp.isFullText())
					dt.drawText(textes[i],x,y,w,h,JrRegionFontName.REGION_FONT_COMMENT_FULL2,align);
				else
					dt.drawText(textes[i],x,y,w,h,JrRegionFontName.REGION_FONT_COMMENT2,align);
			}
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
		int posy = (int)((y - py) / pasy);
		if ((posx >= 0) && (posx < 10) && (posy >= 0) && (posy < 10)) {
			return posy;
		}
		return -1;
	}
}
