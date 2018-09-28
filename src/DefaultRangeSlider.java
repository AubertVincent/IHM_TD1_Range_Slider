import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class DefaultRangeSlider implements RangeSlider {

	private int low;
	private int high;
	private int max;
	private int min;

	protected ChangeEvent changeevent;
	protected EventListenerList eventListenerList;

	public DefaultRangeSlider(int low, int high, int max, int min) {
		super();
		this.low = low;
		this.high = high;
		this.max = max;
		this.min = min;
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

	@Override
	public void addChangeListener(ChangeListener x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChangeListener(ChangeListener x) {
		// TODO Auto-generated method stub

	}

}
