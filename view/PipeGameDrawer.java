package view;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import model.Matrix;

public class PipeGameDrawer extends Canvas{
	
	SimpleObjectProperty<Matrix> charMatrix;
	
public PipeGameDrawer() {
		super();
		this.charMatrix = new SimpleObjectProperty<Matrix>();

	}

//	public Matrix getMatrix() {
//		return charMatrix;
//	}
//
//	public void setMatrix(Matrix matrix) {
//		this.charMatrix = matrix;
//		redraw();
//	}
	


	
	public void setChange(double x, double y) {
		double W = getWidth();
		double H = getHeight();
		
		double w = W / this.charMatrix.get().getCols();
		double h = H / this.charMatrix.get().getRows();
		
		int j = (int)(x/w);
		int i = (int)(y/h);
		
		drawTurn(i, j);
	}
	
	public int getColFromX(double x) {
		double W = getWidth();	
		double w = W / this.charMatrix.get().getCols();
		
		return (int)(x/w);
	}
	
	public int getRowFromY(double y) {
		double H = getHeight();
		double h = H / this.charMatrix.get().getRows();
		
		return (int)(y/h);
	}

	public void drawTurn(int i, int j) {
		this.charMatrix.get().turn(i, j);
		redraw();
	}

	public void redraw(){
		double W = getWidth();
		double H = getHeight();
		
		double w = W / this.charMatrix.get().getCols();
		double h = W / this.charMatrix.get().getRows();
		
		char[][] level = charMatrix.get().getMatrix();
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, W, H);
		
		for (int i=0; i<charMatrix.get().getRows(); i++)
			for (int j=0; j<charMatrix.get().getCols(); j++)
			{
				if (level[i][j] == '-') {
					gc.drawImage(new Image("/view/resources/dark/-pipe.jpg"), j*w, (i*h) + (h/4), w, h - (h/2) );
					//gc.fillRect(j*w, (i*h) + (h/4), w, h - (h/2));
				}
				else if (level[i][j] == '|') {
					gc.drawImage(new Image("/view/resources/dark/pipe.jpg"), j*w + (w/4), (i*h), w - (w/2), h );
					//gc.fillRect(j*w + (w/4), (i*h), w - (w/2), h);
				}
				else if (level[i][j] == 'L'){
					gc.drawImage(new Image("/view/resources/dark/Lpipe.jpg"), j*w, (i*h) + (h/4), w, h - (h/2) );
//					gc.fillRect(j*w + (w/4), i*h, w - (w/2), h/4);
//					gc.fillRect(j*w + (w/4), i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 'F'){
					gc.drawImage(new Image("/view/resources/dark/Fpipe.jpg"), j*w, (i*h) + (h/4), w, h - (h/2));
//					gc.fillRect(j*w + (w/4), i*h + 3*h/4, w - (w/2), h/4);
//					gc.fillRect(j*w + (w/4), i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == '7'){
					gc.drawImage(new Image("/view/resources/dark/7pipe.jpg"),j*w, (i*h) + (h/4), w, h - (h/2) );
//					gc.fillRect(j*w + (w/4), i*h + 3*h/4, w - (w/2), h/4);
//					gc.fillRect(j*w, i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 'J'){
					gc.drawImage(new Image("/view/resources/dark/7pipe.jpg"), j*w, (i*h) + (h/4), w, h - (h/2));
//					gc.fillRect(j*w + (w/4), i*h, w - (w/2), h/4);
//					gc.fillRect(j*w, i*h + h/4, w - (w/4), h/2);
				}
				else if (level[i][j] == 's'){
					gc.drawImage(new Image("/view/resources/start.jpg"), j*w, (i*h) + (h/4), w, h - (h/2));
					//gc.fillRoundRect(j*w + (w/4), i*h + h/4, w/2, h/2,  W/4, H/4);
				}
				else if (level[i][j] == 'g'){
					gc.drawImage(new Image("/view/resources/goal.png"), j*w, (i*h) + (h/4), w, h - (h/2));
					//gc.fillRoundRect(j*w + (w/4), i*h + h/4, w/2, h/2, W/4, H/4);
				}
			}
	}
}
