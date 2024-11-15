package main;

public class utils{
	private utils(){}
	public static void clear(){
		System.out.print("\033[2J");
	}
	public static void printColoredText(String str, String color){
		System.out.println(color + str + "\033[0m");
	}
	public static void error(String message){
		printColoredText(message, "\033[31m");
	}
}
