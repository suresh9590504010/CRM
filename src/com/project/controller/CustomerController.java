package com.project.controller;


import java.util.List;
import java.util.Map;

//import jdk.nashorn.internal.objects.annotations.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.CustomerDAO;
import com.project.entity.Customer;
import com.project.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
  private CustomerService customerService;
	
 @RequestMapping("/list")
	public String listCustomers(Model theModel){
		 
		//get a customers from customerDAO
		List<Customer> thecustomers=customerService.getCustomers();
		
		//and add to the model
		theModel.addAttribute("customers",thecustomers);
		return "list-customers";
	}

@RequestMapping("/showFormForAdd")
public String showFormForAdd(Model theModel){
	
	Customer thecustomer=new Customer();
	theModel.addAttribute("customer", thecustomer);
	return "customer-form";
	
}

@RequestMapping(path="saveCustomer", method=RequestMethod.POST)
public String saveCustomer(Customer thecustomer,Model theModel){
	
	theModel.addAttribute("customer", thecustomer);
	
	customerService.saveCustomer(thecustomer);
	
	return "redirect:/customer/list" ;
	
}

@RequestMapping(path="showFormForUpdate",method=RequestMethod.GET)
 public String showFormForUpadate(@RequestParam("customerId") int theId, Model themodel){
	
	Customer thecustomer=customerService.getCustomer(theId);
themodel.addAttribute("customer", thecustomer);
	return "customer-form";
	
}
@RequestMapping(path="delete",method=RequestMethod.GET)

public String delete(@RequestParam("customerId") int theId){
	customerService.deleteCustomer(theId);
	return "redirect:/customer/list" ;
	
}


}
