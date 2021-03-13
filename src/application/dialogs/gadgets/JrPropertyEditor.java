/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertyEditor extends AbstractCellEditor implements TableCellEditor {
	private JrPropertiesTableModel model;
	private JrProperty prop;
	
	public JrPropertyEditor(JrPropertiesTableModel m) {
		model = m;
	}
		
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean selected, int rowno, int colno) {
		prop = model.getProperty(rowno);
		prop.setCurrentEditor(prop.createComponent(false));	
		return prop.getCurrentEditor();
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		return prop.getValue();
	}
}
