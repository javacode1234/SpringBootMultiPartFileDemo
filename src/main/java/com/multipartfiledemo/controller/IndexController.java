package com.multipartfiledemo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Product;
import com.multipartfiledemo.service.ProductService;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private ProductService ps;
	
	@GetMapping("/save-image-page")
	public String getIndexPage(Model model) {
		System.out.println("....index.html - home page....");
		model.addAttribute("product", new Product());
		
		for(Product p : ps.getAllProducts()) {
			//System.out.println(Base64.getEncoder().encodeToString(p.getImagedata()));
			p.setImageString(Base64.getEncoder().encodeToString(p.getImagedata()));
		}
		model.addAttribute("products", ps.getAllProducts());
		//System.out.println();
		
		return "index";
	}
	
	@PostMapping("/save-image")
	public String saveProductWithImage( @RequestParam(value="imagedata") MultipartFile file,
										@ModelAttribute(value="product") Product product)throws IOException {
		
		byte[] image = Base64.getEncoder().encode(file.getBytes());
		byte[] image2 = Base64.getDecoder().decode(image);
		product.setImagedata(image2);
		ps.saveProductWithImage(product);
				
		return "index";
	}
	

}
