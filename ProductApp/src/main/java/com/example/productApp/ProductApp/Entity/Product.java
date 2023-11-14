 package com.example.productApp.ProductApp.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "prodct_tbl")

public class Product {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private int code;
	    private String name;
	    private String description;
	    @CreationTimestamp 
	    @Temporal(TemporalType.TIMESTAMP) 
	    @Column(name = "created_at")
	    private Date  createdOn;
	    private int rating;
	    private String category;
	    private double price;
	    private boolean active;
	    private boolean deleted;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Product(int id, int code, String name, String description, Date createdOn, int rating, String category,
				double price, boolean active, boolean deleted) {
			super();
			this.id = id;
			this.code = code;
			this.name = name;
			this.description = description;
			this.createdOn = createdOn;
			this.rating = rating;
			this.category = category;
			this.price = price;
			this.active = active;
			this.deleted = deleted;
		}
		@Override
		public String toString() {
			return "Product [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description
					+ ", createdOn=" + createdOn + ", rating=" + rating + ", category=" + category + ", price=" + price
					+ ", active=" + active + ", deleted=" + deleted + "]";
		}
		
	    
	    

}
