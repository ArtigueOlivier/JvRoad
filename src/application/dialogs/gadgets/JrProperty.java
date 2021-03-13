/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrProperty implements ChangeListener,ActionListener{
	private static String [] cbnYesNo = null;
	
	public static final int TEXTFIELD_TYPE = 0;
	public static final int BOOLEAN_TYPE = 1;
	public static final int INTEGER_TYPE = 2;
	public static final int DOUBLE_TYPE = 3;
	public static final int ENUM_TYPE = 4;
	public static final int SECTION_TYPE = 5;
	public static final int OTHER_TYPE = 6;
	public static final int COLOR_TYPE = 7;
	
	private String name = "";
	private String label = "";
	private Object value = null;
	private String unit = "";
	private boolean readOnly = false;
	private int type = TEXTFIELD_TYPE;
	private Vector params = new Vector();
	private Component currentEditor = null;
	private ArrayList listeners = new ArrayList();
	
	public JrProperty(String n,String l) {
		initializeStaticValues();
		label = l;
		name = n;
		value = "";		
		unit = "";
		readOnly = true;
		type = SECTION_TYPE;
	}

	public JrProperty(String n,String l,Color c,boolean ro) {
		initializeStaticValues();
		label = l;
		name = n;
		value = c;		
		unit = "";
		readOnly = ro;
		type = COLOR_TYPE;
	}

	
	public JrProperty(String n,String l,Object v,String u,boolean ro) {
		initializeStaticValues();
		label = l;
		name = n;
		value = v;		
		unit = u;
		readOnly = ro;
		type = OTHER_TYPE;
	}

	public JrProperty(String n,String l,String v,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = v;		
		unit = u;
		readOnly = ro;
	}

	public JrProperty(String n,String l,String v,int max,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = v;		
		unit = u;
		readOnly = ro;
		params.add(new Integer(max));
	}
	
	public JrProperty(String n,String l,boolean v,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Boolean(v);		
		type = BOOLEAN_TYPE;
		unit = u;
		readOnly = ro;
	}

	public JrProperty(String n,String l,int v,int vmin,int vmax,int pas,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Integer(v);
		type = INTEGER_TYPE;
		unit = u;
		readOnly = ro;
		params.add(new Integer(vmin));
		params.add(new Integer(vmax));
		params.add(new Integer(pas));
	}

	public JrProperty(String n,String l,int v,int vmin,int vmax,int pas,String fmt,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Integer(v);
		type = INTEGER_TYPE;
		unit = u;
		readOnly = ro;
		params.add(new Integer(vmin));
		params.add(new Integer(vmax));
		params.add(new Integer(pas));
		params.add(fmt);
	}

	public JrProperty(String n,String l,double v,double vmin,double vmax,double pas,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Double(v);
		type = DOUBLE_TYPE;
		unit = u;
		readOnly = ro;
		params.add(new Double(vmin));
		params.add(new Double(vmax));
		params.add(new Double(pas));
	}
	public JrProperty(String n,String l,double v,double vmin,double vmax,double pas,String fmt,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Double(v);
		type = DOUBLE_TYPE;
		unit = u;
		readOnly = ro;
		params.add(new Double(vmin));
		params.add(new Double(vmax));
		params.add(new Double(pas));
		params.add(fmt);
	}

	public JrProperty(String n,String l,int v,String [] vals,String u,boolean ro) {
		initializeStaticValues();
		name = n;
		label = l;
		value = new Integer(v);	
		type = ENUM_TYPE;
		unit = u;
		readOnly = ro;
		for(int i = 0; i < vals.length; i++)
			params.add(vals[i]);
	}	

	public void initializeStaticValues() {
		if (cbnYesNo == null) {
			cbnYesNo    = new String [2];
			cbnYesNo[0] = JrApplicationOption.GetWord("TxtNon");
			cbnYesNo[1] = JrApplicationOption.GetWord("TxtOui");
		}			
	}
	
	public void addListener(JrPropertyListener lst) {
		listeners.add(lst);
	}

	public void removeListener(JrPropertyListener lst) {
		listeners.remove(lst);
	}
	
	public void fireValueChange() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrPropertyListener)(iter.next())).valueChanged(this);
		}								
	}
	
	public String getStringValue() {
		return (String)value;
	}
	
	public int getIntegerValue() {
		return ((Integer)value).intValue();
	}
	
	public double getDoubleValue() {
		return ((Double)value).doubleValue();
	}

	public boolean getBooleanValue() {
		if ((type == INTEGER_TYPE) || (type == ENUM_TYPE))
			return (((Integer)value).intValue() == 0)? false : true;
		return ((Boolean)value).booleanValue();
	}
	
	public Component createComponent(boolean renderer) {
		SpinnerModel mdl;
		switch(type) {
		case BOOLEAN_TYPE :
			int val = (((Boolean)value).booleanValue() == true)? 1 : 0;
			JComboBox cbn = new JComboBox(cbnYesNo);
			cbn.setSelectedIndex(val);
			cbn.setRenderer(new RightAlignedListCell());			
			if (!renderer) cbn.addActionListener(this);
			return cbn;
		case INTEGER_TYPE :
			int ivmin = ((Integer)(params.get(0))).intValue();
			int ivmax = ((Integer)(params.get(1))).intValue();
			int ipas  = ((Integer)(params.get(2))).intValue();
			mdl = new SpinnerNumberModel(((Integer)value).intValue(),ivmin,ivmax,ipas);
			JSpinner iSpin = new JSpinner(mdl);
			if (params.size() > 3) {
				String fmt = (String)(params.get(3));
				iSpin.setEditor(new JSpinner.NumberEditor(iSpin,fmt));
			}
			if (!renderer) iSpin.addChangeListener(this);
			return iSpin;
		case DOUBLE_TYPE :
			double dvmin = ((Double)(params.get(0))).doubleValue();
			double dvmax = ((Double)(params.get(1))).doubleValue();
			double dpas  = ((Double)(params.get(2))).doubleValue();
			mdl = new SpinnerNumberModel(((Double)value).doubleValue(),dvmin,dvmax,dpas);
			JSpinner dSpin = new JSpinner(mdl);
			if (params.size() > 3) {
				String fmt = (String)(params.get(3));
				dSpin.setEditor(new JSpinner.NumberEditor(dSpin,fmt));
			}
			if (!renderer) dSpin.addChangeListener(this);
			return dSpin;
		case ENUM_TYPE :
			JComboBox cbn2 = new JComboBox(params.toArray());
			if (!renderer) cbn2.addActionListener(this);
			cbn2.setSelectedIndex(((Integer)value).intValue());
			cbn2.setRenderer(new RightAlignedListCell());
			return cbn2;
		case SECTION_TYPE :
			JLabel sec = new JLabel();
			sec.setForeground(Color.BLUE);
			sec.setText(" " + value.toString());
			return sec;	
		case COLOR_TYPE :
		default : break;
		}
		if (isReadOnly()) {
			JLabel label = new JLabel();
			label.setText(value.toString());
			return label;
		}
		if (params.size() > 0) {
			int max = ((Integer)(params.get(0))).intValue();
			JrPropertyTextfield txt = new JrPropertyTextfield(max);
			txt.setText(value.toString());
			txt.addActionListener(this);
			return txt;
		}
		JrPropertyTextfield txt = new JrPropertyTextfield();
		txt.setText(value.toString());
		txt.addActionListener(this);
		return txt;
	}
	
	public Object getValueFromEditor(Object editor) {
		switch(type) {
		case BOOLEAN_TYPE : 
			return new Boolean((((JComboBox)editor).getSelectedIndex() == 0)? false : true);
		case INTEGER_TYPE :
		case DOUBLE_TYPE :
			return ((JSpinner)editor).getValue();
		case ENUM_TYPE :
			return new Integer(((JComboBox)editor).getSelectedIndex());
		}
		return ((JrPropertyTextfield)editor).getText();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Component getCurrentEditor() {
		return currentEditor;
	}
	
	public void setCurrentEditor(Component newEditor) {
		if ((currentEditor != null) && (this.currentEditor != newEditor)){
			if (currentEditor instanceof JComboBox)
				((JComboBox)currentEditor).removeActionListener(this);
			else if (currentEditor instanceof JSpinner)
				((JSpinner)currentEditor).removeChangeListener(this); 
			else if (currentEditor instanceof JrPropertyTextfield)
				((JrPropertyTextfield)currentEditor).removeActionListener(this);
			else if (currentEditor instanceof JButton)
				((JButton)currentEditor).removeActionListener(this);
		}
		this.currentEditor = newEditor;
	}

	public void stateChanged(ChangeEvent arg0) {
		if (currentEditor != null) {
			Object val = getValueFromEditor(currentEditor);
			if (val.equals(value) == false) {
				setValue(val);
				fireValueChange();
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (currentEditor != null) {
			Object val = getValueFromEditor(currentEditor);
			if (val.equals(value) == false) {
				setValue(val);
				fireValueChange();
			}
		}
	}
	
	class RightAlignedListCell extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(
                    JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus)
        {
            // Call the superclass to get a renderer that is correct for the current look-and-feel.
            JLabel cell = (JLabel) super.getListCellRendererComponent(
                     list, value, index, isSelected, cellHasFocus);
            cell.setHorizontalAlignment(JLabel.RIGHT);
            return cell;
        }
    }


	
}
