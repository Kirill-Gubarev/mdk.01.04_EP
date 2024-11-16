package app;

import gui.gui;
import gui.button;

import csv.csvReader;

import ter.terminal;
import ter.terio;
import ter.Point;
import ter.columnPrinter;

import data.customer;
import data.product;
import data.sale;
import data.dataUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class appUtils{
	private appUtils(){}

	public static void theSumOfAllSales(){

		List<sale> sales = dataUtils.parseTableToSaleList(csvReader.parse("data/sales.csv"));
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));

		int length = sales.size();
		float result = 0;
		for(int i = 0; i < length; i++){
			int id = sales.get(i).id_product;
			product p = dataUtils.findProductById(products, id);
			if(p != null)
				result += sales.get(i).quantity * p.price;
			else
				terio.error("Searching product error, id:" + String.valueOf(id));
		}

		System.out.println("the sum of all sales: \033[33m" + String.valueOf(result) + "\033[0m RUB");
		app.appendToFile("data/logs.txt", "the sum of all sales: " + String.valueOf(result) + "RUB");
	}

	public static void theMostAndLessPopularProducts(){
		List<sale> sales = dataUtils.parseTableToSaleList(csvReader.parse("data/sales.csv"));
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));

		Map<Integer, Integer> salesMap = new HashMap<>();

		for (sale s : sales) {
			salesMap.put(s.id_product, salesMap.getOrDefault(s.id_product, 0) + s.quantity);
		}

		List<sale> aggregatedSales = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : salesMap.entrySet()) {
			aggregatedSales.add(new sale(0, "", "", 0, entry.getKey(), entry.getValue()));
		}

		aggregatedSales.sort((s1, s2) -> Integer.compare(s2.quantity, s1.quantity));

		System.out.println("Top 5 most popular products:");
		for (int i = 0; i < Math.min(5, aggregatedSales.size()); i++) {
			sale topSale = aggregatedSales.get(i);
			product topProduct = dataUtils.findProductById(products, topSale.id_product);
			System.out.printf("Product: \033[33m%s\033[0m, Sales: \033[33m%d\033[0m\n", topProduct.name, topSale.quantity);
			app.appendToFile("data/logs.txt", "Product: " + topProduct.name + " Sales: " + topSale.quantity);
		}

		System.out.println("Top 5 least popular products:");
		for (int i = Math.max(0, aggregatedSales.size() - 5); i < aggregatedSales.size(); i++) {
			sale bottomSale = aggregatedSales.get(i);
			product bottomProduct = dataUtils.findProductById(products, bottomSale.id_product);
			System.out.printf("Product: \033[33m%s\033[0m, Sales: \033[33m%d\033[0m\n", bottomProduct.name, bottomSale.quantity);
			app.appendToFile("data/logs.txt", "Product: " + bottomProduct.name + " Sales: " + bottomSale.quantity);
		}
	}

	public static void customers(){
        System.out.print("please enter the cost: \033[32m\033[7m");
        float cost = terio.inputFloat();
		terminal.setCursorPos(24, 2);
        System.out.print("\033[0m\033[32m" + cost + "\033[0m RUB\n");

		app.appendToFile("data/logs.txt", "cost: " + cost);

		List<sale> sales = dataUtils.parseTableToSaleList(csvReader.parse("data/sales.csv"));
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));
		List<customer> customers = dataUtils.parseTableToCustomerList(csvReader.parse("data/customers.csv"));

		Map<Integer, Float> salesMap = new HashMap<>();

		for(sale s : sales){
			product p = dataUtils.findProductById(products, s.id_product);
			if(p != null)
				salesMap.put(s.id_customer, salesMap.getOrDefault(s.id_customer, 0.0f) + p.price * s.quantity);
			else
				terio.error("finding product error: " + String.valueOf(s.id_product));
		}

		columnPrinter cp = new columnPrinter();

		for (Integer key : salesMap.keySet()){
			if(salesMap.get(key) > cost){
				String name = dataUtils.findCustomerById(customers, key).name;
				cp.print("Customer: \033[34m" + name + "\033[0m ");
			}
		}
		cp.next(); for (Integer key : salesMap.keySet()){
			if(salesMap.get(key) > cost){
				cp.print("Purchases: \033[33m" + salesMap.get(key) + "\033[0m RUB");
				String name = dataUtils.findCustomerById(customers, key).name;
				app.appendToFile("data/logs.txt", "Customer: " + name + " Purchases: " + salesMap.get(key) + " RUB");
			}
		}
	}

	public static int productId = 0;
	private static void salesTrendsCalc(){
		List<sale> sales = dataUtils.parseTableToSaleList(csvReader.parse("data/sales.csv"));
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));

		Map<Pair, Integer> salesMap = new HashMap<>();

		for(sale s : sales){
			Pair p = new Pair(s.id_product, s.date);
			salesMap.put(p, salesMap.getOrDefault(s.id_product, 0) + s.quantity);
		}

        List<DateQuantity> dateQuantityList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Map.Entry<Pair, Integer> entry : salesMap.entrySet()) {
			if(entry.getKey().id == productId){
				String dateString = entry.getKey().date;
				int quantity = entry.getValue();
				try {
					Date date = dateFormat.parse(dateString);
					dateQuantityList.add(new DateQuantity(date, quantity));
				} catch (ParseException e) {
					System.err.println("date parsing error: " + dateString);
				}
			}
        }

        Collections.sort(dateQuantityList, Comparator.comparing(DateQuantity::getDate));

		try{
			app.appendToFile("data/logs.txt", "product: " + dataUtils.findProductById(products, productId).name);
		}
		catch(Exception e){
			terio.error("product parsing error: " + e.getMessage());
		}
        for (DateQuantity dq : dateQuantityList) {
            System.out.println("Date: " + dateFormat.format(dq.getDate()) + ", Quantity: " + dq.getQuantity());
			app.appendToFile("data/logs.txt", "Date: " + dateFormat.format(dq.getDate()) + ", Quantity: " + dq.getQuantity());
        }
	}
	public static void salesTrends(){
		List<product> products = dataUtils.parseTableToProductList(csvReader.parse("data/products.csv"));

		gui productsGui = new gui();
		productsGui.add(new button("back", "\033[32m", () -> {
				terio.clear();
				app.setGui(app.salesTrendsGui);
			}));
		for(product p : products)
			productsGui.add(new button(p.name, () -> {
				productId = p.id;
				app.salesTrendsGui.get(1).name = p.name;
				app.salesTrendsGui.get(1).color = "\033[32m";
				app.setGui(app.salesTrendsGui);
				salesTrendsCalc();
			}));
		app.setGui(productsGui);
	}
}
