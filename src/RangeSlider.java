import javax.swing.JSlider;

public class RangeSlider extends JSlider implements RangeSliderInterface {

	private int low;
	private int high;
	private int max;
	private int min;
	private Listen l;

	// protected ChangeEvent changeevent;
	// protected EventListenerList eventListenerList;

	public RangeSlider(int min, int max, int low, int high) {
		this.low = low;
		this.high = high;
		this.max = max;
		this.min = min;

		setUI(new BasicUI(this));
	}

	public void addListen(Listen l) {
		this.l = l;
		this.l.updateMap(low, high);
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
		if (l != null) {
			l.updateMap(low, high);
		}
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
		if (l != null) {
			l.updateMap(low, high);
		}
	}

	public int getMaximum() {
		return max;
	}

	public void setMaximum(int max) {
		this.max = max;
	}

	public int getMinimum() {
		return min;
	}

	public void setMinimum(int min) {
		this.min = min;
	}

}
