/*
 * Created on 20 mai 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * @author olivier
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrButton extends JButton {
	public JrButton() {
		super();
	}
	
	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		Dimension dim = this.getSize();
		g.fillRect(0,0,dim.width,dim.height);
		g.setColor(Color.BLACK);
		g.drawRect(0,0,dim.width-1,dim.height-1);
	}
}
