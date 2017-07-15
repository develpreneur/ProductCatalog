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
public class ProductJDBCTemplateExample {

	public static void main(String[] args) throws SQLException {
		// Configure Database connection
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new com.mysql.jdbc.Driver());
		dataSource.setUrl("jdbc:mysql://localhost:8889/product_catalog");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		//Insert New Record Into Database
		String sqlInsert = "INSERT INTO product (category_id, name, summary, description, price, qty) "
				+ " VALUES (?, ?, ?, ?,?,?)";
		jdbcTemplate.update(sqlInsert, 0, "Product 1", "t-Shirt", "some interesting image on shirt", "$30.00", 10);

		//Update record in Database
		String sqlUpdate = "UPDATE product set description=? where product_id=?";
		jdbcTemplate.update(sqlUpdate, "something more descriptive", 1);

		//Return all records from product table
		String sqlSelect = "SELECT * FROM product";
		List<Product> listProducts = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {
			//Map result set to Product
			public Product mapRow(ResultSet result, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProduct_id(result.getInt("product_id"));
				product.setCategory_id(result.getInt("category_id"));
				product.setSummary(result.getString("summary"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getString("price"));
				product.setQty(result.getInt("qty"));
				return product;
			}

		});

		//Loop through list of Products from result set
		for (Product product : listProducts) {
			System.out.println(product);
		}

		//Cleanup Test Record
//		String sqlDelete = "DELETE FROM Product";
//		jdbcTemplate.update(sqlDelete);
		
	}

}