package com.brillio.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brillio.training.dao.DaoException;

public class DbUtil {

	public static Connection getConnection() throws DaoException {
		
		try {
			String url = "jdbc:hsqldb:hsql://localhost/nwind";
			String user = "sa";
			String password = "";
			String driverClassName = "org.hsqldb.jdbcDriver";

			Class.forName(driverClassName);

			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public static void releaseResources(
			Connection conn, Statement stmt, ResultSet rs)
					throws DaoException{
	
		try {
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
}
