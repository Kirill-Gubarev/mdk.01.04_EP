package data;

import ter.terminal;
import ter.terio;

import java.util.List;
import java.util.ArrayList;

public class dataUtils{
	private dataUtils(){}

	public static product findProductById(List<product> products, int id) {
        for (product p : products) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

	public static customer findCustomerById(List<customer> customers, int id) {
        for (customer p : customers) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }
	public static List<customer> parseTableToCustomerList(List<List<String>> table){
		List<customer> result = new ArrayList<customer>();
		for(List<String> row : table){
			try{
				result.add(new customer(Integer.parseInt(row.get(0)), row.get(1)));
			}
			catch(Exception e){
				terio.error("Parse error: " + e.getMessage());
			}
		}
		return result;
	}
	public static List<product> parseTableToProductList(List<List<String>> table){
		List<product> result = new ArrayList<product>();
		for(List<String> row : table)
			try{
				result.add(new product(Integer.parseInt(row.get(0)), row.get(1), Float.parseFloat(row.get(2))));
			}
			catch(Exception e){
				terio.error("Parse error: " + e.getMessage());
			}
		return result;
	}
	public static List<sale> parseTableToSaleList(List<List<String>> table){
		List<sale> result = new ArrayList<sale>();
		for(List<String> row : table){
			try{
				result.add(new sale(
							Integer.parseInt(row.get(0)),
							row.get(1),
							row.get(2),
							Integer.parseInt(row.get(3)),
							Integer.parseInt(row.get(4)),
							Integer.parseInt(row.get(5))
							));
			}
			catch(Exception e){
				terio.error("Parse error: " + e.getMessage());
			}
		}
		return result;
	}
}
