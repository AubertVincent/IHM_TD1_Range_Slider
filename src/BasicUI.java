import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicSliderUI;

public class BasicUI extends BasicSliderUI {

	// public enum automate {
	// IDLE, LowSelected, HighSelected, DragLow, DragHigh;
	// }

	GraphicComponent rangeslider;

	protected Rectangle thumbRect2 = null;

	private int lastLow;
	private int lastHigh;

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

	public void paintThumbLow(Graphics g) {
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
			g.drawLine(1, 0, w - 2, 0);
			g.drawLine(0, 1, 0, h - 1 - cw);
			g.drawLine(0, h - cw, cw - 1, h - 1);

			g.setColor(Color.black);
			g.drawLine(w - 1, 0, w - 1, h - 2 - cw);
			g.drawLine(w - 1, h - 1 - cw, w - 1 - cw, h - 1);

			g.setColor(getShadowColor());
			g.drawLine(w - 2, 0 + 1, w - 2, h - 2 - cw);
			g.drawLine(w - 2, h - 1 - cw, w - 1 - cw, h - 2);
		}

		g.translate(-knobBounds.x, -knobBounds.y);
	}

	public void paintThumbHigh(Graphics g) {
		Rectangle knobBounds = thumbRect2;
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
			g.drawLine(1, 0, w - 2, 0);
			g.drawLine(0, 1, 0, h - 1 - cw);
			g.drawLine(0, h - cw, cw - 1, h - 1);

			g.setColor(Color.black);
			g.drawLine(w - 1, 0, w - 1, h - 2 - cw);
			g.drawLine(w - 1, h - 1 - cw, w - 1 - cw, h - 1);

			g.setColor(getShadowColor());
			g.drawLine(w - 2, 0 + 1, w - 2, h - 2 - cw);
			g.drawLine(w - 2, h - 1 - cw, w - 1 - cw, h - 2);
		}

		g.translate(-knobBounds.x, -knobBounds.y);
	}

	@Override
	public void installUI(JComponent c) {
		slider = (JSlider) c;

		// checkedLabelBaselines = false;

		slider.setEnabled(slider.isEnabled());
		LookAndFeel.installProperty(slider, "opaque", Boolean.TRUE);

		// isDragging = false;
		trackListener = createTrackListener(slider);
		changeListener = createChangeListener(slider);
		componentListener = createComponentListener(slider);
		focusListener = createFocusListener(slider);
		scrollListener = createScrollListener(slider);
		propertyChangeListener = createPropertyChangeListener(slider);

		installDefaults(slider);
		installListeners(slider);
		installKeyboardActions(slider);

		scrollTimer = new Timer(100, scrollListener);
		scrollTimer.setInitialDelay(300);

		insetCache = slider.getInsets();
		// leftToRightCache = BasicGraphicsUtils.isLeftToRight(slider);
		focusRect = new Rectangle();
		contentRect = new Rectangle();
		labelRect = new Rectangle();
		tickRect = new Rectangle();
		trackRect = new Rectangle();
		thumbRect = new Rectangle();
		thumbRect2 = new Rectangle();
		// lastValue = slider.getValue();

		calculateGeometry(); // This figures out where the labels, ticks, track,
								// and thumb are.
	}

	@Override
	public void uninstallUI(JComponent c) {
		if (c != slider)
			throw new IllegalComponentStateException(
					this + " was asked to deinstall() " + c + " when it only knows about " + slider + ".");

		scrollTimer.stop();
		scrollTimer = null;

		uninstallDefaults(slider);
		uninstallListeners(slider);
		uninstallKeyboardActions(slider);

		insetCache = null;
		leftToRightCache = true;
		focusRect = null;
		contentRect = null;
		labelRect = null;
		tickRect = null;
		trackRect = null;
		thumbRect = null;
		thumbRect2 = null;
		trackListener = null;
		changeListener = null;
		componentListener = null;
		focusListener = null;
		scrollListener = null;
		propertyChangeListener = null;
		slider = null;
	}

	@Override
	protected void calculateThumbSize() {
		Dimension size = getThumbSize();
		thumbRect.setSize(size.width, size.height);
		thumbRect2.setSize(size.width, size.height);
	}

	// @Override
	// protected void calculateThumbLocation() {
	// if (slider.getSnapToTicks()) {
	// int sliderValue = slider.getValue();
	// int snappedValue = sliderValue;
	// int tickSpacing = getTickSpacing();
	//
	// if (tickSpacing != 0) {
	// // If it's not on a tick, change the value
	// if ((sliderValue - slider.getMinimum()) % tickSpacing != 0) {
	// float temp = (float) (sliderValue - slider.getMinimum()) / (float)
	// tickSpacing;
	// int whichTick = Math.round(temp);
	//
	// // This is the fix for the bug #6401380
	// if (temp - (int) temp == .5 && sliderValue < lastValue) {
	// whichTick--;
	// }
	// snappedValue = slider.getMinimum() + (whichTick * tickSpacing);
	// }
	//
	// if (snappedValue != sliderValue) {
	// slider.setValue(snappedValue);
	// }
	// }
	// }
	//
	// if (slider.getOrientation() == JSlider.HORIZONTAL) {
	// int valuePosition = xPositionForValue(slider.getValue());
	//
	// thumbRect.x = valuePosition - (thumbRect.width / 2);
	// thumbRect.y = trackRect.y;
	// } else {
	// int valuePosition = yPositionForValue(slider.getValue());
	//
	// thumbRect.x = trackRect.x;
	// thumbRect.y = valuePosition - (thumbRect.height / 2);
	// }
	// }
}
