/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

import application.JrApplicationOption;
import names.*;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrContext {
	private int currentTrait = JrTraitName.TRAIT_PATH;
	private int currentTraitPas = JrTraitName.TRAIT_PATH;
	private int currentTraitVector = JrTraitName.TRAIT_PATH;
	private int currentBorne = JrBorneName.BORNE_NATIONALE;
	private int currentArc = JrArcName.ARC_NOTHING;
	private int currentObj = JrObjName.OBJ_NOTHING;
	private int currentView = JrViewName.VIEW_ONE;
	private int currentPont = JrPontName.PONT_NONE;
	private int currentVector = JrVectorTypeName.VECTOR_SEGMENT;
	private int vectorMode = JrVectorOperatorName.VECTOR_OPE_SEGMENT;
	
	public JrContext() {
		if (JrApplicationOption.IsReaderMode() > 0)
			currentView = JrViewName.VIEW_REAL;
	}
	
	/**
	 * @return Returns the currentArc.
	 */
	public int getCurrentArc() {
		return currentArc;
	}
	/**
	 * @param currentArc The currentArc to set.
	 */
	public void setCurrentArc(int currentArc) {
		this.currentArc = currentArc;
	}
	/**
	 * @return Returns the currentBorne.
	 */
	public int getCurrentBorne() {
		return currentBorne;
	}
	/**
	 * @param currentBorne The currentBorne to set.
	 */
	public void setCurrentBorne(int currentBorne) {
		this.currentBorne = currentBorne;
	}
	/**
	 * @return Returns the currentTraitPas.
	 */
	public int getCurrentTraitPas() {
		return currentTraitPas;
	}
	/**
	 * @param currentTraitPas The currentTraitPas to set.
	 */
	public void setCurrentTraitPas(int currentTraitPas) {
		this.currentTraitPas = currentTraitPas;
	}
	/**
	 * @return Returns the currentTrait.
	 */
	public int getCurrentTrait() {
		return currentTrait;
	}
	/**
	 * @param currentTrait The currentTrait to set.
	 */
	public void setCurrentTrait(int currentTrait) {
		this.currentTrait = currentTrait;
	}

	/**
	 * @return
	 */
	public int getCurrentObject() {
		return currentObj;
	}

	/**
	 * @param codeObj
	 */
	public void setCurrentObject(int codeObj) {
		currentObj = codeObj;		
	}
	/**
	 * @return Returns the currentView.
	 */
	public int getCurrentView() {
		return currentView;
	}
	/**
	 * @param currentView The currentView to set.
	 */
	public void setCurrentView(int currentView) {
		this.currentView = currentView;
	}
	/**
	 * @return Returns the currentVector.
	 */
	public int getCurrentVector() {
		return currentVector;
	}
	/**
	 * @param currentVector The currentVector to set.
	 */
	public void setCurrentVector(int currentVector) {
		this.currentVector = currentVector;
	}
	/**
	 * @return Returns the moveMode.
	 */
	public int getVectorMode() {
		return vectorMode;
	}
	/**
	 * @param moveMode The moveMode to set.
	 */
	public void setVectorMode(int mode) {
		vectorMode = mode;
	}
	/**
	 * @return Returns the currentTraitVector.
	 */
	public int getCurrentTraitVector() {
		return currentTraitVector;
	}
	/**
	 * @param currentTraitVector The currentTraitVector to set.
	 */
	public void setCurrentTraitVector(int currentTraitVector) {
		this.currentTraitVector = currentTraitVector;
	}
	public int getCurrentPont() {
		return currentPont;
	}
	public void setCurrentPont(int currentPont) {
		this.currentPont = currentPont;
	}
}
