package main;

import ter.terminal;
import gui.gui;
import gui.button;

public class mainClass {
    public static void main(String[] args) {
		terminal.init();
		terminal.setInstantInputMode(true);
		terminal.setCursorVisibility(false);
		terminal.setAltBuf(true);

		gui g = new gui(new button[]{
			new button("button1", () -> System.out.println("delovoy pon")),
			new button("button2"),
			new button("button3"),
			new button("button4"),
			new button("button5")
		});

		g.print();
		for(int ch = 0; (ch = ter.terminal.instantGetChar()) != 'q';){
			if(ch == 'w')
				g.addIndexButtons(-1);
			else if(ch == 's')
				g.addIndexButtons(1);
			else if(ch == 10)
				g.pressButton();

			g.print();
		}

		terminal.terminate();
    }
}
