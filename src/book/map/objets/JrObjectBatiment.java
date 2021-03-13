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
public class JrObjectBatiment extends JrObject {
	
	public JrObjectBatiment(int name) {
		super(name);
	}
	
	public boolean isBigHeight() {
		switch(getName()) {
		case JrObjName.OBJ_HOUSE   :
		case JrObjName.OBJ_PARKING :
		case JrObjName.OBJ_HOPITAL : 
		case JrObjName.OBJ_CP      : return false;
		default: break;
		}
		return true;
	}
	
	public boolean isBigWidth() {
		switch(getName()) {
		case JrObjName.OBJ_HOUSEG :
		case JrObjName.OBJ_CP     : return true;
		default : break;
		}
		return false;
	}	
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		  switch(getName()) {
		  case JrObjName.OBJ_HOUSE    : 
		  case JrObjName.OBJ_HOUSEG   : drawObjHouse(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_CROIX    : drawObjCroix(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_CHAPELLE : drawObjChapelle(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_EGLISE   : drawObjEglise(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_MOSQUEE  : drawObjMosquee(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_HOPITAL  : drawObjHopital(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_STATION  : drawObjStation(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_PARKING  : drawObjParking(dt,x,y,w,h,active,view); break;
		  case JrObjName.OBJ_CP       : drawObjCP(dt,x,y,w,h,active,view); break;
		  default: break;
		  }
	}
	
	public void drawObjHouse(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawRectangle(x,y,w,h,false);
		dt.drawLine(x,y,x+w,y+h);
		dt.drawLine(x+w,y,x,y+h);
	}

	public void drawObjCroix(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float px = w / 5.0f;
		float py = h / 5.0f;
		float pas = Math.min(px,py);
		float ox = x + (w/2.0f);

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawRectangle(x,y+h-py,w,py,false);
		dt.drawRectangle(x+px,y+h-py-py,w-px-px,py,false);
		dt.drawLine(ox,y,ox,y+h-py-py);
		dt.drawLine(ox-pas,y+pas,ox+pas,y+pas);
	}

	public void drawObjEglise(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dh = h / 10.0f;
		float dw = w / 10.0f;
		float dx2 = x + (w/2.0f);
		float dy2 = y + (h/2.0f);

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(dx2-(5*dw),dy2+(5*dh),dx2+(5*dw),dy2+(5*dh));
		dt.drawLine(dx2+(5*dw),dy2+(5*dh),dx2+(5*dw),dy2-(2*dh));
		dt.drawLine(dx2+(5*dw),dy2-(2*dh),dx2+(2*dw),dy2-(2*dh));
		dt.drawLine(dx2+(2*dw),dy2-(2*dh),dx2+(2*dw),dy2+(2*dh));
		dt.drawLine(dx2+(2*dw),dy2+(2*dh),dx2-(5*dw),dy2+(2*dh));
		dt.drawLine(dx2-(5*dw),dy2+(2*dh),dx2-(5*dw),dy2+(5*dh));
		dt.drawLine(dx2+(2*dw),dy2-(2*dh),dx2+(3*dw)+(dw/2),dy2-(5*dh));
		dt.drawLine(dx2+(3*dw)+(dw/2),dy2-(5*dh),dx2+(5*dw),dy2-(2*dh));
		dt.drawLine(dx2-(5*dw),dy2+(2*dh),dx2-(3*dw),dy2+dh);
		dt.drawLine(dx2-(3*dw),dy2+dh,dx2+(2*dw),dy2+dh);
		dt.drawLine(dx2+(3*dw)+(dw/2),dy2-dh,dx2+(3*dw)+(dw/2),dy2+dh);
		dt.drawLine(dx2+(2*dw)+(dw/2),dy2-(dh/2),dx2+(4*dw)+(dw/2),dy2-(dh/2));
	}

	public void drawObjChapelle(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dh = h / 10.0f;
		float dw = w / 10.0f;
		float dx2 = x + (w/2.0f);
		float dy2 = y + (h/2.0f);

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(dx2-(5*dw),dy2+(5*dh),dx2+(5*dw),dy2+(5*dh));		
		dt.drawLine(dx2+(5*dw),dy2+(5*dh),dx2+(5*dw),dy2+(2*dh));
		dt.drawLine(dx2+(5*dw),dy2+(2*dh),dx2+(3*dw)+(dw/2),dy2-dh);
		dt.drawLine(dx2+(3*dw)+(dw/2),dy2-dh,dx2+(2*dw),dy2+(2*dh));
		dt.drawLine(dx2+(2*dw),dy2+(2*dh),dx2-(5*dw),dy2+(2*dh));
		dt.drawLine(dx2-(5*dw),dy2+(2*dh),dx2-(5*dw),dy2+(5*dh));
		dt.drawLine(dx2+(2*dw),dy2+(2*dh),dx2+(2*dw),dy2+(5*dh));		
		dt.drawLine(dx2-(5*dw),dy2+(2*dh),dx2-(3*dw),dy2-dh);
		dt.drawLine(dx2-(3*dw),dy2-dh,dx2+(3*dw)+(dw/2),dy2-dh);		
		dt.drawLine(dx2+(3*dw)+(dw/2),dy2+(2*dh),dx2+(3*dw)+(dw/2),dy2+(4*dh));
		dt.drawLine(dx2+(2*dw)+(dw/2),dy2+(2*dh)+(dh/2),dx2+(4*dw)+(dw/2),dy2+(2*dh)+(dh/2));
	}

	
	public void drawObjMosquee(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dh = h / 10;
		float dw = w / 10;
		float dx2 = x + (w/2);
		float dy2 = y + (h/2);

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(dx2-(5*dw),dy2+(5*dh),dx2+(5*dw),dy2+(5*dh));
		dt.drawLine(dx2+(5*dw),dy2+(5*dh),dx2+(5*dw),dy2-(2*dh));
		dt.drawLine(dx2+(5*dw),dy2-(2*dh),dx2+(4*dw),dy2-(5*dh));
		dt.drawLine(dx2+(4*dw),dy2-(5*dh),dx2+(3*dw),dy2-(2*dh));
		dt.drawLine(dx2+(3*dw),dy2-(2*dh),dx2+(3*dw),dy2+dh);
		dt.drawLine(dx2+(3*dw),dy2+dh,dx2-(5*dw),dy2+dh);
		dt.drawLine(dx2-(5*dw),dy2+dh,dx2-(5*dw),dy2+(5*dh));
		dt.drawArc(dx2-(4*dw),dy2-dh,dw*6,dh*4,0,180);
	}

	public void drawObjHopital(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dx2 = x + (w/2);
		float dy2 = y + (h/2);
		float pasx2 = w / 4;
		float pasy2 = h / 4;

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);		
		dt.drawRectangle(x,y,w,h,false);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2,dx2 - pasx2, dy2 + pasy2);
		dt.drawLine(dx2 + pasx2, dy2 - pasy2,dx2 + pasx2, dy2 + pasy2);
		dt.drawLine(dx2 - pasx2, dy2,dx2 + pasx2, dy2);
	}

	public void drawObjCP(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dx2 = x + (w/4);
		float dy2 = y + (h/2);
		float pasx2 = w / 8;
		float pasy2 = h / 4;

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);		
		dt.drawRectangle(x,y,w,h,false);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2,dx2 - pasx2, dy2 + pasy2);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2, dx2 + pasx2, dy2 - pasy2);
		dt.drawLine(dx2 - pasx2, dy2 + pasy2, dx2 + pasx2, dy2 + pasy2);
		dx2 += w/2;
		dt.drawLine(dx2 - pasx2, dy2 - pasy2,dx2 - pasx2, dy2 + pasy2);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2, dx2 + pasx2, dy2 - pasy2);
		dt.drawLine(dx2 - pasx2, dy2, dx2 + pasx2, dy2);
		dt.drawLine(dx2 + pasx2, dy2 - pasy2,dx2 + pasx2, dy2);
	}

