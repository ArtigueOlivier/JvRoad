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
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTabbedDialog extends JDialog implements ActionListener {
	private int pageCount = 0;
	private int pageStart = 0;
	private boolean okPressed = false;
	private boolean okBtnOnly = false;
	private String textOk = "TxtOk";
	private String textCancel = "TxtAnnuler";
	private JFrame mainWindow = null;
	
	public JrTabbedDialog(JFrame frame,String title,int pgCount,int pgStart) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		pageCount = pgCount;
		pageStart = pgStart;
	}
	
	public JrTabbedDialog(JFrame frame,String title,int pgCount,int pgStart,boolean onlyOk) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		pageCount = pgCount;
		pageStart = pgStart;
		okBtnOnly = onlyOk;
	}
	
	public JrTabbedDialog(JFrame frame,String title,int pgCount,int pgStart,boolean onlyOk,String txtOk,String txtCancel) {
		super(frame,GetWord(title),true);
		mainWindow = frame;
		pageCount = pgCount;
		pageStart = pgStart;
		okBtnOnly = onlyOk;
		textOk = txtOk;
		textCancel = txtCancel;
	}

	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
			
	public boolean validateData() {
		for(int i = 0; i < pageCount; i++) {
			if (validatePage(i) == false)
				return false;
		}
		return true;
	}
	
	public boolean validatePage(int pageno) {
		return true;
	}
	
	public JTabbedPane buildTabbedPanel() {
		return new JTabbedPane();
	}
	
	public JPanel buildPage(int pageno) {
		return null;
	}
	public String getPageName(int pageno) {
		return "";
	}
	public Icon getPageIcon(int pageno) {
		return null;
	}
	public String getPageTooltip(int pageno) {
		return "";
	}
	
	public JTabbedPane buildPanel() {
		JPanel pg;
		String nm;
		Icon icon;
		String tl;
		JTabbedPane tabbedPane = buildTabbedPanel();
		for(int i = 0; i < pageCount; i++) {
			pg   = buildPage(i);
			nm   = GetWord(getPageName(i));
			icon = getPageIcon(i);
			tl   = GetWord(getPageTooltip(i));
			if (pg != null)
				tabbedPane.addTab(nm,icon,pg,tl);
		}
		tabbedPane.setSelectedIndex(pageStart);
		return tabbedPane;
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
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(okButton);
        if (cancelButton != null) {
        	buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
        	buttonPane.add(cancelButton);
        }
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
		JTabbedPane panel = buildPanel();		
		if (panel != null) {
	        //panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
