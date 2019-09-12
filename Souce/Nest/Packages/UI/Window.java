package UI;

public enum Window {
	
	//This is the enum that allows the window switching to work
	
	MainMenu(0),
	Pathfinding(1),
	Playback(2),
	Schedule(3),
	Robot(4),
	Interface(5),
	Eggs(6),
	Settings(7);
	
	
	private final int index;
	
	private Window(final int index) {
		this.index = index;
	}
	
	public int GetIndex() {
		return this.index;		
	}
	
}
