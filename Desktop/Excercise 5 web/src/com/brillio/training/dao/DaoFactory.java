package com.brillio.training.dao;

import java.util.ResourceBundle;

public class DaoFactory {

	//do not let anyone instantiate
	private DaoFactory(){
	}
	
	public static ProductDao getProductDao() throws DaoException{
		
		try{
			String baseName = "com.brillio.training.dao.impl";
			ResourceBundle rb = ResourceBundle.getBundle(baseName);
			String implClass = rb.getString("ProductDao.impl");
			
			return (ProductDao) Class.forName(implClass).newInstance();
		}catch(Exception e1){
			throw new DaoException(e1);
		}
		
	}
	
}
