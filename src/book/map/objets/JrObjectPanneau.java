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
public class JrObjectPanneau extends JrObject {
	
	public JrObjectPanneau(int name) {
		super(name);
	}
	
	public boolean isBigHeight() {
		return true;
	}
	
	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		switch(getName()) {
		case JrObjName.OBJ_STOP      : drawObjStop(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_CEDER     : drawObjCeder(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_SENSINTER : drawObjSensInterdit(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_FEUX      : drawObjFeux(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_ARBRE     : drawObjArbre(dt,x,y,w,h,active,view); break;
		case JrObjName.OBJ_SAPIN     : drawObjSapin(dt,x,y,w,h,active,view); break;
		default: break;
		}
	}

	public void drawObjTronc(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		float px = x + (w / 2);
		dt.selectDefinedPen(JrPenName.PEN_PATH_BLACK,active);
		dt.drawLine(px,y,px,y+h);
	}
	
	public void drawObjStop(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		float dh = h / 12;
		float dw = w / 6;
		float ox = x + (w / 2);
		float oy = y + (h / 4);
		float ptx[] = new float [9];
		float pty[] = new float [9];
		drawObjTronc(dt,x,oy,w,(h*3)/4,active,view);
		ptx[0] = ox-(3*dw); pty[0] = oy-dh;
		ptx[1] = ox-(3*dw); pty[1] = oy+dh;
		ptx[2] = ox-dw;     pty[2] = oy+(3*dh);
		ptx[3] = ox+dw;     pty[3] = oy+(3*dh);
		ptx[4] = ox+(3*dw); pty[4] = oy+dh;
		ptx[5] = ox+(3*dw); pty[5] = oy-dh;
		ptx[6] = ox+dw;     pty[6] = oy-(3*dh);
		ptx[7] = ox-dw;     pty[7] = oy-(3*dh);
		ptx[8] = ox-(3*dw); pty[8] = oy-dh;
		dt.selectDefinedPen(JrPenName.PEN_MAP_LIGHTGRAY,active);
		dt.drawPolygon(ptx,pty,9,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawPolygon(ptx,pty,9,false);
	}

	public void drawObjCeder(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float ptx[] = new float [4];
		float pty[] = new float [4];
		float ox = x + (w / 2);
		float oy = y + (h / 4);
		drawObjTronc(dt,x,oy,w,(h*3)/4,active,view);
		ptx[0] = x;   pty[0] = y;
		ptx[1] = x+w; pty[1] = y;
		ptx[2] = ox;  pty[2] = y+(h/2);
		ptx[3] = x;   pty[3] = y;
		dt.selectDefinedPen(JrPenName.PEN_MAP_LIGHTGRAY,active);
		dt.drawPolygon(ptx,pty,4,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawPolygon(ptx,pty,4,false);
	}

	public void drawObjSensInterdit(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		float dh = h / 12;
		float dw = w / 6;
		float d  = Math.min(dh,dw);
		float ox = x + (w / 2);
		float oy = y + (h / 4);
		drawObjTronc(dt,x,oy,w,(h*3)/4,active,view);
		dt.selectDefinedPen(JrPenName.PEN_MAP_RED,active);
		dt.drawCircle(ox-(d*3),oy-(d*3),(d*6),(d*6),true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawCircle(ox-(d*3),oy-(d*3),(d*6),(d*6),false);
		dt.selectDefinedPen(JrPenName.PEN_MAP_WHITE,active);
		dt.drawRectangle(ox-(d*2),oy-d,(d*4),(d*2),true);
	}
	
	public void drawObjFeux(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float delta = Math.min(w,(h/6.0f));
		float diam  = delta * 0.95f;
		float delta2 = delta / 2.0f;
		
		float d = w / 4.0f;
		float px = x;
		float py = y;
		float oy = y + (h / 4.0f);
		drawObjTronc(dt,x,oy,w,h*0.75f,active,view);
		dt.selectDefinedPen(JrPenName.PEN_MAP_LIGHTGRAY,active);
		dt.drawRectangle(x+d,y,w-d-d,h/2.0f,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawRectangle(x+d,y,w-d-d,h/2.0f,false);
		
		px = x + (w/2.0f) - delta2;
		py = y;
		dt.selectDefinedPen(JrPenName.PEN_MAP_RED,active);
		dt.drawEllipse(px,py,diam,diam,true);
		py += delta;
		dt.selectDefinedPen(JrPenName.PEN_MAP_ORANGE,active);
		dt.drawEllipse(px,py,diam,diam,true);
		py += delta;
		dt.selectDefinedPen(JrPenName.PEN_MAP_GREEN,active);
		dt.drawEllipse(px,py,diam,diam,true);
	}

	public void drawObjArbre(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float pas = w / 4;
		float oy = y + (h / 4);
		drawObjTronc(dt,x,oy,w,(h*3)/4,active,view);
		dt.selectDefinedPen(JrPenName.PEN_MAP_GREEN,active);
		dt.drawEllipse(x+pas,y,w-pas-pas,h/2,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawEllipse(x+pas,y,w-pas-pas,h/2,false);
	}

	public void drawObjSapin(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {		
		float ptx[] = new float [4];
		float pty[] = new float [4];
		float ox = x + (w / 2);
		float oy = y + (h / 4);
		drawObjTronc(dt,x,oy,w,(h*3)/4,active,view);
		ptx[0] = x;   pty[0] = y+(h/2);
		ptx[1] = x+w; pty[1] = y+(h/2);
		ptx[2] = ox;  pty[2] = y;
		ptx[3] = x;   pty[3] = y+(h/2);
		dt.selectDefinedPen(JrPenName.PEN_MAP_GREEN,active);
		dt.drawPolygon(ptx,pty,4,true);
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawPolygon(ptx,pty,4,false);
	}
}
