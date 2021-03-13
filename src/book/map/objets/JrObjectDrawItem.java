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
 * Basic drawing instruction for objects
 */
public class JrObjectDrawItem {
	private int drawCode;
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;
	private int start;
	private int amplitude;
	
	/**
	 * Parse a string define to create an object
	 * @param params
	 * @return
	 */
	public static JrObjectDrawItem Create(String params) {
		String values[] = params.split(";");
		if (values.length < 5) {
			return null;
		}
		int x1 = Integer.parseInt(values[1]);
		int x2 = Integer.parseInt(values[2]);
		int x3 = Integer.parseInt(values[3]);
		int x4 = Integer.parseInt(values[4]);
		if (values.length >= 7) {
			int x5 = Integer.parseInt(values[5]);
			int x6 = Integer.parseInt(values[6]);
			return new JrObjectDrawItem(GetTypeFromString(values[0].toUpperCase()),x1,x2,x3,x4,x5,x6);			
		}
		return new JrObjectDrawItem(GetTypeFromString(values[0].toUpperCase()),x1,x2,x3,x4);
	}
	
	public static int GetTypeFromString(String str) {
		if (str.contentEquals("LINE") || str.contentEquals("LIGNE")) return 1;
		if (str.contentEquals("RECT")) return 2;
		if (str.contentEquals("PAVE")) return 3;
		if (str.contentEquals("CIRCLE") || str.contentEquals("CERCLE")) return 4;
		if (str.contentEquals("ELLIPSE") || str.contentEquals("ELIPSE")) return 5;
		if (str.contentEquals("ARC")) return 6;		
		return 0;
	}
	
	public JrObjectDrawItem(int code, int xi, int yi, int xf, int yf) {
		drawCode = code;
		fromX = Math.max(0,xi);
		fromY = Math.max(0,yi);
		toX = Math.max(0,xf);
		toY = Math.max(0,yf);
	}

	public JrObjectDrawItem(int code, int xi, int yi, int xf, int yf, int star, int ampl) {
		drawCode = code;
		fromX = Math.max(0,xi);
		fromY = Math.max(0,yi);
		toX = Math.max(0,xf);
		toY = Math.max(0,yf);
		start = star;
		amplitude = ampl;
	}

	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int currentView) {
		
		
		
		float posxi = x + ((fromX * w) / 50.0f);
		float posyi = y + ((fromY * h) / 50.0f);
		float posxf = x + ((toX * w) / 50.0f);
		float posyf = y + ((toY * h) / 50.0f);
		float cw = posxf - posxi;
		float ch = posyf - posyi;
		switch(drawCode) {
		case  1 : drawLineTo(dt,posxi,posyi,posxf,posyf, active); break;
		case  2 : drawRectangle(dt,posxi,posyi,cw,ch, active, false); break;
		case  3 : drawRectangle(dt,posxi,posyi,cw,ch, active, true); break;
		case  4 : drawElipse(dt,posxi,posyi,cw,ch, active, false); break;
		case  5 : drawElipse(dt,posxi,posyi,cw,ch, active, true); break;
		case  6 : drawArc(dt,posxi,posyi,cw,ch, active); break;
		default : break;
		}
	}		
	
	private void drawLineTo(JrDrawTools dt,float xi, float yi, float xf, float yf, boolean active) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawLine(xi,yi,xf,yf);		
	}

	private void drawRectangle(JrDrawTools dt,float x, float y, float w, float h, boolean active, boolean fill) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawRectangle(x,y,w,h,false);
	}

	private void drawElipse(JrDrawTools dt,float x, float y, float w, float h, boolean active,boolean fill) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawCircle(x,y,w,h,fill);		
	}
	
	private void drawArc(JrDrawTools dt,float x, float y, float w, float h, boolean active) {
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,active);
		dt.drawArc(x,y,w,h,start,amplitude);		
	}
}
