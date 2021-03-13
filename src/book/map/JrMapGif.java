/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import book.displayer.JrCaseDisplayer;
import book.filter.JrFilter;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

import tools.JrContext;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import tools.files.JrFileFilter;
import tools.files.JrFileSave;
import names.JrMapName;
import names.JrPaletteName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMapGif extends JrMap {
	private String imageName = "";
	private ImageIcon image = null;
	private int luminosite = 100;
	private boolean adjust = false;
	
	public JrMapGif() {
		super();
	}
	
	public void save(JrFileSave file,String entry) {
		int ad = (adjust)? 1 : 0;
		String str = luminosite + ";" + ad + ";" + imageName;
		file.addEntry(entry,str);
	}
	
	public void read(JrFileRead file,String entry) {
		String str = file.getStringValue(entry,"100;0;");
		String s[] = str.split(";");
		if (s.length > 2) {
			luminosite = Integer.parseInt(s[0]);
			adjust = (Integer.parseInt(s[1]) == 1)? true : false;
			imageName = s[2];
			image = new ImageIcon(imageName);
		}
	}
	
	public JrMap copy() {
		JrMapGif map = new JrMapGif();
		map.imageName = new String(imageName);
		map.luminosite = luminosite;
		map.adjust = adjust;
		return map;
	}

	public int getObjectPaletteName() {
		return JrPaletteName.PALETTE_GIF;
	}

	/* (non-Javadoc)
	 * @see book.map.JrMap#getName()
	 */
	public int getName() {
		return JrMapName.MAP_GIF_NAME;
	}

	public int getOriginCount() {
		return 1;
	}
	public int getDestinationCount() {
		return 1;
	}

	public boolean selectFirstObjectName(JrContext cnt) {
		luminosite = 100;
		JrActionCenter.RefreshActionsEditImage();
		return true;
	}
	
	public boolean selectPreviousObjectName(JrContext cnt) {
		if (luminosite == 0)
			return false;
		luminosite -= 10;
		JrActionCenter.RefreshActionsEditImage();
		return true;
	}
	
	public boolean selectNextObjectName(JrContext cnt) {
		luminosite += 10;
		JrActionCenter.RefreshActionsEditImage();
		return true;
	}
	
	public boolean selectLastObjectName(JrContext cnt) {
		luminosite = 0;
		JrActionCenter.RefreshActionsEditImage();
		return true;
	}
		
	public void drawBlack(JrDrawTools dt,JrCaseDisplayer disp,boolean active,int currentView,JrFilter filter) {
		if (image != null)
			dt.drawImage(image,ox-originalW2+5,oy-originalH2+5,(originalW2*2)-10,
						(originalH2*2)-10,luminosite,adjust,active);
	}

	public boolean selectImage() {
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter("JPEG (*.jpg)","jpg"));
		fc.addChoosableFileFilter(new JrFileFilter("GIF (*.gif)","gif"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(JrActionMenu.GetMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            imageName = fil.toString();
            image = new ImageIcon(imageName);
            return true;
        }
        return false;
	}
	public boolean cancelImage() {
		imageName = "";
		image = null;
		return true;
	}
	public boolean increaseLimunosite() {
		luminosite += 10;
		return true;
	}
	public boolean descreaseLimunosite() {
		if (luminosite > 0) {
			luminosite -= 10;
			return true;
		}
		return false;		
	}
	public int getLimunosite() {
		return luminosite;
	}
	public boolean setAdjustMode(boolean adjust) {
		if (this.adjust == adjust)
			return false;
		this.adjust = adjust;
		return true;
	}
	public boolean isAdjustMode() {
		return adjust;
	}
	
	public boolean handleLeftButton(MouseEvent evt,JrContext cnt) {
		return selectImage();
	}
	public boolean handleDoubleClick(MouseEvent evt,JrContext cnt) {
		return selectImage();
	}
}
