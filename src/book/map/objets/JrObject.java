/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map.objets;

import names.JrObjName;
import tools.JrDrawTools;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrObject {
	private int name = JrObjName.OBJ_NOTHING;
	
	public JrObject() {	
	}

	public JrObject(int name) {
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
	
	public String getTextHelp() {
		return "";
	}
	
	public String getImageFilename() {
		return "";
	}
	
	public boolean isDrawOver() {
		return false;
	}

	/**
	 * Return true if object needs 2 cases for height
	 * @return
	 */
	public boolean isBigHeight() {
		return false;
	}

	/**
	 * Return true if object needs 2 cases for width
	 * @return
	 */
	public boolean isBigWidth() {
		return false;
	}	
	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int currentView) {
	}		
}
