package com.hotfix.rest.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotfix.common.responses.domainobject.ResponseEntity;
import com.hotfix.dto.ProductDTO;
import com.hotfix.request.domainobject.ProductRequest;
import com.hotfix.services.ProductService;


@RestController
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/productsync",method = RequestMethod.POST)
    public ResponseEntity product(@RequestBody ProductRequest productRequest) {
	   	ResponseEntity responseEntity=new ResponseEntity();
	   	try {
			   	UUID uuid = UUID.randomUUID();
			   	ProductDTO data=new ProductDTO();
			   	data.setDescription(productRequest.getDescription());
			   	data.setId(uuid.toString());
			   	data.setName(productRequest.getName());
			   	data.setType(productRequest.getType());
			   	data.setUnit(productRequest.getUnit());
			   	productService.addProduct(data);
			    responseEntity.setStatus("200");
	     	    return responseEntity;
	   	}catch(Exception ex){
	   		    responseEntity.setStatus("500");
	         	return responseEntity;
	   	}
   
   }
}