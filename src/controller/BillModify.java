package controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bill;
import model.Drinks;

public class BillModify {
	public static void addListBill(List<Bill> bill,String date,int id) {
		List<Bill> listBill = new ArrayList<Bill>();
		for(Bill b : bill) {
			b = new Bill(id,b.getName(),b.getQuantity(),b.getPrice(),date);
			listBill.add(b);
		}
		for(Bill b : listBill) {
			insert(b);
		}
	}
	
	public static void insert(Bill bill) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "insert into bill(id_bill,name,quantity,price,time) values(?,?,?,?,?)";
		conn = JDBCConnect.getConnection();
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, bill.getId_bill());
			statement.setString(2, bill.getName());
			statement.setInt(3, bill.getQuantity());
			statement.setInt(4, bill.getPrice());
			statement.setString(5, bill.getDate());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Fail to close statement!");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Fail to close connection!");
					e.printStackTrace();
				}
			}
		}				
	}
	
	public static List<Bill> fillBill(int id){
		List<Bill> listBill = new ArrayList<Bill>();
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "select * from bill where id = ?";
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.prepareCall(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Bill b = new Bill(rs.getInt("id_bill"), rs.getString("name"),rs.getInt("price") ,rs.getInt("price"),rs.getString("time") );
				listBill.add(b);
			}
		} catch (SQLException e) {
			System.out.println("Fail to close statement!");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Fail to close statement!");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Fail to close connection!");
					e.printStackTrace();
				}
			}
		}				
		return listBill;
	}

	
}

