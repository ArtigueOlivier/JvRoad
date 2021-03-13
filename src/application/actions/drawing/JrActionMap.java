/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrMapName;
import names.JrViewName;

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import book.JrBook;
import book.JrCase;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionMap extends JrActionMenu {
	public int mapName = JrMapName.MAP_CARREFOUR_NAME;

	public JrActionMap(String txt,int mnemo,int acckey,int accmask,String hlp,String img,
			int name) {
		super(txt,mnemo,acckey,accmask,hlp,img);
		mapName = name;
	}
	
	public JrActionMap(String txt,int mnemo,String hlp,String img,int name) {
		super(txt,mnemo,hlp,img);
		mapName = name;
	}	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrCase cas = book.getCurrentCase();
		if (cas.setMapName(book.getCurrentView(),mapName) == true) {
			book.setModified(true);
			book.fireCurrentViewChanged();
			refreshAllActions();
		}
	}

	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			switch(mapName) {
			case JrMapName.MAP_PONT_NAME :
			case JrMapName.MAP_VILLE_NAME :
			case JrMapName.MAP_MILLEPAS_NAME :
			case JrMapName.MAP_GIF_NAME :
				if (JrApplicationOption.GetCode() != 129834765)
					return false;
			}
			int view = book.getCurrentView();
			if (view >= JrViewName.VIEW_OBJ)
				return false;
			JrCase cas = book.getCurrentCase();			
			return (cas.getMapName(view) != mapName)? true : false;
		}
		return false;
	}
	public static JrActionMap CreateAction(int obj) {
		switch(obj) {
		case JrMapName.MAP_ARCS_NAME :
			return new JrActionMap("MapArc",KeyEvent.VK_A,"MapArcHlp",
					"images/maparc.gif",obj);
		case JrMapName.MAP_BORNE_NAME :
			return new JrActionMap("MapBorne",KeyEvent.VK_B,"MapBorneHlp",
					"images/mapborne.gif",obj);
		case JrMapName.MAP_CAP_NAME :
			return new JrActionMap("MapCap",KeyEvent.VK_P,"MapCapHlp",
					"images/mapcap.gif",obj);
		case JrMapName.MAP_CLIPART_NAME :
			return new JrActionMap("MapClipart",KeyEvent.VK_L,"MapClipartHlp",
					"images/mapclip.gif",obj);
		case JrMapName.MAP_CLOCK_NAME :
			return new JrActionMap("MapHeure",KeyEvent.VK_H,"MapHeureHlp",
					"images/mapclock.gif",obj);
		case JrMapName.MAP_GIF_NAME :
			return new JrActionMap("MapImage",KeyEvent.VK_I,"MapImageHlp",
					"images/mapgif.gif",obj);
		case JrMapName.MAP_GRID_NAME :
			return new JrActionMap("MapGrille",KeyEvent.VK_G,"MapGrilleHlp",
					"images/mapgrid.gif",obj);
		case JrMapName.MAP_MILLEPAS_NAME :
			return new JrActionMap("MapMillePas",KeyEvent.VK_M,"MapMillePasHlp",
					"images/mapmil.gif",obj);
		case JrMapName.MAP_RONDPOINT_NAME :
			return new JrActionMap("MapRondPoint",KeyEvent.VK_R,"MapRondPointHlp",
					"images/maprondp.gif",obj);
		case JrMapName.MAP_TEXT_NAME :
			return new JrActionMap("MapTexte",KeyEvent.VK_T,"MapTexteHlp",
					"images/maptext.gif",obj);
		case JrMapName.MAP_VECTOR_NAME :
			return new JrActionMap("MapVector",KeyEvent.VK_V,"MapVectorHlp",
					"images/mapvect.gif",obj);		
		case JrMapName.MAP_LINETEXT_NAME :
			return new JrActionMap("MapLineTexte",KeyEvent.VK_X,"MapLineTexteHlp",
					"images/maplinetext.gif",obj);
		case JrMapName.MAP_PONT_NAME :
			return new JrActionMap("MapPont",KeyEvent.VK_O,"MapPontHlp",
					"images/mappont.gif",obj);
		case JrMapName.MAP_VILLE_NAME :
			return new JrActionMap("MapVilles",KeyEvent.VK_S,"MapVillesHlp",
					"images/mapville.gif",obj);
		default: break;
		}
		return new JrActionMap("MapCarrefour",KeyEvent.VK_C,"MapCarrefourHlp",
				"images/mapcarref.gif",JrMapName.MAP_CARREFOUR_NAME);
	}
}
