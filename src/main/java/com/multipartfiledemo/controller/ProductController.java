package com.multipartfiledemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Product;
import com.multipartfiledemo.service.ProductService;


@Controller
@RestController
@RequestMapping
public class ProductController {

	@Autowired
	private ProductService ps;
	
	@PostMapping("/save")
	public String saveImageOrFie( @RequestParam("file") MultipartFile file, 
								  @RequestParam("name") String name, 
								  @RequestParam("description") String description )throws IOException {
		
		
		return ps.saveProductToDb(file, name, description);
	}
	
	
	
}
