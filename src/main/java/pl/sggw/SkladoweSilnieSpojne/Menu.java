package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	private JPanel panel;
	private JButton find;
	private JButton clear;
	// private JCheckBox oriented;
	private JLabel resultText;
	private JLabel result;

	public Menu() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1)); // wiersze,kolumny,odległości
													// między guzikami
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //trzeba dodać maximum size
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Menu"));
		find = new JButton("Znajdź");
		find.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(find);

		clear = new JButton("Wyczyść");
		clear.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(clear);

		resultText = new JLabel("Składowe silnie spójne:");
		resultText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(resultText);
		//resultText.hide();

		result = new JLabel(" "); // wynik przesyłany jako tablica?
		
		add(panel);
	}
	public void clearResult() {
		result.setText(" ");
	}
}
