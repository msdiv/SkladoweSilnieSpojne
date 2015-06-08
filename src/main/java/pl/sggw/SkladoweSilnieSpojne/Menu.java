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
	private JButton findButton;
	private JButton clearButton;
	// private JCheckBox oriented;
	private JLabel resultText;
	private JLabel result;

	public Menu() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1)); // wiersze,kolumny,odległości
													// między guzikami
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //trzeba dodać maximum size
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Menu"));
		findButton = new JButton("Znajdź");
		findButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(findButton);

		clearButton = new JButton("Wyczyść");
		clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(clearButton);

		resultText = new JLabel("Składowe silnie spójne:");
		resultText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(resultText);
		//resultText.hide();

		result = new JLabel(" "); // wynik przesyłany jako tablica?
		
		add(panel);
	}
	public JButton clickClear() {
		result.setText(" ");
		return clearButton;
	}
	public JButton clickFind() {
		return findButton;
	}
}
