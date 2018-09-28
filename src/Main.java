import javax.swing.JFrame;

public class Main extends JFrame {

	public Main() {
		setTitle("RangeSlider");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.add(new GraphicComponent(0, 10, 0, 10));
		main.setVisible(true);
	}

}
