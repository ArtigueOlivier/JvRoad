/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrDialog extends JDialog implements ActionListener{
	private boolean okPressed = false;
	private boolean okBtnOnly = false;
	private String textOk = "TxtOk";
	private String textCancel = "TxtAnnuler";
	private JFrame mainWindow = null;
	
	public JrDialog(JFrame frame,String title) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		if (frame == null)
			System.out.println(GetWord("ErrFrameNull"));
	}
	
	public JrDialog(JFrame frame,String title,boolean onlyOk) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		okBtnOnly = onlyOk;
		if (frame == null)
			System.out.println(GetWord("ErrFrameNull"));
	}
	
	public JrDialog(JFrame frame,String title,boolean onlyOk,String txtOk,String txtCancel) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		okBtnOnly = onlyOk;
		textOk = txtOk;
		textCancel = txtCancel;
		if (frame == null)
			System.out.println(GetWord("ErrFrameNull"));
	}

	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
			
	public boolean validateData() {
		return true;
	}
	
	public JPanel buildPanel() {
		return null;
	}
	
	public boolean execute() {
		JButton cancelButton = null;
		if (okBtnOnly == false) {
			cancelButton = new JButton(GetWord(textCancel));
			cancelButton.addActionListener(this);
			cancelButton.setActionCommand("CancelCmd");
		}
        //
        JButton okButton = new JButton(GetWord(textOk));
        okButton.setActionCommand("OkCmd");
        okButton.addActionListener(this);
        getRootPane().setDefaultButton(okButton);

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(okButton);
        if (cancelButton != null) {
        	buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        	buttonPane.add(cancelButton);
        }
        Container contentPane = getContentPane();
        
		JPanel panel = buildPanel();		
		if (panel != null) {
	        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	        contentPane.add(panel, BorderLayout.CENTER);
		}
	    contentPane.add(buttonPane, BorderLayout.PAGE_END);
	    pack();
		setLocationRelativeTo(mainWindow);
	    setVisible(true);
		return okPressed;
	}
	
    public void actionPerformed(ActionEvent e) {
    	if ("OkCmd".equals(e.getActionCommand())) {
    		okPressed = validateData();
    		if (okPressed)
    	    	setVisible(false);    		
    	}
    	else {
        	if ("CancelCmd".equals(e.getActionCommand())) {
        		setVisible(false);
        	}
    	}
    }	
    
    public GridBagConstraints createConstraint(int gx,int gy,int gw,int gh,int wx,int wy,int px,int py) {
    	GridBagConstraints cnt = new GridBagConstraints();
    	cnt.gridx = gx;
    	cnt.gridy = gy;
    	cnt.gridwidth = gw;
    	cnt.gridheight = gh;
    	cnt.weightx = wx;
    	cnt.weighty = wy;
    	cnt.ipadx = px;
    	cnt.ipady = py;
    	//cnt.anchor = GridBagConstraints.WEST;
    	cnt.fill = GridBagConstraints.BOTH;
    	cnt.insets = new Insets(py,py,px,px);
    	return cnt;
    }
    public GridBagConstraints createConstraint(int gx,int gy,int gw,int gh,int wx,int wy,int px,int py,int anchor) {
    	GridBagConstraints cnt = new GridBagConstraints();
    	cnt.gridx = gx;
    	cnt.gridy = gy;
    	cnt.gridwidth = gw;
    	cnt.gridheight = gh;
    	cnt.weightx = wx;
    	cnt.weighty = wy;
    	cnt.ipadx = px;
    	cnt.ipady = py;
    	cnt.anchor = anchor;
    	return cnt;
    }
}
