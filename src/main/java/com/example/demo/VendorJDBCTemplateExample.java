package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * This console application demonstrates how to do CRUD operations using JDBC
 * with Spring framework.
 * 
 * @author www.codejava.net
 *
 */
public class VendorJDBCTemplateExample {

	public static void main(String[] args) throws SQLException {
		// Configure Database connection
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new com.mysql.jdbc.Driver());
		dataSource.setUrl("jdbc:mysql://localhost:8889/product_catalog");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		//Insert New Record Into Database
		String sqlInsert = "INSERT INTO vendor (name, address1, address2, city, state, zip, country, phone, website) " 
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sqlInsert, "Acme Toys", "100 Sunset Blvd", "Suite 400", "Daytona Beach", "FL", 37014, "USA", 555, "http://acme.com");

//		//Update record in Database
//		String sqlUpdate = "UPDATE vendor " 
//				+ "SET name = ? ,"
//				+ "website = ? " 
//			+ "WHERE zip = ?";
//		jdbcTemplate.update(sqlUpdate, "Dr Who Stuff", "http://drwhostuff.com", 37014);

		//Return all records from product table
		String sqlSelect = "SELECT * FROM vendor";
		List<Vendor> listVendor = jdbcTemplate.query(sqlSelect, new RowMapper<Vendor>() {
			//Map result set to Product
			public Vendor mapRow(ResultSet result, int rowNum) throws SQLException {
				Vendor vendor = new Vendor();
				vendor.setVendor_id(result.getInt("vendor_id")); 
				vendor.setName(result.getString("name"));
				vendor.setAddress1(result.getString("address1"));
				vendor.setAddress2(result.getString("address2"));
				vendor.setCity(result.getString("city"));
				vendor.setState(result.getString("state"));
				vendor.setZip(result.getInt("zip"));
				vendor.setCountry(result.getString("country"));
				vendor.setPhone(result.getInt("phone"));
				
				return vendor;
			}

		});

		//Loop through list of Products from result set
		for (Vendor vendor : listVendor) {
			System.out.println(vendor);
		}

		//Cleanup Test Record
		String sqlDelete = "DELETE FROM Vendor";
		jdbcTemplate.update(sqlDelete);
		
	}

}