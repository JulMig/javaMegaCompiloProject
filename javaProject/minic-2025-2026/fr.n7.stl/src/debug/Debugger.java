package debug;

public class Debugger {

	public static final boolean debug = false;
	
	public static void print(String text) {
		if (debug) {
			System.out.println(text);		}
	}
}
