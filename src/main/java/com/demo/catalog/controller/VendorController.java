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

import com.demo.catalog.dao.VendorDAO;
import com.demo.catalog.model.Vendor;

/**
 * Handles requests for the application home page.
 */
@Controller
public class VendorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);
	@Autowired
    private VendorDAO vendorDAO;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/vendor", method = RequestMethod.GET)
	public String vendor(Locale locale, Model model) {
		logger.info("Welcome vendor! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );

		List<Vendor> vendors = vendorDAO.list();		
		model.addAttribute("vendors", vendors );
		
		return "vendor";
	}
	
	@RequestMapping(value = "/editVendor", method = RequestMethod.GET)
	public ModelAndView editVendor(HttpServletRequest request) {
	    int vendorId = Integer.parseInt(request.getParameter("id"));
	    Vendor vendor = vendorDAO.get(vendorId);
	    ModelAndView model = new ModelAndView("VendorForm");
	    model.addObject("vendor", vendor);
	 
	    return model;
	}
	
	@RequestMapping(value = "/saveVendor", method = RequestMethod.POST)
	public ModelAndView saveVendor(@ModelAttribute Vendor vendor) {
		vendorDAO.saveOrUpdate(vendor);
	    return new ModelAndView("redirect:./vendor");
	}
	
	@RequestMapping(value = "/deleteVendor", method = RequestMethod.GET)
	public ModelAndView deleteVendor(HttpServletRequest request) {
	    int vendorId = Integer.parseInt(request.getParameter("id"));
	    vendorDAO.delete(vendorId);
	    return new ModelAndView("redirect:./vendor");
	}
	
	@RequestMapping(value = "/newVendor", method = RequestMethod.GET)
	public ModelAndView newVendor(ModelAndView model) {
		Vendor vendor = new Vendor();
	    model.addObject("vendor", vendor);
	    model.setViewName("VendorForm");
	    return model;
	}	
}
