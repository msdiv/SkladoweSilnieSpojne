package pl.sggw.SkladoweSilnieSpojne;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
		panel.setSize(300, 500);
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

		result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
		result.setBorder(BorderFactory.createEmptyBorder());

		add(panel);
	}

	public JButton clickClear() {
		result.removeAll();
		return clearButton;
	}

	public JButton clickFind() {
		return findButton;
	}

	public void updateResult(List<Set<String>> stronglyConnectedSets) { //wynik przekazywany jako lista setów stringów

		Iterator<Set<String>> i = stronglyConnectedSets.iterator();
		result.removeAll(); //czyszczenie result
		
		JLabel l = new JLabel(" ");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		while (i.hasNext()) {
			Set<String> set = (Set<String>) i.next();
			JLabel label = new JLabel("");
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			String wynik = "hgfjghfh";

			panel.add(label);
			for (String wierzcholek : set) { //pętla foreach, ":" jest odpowiednikiem "in"
				wynik += wierzcholek + ", ";
			}			
			label.setText(wynik);
			result.add(label);
			result.add(l);
			result.revalidate();
//			result.updateUI();
			System.out.println(wynik);
		}
		panel.revalidate(); //odświeżenie menu
	}
}
