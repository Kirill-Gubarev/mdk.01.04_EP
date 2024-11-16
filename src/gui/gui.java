package gui;

import ter.terminal;
import ter.terio;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class gui {
	//data
	private List<button> buttons;
	private int indexButtons;
	public void addIndexButtons(int number){
		indexButtons = (indexButtons + buttons.size() + number) % buttons.size();
	}

	//constructor
	public gui(){
		this.buttons = new ArrayList<>();
		this.indexButtons = 0;
	}
	public gui(button[] buttons){
		this.buttons = new ArrayList<>(Arrays.asList(buttons));
		this.indexButtons = 0;
	}

	public void add(button b){
		buttons.add(b);
	}

	public void pressButton(){
		buttons.get(indexButtons).press();
	}
	public button get(int index){
		return buttons.get(index);
	}

	//output buttons
	public void print(){
		terminal.setCursorPos(0, 0);
		for(int i = 0; i < buttons.size(); i++){
			button b = buttons.get(i);
			if(i == indexButtons)
				terio.printColoredText(b.name, b.color + "\033[7m");
			else
				terio.printColoredText(b.name, b.color);
		}
	}
}
