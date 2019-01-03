package model;

import java.io.Serializable;

public class DataWrapper implements Serializable {


	private static final long serialVersionUID = -1058224548861665589L;
	/**
	 * A class to wrap pipeGameDrawer data (steps, playDuration and the board itself)
	 * In order to successfully serialize and deserialize it
	 */
	
	
	int steps = 0;	
	int playDuration = 0;
	Matrix board = null;
	
	
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public int getPlayDuration() {
		return playDuration;
	}
	public void setPlayDuration(int playDuration) {
		this.playDuration = playDuration;
	}
	public Matrix getMatrix() {
		return board;
	}
	public void setMatrix(Matrix matrix) {
		this.board = matrix;
	}
	
	
}
