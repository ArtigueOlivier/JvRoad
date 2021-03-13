/*
 * Created on 11 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

import tools.JrMath;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrStatEntry {
	private String label = "";
	private String value = "";
	private String unit = "";
	
	public JrStatEntry(String n) {
		label = n;
	}
		
	public JrStatEntry(String n,int v,boolean distance) {
		label = n;
		if (distance) value = JrMath.formatDistance(v);
		else value = Integer.toString(v);
		unit = (distance)? "TxtKm" : "";
	}

	public boolean isSection() {
		return ((value.length() == 0) && (unit.length() == 0))? true : false;
	}
	
	public String getLabel() {
		return label;
	}

	public String getUnit() {
		return unit;
	}

	public String getValue() {
		return value;
	}
}
