package com.multipartfiledemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multipartfiledemo.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
