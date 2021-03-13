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
public class JrFilterOrderEditor extends JComboBox implements JrFilterComponent,ActionListener {
	private static String [] cbnOrder = null;
	private JrFilterGadget filter = null;
	
	public JrFilterOrderEditor() {
		initializeStaticValues();
		for(int i = 0; i < cbnOrder.length; i++)
			addItem(cbnOrder[i]);
		setSelectedIndex(0);
		addActionListener(this);
	}
	
	public JrFilterOrderEditor(JrFilterGadget gad) {
		filter = gad;
		initializeStaticValues();
		for(int i = 0; i < cbnOrder.length; i++)
			addItem(cbnOrder[i]);
		setSelectedIndex(filter.getOrderType());
		addActionListener(this);
	}
	
	public static void initializeStaticValues() {
		if (cbnOrder == null) {
			cbnOrder = new String [JrFilter.ORDER_COUNT];
			for(int i = 0; i < JrFilter.ORDER_COUNT; i++)
				cbnOrder[i] = JrApplicationOption.GetWord(JrFilterGadget.GetOrderTypeText(i));
		}		
	}

	/* (non-Javadoc)
	 * @see application.dialogs.gadgets.JrFilterComponent#getFilterValue()
	 */
	public Integer getFilterValue() {
		return new Integer(getSelectedIndex());
	}

	/* (non-Javadoc)
	 * @see application.dialogs.gadgets.JrFilterComponent#connectFilter(application.dialogs.gadgets.JrFilterGadget)
	 */
	public void connectFilter(JrFilterGadget fil) {
		filter = fil;
		setSelectedIndex(filter.getOrderType());
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj instanceof JrFilterOrderEditor) {
			Integer val = ((JrFilterOrderEditor)obj).getFilterValue();
			filter.setOrderType(val.intValue());
		}
	}
}
