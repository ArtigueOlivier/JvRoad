/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import book.JrBook;
import book.JrCase;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTextPanel extends JrPanel {
	private JTextArea edtComments;
	private JCheckBox chkCenter;
	//---- Text
	private String comments = "";
	private boolean centerComments = false;
	
	public JrTextPanel(JrBook bk) {	
		super(bk,new BorderLayout());
					
		if (book != null) {
			JrCase cas = book.getCurrentCase();
			comments = cas.getComments();
			centerComments = cas.isCenterComments();
		}
		edtComments = new JTextArea(8,20);
		edtComments.setText(comments);
		chkCenter = new JCheckBox(GetWord("TxtCentre"));
		chkCenter.setSelected(centerComments);
        JScrollPane scrollComments = new JScrollPane(edtComments);
        scrollComments.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JLabel note = new JLabel(GetWord("MsgMaxCaracteres"));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(chkCenter,BorderLayout.NORTH);
		add(scrollComments,BorderLayout.CENTER);
		add(note,BorderLayout.PAGE_END);
	}
	
	public boolean validatePage() {
		comments = edtComments.getText();
		centerComments = chkCenter.isSelected();
		if (book != null) {
			JrCase cas = book.getCurrentCase();
			cas.setComments(comments);
			cas.setCenterComments(centerComments);
		}
		return true;
	}
	public String getComments() {
		return comments;
	}
}
