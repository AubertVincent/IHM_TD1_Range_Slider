import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicSliderUI;

public class BasicUI extends BasicSliderUI {

	private enum States {
		IDLE, LOW_PRESSED, LOW_DRAGGED, HIGH_PRESSED, HIGH_DRAGGED;
	}

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
		} else {
			thumbRectLow.y = trackRect.y;
		}
		if (thumbRectHigh == null) {
			int value = slider.getHigh();
			int xvalue = xPositionForValue(value);
			int yvalue = trackRect.y;
			thumbRectHigh = new Rectangle(xvalue, yvalue, 11, 20);
		} else {
			thumbRectHigh.y = trackRect.y;
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

	private class BasicTrackListener extends MouseInputAdapter {

		private int lastx;
		private States current_state;

		public BasicTrackListener() {
			// TODO Auto-generated constructor stub
			current_state = States.IDLE;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			switch (current_state) {
			case IDLE:
				if (thumbRectLow.contains(e.getPoint())) {
					lastx = e.getX();
					current_state = States.LOW_PRESSED;
				} else if (thumbRectHigh.contains(e.getPoint())) {
					lastx = e.getX();
					current_state = States.HIGH_PRESSED;
				}
				break;
			default:
				break;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int next_position;
			switch (current_state) {
			case HIGH_PRESSED:
				current_state = States.HIGH_DRAGGED;
			case HIGH_DRAGGED:
				next_position = thumbRectHigh.x + e.getX() - lastx;
				if (next_position + thumbRectHigh.width <= trackRect.x + trackRect.width
						&& next_position > thumbRectLow.x + thumbRectLow.width) {
					thumbRectHigh.x = next_position;
					lastx = e.getX();
					int min = slider.getMinimum();
					int max = slider.getMaximum();
					int nb_values = max - min;
					int value = thumbRectHigh.x / (trackRect.width / nb_values) + min;
					slider.setHigh(value);
				} else if (next_position + thumbRectHigh.width > trackRect.x + trackRect.width) {
					thumbRectHigh.x  = trackRect.x + trackRect.width - thumbRectHigh.width;
				} else if (next_position <= thumbRectLow.x + thumbRectLow.width) {
					thumbRectHigh.x = thumbRectLow.x + thumbRectLow.width + 1;
				}
				slider.repaint();
				break;

			case LOW_PRESSED:
				current_state = States.LOW_DRAGGED;
			case LOW_DRAGGED:
				next_position = thumbRectLow.x + e.getX() - lastx;
				if (next_position >= trackRect.x && (next_position + thumbRectLow.width) < thumbRectHigh.x) {
					thumbRectLow.x = next_position;
					lastx = e.getX();

					int min = slider.getMinimum();
					int max = slider.getMaximum();
					int nb_values = max - min;
					int value = thumbRectLow.x / (trackRect.width / nb_values) + min;
					slider.setLow(value);
				} else if (next_position < trackRect.x) {
					thumbRectLow.x = trackRect.x;
				} else if ((next_position + thumbRectLow.width) >= thumbRectHigh.x) {
					thumbRectLow.x = thumbRectHigh.x - thumbRectLow.width - 1;
				}
				slider.repaint();

				break;
			default:
				break;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			switch (current_state) {
			case LOW_PRESSED:
				current_state = States.IDLE;
			case LOW_DRAGGED:
				current_state = States.IDLE;
			case HIGH_DRAGGED:
				current_state = States.IDLE;
			case HIGH_PRESSED:
				current_state = States.IDLE;
			default:
				break;
			}
		}
	}

}
