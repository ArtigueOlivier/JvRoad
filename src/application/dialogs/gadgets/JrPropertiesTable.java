/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertiesTable extends JTable {
	public JrPropertiesTable(JrPropertiesTableModel model) {
		super(model);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultRenderer(JrProperty.class,new JrPropertyRenderer(model));
		setDefaultRenderer(String.class, new JrPropertyRenderer(model));
		setDefaultEditor(JrProperty.class,new JrPropertyEditor(model));
		setDefaultEditor(JrPropertyColor.class,new JrPropertyEditor(model));
		setGridColor(Color.WHITE);
	}
}
