import javax.swing.JSlider;

public class GraphicComponent extends JSlider implements RangeSlider {
	protected DefaultRangeSlider model;

	private int low;
	private int high;
	private int max;
	private int min;

	// protected ChangeEvent changeevent;
	// protected EventListenerList eventListenerList;

	public GraphicComponent(int min, int max, int low, int high) {
		this.low = low;
		this.high = high;
		this.max = max;
		this.min = min;

		setUI(new BasicUI(this));
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

}
