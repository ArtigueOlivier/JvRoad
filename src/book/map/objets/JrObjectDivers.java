/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map.objets;

import names.JrObjName;
import names.JrPenName;
import tools.JrDrawTools;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrObjectDivers extends JrObject {
	
	public JrObjectDivers(int name) {
		super(name);
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		switch(getName()) {
		case JrObjName.OBJ_CERCLE : drawObjCercle(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_ROND   : drawObjRond(dt,x,y,w,h,active,view); break;
		default: break;
		}
	}

	public void drawObjCercle(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawCircle(x,y,w,h,false);
	}

	public void drawObjRond(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawCircle(x,y,w,h,true);
	}
}
