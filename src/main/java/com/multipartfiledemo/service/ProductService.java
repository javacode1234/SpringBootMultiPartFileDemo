package com.multipartfiledemo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Product;
import com.multipartfiledemo.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo pr;	
	
	public String saveProductToDb(MultipartFile file, String name, String description) throws IOException {

		Product p = new Product();
		
		p.setName(name);
		p.setDescription(description);
		p.setImagedata(file.getBytes());
		
		pr.save(p);
		
		return "İmage saved in DB";
		
	}
	
	public void saveProduct(Product p, MultipartFile file) throws IOException {
		p.setName(p.getName());
		p.setDescription(p.getDescription());
		p.setImagedata(file.getBytes());		
		pr.save(p);			
	}
	
	public void saveProductWithImage(Product p) throws IOException {
		
		pr.save(p);
	}

	public List<Product> getAllProducts() {
		return pr.findAll();
	}

	public void saveProductWithImage(Product product, MultipartFile file) throws IOException {
		
		Product p = new Product();
		
		if(file!=null && !file.isEmpty()) {
            byte[] fileBytes = file.getBytes();            
            p.setImagedata(fileBytes);
        }else{
            p.setImagedata(null);
        }

        pr.save(p);
		
	}

}
