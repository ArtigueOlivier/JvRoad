/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import tools.JrContext;

import book.JrBook;

import names.JrMapName;
import names.JrTraitName;


import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionTrait extends JrActionMenu {	
	protected int codeTrait;
	
	public JrActionTrait(String txt, int mnemo, String hlp, String img,int trait) {
		super(txt, mnemo, hlp, img);
		codeTrait = trait;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrContext context = book.getContext();
		switch(book.getCurrentMapName()) {
		case JrMapName.MAP_MILLEPAS_NAME :
			if (context.getCurrentTraitPas() != codeTrait) {
				context.setCurrentTraitPas(codeTrait);
				JrActionCenter.RefreshActionsTrait();
			}
			break;
		case JrMapName.MAP_VECTOR_NAME :
			if (context.getCurrentTraitVector() != codeTrait) {
				context.setCurrentTraitVector(codeTrait);
				JrActionCenter.RefreshActionsTrait();
			}
			break;
		default :
			if (context.getCurrentTrait() != codeTrait) {
				context.setCurrentTrait(codeTrait);
				JrActionCenter.RefreshActionsTrait();
			}
			break;
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			JrContext context = book.getContext();
			switch(book.getCurrentMapName()) {
			case JrMapName.MAP_MILLEPAS_NAME :
				return (context.getCurrentTraitPas() == codeTrait)? false : true;
			case JrMapName.MAP_VECTOR_NAME :
				return (context.getCurrentTraitVector() == codeTrait)? false : true;
			default :
				return (context.getCurrentTrait() == codeTrait)? false : true;
			}
		}
		return false;
	}

	public static JrActionTrait CreateAction(int trait) {
		switch(trait) {
		case JrTraitName.TRAIT_PATH :
			return new JrActionTrait("TraitPath",KeyEvent.VK_C,
					"TraitPathHlp","images/linepath.gif",trait);
		case JrTraitName.TRAIT_PATH_DEST :
			return new JrActionTrait("TraitPathDest",KeyEvent.VK_D,
					"TraitPathDestHlp","images/linepdst.gif",trait);
		case JrTraitName.TRAIT_PATH_DESTINV :
			return new JrActionTrait("TraitPathDestInv",KeyEvent.VK_I,
					"TraitPathDestInvHlp","images/linepdsti.gif",trait);
		case JrTraitName.TRAIT_PATH_SRC :
			return new JrActionTrait("TraitPathSrc",KeyEvent.VK_O,
					"TraitPathSrcHlp","images/linepsrc.gif",trait);
		case JrTraitName.TRAIT_PATH_SRCINV :
			return new JrActionTrait("TraitPathSrcInv",KeyEvent.VK_N,
					"TraitPathSrcInvHlp","images/linepsrci.gif",trait);
		case JrTraitName.TRAIT_ROAD :
			return new JrActionTrait("TraitRoad",KeyEvent.VK_R,
					"TraitRoadHlp","images/lineroad.gif",trait);
		case JrTraitName.TRAIT_ROAD_DEST :
			return new JrActionTrait("TraitRoadDest",KeyEvent.VK_D,
					"TraitRoadDestHlp","images/linerdst.gif",trait);
		case JrTraitName.TRAIT_ROAD_DESTINV :
			return new JrActionTrait("TraitRoadDestInv",KeyEvent.VK_I,
					"TraitRoadDestInvHlp","images/linerdsti.gif",trait);
		case JrTraitName.TRAIT_ROAD_SRC :
			return new JrActionTrait("TraitRoadSrc",KeyEvent.VK_O,
					"TraitRoadSrcHlp","images/linersrc.gif",trait);
		case JrTraitName.TRAIT_ROAD_SRCINV :
			return new JrActionTrait("TraitRoadSrcInv",KeyEvent.VK_N,
					"TraitRoadSrcInvHlp","images/linersrci.gif",trait);
		case JrTraitName.TRAIT_SENTIER :
			return new JrActionTrait("TraitSentier",KeyEvent.VK_S,
					"TraitSentierHlp","images/linesent.gif",trait);
		case JrTraitName.TRAIT_SENTIER_DEST :
			return new JrActionTrait("TraitSentierDest",KeyEvent.VK_H,
					"TraitSentierDestHlp","images/lsendst.gif",trait);
		case JrTraitName.TRAIT_SENTIER_DESTINV :
			return new JrActionTrait("TraitSentierDestInv",KeyEvent.VK_P,
					"TraitSentierDestInvHlp","images/lsendsti.gif",trait);
		case JrTraitName.TRAIT_SENTIER_SRC :
			return new JrActionTrait("TraitSentierSrc",KeyEvent.VK_G,
					"TraitSentierSrcHlp","images/lsensrc.gif",trait);
		case JrTraitName.TRAIT_SENTIER_SRCINV :
			return new JrActionTrait("TraitSentierSrcInv",KeyEvent.VK_T,
					"TraitSentierSrcInvHlp","images/lsensrci.gif",trait);
		case JrTraitName.TRAIT_NATIONAL :
			return new JrActionTrait("TraitNational",KeyEvent.VK_R,
					"TraitNationalHlp","images/linenat.gif",trait);
		case JrTraitName.TRAIT_NATIONAL_DEST :
			return new JrActionTrait("TraitNationalDest",KeyEvent.VK_D,
					"TraitNationalDestHlp","images/linendst.gif",trait);
		case JrTraitName.TRAIT_NATIONAL_DESTINV :
			return new JrActionTrait("TraitNationalDestInv",KeyEvent.VK_I,
					"TraitNationalDestInvHlp","images/linendsti.gif",trait);
		case JrTraitName.TRAIT_NATIONAL_SRC :
			return new JrActionTrait("TraitNationalSrc",KeyEvent.VK_O,
					"TraitNationalSrcHlp","images/linensrc.gif",trait);
		case JrTraitName.TRAIT_NATIONAL_SRCINV :
			return new JrActionTrait("TraitNationalSrcInv",KeyEvent.VK_N,
					"TraitNationalSrcInvHlp","images/linensrci.gif",trait);
		case JrTraitName.TRAIT_TRAIN :
			return new JrActionTrait("TraitTrain",KeyEvent.VK_V,
					"TraitTrainHlp","images/linetrain.gif",trait);
		case JrTraitName.TRAIT_RIVER :
			return new JrActionTrait("TraitRiver",KeyEvent.VK_R,
					"TraitRiverHlp","images/lineriver.gif",trait);
		case JrTraitName.TRAIT_DOUBLEROAD :
			return new JrActionTrait("TraitDoubleRoad",KeyEvent.VK_C,
					"TraitDoubleRoadHlp","images/dline.gif",trait);
		case JrTraitName.TRAIT_DOUBLEROAD_DEST :
			return new JrActionTrait("TraitDoubleRoadDest",KeyEvent.VK_S,
					"TraitDoubleRoadDestHlp","images/dlindst.gif",trait);
		case JrTraitName.TRAIT_DOUBLEROAD_DESTINV :
			return new JrActionTrait("TraitDoubleRoadDestInv",KeyEvent.VK_P,
					"TraitDoubleRoadDestInvHlp","images/dlindsti.gif",trait);
		case JrTraitName.TRAIT_DOUBLEROAD_SRC :
			return new JrActionTrait("TraitDoubleRoadSrc",KeyEvent.VK_G,
					"TraitDoubleRoadSrcHlp","images/dlinsrc.gif",trait);
		case JrTraitName.TRAIT_DOUBLEROAD_SRCINV :
			return new JrActionTrait("TraitDoubleRoadSrcInv",KeyEvent.VK_V,
					"TraitDoubleRoadSrcInvHlp","images/dlinsrci.gif",trait);
		default: break;
		}
		return new JrActionTrait("TraitNone",KeyEvent.VK_E,"TraitNone",
				"images/linenone.gif",JrTraitName.TRAIT_NONE);
	}
}
