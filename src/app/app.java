package app;

import ter.terminal;
import ter.terio;

import gui.gui;
import gui.button;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class app{
	private static gui currentGui;

	public static gui mainGui;
	public static gui	sumGui;
	public static gui mostLessPopularGui;
	public static gui customersGui;
	public static gui salesTrendsGui;

	public static void setGui(gui g){
		terio.clear();
		currentGui = g;
		currentGui.print();
	}
	public static void deleteFile(String filePath) {
		Path path = Paths.get(filePath);

		try {
		  Files.delete(path);
		} catch (IOException e) {
		}
	  }
	public static void appendToFile(String filePath, String line) {
		Path path = Paths.get(filePath);

		try {
		  Files.writeString(path, line + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
		  System.err.println("file writing error: " + e.getMessage());
		}
	  }

	public static void init(){
		terminal.setInstantInputMode(true);
		terminal.setCursorVisibility(false);
		terminal.setAltBuf(true);

		deleteFile("data/logs.txt");

		mainGui = new gui(new button[]{
			new button("the sum of all sales.", () -> {
				setGui(sumGui);
				appUtils.theSumOfAllSales();
			}),
			new button("the most and less popular products", () -> {
				setGui(mostLessPopularGui);
				appUtils.theMostAndLessPopularProducts();
			}),
			new button("cusomers", () -> {
				setGui(customersGui);
				appUtils.customers();
			}),
			new button("sales trends", () -> {
				setGui(salesTrendsGui);
			}),
			new button("exit", "\033[31m", () -> {
				terio.clear();
				terminal.hExit();
			})
		});

		sumGui = new gui(new button[]{
			new button("back", "\033[32m", () -> {
				terio.clear();
				setGui(mainGui);
			})
		});

		mostLessPopularGui = new gui(new button[]{
			new button("back", "\033[32m", () -> {
				terio.clear();
				setGui(mainGui);
			})
		});

		customersGui = new gui(new button[]{
			new button("back", "\033[32m", () -> {
				terio.clear();
				setGui(mainGui);
			})
		});

		salesTrendsGui = new gui(new button[]{
			new button("back", "\033[32m", () -> {
				appUtils.productId = 0;
				app.salesTrendsGui.get(1).name = "The product is not selected";
				app.salesTrendsGui.get(1).color = "\033[33m";
				terio.clear();
				setGui(mainGui);
			}),
			new button("The product is not selected", "\033[33m", () -> {
				appUtils.salesTrends();
			})
		});

		currentGui = mainGui;
	}
	public static void exec(){
		currentGui.print();
		for(int ch = 0; (ch = ter.terminal.instantGetChar()) != 'q';){
			if(ch == 'w')
				currentGui.addIndexButtons(-1);
			else if(ch == 's')
				currentGui.addIndexButtons(1);
			else if(ch == 10)
				currentGui.pressButton();

			currentGui.print();
		}
	}
}
