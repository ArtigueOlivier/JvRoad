/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import application.dialogs.util.JrTableSearchModel;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSearchDialog extends JrDialog  {
	private JrBook book = null;	
	private int indexCase = 1;
	private JTable table = null;
	
	public JrSearchDialog(JFrame frame,JrBook bk) {
		super(frame,"TxtRechercher",false,"TxtAller","TxtFermer");
		book = bk;
	}
	
	public JPanel buildPanel() {
		JrTableSearchModel model = new JrTableSearchModel(book);
		table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setWidth(40);
		column.setMaxWidth(40);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(1);
		column.setWidth(40);
		column.setMaxWidth(40);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(2);
		column.setWidth(80);
		column.setMaxWidth(80);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(3);
		column.setWidth(60);
		column.setMaxWidth(60);
		column.setResizable(false);
		
		JScrollPane scpanel = new JScrollPane(table); 			
		model.fireTableDataChanged();
		JPanel pane = new JPanel();
		pane.add(scpanel);
        
        return pane;
	}
	
	public boolean validateData() {
		indexCase = table.getSelectedRow();
		return (indexCase == -1)? false : true;
	}
	
	public int getIndex() {
		return indexCase;
	}
}
