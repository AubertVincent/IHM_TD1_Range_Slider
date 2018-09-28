import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicSliderUI;

public class BasicUI extends BasicSliderUI {

	// public enum automate {
	// IDLE, LowSelected, HighSelected, DragLow, DragHigh;
	// }

	private RangeSlider slider;
	private Rectangle thumbRectLow, thumbRectHigh;

	public BasicUI(RangeSlider slider) {
		super(slider);
		this.slider = slider;
		BasicTrackListener tracker = new BasicTrackListener();
		slider.addMouseListener(tracker);
		slider.addMouseMotionListener(tracker);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		recalculateIfInsetsChanged();
		recalculateIfOrientationChanged();
		Rectangle clip = g.getClipBounds();

		if (!clip.intersects(trackRect) && slider.getPaintTrack())
			calculateGeometry();

		if (slider.getPaintTrack() && clip.intersects(trackRect)) {
			paintTrack(g);
		}
		if (slider.getPaintTicks() && clip.intersects(tickRect)) {
			paintTicks(g);
		}
		if (slider.getPaintLabels() && clip.intersects(labelRect)) {
			paintLabels(g);
		}
		if (slider.hasFocus() && clip.intersects(focusRect)) {
			paintFocus(g);
		}
		if (clip.intersects(thumbRect)) {
			paintThumb(g);
		}
	}

	public void paintThumb(Graphics g) {

		if (thumbRectLow == null) {
			int value = slider.getLow();
			int xvalue = xPositionForValue(value);
			int yvalue = trackRect.y;
			thumbRectLow = new Rectangle(xvalue, yvalue, 11, 20);
		}
		if (thumbRectHigh == null) {
			int value = slider.getHigh();
			int xvalue = xPositionForValue(value);
			int yvalue = trackRect.y;
			thumbRectHigh = new Rectangle(xvalue, yvalue, 11, 20);
		}

		Rectangle knobBounds = thumbRectLow;
		int w = knobBounds.width;
		int h = knobBounds.height;

		g.translate(knobBounds.x, knobBounds.y);

		if (slider.isEnabled()) {
			g.setColor(slider.getBackground());
		} else {
			g.setColor(slider.getBackground().darker());
		}

		int cw = w / 2;
		g.fillRect(1, 1, w - 3, h - 1 - cw);
		Polygon p = new Polygon();
		p.addPoint(1, h - cw);
		p.addPoint(cw - 1, h - 1);
		p.addPoint(w - 2, h - 1 - cw);
		g.fillPolygon(p);

		g.setColor(getHighlightColor());
		g.drawLine(1, 0, w - 2, 0);
		g.drawLine(0, 1, 0, h - 1 - cw);
		g.drawLine(0, h - cw, cw - 1, h - 1);

		g.setColor(Color.black);
		g.drawLine(w - 1, 0, w - 1, h - 2 - cw);
		g.drawLine(w - 1, h - 1 - cw, w - 1 - cw, h - 1);

		g.setColor(getShadowColor());
		g.drawLine(w - 2, 0 + 1, w - 2, h - 2 - cw);
		g.drawLine(w - 2, h - 1 - cw, w - 1 - cw, h - 2);

		g.translate(-knobBounds.x, -knobBounds.y);
		
		
		knobBounds = thumbRectHigh;
		w = knobBounds.width;
		h = knobBounds.height;

		g.translate(knobBounds.x, knobBounds.y);

		if (slider.isEnabled()) {
			g.setColor(slider.getBackground());
		} else {
			g.setColor(slider.getBackground().darker());
		}

		cw = w / 2;
		g.fillRect(1, 1, w - 3, h - 1 - cw);
		p = new Polygon();
		p.addPoint(1, h - cw);
		p.addPoint(cw - 1, h - 1);
		p.addPoint(w - 2, h - 1 - cw);
		g.fillPolygon(p);

		g.setColor(getHighlightColor());
		g.drawLine(1, 0, w - 2, 0);
		g.drawLine(0, 1, 0, h - 1 - cw);
		g.drawLine(0, h - cw, cw - 1, h - 1);

		g.setColor(Color.black);
		g.drawLine(w - 1, 0, w - 1, h - 2 - cw);
		g.drawLine(w - 1, h - 1 - cw, w - 1 - cw, h - 1);

		g.setColor(getShadowColor());
		g.drawLine(w - 2, 0 + 1, w - 2, h - 2 - cw);
		g.drawLine(w - 2, h - 1 - cw, w - 1 - cw, h - 2);

		g.translate(-knobBounds.x, -knobBounds.y);
	}

	@Override
	protected int xPositionForValue(int value) {
		int min = slider.getMinimum();
		int max = slider.getMaximum();
		int trackLength = trackRect.width;
		double valueRange = (double) max - (double) min;
		double pixelsPerValue = (double) trackLength / valueRange;
		int trackLeft = trackRect.x;
		int trackRight = trackRect.x + (trackRect.width - 1);
		int xPosition;

		if (!drawInverted()) {
			xPosition = trackLeft;
			xPosition += Math.round(pixelsPerValue * ((double) value - min));
		} else {
			xPosition = trackRight;
			xPosition -= Math.round(pixelsPerValue * ((double) value - min));
		}

		xPosition = Math.max(trackLeft, xPosition);
		xPosition = Math.min(trackRight, xPosition);

		return xPosition;
	}
	
	
	
	public class BasicTrackListener extends MouseInputAdapter{
		
		int lastx;
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(thumbRectLow.contains(e.getPoint())) {
				lastx = thumbRectLow.x;
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
			thumbRectLow.x = e.getX();
			slider.repaint();
		}
	}

}
