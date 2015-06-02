package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
//ctrl shift f wyrównanie
//ctrl shift o importowanie
//ctrl spacja podpowiedzi
//ctrl alt strzałka w dół powinno kopiować linijkę w dół

import com.mxgraph.swing.mxGraphComponent;

public class Program extends JFrame {

	private Menu menu;
	private Graf graf;

	public Program() {
		super("Znajdowanie składowych silnie spójnych");
		setMinimumSize(new Dimension(700, 500));
		setLayout(new BorderLayout());
		
		menu = new Menu();
		add(menu,BorderLayout.EAST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		graf = new Graf();
		graf.init(this);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Program();
			}
		});
	}

}
