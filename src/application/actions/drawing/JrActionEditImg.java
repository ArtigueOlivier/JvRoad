/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import book.JrBook;
import book.JrCase;
import book.map.JrMap;
import book.map.JrMapGif;

import names.JrImgOperatorName;
import names.JrMapName;
import names.JrViewName;

import application.JrApplicationOption;
import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditImg extends JrActionMenu {
	private int opeImg = JrImgOperatorName.IMG_OPE_SELECT;
	public JrActionEditImg(String txt, String hlp, String img,int ope) {
		super(txt, 0, hlp, img);
		opeImg = ope;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		boolean res = false;
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();
		JrMap map = cas.getMap(book.getCurrentView());
		if (map.getName() == JrMapName.MAP_GIF_NAME) {
			switch(opeImg) {
			case JrImgOperatorName.IMG_OPE_SELECT :
				res = ((JrMapGif)map).selectImage(); break;
			case JrImgOperatorName.IMG_OPE_ERASE :
				res = ((JrMapGif)map).cancelImage(); break;
			case JrImgOperatorName.IMG_OPE_PLUSSOMBRE :
				res = ((JrMapGif)map).descreaseLimunosite(); break;
			case JrImgOperatorName.IMG_OPE_PLUSCLAIR :
				res = ((JrMapGif)map).increaseLimunosite(); break;
			case JrImgOperatorName.IMG_OPE_ADJUST :
				res = ((JrMapGif)map).setAdjustMode(true); break;
			case JrImgOperatorName.IMG_OPE_DEPEND :
				res = ((JrMapGif)map).setAdjustMode(false); break;
			default : break;
			}
		}
		if (res) {
			book.fireCurrentViewChanged();
			JrActionCenter.RefreshActionsEditImage();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == false) 
			return false;
		if (JrApplicationOption.GetCode() != 129834765)
			return false;
		JrCase cas = book.getCurrentCase();
		int view = book.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			JrMap map = cas.getMap(book.getCurrentView());
			if (map.getName() == JrMapName.MAP_GIF_NAME) {
				switch(opeImg) {
				case JrImgOperatorName.IMG_OPE_PLUSSOMBRE :
					return (((JrMapGif)map).getLimunosite() == 0)? false : true;
				case JrImgOperatorName.IMG_OPE_ADJUST :
					return (((JrMapGif)map).isAdjustMode() == true)? false : true;
				case JrImgOperatorName.IMG_OPE_DEPEND :
					return ((JrMapGif)map).isAdjustMode();
				default : break;			
				}
				return true;
			}
		}
		return false;
	}	
	
	public static JrActionEditImg CreateAction(int ope) {
		switch(ope) {
		case JrImgOperatorName.IMG_OPE_SELECT :
			return new JrActionEditImg("MenuImageSelect","MenuImageSelectHlp",
					"images/openimg.gif",ope);
		case JrImgOperatorName.IMG_OPE_PLUSSOMBRE :
			return new JrActionEditImg("MenuImagePlusSombre",
					"MenuImagePlusSombreHlp","images/moincont.gif",ope);
		case JrImgOperatorName.IMG_OPE_PLUSCLAIR :
			return new JrActionEditImg("MenuImagePlusClair",
					"MenuImagePlusClairHlp","images/pluscont.gif",ope);
		case JrImgOperatorName.IMG_OPE_ADJUST :
			return new JrActionEditImg("MenuImageAdjust","MenuImageAdjustHlp",
					"images/adjust.gif",ope);
		case JrImgOperatorName.IMG_OPE_DEPEND :
			return new JrActionEditImg("MenuImageDepend","MenuImageDependHlp",
					"images/depend.gif",ope);
		default : break;			
		}
		return new JrActionEditImg("MenuImageEfface","MenuImageEffaceHlp",
				"images/delimg.gif",JrImgOperatorName.IMG_OPE_ERASE);
	}	
}
