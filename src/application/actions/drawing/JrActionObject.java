/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import names.JrObjName;
import tools.JrContext;
import book.JrBook;



import book.map.objets.JrObjects;
import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionObject extends JrActionMenu {
	private int codeObj = JrObjName.OBJ_NOTHING;

	public JrActionObject(String hlp, String img,int obj) {
		super("", 0, hlp, img);
		codeObj = obj;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrContext context = book.getContext();
		if (context.getCurrentObject() != codeObj) {
			context.setCurrentObject(codeObj);
			JrActionCenter.RefreshActionsObject();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			JrContext context = book.getContext();
			return (context.getCurrentObject() == codeObj)? false : true;
		}
		return false;
	}

	public static JrActionObject CreateAction(int obj) {
		switch(obj) {
		case JrObjName.OBJ_HOUSE :
			return new JrActionObject("MenuObjetHouseHlp","images/objhouse.gif",obj);
		case JrObjName.OBJ_CROIX :
			return new JrActionObject("MenuObjetCroixHlp","images/objcroix.gif",obj);
		case JrObjName.OBJ_CHAPELLE :
			return new JrActionObject("MenuObjetChapelleHlp","images/objchap.gif",obj);
		case JrObjName.OBJ_EGLISE :
			return new JrActionObject("MenuObjetEgliseHlp","images/objeglis.gif",obj);
		case JrObjName.OBJ_MOSQUEE :
			return new JrActionObject("MenuObjetMosqueeHlp","images/objmosq.gif",obj);
		case JrObjName.OBJ_HOPITAL :
			return new JrActionObject("MenuObjetHopitalHlp","images/objhopit.gif",obj);
		case JrObjName.OBJ_STATION :
			return new JrActionObject("MenuObjetStationHlp","images/objstat.gif",obj);
		case JrObjName.OBJ_PARKING :
			return new JrActionObject("MenuObjetParkingHlp","images/objpark.gif",obj);
		case JrObjName.OBJ_STOP :
			return new JrActionObject("MenuObjetStopHlp","images/objstop.gif",obj);
		case JrObjName.OBJ_CEDER :
			return new JrActionObject("MenuObjetCederHlp","images/objceder.gif",obj);
		case JrObjName.OBJ_SENSINTER :
			return new JrActionObject("MenuObjetSensInterHlp","images/objinter.gif",obj);
		case JrObjName.OBJ_FEUX :
			return new JrActionObject("MenuObjetFeuxHlp","images/objfeux.gif",obj);
		case JrObjName.OBJ_ARBRE :
			return new JrActionObject("MenuObjetArbreHlp","images/objarbre.gif",obj);
		case JrObjName.OBJ_SAPIN :
			return new JrActionObject("MenuObjetSapinHlp","images/objsapin.gif",obj);
		case JrObjName.OBJ_VRAIL :
			return new JrActionObject("MenuObjetVRailHlp","images/objvrail.gif",obj);
		case JrObjName.OBJ_HRAIL :
			return new JrActionObject("MenuObjetHRailHlp","images/objhrail.gif",obj);
		case JrObjName.OBJ_VRIVER :
			return new JrActionObject("MenuObjetVRiverHlp","images/objvrive.gif",obj);
		case JrObjName.OBJ_HRIVER :
			return new JrActionObject("MenuObjetHRiverHlp","images/objhrive.gif",obj);
		case JrObjName.OBJ_CERCLE :
			return new JrActionObject("MenuObjetCercleHlp","images/objcercl.gif",obj);
		case JrObjName.OBJ_ROND :
			return new JrActionObject("MenuObjetRondHlp","images/objrond.gif",obj);
		case JrObjName.OBJ_HOUSEG :
			return new JrActionObject("MenuObjetHousegHlp","images/objhousg.gif",obj);
		case JrObjName.OBJ_HBARRIERE :
			return new JrActionObject("MenuObjetHBarriereHlp","images/objhbarr.gif",obj);
		case JrObjName.OBJ_VBARRIERE :
			return new JrActionObject("MenuObjetVBarriereHlp","images/objvbarr.gif",obj);
		case JrObjName.OBJ_CP :
			return new JrActionObject("MenuObjetCPHlp","images/objcp.gif",obj);
		default: 
			if (obj >= JrObjName.OBJ_COUNT) {
				obj = obj + 5000 - JrObjName.OBJ_COUNT;
			}
			if (obj >= 5000) {
				return new JrActionObject(JrObjects.GetTitle(obj),JrObjects.GetImageFilename(obj),obj);				
			}
			break;
		}
		return new JrActionObject("MenuObjetEffacerHlp", "images/efface.gif",
				JrObjName.OBJ_NOTHING);
	}
}
