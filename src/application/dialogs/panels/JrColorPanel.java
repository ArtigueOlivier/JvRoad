/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

import tools.JrColorBook;

import names.JrColorName;

import book.JrBook;

import application.dialogs.gadgets.JrPropertiesTable;
import application.dialogs.gadgets.JrPropertiesTableModel;
import application.dialogs.gadgets.JrPropertyColor;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrColorPanel extends JrPanel {
	private JrPropertiesTableModel modelProp;
	private JrPropertiesTable tableProp;	
	
	//---- couleurs du livre
	private int colorType;
	private int colorCount;
	private Color[] colors = null;
	private int[] index = null;
	
	public JrColorPanel(JrBook bk,int typ) {
		super(bk,new BorderLayout());
		colorType = typ;
		colorCount = 0;
		int i,indice;
		if (colors == null) {
			colors = new Color [JrColorName.COLOR_COUNT];
			index  = new int [JrColorName.COLOR_COUNT];
		}
		modelProp = new JrPropertiesTableModel(true);
		modelProp.defineProperty("SecDesignation","TxtDesingation");
		JrColorBook cbk = bk.getColorBook();
		for (i= 0; i < JrColorName.COLOR_COUNT; i++) {
			if (JrColorName.GetColorType(i) == colorType) {
				modelProp.defineProperty(JrColorName.GetName(i),JrColorName.GetLabel(i),cbk.get(i),false);
				index[colorCount] = i;
				colorCount++;
			}
		}
		tableProp = new JrPropertiesTable(modelProp);
		tableProp.setBorder(new EtchedBorder(2));
		TableColumn column = null;
		column = tableProp.getColumnModel().getColumn(0);
		column.setPreferredWidth(140);
		column.setMinWidth(140);
		column.setMaxWidth(140);
		column.setResizable(false);
		column = tableProp.getColumnModel().getColumn(2);
		column.setPreferredWidth(5);
		column.setMinWidth(5);
		column.setMaxWidth(5);
		column.setResizable(false);
		tableProp.setPreferredSize(new Dimension(320,420));		
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(tableProp,BorderLayout.CENTER);
	}
	
	public boolean validatePage() {	
		int i;
		JrPropertyColor prop;
		for (i= 0; i < colorCount; i++) {
			prop = modelProp.getPropertyColor(i+1);
			colors[i] = prop.getCurrentColor();
		}
		if (book != null) {
			JrColorBook cbk = book.getColorBook();
			for (i= 0; i < colorCount; i++) {
				cbk.set(index[i],colors[i]);
			}
		}
		return true;
	}	
}
