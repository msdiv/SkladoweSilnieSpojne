package pl.sggw.SkladoweSilnieSpojne;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

public class Menu extends JPanel {
	private JPanel panel;
	private JButton findButton;
	private JButton clearButton;
	// private JCheckBox oriented;
	private JLabel resultText;
	private JPanel result;

	public Menu() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1)); // wiersze,kolumny,odległości
												// między guzikami
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //trzeba
		// dodać maximum size
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Menu"));
		findButton = new JButton("Znajdź");
		findButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(findButton);

		clearButton = new JButton("Wyczyść");
		clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(clearButton);

		resultText = new JLabel("Składowe silnie spójne:");
		resultText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(resultText);
		// resultText.hide();

		result = new JPanel(); // wynik przesyłany jako tablica?

		add(panel);
	}

	public JButton clickClear() {
		result.removeAll();
		return clearButton;
	}

	public JButton clickFind() {
		return findButton;
	}

	public void updateResult(List<Set<String>> stronglyConnectedSets) {

		Iterator<Set<String>> i = stronglyConnectedSets.iterator();

		while (i.hasNext()) {
			Set<String> set = (Set<String>) i.next();
			JLabel label = new JLabel("");
			String wynik = "";
			label.setAlignmentX(Component.CENTER_ALIGNMENT);

			panel.add(label);
			for (String wierzcholek : set) {
				wynik += wierzcholek + ", ";
			}
			label.setText(wynik);
			result.add(label);
			result.revalidate();
//			result.updateUI();
			System.out.println(wynik);
		}
	}
}
