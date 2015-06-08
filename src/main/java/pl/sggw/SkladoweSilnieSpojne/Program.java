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
	public List stronglyConnectedSets;

	public Program() {
		super("Znajdowanie składowych silnie spójnych");

		setMinimumSize(new Dimension(700, 500));
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		menu = new Menu();
		add(menu, BorderLayout.EAST);

		menu.clickFind().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				find();
				// menu.setComputeTime(System.currentTimeMillis() - time);
			}
		});
		
		menu.clickClear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				// menu.setComputeTime(System.currentTimeMillis() - time);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		graf = new Graf();
		graf.init(this);
	}

	protected void find() { //znajdowanie składowych silnie spójnych
		DirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);
		StrongConnectivityInspector connectivityInspector = new StrongConnectivityInspector<String, DefaultEdge>(
				directedGraph);

		graf.selectCells(true, false); //zaznaczam wszystkie wierzchołki, żęby je potem pobrać
		for (Object verticle : graf.getSelectionCells()) {
			directedGraph.addVertex((String) ((mxCell) verticle).getValue());
		}

		graf.selectCells(false, true); //to samo dla krawędzi
		for (Object edge : graf.getSelectionCells()) {

			mxICell s = ((mxCell) edge).getSource(); //źródło i ujście każdej krawędzi
			mxICell t = ((mxCell) edge).getTarget();

			String source = (String) s.getValue();
			String target = (String) t.getValue();
			directedGraph.addEdge(source, target);
		}
		graf.clearSelection();
		stronglyConnectedSets = connectivityInspector.stronglyConnectedSets(); //cały algorytm w jednej linijce heh
	};

	protected void clear() {
		graf.wyczyscGraf();
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
