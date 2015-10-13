package com.brillio.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brillio.training.dao.DaoException;
import com.brillio.training.dao.ProductDao;
import com.brillio.training.entity.Product;
import com.brillio.training.util.DbUtil;

public class JdbcProductDao implements ProductDao {

	private Product getProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();
		product.setProductId(resultSet.getInt("product_id"));
		product.setProductName(resultSet.getString("product_name"));
		product.setQuantityPerUnit(resultSet.getString("quantity_Per_Unit"));
		product.setSupplierId(resultSet.getInt("supplier_Id"));
		product.setCategoryId(resultSet.getInt("category_Id"));
		product.setUnitPrice(resultSet.getDouble("unit_Price"));
		product.setUnitsInStock(resultSet.getInt("units_In_Stock"));
		product.setUnitsOnOrder(resultSet.getInt("units_On_Order"));
		product.setReorderLevel(resultSet.getInt("reorder_Level"));
		product.setDiscontinued(resultSet.getInt("discontinued"));
		return product;
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DbUtil.getConnection();
			String sql = "insert into products values(?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, product.getProductId());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getSupplierId());
			stmt.setInt(4, product.getCategoryId());
			stmt.setString(5, product.getQuantityPerUnit());
			stmt.setDouble(6, product.getUnitPrice());
			stmt.setInt(7, product.getUnitsInStock());
			stmt.setInt(8, product.getUnitsOnOrder());
			stmt.setInt(9, product.getReorderLevel());
			stmt.setInt(10, product.getDiscontinued());

			stmt.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, null);
		}
	}

	@Override
	public Product getProduct(int productId) throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where product_id = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				Product product = getProduct(rs);
				return product;
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}

		return null;
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "update products set product_name = ?, " + "supplier_id = ?, category_id=?, "
					+ "quantity_per_unit = ?, unit_price=?, " + "units_in_stock=?, units_on_order=?, "
					+ "reorder_level=?, discontinued=? " + "where product_id=?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getSupplierId());
			stmt.setInt(3, product.getCategoryId());
			stmt.setString(4, product.getQuantityPerUnit());
			stmt.setDouble(5, product.getUnitPrice());
			stmt.setInt(6, product.getUnitsInStock());
			stmt.setInt(7, product.getUnitsOnOrder());
			stmt.setInt(8, product.getReorderLevel());
			stmt.setInt(9, product.getDiscontinued());
			stmt.setInt(10, product.getProductId());

			stmt.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, null);
		}
	}

	@Override
	public void deleteProduct(int productId) throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "delete from products where product_id = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);

			stmt.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, null);
		}
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public List<Product> getProductsByPriceRange(double min, double max) throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where unit_price between ? and ?";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, min);
			stmt.setDouble(2, max);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;

	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where category_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;

	}

	@Override
	public List<Product> getProductsBySupplier(int supplierId) throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where supplier_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, supplierId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;

	}

	@Override
	public List<Product> getProductsInStock() throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where units_in_stock>0";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;

	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from products where units_in_stock=0";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = getProduct(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.releaseResources(conn, stmt, rs);
		}
		return list;
	}

}
