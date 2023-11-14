package com.example.productApp.ProductApp.service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.productApp.ProductApp.Entity.Product;
import com.example.productApp.ProductApp.exce.ResourceNotFoundException;
import com.example.productApp.ProductApp.repo.ProductRepositary;

@Service
public class ProductService {

	@Autowired
	   private ProductRepositary productRepositary;

	   public Product addProduct(Product product) {
	       // Implement the logic to add a new product
	    return productRepositary.save(product);
	   }

	 
	   
	   public void removeProduct(int id) throws ResourceNotFoundException {
	       Optional<Product> optionalProduct = productRepositary.findByIdAndDeletedIsFalse(id);
	       if (optionalProduct.isPresent()) {
	           Product product = optionalProduct.get();
	           product.setDeleted(true);
	          productRepositary.save(product);
	       } else {
	           throw new ResourceNotFoundException("Product not found with id " + id);
	       }
	   }

	   public List<Product> getAllActiveProducts() {
	       return productRepositary.findByDeletedIsFalse();
	   }

	   public Product editProduct(int id, Product updatedProduct) throws ResourceNotFoundException {
	       Optional<Product> optionalProduct = productRepositary.findByIdAndDeletedIsFalse(id);
	       if (optionalProduct.isPresent()) {
	           Product existingProduct = optionalProduct.get();
	           // Update the product properties
	           existingProduct.setName(updatedProduct.getName());
	           existingProduct.setCategory(updatedProduct.getCategory());
	           existingProduct.setDescription(updatedProduct.getDescription());
	           existingProduct.setRating(updatedProduct.getRating());
	           existingProduct.setCode(updatedProduct.getCode());
	           existingProduct.setActive(updatedProduct.isActive());
	           existingProduct.setPrice(updatedProduct.getPrice());
	           
	           
	           
	           // Save the updated product
	           return productRepositary.save(existingProduct);
	       } else {
	           throw new ResourceNotFoundException("Product not found with id " + id);
	       }
	   }

	   public List<Product> searchProducts(String name, String category, Integer rating, Double minPrice, Double maxPrice) {
	       // Implement the logic to search and filter products
	    return productRepositary.searchProducts(name, category, rating, minPrice, maxPrice);
	   }

	   
	   
	   public List<Product> getActiveAndNonDeletedProducts() {
	       return productRepositary.findByActiveIsTrueAndDeletedIsFalse();
	   }
	   
	   
	   public Product activateOrDeactivateProduct(int id, boolean active) throws ResourceNotFoundException {
	       Optional<Product> optionalProduct = productRepositary.findByIdAndDeletedIsFalse(id);
	       if (optionalProduct.isPresent()) {
	           Product product = optionalProduct.get();
	           product.setActive(active);
	           return productRepositary.save(product);
	       } else {
	           throw new ResourceNotFoundException("Product not found with id " + id);
	       }
	   }
	   
	   
	   
	   public Page<Product> getActiveAndNonDeletedProductsPaginated(Pageable pageable) {
	       return productRepositary.findByActiveIsTrueAndDeletedIsFalse(pageable);
	   }
	}
	   
	   


