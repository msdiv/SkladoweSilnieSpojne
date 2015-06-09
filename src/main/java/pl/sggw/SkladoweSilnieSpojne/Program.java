package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

import javax.swing.JFrame;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
//ctrl shift f wyrównanie
//ctrl shift o importowanie
//ctrl spacja podpowiedzi
//ctrl alt strzałka w dół powinno kopiować linijkę w dół

public class Program extends JFrame {

	private Menu menu;
	private Graf graf;
	public List silnieSpojneSkladowe;

	public Program() {
		super("Znajdowanie składowych silnie spójnych");

		setMinimumSize(new Dimension(700, 500));
		setLayout(new BorderLayout());
		Dimension rozmiar = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(rozmiar.width / 2 - getSize().width / 2, rozmiar.height / 2
				- getSize().height / 2);

		menu = new Menu();
		add(menu, BorderLayout.EAST);

		menu.clickFind().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				znajdzSkladowe();
			}
		});

		menu.klikniecieWyczysc().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				czysc();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		graf = new Graf();
		graf.init(this);
	}

	protected void znajdzSkladowe() { // znajdowanie składowych silnie spójnych
		DirectedGraph<String, DefaultEdge> grafZorientowany = new DefaultDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);
		StrongConnectivityInspector testerSpojnosci = new StrongConnectivityInspector<String, DefaultEdge>(
				grafZorientowany);

		graf.selectCells(true, false); // zaznaczam wszystkie wierzchołki, żęby
										// je potem pobrać
		for (Object wierzcholek : graf.getSelectionCells()) {
			grafZorientowany.addVertex((String) ((mxCell) wierzcholek)
					.getValue());
		}

		graf.selectCells(false, true); // to samo dla krawędzi
		for (Object krawedz : graf.getSelectionCells()) {

			mxICell z = ((mxCell) krawedz).getSource(); // źródło i ujście
														// każdej
														// krawędzi
			mxICell u = ((mxCell) krawedz).getTarget();

			String zrodlo = (String) z.getValue();
			String ujscie = (String) u.getValue();
			grafZorientowany.addEdge(zrodlo, ujscie);
		}
		graf.clearSelection();
		silnieSpojneSkladowe = testerSpojnosci.stronglyConnectedSets(); // cały
																		// algorytm
																		// w
																		// jednej
																		// linijce
																		// heh
		menu.updateResult(silnieSpojneSkladowe);
	};

	protected void czysc() {
		graf.wyczyscGraf();
		menu.klikniecieWyczysc();
		if (silnieSpojneSkladowe != null) {
			silnieSpojneSkladowe.clear();
		}
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
