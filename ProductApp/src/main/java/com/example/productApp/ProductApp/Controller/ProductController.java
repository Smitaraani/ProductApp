package com.example.productApp.ProductApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productApp.ProductApp.Entity.Product;
import com.example.productApp.ProductApp.exce.ResourceNotFoundException;
import com.example.productApp.ProductApp.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	   private ProductService productService;

	   @PostMapping    //add
	   public ResponseEntity<Product> addProduct(@RequestBody Product product) {
	       Product createdProduct = productService.addProduct(product);
	       return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	   }
	   
	   @GetMapping("/activeproduct")//delete false& activetrueandfalse// fetch
	   public ResponseEntity<List<Product>> getActiveProducts() {
	       List<Product> products = productService.getAllActiveProducts();
	       return new ResponseEntity<>(products, HttpStatus.OK);
	   }
	   
	   @DeleteMapping("/{id}")    //delete
	   public ResponseEntity<Void> removeProduct(@PathVariable int id) throws ResourceNotFoundException {
	       productService.removeProduct(id);
	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }
	   
	   
	   @PutMapping("/{id}")   //edit
	   public ResponseEntity<Product> editProduct(@PathVariable int id, @RequestBody Product updatedProduct) throws ResourceNotFoundException {
	       Product updated = productService.editProduct(id, updatedProduct);
	       return new ResponseEntity<>(updated, HttpStatus.OK);
	   }
	   
	   
	  

	  
	   
	   
	   
	   @GetMapping("/active")//active(true)&delete(false)//fetch only active data
	   public ResponseEntity<List<Product>> getActiveAndNonDeletedProducts() {
	       List<Product> activeProducts = productService.getActiveAndNonDeletedProducts();
	       return new ResponseEntity<>(activeProducts, HttpStatus.OK);
	   }
	   
	   
	   @GetMapping("/search")//search by name,cat,rating,price   //search
	   public ResponseEntity<List<Product>> searchProducts(
	           @RequestParam(required = false) String name,
	           @RequestParam(required = false) String category,
	           @RequestParam(required = false) Integer rating,
	           @RequestParam(required = false) Double minPrice,
	           @RequestParam(required = false) Double maxPrice) {
	       List<Product> products = productService.searchProducts(name, category, rating, minPrice, maxPrice);
	       return new ResponseEntity<>(products, HttpStatus.OK);
	   }

	   
	   
	   @PutMapping("/{id}/activate")//active(true)
	   public ResponseEntity<Product> activateProduct(@PathVariable int id) throws ResourceNotFoundException {
	       Product activatedProduct = productService.activateOrDeactivateProduct(id, true);
	       return new ResponseEntity<>(activatedProduct, HttpStatus.OK);
	   }

	   @PutMapping("/{id}/deactivate")//active(false)
	   public ResponseEntity<Product> deactivateProduct(@PathVariable int id) throws ResourceNotFoundException {
	       Product deactivatedProduct = productService.activateOrDeactivateProduct(id, false);
	       return new ResponseEntity<>(deactivatedProduct, HttpStatus.OK);
	   }
	   
	   
	   @GetMapping("/pagignation")//pagignation
	   public ResponseEntity<Page<Product>> getActiveAndNonDeletedProducts(
	           @RequestParam(defaultValue = "0") int page,
	           @RequestParam(defaultValue = "10") int size) {
	       Pageable pageable = PageRequest.of(page, size);
	       Page<Product> products = productService.getActiveAndNonDeletedProductsPaginated(pageable);
	       return new ResponseEntity<>(products, HttpStatus.OK);
	   }
	}


