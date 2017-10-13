package com.demo.catalog.dao;

import java.util.List;

import com.demo.catalog.model.Vendor;

/**
 * This console application demonstrates how to do CRUD operations using JDBC
 * with Spring framework.
 * 
 * @author www.codejava.net
 *
 */
public interface VendorDAO {
	public void saveOrUpdate(Vendor vendor);

	public void delete(int vendorId);

	public Vendor get(int vendorId);

	public List<Vendor> list();
}