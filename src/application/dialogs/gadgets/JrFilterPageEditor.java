/*
 * Created on Jul 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterPageEditor extends JSpinner implements JrFilterComponent,ChangeListener {
	private SpinnerModel mdl = null;
	private JrFilterGadget filter = null;
	
	public JrFilterPageEditor() {
		mdl = new SpinnerNumberModel(1,1,200,1);
		setModel(mdl);
		addChangeListener(this);
	}

	public JrFilterPageEditor(JrFilterGadget gad) {
		filter = gad;
		mdl = new SpinnerNumberModel(filter.getPage()+1,1,200,1);
		setModel(mdl);
		addChangeListener(this);
	}
	/* (non-Javadoc)
	 * @see application.dialogs.gadgets.JrFilterComponent#getFilterValue()
	 */
	public Integer getFilterValue() {
		return (Integer)(this.getValue());
	}

	/* (non-Javadoc)
	 * @see application.dialogs.gadgets.JrFilterComponent#connectFilter(application.dialogs.gadgets.JrFilterGadget)
	 */
	public void connectFilter(JrFilterGadget fil) {
		filter = fil;
		mdl = new SpinnerNumberModel(filter.getPage()+1,1,200,1);
		setModel(mdl);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JrFilterPageEditor) {
			Integer val = ((JrFilterPageEditor)obj).getFilterValue();
			filter.setPage(val.intValue() - 1);
		}
	}	
}
