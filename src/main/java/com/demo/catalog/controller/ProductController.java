package com.demo.catalog.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.catalog.dao.ProductDAO;
import com.demo.catalog.model.Product;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
    private ProductDAO productDAO;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String product(Locale locale, Model model) {
		logger.info("Welcome product! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );

		List<Product> products = productDAO.list();		
		model.addAttribute("products", products );
		
		return "product";
	}
	
	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public ModelAndView editProduct(HttpServletRequest request) {
	    int productId = Integer.parseInt(request.getParameter("id"));
	    Product product = productDAO.get(productId);
	    ModelAndView model = new ModelAndView("ProductForm");
	    model.addObject("product", product);
	 
	    return model;
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		productDAO.saveOrUpdate(product);
	    return new ModelAndView("redirect:./product");
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request) {
	    int productId = Integer.parseInt(request.getParameter("id"));
	    productDAO.delete(productId);
	    return new ModelAndView("redirect:./product");
	}
	
	@RequestMapping(value = "/newProduct", method = RequestMethod.GET)
	public ModelAndView newProduct(ModelAndView model) {
		Product product = new Product();
	    model.addObject("product", product);
	    model.setViewName("ProductForm");
	    return model;
	}
}
