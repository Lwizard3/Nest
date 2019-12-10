package Interface;

public enum Timeout {
	Allow (true),
	Deny (false);
	
	boolean value;
	
	Timeout(boolean b) {
		value = b;
	}
	
	boolean get() {
		return value;
	}
}
