/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;

import application.actions.JrActionMenu;
import application.dialogs.JrTextDialog;

import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import names.JrColorName;
import names.JrMapName;
import names.JrPaletteName;
import names.JrPenName;
import names.JrRegionFontName;
import names.JrRegionName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapText extends JrMap {
	private String texte = "";
	private boolean center = false;
	private boolean adroite = false;
	
	public JrMapText() {
		super();
	}
	
	public void save(JrFileSave file,String entry) {
		String a = (center)? "@C@" : "@L@";
		if (adroite == true) {
			a = "@R@";
		}
		String s = a + texte.replaceAll("\n","#13#");
		file.addEntry(entry,s);
	}
	
	public void read(JrFileRead file,String entry) {
		String s = file.getStringValue(entry,"");
		int len = s.length();
		if (len >= 3) {
			if ((s.charAt(0) == '@') && (s.charAt(1) == 'C') &&(s.charAt(2) == '@'))
				center = true;
			else {
				if ((s.charAt(0) == '@') && (s.charAt(1) == 'R') &&(s.charAt(2) == '@'))
					adroite = true;
			}
		}
		String s1 = s.replaceAll("@C@","");
		String s2 = s1.replaceAll("@L@","");
		String s3 = s2.replaceAll("@R@","");
		texte = s3.replaceAll("#13#","\n");
	}
		
	public JrMap copy() {
		JrMapText map = new JrMapText();
		map.texte = new String(texte);
		map.center = center;
		map.adroite = adroite;
		return map;
	}
	
	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_TEXT;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_TEXT_NAME;
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
			center = (right)? false : center;
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
	
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		if (texte.length() > 0) {
			int align = (center)? 1 : 0;
			align = (adroite)? 2 : align;
			Rectangle rect = (Rectangle)(dt.getRegion(JrRegionName.REGION_MAP).clone());
			dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
			dt.selectColor(JrColorName.COLOR_TXT_MAP_TEXT,active);
			
			if (disp.isFullText())
				dt.drawTextArea(rect,JrRegionFontName.REGION_FONT_COMMENT_FULL2,texte,align);
			else
				dt.drawTextArea(rect,JrRegionFontName.REGION_FONT_COMMENT2,texte,align);
		}
	}
	
	public boolean editText() {
		JrTextDialog dlg = new JrTextDialog(JrActionMenu.GetMainFrame(),texte,
				"TxtEditionText","MsgMaxCaracteres");
		if (dlg.execute() == true) {
			String str = dlg.getText();
			if (str.compareTo(texte) != 0) {
				texte = str;
				return true;
			}
		}
		return false;
	}
	
	public boolean cancelText() {
		if (texte.length() > 0) {
			texte = "";
			return true;
		}
		return false;
		
	}
	
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		return editText();
	}
	public boolean handleDoubleClick(MouseEvent evt,JrContext cnt) {
		return editText();
	}
}
