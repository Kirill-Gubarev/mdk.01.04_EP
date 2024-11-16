package main;

import ter.terminal;
import app.app;

public class mainClass {
    public static void main(String[] args) {
		terminal.init();

		app.init();
		app.exec();

		terminal.terminate();
    }
}
