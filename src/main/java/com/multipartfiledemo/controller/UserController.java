package com.multipartfiledemo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.User;
import com.multipartfiledemo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/userpage")
	public String getUserPage(Model model) {
		
		List<User> users = us.getUsers();
		model.addAttribute("users", users);
		model.addAttribute("baslik", "Yeni Kayıt");
		return "userpage";
	}
	
	@PostMapping("/saveuser")
	public String postMethodName(@RequestParam("resim") MultipartFile file,
								 @RequestParam("name") String name,
								 @RequestParam("email") String email) throws IOException {
		
		us.saveUser(name, email, file);
		
		return "redirect:/user/userpage";
	}
	
	@RequestMapping("/getbyid")
	@ResponseBody
	public Optional<User> getUserById(Integer id) {
		return us.getById(id);
	}
	
	@PostMapping("/updateuser/{id}")
	public String updateUserById(@PathVariable Integer id, 
								 @RequestParam("resim") MultipartFile file,
								 @RequestParam("name")String name, 
								 @RequestParam("email")String email ) throws IOException {
		User selectedUser = us.getById(id).get();
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			selectedUser.setName(name);
			selectedUser.setEmail(email);
			selectedUser.setResim(file.getBytes());
			selectedUser.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			us.updateUserById(selectedUser);
		}
		
		selectedUser.setName(name);
		selectedUser.setEmail(email);

		us.updateUserById(selectedUser);
		
		return "redirect:/user/userpage";
	}
	
	@RequestMapping(value="/delbyid", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(Integer id) {
		us.deleteUserById(id);
		return "redirect:/user/userpage";
	}
	
//	@PostMapping("/saveuser")
//	public String saveUser(@ModelAttribute User u, @RequestParam("resim") MultipartFile resim) throws IOException {
//		if (resim != null && !resim.getOriginalFilename().isEmpty()) {
//	       User nuser = new User(u.getName(), u.getEmail(),resim.getBytes());
//	       //us.saveImage(resim, nuser);        
//	        us.saveUser(nuser);
//	        return "redirect:/user/userpage";
//	    }
		
		
//-----------------------------------------------------------------------------------------		
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		try (FileInputStream fis = new FileInputStream(fileName)) {
//			byte[] bytes = new byte[fis.available()];
//			System.out.println(bytes);
//			User user = User.builder()
//					.resim(bytes)
//					.email(u.getEmail())
//					.name(u.getName())
//					.build();
//		us.saveUser(user);
//		}		
//-----------------------------------------------------------------------------------------		
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		try (FileInputStream fis = new FileInputStream(fileName)) {
//			byte[] bytes = fis.readAllBytes();
//			u.setResim(bytes);
//		}
//		us.saveUser(u);
		
//		return "redirect:/user/userpage";
//	}
	
	

}
