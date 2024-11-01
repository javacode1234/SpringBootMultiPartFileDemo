package com.multipartfiledemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Document;
import com.multipartfiledemo.service.DocumentService;



@Controller
public class DocumentController {
	@Autowired
	private DocumentService ds;
	
	@GetMapping("/document")
	public String getMethodName(Model model) {
		List<Document> documents = ds.getFiles();
		model.addAttribute("documents", documents);
		return "document-page";
	}
	
	@PostMapping("/save-document")
	public String postMethodName(@RequestParam("files") MultipartFile[] files) throws IOException {
		for(MultipartFile file : files) {
			ds.saveDocument(file);
		}
		return "redirect:/document";
	}
	
	@GetMapping("/download-file/{id}")
	public ResponseEntity<ByteArrayResource> getMethodName(@PathVariable("id") Integer id) {
		Document doc = ds.getFile(id).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+ doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	
	

}
