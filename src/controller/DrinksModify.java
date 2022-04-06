package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Drinks;

public class DrinksModify {
	public static List<Drinks> fillDrinks(){
		List<Drinks> listDrinks = new ArrayList<Drinks>();
		Connection conn = null;
		Statement statement = null;
		String sql = "select * from drinks";
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
	
	public static void delete(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "delete from drinks where id = ?";
		conn = JDBCConnect.getConnection();
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
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
	
	public static void insert(Drinks dr) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "insert into drinks(name,price,category) values(?,?,?)";
		conn = JDBCConnect.getConnection();
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, dr.getName());
			statement.setInt(2, dr.getPrice());
			statement.setString(3, dr.getCategory());
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
	
	public static int getPA(String name) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "select * from drinks where name = ?";
		int i =0;
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.prepareCall(sql);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				i = rs.getInt("purchase_amount");
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
	return i;
	}
	
	public static void addQuantity(String name,int quantity) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "update drinks set purchase_amount = ? where name = ?";
		conn = JDBCConnect.getConnection();
		int i = getPA(name);
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, quantity+i);
			statement.setString(2, name);
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
}
