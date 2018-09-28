import javax.swing.JSlider;

public class GraphicComponent extends JSlider {
	protected DefaultRangeSlider model;

	public GraphicComponent(int min, int max, int low, int high) {
		model = new DefaultRangeSlider(low, high, max, min);

		setUI(new BasicUI(this));
	}
}
