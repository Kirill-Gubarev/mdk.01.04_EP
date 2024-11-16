package ter;

import java.util.Scanner;

public class terio{
	private static Scanner scanner = new Scanner(System.in);

	public static String input(){
		terminal.setCursorVisibility(true);
		terminal.setInstantInputMode(false);
		String result = scanner.nextLine();
		terminal.setCursorVisibility(false);
		terminal.setInstantInputMode(true);
		return result;
	}
	public static float inputFloat(){
		float result = 0;
		try{
			result = Float.parseFloat(input());
		}
		catch(Exception e){
			error("Error floating number input: " + e.getMessage());
		}
		return result;
	}
	public static int inputInt(){
		int result = 0;
		try{
			result = Integer.parseInt(input());
		}
		catch(Exception e){
			error("Error integer input: " + e.getMessage());
		}
		return result;
	}

	public static void posPrint(int x, int y, String str){
		terminal.setCursorPos(x, y);
		System.out.print(str);
	}
	public static void printColoredText(String str, String color){
		System.out.println(color + str + "\033[0m");
	}

	public static void clear(){
		System.out.print("\033[2J\033[H");
	}
	public static void error(String message){
		printColoredText(message, "\033[31m");
	}
}
