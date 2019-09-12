package UI;

public enum Window {
	
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
