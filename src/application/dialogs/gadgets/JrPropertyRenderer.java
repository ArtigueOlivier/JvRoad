/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.JrApplicationOption;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertyRenderer implements TableCellRenderer {
	private JrPropertiesTableModel model;
	private JrProperty prop;

	public JrPropertyRenderer(JrPropertiesTableModel m) {
		model = m;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int rowno, int colno) {
		prop = model.getProperty(rowno);
		switch(colno) {
		case 0 :
			JLabel lbl = new JLabel();
			if (prop.getType() == JrProperty.SECTION_TYPE) {
				lbl.setText(" " + GetWord(prop.getLabel()));
				lbl.setForeground(Color.BLUE);
			}
			else {
				lbl.setText(GetWord(prop.getLabel()) + ": ");
				lbl.setHorizontalAlignment(JLabel.RIGHT);
			}
			return lbl;
			
		case 2 :
			JLabel unit = new JLabel();
			unit.setText(GetWord(prop.getUnit()));
			return unit;
		default: break;
		}
		prop = model.getProperty(rowno);
		prop.setCurrentEditor(null);
		return prop.createComponent(true);
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
}
