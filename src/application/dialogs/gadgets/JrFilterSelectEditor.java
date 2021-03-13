/*
 * Created on Jul 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterSelectEditor extends JCheckBox implements JrFilterComponent, ItemListener {
	private JrFilterGadget filter = null;
	
	public JrFilterSelectEditor() {
		setSelected(false);
		addItemListener(this);
	}
	
	public JrFilterSelectEditor(JrFilterGadget gad) {
		filter = gad;
		setSelected(filter.isSelected());
		addItemListener(this);
	}
	
	public Integer getFilterValue() {
		return new Integer((isSelected())? 1 : 0);
	}

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();	
		if (source instanceof JrFilterSelectEditor) {
			boolean sel = ((JrFilterSelectEditor)source).isSelected();
			filter.setSelected(sel);
		}
	}

	public void connectFilter(JrFilterGadget fil) {
		filter = fil;
		setSelected(filter.isSelected());
	}
}
