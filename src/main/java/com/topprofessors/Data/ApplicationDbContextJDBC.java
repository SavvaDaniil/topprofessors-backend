package com.topprofessors.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ApplicationDbContextJDBC {
	
   private static final String DB_URL = "jdbc:postgresql://XXXXXXXXXXXX";
   private static final String USER = "XXXXXXXXXXXX";
   private static final String PASS = "XXXXXXXXXXXX";
   
   public static Connection getConnection() throws SQLException {
	   return DriverManager.getConnection(DB_URL, USER, PASS);
   }
   
}
