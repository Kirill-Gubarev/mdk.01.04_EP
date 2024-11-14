package gui;

public class button {
	private String name;
	private buttonAction action;

	public button(String name, buttonAction action){
		this.name = name;
		this.action = action;
	}
	public button(String name){
		this(name,()->{});
	}
	public String getName(){
		return name;
	}

	public void press(){
		action.execute();
	}
}
