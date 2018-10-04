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

	/**
	 * 
	 * @param width width of the map
	 * @param height height of the map
	 * @param nb_home the number of home to initialize
	 */
	public Map(int width, int height, int nb_home) {

		this.width = width;
		this.height = height;
		setRandomHomes(nb_home);
	}

	/**
	 * Update the high and low values for the home values comparison
	 * @param low 
	 * @param high
	 */
	public void sliderStateValue(int low, int high) {
		this.valueHigh = high;
		this.valueLow = low;
	}

	/**
	 * Update the high and low values for the home number of rooms comparison
	 * @param low
	 * @param high
	 */
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

	/**
	 * Paint every home that matches the conditions
	 * @param g
	 */
	private void paintHomes(Graphics g) {
		g.setColor(Color.RED);
		for (Home home : this.homes) {
			if (home.getNb_room() >= roomLow && home.getNb_room() <= roomHigh && home.getValue() <= valueHigh
					&& home.getValue() >= valueLow) {
				g.fillOval(home.getLat(), home.getLon(), 4, 4);
			}
		}
	}

	/**
	 * Initialize the list of home
	 * @param size the size of the list
	 */
	public void setRandomHomes(int size) {
		this.homes = new ArrayList<Home>();
		for (int i = 0; i < size; i++) {
			homes.add(new Home((int) (Math.random() * (width - 4)), (int) (Math.random() * (height - 4)),
					(int) (Math.random() * 4) + 1, (int) (Math.random() * 120000) + 30000));
		}
	}

}
