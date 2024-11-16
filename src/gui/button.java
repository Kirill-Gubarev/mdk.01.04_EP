package gui;

public class button {
	public String name;
	public String color;
	public buttonAction action;

	public button(String name, String color, buttonAction action){
		this.name = name;
		this.color = color;
		this.action = action;
	}
	public button(String name,buttonAction action){
		this(name, "", action);
	}
	public button(String name, String color){
		this(name, color, ()->{});
	}
	public button(String name){
		this(name, "", ()->{});
	}

	public void press(){
		action.execute();
	}
}
