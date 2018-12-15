package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import model.Matrix;

public class PipeGameDrawer extends Canvas{
	
	Matrix matrix;
	
	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
		redraw();
	}

	public void setChange(double x, double y) {
		double W = getWidth();
		double H = getHeight();
		
		double w = W / this.matrix.getCols();
		double h = H / this.matrix.getRows();
		
		int j = (int)(x/w);
		int i = (int)(y/h);
		
		drawTurn(i, j);
	}

	public void drawTurn(int i, int j) {
		this.matrix.turn(i, j);
		redraw();
	}

	public void redraw(){
		double W = getWidth();
		double H = getHeight();
		
		double w = W / this.matrix.getCols();
		double h = W / this.matrix.getRows();
		
		char[][] level = matrix.getMatrix();
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, W, H);
		
		for (int i=0; i<matrix.getRows(); i++)
			for (int j=0; j<matrix.getCols(); j++)
			{
				if (level[i][j] == '-') {
					gc.fillRect(j*w, (i*h) + (h/4), w, h - (h/2));
				}
				else if (level[i][j] == '|') {
					gc.fillRect(j*w + (w/4), (i*h), w - (w/2), h);
				}
				else if (level[i][j] == 'L'){
					gc.fillRect(j*w + (w/4), i*h, w - (w/2), h/4);
					gc.fillRect(j*w + (w/4), i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 'F'){
					gc.fillRect(j*w + (w/4), i*h + 3*h/4, w - (w/2), h/4);
					gc.fillRect(j*w + (w/4), i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == '7'){
					gc.fillRect(j*w + (w/4), i*h + 3*h/4, w - (w/2), h/4);
					gc.fillRect(j*w, i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 'J'){
					gc.fillRect(j*w + (w/4), i*h, w - (w/2), h/4);
					gc.fillRect(j*w, i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 's'){
					gc.fillRoundRect(j*w + (w/4), i*h + h/4, w/2, h/2,  W/4, H/4);
				}
				else if (level[i][j] == 'g'){
					gc.fillRoundRect(j*w + (w/4), i*h + h/4, w/2, h/2, W/4, H/4);
				}
			}
	}
}
