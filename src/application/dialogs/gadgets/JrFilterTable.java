/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterTable extends JTable {
	private JrFilterRenderer editors = new JrFilterRenderer();
	private JrFilterRenderer renderer = new JrFilterRenderer();
	
	public JrFilterTable(JrFilterTableModel model) {
		super(model);
		setShowGrid(true);
		getTableHeader().setVisible(true);		
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultRenderer(Integer.class,renderer);
		setDefaultEditor(Integer.class,editors);
		//setGridColor(Color.WHITE);
	}
	
	public void stopCellEditing() {
		editors.stopCellEditing();
	}
}
