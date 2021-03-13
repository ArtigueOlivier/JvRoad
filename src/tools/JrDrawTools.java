/*
 * Created on Jan 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.ShortLookupTable;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import tools.utils.JrDashItem;
import tools.utils.JrDashTrain;

import application.JrApplicationOption;
import book.displayer.JrCaseDisplayer;

import names.JrColorName;
import names.JrDrawPortName;
import names.JrFontSize;
import names.JrPageRegionName;
import names.JrPenName;
import names.JrPenZoomName;
import names.JrRegionFontName;
import names.JrRegionName;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrDrawTools {
	private Graphics2D dc = null;
	private Rectangle2D tmpRect = new Rectangle2D.Float();
	private Rectangle2D clipRect = new Rectangle2D.Float();
	private Line2D tmpLine = new Line2D.Float();
	private Ellipse2D tmpEllipse = new Ellipse2D.Float();
	private GeneralPath tmpPath = new GeneralPath();
	private Arc2D tmpArc = new Arc2D.Float();
	
	private int zoomPen = JrPenZoomName.ZOOM_NORMAL;
	
	private JrRegions regions = new JrRegions();
	private JrPageRegions pageRegions = new JrPageRegions();
	private int currentFontSize = JrFontSize.FONT_NORMAL_SIZE;
	private int fontSize[] = new int [JrRegionFontName.REGION_FONT_COUNT];
	private Font fonts[] = new Font [JrRegionFontName.REGION_FONT_COUNT];
	private float penWidth[] = new float [JrPenName.PEN_COUNT];
	private static LookupOp redFilter = null;
	private boolean autoParaph = true;
	private int displayMeters = 0;
	
	private JrDashItem dashes[] = new JrDashItem [JrPenZoomName.ZOOM_COUNT];     
	private JrDashTrain dashtr[] = new JrDashTrain [JrPenZoomName.ZOOM_COUNT];     
    
	private JrColorBook colorBook = null;
	
	public JrDrawTools(JrColorBook cbk,int port,int scale,int meters) {
		int i;		
		if (cbk == null)
			colorBook = new JrColorBook();
		else
			colorBook = cbk;
		for(i = 0; i < JrRegionFontName.REGION_FONT_COUNT; i++) {
			fontSize[i] = 10;
			fonts[i] = createFont(fontSize[i],false);
		}
		switch(port) {
		case JrDrawPortName.PORT_EDITION :
			zoomPen = JrApplicationOption.GetEditionZoom();
			break;
		case JrDrawPortName.PORT_NAVIGATOR :
			zoomPen = JrApplicationOption.GetNavigatorZoom();
			break;
		default :
			zoomPen = JrApplicationOption.GetPrintingZoom();
			break;
		}
		for(i = 0; i < JrPenZoomName.ZOOM_COUNT; i++) {
			dashes[i] = new JrDashItem(1.0f,1.0f);
			dashtr[i] = new JrDashTrain(1.0f,1.0f);
		}
		setScale(scale);
		
		if (redFilter == null)
			redFilter = createRedFilter();
	}
	
	public void setAutoParaph(boolean autop) {
		autoParaph = autop;
	}
	
	public void setDisplayMeters(int meters) {
		displayMeters = meters;
	}
	
	public int getDisplayMeters() {
		return displayMeters;
	}
	
	public void setScale(int scale) {
		int i;
		for(i = 0; i < JrPenName.PEN_COUNT; i++)
			penWidth[i] = JrPenName.GetWidth(i,scale) * JrPenZoomName.GetZoomValue(zoomPen);
		
		for(i = 0; i < JrPenZoomName.ZOOM_COUNT; i++)
			dashes[i].build(penWidth[JrPenName.PEN_SENTIER_BLACK],JrPenZoomName.GetZoomValue(i));
		
		for(i = 0; i < JrPenZoomName.ZOOM_COUNT; i++)
			dashtr[i].build(penWidth[JrPenName.PEN_TRAIN_BLACK],JrPenZoomName.GetZoomValue(i));
	}
	
	public void setColorBook(JrColorBook cbk) {
		colorBook = cbk;
	}
	
	public JrColorBook getColorBook() {
		return colorBook;
	}
	
	public JrRegions getRegions() {
		return regions;
	}
	public void setRegions(JrRegions regs) {
		regions = regs;
	}
	public Rectangle getRegion(int name) {
		return regions.get(name);
	}	
	
	public boolean isNullRegion(int rectname) {
		return regions.isNull(rectname);
	}

	public boolean isRegionModified(Rectangle rect) {
		return regions.hasBeenChanged(rect);
	}
	
	public void setDeviceGraphic(Graphics g) {
		dc = (Graphics2D)g;
		dc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public Graphics2D getDeviceGraphic() {
		return dc;
	}

	public int getFontSize(int name) {
		return fontSize[name];
	}
	
	public Font getFont(int name) {
		return fonts[name];
	}
	public int min(int a,int b) {
		if (b < 1) return a;
		return (a < b)? a : b;
	}
	public int max(int a,int b) {
		if (b < 1) return a;
		return (a < b)? b : a;
	}
	
	public void setPageRegions(JrPageRegions reg) {
		pageRegions = reg;
		int w = pageRegions.width(JrPageRegionName.PAGE_REGION_COPYRIGHT);
		int h = pageRegions.height(JrPageRegionName.PAGE_REGION_COPYRIGHT);
		int h1 = max(h / 2, 1);
		int w1 = w / 20;
		int val = min (h1,w1);
		updateFont(JrRegionFontName.REGION_FONT_COPYRIGHT,val);
				
		w = reg.width(JrPageRegionName.PAGE_REGION_TITRE);
		w = w / 15;
		h = reg.height(JrPageRegionName.PAGE_REGION_TITRE);
		val = min((2 * h) / 4,w);
		updateFont(JrRegionFontName.REGION_FONT_TITRE_PAGE,val);
		
		w = reg.width(JrPageRegionName.PAGE_REGION_TOTALPAGE1);
		w = (2 * w) / 23;
		h = reg.height(JrPageRegionName.PAGE_REGION_TOTALPAGE1);
		val = min(h / 2,w);
		updateFont(JrRegionFontName.REGION_FONT_TOTAL_PAGE,val);

		w = max(reg.width(JrPageRegionName.PAGE_REGION_PARTIEL1),reg.width(JrPageRegionName.PAGE_REGION_TOTAL1));
		h = max(reg.height(JrPageRegionName.PAGE_REGION_PARTIEL1),reg.height(JrPageRegionName.PAGE_REGION_TOTAL1));
		val = min((h / 2),(w / 4));
		updateFont(JrRegionFontName.REGION_FONT_TXT_PARTIEL,val);
	}
	
	public void adjustFonts(JrCaseDisplayer disp) {
		int val;
		int wx = max(regions.width(JrRegionName.REGION_TOTAL),regions.width(JrRegionName.REGION_PARTIEL));
		int wm = min(regions.width(JrRegionName.REGION_TOTAL),regions.width(JrRegionName.REGION_PARTIEL));
		int w = regions.width(JrRegionName.REGION_DISTANCE);
		w = min(w,regions.width(JrRegionName.REGION_RAZ));
		w = min(w,regions.width(JrRegionName.REGION_APPROX));
		int wmax = (min(w,wx) * 4) / 15;
		int wmin = (min(w,wm) * 4) / 15;
		int hx = max(regions.height(JrRegionName.REGION_TOTAL),regions.height(JrRegionName.REGION_PARTIEL));
		int hm = min(regions.height(JrRegionName.REGION_TOTAL),regions.height(JrRegionName.REGION_PARTIEL));
		int h = regions.height(JrRegionName.REGION_DISTANCE);
		h = min(h,regions.height(JrRegionName.REGION_RAZ));
		h = min(h,regions.height(JrRegionName.REGION_APPROX));
		int hmax = min(h,hx);
		int hmin = min(h,hm);
		val = min(hmax,wmax);
		updateFont(JrRegionFontName.REGION_FONT_DIST_NORMAL,val);
		val = (min(hmin,wmin) * 4)/5;
		updateFont(JrRegionFontName.REGION_FONT_DIST_SMALL,val);
		w = max((regions.width(JrRegionName.REGION_INDEX)  * 4) / 15,1);
		h = max(regions.height(JrRegionName.REGION_INDEX),1);
		val = min(h,w);
		updateFont(JrRegionFontName.REGION_FONT_INDEX,val);
		w = regions.width(JrRegionName.REGION_SYMBOL1);
		val = min(w,regions.height(JrRegionName.REGION_SYMBOL1)) / 3;
		updateFont(JrRegionFontName.REGION_FONT_SYMBOL,val);
		val = min(w,regions.height(JrRegionName.REGION_SYMBOL1)) / 2;
		updateFont(JrRegionFontName.REGION_FONT_SYMBOL_BIG,val);
		val = (2 * min(w,regions.height(JrRegionName.REGION_SYMBOL1))) / 7;
		updateFont(JrRegionFontName.REGION_FONT_SYMBOL_SMALL,val);
						
		w = regions.width(JrRegionName.REGION_TEXT);
		h = regions.height(JrRegionName.REGION_TEXT);
		
		int h1 = max(h / 2, 1);
		int hf1 = max(h / 6, 1);
		int w1 = (h1 * 3) / 5;
		int w2 = (h1 * 2) / 3;
		int w3 = (h1 * 3) / 4;
		int wf1 = min(hf1,max(w / 12, 1));
		int wf2 = min(hf1,max(w / 10, 1));
		int wf3 = min(hf1,max(w /  8, 1));		
		
		w1 -= (w1 == w2)? 1 : 0;
		w3 += (w3 == w2)? 1 : 0;
		wf1 -= (wf1 == wf2)? 1 : 0;
		wf3 += (wf3 == wf2)? 1 : 0;
		
		updateFont(JrRegionFontName.REGION_FONT_COMMENT1,w1);
		updateFont(JrRegionFontName.REGION_FONT_COMMENT2,w2);
		updateFont(JrRegionFontName.REGION_FONT_COMMENT3,w3);
		updateFont(JrRegionFontName.REGION_FONT_COMMENT_FULL1,wf1);
		updateFont(JrRegionFontName.REGION_FONT_COMMENT_FULL2,wf2);
		updateFont(JrRegionFontName.REGION_FONT_COMMENT_FULL3,wf3);
		
		w = max(regions.width(JrRegionName.REGION_MAP) / 7,1);
		h = max(regions.height(JrRegionName.REGION_MAP) / 7,1);
		val = min(h,w);
		updateFont(JrRegionFontName.REGION_FONT_MAP_BIG,val);
		w = max(regions.width(JrRegionName.REGION_MAP) / 28,1);
		h = max(regions.height(JrRegionName.REGION_MAP) / 12,1);
		val = min(h,w);
		updateFont(JrRegionFontName.REGION_FONT_BORNE,val);
	}	
	
	public void updateFont(int name,int taille) {
		if (fontSize[name] != taille) {
			fontSize[name] = taille;
			fonts[name] = createFont(taille,true);
		}
		
	}
	
	public void adjustPens() {
	}
	
	public int filterFontSize(int name) {
		switch(name) {
		case JrRegionFontName.REGION_FONT_COMMENT1 :
		case JrRegionFontName.REGION_FONT_COMMENT2 :
		case JrRegionFontName.REGION_FONT_COMMENT3 :
			name = JrRegionFontName.REGION_FONT_COMMENT1 + currentFontSize;
		    break;
		case JrRegionFontName.REGION_FONT_COMMENT_FULL1 :
		case JrRegionFontName.REGION_FONT_COMMENT_FULL2 :
		case JrRegionFontName.REGION_FONT_COMMENT_FULL3 :
			name = JrRegionFontName.REGION_FONT_COMMENT_FULL1 + currentFontSize;
			break;
		default : break;
		}
		return name;
	}

	public int selectFont(int name) {		
		dc.setFont(fonts[filterFontSize(name)]);
		return name;
	}
	
	public void selectFont(Font ft) {
		dc.setFont(ft);
	}
	
	public Font createFont(int size,boolean bold) {
		if (bold)
			return new Font("Helvetica",Font.BOLD,size);
		return new Font("Helvetica",Font.PLAIN,size);
	}
	
	public void selectColor(Color color) {
		dc.setColor(color);
	}
	
	public void selectColor(int name) {
		dc.setColor(colorBook.get(name));
	}
	
	public void selectColor(int name,boolean active) {
		int realname = (active == true)? name : JrColorName.GetInactiveColorName(name); 
		dc.setColor(colorBook.get(realname));		
	}

	public float getPenWidth(int name) {
		return penWidth[name];
	}
	
	public void selectPen(int width) {
		dc.setStroke(new BasicStroke((float)width,BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));		
	}
	public void selectPen(float width) {
		dc.setStroke(new BasicStroke(width,BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));		
	}
	public void selectDefinedPen(int name,boolean active) {
		boolean dashed = false;
		selectColor(colorBook.getPenColor(name,active));
		if (JrPenName.IsDashed(name)) 
			dc.setStroke(dashes[zoomPen].get());
		else {
			if (JrPenName.IsDashedTrain(name)) 
				dc.setStroke(dashtr[zoomPen].get());
			else
				selectPen(penWidth[name]);
		}
	}

	public void drawText(String txt,float x,float y,float w,float h,int fontname,int align) {
		if (txt.length() > 0) {
			FontRenderContext cnt = dc.getFontRenderContext();
			fontname = selectFont(fontname);
			FontMetrics tm = dc.getFontMetrics();
			float px = x;
			float py = y + (h/2.0f) + ((tm.getAscent() - tm.getDescent()) / 2.0f);
			switch (align) {
			case 1 : px += (w - tm.stringWidth(txt)) / 2.0f; break;
			case 2 : px += w - tm.stringWidth(txt); break;
			default: px += 2.0f;
			}
			TextLayout tl = new TextLayout(txt,fonts[fontname],cnt); 
			tl.draw(dc,px,py);	
		}
	}
	
	public void drawText(String txt,Rectangle rect,int align) {
		FontMetrics tm = dc.getFontMetrics();
		int x = rect.x;
		int y = ((int)rect.getCenterY()) + ((tm.getAscent() - tm.getDescent()) / 2);
		switch (align) {
		case 1 : x += (rect.width - tm.stringWidth(txt)) / 2; break;
		case 2 : x += rect.width - tm.stringWidth(txt); break;
		default: x += 2; break;
		}
		dc.drawString(txt,x,y);
	}
	
	public void drawText(String txt,int rectname,int align) {
		if (regions.isNull(rectname) == false)
			drawText(txt,regions.get(rectname),align);
	}

	public void drawInt(int num,int rectname,int align) {
		String txt = Integer.toString(num);
		drawText(txt,rectname,align);
	}

	public void drawTextArea(Rectangle reg,int fontname,String s,int align) {
		int x = reg.x + 5;
		int y = reg.y + 2;
		int w = reg.width - 10;
		int h = reg.height - 4;
		int use = 0;
		int ny;
		if (autoParaph == true) {
			String sp[] = s.split("\n");
			int count = sp.length;
			LineMetrics lm = fonts[fontname].getLineMetrics("W",dc.getFontRenderContext());
			ny = (int)(lm.getHeight());
			for(int i = 0; i < count; i++) {
				y += use;
				h -= use;
				if (h > 0) {
					ny = drawTextLine(x,y,w,h,fontname,sp[i],align);
					use = ny - y;
				}
			}
		}
		else {
			FontRenderContext frc = dc.getFontRenderContext();
			String sp[] = s.split("\n");
			int count = sp.length;
			LineMetrics lm = fonts[fontname].getLineMetrics("W",frc);
			Rectangle2D r = fonts[fontname].getStringBounds("M",frc);
			ny = (int)(lm.getHeight());
			int pos;
			int cmax = w / (int)(r.getWidth());
			if (cmax < 10) cmax = 10;
			int len,from,posw;
			String dst;
			for(int i = 0; i < count; i++) {
				len = sp[i].length();
				from = 0;
				if (len > 0) {
					while ((len > 0) && h >= ny) {
						h -= ny;
						pos = Math.min(cmax-1,len-1);
						posw = 1;
						while ((pos < len) && (posw < (w-cmax))) {
							pos++;
							dst = sp[i].substring(from,from+pos);
							r = fonts[fontname].getStringBounds(dst,frc);
							posw = (int)(r.getWidth());
						}
						drawText(sp[i].substring(from,from+pos),x,y,w,ny,fontname,align);					
						y += ny;
						len -= pos;
						from += pos;
					}
				}
				else  {
					h -= ny;
					y += ny;
				}
			}
		}
	}
	
	public int drawTextLine(int x,int y,int w,int h,int fontname,String s,int align) {
		if (s.length() == 0)
			s = " ";
		AttributedString attString;
		fontname = filterFontSize(fontname);
		Hashtable map = new Hashtable(); 
		map.put(TextAttribute.FONT,fonts[fontname]);
		attString = new AttributedString(s,map);
        AttributedCharacterIterator paragraph = attString.getIterator();
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        float formatWidth = (float)w;
        float drawPosY = y;
        float drawPosX = x;
        Shape oldClip = dc.getClip();
        dc.setClip(x,y,w,h);
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph,
        		BreakIterator.getLineInstance(),
                new FontRenderContext(null, false, false));
        lineMeasurer.setPosition(paragraphStart);
        while (lineMeasurer.getPosition() < paragraphEnd) {
            TextLayout layout = lineMeasurer.nextLayout(formatWidth);
            drawPosY += layout.getAscent();
            switch (align) {
            case 1 : drawPosX = x + ((formatWidth - layout.getAdvance())/ 2); break;
            case 2 : drawPosX = x + (formatWidth - layout.getAdvance()); break;
            default: break;
            }
            layout.draw((Graphics2D)dc, drawPosX, drawPosY);
            //drawPosY += layout.getLeading();
            drawPosY += layout.getDescent() + layout.getLeading();
        }
        dc.setClip(oldClip);
        return (int)drawPosY;
	}
	
	public void drawTextArea(int rectname,int fontname,String s,int align) {
		drawTextArea(regions.get(rectname),fontname,s,align);
	}
	
	public void drawDistance(int num,int rectname,int align) {
		if (displayMeters == 1)
			drawInt(num,rectname,align);
		else
			drawText(JrMath.formatDistance(num),rectname,align);
	}
	
	public void drawRegion(int rectname,boolean penNormal) {
		Rectangle rect = regions.get(rectname);
		if (penNormal)
			selectDefinedPen(JrPenName.PEN_CADRE_NORMAL,true);
		else
			selectDefinedPen(JrPenName.PEN_CADRE_EPAIS,true);
		selectColor(colorBook.getRegionColor(rectname));
		drawRectangle(rect.x+1,rect.y+1,rect.width-2,rect.height-2,true);
		selectColor(Color.BLACK);
		drawRectangle(rect.x,rect.y,rect.width,rect.height,false);
	}

	public void drawPageRegion(int regname,float x,float y,float w,float h,boolean penNormal,Color edition) {
		if (penNormal)
			selectDefinedPen(JrPenName.PEN_CADRE_NORMAL,true);
		else
			selectDefinedPen(JrPenName.PEN_CADRE_EPAIS,true);
		selectColor(colorBook.getPageRegionColor(regname));
		drawRectangle(x,y,w,h,true);
		selectColor(edition);
		drawRectangle(x,y,w,h,false);
	}
	
	public void drawRectangle(float x,float y,float w,float h,boolean fill) {
		tmpRect.setRect(x,y,w,h);
		if (fill == true)
			dc.fill(tmpRect);
		else
			dc.draw(tmpRect);
	}	
	
	public void drawCircle(Rectangle rect,boolean fill) {
		int w = ((rect.width < rect.height)? rect.width : rect.height);
		drawEllipse((int)rect.getCenterX()-(w/2),(int)rect.getCenterY()-(w/2),w,w,fill);	
	}

	public void drawCircle(float x,float y,float w,float h,boolean fill) {
		float pas = Math.min(w,h);
		float px  = x + (w/2.0f) - (pas/2.0f); 
		float py  = y + (h/2.0f) - (pas/2.0f); 
		drawEllipse(px,py,pas,pas,fill);
	}	
	
	public void drawEllipse(float x,float y,float w,float h,boolean fill) {
		tmpEllipse.setFrame(x,y,w,h);
		if (fill == true)
			dc.fill(tmpEllipse);	
		else
			dc.draw(tmpEllipse);	
	}
		
	public void drawTriangle(Rectangle rect) {
		int ox = (int)rect.getCenterX();
		int oy = (int)rect.getCenterY();		
		int w = ((rect.width < rect.height)? rect.width : rect.height) / 2;
		drawTriangle(ox,oy-w,ox+w,oy+w,ox-w,oy+w,false);
	}

	public void drawTriangle(float x,float y,float w,float h,boolean fill) {
		float ox = x + (w / 2.0f);
		float oy = y + (h / 2.0f);		
		float pa = Math.min(w,h) / 2.0f;
		tmpPath.reset();
		tmpPath.moveTo(ox,oy-pa);
		tmpPath.lineTo(ox+pa,oy+pa);
		tmpPath.lineTo(ox-pa,oy+pa);
		tmpPath.closePath();
		if (fill == true) 
			dc.fill(tmpPath);
		else 
			dc.draw(tmpPath);
	}
	public void drawTriangle(float x1,float y1,float x2,float y2,float x3,float y3,boolean fill) {
		tmpPath.reset();
		tmpPath.moveTo(x1,y1);
		tmpPath.lineTo(x2,y2);
		tmpPath.lineTo(x3,y3);
		tmpPath.closePath();
		if (fill == true) 
			dc.fill(tmpPath);
		else 
			dc.draw(tmpPath);
	}

	
	public void drawPolygon(float ptx[],float pty[],int nb, boolean fill) {
		tmpPath.reset();
		tmpPath.moveTo(ptx[0],pty[0]);
		for(int i = 1; i < nb; i++)
			tmpPath.lineTo(ptx[i],pty[i]);
		if (fill == true)
			dc.fill(tmpPath);
		else
			dc.draw(tmpPath);
	}
	
	public void drawLine(float x1,float y1,float x2,float y2) {
		tmpLine.setLine(x1,y1,x2,y2);
		dc.draw(tmpLine);	
		//dc.drawLine(x1,y1,x2,y2);
	}
	
	public void drawArc(int x,int y,int w,int h,int start,int amplitude) {
		dc.drawArc(x,y,w,h,start,amplitude);
	}
	
	public void drawArc(float x,float y,float w,float h,int start,int amplitude) {
		tmpArc.setArc(x,y,w,h,(float)start,(float)amplitude,Arc2D.OPEN);
		dc.draw(tmpArc);
	}
	
	public void drawShape(Shape sp) {
		dc.draw(sp);
	}
	
	public void drawImage(ImageIcon img,float x,float y,float w,float h,
				int luminosite,boolean adjust,boolean active) {
		float wDisp = w;
		float wImg  = img.getIconWidth();
		float hImg  = img.getIconHeight();
		double reduce = ((double)wImg) / ((double)wDisp);
		float hDisp = (float)(((double)hImg) / reduce);

		if (adjust == true) {
			hDisp = h;
		}
		else {
			if (hDisp > h) {
				hDisp = h;
				reduce = ((double)hImg) / ((double)hDisp);
				wDisp = (float)(((double)wImg) / reduce);
			}
		}
		
		hDisp = (adjust == true)? h : hDisp;
		
        Shape oldClip = setClip(x,y,w,h);

        if((luminosite == 100) && (active == true)) {
			dc.drawImage(img.getImage(),(int)x,(int)y,(int)(x+wDisp),(int)(y+hDisp),0,0,img.getIconWidth(),
					img.getIconHeight(),img.getImageObserver());        	
        }
        else {
			BufferedImage buffImg = makeBufferedImage(img.getImage(),
					BufferedImage.TYPE_INT_RGB,img.getIconWidth(),img.getIconHeight());
			if (buffImg != null) {
				BufferedImage opImg = buffImg;
				if (luminosite != 100) {
					BufferedImageOp op = new RescaleOp(((float)luminosite)/100.0f, 0, null);;
					opImg = op.filter(buffImg,null);
				}
				if (active) {
					dc.drawImage(opImg,(int)x,(int)y,(int)(x+wDisp),(int)(y+hDisp),0,0,img.getIconWidth(),
							img.getIconHeight(),img.getImageObserver());  				
				}
	        	else {
	        		dc.drawImage(redFilter.filter(opImg,null),(int)x,(int)y,(int)(x+wDisp),(int)(y+hDisp),0,0,img.getIconWidth(),
	        				img.getIconHeight(),img.getImageObserver());
	        	}
			}
        }        
        restoreClip(oldClip);		
	}
	
	public BufferedImage makeBufferedImage(Image image, int imageType,int w,int h) {
		BufferedImage bufferedImage = null;
		if ((w > 0) && (h > 0)) {
			bufferedImage = new BufferedImage(w,h,imageType);
			Graphics2D g2 = bufferedImage.createGraphics();
			g2.drawImage(image, null, null);
		}
		return bufferedImage;
	}
	
	public Shape setClip(int rectname) {
		Rectangle rect = regions.get(rectname);
		return setClip((float)rect.x,(float)rect.y,(float)rect.width,(float)rect.height);
	}
	
	public Shape setClip(float x,float y,float w,float h) {
		Shape shape = dc.getClip();
		clipRect.setRect(x,y,w,h);
		dc.setClip(clipRect);
		return shape;
	}
	public void restoreClip(Shape oldClip) {
        dc.setClip(oldClip);				
	}
	
	public boolean isThisRegion(int x,int y,int rectname) {
		return (regions.whichRegion(x,y) == rectname)? true : false;
	}
	
	private LookupOp createRedFilter() {
		short[] ident = new short[256];
		short[] zero = new short[256];
		for (int i = 0; i < 256; i++) {
		ident[i] = (short)i;
		zero[i] = (short)0;
		}
		zero[255] = 255;
		ident[0] = 255;
		short[][] avecRouge= { ident, zero, zero };
		LookupTable table = new ShortLookupTable(0, avecRouge);
		return new LookupOp(table,null);		
	}
	
	public void setCurrentFontSize(int name) {
		currentFontSize = name;
	}
}
