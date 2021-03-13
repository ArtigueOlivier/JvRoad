/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools.files;

import java.awt.Color;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFileEntry {
	private String value = "";
	
	JrFileEntry(String v) {
		value = v;
	}

	public String getStringValue() {
		return value;
	}

	public int getIntegerValue() {
		return Integer.parseInt(value);
	}

	public Color getColorValue() {
		long v = Long.parseLong(value);
		int r = (int)(v / 1000000L);
		int g = (int)((v / 1000L)%1000L);
		int b = (int)(v % 1000L);
		return new Color(r,g,b);
	}
		
	public void setValue(String value) {
		this.value = value;
	}
}
