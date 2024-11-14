package gui;

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

	private void printColoredText(String str, String color){
		System.out.println(color + str + "\033[0m");
	}
	public void pressButton(){
		buttons[indexButtons].press();
	}

	//output buttons
	public void print(){
		for(int i = 0; i < buttons.length; i++){
			if(i == indexButtons)
				printColoredText(buttons[i].getName(), "\033[7m");
			else
				printColoredText(buttons[i].getName(), "\033[0m");
		}
	}
}
