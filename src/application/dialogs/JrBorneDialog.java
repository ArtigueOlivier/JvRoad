/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBorneDialog extends JrDialog  {
	private JSpinner edtNumero = null;
	
	private int numero = 1;
	
	public JrBorneDialog(JFrame frame) {
		super(frame,"TxtNumeroBorne");
	}
	
	public JPanel buildPanel() {
		SpinnerModel model = new SpinnerNumberModel(numero, 0, 1000, 1);
		edtNumero = new JSpinner(model);
		
        JPanel listPane = new JPanel();
        listPane.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(GetWord("TxtNumero") + ":");
        listPane.add(label);
        listPane.add(edtNumero);
        
        edtNumero.setValue(new Integer(numero));
        return listPane;
	}
	
	public boolean validateData() {
		SpinnerNumberModel model = (SpinnerNumberModel)edtNumero.getModel();
		numero = model.getNumber().intValue();
		return true;
	}
	
	public int getNumero() {
		return numero;
	}
}
