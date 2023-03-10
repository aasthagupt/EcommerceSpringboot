package com.ecom.ecomspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.ecomspringboot.beans.ProductBean;
import com.ecom.ecomspringboot.entity.Category;
import com.ecom.ecomspringboot.entity.Product;
import com.ecom.ecomspringboot.exception.ProductNotExistsException;
//import com.ecom.ecomspringboot.exception.ProductNotExistsException;
import com.ecom.ecomspringboot.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}
	
	public Product addProduct(Product product)
    {
		Product p=productRepository.save(product);
        return p;
    }
	
	public void removeProductById(long id){
    	
		productRepository.deleteById(id);
		ResponseEntity.ok("deleted");
		
   }
	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}
	
//	public Product updateProductByid(int id,Product updateProduct){
//		 Product product=productRepository.findById((long) id).get();
//	        ProductBean p=new ProductBean();
//	        p.setId(product.getId());
//	        p.setProd_name(product.getProd_name());
//	        p.setCategoryId(product.getCategory_id());
//	        p.setPrice(product.getPrice());
//	        p.setQty(product.getQty());
//	        p.setDescription(product.getDescription());
//	        Product c= productRepository.save(product);
//	    	return c; 
//	}
	
	public Product findById(Long product_id)throws ProductNotExistsException
	{
		Optional<Product> optionalProduct=productRepository.findById( product_id);
		if(optionalProduct.isEmpty())
		{
			throw new ProductNotExistsException("product is is invalid:"+product_id);
		}
		return optionalProduct.get();
		
	}
   
}
