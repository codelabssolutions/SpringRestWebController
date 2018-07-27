package com.hotfix.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.hotfix.dto.ProductDTO;

public class ProductProcessor implements ItemProcessor<ProductDTO, ProductDTO> {
	
    private static final Logger log = LoggerFactory.getLogger(ProductProcessor.class);
    
    @Override
    public ProductDTO process(final ProductDTO productDTO) throws Exception {
    	
    	final String id=productDTO.getId();
    	final String name=productDTO.getName();
    	final String description=productDTO.getDescription();
    	final String type=productDTO.getType();
    	final String price=productDTO.getPrice();
    	final String unit=productDTO.getUnit();
    	
        final ProductDTO transformedProductDTO = new ProductDTO(id, name, description,type,price,unit);

        log.info("Converting (" + productDTO + ") into (" + transformedProductDTO + ")");

        return transformedProductDTO;
    }

}
