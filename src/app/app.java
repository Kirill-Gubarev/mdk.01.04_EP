package app;

import ter.terminal;
import csv.csvReader;
import main.utils;

import gui.gui;
import gui.button;

import data.customer;
import data.product;
import data.sale;
import data.dataUtils;

import java.util.List;
import java.util.ArrayList;

public class app{
	private static void myExit(){
		terminal.terminate();
		System.exit(0);
	}
	private static void myPrint(){
		List<List<String>> records = csvReader.parse("data/products.csv");
		for (List<String> row : records) {
			for (String str : row) {
				System.out.println(str);
			}
		}
	}
	public app(){
		terminal.setInstantInputMode(true);
		terminal.setCursorVisibility(false);
		terminal.setAltBuf(true);
	}
	private float sumSales(){
		float result = 0;

		List<sale> sales = dataUtils.parseTableToSaleList(csvReader.parse("data/sales.csv"));
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));

		int length = sales.size();
		for(int i = 0; i < length; i++){
			int id = sales.get(i).id_product - 1;
			product p = dataUtils.findProductById(products, id);
			if(p != null)
				result += sales.get(i).quantity * p.price;
			else
				utils.error("Searching product error, id:" + String.valueOf(id));
		}

		return result;
	}
	public void exec(){
		gui g = new gui(new button[]{
			new button("the sum of all sales.", () -> {
				utils.clear();
				System.out.println("the sum of all sales: \033[33m" + String.valueOf(sumSales()) + "\033[0m");
						}),
			new button("button2", () -> {
				utils.clear();
				myPrint();
			}),
			new button("button3"),
			new button("button4"),
			new button("exit", () -> {
				utils.clear();
				myExit();
			})
		});

		g.print();
		for(int ch = 0; (ch = ter.terminal.instantGetChar()) != 'q';){
			if(ch == 'w')
				g.addIndexButtons(-1);
			else if(ch == 's')
				g.addIndexButtons(1);
			else if(ch == 10)
				g.pressButton();
			if (ch != 10)
				g.print();
		}
	}
}
