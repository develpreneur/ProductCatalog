package com.demo.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.demo.catalog.model.Vendor;

/**
 * This console application demonstrates how to do CRUD operations using JDBC
 * with Spring framework.
 * 
 * @author www.codejava.net
 *
 */
public class VendorDAOImpl implements VendorDAO{
	private JdbcTemplate jdbcTemplate;

	public VendorDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void saveOrUpdate(Vendor vendor) {
		if (vendor.getVendor_id() == 0) {
			String sqlInsert = "INSERT INTO vendor (name, address1, address2, city, state, zip, country, phone, website) " 
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			jdbcTemplate.update(sqlInsert, vendor.getName(), vendor.getAddress1(), vendor.getAddress2(), vendor.getCity(), vendor.getState(), vendor.getZip(), vendor.getCountry(), vendor.getPhone(), vendor.getWebsite());
		} else {
			String sqlUpdate = "UPDATE vendor SET name = ?, address1 = ?, address2 = ?, city = ?, state = ?, zip = ?, country = ?, phone = ?, website = ? WHERE vendor_id = ?";
			jdbcTemplate.update(sqlUpdate, vendor.getName(), vendor.getAddress1(), vendor.getAddress2(), vendor.getCity(), vendor.getState(), vendor.getZip(), vendor.getCountry(), vendor.getPhone(), vendor.getWebsite(), vendor.getVendor_id());
		}
	}

	public void delete(int vendorId) {
		String sqlDelete = "DELETE FROM vendor where vendor_id = ?";
		System.out.println(sqlDelete);
		jdbcTemplate.update(sqlDelete, vendorId);
	}

	public Vendor get(int vendorId) {
		String sqlSelect = "SELECT * FROM vendor where vendor_id = " + vendorId;
		return jdbcTemplate.query(sqlSelect, new ResultSetExtractor<Vendor>() {

			@Override
			public Vendor extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Vendor vendor = new Vendor();
					vendor.setName(rs.getString("name"));
					vendor.setAddress1(rs.getString("address1"));
					vendor.setAddress2(rs.getString("address2"));
					vendor.setCity(rs.getString("city"));
					vendor.setState(rs.getString("state"));
					vendor.setZip(rs.getInt("zip"));
					vendor.setCountry(rs.getString("country"));
					vendor.setPhone(rs.getInt("phone"));
					vendor.setWebsite(rs.getString("website"));
					vendor.setVendor_id(rs.getInt("vendor_id"));
					return vendor;
				}

				return null;
			}

		});
	}

	public List<Vendor> list() {
		String sqlSelect = "SELECT * FROM vendor";
		List<Vendor> listVendors = jdbcTemplate.query(sqlSelect, new RowMapper<Vendor>() {
			// Map result set to Vendor
			public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vendor vendor = new Vendor();
				vendor.setName(rs.getString("name"));
				vendor.setAddress1(rs.getString("address1"));
				vendor.setAddress2(rs.getString("address2"));
				vendor.setCity(rs.getString("city"));
				vendor.setState(rs.getString("state"));
				vendor.setZip(rs.getInt("zip"));
				vendor.setCountry(rs.getString("country"));
				vendor.setPhone(rs.getInt("phone"));
				vendor.setWebsite(rs.getString("website"));
				vendor.setVendor_id(rs.getInt("vendor_id"));

				return vendor;
			}

		});
		return listVendors;
	}

}