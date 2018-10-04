import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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
		split.setLeftComponent(new Map(250,250,20));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel lab = new JLabel("Nombre de pièces");
		panel.add(lab);
		RangeSlider pieces = new RangeSlider(1,4,1,4);
//		pieces.setPreferredSize(new Dimension(150,150));
//		pieces.setMinimumSize(new Dimension(50,150));
		panel.add(pieces);
		JLabel lab2 = new JLabel("Valeur des maisons");
		panel.add(lab2);
		RangeSlider valeur = new RangeSlider(30000,150000,30000,150000);
		valeur.setSize(new Dimension(200,150));
//		valeur.setPreferredSize(new Dimension(150,150));
//		valeur.setMinimumSize(new Dimension(150,150));
		panel.add(valeur);
		
		split.setRightComponent(panel);
		split.setDividerLocation(400);
		main.add(split);
		main.setVisible(true);
	}
	

}
