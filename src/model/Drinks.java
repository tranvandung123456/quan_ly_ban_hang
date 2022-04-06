package model;

public class Drinks {
	private int id,price,quantity;

	private String name,Category;
	
	public Drinks(int id, String name,int price, String category,int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.Category = category;
		this.quantity = quantity;
	}
	
	public Drinks( String name,int quantity) {
		super();
		this.quantity = quantity;
		this.name = name;
	}
	
	public Drinks( String name,int price, String category) {
		this.name = name;
		this.price = price;
		this.Category = category;
	}
	
	public Drinks() {
	}

	public int getId() {
		return id;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}
	
	
	
}
