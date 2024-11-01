package com.multipartfiledemo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Document;
import com.multipartfiledemo.model.User;
import com.multipartfiledemo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo ur;
	
	public void saveUser(User u) {
		ur.save(u);
	}
	
	public User saveUser(String name, String email, MultipartFile file) throws IOException {
		//String documentName = file.getOriginalFilename();
		//String contentType = file.getContentType();
		byte[] byteResim = file.getBytes();
		String stringResim = Base64.getEncoder().encodeToString(byteResim);
		try {
			User nu = new User(name, email, byteResim, stringResim);
			return ur.save(nu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<User> getFile(Integer id){
		return ur.findById(id);
	}
	
	public List<User> getUsers(){			
		return ur.findAll();
	}
	
	public Optional<User> getById(Integer id){
		return ur.findById(id);
	}
	
//	public User saveImage(MultipartFile file, User u) throws IOException {
//		//String documentName = file.getOriginalFilename();
//		//String contentType = file.getContentType();
//		User nu = new User();
//		nu.setEmail(u.getEmail());
//		nu.setName(u.getName());
//		
//		byte[] resimData = file.getBytes();
//		try {
//			nu.setResim(resimData);
//			return ur.save(nu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return nu;
//	}

}
