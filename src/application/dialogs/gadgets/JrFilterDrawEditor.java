/*
 * Created on Jul 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import application.JrApplicationOption;
import book.filter.JrFilter;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterDrawEditor extends JComboBox implements JrFilterComponent, ActionListener {
	private static String [] cbnDraw = null;
	private JrFilterGadget filter = null;
	
	public JrFilterDrawEditor() {
		initializeStaticValues();
		for(int i = 0; i < cbnDraw.length; i++)
			addItem(cbnDraw[i]);
		setSelectedIndex(0);
		addActionListener(this);
	}
	
	public JrFilterDrawEditor(JrFilterGadget gad) {
		filter = gad;
		initializeStaticValues();
		for(int i = 0; i < cbnDraw.length; i++)
			addItem(cbnDraw[i]);
		setSelectedIndex(filter.getDrawMode());
		addActionListener(this);
	}
	
	public static void initializeStaticValues() {
		if (cbnDraw == null) {
			cbnDraw = new String [JrFilter.DRAW_COUNT];
			for(int i = 0; i < JrFilter.DRAW_COUNT; i++)
				cbnDraw[i] = JrApplicationOption.GetWord(JrFilterGadget.GetDrawModeText(i));
		}
		
	}

	public Integer getFilterValue() {
		return new Integer(getSelectedIndex());
	}

	public void connectFilter(JrFilterGadget fil) {
		filter = fil;
		setSelectedIndex(filter.getDrawMode());		
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj instanceof JrFilterDrawEditor) {
			Integer val = ((JrFilterDrawEditor)obj).getFilterValue();
			filter.setDrawMode(val.intValue());
		}
	}
}
