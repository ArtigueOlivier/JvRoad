/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import tools.JrContext;

import book.JrBook;
import book.JrCase;
import book.map.JrMap;
import book.map.JrMapVector;

import names.JrMapName;
import names.JrVectorOperatorName;
import names.JrVectorTypeName;
import names.JrViewName;

import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionVector extends JrActionMenu {
	private int opeVector = JrVectorOperatorName.VECTOR_OPE_SEGMENT;
	
	public JrActionVector(String txt, String hlp, String img,int ope) {
		super(txt, 0, hlp, img);
		opeVector = ope;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		boolean res = false;
		JrBook book = getCurrentBook();
		JrContext cnt = book.getContext();
		JrCase cas = book.getCurrentCase();
		JrMap map = cas.getMap(book.getCurrentView());
		if (map.getName() == JrMapName.MAP_VECTOR_NAME) {
			switch(opeVector) {			
			case JrVectorOperatorName.VECTOR_OPE_SEGMENT :				
				cnt.setCurrentVector(JrVectorTypeName.VECTOR_SEGMENT);
				cnt.setVectorMode(opeVector);
			    res = true;
			    break;
			case JrVectorOperatorName.VECTOR_OPE_CURVE :
				book.getContext().setCurrentVector(JrVectorTypeName.VECTOR_CURVE);
				cnt.setVectorMode(opeVector);
		    	res = true;
		    	break;
			case JrVectorOperatorName.VECTOR_OPE_MOVE :
				cnt.setVectorMode(opeVector);
				res = true;
				break;
			case JrVectorOperatorName.VECTOR_OPE_SELECT :
				cnt.setVectorMode(opeVector);
				res = true;
				break;
			case JrVectorOperatorName.VECTOR_OPE_DEL :
				if (((JrMapVector)map).deleteCurrent()) 
					book.fireCurrentMapChanged();
				break;
			default : break;
			}
		}
		if (res) {
			JrActionCenter.RefreshActionsVector();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == false) 
			return false;
		JrCase cas = book.getCurrentCase();
		int view = book.getCurrentView();
		if (view < JrViewName.VIEW_REAL) {
			JrContext cnt = book.getContext();
			return (opeVector == cnt.getVectorMode())? false : true;
		}
		return false;
	}	
	
	public static JrActionVector CreateAction(int ope) {
		switch(ope) {
		case JrVectorOperatorName.VECTOR_OPE_MOVE :
			return new JrActionVector("MenuVectorMove","MenuVectorMoveHlp",
					"images/move.gif",ope);
		case JrVectorOperatorName.VECTOR_OPE_CURVE :
			return new JrActionVector("MenuVectorCurve","MenuVectorCurveHlp",
					"images/addcur.gif",ope);
		case JrVectorOperatorName.VECTOR_OPE_SELECT :
			return new JrActionVector("MenuVectorSelect","MenuVectorSelectHlp",
					"images/select.gif",ope);
		case JrVectorOperatorName.VECTOR_OPE_DEL :
			return new JrActionVector("MenuVectorDel","MenuVectorDelHlp",
					"images/delseg.gif",ope);
		default : break;			
		}
		return new JrActionVector("MenuVectorSegment","MenuVectorSegmentHlp",
				"images/addseg.gif",JrVectorOperatorName.VECTOR_OPE_SEGMENT);
	}	
}
