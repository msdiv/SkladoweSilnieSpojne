package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
//ctrl shift f wyrównanie
//ctrl shift o importowanie
//ctrl spacja podpowiedzi
//ctrl alt strzałka w dół powinno kopiować linijkę w dół

public class Grafy extends JFrame {

	private JPanel panel;
	private Menu menu;

	public Grafy() {
		super("Znajdowanie składowych silnie spójnych");
		setMinimumSize(new Dimension(700, 500));
		
		panel = new JPanel();
		menu = new Menu();
		add(menu);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Grafy();
			}
		});
	}

}
