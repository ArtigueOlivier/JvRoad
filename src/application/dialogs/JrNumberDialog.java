/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrNumberDialog extends JrDialog  {
	private JSpinner edtValue = null;
	
	private int vmin = 0;
	private int vmax = 10;
	private int value = 0;
	private String texte = "";
	
	public JrNumberDialog(JFrame frame, int vmin,int vmax,int val,String title,String txt) {
		super(frame,GetWord(title));
		this.vmin = vmin;
		this.vmax = vmax;
		value = Math.min(vmax,Math.max(vmin,val));		
		texte = GetWord(txt) + ":";
	}
	
	public JPanel buildPanel() {
		SpinnerModel model = new SpinnerNumberModel(value, vmin, vmax, 1);
		edtValue = new JSpinner(model);
		
        JPanel listPane = new JPanel();
        listPane.setLayout(new GridLayout(4,1));
        JLabel label = new JLabel(texte);
        listPane.add(label);
        listPane.add(edtValue);
        
        edtValue.setValue(new Integer(value));
        return listPane;
	}
	
	public boolean validateData() {
		SpinnerNumberModel model = (SpinnerNumberModel)edtValue.getModel();
		value = Math.min(vmax,Math.max(vmin,model.getNumber().intValue()));
		return true;
	}
	
	public int getValue() {
		return value;
	}
}
