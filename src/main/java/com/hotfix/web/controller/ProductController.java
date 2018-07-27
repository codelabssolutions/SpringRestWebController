package com.hotfix.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotfix.dto.ProductDTO;
import com.hotfix.services.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
   
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("productResults",productService.getProducts(model));
		return "welcome";
	}
	@RequestMapping("/addproduct")
	public String addProduct(@ModelAttribute("productdata") ProductDTO data)  {
		productService.addProduct(data);
		return "redirect:/";
	}
	@RequestMapping(value="/product")
    public String product(Model model) {
		  model.addAttribute("productdata", new ProductDTO()); 
          return "product";
    }
	@RequestMapping(value="/delete")
	public String deleteProduct(HttpServletRequest request) {
		 productService.deleteProduct(request);
		 return "redirect:/";
	}
	@RequestMapping("/home")
	public String home(Map<String, Object> model)  {
		model.put("productResults",productService.getProducts(model));
		return "redirect:/";
	}

    

}