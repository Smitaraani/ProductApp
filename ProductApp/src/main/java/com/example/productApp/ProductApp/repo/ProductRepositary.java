 package com.example.productApp.ProductApp.repo;
 
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.productApp.ProductApp.Entity.Product;
@Repository

public interface ProductRepositary extends JpaRepository<Product, Integer>{
	
	
	
	@Query
	("SELECT p FROM Product p " +
	           "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
	           "AND (:category IS NULL OR p.category = :category) " +
	           "AND (:rating IS NULL OR p.rating = :rating) " +
	           "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
	           "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
	           "AND p.active = true " +
	           "AND p.deleted = false")
	    List<Product> searchProducts(
	            @Param("name") String name,
	            @Param("category") String category,
	            @Param("rating") Integer rating,
	            @Param("minPrice") Double minPrice,
	            @Param("maxPrice") Double maxPrice);
	   
	   
	   
	   
	    List<Product> findByDeletedIsFalse();
	    Optional<Product> findByIdAndDeletedIsFalse(int id);
	   
	   
	    List<Product> findByActiveIsTrueAndDeletedIsFalse();
	   
	    Page<Product> findByActiveIsTrueAndDeletedIsFalse(Pageable pageable);
	}


