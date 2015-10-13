package com.brillio.training.dao;

import java.util.List;

import com.brillio.training.entity.Product;

public interface ProductDao {

	// CRUD operations

	public void addProduct(Product product) throws DaoException;

	public Product getProduct(int productId) throws DaoException;

	public void updateProduct(Product product) throws DaoException;

	public void deleteProduct(int productId) throws DaoException;

	// Queries
	
	public List<Product> getAllProducts() throws DaoException;

	public List<Product> getProductsByPriceRange(double min, double max) throws DaoException;

	public List<Product> getProductsByCategory(int categoryId) throws DaoException;

	public List<Product> getProductsBySupplier(int supplierId) throws DaoException;

	public List<Product> getProductsInStock() throws DaoException;

	public List<Product> getProductsNotInStock() throws DaoException;

}
