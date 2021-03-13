/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import book.JrBiblio;
import book.JrBiblioListener;
import book.JrBook;

import application.JrApplicationOption;
import application.window.util.JrTableCheckModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCheckWindow extends JScrollPane implements MouseListener {
	private JTable table = null;
	private JrTableCheckModel model = null;
	private JrBiblio biblio = null;
	
	public JrCheckWindow(JrBiblio bib) {

		biblio = bib;
		
		model = new JrTableCheckModel(biblio.getCurrentBook());
		table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
		table.setShowGrid(false);
		
		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setWidth(40);
		column.setMaxWidth(40);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(1);
		column.setWidth(60);
		column.setMaxWidth(60);
		column.setResizable(false);
		
		setViewportView(table);
		getViewport().setBackground(Color.WHITE);	
		
		JrBiblioListener bl = new JrBiblioListener(){
			public void bookAdded(JrBook book) {
				model.setBook(book);
			}

			public void bookClosed() {
				model.setBook(biblio.getCurrentBook());
			}

			public void bookSaved() {
			}

			public void currentBookChanged(int index) {
				model.setBook(biblio.getCurrentBook());
			}

			public void bookTitleChanged(JrBook book) {
			}

			public void bookCopyrightChanged(JrBook book) {
			}

			public void bookPositionChanged(JrBook book) {
			}

			public void bookCaseAdded(JrBook book) {
			}

			public void bookCaseDeleted(JrBook book) {
			}

			public void bookCurrentViewChanged(JrBook book) {
			}

			public void bookCurrentMapChanged(JrBook book) {
		}};
		biblio.addListener(bl);
		
		model.fireTableDataChanged();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			int ce = JrApplicationOption.GetCode();
			if ((ce == 129834765) || (ce == 127895623))
				model.synchronize(table.getSelectedRow());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
