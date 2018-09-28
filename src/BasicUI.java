import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class BasicUI extends BasicSliderUI {

	// public enum automate {
	// IDLE, LowSelected, HighSelected, DragLow, DragHigh;
	// }

	GraphicComponent rangeslider;

	public BasicUI(GraphicComponent slider) {
		super(slider);
		slider.putClientProperty("Slider.paintThumbArrowShape", Boolean.TRUE);
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

	@Override
	public void paintThumb(Graphics g) {
		Rectangle knobBounds = thumbRect;
		int w = knobBounds.width;
		int h = knobBounds.height;

		g.translate(knobBounds.x, knobBounds.y);

		if (slider.isEnabled()) {
			g.setColor(slider.getBackground());
		} else {
			g.setColor(slider.getBackground().darker());
		}

		Boolean paintThumbArrowShape = (Boolean) slider.getClientProperty("Slider.paintThumbArrowShape");

		if ((!slider.getPaintTicks() && paintThumbArrowShape == null) || paintThumbArrowShape == Boolean.FALSE) {

			// "plain" version
			g.fillRect(0, 0, w, h);

			g.setColor(Color.black);
			g.drawLine(0, h - 1, w - 1, h - 1);
			g.drawLine(w - 1, 0, w - 1, h - 1);

			g.setColor(getHighlightColor());
			g.drawLine(0, 0, 0, h - 2);
			g.drawLine(1, 0, w - 2, 0);

			g.setColor(getShadowColor());
			g.drawLine(1, h - 2, w - 2, h - 2);
			g.drawLine(w - 2, 1, w - 2, h - 3);
		} else if (slider.getOrientation() == JSlider.HORIZONTAL) {
			int cw = w / 2;
			g.fillRect(1, 1, w - 3, h - 1 - cw);
			Polygon p = new Polygon();
			p.addPoint(1, h - cw);
			p.addPoint(cw - 1, h - 1);
			p.addPoint(w - 2, h - 1 - cw);
			g.fillPolygon(p);

			g.setColor(getHighlightColor());
			g.drawLine(0, 0, w - 2, 0);
			g.drawLine(0, 1, 0, h - 1 - cw);
			g.drawLine(0, h - cw, cw - 1, h - 1);

			g.setColor(Color.black);
			g.drawLine(w - 1, 0, w - 1, h - 2 - cw);
			g.drawLine(w - 1, h - 1 - cw, w - 1 - cw, h - 1);

			g.setColor(getShadowColor());
			g.drawLine(w - 2, 1, w - 2, h - 2 - cw);
			g.drawLine(w - 2, h - 1 - cw, w - 1 - cw, h - 2);
		}

		g.translate(-knobBounds.x, -knobBounds.y);
	}

}
