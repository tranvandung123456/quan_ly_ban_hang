package model;


public class Bills {
	private int id,total;
	private String date;
	
	public Bills(int id, String date,int total) {
		super();
		this.id = id;
		this.total = total;
		this.date = date;
	}
	
	public Bills( int total, String date) {
		super();
		this.total = total;
		this.date = date;
	}
	
	public Bills() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
