package main;

import ter.terminal;
import app.app;

public class mainClass {
    public static void main(String[] args) {
		terminal.init();

		app a = new app();
		a.exec();

		terminal.terminate();
    }
}
