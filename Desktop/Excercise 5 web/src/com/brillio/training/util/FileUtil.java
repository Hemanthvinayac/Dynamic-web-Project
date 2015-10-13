package com.brillio.training.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.brillio.training.dao.DaoException;
import com.brillio.training.entity.Product;

public class FileUtil {

	private static String FILENAME = "products.dat";

	public static void saveProductsMap(Map<Integer, Product> productsMap)
			throws DaoException {
		try {
			FileOutputStream file = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(productsMap);
			out.close();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public static Map<Integer, Product> getProductsMap() throws DaoException {
		try {
			FileInputStream file = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(file);
			Object obj = in.readObject();
			in.close();
			return (Map<Integer, Product>) obj;
		} catch (FileNotFoundException e) {
			return new HashMap<Integer, Product>();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

}