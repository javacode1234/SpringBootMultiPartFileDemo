package com.multipartfiledemo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multipartfiledemo.model.Document;
import com.multipartfiledemo.repo.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository dr;
	
	public Document saveDocument(MultipartFile file) throws IOException {
		String documentName = file.getOriginalFilename();
		String contentType = file.getContentType();
		byte[] ducumentData = file.getBytes();
		try {
			Document doc = new Document(documentName, contentType, ducumentData);
			return dr.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Document> getFile(Integer id){
		return dr.findById(id);
	}
	
	public List<Document> getFiles(){
		return dr.findAll();
	}
}
