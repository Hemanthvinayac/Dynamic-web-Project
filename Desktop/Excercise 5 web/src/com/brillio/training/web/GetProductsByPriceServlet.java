package com.brillio.training.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brillio.training.dao.DaoException;
import com.brillio.training.dao.DaoFactory;
import com.brillio.training.dao.ProductDao;
import com.brillio.training.entity.Product;

/**
 * Servlet implementation class GetProductsByPriceServlet
 */
@WebServlet("/getProductsByPrice")
public class GetProductsByPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. read the inputs
		
		double min,max;
		min = Double.parseDouble(request.getParameter("min_price"));
		max = Double.parseDouble(request.getParameter("max_price"));
		
		//2. get the data for the given input
		
		try {
			ProductDao dao = DaoFactory.getProductDao();
			List<Product> list = dao.getProductsByPriceRange(min, max);
	
			//3. generate the output
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<!doctype html>");
			out.print("<html>");
			out.println("<head>");
			out.println("<title>Product data</title>");
			out.println("</head>");
			
			out.println("<body>");
			out.println("<h1>Product data</h1>");
			out.println("<hr />");
			out.println("<p>Price Range:</p>");
			out.println("<p>Min: "+min+", Max: "+max+"</p>");
			
			
			out.println("<ul>");
			
			for (Product p : list) {
				out.print("<li>"+p.getProductName());
				out.println(" $"+p.getUnitPrice()+"</li>");
			}
			
			out.println("</ul>");
			out.println("<p><a href='index.html'>Try again</a></p>");
			out.println("</body>");
			out.println("</html>");
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
