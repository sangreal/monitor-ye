package enn.monitor.ai.common;

public class AIMatrix {

	private double matrix[][] = null;
	
	public AIMatrix(int column, int row) {
		int i, j;
		
		matrix = new double[column][row];
		
		for (i = 0; i < column; ++i) {
			for (j = 0; j < row; ++j) {
				matrix[i][j] = 0.0;
			}
		}
	}
	
	public int getColumn() throws Exception {
		if (matrix == null || matrix.length <= 0) {
			throw new Exception("the matrix is null");
		}
		
		return matrix.length;
	}
	
	public int getRow() throws Exception {
		if (matrix == null || matrix.length <= 0 || matrix[0] == null) {
			throw new Exception("the matrix is null");
		}
		
		return matrix[0].length;
	}
	
	public double getAt(int i, int j) throws Exception {
		if (matrix == null || i >= matrix.length || matrix[i] == null || j >= matrix[i].length) {
			throw new Exception("the matrix is null");
		}
		
		return matrix[i][j];
	}
	
	public void setAt(int i, int j, double value) throws Exception {
		if (matrix == null || i >= matrix.length || matrix[i] == null || j >= matrix[i].length) {
			throw new Exception("the matrix is null");
		}
		
		matrix[i][j] = value;
	}
	
	public void rightMultiply(AIMatrix aiMatrix) throws Exception {
		int i, j, k;
		double accumulate = 0.0;
		double tmp[][] = null;
		
		if (matrix == null || matrix.length <= 0 || matrix[0] == null) {
			throw new Exception("the matrix is null");
		}
		
		if (aiMatrix == null) {
			throw new Exception("the argument is null");
		}
		
		if (getRow() != aiMatrix.getColumn()) {
			throw new Exception("illegal matrix");
		}
		
		tmp = new double[this.getColumn()][aiMatrix.getRow()];
		for (i = 0; i < this.getColumn(); ++i) {
			for (j = 0; j < aiMatrix.getRow(); ++j) {
				accumulate = 0.0;
				for (k = 0; k < this.getRow(); ++k) {
					accumulate = accumulate + getAt(i, k) * aiMatrix.getAt(k, j);
				}
				tmp[i][j] = accumulate;
			}
		}
		
		matrix = tmp;
	}
}
