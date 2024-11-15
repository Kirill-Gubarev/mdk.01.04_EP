package gui;

import ter.terminal;
import main.utils;

public class gui {
	//data
	private button[] buttons;
	private int indexButtons;
	public void addIndexButtons(int number){
		indexButtons = (indexButtons + buttons.length + number) % buttons.length;
	}

	//constructor
	public gui(button[] buttons){
		this.buttons = buttons;
		this.indexButtons = 0;
	}

	public void pressButton(){
		buttons[indexButtons].press();
	}

	//output buttons
	public void print(){
		terminal.setCursorPos(0, 0);
		for(int i = 0; i < buttons.length; i++){
			if(i == indexButtons)
				utils.printColoredText(buttons[i].getName(), "\033[7m");
			else
				utils.printColoredText(buttons[i].getName(), "\033[0m");
		}
	}
}
