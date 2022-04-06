package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Drinks;

public class Search {

	public static List<Drinks> searchCaffee() {
		List<Drinks> listDrinks = new ArrayList<Drinks>();
		Connection conn = null;
		Statement statement = null;
		String sql = "select * from drinks where category = 'coffee'";
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Drinks dr = new Drinks(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"),rs.getInt("purchase_amount"));
				listDrinks.add(dr);
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
		return listDrinks;
	}
	
	public static List<Drinks> searchTea() {
		List<Drinks> listDrinks = new ArrayList<Drinks>();
		Connection conn = null;
		Statement statement = null;
		String sql = "select * from drinks where category = 'tea'";
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Drinks dr = new Drinks(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"),rs.getInt("purchase_amount"));
				listDrinks.add(dr);
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
		return listDrinks;
	}
	
	public static List<Drinks> searchJuice() {
		List<Drinks> listDrinks = new ArrayList<Drinks>();
		Connection conn = null;
		Statement statement = null;
		String sql = "select * from drinks where category = 'juice'";
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Drinks dr = new Drinks(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"),rs.getInt("purchase_amount"));
				listDrinks.add(dr);
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
		return listDrinks;
	}
	
	public static List<Drinks> searchByBtn(String name) {
		List<Drinks> listDrinks = new ArrayList<Drinks>();
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "select * from drinks where name like ?" ;
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + name + "%");
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Drinks dr = new Drinks(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"),rs.getInt("purchase_amount"));
				listDrinks.add(dr);
			}
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
		return listDrinks;
	}
}
