/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.drawing;

import java.awt.event.ActionEvent;

import tools.JrContext;
import book.JrBook;

import names.JrPontName;


import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionPont extends JrActionMenu {
	private int codePont = JrPontName.PONT_NONE;

	public JrActionPont(String hlp, String img,int pont) {
		super("", 0, hlp, img);
		codePont = pont;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrContext context = book.getContext();
		if (context.getCurrentPont() != codePont) {
			context.setCurrentPont(codePont);
			JrActionCenter.RefreshActionsPont();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			JrContext context = book.getContext();
			return (context.getCurrentPont() == codePont)? false : true;
		}
		return false;
	}

	public static JrActionPont CreateAction(int pont) {
		switch(pont) {
		case JrPontName.PONT_SENTIER_V :
			return new JrActionPont("MenuPontSentierVHlp","images/pontsenv.gif",pont);
		case JrPontName.PONT_SENTIER_H :
			return new JrActionPont("MenuPontSentierHHlp","images/pontsenh.gif",pont);
		case JrPontName.PONT_PATH_V :
			return new JrActionPont("MenuPontPathVHlp","images/pontpathv.gif",pont);
		case JrPontName.PONT_PATH_H :
			return new JrActionPont("MenuPontPathHHlp","images/pontpathh.gif",pont);
		case JrPontName.PONT_ROAD_V :
			return new JrActionPont("MenuPontRoadVHlp","images/pontroadv.gif",pont);
		case JrPontName.PONT_ROAD_H :
			return new JrActionPont("MenuPontRoadHHlp","images/pontroadh.gif",pont);
		case JrPontName.PONT_NATIONAL_V :
			return new JrActionPont("MenuPontNationalVHlp","images/pontnatv.gif",pont);
		case JrPontName.PONT_NATIONAL_H :
			return new JrActionPont("MenuPontNationalHHlp","images/pontnath.gif",pont);
		case JrPontName.PONT_DOUBLEROAD_V :
			return new JrActionPont("MenuPontDoubleRoadVHlp","images/pontdlnv.gif",pont);
		case JrPontName.PONT_DOUBLEROAD_H :
			return new JrActionPont("MenuPontDoubleRoadHHlp","images/pontdlnh.gif",pont);
		case JrPontName.PONT_TRAIN_V :
			return new JrActionPont("MenuPontTrainVHlp","images/ponttrainv.gif",pont);
		case JrPontName.PONT_TRAIN_H :
			return new JrActionPont("MenuPontTrainHHlp","images/ponttrainh.gif",pont);
		case JrPontName.PONT_RIVER_V :
			return new JrActionPont("MenuPontRiverVHlp","images/pontriverv.gif",pont);
		case JrPontName.PONT_RIVER_H :
			return new JrActionPont("MenuPontRiverHHlp","images/pontriverh.gif",pont);
		default: break;
		}
		return new JrActionPont("MenuPontEffacerHlp", "images/efface.gif",
				JrPontName.PONT_NONE);
	}
}
