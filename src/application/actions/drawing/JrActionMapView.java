/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrViewName;

import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionMapView extends JrActionMenu {
	private int viewName = JrViewName.VIEW_ONE;
	
	public JrActionMapView() {
		super("Carte des objets",KeyEvent.VK_O,"Activer la carte des objets",
				"images/viewobj.gif");
	}
	public JrActionMapView(String txt,int mnemo,String hlp,String img,int name) {
		super(txt,mnemo,hlp,img);
		viewName = name;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
			getCurrentBook().setCurrentView(viewName);
			refreshAllActions();			
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			return (book.getCurrentView() != viewName)? true : false;
		}
		return false;
	}
	public static JrActionMapView CreateAction(int obj) {
		switch(obj) {
		case JrViewName.VIEW_TWO :
			return new JrActionMapView("MapDeux",KeyEvent.VK_D,
					"MapDeuxHlp","images/view2.gif",obj);
		case JrViewName.VIEW_THREE :
			return new JrActionMapView("MapTrois",KeyEvent.VK_T,
					"MapTroisHlp","images/view3.gif",obj);
		case JrViewName.VIEW_FOUR :
			return new JrActionMapView("MapQuatre",KeyEvent.VK_Q,
					"MapQuatreHlp","images/view4.gif",obj);
		case JrViewName.VIEW_OBJ :
			return new JrActionMapView("MapObjets",KeyEvent.VK_O,
					"MapObjetsHlp","images/viewobj.gif",obj);
		case JrViewName.VIEW_REAL :			
			return new JrActionMapView("MapApercu",KeyEvent.VK_A,
					"MapApercu","images/viewreal.gif",obj);
		default: break;
		}
		return new JrActionMapView("MapUn",KeyEvent.VK_P,"MapUnHlp",
				"images/view1.gif",JrViewName.VIEW_ONE);
	}	
}
