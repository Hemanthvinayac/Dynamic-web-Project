package com.brillio.training.entity;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int productId;
	private String productName;
	private String quantityPerUnit;
	private int supplierId;
	private int categoryId;
	private double unitPrice;
	private int unitsInStock;
	private int unitsOnOrder;
	private int reorderLevel;
	private int discontinued;

	public Product() {
	}
	
	

	public Product(int productId, String productName, String quantityPerUnit, int supplierId, int categoryId,
			double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantityPerUnit = quantityPerUnit;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.reorderLevel = reorderLevel;
		this.discontinued = discontinued;
	}



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public int getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(int discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", quantityPerUnit="
				+ quantityPerUnit + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", unitPrice="
				+ unitPrice + ", unitsInStock=" + unitsInStock + ", unitsOnOrder=" + unitsOnOrder + ", reorderLevel="
				+ reorderLevel + ", discontinued=" + discontinued + "]";
	}



	public String toCsvString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
				productId,
				productName,
				quantityPerUnit,
				supplierId,
				categoryId,
				unitPrice,
				unitsInStock,
				unitsOnOrder,
				reorderLevel,
				discontinued
				);
	}
	
	public void setValues(String csv){
		String[] ar = csv.split(",");
		productId = Integer.parseInt(ar[0]);
		productName = ar[1];
		quantityPerUnit = ar[2];
		supplierId = Integer.parseInt(ar[3]);
		categoryId = Integer.parseInt(ar[4]);
		unitPrice = Double.parseDouble(ar[5]);
		unitsInStock = Integer.parseInt(ar[6]);
		unitsOnOrder = Integer.parseInt(ar[7]);
		reorderLevel = Integer.parseInt(ar[8]);
		discontinued =Integer.parseInt(ar[9]);
	}

}
