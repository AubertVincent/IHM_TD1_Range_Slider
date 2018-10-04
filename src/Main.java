import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Main extends JFrame {

	public Main() {
		setTitle("RangeSlider");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main main = new Main();
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		Map map = new Map(250, 250, 20);
		split.setLeftComponent(map);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JLabel lab = new JLabel("Nombre de pièces");
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(lab,c);
		RangeSlider pieces = new RangeSlider(1, 4, 1, 4);
		Listen listenRooms = new Listen() {

			@Override
			public void updateMap(int low, int high) {
				map.sliderStateRooms(low, high);
				map.repaint();
			}
		};
		pieces.addListen(listenRooms);
		
		c.gridy = 1;
		c.ipady = 50;
		panel.add(pieces,c);
		JLabel lab2 = new JLabel("Valeur des maisons");
		c.gridy = 2;
		panel.add(lab2,c);
		
		RangeSlider valeur = new RangeSlider(30000, 150000, 30000, 150000);
		Listen listenValue = new Listen() {

			@Override
			public void updateMap(int low, int high) {
				map.sliderStateValue(low, high);
				map.repaint();
			}
		};
		valeur.addListen(listenValue);
		
		c.gridy = 3;
		panel.add(valeur,c);

		split.setRightComponent(panel);
		split.setDividerLocation(350);
		main.add(split);
		main.setVisible(true);
	}

}
