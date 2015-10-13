package com.brillio.training.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.brillio.training.dao.DaoException;
import com.brillio.training.dao.ProductDao;
import com.brillio.training.entity.Product;

public class CsvProductDao implements ProductDao{

	private static final String FILENAME = "vinay.csv";

	public void addProduct(Product p) throws DaoException{

		FileWriter file = null;
		try {
			file = new FileWriter(FILENAME, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(file);
		out.print(p.toCsvString());
		out.close();
	}

	public List<Product> getAllProducts() throws DaoException{
		FileReader file = null;
		try {
			file = new FileReader(FILENAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(file);
		String line;

		List<Product> list = new ArrayList<>();
		try {
			while((line=in.readLine())!=null){
				Product p = new Product();
				p.setValues(line);
				list.add(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			in.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> getProductsNotInStock() throws DaoException{
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();

		for(Product p: list){
			if(p.getUnitsInStock()==0){
				list1.add(p);
			}
		}
		return list1;
	}

	@Override
	public Product getProduct(int productId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int productId) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProductsByPriceRange(double min, double max) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsBySupplier(int supplierId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsInStock() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
