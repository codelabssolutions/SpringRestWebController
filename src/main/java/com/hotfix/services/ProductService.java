package com.hotfix.services;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hotfix.dto.ProductDTO;

@Service
public class ProductService {
	@Autowired
	JdbcTemplate jdbcTemplate;
   
	
	 public  List<ProductDTO> getProducts(Map<String, Object> model) {
		  List<ProductDTO> results = jdbcTemplate.query("SELECT id, name,type,description,price,unit FROM product", new RowMapper<ProductDTO>() {
			  @Override
				public ProductDTO mapRow(ResultSet rs, int row) throws SQLException {
					return new ProductDTO(rs.getString(1), rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(5),rs.getString(6));
				}
			});
		
		    
		return results;
	}
	
	public void addProduct(@ModelAttribute("productdata") ProductDTO data)  {
		jdbcTemplate.update("INSERT INTO product(id,name,type, description, price, unit) VALUES(?,?,?,?,?,?)", data.getId(), data.getName(),data.getType(),data.getDescription(),data.getPrice(),data.getUnit());
		
	}
	public void deleteProduct(HttpServletRequest request) {
		     String id=request.getParameter("id");
		     jdbcTemplate.update("delete  FROM product where id="+id);
	}

}