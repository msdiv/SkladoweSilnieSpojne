package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Graf extends mxGraph {

	private int index = 1;

	public Graf(){
	}
	
	public void init(JFrame okno){
		final mxGraphComponent rysowanie = new mxGraphComponent(this);
		okno.add(rysowanie,BorderLayout.CENTER);
		
		rysowanie.getGraphControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Object objCell = rysowanie.getCellAt(e.getX(), e.getY());

				if (e.getButton() == MouseEvent.BUTTON3) { //prawy przycisk
					if (objCell == null)
						createVertex(e.getX(), e.getY()); //tworzy nowy
					else
						removeCells();
				}
				if (objCell != null) { //to samo, tylko dla krawędzi
					mxCell cell = (mxCell) objCell;
					if (cell.isEdge())
						removeSelectionCell(cell);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mxCell cell = (mxCell) selectionModel.getCell(); //pobiera pierwszy zaznaczony elementy
				if (cell != null && cell.isEdge()) {
					removeSelectionCell(cell); //usuwa zaznaczenie

					for (Object obj : getOutgoingEdges(cell.getSource())) {
						mxCell out = (mxCell) obj;
						if (out != cell && out.getTarget() == cell.getTarget()) {
							getModel().remove(cell);
							return;
						}
					}

					for (Object obj : getOutgoingEdges(cell.getTarget())) {
						mxCell out = (mxCell) obj;
						if (out != cell && out.getTarget() == cell.getSource()) {
							getModel().remove(cell);
							return;
						}
					}
				}
			}
		});
	}
	private void createVertex(int x, int y) {
		int size = 30;
//		mxCell cell = (mxCell) insertVertex(getDefaultParent(), "asd" + index, "" + index, x - size / 2, y - size / 2, size, size);
		mxCell cell = (mxCell) insertVertex(getDefaultParent(), "asd" + index, "" + index, x - size / 2, y - size / 2, size, size,"shape=ellipse;perimeter=100;whiteSpace=wrap;fillColor=lightgray");
		index++;

//		if (sidePanel.getCreateEdges().isSelected())
//			connectWithOtherVertices(cell);

//		 mxCircleLayout layout = new mxCircleLayout(this);
//		 layout.execute(getDefaultParent());
	}
}
