package controller;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import model.Drinks;

public class BillsModify {
	public static void insert(String date,int total) {
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "insert into bills(time,total) values(?,?)";
		conn = JDBCConnect.getConnection();
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, date);
			statement.setInt(2, total);
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
	
	public static int takeIdBill(String date) {
		int id = 1;
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "select * from bills where time like ?";
		conn = JDBCConnect.getConnection();
		
		try {
			statement = conn.prepareCall(sql);
			statement.setString(1,"%"+ date + "%" );
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
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
		
		System.out.println("ID:"+id);
		return id;
	}
	
}
