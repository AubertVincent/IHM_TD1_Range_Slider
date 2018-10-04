import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Map extends JComponent {

	private ArrayList<Home> homes;

	private int width;
	private int height;

	private int valueHigh;
	private int valueLow;
	private int roomHigh;
	private int roomLow;

	public Map(int width, int height, int nb_home) {

		this.width = width;
		this.height = height;
		setRandomHomes(nb_home);
	}

	public void sliderStateValue(int low, int high) {
		this.valueHigh = high;
		this.valueLow = low;
	}

	public void sliderStateRooms(int low, int high) {
		this.roomHigh = high;
		this.roomLow = low;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.translate(50, 50);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		paintHomes(g);
	}

	private void paintHomes(Graphics g) {
		g.setColor(Color.RED);
		for (Home home : this.homes) {
			if (home.getNb_room() >= roomLow && home.getNb_room() <= roomHigh && home.getValue() <= valueHigh
					&& home.getValue() >= valueLow) {
				g.fillOval(home.getLat(), home.getLon(), 4, 4);
			}
		}
	}

	public void setRandomHomes(int size) {
		this.homes = new ArrayList<Home>();
		for (int i = 0; i < size; i++) {
			homes.add(new Home((int) (Math.random() * (width - 4)), (int) (Math.random() * (height - 4)),
					(int) (Math.random() * 4) + 1, (int) (Math.random() * 120000) + 30000));
		}
	}

}
