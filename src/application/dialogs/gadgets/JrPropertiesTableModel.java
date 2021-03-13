/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertiesTableModel implements TableModel,JrPropertyListener {
	private boolean hasColumnUnit = false;
	private Vector props = new Vector();
	
	public JrPropertiesTableModel(boolean colUnit) {
		hasColumnUnit = colUnit;
	}
	
	public void defineProperty(String n,String l) {
		defineProperty(new JrProperty(n,l));
	}
	public void defineProperty(String n,String l,Object v,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,u,ro));
	}
	public void defineProperty(String n,String l,Color c,boolean ro) {
		defineProperty(new JrPropertyColor(n,l,c,ro));
	}
	public void defineProperty(String n,String l,String v,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,u,ro));
	}
	public void defineProperty(String n,String l,String v,int max,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,max,u,ro));
	}
	public void defineProperty(String n,String l,boolean v,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,u,ro));
	}
	public void defineProperty(String n,String l,int v,int vmin,int vmax,int pas,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,vmin,vmax,pas,u,ro));
	}
	public void defineProperty(String n,String l,int v,int vmin,int vmax,int pas,String fmt,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,vmin,vmax,pas,fmt,u,ro));
	}
	public void defineProperty(String n,String l,double v,double vmin,double vmax,double pas,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,vmin,vmax,pas,u,ro));
	}
	public void defineProperty(String n,String l,double v,double vmin,double vmax,double pas,String fmt,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,vmin,vmax,pas,fmt,u,ro));
	}
	public void defineProperty(String n,String l,int v,String[] items,String u,boolean ro) {
		defineProperty(new JrProperty(n,l,v,items,u,ro));
	}
	public void defineProperty(JrProperty prop) {
		prop.addListener(this);
		props.add(prop);
	}	
	
	public void defineProperty(JrPropertyColor prop) {
		prop.addListener(this);
		props.add(prop);
	}	
	
	public JrProperty getProperty(int index) {
		return (JrProperty)(props.get(index));
	}
	
	public JrPropertyColor getPropertyColor(int index) {
		return (JrPropertyColor)(props.get(index));
	}
	
	public int getColumnCount() {
		return (hasColumnUnit == true)? 3 : 2;
	}

	public int getRowCount() {		
		return props.size();
	}

	public boolean isCellEditable(int rowno, int colno) {
		if (colno == 1) {
			JrProperty prop = (JrProperty)(props.get(rowno));
			if (prop != null)
				return !prop.isReadOnly();
		}
		return false;
	}

	public Class getColumnClass(int colno) {		
		return (colno == 1)? JrProperty.class : String.class;
	}

	public Object getValueAt(int rowno, int colno) {
		JrProperty prop = (JrProperty)(props.get(rowno));
		if (prop != null) {
			switch(colno) {
			case 1 : return prop.getValue();
			case 2 : return prop.getUnit();
			default: return prop.getLabel();
			}
		}
		return null;
	}

	public void setValueAt(Object val, int rowno, int colno) {
		JrProperty prop = (JrProperty)(props.get(rowno));
		if ((prop != null) && (colno == 1))
			prop.setValue(val);
	}

	public String getColumnName(int colno) {
		switch(colno) {
		case 1 : return "TxtValeur";
		case 2 : return "TxtUnite";
		default: break;
		}
		return "TxtPropriete";
	}

	public void addTableModelListener(TableModelListener arg0) {
	}

	public void removeTableModelListener(TableModelListener arg0) {
	}

	public void valueChanged(JrProperty prop) {
	}
}