	public void drawObjStation(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dh = h / 8.0f;
		float dw = w / 8.0f;
		float dh2 = dh / 2;
		float dw2 = dw / 2;
		float dx2 = x + (w / 2);
		float dy2 = y + (h / 2);

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(dx2-(3*dw),dy2+(4*dh),dx2+(3*dw),dy2+(4*dh));
		dt.drawLine(dx2+(3*dw),dy2+(4*dh),dx2+(3*dw),dy2+(3*dh));
		dt.drawLine(dx2+(3*dw),dy2+(3*dh),dx2+(2*dw),dy2+(3*dh));
		dt.drawLine(dx2+(2*dw),dy2+(3*dh),dx2+(2*dw),dy2-(4*dh));
		dt.drawLine(dx2+(2*dw),dy2-(4*dh),dx2-(2*dw),dy2-(4*dh));
		dt.drawLine(dx2-(2*dw),dy2-(4*dh),dx2-(2*dw),dy2+(3*dh));
		dt.drawLine(dx2-(2*dw),dy2+(3*dh),dx2-(3*dw),dy2+(3*dh));
		dt.drawLine(dx2-(3*dw),dy2+(3*dh),dx2-(3*dw),dy2+(4*dh));

		dt.drawLine(dx2-dw-dw2,dy2-dh-dh2,dx2+dw+dw2,dy2-dh-dh2);
		dt.drawLine(dx2+dw+dw2,dy2-dh-dh2,dx2+dw+dw2,dy2-(3*dh)-dh2);
		dt.drawLine(dx2+dw+dw2,dy2-(3*dh)-dh2,dx2-dw-dw2,dy2-(3*dh)-dh2);
		dt.drawLine(dx2-dw-dw2,dy2-(3*dh)-dh2,dx2-dw-dw2,dy2-dh-dh2);
		dt.drawLine(dx2+(2*dw),dy2-dh,dx2+(2*dw)+dw2,dy2-dh);
		dt.drawLine(dx2+(2*dw)+dw2,dy2-dh,dx2+(2*dw)+dw2,dy2+(2*dh));
		dt.drawLine(dx2+(2*dw)+dw2,dy2+(2*dh),dx2+(3*dw),dy2+(2*dh));
		dt.drawLine(dx2+(3*dw),dy2+(2*dh),dx2+(3*dw),dy2-(2*dh));
		dt.drawLine(dx2+(3*dw),dy2-(2*dh),dx2+(2*dw),dy2-(3*dh));

		dt.drawLine(dx2+(2*dw)+dw2,dy2-(2*dh)-dh2,dx2+(2*dw)+dw2,dy2-(2*dh));
		dt.drawLine(dx2+(2*dw)+dw2,dy2-(2*dh),dx2+(3*dw),dy2-dh);
	}
	
	public void drawObjParking(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float dx2 = x + (w/2);
		float dy2 = y + (h/2);
		float pasx2 = w / 4;
		float pasy2 = h / 4;

		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawRectangle(x,y,w,h,false);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2,dx2 - pasx2, dy2 + pasy2);
		dt.drawLine(dx2 + pasx2, dy2 - pasy2,dx2 + pasx2, dy2);
		dt.drawLine(dx2 - pasx2, dy2,dx2 + pasx2, dy2);
		dt.drawLine(dx2 - pasx2, dy2 - pasy2,dx2 + pasx2, dy2 - pasy2);
	}
}
