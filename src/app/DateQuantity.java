package app;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateQuantity {
	private Date date;
	private int quantity;

	public DateQuantity(Date date, int quantity) {
		this.date = date;
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public int getQuantity() {
		return quantity;
	}
}
