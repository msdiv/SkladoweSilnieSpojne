package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Graf extends mxGraph {

	private int indeks = 1;

	public Graf() {
	}

	public void init(JFrame okno) {

		setDisconnectOnMove(false);
		setAllowLoops(true);
		setAllowDanglingEdges(false);
		setCellsBendable(true);
		setDropEnabled(false);
		setCellsResizable(false);

		final mxGraphComponent graf = new mxGraphComponent(this);
		okno.add(graf, BorderLayout.CENTER);

		graf.getGraphControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Object objCell = graf.getCellAt(e.getX(), e.getY());

				if (e.getButton() == MouseEvent.BUTTON3) { // prawy przycisk
					if (objCell == null)
						utworzWierzcholek(e.getX(), e.getY()); // tworzy nowy
					else
						removeCells();
				}
				if (objCell != null) { // to samo, tylko dla krawędzi
					mxCell cell = (mxCell) objCell;
					if (cell.isEdge())
						removeSelectionCell(cell);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mxCell cell = (mxCell) selectionModel.getCell(); // pobiera
																	// pierwszy
																	// zaznaczony
																	// elementy?
				if (cell != null && cell.isEdge()) {
					removeSelectionCell(cell); // usuwa zaznaczenie

					for (Object obj : getOutgoingEdges(cell.getSource())) {
						mxCell out = (mxCell) obj;
						if (out != cell && out.getTarget() == cell.getTarget()) {
							getModel().remove(cell);
							return;
						}
					}
				}
			}
		});
	}

	private void utworzWierzcholek(int x, int y) {
		int size = 30;
		mxCell cell = (mxCell) insertVertex(getDefaultParent(), "asd" + indeks,
				"" + indeks, x - size / 2, y - size / 2, 40, 40,
				"shape=ellipse;" + "whiteSpace=wrap;" + "fillColor=#c3d1ea;"
						+ "strokeColor=c3d1ea;" + "fontColor=#6482b9;"
						+ "fontSize=16;" + "fontStyle=1");
		indeks++;
	}

	public void wyczyscGraf() {
		selectAll();
		removeCells(getSelectionCells());
		indeks = 1;
	}
}
