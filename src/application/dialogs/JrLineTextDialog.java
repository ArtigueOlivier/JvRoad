/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.dialogs.gadgets.JrTextfield;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLineTextDialog extends JrDialog  {
	private String comments = null;	
	private JrTextfield edtText;
	
	private String text = new String();
	
	public JrLineTextDialog(JFrame frame,String txt,String title,String comment) {
		super(frame,title);
		text = txt;
		comments = comment;
	}
	
	public JPanel buildPanel() {
        edtText = new JrTextfield(30);
        edtText.setText(text);

        JEditorPane edtComment = new JEditorPane();
        edtComment.setEditable(false);
        edtComment.setText(GetWord(comments));
        JScrollPane scrollComment = new JScrollPane(edtComment);
        scrollComment.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollComment.setPreferredSize(new Dimension(250, 40));
                
        JLabel labText = new JLabel(GetWord("TxtVotreTexte") + ":");
        JLabel labNote = new JLabel(GetWord("TxtNote") + ":");
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
		labText.setAlignmentX(Component.LEFT_ALIGNMENT);
		labNote.setAlignmentX(Component.LEFT_ALIGNMENT);
		edtText.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollComment.setAlignmentX(Component.LEFT_ALIGNMENT);

		listPane.add(labText);
        listPane.add(edtText);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.add(labNote);       
        listPane.add(scrollComment);
        
        return listPane;
	}
	
	public boolean validateData() {
		text = edtText.getText();
		return true;
	}
	
	public String getText() {
		return text;
	}
}
