package com.brillio.training.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.brillio.training.dao.DaoException;
import com.brillio.training.dao.ProductDao;
import com.brillio.training.entity.Product;
import com.brillio.training.util.FileUtil;

public class FileProductDao implements ProductDao {

	@Override
	public void addProduct(Product product) throws DaoException {
		Map<Integer, Product> map = FileUtil.getProductsMap();
		if (map.containsKey(product.getProductId())) {
			throw new DaoException("Product ID already exists");
		}
		map.put(product.getProductId(), product);
		FileUtil.saveProductsMap(map);
	}

	@Override
	public Product getProduct(int productId) throws DaoException {
		Map<Integer, Product> map = FileUtil.getProductsMap();
		return map.get(productId);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		Map<Integer, Product> map = FileUtil.getProductsMap();
		if (map.containsKey(product.getProductId())) {
			map.put(product.getProductId(), product);
			FileUtil.saveProductsMap(map);
		} else {
			throw new DaoException(
					"Could not update the product, because it does not exist.");
		}
	}

	@Override
	public void deleteProduct(int productId) throws DaoException {
		Map<Integer, Product> map = FileUtil.getProductsMap();
		if (map.containsKey(productId)) {
			map.remove(productId);
			FileUtil.saveProductsMap(map);
		} else {
			throw new DaoException(
					"Could not delete the product, because it does not exist.");
		}
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		List<Product> list = new ArrayList<>();
		list.addAll(FileUtil.getProductsMap().values());
		return list;
	}

	@Override
	public List<Product> getProductsByPriceRange(double min, double max)
			throws DaoException {
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();
		for (Product p : list) {
			double price = p.getUnitPrice();
			if (price >= min && price <= max) {
				list1.add(p);
			}
		}
		return list1;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId)
			throws DaoException {
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();
		for (Product p : list) {
			if (p.getCategoryId() == categoryId) {
				list1.add(p);
			}
		}
		return list1;
	}

	@Override
	public List<Product> getProductsBySupplier(int supplierId)
			throws DaoException {
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();
		for (Product p : list) {
			if (p.getSupplierId() == supplierId) {
				list1.add(p);
			}
		}
		return list1;
	}

	@Override
	public List<Product> getProductsInStock() throws DaoException {
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();
		for (Product p : list) {
			if (p.getUnitsInStock() > 0) {
				list1.add(p);
			}
		}
		return list1;
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		List<Product> list = getAllProducts();
		List<Product> list1 = new ArrayList<>();
		for (Product p : list) {
			if (p.getUnitsInStock() == 0) {
				list1.add(p);
			}
		}
		return list1;
	}

}