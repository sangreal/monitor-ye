package enn.monitor.framework.lang.format.common;

public abstract class LangFormatLogImpl {
	
	protected void printWhitspace(int length) {
		int i; 
		
		for (i = 0; i < length * 2; ++i) {
			System.out.print(" ");
		}
	}

	public abstract void logPrint(int length);
	
}
