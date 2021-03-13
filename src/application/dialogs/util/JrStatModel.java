/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrStatModel extends AbstractTableModel {
	private JrBook book = null;
	private ArrayList data = new ArrayList();
	private int shorterDistance = 0;
	private int longerDistance = 0;
	private int totalDistance = 0;
	private int averageDistance = 0;
	private int razCount = 0;
	private int approxCount = 0;
	private int inconnueCount = 0;
	
	public JrStatModel(JrBook bk) {
		book = bk;
		buildData();
	}

	public void buildData() {
		int nbPg = book.getPageCount();
		int nbCol = (book.getPageDisplayer().hasTwoColumns())? 2 : 1;
		int nbColCase = book.getCasePerColumn();
		if (book.getPageCountForPrinting() > 0)
			nbPg /= book.getPageCountForPrinting();
		book.collectData(this);
		data.add(new JrStatEntry("TxtDistance"));
		data.add(new JrStatEntry("TxtLaPlusCourte",shorterDistance,true));
		data.add(new JrStatEntry("TxtLaPlusLongue",longerDistance,true));
		data.add(new JrStatEntry("TxtTotale",totalDistance,true));
		data.add(new JrStatEntry("TxtMoyenne",averageDistance,true));
		data.add(new JrStatEntry("TxtNbRaz",razCount,false));
		data.add(new JrStatEntry("TxtNbApprox",approxCount,false));
		data.add(new JrStatEntry("TxtNbInconnu",inconnueCount,false));
		data.add(new JrStatEntry("TxtCases"));
		data.add(new JrStatEntry("TxtNombre",book.getCaseCount(),false));
		data.add(new JrStatEntry("TxtParColonne",nbColCase,false));		
		data.add(new JrStatEntry("TxtPages"));
		data.add(new JrStatEntry("TxtNombre",book.getPageCount(),false));
		data.add(new JrStatEntry("TxtNbColonnes",nbCol,false));
		data.add(new JrStatEntry("TxtNbCases",nbColCase * nbCol,false));
		data.add(new JrStatEntry("TxtFeuilles"));
		data.add(new JrStatEntry("TxtNombre",book.getPageCountForPrinting(),false));
		data.add(new JrStatEntry("TxtPagesFeuille",nbPg,false));
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
		JrStatEntry item = (JrStatEntry)(data.get(row));
		if (item != null) {
			switch(col) {
			case 0 : return item.getLabel();
			case 1 : return item.getValue();
			default: return item.getUnit();
			}
		}
		return null;
	}
	
	public String getColumnName(int col) {
		return null;
    }

    public Class getColumnClass(int c) {
        return String.class;
    }
	
	/**
	 * @param approxCount The approxCount to set.
	 */
	public void setApproxCount(int approxCount) {
		this.approxCount = approxCount;
	}
	/**
	 * @param averageDistance The averageDistance to set.
	 */
	public void setAverageDistance(int averageDistance) {
		this.averageDistance = averageDistance;
	}
	/**
	 * @param inconnueCount The inconnueCount to set.
	 */
	public void setInconnueCount(int inconnueCount) {
		this.inconnueCount = inconnueCount;
	}
	/**
	 * @param longerDistance The longerDistance to set.
	 */
	public void setLongerDistance(int longerDistance) {
		this.longerDistance = longerDistance;
	}
	/**
	 * @param razCount The razCount to set.
	 */
	public void setRazCount(int razCount) {
		this.razCount = razCount;
	}
	/**
	 * @param shorterDistance The shorterDistance to set.
	 */
	public void setShorterDistance(int shorterDistance) {
		this.shorterDistance = shorterDistance;
	}
	/**
	 * @param totalDistance The totalDistance to set.
	 */
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}
	
	public JrStatEntry getEntry(int index) {
		return (JrStatEntry)(data.get(index));
	}
	
}
