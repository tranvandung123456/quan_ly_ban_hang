package model;


public class Bill {
	private int id_bill,price,quantity;
	private String name;
	private String date;
	
	public Bill(int id_bill,String name,int quantity, int price,String date) {
		super();
		this.id_bill = id_bill;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
		this.date = date;
	}
	
	
	
	public Bill(String name, int quantity, int price) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}



	public Bill() {
		
	}

	public int getId_bill() {
		return id_bill;
	}

	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
