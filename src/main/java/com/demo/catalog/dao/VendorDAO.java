package com.demo.catalog.dao;

import java.util.List;

import com.demo.catalog.model.Vendor;

public interface VendorDAO {
	public void saveOrUpdate(Vendor vendor);
	
	public void delete(int vendorId);

	public Vendor get(int vendorId);

	public List<Vendor> list();
}
