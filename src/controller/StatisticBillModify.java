package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bills;

public class StatisticBillModify {
	public static List<Bills> fillAll(){
		List<Bills> listBills = new ArrayList<Bills>();
		Connection conn = null;
		Statement statement = null;
		String sql = "select * from bills";
		conn = JDBCConnect.getConnection();
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Bills bill = new Bills(rs.getInt("id"), rs.getString("time"), rs.getInt("total"));
				listBills.add(bill);
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
		
		return listBills;
	}
}
