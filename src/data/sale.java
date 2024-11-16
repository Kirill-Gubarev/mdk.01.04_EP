package data;

public class sale{
	public int id;
	public String date;
	public String time;
	public int id_customer;
	public int id_product;
	public int quantity;

	public sale(int id, String date, String time, int id_customer, int id_product, int quantity){
		this.id = id;
		this.date = date;
		this.time = time;
		this.id_customer = id_customer;
		this.id_product = id_product;
		this.quantity = quantity;
	}
}
