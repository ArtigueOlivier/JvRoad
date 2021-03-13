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

import names.JrArcName;


import application.actions.JrActionCenter;
import application.actions.JrActionMenu;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionArc extends JrActionMenu {
	private int codeArc = JrArcName.ARC_NOTHING;

	public JrActionArc(String hlp, String img,int arc) {
		super("", 0, hlp, img);
		codeArc = arc;
	}

	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JrContext context = book.getContext();
		if (context.getCurrentArc() != codeArc) {
			context.setCurrentArc(codeArc);
			JrActionCenter.RefreshActionsArc();
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			JrContext context = book.getContext();
			return (context.getCurrentArc() == codeArc)? false : true;
		}
		return false;
	}

	public static JrActionArc CreateAction(int arc) {
		switch(arc) {
		case JrArcName.ARC_PATH_NO :
			return new JrActionArc("ArcPathNOHlp","images/arcparno.gif",arc);
		case JrArcName.ARC_PATH_BIGNO :
			return new JrActionArc("ArcPathBIGNOHlp","images/arcpbrno.gif",arc);
		case JrArcName.ARC_PATH_NE :
			return new JrActionArc("ArcPathNEHlp","images/arcparne.gif",arc);
		case JrArcName.ARC_PATH_BIGNE :
			return new JrActionArc("ArcPathBIGNEHlp","images/arcpbrne.gif",arc);
		case JrArcName.ARC_PATH_SO :
			return new JrActionArc("ArcPathSOHlp","images/arcparso.gif",arc);
		case JrArcName.ARC_PATH_BIGSO :
			return new JrActionArc("ArcPathBIGSOHlp","images/arcpbrso.gif",arc);
		case JrArcName.ARC_PATH_SE :
			return new JrActionArc("ArcPathSEHlp","images/arcparse.gif",arc);
		case JrArcName.ARC_PATH_BIGSE :
			return new JrActionArc("ArcPathBIGSEHlp","images/arcpbrse.gif",arc);
		case JrArcName.ARC_ROAD_NO :
			return new JrActionArc("ArcRoadNOHlp","images/arcrarno.gif",arc);
		case JrArcName.ARC_ROAD_BIGNO :
			return new JrActionArc("ArcRoadBIGNOHlp","images/arcrbrno.gif",arc);
		case JrArcName.ARC_ROAD_NE :
			return new JrActionArc("ArcRoadNEHlp","images/arcrarne.gif",arc);
		case JrArcName.ARC_ROAD_BIGNE :
			return new JrActionArc("ArcRoadBIGNEHlp","images/arcrbrne.gif",arc);
		case JrArcName.ARC_ROAD_SO :
			return new JrActionArc("ArcRoadSOHlp","images/arcrarso.gif",arc);
		case JrArcName.ARC_ROAD_BIGSO :
			return new JrActionArc("ArcRoadBIGSOHlp","images/arcrbrso.gif",arc);
		case JrArcName.ARC_ROAD_SE :
			return new JrActionArc("ArcRoadSEHlp","images/arcrarse.gif",arc);
		case JrArcName.ARC_ROAD_BIGSE :
			return new JrActionArc("ArcRoadBIGSEHlp","images/arcrbrse.gif",arc);
		case JrArcName.ARC_PATH_NOSE :
			return new JrActionArc("ArcPathNOSEHlp","images/arcpnose.gif",arc);
		case JrArcName.ARC_PATH_NESO :
			return new JrActionArc("ArcPathNESOHlp","images/arcpneso.gif",arc);
		case JrArcName.ARC_ROAD_NOSE :
			return new JrActionArc("ArcRoadNOSEHlp","images/arcrnose.gif",arc);
		case JrArcName.ARC_ROAD_NESO :
			return new JrActionArc("ArcRoadNESOHlp","images/arcrneso.gif",arc);
		case JrArcName.ARC_PATH_CROSS :
			return new JrActionArc("ArcPathCrossHlp","images/arcpcros.gif",arc);
		case JrArcName.ARC_PATHROAD_CROSS :
			return new JrActionArc("ArcPathRoadCrossHlp","images/arcprcro.gif",arc);
		case JrArcName.ARC_ROAD_CROSS :
			return new JrActionArc("ArcRoadCrossHlp","images/arcrcros.gif",arc);
		case JrArcName.ARC_ROADPATH_CROSS :
			return new JrActionArc("ArcRoadPathCrossHlp","images/arcrpcro.gif",arc);
		default: break;
		}
		return new JrActionArc("ArcNothingHlp", "images/linenone.gif",
				JrArcName.ARC_NOTHING);
	}
}
