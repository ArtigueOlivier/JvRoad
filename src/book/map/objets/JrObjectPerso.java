/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map.objets;

import java.io.IOException;

import names.JrObjName;
import names.JrPenName;
import tools.JrDrawTools;
import tools.files.JrFileRead;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * Image: Gif, 20 x 20
 * 
 */
public class JrObjectPerso extends JrObject {
	private String textHelp = "";
	private String imageFilename = "";
	private boolean symetrique = false;
	private JrObjectDrawItem[] actions = null;
	
	public JrObjectPerso(int name) {
		super(name);
		imageFilename = "objects/img" + name + ".gif";
		try {
			parse();
		} catch (IOException e) {
		}
	}
	
	public String getTextHelp() {
		return textHelp;
	}
	
	public String getImageFilename() {
		return imageFilename;
	}
	
	public boolean isDrawOver() {
		return true;
	}

	public void draw(JrDrawTools dt,float x,float y,float w,float h,boolean active,int view) {
		if (symetrique) {
			float vm = Math.min(w, h);
			if (w != vm) {
				x += (w / 2.0f) - (vm / 2.0f);
				w = vm;				
			}
			if (h != vm) {
				y += (h / 2.0f) - (vm / 2.0f);
				h = vm;				
			}
		}
		if (actions != null) {
			for(int i = 0; i < actions.length; i++) {
				if (actions[i] != null)
					actions[i].draw(dt, x, y, w, h, active, view);
			}
		}
	}
	
	public boolean parse() throws IOException {
		JrFileRead file = new JrFileRead("objects/obj" + getName() + ".job");
		if (file.read()) {
			file.setCurrentSection("Definition");
			textHelp = file.getStringValue("Title", "undefined");
			symetrique = (file.getIntegerValue("Symetry", 0) == 0)? false : true;
			file.setCurrentSection("Draw");
			int count = file.getIntegerValue("Count", 0);
			if (count > 0) {
				actions = new JrObjectDrawItem[count];
				for(int i = 0; i < count; i++) {
					actions[i] = JrObjectDrawItem.Create(file.getStringValue("Action" + i, "NONE;"));
				}
			}
			
			return true;
		}
		System.out.println("Can't read file: " + file.getFilename());
		return false;
	}
}
