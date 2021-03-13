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
public class JrObjectChemin extends JrObject {
	
	public JrObjectChemin(int name) {
		super(name);
	}
	
	public boolean isBigHeight() {
		switch(getName()) {
		case JrObjName.OBJ_VBARRIERE : return true;
		default : break;
		}
		return false;
	}
	public boolean isBigWidth() {
		switch(getName()) {
		case JrObjName.OBJ_HBARRIERE : return true;
		default : break;
		}
		return false;
	}	
	
	public boolean isDrawOver() {
		switch(getName()) {
		case JrObjName.OBJ_HBARRIERE :
		case JrObjName.OBJ_VBARRIERE : return true;
		default : break;
		}
		return false;
	}

	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		x -= 2;
		y -= 2;
		w += 4;
		h += 4;
		switch(getName()) {
		case JrObjName.OBJ_VRAIL  : drawObjVRail(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_HRAIL  : drawObjHRail(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_VRIVER : drawObjVRiver(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_HRIVER : drawObjHRiver(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_VBARRIERE : drawObjVBarriere(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_HBARRIERE : drawObjHBarriere(dt,x,y,w,h,active,view); break;
		default: break;
		}
	}

	public void drawObjVRail(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		float dh = h / 5;
		float dw = (w*2) / 5;
		
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(x+dw,y,x+dw,y+h);
		dt.drawLine(x+w-dw,y,x+w-dw,y+h);
		dw = w / 4;
		for(int i = 0; i < 5; i++) {
		    dt.drawLine(x+dw,y+(i*dh),x+w-dw,y+(i*dh));
		}
	}

	public void drawObjHRail(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dw = w / 5;
		float dh = (h*2) / 5;

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(x,y+dh,x+w,y+dh);
		dt.drawLine(x,y+h-dh,x+w,y+h-dh);
		dh = h / 4;
		for(int i = 0; i < 5; i++) {
			dt.drawLine(x+(i*dw),y+dh,x+(i*dw),y+h-dh);
		}
	}

	public void drawObjVRiver(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float d4 = Math.max(2,(w / 4));
		float d = Math.max(2,(w / 8));

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		for(int i = 0; i < 5; i++) {
		    dt.drawLine(x+d4+(i*d),y,x+d4+(i*d),y+h);
		}
	}

	public void drawObjHRiver(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float d4 = Math.max(2,(h / 4));
		float d = Math.max(2,(h / 8));

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		for(int i = 0; i < 5; i++) {
		    dt.drawLine(x,y+d4+(i*d),x+w,y+d4+(i*d));
		}
	}
	
	public void drawObjVBarriere(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dy = h / 10;
		float dx = w / 8;
		dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		dt.drawLine(x+dx,y+dy,x+w-dx-dx-dx,y+dy);
		dt.drawLine(x+dx,y+h-dy,x+w-dx-dx-dx,y+h-dy);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(x+w,y,x+(w/2),y+h);
	}

	public void drawObjHBarriere(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dy = h / 8;
		float dx = w / 10;
		dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		dt.drawLine(x+dx,y+dy+dy+dy,x+dx,y+h-dy);
		dt.drawLine(x+w-dx,y+dy+dy+dy,x+w-dx,y+h-dy);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(x,y+dy,x+w,y+(h/2));
	}
	
}
