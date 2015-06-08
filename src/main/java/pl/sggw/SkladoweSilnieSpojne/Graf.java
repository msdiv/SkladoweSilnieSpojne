package pl.sggw.SkladoweSilnieSpojne;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxRectangleShape;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxVertexHandler;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class Graf extends mxGraph {

	private int index = 1;

	public Graf() {
	}

	public void init(JFrame okno) {

		setDisconnectOnMove(false);
//		setAllowLoops(true);
		setAllowDanglingEdges(false);
		setCellsBendable(true);
		setDropEnabled(false);
		setCellsResizable(false);

		
//		@Override
//		mxVertexHandler.createSelectionShape = function(bounds){var shape = new mxRectangleShape(bounds, null, this.getSelectionColor());shape.strokewidth = this.getSelectionStrokeWidth();shape.isDashed = this.isSelectionDashed();shape.isRounded = this.state.style[mxConstants.STYLE_ROUNDED] == 1;return shape;};

		final mxGraphComponent rysowanie = new mxGraphComponent(this);
		okno.add(rysowanie, BorderLayout.CENTER);

		rysowanie.getGraphControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Object objCell = rysowanie.getCellAt(e.getX(), e.getY());

				if (e.getButton() == MouseEvent.BUTTON3) { // prawy przycisk
					if (objCell == null)
						createVertex(e.getX(), e.getY()); // tworzy nowy
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
																	// elementy
				if (cell != null && cell.isEdge()) {
					removeSelectionCell(cell); // usuwa zaznaczenie

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
		mxCell cell = (mxCell) insertVertex(getDefaultParent(), "asd" + index,
				"" + index, x - size / 2, y - size / 2, 40, 40,
				"shape=ellipse;" + "whiteSpace=wrap;" + "fillColor=#c3d1ea;"
						+ "strokeColor=c3d1ea;" + "fontColor=#6482b9;"
						+ "fontSize=16;" + "fontStyle=1");
		index++;

		// if (sidePanel.getCreateEdges().isSelected())
		// connectWithOtherVertices(cell);

		// mxCircleLayout layout = new mxCircleLayout(this);
		// layout.execute(getDefaultParent());
	}
	public void wyczyscGraf(){
		selectAll();
		removeCells(getSelectionCells());
		index=1;
	}
}
