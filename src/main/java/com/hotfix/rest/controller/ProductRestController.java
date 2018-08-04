package com.hotfix.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotfix.common.responses.domainobject.ProductResponse;
import com.hotfix.common.responses.domainobject.ProductResponseList;
import com.hotfix.jpa.data.repository.ProductRepository;
import com.hotfix.jpa.enitity.Product;
import com.hotfix.request.domainobject.ProductRequest;




@RestController
public class ProductRestController {
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(value ="/productsync",method = RequestMethod.POST)
    public ResponseEntity product(@RequestBody ProductRequest productRequest) {
	   	try {
			   	UUID uuid = UUID.randomUUID();
			   	Product data=new Product();
			   	data.setDescription(productRequest.getDescription());
			   	data.setId(uuid.toString());
			   	data.setName(productRequest.getName());
			   	data.setType(productRequest.getType());
			   	data.setUnit(productRequest.getUnit());
			   	productRepository.save(data);
			   	
				List<Product> productList=productRepository.findAll();
				System.out.println("============================================"+productList.size());
				for(Product product:productList ) {
					
					System.out.println("============================================"+product.getName());
				}
			    //productService.addProduct(data);
			   return new ResponseEntity(HttpStatus.OK);
	   	}catch(Exception ex){
	   		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	   	}
   
   }  
	
	@RequestMapping(value ="/getproductlist",method = RequestMethod.GET)
    public ResponseEntity productList() {
	  	   	try {
			  List<Product> productList=productRepository.findAll();
			  ProductResponseList productListDomain=new ProductResponseList();
			  List<ProductResponse> ProductResponseList=new ArrayList<>();
			  for(Product product:productList ) {
				  ProductResponse  productDomain=new ProductResponse();
				  productDomain.setDescription(product.getDescription());
				  productDomain.setId(product.getId());
				  productDomain.setName(product.getName());
				  productDomain.setUnit(product.getUnit());
				  ProductResponseList.add(productDomain);
				} 
			 productListDomain.setProductList(ProductResponseList);
			 return new ResponseEntity(productListDomain,HttpStatus.OK);
	   	}catch(Exception ex){
	   	 return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	   	}
   
   } 
	
}