package debug;

public class Debugger {

	public static final boolean debug = true;
	
	public static void print(String text) {
		if (debug) {
			System.out.println(text);		}
	}
}
