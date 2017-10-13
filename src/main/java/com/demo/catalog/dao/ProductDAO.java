package com.demo.catalog.dao;

import java.util.List;

import com.demo.catalog.model.Product;

public interface ProductDAO {
	public void saveOrUpdate(Product product);
	
	public void delete(int productId);

	public Product get(int productId);

	public List<Product> list();
}
