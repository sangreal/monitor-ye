package enn.monitor.ai.common;

public class AIMatrixUtil {
	
	public static void move(AIMatrix aiMatrix, int dx, int dy) throws Exception {
		int i;
		
		AIMatrix moveMatrix = new AIMatrix(3, 3);
		
		for (i = 0; i < 3; ++i) {
			moveMatrix.setAt(i, i, 1);
		}
		moveMatrix.setAt(2, 0, dx);
		moveMatrix.setAt(2, 1, dy);
		
		aiMatrix.rightMultiply(moveMatrix);
	}
	
	public static void scale(AIMatrix aiMatrix, int scaleX, int scaleY) throws Exception {
		AIMatrix scaleMatrix = new AIMatrix(3, 3);
		
		scaleMatrix.setAt(0, 0, scaleX);
		scaleMatrix.setAt(1, 1, scaleY);
		scaleMatrix.setAt(2, 2, 1);
		
		aiMatrix.rightMultiply(scaleMatrix);
	}
	
	public static void rotation(AIMatrix aiMatrix, double angle) throws Exception {
		AIMatrix rotationMatrix = new AIMatrix(3, 3);
		
		rotationMatrix.setAt(0, 0, Math.cos(angle));
		rotationMatrix.setAt(0, 1, Math.sin(angle));
		rotationMatrix.setAt(1, 0, -Math.sin(angle));
		rotationMatrix.setAt(1, 1, Math.cos(angle));
		rotationMatrix.setAt(2, 2, 1);
		
		aiMatrix.rightMultiply(rotationMatrix);
	}

}
