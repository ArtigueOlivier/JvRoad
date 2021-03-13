/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

import names.JrSymbolName;

import book.JrBook;
import book.JrCase;

import application.dialogs.gadgets.JrPropertiesTable;
import application.dialogs.gadgets.JrPropertiesTableModel;
import application.dialogs.gadgets.JrProperty;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCasePanel extends JrPanel {
	private static String[] cbnSymb  = null;

	private JrPropertiesTableModel modelProp;
	private JrPropertiesTable tableProp;
	
	//---- Distance
	private int distance = 0;
	private boolean raz = false;
	private boolean approx = false;
	private boolean inconnu = false;
	//---- Dessin
	private int symbol[] = { JrSymbolName.SYMBOL_NOTHING,
			JrSymbolName.SYMBOL_NOTHING,JrSymbolName.SYMBOL_NOTHING,
			JrSymbolName.SYMBOL_NOTHING};
	
	public JrCasePanel(JrBook bk) {	
		super(bk,new BorderLayout());
		int i;
		if (cbnSymb == null) {
			cbnSymb = new String [JrSymbolName.SYMBOL_COUNT];
			for(i = 0; i < JrSymbolName.SYMBOL_COUNT; i++) {
				cbnSymb[i] = GetWord(JrSymbolName.GetSymbolText(i));
			}
		}
					
		if (book != null) {
			JrCase cas = book.getCurrentCase();
			distance = cas.getDistance();
			raz = cas.isRaz();
			approx = cas.isApprox();
			inconnu = cas.isInconnuDistance();
			for(i = 0; i < 4; i++)
				symbol[i] = cas.getSymbol(i);
		}
		String s = " " + GetWord("TxtM");
		modelProp = new JrPropertiesTableModel(true);
		modelProp.defineProperty("Distance","TxtDistance");
		modelProp.defineProperty("Partiel","TxtPartiel",distance,0,1000000,1,s,false);
		modelProp.defineProperty("RAZ","TxtRaz",raz,"",false);
		modelProp.defineProperty("Approx","TxtDistanceApprox",approx,"",false);
		modelProp.defineProperty("Inconnue","TxtDistanceInconnue",inconnu,"",false);
		modelProp.defineProperty("Indic","TxtIndications");
		String str = GetWord("TxtNumero") + " ";
		for(i = 0; i < 4; i++)
			modelProp.defineProperty("Indic" + i,str + (i+1),symbol[i],cbnSymb,"",false);
		tableProp = new JrPropertiesTable(modelProp);
		tableProp.setBorder(new EtchedBorder(2));
		TableColumn column = null;
		column = tableProp.getColumnModel().getColumn(0);
		column.setPreferredWidth(140);
		column.setMinWidth(140);
		column.setMaxWidth(140);
		column.setResizable(false);
		column = tableProp.getColumnModel().getColumn(2);
		column.setPreferredWidth(15);
		column.setMinWidth(15);
		column.setMaxWidth(15);
		column.setResizable(false);
		tableProp.setPreferredSize(new Dimension(370,310));		
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(tableProp,BorderLayout.CENTER);
	}
	
	public boolean validatePage() {
		int i;
		JrProperty prop = modelProp.getProperty(1);
		distance = prop.getIntegerValue();
		prop = modelProp.getProperty(2);
		raz = prop.getBooleanValue();
		prop = modelProp.getProperty(3);
		approx = prop.getBooleanValue();
		prop = modelProp.getProperty(4);
		inconnu = prop.getBooleanValue();
		for(i = 0; i < 4; i++) {
			prop = modelProp.getProperty(6+i);
			symbol[i] = prop.getIntegerValue();
		}
		if (book != null) {
			JrCase cas = book.getCurrentCase();
			cas.setDistance(distance);
			cas.setRaz(raz);
			cas.setApprox(approx);
			cas.setInconnuDistance(inconnu);
			for(i = 0; i < 4; i++) 
				cas.setSymbol(i,symbol[i]);
		}
		return true;
	}
	public int getDistance() {
		return distance;
	}
	public boolean isRaz() {
		return raz;
	}	
	public boolean isApprox() {
		return approx;
	}
	public boolean isInconnuDistance() {
		return inconnu;
	}
	public int getSymbol(int num) {
		return symbol[num];
	}
}
