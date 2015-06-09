package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
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
	private JButton znajdzButton;
	private JButton wyczyscButton;
	private JLabel wynikTytul;
	private JPanel wynik;

	public Menu() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setSize(getWidth(), getHeight());
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Menu"));
		znajdzButton = new JButton("Znajdź");
		znajdzButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		znajdzButton.setSize(300, 100);
		znajdzButton.setMaximumSize(new Dimension(300, 100));
		panel.add(znajdzButton);

		wyczyscButton = new JButton("Wyczyść");
		wyczyscButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		wyczyscButton.setSize(300, 100);
		wyczyscButton.setMaximumSize(new Dimension(300, 100));
		panel.add(wyczyscButton);

		wynikTytul = new JLabel("Składowe silnie spójne:");
		wynikTytul.setAlignmentX(Component.CENTER_ALIGNMENT);
		wynikTytul.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(wynikTytul);

		wynik = new JPanel();
		wynik.setLayout(new BoxLayout(wynik, BoxLayout.Y_AXIS));
		wynik.setBorder(BorderFactory.createEmptyBorder());
		panel.add(wynik);

		add(panel);
	}

	public JButton klikniecieWyczysc() {
		this.wynik.removeAll();
		this.wynik.revalidate();
		this.panel.revalidate();
		return wyczyscButton;
	}

	public JButton clickFind() {
		return znajdzButton;
	}

	public void updateResult(List<Set<String>> stronglyConnectedSets) { // wynik
																		// przekazywany
																		// jako
																		// lista
																		// setów
																		// stringów

		Iterator<Set<String>> i = stronglyConnectedSets.iterator();
		wynik.removeAll(); // czyszczenie result

		JLabel l = new JLabel(" ");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);

		while (i.hasNext()) {
			Set<String> set = (Set<String>) i.next();
			JLabel label = new JLabel("");
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			String skladowa = "(";

			panel.add(label);
			for (String wierzcholek : set) { // pętla foreach, ":" jest
												// odpowiednikiem "in"
				skladowa += wierzcholek + ", ";
			}
			StringBuilder temp = new StringBuilder(skladowa); // zamiana
																// ostatniego
																// przecinka na
																// nawias
			temp.replace(skladowa.lastIndexOf(","),
					skladowa.lastIndexOf(",") + 1, "),");
			skladowa = temp.toString();
			label.setText(skladowa);
			wynik.add(label);
			wynik.add(l);
			wynik.revalidate();
		}
		panel.revalidate(); // odświeżenie menu
	}
}
