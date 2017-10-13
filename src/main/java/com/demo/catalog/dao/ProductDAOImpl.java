package com.demo.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.demo.catalog.model.Product;

public class ProductDAOImpl implements ProductDAO {
	private JdbcTemplate jdbcTemplate;

	public ProductDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Product product) {
		if (product.getProduct_id() == 0) {
			// Insert New Record Into Database
			String sqlInsert = "INSERT INTO product (category_id, name, summary, description, price, qty) "
					+ " VALUES (?, ?, ?, ?,?,?)";
			jdbcTemplate.update(sqlInsert, product.getCategory_id(), product.getName(), product.getSummary(),
					product.getDescription(), product.getPrice(), product.getQty());
		} else {
			// Update record in Database
			String sqlUpdate = "UPDATE product SET category_id = ?, name = ?, summary = ?, description = ?, price = ?, qty = ? WHERE product_id = ?";
			jdbcTemplate.update(sqlUpdate, product.getCategory_id(), product.getName(), product.getSummary(),
					product.getDescription(), product.getPrice(), product.getQty(), product.getProduct_id());
		}
	}

	@Override
	public void delete(int productId) {
		String sqlDelete = "DELETE FROM Product WHERE product_id = ?";
		jdbcTemplate.update(sqlDelete, productId);
	}

	@Override
	public Product get(int productId) {
		String sqlSelect = "SELECT * FROM product where product_id = " + productId;
		return jdbcTemplate.query(sqlSelect, new ResultSetExtractor<Product>() {

			@Override
			public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Product product = new Product();
					product.setProduct_id(rs.getInt("product_id"));
					product.setCategory_id(rs.getInt("category_id"));
					product.setName(rs.getString("name"));
					product.setSummary(rs.getString("summary"));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getString("price"));
					product.setQty(rs.getInt("qty"));
					return product;
				}

				return null;
			}

		});
	}

	@Override
	public List<Product> list() {
		String sqlSelect = "SELECT * FROM product";
		List<Product> listProducts = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {
			// Map result set to Product
			public Product mapRow(ResultSet result, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProduct_id(result.getInt("product_id"));
				product.setCategory_id(result.getInt("category_id"));
				product.setName(result.getString("name"));
				product.setSummary(result.getString("summary"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getString("price"));
				product.setQty(result.getInt("qty"));
				return product;
			}

		});
		return listProducts;
	}

}
