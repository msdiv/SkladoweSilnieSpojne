package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

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
		find = new JButton("Znajdź silnie spójne składowe");
		find.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(find);

		clear = new JButton("Wyczyść wszystko");
		clear.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(clear);

		resultText = new JLabel("Składowe silnie spójne:");
		resultText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(resultText);

		result = new JLabel(" "); // wynik przesyłany jako tablica?
		
		add(panel);
	}

}
