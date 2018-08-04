package com.hotfix.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.hotfix.jpa.enitity.Product;

public class ProductProcessor implements ItemProcessor<Product, Product> {
	
    private static final Logger log = LoggerFactory.getLogger(ProductProcessor.class);
    
    @Override
    public Product process(final Product productDTO) throws Exception {
    	
    	final String id=productDTO.getId();
    	final String name=productDTO.getName();
    	final String description=productDTO.getDescription();
    	final String type=productDTO.getType();
    	final String price=productDTO.getPrice();
    	final String unit=productDTO.getUnit();
    	
        final Product transformedProductDTO = new Product(id, name, description,type,price,unit);

        log.info("Converting (" + productDTO + ") into (" + transformedProductDTO + ")");

        return transformedProductDTO;
    }

}
